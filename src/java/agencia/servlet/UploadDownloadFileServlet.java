package agencia.servlet;

import agencia.ficheros.ImprimeInventario;
import agencia.ficheros.ProcesaFicheros;
import agencia.modelo.Usuario;
import agencia.persistencia.UserDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@WebServlet("/UploadDownloadFileServlet")
public class UploadDownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServletFileUpload uploader = null;
	@Override
	public void init() throws ServletException{
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
		fileFactory.setRepository(filesDir);
		this.uploader = new ServletFileUpload(fileFactory);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = "Inventario"+System.currentTimeMillis()+".xlsx";
		ImprimeInventario genInv = new ImprimeInventario();
                
                if(fileName == null || fileName.equals("")){
			throw new ServletException("File Name can't be null or empty");
		}
		File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileName);
		file.createNewFile();
                if(!file.exists()){
                    throw new ServletException("File doesn't exists on server.");
		}
		System.out.println("File location on server::"+file.getAbsolutePath());
                file = genInv.generaInventario(file);
		ServletContext ctx = getServletContext();
		InputStream fis = new FileInputStream(file);
		String mimeType = ctx.getMimeType(file.getAbsolutePath());
		response.setContentType(mimeType != null? mimeType:"application/octet-stream");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		ServletOutputStream os       = response.getOutputStream();
		byte[] bufferData = new byte[1024];
		int read=0;
		while((read = fis.read(bufferData))!= -1){
			os.write(bufferData, 0, read);
		}
		os.flush();
		os.close();
		fis.close();
		System.out.println("File downloaded at client successfully");
                request.getRequestDispatcher("/inventario.jsp").forward(request, response);
	}

        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ProcesaFicheros proFile = new ProcesaFicheros();
            File file = null;
            
            if(!ServletFileUpload.isMultipartContent(request)){
                    throw new ServletException("Content type is not multipart/form-data");
            }

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            try {
                List<FileItem> fileItemsList = uploader.parseRequest(request);
                Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
                if(fileItemsIterator.hasNext()){ 
                    FileItem fileItem = fileItemsIterator.next();
                    System.out.println("FieldName="+fileItem.getFieldName());
                    System.out.println("FileName="+fileItem.getName());
                    System.out.println("ContentType="+fileItem.getContentType());
                    System.out.println("Size in bytes="+fileItem.getSize());

                    file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());
                    System.out.println("Absolute Path at server="+file.getAbsolutePath());
                    fileItem.write(file);
                }
                System.out.println("REQUEST " + request.getParameter("aviones"));
                if(request.getParameter("aviones") != null && file != null) {
                    proFile.procesaAviones(file);
                    request.getSession().setAttribute("result", "ok");
                    request.getRequestDispatcher("/gestionAviones.jsp").forward(request, response);
                } else if(request.getParameter("adminComp") != null && file != null) {
                    proFile.procesaAdmins(file);
                    request.getSession().setAttribute("result", "ok");
                    request.getRequestDispatcher("/gestionAdminComp.jsp").forward(request, response);
                } else if(request.getParameter("aero") != null && file != null) {
                    proFile.procesaAeropuertos(file);
                    request.getSession().setAttribute("result", "ok");
                    request.getRequestDispatcher("/gestionAero.jsp").forward(request, response);
                } else if(request.getParameter("comp") != null && file != null) {
                    proFile.procesaCompanias(file);
                    request.getSession().setAttribute("result", "ok");
                    request.getRequestDispatcher("/gestionComp.jsp").forward(request, response);
                } else if(request.getParameter("vuelos") != null && file != null) {
                    proFile.procesaVuelos(file);
                    request.getSession().setAttribute("result", "ok");
                    request.getRequestDispatcher("/gestionVuelos.jsp").forward(request, response);
                } else if(request.getParameter("hoteles") != null && file != null) {
                    Usuario user;
                    UserDAO dao = new UserDAO();
                    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                    String name = auth.getName(); //get logged in username

                    user = dao.buscarPorUsername(name);
                    proFile.procesaHoteles(file, user.getRefCompañia());
                    
                    request.getSession().setAttribute("result", "ok");
                    request.getRequestDispatcher("/gestionHoteles.jsp").forward(request, response);
                }
                
            } catch (FileUploadException e) {
                System.out.println(e);
                out.write("Exception in uploading file.");
            } catch (Exception e) {
                System.out.println(e);
                out.write("Exception in uploading file.");
            } finally {
                out.close();
            }
	}
}
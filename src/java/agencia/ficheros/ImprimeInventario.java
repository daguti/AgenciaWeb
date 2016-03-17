/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia.ficheros;

import agencia.modelo.Avion;
import agencia.modelo.DisponibilidadHotel;
import agencia.modelo.Hotel;
import agencia.modelo.Usuario;
import agencia.modelo.Vuelo;
import agencia.persistencia.GestionDAO;
import agencia.persistencia.UserDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author David
 */
public class ImprimeInventario {
    public File generaInventario(File file) throws IOException {
        //Variable definition
        Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        UserDAO userDao = new UserDAO();
        GestionDAO dao = new GestionDAO();
        Sheet sheet = wb.createSheet("Inventario");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        Usuario user = userDao.buscarPorUsername(name);
        Row row;
        int i = 1;
        
        if(user.getUserType() == 1) {
           createAereoColumnHeaders(sheet, wb, createHelper);
           List<Vuelo> vueloList = dao.getVuelosInventario(user.getRefCompañia());
           for(Vuelo vuelo : vueloList) {
               row = sheet.createRow(i);
               addVuelosData(row, vuelo, createHelper);
               i++;
           }
        } else {
           createHotelesColumnHeaders(sheet, wb, createHelper);
           List<DisponibilidadHotel> dispoList = dao.getDisponiHotelesInventario(user.getRefCompañia());
           Hotel hotel;
           for(DisponibilidadHotel dispo : dispoList) {
               hotel = dao.getHotelByRef(dispo.getRefHotel());
               row = sheet.createRow(i);
               addHotelesData(row, dispo, hotel, createHelper);
               i++;
           }
        }
        FileOutputStream fileOut = new FileOutputStream(file);
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
        return file;
    }
    
    private void createAereoColumnHeaders(Sheet sheet, Workbook wb, CreationHelper createHelper) {
        CellStyle style = wb.createCellStyle();
        
        style.setBorderBottom(CellStyle.BORDER_MEDIUM);
        style.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(createHelper.createRichTextString("ORIGEN"));
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue(createHelper.createRichTextString("DESTINO"));
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue(createHelper.createRichTextString("FECHA VUELO"));
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue(createHelper.createRichTextString("HORA VUELO"));
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue(createHelper.createRichTextString("DISPONIBLE TURISTA"));
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue(createHelper.createRichTextString("DISPONIBLE ECONOMICA"));
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue(createHelper.createRichTextString("DISPONIBLE BUSSINES"));
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue(createHelper.createRichTextString("RESERVADO TURISTA"));
        cell.setCellStyle(style);
        cell = row.createCell(8);
        cell.setCellValue(createHelper.createRichTextString("RESERVADO ECONOMICA"));
        cell.setCellStyle(style);
        cell = row.createCell(9);
        cell.setCellValue(createHelper.createRichTextString("RESERVADO BUSSINES"));
        cell.setCellStyle(style);
        cell = row.createCell(10);
        cell.setCellValue(createHelper.createRichTextString("PRECIO TURISTA"));
        cell.setCellStyle(style);
        cell = row.createCell(11);
        cell.setCellValue(createHelper.createRichTextString("PRECIO ECONOMICA"));
        cell.setCellStyle(style);
        cell = row.createCell(12);
        cell.setCellValue(createHelper.createRichTextString("PRECIO BUSSINES"));
        cell.setCellStyle(style);
    }
    private void addVuelosData(Row row, Vuelo vuelo, CreationHelper createHelper) {
        Cell cell = row.createCell(0);
        Avion av = vuelo.getAvion();
        
        cell.setCellValue(createHelper.createRichTextString(vuelo.getAeroOrig().getNombre()));
        cell = row.createCell(1);
        cell.setCellValue(createHelper.createRichTextString(vuelo.getAeroDest().getNombre()));
        cell = row.createCell(2);
        cell.setCellValue(vuelo.getFecVuelo());
        cell = row.createCell(3);
        cell.setCellValue(createHelper.createRichTextString(vuelo.getHoraSalida()));
        cell = row.createCell(4);
        cell.setCellValue(vuelo.getNumTur());
        cell = row.createCell(5);
        cell.setCellValue(vuelo.getNumEco());
        cell = row.createCell(6);
        cell.setCellValue(vuelo.getNumBuss());
        cell = row.createCell(7);
        cell.setCellValue(av.getNumTur() - vuelo.getNumTur());
        cell = row.createCell(8);
        cell.setCellValue(av.getNumEco() - vuelo.getNumEco());
        cell = row.createCell(9);
        cell.setCellValue(av.getNumBuss() - vuelo.getNumBuss());
        cell = row.createCell(10);
        cell.setCellValue(vuelo.getPreTur());
        cell = row.createCell(11);
        cell.setCellValue(vuelo.getPreEco());
        cell = row.createCell(12);
        cell.setCellValue(vuelo.getPreBuss());
    }
    private void createHotelesColumnHeaders(Sheet sheet, Workbook wb, CreationHelper createHelper) {
        CellStyle style = wb.createCellStyle();
        
        style.setBorderBottom(CellStyle.BORDER_MEDIUM);
        style.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(createHelper.createRichTextString("NOMBRE"));
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue(createHelper.createRichTextString("FECHA"));
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue(createHelper.createRichTextString("DISPONIBLE INDIVIDUAL"));
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue(createHelper.createRichTextString("DISPONIBLE DOBLE"));
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue(createHelper.createRichTextString("DISPONIBLE SUPERIOR"));
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue(createHelper.createRichTextString("RESERVADO INDIVIDUAL"));
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue(createHelper.createRichTextString("RESERVADO DOBLE"));
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue(createHelper.createRichTextString("RESERVADO SUPERIOR"));
        cell.setCellStyle(style);
        cell = row.createCell(8);
        cell.setCellValue(createHelper.createRichTextString("PRECIO INDIVIDUAL"));
        cell.setCellStyle(style);
        cell = row.createCell(9);
        cell.setCellValue(createHelper.createRichTextString("PRECIO DOBLE"));
        cell.setCellStyle(style);
        cell = row.createCell(10);
        cell.setCellValue(createHelper.createRichTextString("PRECIO SUPERIOR"));
        cell.setCellStyle(style);
    }
    private void addHotelesData(Row row, DisponibilidadHotel dispo, Hotel hotel, CreationHelper createHelper) {
        Cell cell = row.createCell(0);
        cell.setCellValue(createHelper.createRichTextString(hotel.getNombre()));
        cell = row.createCell(1);
        cell.setCellValue(dispo.getFecha());
        cell = row.createCell(2);
        cell.setCellValue(dispo.getIndDisp());
        cell = row.createCell(3);
        cell.setCellValue(dispo.getDobDisp());
        cell = row.createCell(4);
        cell.setCellValue(dispo.getSupDisp());
        cell = row.createCell(5);
        cell.setCellValue(hotel.getHabInd() - dispo.getIndDisp());
        cell = row.createCell(6);
        cell.setCellValue(hotel.getHabDob() - dispo.getDobDisp());
        cell = row.createCell(7);
        cell.setCellValue(hotel.getHabSup() - dispo.getSupDisp());
        cell = row.createCell(8);
        cell.setCellValue(hotel.getPreInd());
        cell = row.createCell(9);
        cell.setCellValue(hotel.getPreDob());
        cell = row.createCell(10);
        cell.setCellValue(hotel.getPreSup());
    }
}

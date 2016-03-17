/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia.ficheros;

import agencia.modelo.Aeropuerto;
import agencia.modelo.Avion;
import agencia.modelo.CompaniaAerea;
import agencia.modelo.CompaniaHotelera;
import agencia.modelo.Hotel;
import agencia.modelo.Usuario;
import agencia.modelo.Vuelo;
import agencia.persistencia.GestionDAO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author David
 */
public class ProcesaFicheros {
    public void procesaAviones(File f) throws IOException, InvalidFormatException {
        InputStream inputStream= new ByteArrayInputStream(IOUtils.toByteArray(new FileInputStream(f)));
        int cont;
        Avion avion;
        GestionDAO dao = new GestionDAO();
        String num;
        
        Workbook wb = WorkbookFactory.create(inputStream);
        Sheet mySheet = wb.getSheetAt(0);
        Iterator<Row> rowIter = mySheet.rowIterator();
        rowIter.next();
        while(rowIter.hasNext()) {
            Row row = rowIter.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            cont = 0;
            avion = new Avion();
            while(cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if(cont == 0) {
                    avion.setDescripcion(cell.getStringCellValue());
                } else if (cont == 1) {
                    num = Double.toString(cell.getNumericCellValue());
                    avion.setNumEco(Integer.parseInt(num.substring(0, num.indexOf("."))));
                } else if(cont == 2) {
                    num = Double.toString(cell.getNumericCellValue());
                    avion.setNumTur(Integer.parseInt(num.substring(0, num.indexOf("."))));
                } else if(cont == 3) {
                    num = Double.toString(cell.getNumericCellValue());
                    avion.setNumBuss(Integer.parseInt(num.substring(0, num.indexOf("."))));
                } else if(cont == 4) {
                    avion.setCompania(dao.getCompañíaID(cell.getStringCellValue()));
                }
                cont++;
            }
            dao.storeAvion(avion);
        }
    }
    public void procesaAdmins(File f) throws IOException, InvalidFormatException {
        InputStream inputStream= new ByteArrayInputStream(IOUtils.toByteArray(new FileInputStream(f)));
        int cont;
        Usuario user;
        GestionDAO dao = new GestionDAO();
        
        Workbook wb = WorkbookFactory.create(inputStream);
        Sheet mySheet = wb.getSheetAt(0);
        Iterator<Row> rowIter = mySheet.rowIterator();
        rowIter.next();
        while(rowIter.hasNext()) {
            Row row = rowIter.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            cont = 0;
            user = new Usuario();
            while(cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if(cont == 0) {
                    user.setNombre(cell.getStringCellValue());
                } else if (cont == 1) {
                    user.setApellidos(cell.getStringCellValue());
                } else if(cont == 2) {
                    if(cell.getStringCellValue().equalsIgnoreCase("HOTELERA")) {
                      user.setUserType(2);
                    } else {
                      user.setUserType(1);
                    }
                } else if(cont == 3) {
                    user.setRefCompañia(dao.getCompañíaID(cell.getStringCellValue()).getRefCompañia());
                }
                cont++;
            }
            dao.storeAdmin(user);
        }
      }
      public void procesaAeropuertos(File f) throws IOException, InvalidFormatException {
        InputStream inputStream= new ByteArrayInputStream(IOUtils.toByteArray(new FileInputStream(f)));
        int cont;
        Aeropuerto aero;
        GestionDAO dao = new GestionDAO();
        String num;
        
        Workbook wb = WorkbookFactory.create(inputStream);
        Sheet mySheet = wb.getSheetAt(0);
        Iterator<Row> rowIter = mySheet.rowIterator();
        rowIter.next();
        while(rowIter.hasNext()) {
            Row row = rowIter.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            cont = 0;
            aero = new Aeropuerto();
            while(cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if(cont == 0) {
                    aero.setCodigo(cell.getStringCellValue());
                } else if (cont == 1) {
                    aero.setNombre(cell.getStringCellValue());
                } else if(cont == 2) {
                    aero.setCiudad(cell.getStringCellValue());
                } else if(cont == 3) {
                    aero.setPais(cell.getStringCellValue());
                }
                cont++;
            }
            dao.storeAeropuerto(aero);
        }
      }
      public void procesaCompanias(File f) throws IOException, InvalidFormatException {
        InputStream inputStream= new ByteArrayInputStream(IOUtils.toByteArray(new FileInputStream(f)));
        int cont;
        GestionDAO dao = new GestionDAO();
        String refComp = null;
        String nombre  = null;
        String pais    = null;
        boolean hotelera = false;
        
        Workbook wb = WorkbookFactory.create(inputStream);
        Sheet mySheet = wb.getSheetAt(0);
        Iterator<Row> rowIter = mySheet.rowIterator();
        rowIter.next();
        while(rowIter.hasNext()) {
            Row row = rowIter.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            cont = 0;
        
            while(cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if(cont == 0) {
                    refComp = cell.getStringCellValue();
                } else if (cont == 1) {
                    nombre = cell.getStringCellValue();
                } else if(cont == 2) {
                    pais = cell.getStringCellValue();
                } else if(cont == 3) {
                    if(cell.getStringCellValue().equalsIgnoreCase("hotelera")) {
                      hotelera = true;
                    } else {
                      hotelera = false;
                    }
                }
                cont++;
            }
            if(hotelera) {
              dao.storeCompaniaHotelera(new CompaniaHotelera(refComp, nombre, pais, new HashSet<Hotel>()));
            } else {
              dao.storeCompaniaAerea(new CompaniaAerea(refComp, nombre, pais, new HashSet<Avion>()));
            }
            
        }
      }
      
      public void procesaVuelos(File f) throws IOException, InvalidFormatException, ParseException {
        InputStream inputStream= new ByteArrayInputStream(IOUtils.toByteArray(new FileInputStream(f)));
        int cont;
        Vuelo vuelo;
        GestionDAO dao = new GestionDAO();
        String num;
        
        Workbook wb = WorkbookFactory.create(inputStream);
        Sheet mySheet = wb.getSheetAt(0);
        Iterator<Row> rowIter = mySheet.rowIterator();
        rowIter.next();
        while(rowIter.hasNext()) {
            Row row = rowIter.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            cont = 0;
            vuelo = new Vuelo();
            
            while(cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if(cont == 0) {
                    num = Double.toString(cell.getNumericCellValue());
                    Avion av = dao.getAvion(Integer.parseInt(num.substring(0, num.indexOf("."))));
                    vuelo.setAvion(av);
                    vuelo.setNumBuss(av.getNumBuss());
                    vuelo.setNumEco(av.getNumEco());
                    vuelo.setNumTur(av.getNumTur());
                } else if (cont == 1) {
                    vuelo.setFecVuelo(cell.getDateCellValue());
                } else if (cont == 2) {
                    vuelo.setHoraSalida(cell.getDateCellValue().toString());
                } else if (cont == 3) {
                    vuelo.setDuracion(cell.getDateCellValue().toString());
                } else if(cont == 4) {
                    vuelo.setPreTur(cell.getNumericCellValue());
                } else if(cont == 5) {
                    vuelo.setPreEco(cell.getNumericCellValue());
                } else if(cont == 6) {
                    vuelo.setPreBuss(cell.getNumericCellValue());
                } else if(cont == 7) {
                    vuelo.setAeroOrig(dao.getAero(cell.getStringCellValue())); //Falta query recupera aero por nombre
                } else if(cont == 8) {
                    vuelo.setAeroDest(dao.getAero(cell.getStringCellValue()));
                } 
                cont++;
            }
            dao.storeVuelo(vuelo);
        }
    }
      
    public void procesaHoteles(File f, String refCompania) throws IOException, InvalidFormatException, ParseException {
      InputStream inputStream= new ByteArrayInputStream(IOUtils.toByteArray(new FileInputStream(f)));
      int cont;
      Hotel hotel;
      GestionDAO dao = new GestionDAO();
      String num;

      Workbook wb = WorkbookFactory.create(inputStream);
      Sheet mySheet = wb.getSheetAt(0);
      Iterator<Row> rowIter = mySheet.rowIterator();
      rowIter.next();
      while(rowIter.hasNext()) {
          Row row = rowIter.next();
          Iterator<Cell> cellIterator = row.cellIterator();
          cont = 0;
          hotel = new Hotel();
          
          while(cellIterator.hasNext()) {
              Cell cell = cellIterator.next();
              if(cont == 0) {
                hotel.setNombre(cell.getStringCellValue());
              } else if (cont == 1) {
                num = Double.toString(cell.getNumericCellValue());
                hotel.setCategoria(Integer.parseInt(num.substring(0, num.indexOf("."))));
              } else if(cont == 2) {
                 hotel.setDescripcion(cell.getStringCellValue());
              } else if(cont == 3) {
                hotel.setCiudad(cell.getStringCellValue());
              } else if(cont == 4) {
                hotel.setDireccion(cell.getStringCellValue());
              } else if(cont == 5) {
                num = Double.toString(cell.getNumericCellValue());
                hotel.setHabInd(Integer.parseInt(num.substring(0, num.indexOf("."))));
              } else if(cont == 6) {
                hotel.setPreInd(cell.getNumericCellValue());
              } else if(cont == 7) {
                num = Double.toString(cell.getNumericCellValue());
                hotel.setHabDob(Integer.parseInt(num.substring(0, num.indexOf("."))));
              } else if(cont == 8) {
                hotel.setPreDob(cell.getNumericCellValue());
              } else if(cont == 9) {
                num = Double.toString(cell.getNumericCellValue());
                hotel.setHabSup(Integer.parseInt(num.substring(0, num.indexOf("."))));
              } else if(cont == 10) {
                hotel.setPreSup(cell.getNumericCellValue());
              }
              cont++;
          }
          hotel.setCompañia(dao.getHoteleraByRef(refCompania));
          dao.storeHotel(hotel, false);
      }
    }
}

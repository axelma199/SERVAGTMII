/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.controllers;

import cr.ac.una.reg.info.beans.BitacoraSolicitudBean;
import cr.ac.una.reg.info.dao.BitacoraSolicitudDao;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.tools.ManejoFacesContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

//import org.primefaces.context.RequestContext;


/**
 *
 * @author oscar
 */
@ManagedBean(name = "BitacoraSolicitudesController")
@ViewScoped
public class BitacoraSolicitudesController {
       private ArrayList<BitacoraSolicitudBean> arrayBitacoraSolicitud = new ArrayList<>();
       private String URLReporte;
       private BitacoraSolicitudBean bitacoraSolicitudSelected;
       private ManejoFacesContext manejoFacesContext;
        private static final String REDIRECCION_PAG_WARNING = "/SERVAGTMII/paginas/error.xhtml";

         /**mau**********/
          private List<BitacoraSolicitudBean> filtroDocumentosDisponible;
          ////////*****************************************/
    
       
   //Muestra el historial de los movimientos    
    public BitacoraSolicitudesController() {
        arrayBitacoraSolicitud = new ArrayList<>();
        listaBitacora();
    }
    
    //Muestra el historial de los movimientos  de una solicitud o eqquiparacion  
    public void listaBitacora(){
           try {
               BitacoraSolicitudDao bitacoraSolicitudDao=new BitacoraSolicitudDao();
               arrayBitacoraSolicitud=bitacoraSolicitudDao.ListarDr_regt_bitacora_solicitud();
           } catch (ExceptionConnection ex) {
               Logger.getLogger(BitacoraSolicitudesController.class.getName()).log(Level.SEVERE, null, ex);
           }
        
        
    }

    
    public void GenerarReporte(){
    
        try{
       
            URLReporte="/SERVAGTMII/ReporteBitacora?pNsolicitud="+this.bitacoraSolicitudSelected.getBit_sol_id();
            String reporte="javascript:window.open( '" + this.URLReporte + "','BITACORA','width=800,height=600');";
    
       //         RequestContext.getCurrentInstance().execute(reporte); 
    
       
        } catch (Exception ex) {
            this.manejoFacesContext.redireccionarFlujoWeb(REDIRECCION_PAG_WARNING);
        }
     }
     
    
    
    
    public ArrayList<BitacoraSolicitudBean> getArrayBitacoraSolicitud() {
        return arrayBitacoraSolicitud;
    }

    public void setArrayBitacoraSolicitud(ArrayList<BitacoraSolicitudBean> arrayBitacoraSolicitud) {
        this.arrayBitacoraSolicitud = arrayBitacoraSolicitud;
    }
       
     public String getURLReporte() {
        String concat = "/SERVAGTMII/ReporteBitacora?";
        
        URLReporte=concat;        
        return URLReporte;
    }

    public void setURLReporte(String URLReporte) {
        this.URLReporte = URLReporte;
    }

    public List<BitacoraSolicitudBean> getFiltroDocumentosDisponible() {
        return filtroDocumentosDisponible;
    }

    public void setFiltroDocumentosDisponible(List<BitacoraSolicitudBean> filtroDocumentosDisponible) {
        this.filtroDocumentosDisponible = filtroDocumentosDisponible;
    }

    public BitacoraSolicitudBean getBitacoraSolicitudSelected() {
        return bitacoraSolicitudSelected;
    }

    public void setBitacoraSolicitudSelected(BitacoraSolicitudBean bitacoraSolicitudSelected) {
        this.bitacoraSolicitudSelected = bitacoraSolicitudSelected;
    }
       
    
    
}

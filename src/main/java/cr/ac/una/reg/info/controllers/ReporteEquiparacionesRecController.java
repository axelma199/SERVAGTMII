
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.controllers;


import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.PdfWriter;
import cr.ac.una.reg.info.beans.UniversidadBean;
import cr.ac.una.reg.info.dao.UniversidadDao;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.exceptions.ExceptionGeneral;
import cr.ac.una.reg.info.tools.ManejoFacesContext;
import java.awt.Color;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.icefaces.ace.context.RequestContext;
 
/**
 *
 * @author N00148096
 */

@ManagedBean(name = "ReporteEquiparacionesRecController")
@ViewScoped
public class ReporteEquiparacionesRecController {
     private boolean parametroFechas;
     private String nEquiparacion;
     private String pAnno;
     private String pPeriodo;
     private String pEstado;
     private String tipoFechaSelected;
     private Date pFechaInicial;
     private Date pFechaFinal;
     private String disableAnnoPeriodo;
     private String disableFechas;
     private String URLReporteEquiparaciones;
     private ManejoFacesContext manejoFacesContext;
     private static final String REDIRECCION_PAG_WARNING = "/SERVAGTMII/paginas/error.xhtml";
     private Calendar calendar = Calendar.getInstance();
     private List listUniversidadesItems;
      private List<SelectItem> universidades = new ArrayList();
     private List<UniversidadBean> listUniversidades;
     private String universidadSelected;
 private String pInicial="";
  private String pFinal="";

    public String getpInicial() {
        return pInicial;
    }

    public void setpInicial(String pInicial) {
        this.pInicial = pInicial;
    }

    public String getpFinal() {
        return pFinal;
    }

    public void setpFinal(String pFinal) {
        this.pFinal = pFinal;
    }

 
     
     public  ReporteEquiparacionesRecController() throws ExceptionConnection {
 
         pFinal="";
         pInicial="";
     nEquiparacion="";
     pAnno="";
     pPeriodo="";
     pEstado="";
     disableAnnoPeriodo="false";
     parametroFechas=false;
     disableFechas="true";
     tipoFechaSelected="1";
     calendar = Calendar.getInstance();
     universidadSelected="";
     
     ListaUniversidad();
    
     
     }
         
         public void activaFechas() {
        String summary = parametroFechas ? "Checked" : "Unchecked";
       if (parametroFechas){
           disableAnnoPeriodo="true";
           disableFechas="false";
       }else{
            disableAnnoPeriodo="false";
            disableFechas="true";
       }
    }

         
     public void GenerarReporte(){
    
         String estado="";
         String fecha1="";
        String fecha2="";
      
         
        try{
         if (this.pEstado.equals("TO")){
             this.pEstado="";
         }
         
          if (this.pPeriodo.equals("0")){
               this.pPeriodo="";
         
       }
           
            
         
     if (parametroFechas){
          nEquiparacion="";
          pAnno="";
          pPeriodo="";
         
           calendar.setTime((Date) this.pFechaInicial);
             
              SimpleDateFormat format=new  SimpleDateFormat("dd/MM/yyyy");
             fecha1= format.format(this.pFechaInicial);
             
              fecha2=format.format(this.pFechaFinal); 
           
          
         
         
     }else{
      
               if (this.pAnno.equals("")){
                  addMessage("Debe Digitar un año",2);
         }
     
    
     
     }
     
                 
      //  String concat = "/SERVAGTMII/ReporteEquiparacionesRecibidas?pEquiparacion="+this.nEquiparacion+"&pAnno="+this.pAnno+"&pPeriodo="+this.pPeriodo+"&pEstado="+this.pEstado+"&pWhereFechas="+pWhereFechas;
       String concat = "/SERVAGTMII/ReporteEquiparacionesRecibidas?pEquiparacion="+this.nEquiparacion+"&pAnno="+this.pAnno+"&pPeriodo="+this.pPeriodo+"&pEstado="+this.pEstado+"&pParametroFechas="+parametroFechas+"&pTipoFechaSelected="+this.tipoFechaSelected+"&pFecha1="+ fecha1+"&pFecha2="+ fecha2+"&pUniversidad="+ this.universidadSelected;
            
         URLReporteEquiparaciones=concat;   
            String reporte="javascript:window.open( '" + this.URLReporteEquiparaciones + "','EQUIPARACIONES RECIBIDAS','width=800,height=600');";
    //             JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), reporte);   
              //  RequestContext.getCurrentInstance().execute(reporte); 
 Document document = new Document();
PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));
 
document.open();
Font font = FontFactory.getFont(FontFactory.COURIER, 16, Color.BLACK);
Chunk chunk = new Chunk("Hello World", font);
 
document.add(chunk);
    //      RequestContext.getCurrentInstance().addPartialUpdateTarget(reporte);
document.close();

     
       
        } catch (Exception ex) {
            this.manejoFacesContext.redireccionarFlujoWeb(REDIRECCION_PAG_WARNING);
        }
     }
     
    
     
      public void ListaUniversidad() {
        try {
            
             UniversidadDao universidadDao = new UniversidadDao();
             this.listUniversidades  = universidadDao.ListarUniversidad();
             this.listUniversidadesItems=new ArrayList();
             this.listUniversidadesItems.add(new SelectItem("", "TODAS"));
             for (int i = 0; i < listUniversidades.size(); i++) {
                 this.listUniversidadesItems.add(new SelectItem(listUniversidades.get(i).getUni_id(),listUniversidades.get(i).getUni_descripcion()));
             }
             this.universidadSelected="";
             
          
        }catch (ExceptionConnection ex){
            Logger.getLogger(SolicitanteController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("No fue posible cargar las universidades", 1);

       }
            
        
    }
     
     
     
     
     
           
           
            public void addMessage(String mensaje, int tipo) {
        switch (tipo) {
            case 1:
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, ""));
                break;
            case 2:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, ""));
                break;
            case 3:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje, ""));
                break;
            case 4:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, mensaje, ""));
                break;
            default:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES, mensaje));
                break;
        }
    }
     
    public boolean isParametroFechas() {
        return parametroFechas;
    }

    public void setParametroFechas(boolean parametroFechas) {
        this.parametroFechas = parametroFechas;
    }

    public String getDisableAnnoPeriodo() {
        return disableAnnoPeriodo;
    }

    public void setDisableAnnoPeriodo(String disableAnnoPeriodo) {
        this.disableAnnoPeriodo = disableAnnoPeriodo;
    }

    public String getDisableFechas() {
        return disableFechas;
    }

    public void setDisableFechas(String disableFechas) {
        this.disableFechas = disableFechas;
    }

    public String getnEquiparacion() {
        return nEquiparacion;
    }

    public void setnEquiparacion(String nEquiparacion) {
        this.nEquiparacion = nEquiparacion;
    }

    public String getpAnno() {
        return pAnno;
    }

    public void setpAnno(String pAnno) {
        this.pAnno = pAnno;
    }

    public String getTipoFechaSelected() {
        return tipoFechaSelected;
    }

    public void setTipoFechaSelected(String tipoFechaSelected) {
        this.tipoFechaSelected = tipoFechaSelected;
    }

    public String getpPeriodo() {
        return pPeriodo;
    }

    public void setpPeriodo(String pPeriodo) {
        this.pPeriodo = pPeriodo;
    }

    

    public String getpEstado() {
        return pEstado;
    }

    public void setpEstado(String pEstado) {
        this.pEstado = pEstado;
    }

    public Date getpFechaInicial() {
        return pFechaInicial;
    }

    public void setpFechaInicial(Date pFechaInicial) {
        this.pFechaInicial = pFechaInicial;
    }

    public Date getpFechaFinal() {
        return pFechaFinal;
    }

    public void setpFechaFinal(Date pFechaFinal) {
        this.pFechaFinal = pFechaFinal;
    }

    public List getListUniversidadesItems() {
        return listUniversidadesItems;
    }

    public void setListUniversidadesItems(List listUniversidadesItems) {
        this.listUniversidadesItems = listUniversidadesItems;
    }

    public String getUniversidadSelected() {
        return universidadSelected;
    }

    public void setUniversidadSelected(String universidadSelected) {
        this.universidadSelected = universidadSelected;
    }

      
}




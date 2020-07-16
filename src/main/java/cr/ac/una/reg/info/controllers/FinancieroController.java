/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.controllers;

import cr.ac.una.reg.info.beans.FinancieroBean;
import cr.ac.una.reg.info.dao.FinancieroDao;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author JGA
 */
@ManagedBean(name = "FinancieroController")
@ViewScoped
public class FinancieroController {
    private List<FinancieroBean> listaFinanciero = new ArrayList();
    private FinancieroBean financieroBean;
    private FinancieroBean Selected;
    private int p_opcion;    
      private String visibleAgregarMateria = "";
     private String visibleEliminarMateria = "";
    private String colorMensage;

    public String getColorMensage() {
        return colorMensage;
    }

    public void setColorMensage(String colorMensage) {
        this.colorMensage = colorMensage;
    }

    public String getVisibleAgregarMateria() {
        return visibleAgregarMateria;
    }

    public void setVisibleAgregarMateria(String visibleAgregarMateria) {
        this.visibleAgregarMateria = visibleAgregarMateria;
    }

    public String getVisibleEliminarMateria() {
        return visibleEliminarMateria;
    }

    public void setVisibleEliminarMateria(String visibleEliminarMateria) {
        this.visibleEliminarMateria = visibleEliminarMateria;
    }
    
      /**mau**********/
          private List<FinancieroBean> filtroDocFinancieroDisponible;
          ////////*****************************************/
    
    
    @PostConstruct
    public void FinancieroController()  {
        setFinancieroBean(new FinancieroBean());
        setListaFinanciero(new ArrayList<FinancieroBean>());
        setSelected(new FinancieroBean());
        Listar();
    }
    
    public void nuevo() {
        Selected = new FinancieroBean();
        p_opcion = 1;
    }
    
    //Muestra todos los parametros registrados
    public void Listar() {
        try {
            FinancieroDao financieroDao = new FinancieroDao();
            this.getListaFinanciero().clear();
            financieroDao.cargaFinanciero();
            this.setListaFinanciero(financieroDao.cargaFinanciero());
        } catch (ExceptionConnection ex) {
            Logger.getLogger(ApelacionController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("Se produjo un error al cargar el registros, contacte al administrador del sistema", 1);
        }
    }
    
    public void abrirForm(){
                this.Selected = new FinancieroBean();

    this.visibleAgregarMateria="true";
    this.p_opcion=1;}
    
      public void actionCancelarEliminarUniversidad() throws ExceptionConnection {
        //this.SolicitanteBean = new FHMateriaBean();
        this.Selected = new FinancieroBean();
         this.Listar();
       this.visibleEliminarMateria= "false";

    }
        
    public void actionCancelarAddFinanciero(){
    this.visibleAgregarMateria="false";
               }
    public void abrirFormModificar(){
            this.visibleAgregarMateria="true";

    this.p_opcion=2;}
        
    public void abrirFormEliminar(){
    this.visibleEliminarMateria="true";
    this.p_opcion=3;}

    
  //Guarda o modifica un parametro
    public void guardar() {
        try {
            FinancieroDao financieroDao = new FinancieroDao();
            financieroDao.IME_FINANCIERO(p_opcion, Selected);
            this.Listar();
            this.financieroBean = new FinancieroBean();
            colorMensage="color:green";
            addMessage("Guadado Exitosamente", 1);
            
        } catch (ExceptionConnection ex) {
            Logger.getLogger(ApelacionController.class.getName()).log(Level.SEVERE, null, ex);
                        colorMensage="color:red";

            addMessage("Se produjo un error al insertar el registro, contacte al administrador del sistema", 1);
        }
    }
    
    //Elimina un parametro
    public void eliminar() {
        try {
            FinancieroDao financieroDao = new FinancieroDao();
            financieroDao.IME_FINANCIERO(3, Selected);
            this.Listar();
                        colorMensage="color:green";

            addMessage("Eliminado Exitosamente", 1);
        } catch (ExceptionConnection ex) {
            Logger.getLogger(ApelacionController.class.getName()).log(Level.SEVERE, null, ex);
                        colorMensage="color:red";

            addMessage("Se produjo un error al eliminar el registro, contacte al administrador del sistema", 2);
        }
    }
    
  //Contiene la lista de los mensajes que se muestran en pantalla  
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

    /**
     * @return the listaFinanciero
     */
    public List<FinancieroBean> getListaFinanciero() {
        return listaFinanciero;
    }

    /**
     * @param listaFinanciero the listaFinanciero to set
     */
    public void setListaFinanciero(List<FinancieroBean> listaFinanciero) {
        this.listaFinanciero = listaFinanciero;
    }

    /**
     * @return the financieroBean
     */
    public FinancieroBean getFinancieroBean() {
        return financieroBean;
    }

    /**
     * @param financieroBean the financieroBean to set
     */
    public void setFinancieroBean(FinancieroBean financieroBean) {
        this.financieroBean = financieroBean;
    }

    /**
     * @return the Selected
     */
    public FinancieroBean getSelected() {
        return Selected;
    }

    /**
     * @param Selected the Selected to set
     */
    public void setSelected(FinancieroBean Selected) {
        this.Selected = Selected;
    }

    /**
     * @return the p_opcion
     */
    public int getP_opcion() {
        return p_opcion;
    }

    /**
     * @param p_opcion the p_opcion to set
     */
    public void setP_opcion(int p_opcion) {
        this.p_opcion = p_opcion;
    }

    public List<FinancieroBean> getFiltroDocFinancieroDisponible() {
        return filtroDocFinancieroDisponible;
    }

    public void setFiltroDocFinancieroDisponible(List<FinancieroBean> filtroDocFinancieroDisponible) {
        this.filtroDocFinancieroDisponible = filtroDocFinancieroDisponible;
    }
    
    
    
    
}



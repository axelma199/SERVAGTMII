/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.controllers;

import cr.ac.una.reg.info.beans.DocumentosBean;
import cr.ac.una.reg.info.beans.UniversidadBean;
import cr.ac.una.reg.info.dao.DocumentosDao;
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
@ManagedBean(name = "DocumentosController")
@ViewScoped
public class DocumentosController {

    private List<DocumentosBean> listaDocumentos = new ArrayList();
    private DocumentosBean documentosBean;
    private DocumentosBean Selected;
    private int p_opcion;
    private String visibleForm;
    private String visibleFormEliminar;
    private String eqp="X";
    private String eqv="X";
    private String colorMensage;

    public String getEqp() {
        return eqp;
    }

    public void setEqp(String eqp) {
        this.eqp = eqp;
    }

    public String getEqv() {
        return eqv;
    }

    public void setEqv(String eqv) {
        this.eqv = eqv;
    }

    public String getVisibleFormEliminar() {
        return visibleFormEliminar;
    }

    public void setVisibleFormEliminar(String visibleFormEliminar) {
        this.visibleFormEliminar = visibleFormEliminar;
    }

    public String getVisibleForm() {
        return visibleForm;
    }

    public void setVisibleForm(String visibleForm) {
        this.visibleForm = visibleForm;
    }
    
         /**mau**********/
          private List<DocumentosBean> filtroDocumentosDisponible;
          ////////*****************************************/
    

    @PostConstruct
    public void DocumentosController() {
        setDocumentosBean(new DocumentosBean());
        setListaDocumentos(new ArrayList<DocumentosBean>());
        setSelected(new DocumentosBean());
        this.Selected.setDoc_eqp(true);
        
              if(!Selected.getDoc_eqp()){eqp=""; }
            if(!Selected.getDoc_eqv()){eqv=""; }

        Listar();
    }

   
    //Lista todos los documentos registrados
    public void Listar() {
        try {
            DocumentosDao documentosDao = new DocumentosDao();
            this.getListaDocumentos().clear();
            this.setListaDocumentos(documentosDao.ListarDocumentos());
        } catch (ExceptionConnection ex) {
            Logger.getLogger(ApelacionController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("Se produjo un error al cargar el registros, contacte al administrador del sistema", 1);
        }
    }

    public String getColorMensage() {
        return colorMensage;
    }

    public void setColorMensage(String colorMensage) {
        this.colorMensage = colorMensage;
    }

    public void abrirForm(){
                 this.Selected = new DocumentosBean();

       this.visibleForm="true";    
               p_opcion = 1;

    }
    
    public void actionCancelarAddDocumento(){
    this.visibleForm="false";
               }
    
     public void abrirFormModificar(){
 
       this.visibleForm="true";    
               p_opcion = 2;

    }
    
     
    public void abrirFormEliminar(){
 
       this.visibleFormEliminar="true";    
               p_opcion = 3;

    }
    public void nuevo() {
        Selected = new DocumentosBean();
        p_opcion = 1;
    }

      public void actionCancelarEliminarDocumento() throws ExceptionConnection {
        //this.SolicitanteBean = new FHMateriaBean();
        this.Selected = new DocumentosBean();
         this.Listar();
       this.visibleFormEliminar= "false";

    }
       
   
    
    //Guarda y actualiza documentos
    public void guardar() {
        try {
            DocumentosDao documentosDao = new DocumentosDao();
            documentosDao.dmlDocumentos(p_opcion, Selected);
            this.Listar();
            this.documentosBean = new DocumentosBean();
             colorMensage="color:green";
            addMessage("Guadado Exitosamente", 1);
 
        } catch (ExceptionConnection ex) {
            Logger.getLogger(ApelacionController.class.getName()).log(Level.SEVERE, null, ex);
             colorMensage="color:red";
            addMessage("Se produjo un error al insertar el registro, contacte al administrador del sistema", 1);
        }
    }

 //Elimina un documento en especifico
    public void eliminar() {
        try {
            DocumentosDao documentosDao = new DocumentosDao();
            documentosDao.dmlDocumentos(3, Selected);
            this.Listar();
             colorMensage="color:green";
            addMessage("Eliminado Exitosamente", 1);
        } catch (ExceptionConnection ex) {
             colorMensage="color:red";
            Logger.getLogger(ApelacionController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("Se produjo un error al eliminar el registro, contacte al administrador del sistema", 2);
        }
    }

   //Contiene un listado de los documentos que se muestran en pantalla
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
     * @return the listaDocumentos
     */
    public List<DocumentosBean> getListaDocumentos() {
        return listaDocumentos;
    }

    /**
     * @param listaDocumentos the listaDocumentos to set
     */
    public void setListaDocumentos(List<DocumentosBean> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    /**
     * @return the documentosBean
     */
    public DocumentosBean getDocumentosBean() {
        return documentosBean;
    }

    /**
     * @param documentosBean the documentosBean to set
     */
    public void setDocumentosBean(DocumentosBean documentosBean) {
        this.documentosBean = documentosBean;
    }

    /**
     * @return the Selected
     */
    public DocumentosBean getSelected() {
        return Selected;
    }

    /**
     * @param Selected the Selected to set
     */
    public void setSelected(DocumentosBean Selected) {
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

    public List<DocumentosBean> getFiltroDocumentosDisponible() {
        return filtroDocumentosDisponible;
    }

    public void setFiltroDocumentosDisponible(List<DocumentosBean> filtroDocumentosDisponible) {
        this.filtroDocumentosDisponible = filtroDocumentosDisponible;
    }
    
    
    
}

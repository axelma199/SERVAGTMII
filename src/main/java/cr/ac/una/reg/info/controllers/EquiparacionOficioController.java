/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.controllers;

import cr.ac.una.reg.info.beans.BitacoraSolicitudBean;
import cr.ac.una.reg.info.beans.Dr_siseg_usuarioBean;
import cr.ac.una.reg.info.beans.EquiparacionOficioBean;
import cr.ac.una.reg.info.dao.BitacoraSolicitudDao;
import cr.ac.una.reg.info.dao.EquiparacionOficioDao;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "EquiparacionOficioController")
@ViewScoped
public class EquiparacionOficioController {

    private ArrayList<EquiparacionOficioBean> arrayEquiparacionOficio = new ArrayList<>();
    private EquiparacionOficioBean equiparacionOficioSelected;

    
    /***mau***/
     private List<EquiparacionOficioBean> filtroListaEquiparacionesOficio;
     List<EquiparacionOficioBean> arrayEquiparacionesOficioSelected = new ArrayList<>();
    
    /*************************************************/
    public EquiparacionOficioController() {
        try {
            equiparacionOficioSelected = new EquiparacionOficioBean();
            listaEquiparacionOficio();
        } catch (ExceptionConnection ex) {
            Logger.getLogger(EquiparacionOficioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   //Direcciona a la pagina de ingreso de nuevas equiparaciones
    public void nuevaEquiparacionOficio() throws ExceptionConnection {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/faces/paginas/MantEquiparacionOficio.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ListaEquiparacionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    //Muestra la lista de todas las equiparaciones por oficio existentes
    public void listaEquiparacionOficio() throws ExceptionConnection {
        try {
            EquiparacionOficioDao equiparacionOficioDao = new EquiparacionOficioDao();
            arrayEquiparacionOficio = equiparacionOficioDao.ListarEquiparacionOficio();
        } catch (ExceptionConnection ex) {
            Logger.getLogger(CarreraExternaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    //Elimina una equiparacion por oficio en especifico
    public void eliminar() {
        try {
            EquiparacionOficioDao equiparacionOficioDao = new EquiparacionOficioDao();
            equiparacionOficioDao.dmlEquiparacionOficio(3, equiparacionOficioSelected);
            addMessage("Registro eliminado correctamente", 1);
            
             FacesContext facesContext = FacesContext.getCurrentInstance(); 
            Dr_siseg_usuarioBean usuario= new Dr_siseg_usuarioBean();
            usuario = (Dr_siseg_usuarioBean) facesContext.getExternalContext().getSessionMap().get("usuario");
            
            BitacoraSolicitudDao bitacoraSolicitudDao=new BitacoraSolicitudDao();//INSERTA EL MOVIMIENTO EN LA BITACORA
            BitacoraSolicitudBean bitacoraSolicitudBean=new BitacoraSolicitudBean();
            bitacoraSolicitudBean.setBit_sol_id(Integer.toString(equiparacionOficioSelected.getEqp_ofi_id()));
            bitacoraSolicitudBean.setUsuarioBean(usuario);
            bitacoraSolicitudBean.setBit_tipo_movimiento("E");
            bitacoraSolicitudBean.setBit_detalle("La Equiparacion fue Eliminada");
            bitacoraSolicitudDao.dmlDr_regt_bitacora_solicitud(bitacoraSolicitudBean);
            
            
            listaEquiparacionOficio();
        } catch (ExceptionConnection ex) {
            addMessage("No fue posible ejecutar la operación, contacte con el administrador del sistema", 1);
            Logger.getLogger(UniversidadController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Modifica una equiparacion por oficio en especifico
    public void editarEquiparacion() throws ExceptionConnection {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().getSessionMap().put("equiparacionOficio", equiparacionOficioSelected);
            context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/faces/paginas/MantEquiparacionOficio.xhtml");

        } catch (IOException ex) {
            Logger.getLogger(ListaEquiparacionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<EquiparacionOficioBean> getArrayEquiparacionOficio() {
        return arrayEquiparacionOficio;
    }

    public void setArrayEquiparacionOficio(ArrayList<EquiparacionOficioBean> arrayEquiparacionOficio) {
        this.arrayEquiparacionOficio = arrayEquiparacionOficio;
    }

    public EquiparacionOficioBean getEquiparacionOficioSelected() {
        return equiparacionOficioSelected;
    }

    public void setEquiparacionOficioSelected(EquiparacionOficioBean equiparacionOficioSelected) {
        this.equiparacionOficioSelected = equiparacionOficioSelected;
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

    public List<EquiparacionOficioBean> getFiltroListaEquiparacionesOficio() {
        return filtroListaEquiparacionesOficio;
    }

    public void setFiltroListaEquiparacionesOficio(List<EquiparacionOficioBean> filtroListaEquiparacionesOficio) {
        this.filtroListaEquiparacionesOficio = filtroListaEquiparacionesOficio;
    }

    public List<EquiparacionOficioBean> getArrayEquiparacionesOficioSelected() {
        return arrayEquiparacionesOficioSelected;
    }

    public void setArrayEquiparacionesOficioSelected(List<EquiparacionOficioBean> arrayEquiparacionesOficioSelected) {
        this.arrayEquiparacionesOficioSelected = arrayEquiparacionesOficioSelected;
    }
    
    
    
}

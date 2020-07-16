/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.controllers;

import cr.ac.una.reg.info.beans.BitacoraSolicitudBean;
import cr.ac.una.reg.info.beans.Dr_siseg_usuarioBean;
import cr.ac.una.reg.info.dao.EquiparacionesDao;
import cr.ac.una.reg.info.beans.EquiparacionesBean;
import cr.ac.una.reg.info.dao.BitacoraSolicitudDao;
import cr.ac.una.reg.info.dao.MateriasSolicitudDao;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext; 
import org.icefaces.ace.event.SelectEvent;

@ManagedBean(name = "ListaEquiparacionesController")
@ViewScoped
public class ListaEquiparacionesController {

    ArrayList<EquiparacionesBean> arrayEquiparaciones = new ArrayList<>();
    EquiparacionesBean equiparacionBeanSelected = new EquiparacionesBean();
    
    /*mau*/  
    private List<EquiparacionesBean> filtroEquiparaciones;
     ArrayList<EquiparacionesBean> arrayequiparacion = new ArrayList<>();//array list del detalle
     List<EquiparacionesBean> arrayEquiparacionesSelected = new ArrayList<>();
/*************/
  
     //Carga la lista de todas las equiparaciones 
     public ListaEquiparacionesController() {
        arrayEquiparaciones = new ArrayList<>();
        try {
            listaEquiparaciones();
        } catch (ExceptionConnection ex) {
            Logger.getLogger(ListaEquiparacionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listaEquiparaciones() throws ExceptionConnection {
        EquiparacionesDao equiparacionDao = new EquiparacionesDao();
        arrayEquiparaciones = equiparacionDao.ListarDr_regt_equiparaciones();

    }

    //Redirecciona a la pantalla de inclusion de equiparaciones
    public void nuevaEquiparacion() throws ExceptionConnection {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/faces/paginas/Equiparaciones.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ListaEquiparacionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  //Modifica un aequiparacion que no este en estado de finalizado
    public void editarEquiparacion() throws ExceptionConnection {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (equiparacionBeanSelected.getEqp_estado().equals("0")) {
                context.getExternalContext().getSessionMap().put("equiparacion", equiparacionBeanSelected);
                context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/faces/paginas/EquiparacionesMaterias.xhtml");
            } else {
                context.getExternalContext().getSessionMap().put("equiparacion", equiparacionBeanSelected);
                context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/faces/paginas/ModificarEquiparacion.xhtml");
            }

        } catch (IOException ex) {
            Logger.getLogger(ListaEquiparacionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Pasa a estado de anulado a una equiparacion que no este en estado finalizada
    public void anularEquiparacion() {


        try {

 if (!equiparacionBeanSelected.getEqp_estado().equals("2")) {
            EquiparacionesDao equiparacionDAo = new EquiparacionesDao();
            equiparacionBeanSelected.setEqp_estado("3");

            equiparacionDAo.dmlDr_regt_equiparaciones(2, equiparacionBeanSelected);

            MateriasSolicitudDao materiaDao = new MateriasSolicitudDao();

            addMessage("Registro anulado satisfactoriamente", 1);
             FacesContext facesContext = FacesContext.getCurrentInstance(); 
            Dr_siseg_usuarioBean usuario= new Dr_siseg_usuarioBean();
            usuario = (Dr_siseg_usuarioBean) facesContext.getExternalContext().getSessionMap().get("usuario");
            
            BitacoraSolicitudDao bitacoraSolicitudDao=new BitacoraSolicitudDao();//INSERTA EL MOVIMIENTO EN LA BITACORA
            BitacoraSolicitudBean bitacoraSolicitudBean=new BitacoraSolicitudBean();
            bitacoraSolicitudBean.setBit_sol_id(equiparacionBeanSelected.getEqp_sol_numero());
            bitacoraSolicitudBean.setUsuarioBean(usuario);
            bitacoraSolicitudBean.setBit_tipo_movimiento("E");
            bitacoraSolicitudBean.setBit_detalle("La Equiparacion fue Eliminada");
            bitacoraSolicitudDao.dmlDr_regt_bitacora_solicitud(bitacoraSolicitudBean);
            
            
            listaEquiparaciones();
 }else{
          addMessage("Equiparacion NO puede ser Eliminada", 1);
 }

        } catch (ExceptionConnection ex) {
           /* Logger.getLogger(EquivalenciaController.class
                    .getName()).log(Level.SEVERE, null, ex);
            addMessage("Se produjo un error al anular el registro, contacte al administrador del sistema", 2);
      */  }

    }

    //Contiene la lista de los mensajes que son mostardos en pantalla
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

    public void onRowSelect(SelectEvent event) {
        String codigo = ((EquiparacionesBean) event.getObject()).getEqp_sol_numero();
     //   String descripcion = ((EquiparacionesBean) event.getObject()).getMat_ext_descripcion();
        //this.rolBeanEliminar.setCodigo(codigo);
        //this.rolBeanEliminar.setDescripcion(descripcion);
        System.out.print(codigo);
    }
    
    
    public ArrayList<EquiparacionesBean> getArrayEquiparaciones() {
        return arrayEquiparaciones;
    }

    public void setArrayEquiparaciones(ArrayList<EquiparacionesBean> arrayEquiparaciones) {
        this.arrayEquiparaciones = arrayEquiparaciones;
    }

    public EquiparacionesBean getEquiparacionBeanSelected() {
        return equiparacionBeanSelected;
    }

    public void setEquiparacionBeanSelected(EquiparacionesBean equiparacionBeanSelected) {
        this.equiparacionBeanSelected = equiparacionBeanSelected;
    }

    public List<EquiparacionesBean> getFiltroEquiparaciones() {
        return filtroEquiparaciones;
    }

    public void setFiltroEquiparaciones(List<EquiparacionesBean> filtroEquiparaciones) {
        this.filtroEquiparaciones = filtroEquiparaciones;
    }

    public ArrayList<EquiparacionesBean> getArrayequiparacion() {
        return arrayequiparacion;
    }

    public void setArrayequiparacion(ArrayList<EquiparacionesBean> arrayequiparacion) {
        this.arrayequiparacion = arrayequiparacion;
    }

    public List<EquiparacionesBean> getArrayEquiparacionesSelected() {
        return arrayEquiparacionesSelected;
    }

    public void setArrayEquiparacionesSelected(List<EquiparacionesBean> arrayEquiparacionesSelected) {
        this.arrayEquiparacionesSelected = arrayEquiparacionesSelected;
    }
    
    
    
}

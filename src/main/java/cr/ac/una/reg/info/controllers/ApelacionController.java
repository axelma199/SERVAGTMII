/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.controllers;

import cr.ac.una.reg.info.beans.ApelacionesBean;
import cr.ac.una.reg.info.beans.BitacoraSolicitudBean;
import cr.ac.una.reg.info.beans.EquivalenciasBean;
import cr.ac.una.reg.info.beans.Dr_siseg_usuarioBean;
import cr.ac.una.reg.info.beans.EquiparacionesBean;
import cr.ac.una.reg.info.beans.UsuarioBean;
//import cr.ac.una.reg.info.business.SecurityBusiness;
import cr.ac.una.reg.info.dao.ApelacionesDao;
import cr.ac.una.reg.info.dao.BitacoraSolicitudDao;
import cr.ac.una.reg.info.dao.EquiparacionesDao;
import cr.ac.una.reg.info.dao.EquivalenciasDao;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.tools.ManejoFacesContext;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

@ManagedBean(name = "ApelacionController")
@ViewScoped
/**
 *
 * @author JGA
  * 
 */
public class ApelacionController {

    private List<ApelacionesBean> listaApelaciones = new ArrayList();
    ApelacionesBean apelacionesBean;
    private List<SelectItem> tipoApelacionSelecionada = new ArrayList();
    private ApelacionesBean Selected;
    private int p_opcion;
    private Calendar calendar = Calendar.getInstance();
    private Date fecha;
    private Dr_siseg_usuarioBean usuarioBean; 
    private boolean  modoAdministrador;
    private ManejoFacesContext manejoFacesContext;

    //@PostConstruct
    public void ApelacionController() throws ExceptionConnection {
        apelacionesBean = new ApelacionesBean();
        tipoApelacionSelecionada = new ArrayList<SelectItem>();
        listaApelaciones = new ArrayList<ApelacionesBean>();
        Selected = new ApelacionesBean();
        this.manejoFacesContext = new ManejoFacesContext();
        cargaLista();
        Listar();
        verificarUsuario();
       
    }

 //Veirifica si el usuario logeado es de tipo administrador
    public boolean verificarUsuario(){
       boolean estado = false;
       try{
           this.usuarioBean = (Dr_siseg_usuarioBean) this.manejoFacesContext.obtenerObjetoSession("usuario");
           if(this.usuarioBean != null){
               estado = true;
                   if (this.usuarioBean.getRoll().equals("GRP_SERVAGTMII_ADMIN")){
                        modoAdministrador=true;
//                       this.usuarioRoll="ADMIN";
//                       this.disableBotonFinalizar="false";
//                       this.visibleImprimirComprobanteEncargado="true";
//                       this.visibleImprimirComprobanteEstudiante="true";
                       

                   }else{
                       if (this.usuarioBean.getRoll().equals("GRP_SERVAGTMII_PF1")){
                             modoAdministrador=true;
//                           this.usuarioRoll="PF1";
//                           this.disableBotonFinalizar="true";
//                           this.visibleImprimirComprobanteEncargado="false";
//                           this.visibleImprimirComprobanteEstudiante="true";
                           
                       }
                   }

           }else{
           this.manejoFacesContext.redireccionarFlujoWeb("/WebAppSLATE/LogoutServlet" );
          }
       }catch(Exception ex){
           this.manejoFacesContext.redireccionarFlujoWeb("/WebAppSLATE/LogoutServlet" );
          // this.manejoFacesContext.redireccionarFlujoWeb("/WebAppMADEB/cr.ac.una.reg.info.contenido/moduloFuncionario/estudiante/e_editarDireccion.jspx");
       }//
       return estado;
    }//
    
    
    //Busca una apelacion en especifico
    public boolean buscarCodApelacion() throws ExceptionConnection {
        boolean indicador = true;
        EquivalenciasDao oEquivalencia = new EquivalenciasDao();
        EquivalenciasBean oEqv = new EquivalenciasBean();
        oEqv.setEqv_sol_numero(this.apelacionesBean.getApel_codApelar());
        oEqv = oEquivalencia.buscarEquivalenciasNum(oEqv);
        if (oEqv.getEqv_sol_numero() == null) {
            EquiparacionesDao oEquiparacion = new EquiparacionesDao();
            EquiparacionesBean oEqp = new EquiparacionesBean();
            oEqp.setEqp_sol_numero(this.apelacionesBean.getApel_codApelar());
            if (oEquiparacion.buscaDr_regt_equiparaciones(oEqp) == false) {
                indicador = false;
            }
        }
        return indicador;
    }

   //Giuarda los datos de las nuevas apelaciones
    public void guardar() {
        try {
            if (this.buscarCodApelacion()) {
                Dr_siseg_usuarioBean usuario = new Dr_siseg_usuarioBean();
                FacesContext facesContext = FacesContext.getCurrentInstance();
                usuario = (Dr_siseg_usuarioBean) facesContext.getExternalContext().getSessionMap().get("usuario");

                ApelacionesDao apelacionesDao = new ApelacionesDao();
                apelacionesDao.INSERTA_APELACIONES(apelacionesBean);          

                BitacoraSolicitudDao bitacoraSolicitudDao = new BitacoraSolicitudDao();//INSERTA EL MOVIMIENTO EN LA BITACORA
                BitacoraSolicitudBean bitacoraSolicitudBean = new BitacoraSolicitudBean();
                bitacoraSolicitudBean.setBit_sol_id(this.apelacionesBean.getApel_sol_numero());
                bitacoraSolicitudBean.setUsuarioBean(usuario);
                bitacoraSolicitudBean.setBit_tipo_movimiento("0");
                bitacoraSolicitudBean.setBit_detalle("Fue insertada la apelacion");
                bitacoraSolicitudDao.dmlDr_regt_bitacora_solicitud(bitacoraSolicitudBean);

                this.Listar();
                this.apelacionesBean = new ApelacionesBean();
                addMessage("Guadado Exitosamente", 1);
            } else {
                addMessage("La solicitud de apelacion especificada no existe", 1);
                this.Listar();
            }
        } catch (ExceptionConnection ex) {
            Logger.getLogger(ApelacionController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("Se produjo un error al insertar el registro, contacte al administrador del sistema", 1);
        }
    }
    
   //modifica los datos de una apelacion ya existente 
    public void modificar() {
        try {
            if(!fecha.equals(null)){
                Dr_siseg_usuarioBean usuario = new Dr_siseg_usuarioBean();
                FacesContext facesContext = FacesContext.getCurrentInstance();
                usuario = (Dr_siseg_usuarioBean) facesContext.getExternalContext().getSessionMap().get("usuario");
                    
                    calendar.setTime((Date) this.fecha);
                    this.Selected.setApel_fecha_sesion(calendar.getTime());

                ApelacionesDao apelacionesDao = new ApelacionesDao();
                apelacionesDao.IME_APELACIONES(2, Selected);          

                BitacoraSolicitudDao bitacoraSolicitudDao = new BitacoraSolicitudDao();//INSERTA EL MOVIMIENTO EN LA BITACORA
                BitacoraSolicitudBean bitacoraSolicitudBean = new BitacoraSolicitudBean();
                bitacoraSolicitudBean.setBit_sol_id(this.Selected.getApel_sol_numero());
                bitacoraSolicitudBean.setUsuarioBean(usuario);
                bitacoraSolicitudBean.setBit_tipo_movimiento("1");
                bitacoraSolicitudBean.setBit_detalle("Fue modificada la apelacion");
                bitacoraSolicitudDao.dmlDr_regt_bitacora_solicitud(bitacoraSolicitudBean);

                this.Listar();
                this.Selected = new ApelacionesBean();
                addMessage("Guadado Exitosamente", 1);
            }else
            {
                addMessage("Es requerida la fecha",1 );
            }
        } catch (ExceptionConnection ex) {
            Logger.getLogger(ApelacionController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("Se produjo un error al insertar el registro, contacte al administrador del sistema", 1);
        }
    }

    //Elimina una apelacion en especifico
    public void eliminar() {
        try {
            Dr_siseg_usuarioBean usuario = new Dr_siseg_usuarioBean();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            usuario = (Dr_siseg_usuarioBean) facesContext.getExternalContext().getSessionMap().get("usuario");

            ApelacionesDao apelacionesDao = new ApelacionesDao();
            apelacionesDao.IME_APELACIONES(3, this.Selected);

            BitacoraSolicitudDao bitacoraSolicitudDao = new BitacoraSolicitudDao();//INSERTA EL MOVIMIENTO EN LA BITACORA
            BitacoraSolicitudBean bitacoraSolicitudBean = new BitacoraSolicitudBean();
            bitacoraSolicitudBean.setBit_sol_id(this.Selected.getApel_sol_numero());
            bitacoraSolicitudBean.setUsuarioBean(usuario);
            bitacoraSolicitudBean.setBit_tipo_movimiento("2");
            bitacoraSolicitudBean.setBit_detalle("Fue eliminada la apelacion");
            bitacoraSolicitudDao.dmlDr_regt_bitacora_solicitud(bitacoraSolicitudBean);

            this.Listar();
            addMessage("Eliminado Exitosamente", 1);
        } catch (ExceptionConnection ex) {
            Logger.getLogger(ApelacionController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("Se produjo un error al eliminar el registro, contacte al administrador del sistema", 2);
        }
    }

   
    //Proporciona los mensage en pantalla
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

  
    //Lista todas las apelaciones 
    public void Listar() {
        try {
            ApelacionesDao apelacionesDao = new ApelacionesDao();
            this.listaApelaciones.clear();
            setListaApelaciones(apelacionesDao.cargaApelaciones());
        } catch (ExceptionConnection ex) {
            Logger.getLogger(ApelacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Carga en combo los tipos de apleaciones
    private void cargaLista() {
        this.getTipoApelacionSelecionada().add(new SelectItem("Aclaracion", "Aclaracion"));
        this.getTipoApelacionSelecionada().add(new SelectItem("Revision", "Revision"));
        this.getTipoApelacionSelecionada().add(new SelectItem("Revocatoria", "Revocatoria"));
        this.getTipoApelacionSelecionada().add(new SelectItem("Subsidiaria", "Subsidiaria"));
        
    }

    public ApelacionesBean getApelacionesBean() {
        return apelacionesBean;
    }

    public void setApelacionesBean(ApelacionesBean apelacionesBean) {
        this.apelacionesBean = apelacionesBean;
    }

    /**
     * @return the tipoApelacionSelecionada
     */
    public List<SelectItem> getTipoApelacionSelecionada() {
        return tipoApelacionSelecionada;
    }

    /**
     * @param tipoApelacionSelecionada the tipoApelacionSelecionada to set
     */
    public void setTipoApelacionSelecionada(List<SelectItem> tipoApelacionSelecionada) {
        this.tipoApelacionSelecionada = tipoApelacionSelecionada;
    }

    /**
     * @return the Selected
     */
    public ApelacionesBean getSelected() {
        return Selected;
    }

    /**
     * @param Selected the Selected to set
     */
    public void setSelected(ApelacionesBean Selected) {
        this.Selected = Selected;
    }

    /**
     * @return the listaApelaciones
     */
    public List<ApelacionesBean> getListaApelaciones() {
        return listaApelaciones;
    }

    /**
     * @param listaApelaciones the listaApelaciones to set
     */
    public void setListaApelaciones(List<ApelacionesBean> listaApelaciones) {
        this.listaApelaciones = listaApelaciones;
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

    /**
     * @return the fecha
     */
    public Date getFecha() {
       // fecha=Selected.getApel_fecha_sesion();
        return null;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the calendar
     */
    public Calendar getCalendar() {
        return calendar;
    }

    /**
     * @param calendar the calendar to set
     */
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public boolean isModoAdministrador() {
        return modoAdministrador;
    }

    public void setModoAdministrador(boolean modoAdministrador) {
        this.modoAdministrador = modoAdministrador;
    }

    
    
}

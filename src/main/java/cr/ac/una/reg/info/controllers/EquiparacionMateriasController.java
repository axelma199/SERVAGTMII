/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.controllers;

import cr.ac.una.reg.info.beans.BitacoraSolicitudBean;
import cr.ac.una.reg.info.beans.CarreraExternaBean;
import cr.ac.una.reg.info.beans.Dr_siseg_usuarioBean;
import cr.ac.una.reg.info.dao.ScbcrseDao;
import cr.ac.una.reg.info.dao.MateriasSolicitudDao;
import cr.ac.una.reg.info.beans.MateriasSolicitudBean;
import cr.ac.una.reg.info.beans.EquiparacionesBean;
import cr.ac.una.reg.info.beans.ScbcrseBean;

import cr.ac.una.reg.info.beans.MateriaExternaBean;
import cr.ac.una.reg.info.beans.UniversidadBean;
import cr.ac.una.reg.info.dao.BitacoraSolicitudDao;
import cr.ac.una.reg.info.dao.CarreraExternaDao;
import cr.ac.una.reg.info.dao.MateriasSolicitudDao;
import cr.ac.una.reg.info.dao.EquiparacionesDao;
import cr.ac.una.reg.info.dao.MateriaExternaDao;
import cr.ac.una.reg.info.dao.UniversidadDao;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent; 
import org.icefaces.ace.event.SelectEvent;

/**
 *
 * @author oscar
 */
@ManagedBean(name = "EquiparacionMateriasController")
@ViewScoped
public class EquiparacionMateriasController {

    ArrayList<ScbcrseBean> arrayScbcrse = new ArrayList<>();
    ArrayList<MateriaExternaBean> arrayMateriasEquivalentes = new ArrayList<>();
    EquiparacionesBean equiparacionBean = new EquiparacionesBean();
    ScbcrseBean scbcrseBeanSelected = new ScbcrseBean();
    private ArrayList<CarreraExternaBean> arrayCarreraExterna = new ArrayList<>();
    CarreraExternaBean carreraExternaSelected = new CarreraExternaBean();
    ArrayList<UniversidadBean> arrayUniversidades = new ArrayList<>();
    private UniversidadBean universidadSelected = new UniversidadBean();
    ArrayList<MateriaExternaBean> arrayMateriaExterna = new ArrayList<>();//materias externas equivalentes a la materia scbcrse provenientes del pdialig
    List<MateriaExternaBean> arrayMateriasExternasSelected = new ArrayList<>();//materias externas seleccionadas en el pdialog
    
    //**mau**//
    List<ScbcrseBean>arrayMateriasEquiparaSelected= new ArrayList<>();
    ArrayList<ScbcrseBean> filteredScbcrse = new ArrayList<>();
   //********************* 
    ArrayList<MateriasSolicitudBean> arrayMateriaSolicitud = new ArrayList<>();//array list del detalle
    private MateriasSolicitudBean detalleMateriaSeleccionada=new MateriasSolicitudBean();;
    
    
    private List<MateriaExternaBean> filtroRolDisponibles;
    private List<ScbcrseBean> filtroScbcrseBean;

    private List<MateriasSolicitudBean>listdetalleMateriaSeleccionadaselected;
    private String ScbcrseCodigo="";
    
    private String URLReporteEstudiante;
    private boolean opcionReporteGenerales;
    
    
    public EquiparacionMateriasController() throws ExceptionConnection {
        equiparacionBean = new EquiparacionesBean();
        scbcrseBeanSelected = new ScbcrseBean();
        arrayMateriasEquivalentes = new ArrayList<>();
        arrayMateriaExterna = new ArrayList<>();
        arrayMateriasExternasSelected = new ArrayList<>();
        arrayUniversidades = new ArrayList<>();
        universidadSelected = new UniversidadBean();
        arrayCarreraExterna = new ArrayList<>();
        carreraExternaSelected = new CarreraExternaBean();
        cargaEquiparacion();
        cargaSCBCRSE();
        ListaUniversidad();
        ListaMateriaExterna();
        ScbcrseCodigo="";
         this.opcionReporteGenerales=true;
    }

   
    //carga todas las materias registradas en una equiparacion en especifico
    public void cargaEquiparacion() throws ExceptionConnection {
        String equiparacion_num;
        EquiparacionesDao equiparacionDao = new EquiparacionesDao();
        MateriasSolicitudDao materiaSolicitudDao = new MateriasSolicitudDao();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        equiparacionBean = (EquiparacionesBean) facesContext.getExternalContext().getSessionMap().get("equiparacion");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("equiparacion");
//        arrayMateriaSolicitud = materiaSolicitudDao.cargaDetalle(equiparacionBean.getEqp_sol_numero());
    }

  
    //Carga los datos de una equiparacion en especifico
    public void cargaDetalleEquiparacion() {
        try {
            MateriasSolicitudDao materiaSolicitudDao = new MateriasSolicitudDao();
            arrayMateriaSolicitud = materiaSolicitudDao.cargaDetalle(equiparacionBean.getEqp_sol_numero());
        } catch (ExceptionConnection ex) {
            Logger.getLogger(EquiparacionMateriasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   //Lista las materias o cursos pertenecientes a otras universidades
    public void ListaMateriaExterna() throws ExceptionConnection {
        try {

            MateriaExternaDao materiaExternaDao = new MateriaExternaDao();
            arrayMateriaExterna = materiaExternaDao.ListarMateriaExterna(0);

        } catch (ExceptionConnection ex) {
            Logger.getLogger(CarreraExternaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    //Lista las carreras pertenecinetes a otras universidades
    public void ListaCarreraExterna(ActionEvent event) throws ExceptionConnection {
        try {
            if (universidadSelected.getUni_id() != 0) {
                CarreraExternaDao carreraExternaDao = new CarreraExternaDao();
                arrayCarreraExterna = carreraExternaDao.ListarCarreraExterna(universidadSelected.getUni_id());
            }
        } catch (ExceptionConnection ex) {
            Logger.getLogger(CarreraExternaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    //Carga la lista de todos los cursos pertenecinetes a la UNA
    public void cargaSCBCRSE() throws ExceptionConnection {
        arrayScbcrse = new ArrayList<>();
        ScbcrseDao scbcrseDao = new ScbcrseDao();
        //arrayScbcrse = scbcrseDao.ListarScbcrse();
        
        
        //**mau
         filteredScbcrse = new ArrayList<>();
        //***** 
    }

    
    //Pasa la equiparacion a estado finalizado 
    public void FinalizaEquiparacion() {
   
        try {
            if (arrayMateriaSolicitud.size()==0){
           
                addMessage("Debe seleccionar una   materia a equipara y una materia externa como minimo  ", 1);
            }else{
            EquiparacionesDao equiparacionDao = new EquiparacionesDao();
            equiparacionBean.setEqp_estado("1");
            equiparacionDao.dmlDr_regt_equiparaciones(2, equiparacionBean);
            
             FacesContext facesContext = FacesContext.getCurrentInstance(); 
            Dr_siseg_usuarioBean usuario= new Dr_siseg_usuarioBean();
            usuario = (Dr_siseg_usuarioBean) facesContext.getExternalContext().getSessionMap().get("usuario");
            
            BitacoraSolicitudDao bitacoraSolicitudDao=new BitacoraSolicitudDao();//INSERTA EL MOVIMIENTO EN LA BITACORA
            BitacoraSolicitudBean bitacoraSolicitudBean=new BitacoraSolicitudBean();
            bitacoraSolicitudBean.setBit_sol_id( equiparacionBean.getEqp_sol_numero());
            bitacoraSolicitudBean.setUsuarioBean(usuario);
            bitacoraSolicitudBean.setBit_tipo_movimiento("F");
            bitacoraSolicitudBean.setBit_detalle("La Equiparacion fue Finalizada");
            bitacoraSolicitudDao.dmlDr_regt_bitacora_solicitud(bitacoraSolicitudBean);
            
            
            
            
            FacesContext context = FacesContext.getCurrentInstance();
            try {
                context.getExternalContext().getSessionMap().remove("equiparacion");
                context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/faces/paginas/ListarEquiparaciones.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(EquiparacionMateriasController.class.getName()).log(Level.SEVERE, null, ex);
            }
           }        
     } catch (ExceptionConnection ex) {
            Logger.getLogger(EquiparacionMateriasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    //Carga las materias o cursos de otras universidades asignadas a una equiparacion en especifico
    public void cargarMateriasExternasSelected() {
        Iterator iterador = arrayMateriasExternasSelected.listIterator();

        while (iterador.hasNext()) {
            MateriaExternaBean materia = (MateriaExternaBean) iterador.next(); //Obtengo el elemento contenido                    

            Iterator iteradorMaterias = arrayMateriasEquivalentes.listIterator();
            boolean verificado = true;
            while (iteradorMaterias.hasNext()) {
                MateriaExternaBean materiaExterna = (MateriaExternaBean) iteradorMaterias.next();
                if (materiaExterna.getMat_ext_codigo().equals(materia.getMat_ext_codigo())) {
                    verificado = false;
                }
            }
            if (verificado) {
                arrayMateriasEquivalentes.add(materia);
            }
        }
        arrayMateriasExternasSelected = new ArrayList<>();
    }

    //Carga los datos de una materia en especifico
      public void cargarMateriasEquiparaSelected() {
        Iterator iterador = arrayMateriasEquiparaSelected.listIterator();

        while (iterador.hasNext()) {
            ScbcrseBean materia = (ScbcrseBean) iterador.next(); //Obtengo el elemento contenido                    


           
                scbcrseBeanSelected.setCodigo(materia.getCodigo());
                scbcrseBeanSelected.setScbcrse_title(materia.getScbcrse_title());
                scbcrseBeanSelected.setScbcrse_credit_hr_low(materia.getScbcrse_credit_hr_low());
                ScbcrseCodigo=materia.getCodigo();
//            }
        }
        arrayMateriasEquiparaSelected = new ArrayList<>();
    }

    
    
    
    
    
    
 public void onRowSelect(SelectEvent event) {
        String codigo = ((MateriaExternaBean) event.getObject()).getMat_ext_codigo();
        String descripcion = ((MateriaExternaBean) event.getObject()).getMat_ext_descripcion();
        //this.rolBeanEliminar.setCodigo(codigo);
        //this.rolBeanEliminar.setDescripcion(descripcion);
    }
 
 public void onRowSelectScbcrse(SelectEvent event) {
        String codigo = ((ScbcrseBean) event.getObject()).getCodigo();
        //String descripcion = ((MateriaExternaBean) event.getObject()).getMat_ext_descripcion();
        //this.rolBeanEliminar.setCodigo(codigo);
        //this.rolBeanEliminar.setDescripcion(descripcion);
    }
 
    
    public void quitarCarrera(MateriaExternaBean item) {
        arrayMateriasEquivalentes.remove(item);
    }

  
    //Retorna la lista de las Universidades
    public void ListaUniversidad() throws ExceptionConnection {
        try {
            UniversidadDao universidadDao = new UniversidadDao();
            arrayUniversidades = universidadDao.ListarUniversidad();
        } catch (ExceptionConnection ex) {
            Logger.getLogger(UniversidadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  //Asignas las materias seleccionadas a una equiparacion en especifico
    public void guardaMateriaEquiparacion() {
         if (ScbcrseCodigo.equals("")|| arrayMateriasEquivalentes.isEmpty()){
        addMessage("Debe seleccionar una   materia a equipara y una materia externa como minimo  ", 1);
         }else{
             
            
       
             
        Iterator iterador = arrayMateriasEquivalentes.listIterator();
        MateriasSolicitudDao materiaSolicitudDao;
        try {
            materiaSolicitudDao = new MateriasSolicitudDao();
            while (iterador.hasNext()) {
                MateriaExternaBean materia = (MateriaExternaBean) iterador.next(); //Obtengo el elemento contenido                    

                MateriasSolicitudBean materiaSolicitudBean = new MateriasSolicitudBean();
                materiaSolicitudBean.setMat_sol_numero(equiparacionBean.getEqp_sol_numero());
                materiaSolicitudBean.setMat_sol_reconocer(scbcrseBeanSelected.getCodigo());
                materiaSolicitudBean.setMat_sol_reconocer_nom(scbcrseBeanSelected.getScbcrse_title());
                materiaSolicitudBean.setMat_sol_creditos(scbcrseBeanSelected.getScbcrse_credit_hr_low());
                materiaSolicitudBean.setMat_sol_equivalente(materia.getMat_ext_codigo());
                materiaSolicitudBean.setMat_sol_equivalente_nom(materia.getMat_ext_descripcion());
                materiaSolicitudBean.setMat_sol_creditos_eqv(materia.getCreditos());
                materiaSolicitudBean.setMat_sol_condicion("EP");
              
                  if (!materiaSolicitudDao.buscaMateriasSolicitud(materiaSolicitudBean)){
                       materiaSolicitudDao.dmlMateriasSolicitud(1, materiaSolicitudBean);
                  }else{
                    addMessage("Matetria a Equiparar Solicitada ya Existe  ", 1);
                  }
            }
            limpiarFormulario();
            cargaDetalleEquiparacion();
            ScbcrseCodigo="";
        } catch (ExceptionConnection ex) {
            Logger.getLogger(EquiparacionMateriasController.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
    }

 
    
    public void limpiarFormulario() {
        scbcrseBeanSelected = new ScbcrseBean();
        arrayMateriasEquivalentes = new ArrayList<>();
        arrayMateriasExternasSelected = new ArrayList<>();
        this.arrayMateriasExternasSelected=new ArrayList<>();
    }

   
    //Elimina las materias asignadas a una equiparacion en especifico
    public void Eliminar() {
        MateriasSolicitudDao oMateria;
        
        
        
        try {
            oMateria = new MateriasSolicitudDao();
            this.detalleMateriaSeleccionada.setMat_sol_numero(this.equiparacionBean.getEqp_sol_numero());
            oMateria.IME_Materias(3, this.detalleMateriaSeleccionada);
            this.detalleMateriaSeleccionada = new MateriasSolicitudBean();
            addMessage("Registro Eliminado ", 1);
            cargaDetalleEquiparacion();
        } catch (ExceptionConnection ex) {
            addMessage("No es posible eliminar este registro", 1);
            Logger.getLogger(EquiparacionMateriasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    //Contiene la lista de los mensajes que aparecen en pantalla
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
    
    
     public void checkboxGeneralesChanged(AjaxBehaviorEvent event){
     //URLReporteInterno = "/SERVAGTMII/ReporteEquiparacion?pEquiparacion="+equiparacionBean.getEqp_sol_numero()+"&annoReporte="+this.annoReporte+"&periodoReporte="+this.periodoReporte+"&opcionReporteGenerales="+this.opcionReporteGenerales;
        // URLReporteEstudiante = "/SERVAGTMII/ReporteEquiparacionEstudiante?pEquiparacion="+equiparacionBean.getEqp_sol_numero()+"&annoReporte="+this.annoReporte+"&periodoReporte="+this.periodoReporte+"&opcionReporteGenerales="+this.opcionReporteGenerales;
    
    }

    public ArrayList<ScbcrseBean> getArrayScbcrse() {
        return arrayScbcrse;
    }

    public void setArrayScbcrse(ArrayList<ScbcrseBean> arrayScbcrse) {
        this.arrayScbcrse = arrayScbcrse;
    }

    public EquiparacionesBean getEquiparacionBean() {
        return equiparacionBean;
    }

    public void setEquiparacionBean(EquiparacionesBean equiparacionBean) {
        this.equiparacionBean = equiparacionBean;
    }

    public ScbcrseBean getScbcrseBeanSelected() {
        return scbcrseBeanSelected;
    }

    public void setScbcrseBeanSelected(ScbcrseBean scbcrseBeanSelected) {
        this.scbcrseBeanSelected = scbcrseBeanSelected;
    }

    public ArrayList<MateriaExternaBean> getArrayMateriasEquivalentes() {
        return arrayMateriasEquivalentes;
    }

    public void setArrayMateriasEquivalentes(ArrayList<MateriaExternaBean> arrayMateriasEquivalentes) {
        this.arrayMateriasEquivalentes = arrayMateriasEquivalentes;
    }

    public ArrayList<CarreraExternaBean> getArrayCarreraExterna() {
        return arrayCarreraExterna;
    }

    public void setArrayCarreraExterna(ArrayList<CarreraExternaBean> arrayCarreraExterna) {
        this.arrayCarreraExterna = arrayCarreraExterna;
    }

    public ArrayList<MateriaExternaBean> getArrayMateriaExterna() {
        return arrayMateriaExterna;
    }

    public void setArrayMateriaExterna(ArrayList<MateriaExternaBean> arrayMateriaExterna) {
        this.arrayMateriaExterna = arrayMateriaExterna;
    }

    public List<MateriaExternaBean> getArrayMateriasExternasSelected() {
        return arrayMateriasExternasSelected;
    }

    public void setArrayMateriasExternasSelected(List<MateriaExternaBean> arrayMateriasExternasSelected) {
        this.arrayMateriasExternasSelected = arrayMateriasExternasSelected;
    }

    
    
    
    
    
    
    public CarreraExternaBean getCarreraExternaSelected() {
        return carreraExternaSelected;
    }

    public void setCarreraExternaSelected(CarreraExternaBean carreraExternaSelected) {
        this.carreraExternaSelected = carreraExternaSelected;
    }

    public ArrayList<UniversidadBean> getArrayUniversidades() {
        return arrayUniversidades;
    }

    public void setArrayUniversidades(ArrayList<UniversidadBean> arrayUniversidades) {
        this.arrayUniversidades = arrayUniversidades;
    }

    public UniversidadBean getUniversidadSelected() {
        return universidadSelected;
    }

    public void setUniversidadSelected(UniversidadBean universidadSelected) {
        this.universidadSelected = universidadSelected;
    }

    public ArrayList<MateriasSolicitudBean> getArrayMateriaSolicitud() {
        return arrayMateriaSolicitud;
    }

    public void setArrayMateriaSolicitud(ArrayList<MateriasSolicitudBean> arrayMateriaSolicitud) {
        this.arrayMateriaSolicitud = arrayMateriaSolicitud;
    }

    public MateriasSolicitudBean getDetalleMateriaSeleccionada() {
        return detalleMateriaSeleccionada;
    }

    public void setDetalleMateriaSeleccionada(MateriasSolicitudBean detalleMateriaSeleccionada) {
        this.detalleMateriaSeleccionada = detalleMateriaSeleccionada;
    }

    public List<MateriaExternaBean> getFiltroRolDisponibles() {
        return filtroRolDisponibles;
    }

    public void setFiltroRolDisponibles(List<MateriaExternaBean> filtroRolDisponibles) {
        this.filtroRolDisponibles = filtroRolDisponibles;
    }

    public List<ScbcrseBean> getFiltroScbcrseBean() {
        return filtroScbcrseBean;
    }

    public void setFiltroScbcrseBean(List<ScbcrseBean> filtroScbcrseBean) {
        this.filtroScbcrseBean = filtroScbcrseBean;
    }

    public List<ScbcrseBean> getArrayMateriasEquiparaSelected() {
        return arrayMateriasEquiparaSelected;
    }

    public void setArrayMateriasEquiparaSelected(List<ScbcrseBean> arrayMateriasEquiparaSelected) {
        this.arrayMateriasEquiparaSelected = arrayMateriasEquiparaSelected;
    }

    public ArrayList<ScbcrseBean> getFilteredScbcrse() {
        return filteredScbcrse;
    }

    public void setFilteredScbcrse(ArrayList<ScbcrseBean> filteredScbcrse) {
        this.filteredScbcrse = filteredScbcrse;
    }

    public List<MateriasSolicitudBean> getListdetalleMateriaSeleccionadaselected() {
        return listdetalleMateriaSeleccionadaselected;
    }

    public void setListdetalleMateriaSeleccionadaselected(List<MateriasSolicitudBean> listdetalleMateriaSeleccionadaselected) {
        this.listdetalleMateriaSeleccionadaselected = listdetalleMateriaSeleccionadaselected;
    }

    
    
    
      public String getURLReporteEstudiante() {
         String concat = "/SERVAGTMII/ReporteEquiparacionEstudiante?p_equiparacion="+equiparacionBean.getEqp_sol_numero()+"&opcionReporteGenerales="+this.opcionReporteGenerales;
       URLReporteEstudiante=concat;
        return URLReporteEstudiante;
    }

    public void setURLReporteEstudiante(String URLReporteEstudiante) {
        this.URLReporteEstudiante = URLReporteEstudiante;
    }
    
    public boolean isOpcionReporteGenerales() {
        return opcionReporteGenerales;
    }

    public void setOpcionReporteGenerales(boolean opcionReporteGenerales) {
        this.opcionReporteGenerales = opcionReporteGenerales;
        
    }
    
    
}

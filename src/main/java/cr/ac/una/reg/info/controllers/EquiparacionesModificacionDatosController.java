/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.controllers;

import cr.ac.una.reg.info.beans.BitacoraSolicitudBean;
import cr.ac.una.reg.info.beans.CarreraExternaBean;
import cr.ac.una.reg.info.beans.Dr_siseg_usuarioBean;
import cr.ac.una.reg.info.beans.EquiparacionesBean;
import cr.ac.una.reg.info.beans.MateriaExternaBean;
import cr.ac.una.reg.info.dao.BitacoraSolicitudDao;
import cr.ac.una.reg.info.dao.CarreraExternaDao;

import cr.ac.una.reg.info.dao.EquiparacionesModificacionDatosDao;
import cr.ac.una.reg.info.dao.Smrprle_Dao;
import cr.ac.una.reg.info.dao.StvcollDao;
import cr.ac.una.reg.info.dao.StvdivsDao;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author N00148096
 */

@ManagedBean(name = "EquiparacionesModDatosController")
@ViewScoped
public class EquiparacionesModificacionDatosController {
      ArrayList<MateriaExternaBean> arrayMateriaExterna = new ArrayList<>();
       EquiparacionesBean equiparacionesBean;
       private List<SelectItem> facultadContinuar = new ArrayList();
       private List<SelectItem> escuelaContinuar = new ArrayList();
       private List<SelectItem> carreraContinuar = new ArrayList();
       private String carreraActual;
       private List<SelectItem> gradoContinuar = new ArrayList();
       private ArrayList<Object[]> Facult = null;
       private ArrayList<Object[]> escue = null;
       private ArrayList<Object[]> carr = null;
       private String sedeContinuarSelected;
       private String facultadContinuarSelected;
       private String escuelaContinuarSelected;
       private String carreraContinuarSelected;
       private ArrayList<CarreraExternaBean> arrayCarreraExterna = new ArrayList<>();
     
       public  CarreraExternaBean carreraSelected;
   
       private List<CarreraExternaBean> filtroCarreraExternaDisponible;
   
public  EquiparacionesModificacionDatosController() throws ExceptionConnection, SQLException{
     sedeContinuarSelected="";
     carreraActual="";
    
     equiparacionesBean =new EquiparacionesBean();
     facultadContinuar = new ArrayList();
     escuelaContinuar = new ArrayList();
     carreraContinuar = new ArrayList();
     gradoContinuar = new ArrayList();
       ListaCarreraExterna();
        cargaFacultad();
        cargaGrados();
}

//Carga los datos del solicitante de la equiparacion
    public void cargaEquiparacion() {
          this.carreraSelected = new  CarreraExternaBean();
        try {
            EquiparacionesModificacionDatosDao equiparacionesModDatosDao = new   EquiparacionesModificacionDatosDao();
            if (!equiparacionesModDatosDao.cargaEquiparacion(equiparacionesBean)) {
                addMessage("Numero de Solicitud No encontrada o ha sido Finalizada", 2);
            }else{
            sedeContinuarSelected=equiparacionesBean.getEqpSedeIngresar();
            this.carreraActual=equiparacionesBean.getEqp_carrera_continuar_est();
            this.carreraSelected.setCar_descripcion(equiparacionesBean.getDescripcionCarreraProcedencia());
            }
        } catch (ExceptionConnection ex) {
            Logger.getLogger(EquiparacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
  public void actualizarEquiparacion() throws ExceptionConnection {
    try {
         Dr_siseg_usuarioBean usuario= new Dr_siseg_usuarioBean();
          FacesContext facesContext = FacesContext.getCurrentInstance();
          usuario = (Dr_siseg_usuarioBean) facesContext.getExternalContext().getSessionMap().get("usuario");
        
          EquiparacionesModificacionDatosDao equiparacionesModDatosDao = new   EquiparacionesModificacionDatosDao();
          equiparacionesBean.setEqpSedeIngresar(sedeContinuarSelected);
          if (equiparacionesBean.getEqp_carrera_continuar_est().equals("0")){
              equiparacionesBean.setEqp_carrera_continuar_est(this.carreraActual);
          }
          equiparacionesModDatosDao.ActualizardatosEquiparacion(equiparacionesBean);
          
          
          
            BitacoraSolicitudDao bitacoraSolicitudDao=new BitacoraSolicitudDao();//INSERTA EL MOVIMIENTO EN LA BITACORA
            BitacoraSolicitudBean bitacoraSolicitudBean=new BitacoraSolicitudBean();
            bitacoraSolicitudBean.setBit_sol_id(equiparacionesBean.getEqp_sol_numero());
            bitacoraSolicitudBean.setUsuarioBean(usuario);
            bitacoraSolicitudBean.setBit_tipo_movimiento("M");
            bitacoraSolicitudBean.setBit_detalle("Datos Generales Modificados");
            bitacoraSolicitudDao.dmlDr_regt_bitacora_solicitud(bitacoraSolicitudBean);
          
          
          cargaEquiparacion();
           addMessage("Datos Modificados", 1);
  } catch (ExceptionConnection ex) {
            Logger.getLogger(EquiparacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
    
    //Carga el listado de los tipos de grados
    public void cargaGrados() {
        this.gradoContinuar.add(new SelectItem("Diplomado"));
        this.gradoContinuar.add(new SelectItem("Bachillerato"));
        this.gradoContinuar.add(new SelectItem("Licenciatura"));
        this.gradoContinuar.add(new SelectItem("Maestría"));
        this.gradoContinuar.add(new SelectItem("Doctorado"));
    }

   //Carga una lista de todas las facultades existentes en la UNA
    public void cargaFacultad() throws ExceptionConnection, SQLException {
        Object[] myBean1 = null;
        StvdivsDao stvdivsDao = new StvdivsDao();
        this.setFacult(stvdivsDao.consulta_facultad());
        for (int i = 0; i < getFacult().size(); i++) {
            myBean1 = getFacult().get(i);
            this.facultadContinuar.add(new SelectItem(myBean1[0].toString(),myBean1[0].toString()+" "+" "+ myBean1[1].toString()));
        }
    }

    
    
    //Carca la lista de todas las escuelas pertenecientes a una facultad en especifico
    public void CargaListaEscuelaContinuar() throws Exception {
        this.escuelaContinuar.clear();
        this.carreraContinuar.clear();
        Object[] Aux = null;
        for (int i = 0; i < this.Facult.size(); i++) {     //Busca el valor del combo en la lista 
            if (this.facultadContinuarSelected.equals(Facult.get(i)[0])) { // Se compara el valor del combo con el objeto[1] que entra de la lista
                Aux = this.Facult.get(i); // la lista posee objetos
            }
        }
        Object[] myBean2 = null;
        StvcollDao stvcollDao = new StvcollDao();
        escue = stvcollDao.consulta_escuela(Aux[0].toString());
        for (int i = 0; i < escue.size(); i++) {
            myBean2 = escue.get(i);
            this.escuelaContinuar.add(new SelectItem(myBean2[0].toString(), myBean2[0].toString()+" "+" "+ myBean2[1].toString()));
        }
    }
    
    
//Carga la lista de las carreras pertencientes a una escuela
    public void CargaListaCarreraContinuar() throws Exception {
        this.carreraContinuar.clear();
        Object[] Aux = null;
        for (int i = 0; i < this.escue.size(); i++) {     //Busca el valor del combo en la lista 
            if (this.escuelaContinuarSelected.equals(escue.get(i)[0])) { // Se compara el valor del combo con el objeto[1] que entra de la lista
                Aux = this.escue.get(i); // la lista posee objetos
            }
        }
        Object[] myBean2 = null;
        Smrprle_Dao smrprleDao = new Smrprle_Dao();
        carr = smrprleDao.Consulta_Carrera(Aux[0].toString());
        for (int i = 0; i < carr.size(); i++) {
            myBean2 = carr.get(i);
            this.carreraContinuar.add(new SelectItem(myBean2[0].toString(), myBean2[0].toString()+" "+" "+myBean2[1].toString()));
        }
    }

    //Carga la lista de las carreras impartidas por otras universidaes
     public void ListaCarreraExterna() throws ExceptionConnection {
        try {
            CarreraExternaDao carreraExternaDao = new CarreraExternaDao();
            arrayCarreraExterna = carreraExternaDao.ListarCarreraExterna(0);
        } catch (ExceptionConnection ex) {
            Logger.getLogger(CarreraExternaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
//Contiene la lista de los mensages que son mostrados en pnatalla
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

    public EquiparacionesBean getEquiparacionesBean() {
        return equiparacionesBean;
    }

    public void setEquiparacionesBean(EquiparacionesBean equiparacionesBean) {
        this.equiparacionesBean = equiparacionesBean;
    }

    public String getSedeContinuarSelected() {
        return sedeContinuarSelected;
    }

    public void setSedeContinuarSelected(String sedeContinuarSelected) {
        this.sedeContinuarSelected = sedeContinuarSelected;
    }

    public ArrayList<Object[]> getFacult() {
        return Facult;
    }

    public void setFacult(ArrayList<Object[]> Facult) {
        this.Facult = Facult;
    }

    public List<SelectItem> getFacultadContinuar() {
        return facultadContinuar;
    }

    public void setFacultadContinuar(List<SelectItem> facultadContinuar) {
        this.facultadContinuar = facultadContinuar;
    }

    public String getFacultadContinuarSelected() {
        return facultadContinuarSelected;
    }

    public void setFacultadContinuarSelected(String facultadContinuarSelected) {
        this.facultadContinuarSelected = facultadContinuarSelected;
    }

    public String getEscuelaContinuarSelected() {
        return escuelaContinuarSelected;
    }

    public void setEscuelaContinuarSelected(String escuelaContinuarSelected) {
        this.escuelaContinuarSelected = escuelaContinuarSelected;
    }

    public String getCarreraContinuarSelected() {
        return carreraContinuarSelected;
    }

    public void setCarreraContinuarSelected(String carreraContinuarSelected) {
        this.carreraContinuarSelected = carreraContinuarSelected;
    }

    public List<SelectItem> getEscuelaContinuar() {
        return escuelaContinuar;
    }

    public void setEscuelaContinuar(List<SelectItem> escuelaContinuar) {
        this.escuelaContinuar = escuelaContinuar;
    }

    public List<SelectItem> getCarreraContinuar() {
        return carreraContinuar;
    }

    public void setCarreraContinuar(List<SelectItem> carreraContinuar) {
        this.carreraContinuar = carreraContinuar;
    }

    public List<SelectItem> getGradoContinuar() {
        return gradoContinuar;
    }

    public void setGradoContinuar(List<SelectItem> gradoContinuar) {
        this.gradoContinuar = gradoContinuar;
    }

    public ArrayList<MateriaExternaBean> getArrayMateriaExterna() {
        return arrayMateriaExterna;
    }

    public void setArrayMateriaExterna(ArrayList<MateriaExternaBean> arrayMateriaExterna) {
        this.arrayMateriaExterna = arrayMateriaExterna;
    }

    public ArrayList<CarreraExternaBean> getArrayCarreraExterna() {
        return arrayCarreraExterna;
    }

    public void setArrayCarreraExterna(ArrayList<CarreraExternaBean> arrayCarreraExterna) {
        this.arrayCarreraExterna = arrayCarreraExterna;
    }

    public CarreraExternaBean getCarreraSelected() {
        return carreraSelected;
    }

    public void setCarreraSelected(CarreraExternaBean carreraSelected) {
        this.carreraSelected = carreraSelected;
    }

    public List<CarreraExternaBean> getFiltroCarreraExternaDisponible() {
        return filtroCarreraExternaDisponible;
    }

    public void setFiltroCarreraExternaDisponible(List<CarreraExternaBean> filtroCarreraExternaDisponible) {
        this.filtroCarreraExternaDisponible = filtroCarreraExternaDisponible;
    }


}

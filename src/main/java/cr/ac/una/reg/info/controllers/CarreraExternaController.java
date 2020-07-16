/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.controllers;

import cr.ac.una.reg.info.beans.CarreraExternaBean;
import cr.ac.una.reg.info.beans.UniversidadBean;
import cr.ac.una.reg.info.dao.CarreraExternaDao;
import cr.ac.una.reg.info.dao.UniversidadDao;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext; 
import javax.faces.model.SelectItem;
import org.icefaces.ace.event.SelectEvent;



@ManagedBean(name = "CarreraExternaController")
@ViewScoped
public class CarreraExternaController {

    private ArrayList<UniversidadBean> arrayUniversidades = new ArrayList<>();
    private ArrayList<CarreraExternaBean> arrayCarreraExterna = new ArrayList<>();
       private String colorMensage;

    public String getColorMensage() {
        return colorMensage;
    }

    public void setColorMensage(String colorMensage) {
        this.colorMensage = colorMensage;
    }

    private CarreraExternaBean carreraSelected = new CarreraExternaBean();
    private int p_opcion;
    private String visibleAgregarCarrera = "";
    private String visibleAgregarUniversidades;
    private String visibleEliminarCarrera;

    public String getVisibleAgregarCarrera() {
        return visibleAgregarCarrera;
    }

    public void setVisibleAgregarCarrera(String visibleAgregarCarrera) {
        this.visibleAgregarCarrera = visibleAgregarCarrera;
    }

    public String getVisibleEliminarCarrera() {
        return visibleEliminarCarrera;
    }

    public void setVisibleEliminarCarrera(String visibleEliminarCarrera) {
        this.visibleEliminarCarrera = visibleEliminarCarrera;
    }

    public String getVisibleAgregarUniversidades() {
        return visibleAgregarUniversidades;
    }

    public void setVisibleAgregarUniversidades(String visibleAgregarUniversidades) {
        this.visibleAgregarUniversidades = visibleAgregarUniversidades;
    }

 
      /**mau**********/
     
     private List<CarreraExternaBean> filtroCarreraExternaDisponible;
      List<CarreraExternaBean> arrayCarreraExternaSelected = new ArrayList<>();
      
      
      private List<UniversidadBean> filtroUniversidadDisponible;
      List<UniversidadBean> arrayUniversidadSelected = new ArrayList<>();
      
         public  UniversidadBean universidadSelected = new UniversidadBean();
      
       private int codUniversidad=0;
      private String desUniversidad="";
      private String universidad="";
      
    //*******************************//
    
//Carga el listado de todas las carreras y las Universidades
    public CarreraExternaController() throws ExceptionConnection {
        arrayUniversidades = new ArrayList<>();
        arrayCarreraExterna = new ArrayList<>();
       
        carreraSelected = new CarreraExternaBean();
        ListaCarreraExterna();
        ListaUniversidad();
    }
    
    
   //Carga la lista de todas las universidades    
    public void ListaUniversidad() throws ExceptionConnection {
        try {
            UniversidadDao universidadDao = new UniversidadDao();
            arrayUniversidades = universidadDao.ListarUniversidad();
        } catch (ExceptionConnection ex) {
            Logger.getLogger(CarreraExternaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   //Carga la lista de las carrera de otras universidades
    public void ListaCarreraExterna() throws ExceptionConnection {
        try {
             CarreraExternaDao carreraExternaDao = new CarreraExternaDao();
            arrayCarreraExterna = carreraExternaDao.ListarCarreraExterna(0);
        } catch (ExceptionConnection ex) {
            Logger.getLogger(CarreraExternaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  //Inicializa la pantalla para ingresar una carrera nueva
    public void nuevo() {
        carreraSelected = new CarreraExternaBean();
        visibleAgregarCarrera="true";
        p_opcion = 1;
        this.codUniversidad=0;
        this.desUniversidad="";
        universidad="";
    }
    
    public void accionCancelarFormEliminar(){
        visibleEliminarCarrera="false"; }
    
public void mostrarFormEliminar(){
        visibleEliminarCarrera="true"; }
   //Elimina una carrera

   public void eliminar() {
         try {
            CarreraExternaDao carreraExternaDao = new CarreraExternaDao();

           if (!carreraExternaDao.BuscaCarreraExternaAsignada(carreraSelected)){
                carreraExternaDao.dmlCarreraExterna(3, carreraSelected);
                addMessage("Registro eliminado correctamente", 1);
                this.visibleEliminarCarrera="false";
                ListaCarreraExterna();
                                                            colorMensage="color:green";

           }else{                                            colorMensage="color:red";

                addMessage("Carrera NO puede ser eliminada, Ya ha sido asignada a una equiparacion", 1);
           }
              
        } catch (ExceptionConnection ex) {                                            colorMensage="color:red";

            addMessage("No fue posible ejecutar la operación, contacte con el administrador del sistema", 2);
            Logger.getLogger(CarreraExternaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   public void mostrarForm(){
                 this.carreraSelected=new CarreraExternaBean();
                 universidad=" ";
         this.visibleAgregarCarrera="true";
         p_opcion=1;
}
    
           
  public void mostrarFormActualizar(){
         this.visibleAgregarCarrera="true";
  p_opcion=2;}
              
  public void cerrarFormAgregar(){
         this.visibleAgregarCarrera="false";
  this.codUniversidad=0;
        this.desUniversidad="";
        universidad="";}
  
      public void mostrarUniversidades(){
         this.visibleAgregarUniversidades="true";}
   
      public void add(){
         
       guardar();}
      
      
       public void limpiarForm(){
          this.carreraSelected=new CarreraExternaBean();
                    mostrarForm();    }
      
    //ingresa o modifica los datos de una carrera
    public void guardar() {
      UniversidadBean   universidades;
         try {
//            if ( p_opcion == 2 && this.carreraSelected.getUniversidad()==null){
                universidades=new UniversidadBean();
                universidades.setUni_id(this.codUniversidad);
                universidades.setUni_descripcion(this.desUniversidad);
                this.carreraSelected.setUniversidad(universidades);
//                }   
            
            
            
            if (carreraSelected.getCar_descripcion().equals("")
                || universidad.equals("")){            colorMensage="color:red";

               addMessage("Los campos carrera y Universidad NO pueden ser nulos", 1);
            }else{
              
            CarreraExternaDao carreraExternaDao = new CarreraExternaDao();
 
              if (!carreraExternaDao.buscaCarreraExternaExistente(carreraSelected)){
                         //   addMessage(carreraExternaDao.BuscaCarreraExternaAsignada(carreraSelected)+"", 1);

                  
                   if (!carreraExternaDao.BuscaCarreraExternaAsignada(carreraSelected)){
                       carreraExternaDao.dmlCarreraExterna(p_opcion, carreraSelected);
                                   colorMensage="color:green";

                        addMessage("Registro guardado correctamente", 1);
                       ListaCarreraExterna();
                   }else{            colorMensage="color:red";

                       addMessage("Registro No se puede modificar debido a que  ya ha sido asignado a una equiparacion", 1); 
                   }   
              }else{            colorMensage="color:red";

                  addMessage("Carrrera ya asignada a esta universidad", 1);
              }   
            }
        } catch (ExceptionConnection ex) {
            Logger.getLogger(CarreraExternaController.class.getName()).log(Level.SEVERE, null, ex);
                       colorMensage="color:red";

            addMessage("No fue posible ejecutar la operación, contacte con el administrador del sistema", 2);
        }
         
         
         
    }

    
      public void onRowSelect(SelectEvent event) {
                 universidadSelected = (UniversidadBean) event.getObject();

    
       
    }
      public void selectListener(SelectEvent event) {

        //  idEstudiante=(String) event.getObject();
                 universidadSelected = (UniversidadBean) event.getObject();
     
      }
      
      
      //optiene los datos de una universidad seleccionada
     public void seleccionaUniversidad(){
     universidad=universidadSelected.getUni_descripcion();
    this.codUniversidad=universidadSelected.getUni_id();
    this.desUniversidad=universidadSelected.getUni_descripcion();
    this.visibleAgregarUniversidades="false";
    }
      
      
    //Provee los mensajes al usuario en pantalla
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

    public ArrayList<UniversidadBean> getArrayUniversidades() {
        return arrayUniversidades;
    }

    public void setArrayUniversidades(ArrayList<UniversidadBean> arrayUniversidades) {
        this.arrayUniversidades = arrayUniversidades;
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
         if (this.p_opcion==2){
             this.codUniversidad=this.carreraSelected.getUniversidad().getUni_id();
             this.desUniversidad=this.carreraSelected.getUniversidad().getUni_descripcion();
        
       }
        
        
    }

     
    
    public int getP_opcion() {
        return p_opcion;
    }

    public void setP_opcion(int p_opcion) {
        this.p_opcion = p_opcion;
         if   (this.p_opcion==2){
             universidad=this.carreraSelected.getUniversidad().getUni_descripcion();
            
               this.codUniversidad=this.carreraSelected.getUniversidad().getUni_id();
               this.desUniversidad=this.carreraSelected.getUniversidad().getUni_descripcion();
        }else{
                 universidad="";   
         }
    }

    public List<CarreraExternaBean> getFiltroCarreraExternaDisponible() {
        return filtroCarreraExternaDisponible;
    }

    public void setFiltroCarreraExternaDisponible(List<CarreraExternaBean> filtroCarreraExternaDisponible) {
        this.filtroCarreraExternaDisponible = filtroCarreraExternaDisponible;
    }

    public List<CarreraExternaBean> getArrayCarreraExternaSelected() {
        return arrayCarreraExternaSelected;
    }

    public void setArrayCarreraExternaSelected(List<CarreraExternaBean> arrayCarreraExternaSelected) {
        this.arrayCarreraExternaSelected = arrayCarreraExternaSelected;
    }

    public List<UniversidadBean> getFiltroUniversidadDisponible() {
        return filtroUniversidadDisponible;
    }

    public void setFiltroUniversidadDisponible(List<UniversidadBean> filtroUniversidadDisponible) {
        this.filtroUniversidadDisponible = filtroUniversidadDisponible;
    }

    public List<UniversidadBean> getArrayUniversidadSelected() {
        return arrayUniversidadSelected;
    }

    public void setArrayUniversidadSelected(List<UniversidadBean> arrayUniversidadSelected) {
        this.arrayUniversidadSelected = arrayUniversidadSelected;
    }

    public int getCodUniversidad() {
        return codUniversidad;
    }

    public void setCodUniversidad(int codUniversidad) {
        this.codUniversidad = codUniversidad;
    }

    public String getDesUniversidad() {
        return desUniversidad;
    }

    public void setDesUniversidad(String desUniversidad) {
        this.desUniversidad = desUniversidad;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public UniversidadBean getUniversidadSelected() {
        return universidadSelected;
    }

    public void setUniversidadSelected(UniversidadBean universidadSelected) {
        this.universidadSelected = universidadSelected;
    }

      
    
    
  
}

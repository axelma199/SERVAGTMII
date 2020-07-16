/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.controllers;

import cr.ac.una.reg.info.beans.CarreraExternaBean;
import cr.ac.una.reg.info.beans.MateriaExternaBean;
import cr.ac.una.reg.info.beans.UniversidadBean;
import cr.ac.una.reg.info.dao.CarreraExternaDao;
import cr.ac.una.reg.info.dao.MateriaExternaDao;
import cr.ac.una.reg.info.dao.UniversidadDao;
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
import org.icefaces.ace.event.SelectEvent;

@ManagedBean(name = "MateriaExternaController")
@ViewScoped
public class MateriaExternaController {

    public  MateriaExternaBean materiaSelected = new MateriaExternaBean();
    ArrayList<MateriaExternaBean> arrayMateriaExterna = new ArrayList<>();
    private ArrayList<CarreraExternaBean> arrayCarreraExterna = new ArrayList<>();
    private int p_opcion;
    private String ConsecutivoCME;
private String visibleMostrarUniversidad;
private String visibleEliminarMateria;
private String colorMensage="";

    public String getColorMensage() {
        return colorMensage;
    }

    public void setColorMensage(String colorMensage) {
        this.colorMensage = colorMensage;
    }

    public String getVisibleEliminarMateria() {
        return visibleEliminarMateria;
    }

    public void setVisibleEliminarMateria(String visibleEliminarMateria) {
        this.visibleEliminarMateria = visibleEliminarMateria;
    }

    private UniversidadBean UniversidadSelected;

    public String getVisibleMostrarUniversidad() {
        return visibleMostrarUniversidad;
    }

    public void setVisibleMostrarUniversidad(String visibleMostrarUniversidad) {
        this.visibleMostrarUniversidad = visibleMostrarUniversidad;
    }
    public String getVisibleAgregarMateria() {
        return visibleAgregarMateria;
    }

    public void setVisibleAgregarMateria(String visibleAgregarMateria) {
        this.visibleAgregarMateria = visibleAgregarMateria;
    }
    public void accionCancelarFormEliminar(){
    this.visibleEliminarMateria="false";}

      public void mostrarFormEliminar(){
    this.visibleEliminarMateria="true";}
      
  public void mostrarDialogModoficar(){
  this.visibleAgregarMateria="true";}
  
      /**mau**********/
    
     private List<MateriaExternaBean> filtroMateriasExternaDisponible;
      List<MateriaExternaBean> arrayMateriasSelected = new ArrayList<>();
     
       private List<MateriaExternaBean> filtroMateriasExternaDisponibleMaterias;
      private List<CarreraExternaBean> filtroCarreraExternaDisponible;
    
        private int codUniversidad=0;
      private int codCarrera=0;
      private String desCarrera="";
      
      private String carrera;
      private String universidad;
       private boolean materiaExtDeOficio;
       
       
        private ArrayList<UniversidadBean> arrayUniversidades = new ArrayList<>();
        
        private List<UniversidadBean> filtroUniversidadDisponible;
        List< UniversidadBean>arrayuniversidadSelected= new ArrayList<>();
         public  UniversidadBean universidadSelected = new UniversidadBean();
    ////////***************//////////
    private String visibleAgregarMateria;
    
    
    public MateriaExternaController() throws ExceptionConnection {
        arrayMateriaExterna = new ArrayList<>();
        arrayCarreraExterna = new ArrayList<>();
         arrayUniversidades = new ArrayList<>();
         universidadSelected = new UniversidadBean();
        materiaSelected = new MateriaExternaBean();
        this.materiaExtDeOficio=false;
        ListaCarreraExterna();
        ListaMateria();
        ListaUniversidad();
        
        BuscarConsecutivoCME();
        
    }

    public ArrayList<MateriaExternaBean> getArrayMateriaExterna() {
        return arrayMateriaExterna;
    }

    public void setArrayMateriaExterna(ArrayList<MateriaExternaBean> arrayMateriaExterna) {
        this.arrayMateriaExterna = arrayMateriaExterna;
    }

   
    //Muestra la lista de las materias regsitradas de otras univerisidades 
    public void ListaMateria() throws ExceptionConnection {
        try {
            this.materiaExtDeOficio=false;
            MateriaExternaDao materiaExternaDao = new MateriaExternaDao();
            arrayMateriaExterna = materiaExternaDao.ListarMateriaExterna(0);
        } catch (ExceptionConnection ex) {
            Logger.getLogger(CarreraExternaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   //Lista las carreras registradas de otras universidades
    public void ListaCarreraExterna() throws ExceptionConnection {
        try {
            CarreraExternaDao carreraExternaDao = new CarreraExternaDao();
            arrayCarreraExterna = carreraExternaDao.ListarCarreraExterna(0);
        } catch (ExceptionConnection ex) {
            Logger.getLogger(CarreraExternaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
  //Retorna la lista de Universidades
    public void ListaUniversidad() throws ExceptionConnection {
        try {
            UniversidadDao universidadDao = new UniversidadDao();
            arrayUniversidades = universidadDao.ListarUniversidad();
        } catch (ExceptionConnection ex) {
            Logger.getLogger(CarreraExternaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   //Prepara la pagina para insertar un nuevo registro
    public void nuevo() {
        this.materiaSelected = new MateriaExternaBean();
       // this.materiaSelected.setCarreraExterna(null);
      //  this.materiaSelected=null;
        p_opcion = 1;
        this.codCarrera=0;
        this.codUniversidad=0;
        this.desCarrera="";
        this.materiaExtDeOficio=false;
    }

     public void limpiarFormulario(){
          this.materiaSelected=new MateriaExternaBean();
          this.mostrarDialogAgregarMaterias();
               }
     
     
    //Elimina una materia externa en especifico
    public void eliminar() {
        
        boolean eliminar=false;
        CarreraExternaBean carreraExterna;
        
            carreraExterna=new CarreraExternaBean ();
            carreraExterna.setCar_id(this.codCarrera);
            carreraExterna.setCar_descripcion(this.desCarrera);
            this.materiaSelected.setCarreraExterna(carreraExterna);
            this.materiaSelected.setMat_ext_uni_id(Integer.toString(this.materiaSelected.getCarreraExterna().getUniversidad().getUni_id()));
        try {
            MateriaExternaDao materiaExternaDao = new MateriaExternaDao();
          
            if (materiaExternaDao.BuscaMateriaExternaAsignada(materiaSelected)||materiaExternaDao.BuscaMateriaExternaOficioAsignada(materiaSelected)){
                eliminar=false;                                            colorMensage="color:red";

                addMessage("NO SE PUEDE  ELIMINAR: Materia Externa ya ha sido asignada a una equiparacion ", 1);
            }else{
                eliminar=true;
            }
            if (eliminar){ 
                 materiaExternaDao.dmlMateriaExterna(3, materiaSelected);
                 addMessage("Registro eliminado correctamente", 1);
                 ListaMateria();                                            colorMensage="color:green";

            }     
        } catch (ExceptionConnection ex) {                                            colorMensage="color:red";

            addMessage("No fue posible ejecutar la operación, contacte con el administrador del sistema", 2);
            Logger.getLogger(CarreraExternaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void mostrarUniversidad(){
    this.visibleMostrarUniversidad="true";}
    
    //Guarada o actualiza los datos de una materia externa
    public void guardar() {
         CarreraExternaBean carreraExterna;
 
         
        try {
           
           //  if ( p_opcion == 2 && this.materiaSelected.getCarreraExterna()==null){
//                carreraExterna=new CarreraExternaBean ();
//                carreraExterna.setCar_id(this.codCarrera);
//                carreraExterna.setCar_descripcion(this.desCarrera);
                
            
            
                if (this.materiaExtDeOficio){
                    this.materiaSelected.setMat_ext_oficio("S");
                }else{
                    this.materiaSelected.setMat_ext_oficio("N");
                }
                
                if (this.codUniversidad!=0){  
                    materiaSelected.setMat_ext_uni_id(Integer.toString(this.codUniversidad));
                
                }
                
            //    this.materiaSelected.setCarreraExterna(carreraExterna);
                
//             }else{
//                 if (this.materiaSelected.getCarreraExterna()==null && p_opcion == 1){
//                 carreraExterna=new CarreraExternaBean ();
//                 carreraExterna.setCar_id(0);
//                 carreraExterna.setCar_descripcion("");
//                 this.materiaSelected.setCarreraExterna(carreraExterna);
//                 }
             //}
            
                
             if (this.materiaSelected.getMat_ext_codigo().equals("")
             || this.materiaSelected.getMat_ext_descripcion().equals("")
           ){
             //|| this.materiaSelected.getCarreraExterna().getCar_descripcion().equals("")){
               addMessage("NO deben existir campos en blanco", 1);  
                                            colorMensage="color:red";

            
            
            
            }else{
                MateriaExternaDao materiaExternaDao = new MateriaExternaDao();
                if (materiaExternaDao.BuscaMateriaExternaOficioAsignada(materiaSelected)){
                                            colorMensage="color:red";

                    addMessage("Esta materia NO deben ser modificada por que ya ha sido asignada a una  esquiparacion", 1);         
                }else{
                     //if (materiaExternaDao.BuscaMateriaExternaAsignada(materiaSelected)){
                       //  addMessage("Esta materia NO deben ser modificada por que ya ha sido asignada a una  esquiparacion", 1);         
                     //}else{
                          if (materiaExternaDao.BuscaMateriaExternaExtrangero(materiaSelected)) {
                             addMessage("Codigo de materia ya registrada para esta Univerisdad", 1);  
                                            colorMensage="color:red";

                          }else{
                                       int uv=   materiaExternaDao.buscarUniversidad(universidad);
               materiaSelected.setMat_ext_uni_id(uv+"");

                              if(uv!=0){
                               materiaExternaDao.dmlMateriaExterna(p_opcion, materiaSelected);
                               
                               addMessage("Registro guardado correctamente", 1);
                                                                  colorMensage="color:green";

                               ListaMateria();
                               BuscarConsecutivoCME();
                               }
                              
                              else{
                   addMessage("Error al guardar id de universidad", 1);
                                                      colorMensage="color:red";

                        }
                          
                          }
                          }
                 
                      }
                  // }
        
        } catch (ExceptionConnection ex) {
            Logger.getLogger(CarreraExternaController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(ex.getMessage(), 2);

        }
    }
    
    
    //Genera un codigo propio en caso de que las materias de otras univerisidade no lo contengan
    public void BuscarConsecutivoCME(){
       try{
        MateriaExternaDao materiaExternaDao = new MateriaExternaDao(); 
        this.ConsecutivoCME="CME"+ String.valueOf(materiaExternaDao.BuscaConsecutivoMateriaExternaExtrangero());            
         } catch (ExceptionConnection ex) {
            Logger.getLogger(CarreraExternaController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(ex.getMessage(), 2);

        }
        
    }
    
//Contiene la lista de los mendajes que se muestran en pantalla
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
 public void cerrarForm(){
        this.visibleAgregarMateria="false";
 this.codUniversidad=0; 
        universidad="";}
 
    public void seleccionaUniversidad(){
         //universidad= universidadSelected.getUni_descripcion();
         //this.codUniversidad= universidadSelected.getUni_id();
   universidad=UniversidadSelected.getUni_descripcion();
     //this.codUniversidad=arrayuniversidadSelected.get(0).getUni_id();*/
    this.visibleMostrarUniversidad="false";
    }
    
     public void onRowSelect(SelectEvent event) {
        
         //carrera=((CarreraExternaBean) event.getObject()).getCar_descripcion();
//         universidad=((UniversidadBean) event.getObject()).getUni_descripcion();
//         this.codUniversidad=((UniversidadBean) event.getObject()).getUni_id();
         this.codCarrera=((CarreraExternaBean) event.getObject()).getCar_id();
        //this.desCarrera=((CarreraExternaBean) event.getObject()).getCar_descripcion();
    }
     
    public MateriaExternaBean getMateriaSelected() {
             return materiaSelected;
    }

    public void setMateriaSelected(MateriaExternaBean materiaSelected) {
        this.materiaSelected = materiaSelected;
       if (this.p_opcion==2){
           this.codCarrera=this.materiaSelected.getCarreraExterna().getCar_id();
           this.desCarrera=this.materiaSelected.getCarreraExterna().getCar_descripcion();
           this.universidad=this.materiaSelected.getCarreraExterna().getUniversidad().getUni_descripcion();
         }
       
        if (this.materiaSelected.getMat_ext_oficio()!=null){
            if (this.materiaSelected.getMat_ext_oficio().equals("S")){
                this.materiaExtDeOficio=true;
             }else{
                this.materiaExtDeOficio=false;
             }
        
       }
         
       
    }

    public ArrayList<CarreraExternaBean> getArrayCarreraExterna() {
        return arrayCarreraExterna;
    }

    public void setArrayCarreraExterna(ArrayList<CarreraExternaBean> arrayCarreraExterna) {
        this.arrayCarreraExterna = arrayCarreraExterna;
    }

    public int getP_opcion() {
        return p_opcion;
    }

    public void setP_opcion(int p_opcion) {
        this.p_opcion = p_opcion;
         if   (this.p_opcion==2){
             
             carrera=this.materiaSelected.getCarreraExterna().getCar_descripcion();
            universidad=this.materiaSelected.getCarreraExterna().getUniversidad().getUni_descripcion();
              // this.codCarrera=this.materiaSelected.getCarreraExterna().getCar_id();
               //this.desCarrera=this.materiaSelected.getCarreraExterna().getCar_descripcion();
        }else{
                 carrera="";   
                 universidad="";
         }
    }

    public List<MateriaExternaBean> getFiltroMateriasExternaDisponible() {
        return filtroMateriasExternaDisponible;
    }

    public void setFiltroMateriasExternaDisponible(List<MateriaExternaBean> filtroMateriasExternaDisponible) {
        this.filtroMateriasExternaDisponible = filtroMateriasExternaDisponible;
    }

    public List<CarreraExternaBean> getFiltroCarreraExternaDisponible() {
        return filtroCarreraExternaDisponible;
    }

    public void setFiltroCarreraExternaDisponible(List<CarreraExternaBean> filtroCarreraExternaDisponible) {
        this.filtroCarreraExternaDisponible = filtroCarreraExternaDisponible;
    }

    public List<MateriaExternaBean> getArrayMateriasSelected() {
        return arrayMateriasSelected;
    }

    public void setArrayMateriasSelected(List<MateriaExternaBean> arrayMateriasSelected) {
        this.arrayMateriasSelected = arrayMateriasSelected;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }
    
    
    

    public boolean isMateriaExtDeOficio() {
        return materiaExtDeOficio;
    }

    public void setMateriaExtDeOficio(boolean materiaExtDeOficio) {
        this.materiaExtDeOficio = materiaExtDeOficio;
    }

    public ArrayList<UniversidadBean> getArrayUniversidades() {
        return arrayUniversidades;
    }

    public void setArrayUniversidades(ArrayList<UniversidadBean> arrayUniversidades) {
        this.arrayUniversidades = arrayUniversidades;
    }

    public List<UniversidadBean> getFiltroUniversidadDisponible() {
        return filtroUniversidadDisponible;
    }

    public void setFiltroUniversidadDisponible(List<UniversidadBean> filtroUniversidadDisponible) {
        this.filtroUniversidadDisponible = filtroUniversidadDisponible;
    }

    public List<UniversidadBean> getArrayuniversidadSelected() {
        return arrayuniversidadSelected;
    }

    public void setArrayuniversidadSelected(List<UniversidadBean> arrayuniversidadSelected) {
        this.arrayuniversidadSelected = arrayuniversidadSelected;
    }

    
    
    
    public UniversidadBean getUniversidadSelected() {
        return universidadSelected;
    }

    public void setUniversidadSelected(UniversidadBean universidadSelected) {
        this.universidadSelected = universidadSelected;
    }

    public String getConsecutivoCME() {
        return ConsecutivoCME;
    }

    public void setConsecutivoCME(String ConsecutivoCME) {
        this.ConsecutivoCME = ConsecutivoCME;
    }

    private void mostrarDialogAgregarMaterias() {
           this.p_opcion=1;
         this.visibleAgregarMateria = "true";

    }

     public void selectListener(SelectEvent event) {
        universidad= "fres";

        //  idEstudiante=(String) event.getObject();
        UniversidadSelected = (UniversidadBean) event.getObject();
     
      }
      
    
    
}

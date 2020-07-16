package cr.ac.una.reg.info.controllers;

import cr.ac.una.reg.info.beans.StvnatnBean;
import cr.ac.una.reg.info.beans.UniversidadBean;
import cr.ac.una.reg.info.dao.StvnatnDao;
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
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import org.icefaces.ace.event.SelectEvent;

@ManagedBean(name = "UniversidadController")
@ViewScoped
public class UniversidadController {

    ArrayList<UniversidadBean> arrayUniversidades = new ArrayList<>();
    private UniversidadBean UniversidadSelected = new UniversidadBean();
    private List<SelectItem> paises = new ArrayList();
    private int p_opcion;
     private String visibleAgregarMateria = "";
     private String visibleEliminarMateria = "";
private int cont=0;
    
    
    /**mau**********/
     private List<UniversidadBean> filtroUniversidadDisponible;
      List<UniversidadBean> arrayUniversidadSelected = new ArrayList<>();
       private String desAbilitaBotonGuardar;
       boolean paisSeleccionado;
////////*****************************************///////   
    private String colorMensage;

    public String getColorMensage() {
        return colorMensage;
    }

    public void setColorMensage(String colorMensage) {
        this.colorMensage = colorMensage;
    }

    public UniversidadController() throws ExceptionConnection {
        arrayUniversidades = new ArrayList<>();
        UniversidadSelected = new UniversidadBean();
        paises = new ArrayList();
        ListaUniversidad();
        CargaPises();
        this.paisSeleccionado=false;
        
        
    }

    //carga la lista de los paise existentes en BANNER
    public void CargaPises() {
        try {
            ArrayList<StvnatnBean> arraypaises = new ArrayList<>();
            StvnatnDao stvnatnDao = new StvnatnDao();
            arraypaises = stvnatnDao.ListarStvnatn();
            Iterator iterador = arraypaises.listIterator(); //Le solicito a la lista que me devuelva un iterador con todos los el elementos contenidos en ella

            while (iterador.hasNext()) {

                StvnatnBean objeto = (StvnatnBean) iterador.next(); //Obtengo el elemento contenido                    
                paises.add(new SelectItem(objeto.getStvnatn_code(), objeto.getStvnatn_nation()));
            }

        } catch (ExceptionConnection ex) {
            Logger.getLogger(UniversidadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Carga la lista de las universidades registradas
    public void ListaUniversidad() throws ExceptionConnection {
        try {
            UniversidadDao universidadDao = new UniversidadDao();
            arrayUniversidades = universidadDao.ListarUniversidad();
         } catch (ExceptionConnection ex) {
            Logger.getLogger(UniversidadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificar() {
                mostrarDialogAgregarUniversidades();

        p_opcion = 2;
         guardar();
     }

    public void nuevo() {
        UniversidadSelected = new UniversidadBean();
        p_opcion = 1;
        desAbilitaBotonGuardar="true";
    }

   
    //Elimina una universidad en especifico
    public void eliminar() {
        try {
            UniversidadDao universidadDao = new UniversidadDao();
 
            universidadDao.dmlUniversidad(3, UniversidadSelected);
           this.visibleEliminarMateria="false";     colorMensage="color:green";

            addMessage("Registro eliminado correctamente", 1);
            ListaUniversidad();
        } catch (ExceptionConnection ex) {                                          
            colorMensage="color:red";

            addMessage("No fue posible ejecutar la operación, contacte con el administrador del sistema", 1);
            Logger.getLogger(UniversidadController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   public void mostrarDialogEliminarUniversidad() {
       // this.registrarMateriaBean = new FHMateriaBean();
       // solicitanteSelected = new SolicitanteBean();
        p_opcion = 3;
       //  this.desAbilitaBotonGuardar="true";
        this.visibleEliminarMateria = "true";

    }
   
   public void mostrarDialogModoficarSolicitante() {
       // this.registrarMateriaBean = new FHMateriaBean();
       
       // solicitanteSelected = new SolicitanteBean();
        p_opcion = 2; 
        this.visibleAgregarMateria = "true";

    }
   
   public void actionCancelarEliminarUniversidad() throws ExceptionConnection {
        //this.SolicitanteBean = new FHMateriaBean();
        UniversidadSelected = new UniversidadBean();
         ListaUniversidad();
       this.visibleEliminarMateria= "false";

    }
   
     public void actionCancelarAgregarUniversidad() throws ExceptionConnection {
        //this.SolicitanteBean = new FHMateriaBean();
        UniversidadSelected = new UniversidadBean();
         ListaUniversidad();
         this.visibleAgregarMateria= "false";

    }
   
   public void limpiarFormulario(){
          this.UniversidadSelected=new UniversidadBean();
          this.mostrarDialogAgregarUniversidades();
               }
    
    //Guarda los datos de la universidad
    public void guardar() {
        try {
            
            if (UniversidadSelected.getUni_descripcion().equals("")||UniversidadSelected.getStvnatn().getStvnatn_code()==null){
               addMessage("No se deben dejar campos en blanco", 1);
            }else{
                
                 UniversidadDao universidadDao = new UniversidadDao();

            if(!universidadDao.buscarUniversidad(UniversidadSelected)) {   
             
            universidadDao.dmlUniversidad(p_opcion, UniversidadSelected);
            colorMensage="color:green";
            addMessage("Registro guardado correctamente", 1);
            ListaUniversidad();}
            else{                colorMensage="color:red";

                              addMessage("No se puede agregar , universidad ya existe!!", 1);
}
            }
        } catch (ExceptionConnection ex) {
            Logger.getLogger(UniversidadController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("No fue posible ejecutar la operación, contacte con el administrador del sistema", 1);
        }
    }
    
    
    
     public void comboPaisesChange(ValueChangeEvent event){  
 
        if (event.getNewValue()==null){
            this.desAbilitaBotonGuardar="true";
              colorMensage="color:green";
            this.paisSeleccionado=false;
        }else{
            this.desAbilitaBotonGuardar="false";
            this.paisSeleccionado=true;
        }
     }
     
     
     public void namedChanged(AjaxBehaviorEvent event) {
     this.desAbilitaBotonGuardar="true";
//              if (this.paisSeleccionado){
//                 this.desAbilitaBotonGuardar="false";
//                 }else{
//                  this.desAbilitaBotonGuardar="true";
//              }   
        
}
     public void universidadValueChange(ValueChangeEvent event) {
        if (event.getNewValue().equals("")){
            this.desAbilitaBotonGuardar="true";
        }else{
              if (this.paisSeleccionado){               

                 this.desAbilitaBotonGuardar="false";
              }   
        }
      
   }
     
     
      public void selectListener(SelectEvent event) {

        //  idEstudiante=(String) event.getObject();
        UniversidadSelected = (UniversidadBean) event.getObject();
     
      }
      
       public void mostrarDialogAgregarUniversidades() {
       // this.registrarMateriaBean = new FHMateriaBean();
       this.p_opcion=1;
         this.visibleAgregarMateria = "true";

    }

      
      
    public ArrayList<UniversidadBean> getArrayUniversidades() {
        return arrayUniversidades;
    }

    public void setArrayUniversidades(ArrayList<UniversidadBean> arrayUniversidades) {
        this.arrayUniversidades = arrayUniversidades;
    }

    public UniversidadBean getUniversidadSelected() {
        return UniversidadSelected;
    }

    public void setUniversidadSelected(UniversidadBean UniversidadSelected) {
        this.UniversidadSelected = UniversidadSelected;
    }

    public List<SelectItem> getPaises() {
        return paises;
    }

    public void setPaises(List<SelectItem> paises) {
        this.paises = paises;
    }

    public int getP_opcion() {
        return p_opcion;
    }

    public void setP_opcion(int p_opcion) {
        this.p_opcion = p_opcion;
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

    public String getDesAbilitaBotonGuardar() {
        return desAbilitaBotonGuardar;
    }

    public void setDesAbilitaBotonGuardar(String desAbilitaBotonGuardar) {
        this.desAbilitaBotonGuardar = desAbilitaBotonGuardar;
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
    
    
    
    
}

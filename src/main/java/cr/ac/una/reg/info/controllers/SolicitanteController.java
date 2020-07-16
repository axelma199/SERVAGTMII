/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.controllers;

import cr.ac.una.reg.info.beans.CantonBean;
import cr.ac.una.reg.info.beans.DistritoBean;
import cr.ac.una.reg.info.beans.ProvinciaBean;
import cr.ac.una.reg.info.beans.SolicitanteBean;
import cr.ac.una.reg.info.dao.SolicitanteDao;
import cr.ac.una.reg.info.dao.SpridenDao;
import cr.ac.una.reg.info.beans.SpridenBean;
import cr.ac.una.reg.info.dao.StvnatnDao;
import cr.ac.una.reg.info.beans.StvnatnBean;
import cr.ac.una.reg.info.beans.WarningBean;
import cr.ac.una.reg.info.dao.GtvzipcDaoImpl;
import cr.ac.una.reg.info.dao.StvcntyDaoImpl;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
//import org.primefaces.context.RequestContext;
import javax.faces.model.SelectItem;
import org.icefaces.ace.event.SelectEvent;

@ManagedBean(name = "SolicitanteController")
@ViewScoped
public class SolicitanteController {

    ArrayList<SolicitanteBean> arraySolicitantes = new ArrayList<>();
    private SolicitanteBean solicitanteSelected = new SolicitanteBean();
    private List<SelectItem> paises = new ArrayList();
    private int p_opcion;
    
    /**mau**********/
     private List<SolicitanteBean> filtroSolicitanteDisponible;
      List<SolicitanteBean> arraySolicitanteSelected = new ArrayList<>();
      private String desAbilitaBotonGuardar;
      
       private List<ProvinciaBean> listProvincias;
       private List provinciasItems;
       private List cantonesItems;
       private List distritosItems;
        private List<CantonBean> listCantones;
        private List <DistritoBean>listDistritos;
        private String provinciasSelected;
        private String cantonesSelected;
        private String distritosSelected;
        private ProvinciaBean provinciaBean;
        private GtvzipcDaoImpl gtvzipcDaoImpl;
        private StvcntyDaoImpl stvcntyDaoImpl;
        private String visibleAgregarSolicitante = "";
         private String visibleEliminarSolicitante = "";
         private String colorMensage;
      
////////*****************************************///////    
 
    public SolicitanteController() throws ExceptionConnection {
        arraySolicitantes = new ArrayList<>();
        solicitanteSelected = new SolicitanteBean();
        this.gtvzipcDaoImpl = new GtvzipcDaoImpl(); 
        this.stvcntyDaoImpl=new StvcntyDaoImpl();
        paises = new ArrayList();
        colorMensage="color:red";
        ListaSolicitantes();
        CargaPises();
       this.cargaProvincias();
//      this.cantonesSelected="0";
//      this.distritosSelected="0";
    }

   //Carga la lista de los paise existentes en BANNER           
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
            Logger.getLogger(SolicitanteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  //Carga la lista de todos los solicitantes 
       public void ListaSolicitantes() throws ExceptionConnection {
        try {
            SolicitanteDao solicitanteDao = new SolicitanteDao();
            arraySolicitantes = solicitanteDao.ListarDr_regt_solicitante();
        } catch (ExceptionConnection ex) {
            Logger.getLogger(SolicitanteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
       //Busca los datos de un solicitante en BANNER
       public void buscaSpriden() throws ExceptionConnection {
        try {
            SpridenDao spridenDao = new SpridenDao();
            SpridenBean spridenBean = new SpridenBean();
            spridenBean.setSpriden_id(solicitanteSelected.getSolte_id());
            if (!spridenDao.buscaSpriden(spridenBean)) {
                addMessage("Identificación no encontrada", 2);
            } else {
                  
//                if (solicitanteSelected.getSolte_nombre()!=null){
//                    solicitanteSelected.setSolte_nombre(spridenBean.getSpriden_last_name());
//                
//                  }else{
                    solicitanteSelected.setSolte_nombre("");
                    //solicitanteSelected.setSolte_nombre(spridenBean.getSpriden_first_name() +" "+spridenBean.getSpriden_last_name() );
                    solicitanteSelected.setSolteNombre(spridenBean.getSpriden_first_name());
                    solicitanteSelected.setSolteApellidos(spridenBean.getSpriden_last_name());
                    solicitanteSelected.setSolteSegNombre(spridenBean.getSpriden_mi());
//                } 
               }
        } catch (ExceptionConnection ex) {
            Logger.getLogger(SolicitanteController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

       //contiene la lista de los mendajes que se muestran en pantalla
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

 //Prepara la pagina para ingresar un nuevo solicitante
//////    public void nuevoSolicitante() {
//////        solicitanteSelected = new SolicitanteBean();
//////        p_opcion = 1;
//////         this.desAbilitaBotonGuardar="true";
//////    }

   //Pasa el estado a modificacion
    public void modificarSolicitante() {
        p_opcion = 2;
    }

   //Elimina un solictante en especifico
    public void eliminarSolicitante() {
        try {
            SolicitanteDao solicitanteDao = new SolicitanteDao();
            
            if(!solicitanteDao.buscarEquiparaciones(solicitanteSelected)){
            solicitanteDao.dmlSolicitante(3, solicitanteSelected);
                                             colorMensage="color:green";

            visibleEliminarSolicitante="false";
            addMessage("Registro eliminado correctamente", 1);}
            
            else
            {                                            colorMensage="color:red";

             addMessage("No se puede eliminar registro debido a que cuenta con equiparacion asociada", 1);}
            
            ListaSolicitantes();
        } catch (ExceptionConnection ex) {
            Logger.getLogger(SolicitanteController.class.getName()).log(Level.SEVERE, null, ex);
                                                       colorMensage="color:red";
            addMessage("No fue posible ejecutar la operación, contacte con el administrador del sistema", 1);
        }
    }

    
    //Guarda los datos de un soliictante
    public void guardarSolicitante() {
        //String provinciaDistrito;
        solicitanteSelected.setSolte_nombre("");
        try {
           
            if (solicitanteSelected.getStvnatnBean().getStvnatn_code()==null 
               || solicitanteSelected.getSolte_id().equals("")
               //|| solicitanteSelected.getSolte_nombre().equals("")
               || solicitanteSelected.getSolteApellidos().equals("")
               || solicitanteSelected.getSolteNombre().equals("")
               || this.provinciasSelected.equals("0")
               || this.cantonesSelected.equals("0")
               || this.distritosSelected.equals("0")){
                colorMensage="color:red";
               addMessage("Identificacion y Nombre NO pueden ser nulos o bien debe seleccionar un pais, provincia, canton y distrito", 1);
            }else{
            //provinciaDistrito=this.provinciasSelected + this.distritosSelected;
                solicitanteSelected.setGtvzipcCode(this.distritosSelected);
                solicitanteSelected.setStvcntyCode(this.cantonesSelected);
                
            SolicitanteDao solicitanteDao = new SolicitanteDao();
            
            
           if(!solicitanteDao.buscarSolicitante(solicitanteSelected)||p_opcion==2) {   

            
            solicitanteDao.dmlSolicitante(p_opcion, solicitanteSelected);
             colorMensage="color:green";
            addMessage("Registro guardado correctamente", 1);
           
            ListaSolicitantes();}
           
           else{
            colorMensage="color:red";

                              addMessage("No se puede agregar , solicitante ya existe!!", 1);}
            }
        } catch (ExceptionConnection ex) {
            Logger.getLogger(SolicitanteController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("No fue posible ejecutar la operación, contacte con el administrador del sistema", 1);
        }
    }

    public void comboPaisesChange(ValueChangeEvent event){  
     
        if (event.getNewValue()==null){
            this.desAbilitaBotonGuardar="true";
        }else{
            this.desAbilitaBotonGuardar="false";
        }
        
}
    
     
    //carga el listado de todas las provincias de BANNER
    public void cargaProvincias() {
        try {
            
            this.listProvincias = gtvzipcDaoImpl.cargaProvincias();
            this.provinciasItems = new ArrayList();
            this.provinciasItems.add(new SelectItem(0, "SELECCIONE PROVINCIA"));
            for (int i = 0; i < listProvincias.size(); i++) {
                this.provinciasItems.add(new SelectItem(listProvincias.get(i).getNumero(), listProvincias.get(i).getDescripcion().toUpperCase()));
            }///
          this.provinciasSelected="0";
        }catch (ExceptionConnection ex){
            Logger.getLogger(SolicitanteController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("No fue posible cargar las provincias", 1);

       }
   }//

//Carga la lista de los cantones de acuerdo a la provincia seleccionada
   public void cargaCantones() {
        try {
            //inicializaCombosParaInsercion();
            //StvcntyBusines stvcntyBusines = new StvcntyBusines();
            ProvinciaBean provinciaBean = new ProvinciaBean();
            provinciaBean.setNumero(Integer.valueOf(this.provinciasSelected));
            this.listCantones =  stvcntyDaoImpl.cargaCantones(provinciaBean);
            this.cantonesItems = new ArrayList();
            this.cantonesItems.add(new SelectItem(0, "SELECCIONE CANTON"));
            for (int i = 0; i < listCantones.size(); i++) {
                this.cantonesItems.add(new SelectItem(listCantones.get(i).getCodigo(), listCantones.get(i).getDescripcion().toUpperCase()));
            }//
       }catch(ExceptionConnection ex){
            Logger.getLogger(SolicitanteController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("No fue posible cargar los cantones", 1);
   }
   }
   
   public void cargaComboCantones(ValueChangeEvent event) {
     this.provinciasSelected=event.getNewValue().toString();
     this.inicializaCombosParaInsercion();
     this.cargaCantones();
     
     
    
   }
   
   
    public void cargaComboDistritos(ValueChangeEvent event) {
    this.cantonesSelected=event.getNewValue().toString();
    this.cargaDistritos();
   }
// 
//
   
    //Carga los distritos de acuerdo al canto seleccionado
    public void cargaDistritos(){
        try {
          if (Integer.valueOf(this.cantonesSelected)==0) {
              this.distritosItems = new ArrayList();
              this.distritosItems.add(new SelectItem(0, "SELECCIONE DISTRITO"));
          }else {
//            GtvzipcBusiness gtvzipcBusiness = new GtvzipcBusiness();
            CantonBean cantonBean = new CantonBean();
            cantonBean.setCodigo(Integer.valueOf(this.cantonesSelected));
            this.listDistritos = gtvzipcDaoImpl.cargaDistritos(cantonBean);
            this.distritosItems= new ArrayList();
            this.distritosItems.add(new SelectItem(0, "NO ASIGNADO"));
            for (int i=0; i < listDistritos.size(); i++) {
              this.distritosItems.add(new SelectItem(listDistritos.get(i).getCodigo(), listDistritos.get(i).getDescripcion().toUpperCase()));
            }//
          }//
      }catch(ExceptionConnection ex){
            Logger.getLogger(SolicitanteController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("No fue posible cargar los distritos", 1);
     
       }//
    }
    
    
    //Carga la provincia canton y distrito de un estudiante ya registrado
    public void cargaDireccionEstudianteEnCombo(String zip){
        boolean zip_valido=false;

        
        try{
            if(this.isNumeric(zip)){
               this.cargaProvincias();
               if(zip != null && zip.length() == 5 ){
                  this.provinciasSelected = zip.substring(0, 1);
                  this.cargaCantones();
                  this.cantonesSelected = zip.substring(0, 3);
                  this.cargaDistritos();
                  this.distritosSelected = zip;
                }else{
                   this.inicializaCombosParaInsercion();
                   this.provinciasSelected="";
                   this.cantonesSelected="";
                   this.distritosSelected="";
                }//

                 }else{
                   this.inicializaCombosParaInsercion();
                     this.provinciasSelected="";
                   this.cantonesSelected="";
                   this.distritosSelected="";
                }//


        }catch(Exception ex){
          Logger.getLogger(SolicitanteController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("No fue posible cargar la direccion del estudiante", 1);
        }
    }//

       private  boolean isNumeric(String cadena){

      try {
          Integer.parseInt(cadena);
          return true;
          } catch (NumberFormatException nfe){
           return false;
          }
        }
    
     public void inicializaCombosParaInsercion(){
         //this.provinciasSelected="";
         //this.cantonesSelected="";
         //this.distritosSelected ="";
         
            this.cantonesItems = new ArrayList();
            this.cantonesItems.add(new SelectItem(0, "SELECCIONE CANTON"));
            this.distritosItems= new ArrayList();
            this.distritosItems.add(new SelectItem(0, "SELECCIONE DISTRITO"));
    }
       
    
   public void mostrarDialogAgregarSolicitante() {
       // this.registrarMateriaBean = new FHMateriaBean();
       
        solicitanteSelected = new SolicitanteBean();
        p_opcion = 1;
         this.desAbilitaBotonGuardar="true";
        this.visibleAgregarSolicitante = "true";

    }
   
    public void mostrarDialogModoficarSolicitante() {
       // this.registrarMateriaBean = new FHMateriaBean();
       
       // solicitanteSelected = new SolicitanteBean();
        p_opcion = 2;
         this.desAbilitaBotonGuardar="true";
        this.visibleAgregarSolicitante = "true";

    }
    
     public void mostrarDialogEliminarSolicitante() {
       // this.registrarMateriaBean = new FHMateriaBean();
       
       // solicitanteSelected = new SolicitanteBean();
        p_opcion = 3;
       //  this.desAbilitaBotonGuardar="true";
        this.visibleEliminarSolicitante = "true";

    }

   public void actionCancelarIncluiSolicitante() throws ExceptionConnection {
        //this.SolicitanteBean = new FHMateriaBean();
        solicitanteSelected = new SolicitanteBean();
         ListaSolicitantes();
       this.visibleAgregarSolicitante= "false";

    }

    public void actionCancelarEliminarSolicitante() throws ExceptionConnection {
        //this.SolicitanteBean = new FHMateriaBean();
        solicitanteSelected = new SolicitanteBean();
         ListaSolicitantes();
       this.visibleEliminarSolicitante= "false";

    }
   
     public void selecSolicitanteListener(SelectEvent event) {
        solicitanteSelected = (SolicitanteBean) event.getObject();
    //      arraySolicitanteSelected=(List<SolicitanteBean>) event.getObject();
    }
   
   
    public ArrayList<SolicitanteBean> getArraySolicitantes() {
        return arraySolicitantes;
    }

    public void setArraySolicitantes(ArrayList<SolicitanteBean> arraySolicitantes) {
         
        this.arraySolicitantes = arraySolicitantes;
    }

    public SolicitanteBean getSolicitanteSelected() {
        if (solicitanteSelected.getSolteApellidos()!=null){
           solicitanteSelected.setSolte_nombre("");
        }
        return solicitanteSelected;
        
    }

    public void setSolicitanteSelected(SolicitanteBean solicitanteSelected) {
        this.solicitanteSelected = solicitanteSelected;
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
           this.cargaDireccionEstudianteEnCombo(solicitanteSelected.getGtvzipcCode());
        this.p_opcion = p_opcion;
    }

    public List<SolicitanteBean> getFiltroSolicitanteDisponible() {
        return filtroSolicitanteDisponible;
    }

    public void setFiltroSolicitanteDisponible(List<SolicitanteBean> filtroSolicitanteDisponible) {
        this.filtroSolicitanteDisponible = filtroSolicitanteDisponible;
    }

    public List<SolicitanteBean> getArraySolicitanteSelected() {
         
        return arraySolicitanteSelected;
    }

    public void setArraySolicitanteSelected(List<SolicitanteBean> arraySolicitanteSelected) {
        this.arraySolicitanteSelected = arraySolicitanteSelected;
    }

    public String getDesAbilitaBotonGuardar() {
        return desAbilitaBotonGuardar;
    }

    public void setDesAbilitaBotonGuardar(String desAbilitaBotonGuardar) {
        this.desAbilitaBotonGuardar = desAbilitaBotonGuardar;
    }

   
    
    
     public String getProvinciasSelected() {
        return provinciasSelected;
    }

    public void setProvinciasSelected(String provinciasSelected) {
        this.provinciasSelected = provinciasSelected;
    }

    public List getProvinciasItems() {
        return provinciasItems;
    }

    public void setProvinciasItems(List provinciasItems) {
        this.provinciasItems = provinciasItems;
    }

    public String getCantonesSelected() {
        return cantonesSelected;
    }

    public void setCantonesSelected(String cantonesSelected) {
        this.cantonesSelected = cantonesSelected;
    }

    public List getCantonesItems() {
        return cantonesItems;
    }

    public void setCantonesItems(List cantonesItems) {
        this.cantonesItems = cantonesItems;
    }

    public String getDistritosSelected() {
        return distritosSelected;
    }

    public void setDistritosSelected(String distritosSelected) {
        this.distritosSelected = distritosSelected;
    }

    public List getDistritosItems() {
        return distritosItems;
    }

    public void setDistritosItems(List distritosItems) {
        this.distritosItems = distritosItems;
    }

    public String getVisibleAgregarSolicitante() {
        return visibleAgregarSolicitante;
    }

    public void setVisibleAgregarSolicitante(String visibleAgregarSolicitante) {
        this.visibleAgregarSolicitante = visibleAgregarSolicitante;
    }

    public String getVisibleEliminarSolicitante() {
        return visibleEliminarSolicitante;
    }

    public void setVisibleEliminarSolicitante(String visibleEliminarSolicitante) {
        this.visibleEliminarSolicitante = visibleEliminarSolicitante;
    }

    public String getColorMensage() {
        return colorMensage;
    }

    public void setColorMensage(String colorMensage) {
        this.colorMensage = colorMensage;
    }
    
    
    
    
}

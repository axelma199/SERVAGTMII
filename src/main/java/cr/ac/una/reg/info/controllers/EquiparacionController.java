/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.controllers;

import cr.ac.una.reg.info.dao.SolicitanteDao;
import cr.ac.una.reg.info.dao.BitacoraSolicitudDao;
import cr.ac.una.reg.info.beans.EquiparacionesBean;
import cr.ac.una.reg.info.beans.DocumentosBean;
import cr.ac.una.reg.info.beans.Dr_siseg_usuarioBean;
import cr.ac.una.reg.info.beans.BitacoraSolicitudBean;
import cr.ac.una.reg.info.beans.CarreraExternaBean;
import cr.ac.una.reg.info.dao.EquiparacionesDao;
import cr.ac.una.reg.info.dao.DocumentosDao;
import cr.ac.una.reg.info.beans.MateriaExternaBean;
import cr.ac.una.reg.info.beans.SolicitanteBean;
import cr.ac.una.reg.info.dao.CarreraExternaDao;
import cr.ac.una.reg.info.dao.StvdivsDao;
import cr.ac.una.reg.info.dao.StvcollDao;
import cr.ac.una.reg.info.dao.Smrprle_Dao;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.io.IOException;
import java.sql.SQLException;
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

@ManagedBean(name = "EquiparacionController")
@ViewScoped
public class EquiparacionController {

    EquiparacionesBean equiparacionBean;
    ArrayList<MateriaExternaBean> arrayMateriaExterna = new ArrayList<>();
    ArrayList<DocumentosBean> arrayDocumentos = new ArrayList<>();
    private List<SelectItem> facultadContinuar = new ArrayList();
    private List<SelectItem> escuelaContinuar = new ArrayList();
    private List<SelectItem> carreraContinuar = new ArrayList();
    private List<SelectItem> gradoContinuar = new ArrayList();
    private ArrayList<Object[]> Facult = null;
    private ArrayList<Object[]> escue = null;
    private ArrayList<Object[]> carr = null;
    private String sedeContinuarSelected;
    private String facultadContinuarSelected;
    private String escuelaContinuarSelected;
    private String carreraContinuarSelected;
        private String gradoContinuarSelected;

    public String getGradoContinuarSelected() {
        return gradoContinuarSelected;
    }

    public void setGradoContinuarSelected(String gradoContinuarSelected) {
        this.gradoContinuarSelected = gradoContinuarSelected;
    }

    private SolicitanteBean solicitanteBean;

    public SolicitanteBean getSolicitanteBean() {
        return solicitanteBean;
    }

    public void setSolicitanteBean(SolicitanteBean solicitanteBean) {
        this.solicitanteBean = solicitanteBean;
    }
     private ArrayList<CarreraExternaBean> arrayCarreraExterna = new ArrayList<>();
   
     public  CarreraExternaBean carreraSelected = new CarreraExternaBean();
     
       private List<CarreraExternaBean> filtroCarreraExternaDisponible;

       
    public EquiparacionController() throws ExceptionConnection, SQLException {
        solicitanteBean=new SolicitanteBean();
        arrayCarreraExterna = new ArrayList<>();
        carreraSelected=new  CarreraExternaBean();
        equiparacionBean = new EquiparacionesBean();
        arrayDocumentos = new ArrayList<>();
        facultadContinuar = new ArrayList();
        escuelaContinuar = new ArrayList();
        carreraContinuar = new ArrayList();
        gradoContinuar = new ArrayList();
        Facult = null;
        escue = null;
        carr = null;
        ListaCarreraExterna();
        cargaDocumetos();
        cargaFacultad();
        cargaGrados();
    }

   
    //Carga los datos del solicitante de la equiparacion
    public void buscarSolicitante() {
        try {
            SolicitanteDao solicitanteDao = new SolicitanteDao();
            if (!solicitanteDao.buscaSolicitante(equiparacionBean.getSolicitanteBean())) {
                addMessage("Identificación no encontrada", 2);
            }
        } catch (ExceptionConnection ex) {
            Logger.getLogger(EquiparacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Carga el listado de todos los documentos
    public void cargaDocumetos() {
        try {
            DocumentosDao documentosDao = new DocumentosDao();
            arrayDocumentos = documentosDao.ListarDocumentos();
        } catch (ExceptionConnection ex) {
            Logger.getLogger(EquiparacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  //Da un check a los documentos pertenecientes a una equiparacion
    public Boolean verificarDocumentos() {
        int contDocChequeado=0;
        Iterator iterador = arrayDocumentos.listIterator();
        boolean verificado = true;
        while (iterador.hasNext()) {
            DocumentosBean objeto = (DocumentosBean) iterador.next(); //Obtengo el elemento contenido  
            
            if(objeto.getDoc_eqv()){objeto.setVerificado(true);}
            
            
            if (!objeto.getVerificado()) {
                verificado = false;
            }else{
                contDocChequeado=1;
            }
            
        
        }
        
            if (contDocChequeado==1){
                verificado = true;
             }   
        
        return verificado;
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
    
    //Guarda los datos de una nueva equiparacion
    public void guardaEquiparacion() throws ExceptionConnection {

        FacesContext context = FacesContext.getCurrentInstance();
        Dr_siseg_usuarioBean usuario= new Dr_siseg_usuarioBean();

  if (!equiparacionBean.getEqp_carrera_continuar_est().equals("0")){
      equiparacionBean.getSolicitanteBean().setSolte_id(this.solicitanteBean.getSolte_id());
          {
            if (this.carreraSelected!=null){
             equiparacionBean.setEqpCarreraProcedencia(this.carreraSelected.getCar_id());
             equiparacionBean.setEqpSedeIngresar(this.sedeContinuarSelected);
             FacesContext facesContext = FacesContext.getCurrentInstance();
           
             usuario = (Dr_siseg_usuarioBean) facesContext.getExternalContext().getSessionMap().get("usuario");
            EquiparacionesDao equiparacionDao = new EquiparacionesDao();
            equiparacionDao.insertaEquiparacion(equiparacionBean);//INSERTA LA EQUIPARACION
            
            
            BitacoraSolicitudDao bitacoraSolicitudDao=new BitacoraSolicitudDao();//INSERTA EL MOVIMIENTO EN LA BITACORA
            BitacoraSolicitudBean bitacoraSolicitudBean=new BitacoraSolicitudBean();
            bitacoraSolicitudBean.setBit_sol_id(equiparacionBean.getEqp_sol_numero());
            bitacoraSolicitudBean.setUsuarioBean(usuario);
            
            //Código nuevo
            usuario= new Dr_siseg_usuarioBean();
            usuario.setCodigo(this.solicitanteBean.getSolte_id());
            bitacoraSolicitudBean.setUsuarioBean(usuario);

             bitacoraSolicitudBean.setBit_tipo_movimiento("I");
            bitacoraSolicitudBean.setBit_detalle("Fue insertada la equiparación");
            bitacoraSolicitudDao.dmlDr_regt_bitacora_solicitud(bitacoraSolicitudBean);
            try {
                context.getExternalContext().getSessionMap().put("equiparacion", equiparacionBean);
                context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/faces/paginas/EquiparacionesMaterias.xhtml");

            } catch (IOException ex) {
                Logger.getLogger(EquiparacionController.class.getName()).log(Level.SEVERE, null, ex);
            }
      
          } else {
               addMessage("Debe seleccionar una carrera de procedencia", 1);
        }  
            }   {
            addMessage("Faltan documetos por verificar", 1);
        }
        
   }else{
            addMessage("Debe seleccionar una carrera", 1);
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

    public EquiparacionesBean getEquiparacionBean() {
        return equiparacionBean;
    }

    public void setEquiparacionBean(EquiparacionesBean equiparacionBean) {
        this.equiparacionBean = equiparacionBean;
    }

    public ArrayList<MateriaExternaBean> getArrayMateriaExterna() {
        return arrayMateriaExterna;
    }

    public void setArrayMateriaExterna(ArrayList<MateriaExternaBean> arrayMateriaExterna) {
        this.arrayMateriaExterna = arrayMateriaExterna;
    }

    public ArrayList<DocumentosBean> getArrayDocumentos() {
        return arrayDocumentos;
    }

    public void setArrayDocumentos(ArrayList<DocumentosBean> arrayDocumentos) {
        this.arrayDocumentos = arrayDocumentos;
    }

    public List<SelectItem> getFacultadContinuar() {
        return facultadContinuar;
    }

    public void setFacultadContinuar(List<SelectItem> facultadContinuar) {
        this.facultadContinuar = facultadContinuar;
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

    public ArrayList<Object[]> getFacult() {
        return Facult;
    }

    public void setFacult(ArrayList<Object[]> Facult) {
        this.Facult = Facult;
    }

    public ArrayList<Object[]> getEscue() {
        return escue;
    }

    public void setEscue(ArrayList<Object[]> escue) {
        this.escue = escue;
    }

    public ArrayList<Object[]> getCarr() {
        return carr;
    }

    public void setCarr(ArrayList<Object[]> carr) {
        this.carr = carr;
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

    public String getSedeContinuarSelected() {
        return sedeContinuarSelected;
    }

    public void setSedeContinuarSelected(String sedeContinuarSelected) {
        this.sedeContinuarSelected = sedeContinuarSelected;
    }
    
    
    
    
}

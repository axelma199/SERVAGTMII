/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.controllers;

import cr.ac.una.reg.info.beans.BitacoraSolicitudBean;
import cr.ac.una.reg.info.beans.EquivalenciasBean;
import cr.ac.una.reg.info.beans.Dr_regt_historialBean;
import cr.ac.una.reg.info.beans.Dr_siseg_usuarioBean;
import cr.ac.una.reg.info.beans.MateriasSolicitudBean;
import cr.ac.una.reg.info.dao.BitacoraSolicitudDao;
import cr.ac.una.reg.info.dao.EquivalenciasDao;
import cr.ac.una.reg.info.dao.HistorialDao;
import cr.ac.una.reg.info.dao.MateriasSolicitudDao;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext; 
import org.icefaces.ace.event.SelectEvent;

@ManagedBean(name = "MateriasSolicitudController")
@ViewScoped
/**
 *
 * @author JGA
 */
public class MateriasSolicitudController {

    private ArrayList<MateriasSolicitudBean> materiasReconocer;
    private ArrayList<Dr_regt_historialBean> materiasEquivalentes;
    private MateriasSolicitudBean MateriaSeleccionada;
    private Dr_regt_historialBean[] MateriasSeleccionadas;
    private ArrayList<Dr_regt_historialBean> detalleMaterias;
    private ArrayList<Dr_regt_historialBean> detalleMateriasHistorial;
    private ArrayList<MateriasSolicitudBean> detalle;
    private MateriasSolicitudBean materiaReconocida;
    private EquivalenciasBean equivalencia = new EquivalenciasBean();
    private MateriasSolicitudBean detalleMateriaSeleccionada;
    private String URLReporte;
    
    /**mau***/
    private List<MateriasSolicitudBean> filtroMateriasSolicitudDisponibles;
    List<MateriasSolicitudBean> arrayMateriasSolicitudSelected = new ArrayList<>();
    
    private List<Dr_regt_historialBean> filtroMateriasRegtHistorialDisponibles;
     List<Dr_regt_historialBean> arrayMateriasRegtHistorialSelected = new ArrayList<>();
    //*******************************
    

    @PostConstruct
    public void EquivalenciaController()  {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        setEquivalencia((EquivalenciasBean) facesContext.getExternalContext().getSessionMap().get("equivalencia"));
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("equivalencia");

        this.materiasReconocer = new ArrayList();
        this.materiasEquivalentes = new ArrayList();
        this.MateriaSeleccionada = new MateriasSolicitudBean();
        this.setDetalleMaterias((ArrayList<Dr_regt_historialBean>) new ArrayList());
        this.setDetalle(new ArrayList());
        this.materiaReconocida = new MateriasSolicitudBean();
        try {
            this.CargaMateriasReconocer();
        } catch (ExceptionConnection ex) {
            Logger.getLogger(MateriasSolicitudController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.CargaMateriasEquivalentes();
        } catch (ExceptionConnection ex) {
            Logger.getLogger(MateriasSolicitudController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cargaDetalle();
        
        
        //**mau*/
                 arrayMateriasSolicitudSelected     = new ArrayList<>();
                 arrayMateriasRegtHistorialSelected = new ArrayList<>();
         //**********************************
        
    }

    public void CargaMateriasReconocer() throws ExceptionConnection {
        MateriasSolicitudDao oMateria = new MateriasSolicitudDao();
        this.setMateriasReconocer(oMateria.cargaMateriasReconocer(this.equivalencia.getEqv_escuela_continuar()));
    }

    public void CargaMateriasEquivalentes() throws ExceptionConnection {
        HistorialDao oHistorial = new HistorialDao();
        this.setMateriasEquivalentes(oHistorial.cargaMateriasEquivaler());
    }

    public void AgregarMateria() {
        //this.setMateriaReconocida(this.MateriaSeleccionada);
        this.materiaReconocida=((MateriasSolicitudBean)this.arrayMateriasSolicitudSelected.get(0));
    }

    public void AgregarMateriasEquivalentes() {
       //this.detalleMaterias.clear();
        //this.detalleMaterias.addAll(Arrays.asList(this.MateriasSeleccionadas));
    
        
        /*************mau*////////////
        
        
        
        
        this.detalleMaterias.add(this.arrayMateriasRegtHistorialSelected.get(0));
        arrayMateriasRegtHistorialSelected = new ArrayList<>();
    
    
    
    
    }

    
    public void AgregarMateriasEquivalentes2() {
        Iterator iterador = arrayMateriasRegtHistorialSelected.listIterator();

        while (iterador.hasNext()) {
           Dr_regt_historialBean materia = (Dr_regt_historialBean) iterador.next(); //Obtengo el elemento contenido                    

            Iterator iteradorMaterias = this.detalleMaterias.listIterator();
            boolean verificado = true;
            while (iteradorMaterias.hasNext()) {
                 Dr_regt_historialBean materiaEquivaler = ( Dr_regt_historialBean) iteradorMaterias.next();
                if (materiaEquivaler.getCodigo().equals(materia.getCodigo())) {
                    verificado = false;
                }
            }
            if (verificado) {
               this.detalleMaterias.add(this.arrayMateriasRegtHistorialSelected.get(0));
            }
        }
        arrayMateriasRegtHistorialSelected = new ArrayList<>();
    }

    
    
    
    
    public void Guardar() throws ExceptionConnection {
        try {
            if (this.materiaReconocida.getMat_sol_reconocer()==null || detalleMaterias.isEmpty()) {
                addMessage("Debe escoger una materia a reconocer y una materia aprobada", 1);
            } else {
               // if (this.MateriasSeleccionadas.length != 0) {
                   if (!this.detalleMaterias.isEmpty()) {
                    MateriasSolicitudDao oMateria = new MateriasSolicitudDao();
                    this.materiaReconocida.setMat_sol_numero(this.equivalencia.getEqv_sol_numero());
                    this.materiaReconocida.getMateriasEquivaler().clear();
                    this.materiaReconocida.setMateriasEquivaler(this.detalleMaterias);
                    oMateria.IME_Materias(1, this.materiaReconocida);
                    this.MateriasSeleccionadas = null;
                    this.arrayMateriasRegtHistorialSelected=new ArrayList<>();
                    this.cargaDetalle();
                    this.materiaReconocida = new MateriasSolicitudBean();
                    this.detalleMaterias = new ArrayList();
                    addMessage("Se ha guardado exitosamente", 1);
                } else {
                    addMessage("Debe escoger al menos una materia a equivaler", 1);
                }
            }
        } catch (ExceptionConnection ex) {
            Logger.getLogger(MateriasSolicitudController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("Se produjo un error al insertar el registro, contacte al administrador del sistema", 1);
        }
    }

    public void Eliminar() throws ExceptionConnection {
        MateriasSolicitudDao oMateria = new MateriasSolicitudDao();
        this.detalleMateriaSeleccionada.setMat_sol_numero(this.equivalencia.getEqv_sol_numero());
        oMateria.IME_Materias(3, this.detalleMateriaSeleccionada);
        this.detalleMateriaSeleccionada = new MateriasSolicitudBean();
        this.cargaDetalle();
        addMessage("Registro Eliminado ", 1);
    }

    public void finalizar() throws ExceptionConnection {
      
        
        
        try {
            if (this.detalle.isEmpty()) {
                addMessage("Debe escoger una materia a reconocer y una materia aprobada", 1);
            } else {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                Dr_siseg_usuarioBean usuario = new Dr_siseg_usuarioBean();
                facesContext = FacesContext.getCurrentInstance();
                usuario = (Dr_siseg_usuarioBean) facesContext.getExternalContext().getSessionMap().get("usuario");

                EquivalenciasDao oEquivalencia = new EquivalenciasDao();
                oEquivalencia.IME_EQUIVALENCIAS(4, this.equivalencia);

                BitacoraSolicitudDao bitacoraSolicitudDao = new BitacoraSolicitudDao();//INSERTA EL MOVIMIENTO EN LA BITACORA
                BitacoraSolicitudBean bitacoraSolicitudBean = new BitacoraSolicitudBean();
                bitacoraSolicitudBean.setBit_sol_id(this.equivalencia.getEqv_sol_numero());
                bitacoraSolicitudBean.setUsuarioBean(usuario);
                bitacoraSolicitudBean.setBit_tipo_movimiento("2");
                bitacoraSolicitudBean.setBit_detalle("Fue completada la equivalencia");
                bitacoraSolicitudDao.dmlDr_regt_bitacora_solicitud(bitacoraSolicitudBean);

                facesContext.getExternalContext().redirect("ListaEquivalencias.xhtml");
            }
        } catch (IOException ex) {
            Logger.getLogger(EquivalenciasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargaDetalle() {
        try {
            MateriasSolicitudDao oMateria = new MateriasSolicitudDao();
            this.setDetalle(oMateria.cargaDetalle(this.equivalencia.getEqv_sol_numero()));
        } catch (ExceptionConnection ex) {
            Logger.getLogger(MateriasSolicitudController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("Se produjo un error al cargar los registros, contacte al administrador del sistema", 1);
        }
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

    
     public void onRowSelect(SelectEvent event) {
        String codigo = ((MateriasSolicitudBean) event.getObject()).getMat_sol_reconocer();
        String descripcion = ((MateriasSolicitudBean) event.getObject()).getMat_sol_reconocer_nom();
        //this.rolBeanEliminar.setCodigo(codigo);
        //this.rolBeanEliminar.setDescripcion(descripcion);
        System.out.println(codigo+"  "+descripcion);
    }
    
    
    
    /**
     * @return the materiasReconocer
     */
    public ArrayList<MateriasSolicitudBean> getMateriasReconocer() {
        return materiasReconocer;
    }

    /**
     * @param materiasReconocer the materiasReconocer to set
     */
    public void setMateriasReconocer(ArrayList<MateriasSolicitudBean> materiasReconocer) {
        this.materiasReconocer = materiasReconocer;
    }

    /**
     * @return the materiasEquivalentes
     */
    public ArrayList<Dr_regt_historialBean> getMateriasEquivalentes() {
        return materiasEquivalentes;
    }

    /**
     * @param materiasEquivalentes the materiasEquivalentes to set
     */
    public void setMateriasEquivalentes(ArrayList<Dr_regt_historialBean> materiasEquivalentes) {
        this.materiasEquivalentes = materiasEquivalentes;
    }

    /**
     * @return the MateriaSeleccionada
     */
    public MateriasSolicitudBean getMateriaSeleccionada() {
        return MateriaSeleccionada;
    }

    /**
     * @param MateriaSeleccionada the MateriaSeleccionada to set
     */
    public void setMateriaSeleccionada(MateriasSolicitudBean MateriaSeleccionada) {
        this.MateriaSeleccionada = MateriaSeleccionada;
    }

    /**
     * @return the MateriasSeleccionadas
     */
    public Dr_regt_historialBean[] getMateriasSeleccionadas() {
        return MateriasSeleccionadas;
    }

    /**
     * @param MateriasSeleccionadas the MateriasSeleccionadas to set
     */
    public void setMateriasSeleccionadas(Dr_regt_historialBean[] MateriasSeleccionadas) {
        this.MateriasSeleccionadas = MateriasSeleccionadas;
    }

    /**
     * @return the materiaReconocida
     */
    public MateriasSolicitudBean getMateriaReconocida() {
        return materiaReconocida;
    }

    /**
     * @param materiaReconocida the materiaReconocida to set
     */
    public void setMateriaReconocida(MateriasSolicitudBean materiaReconocida) {
        this.materiaReconocida = materiaReconocida;
    }

    /**
     * @return the equivalencia
     */
    public EquivalenciasBean getEquivalencia() {
        return equivalencia;
    }

    /**
     * @param equivalencia the equivalencia to set
     */
    public void setEquivalencia(EquivalenciasBean equivalencia) {
        this.equivalencia = equivalencia;
    }

    /**
     * @return the detalleMaterias
     */
    public ArrayList<Dr_regt_historialBean> getDetalleMaterias() {
        return detalleMaterias;
    }

    /**
     * @param detalleMaterias the detalleMaterias to set
     */
    public void setDetalleMaterias(ArrayList<Dr_regt_historialBean> detalleMaterias) {
        this.detalleMaterias = detalleMaterias;
    }

    /**
     * @return the detalle
     */
    public ArrayList<MateriasSolicitudBean> getDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(ArrayList<MateriasSolicitudBean> detalle) {
        this.detalle = detalle;
    }

    /**
     * @return the detalleMateriasHistorial
     */
    public ArrayList<Dr_regt_historialBean> getDetalleMateriasHistorial() {
        return detalleMateriasHistorial;
    }

    /**
     * @param detalleMateriasHistorial the detalleMateriasHistorial to set
     */
    public void setDetalleMateriasHistorial(ArrayList<Dr_regt_historialBean> detalleMateriasHistorial) {
        this.detalleMateriasHistorial = detalleMateriasHistorial;
    }

    /**
     * @return the detalleMateriaSeleccionada
     */
    public MateriasSolicitudBean getDetalleMateriaSeleccionada() {
        return detalleMateriaSeleccionada;
    }

    /**
     * @param detalleMateriaSeleccionada the detalleMateriaSeleccionada to set
     */
    public void setDetalleMateriaSeleccionada(MateriasSolicitudBean detalleMateriaSeleccionada) {
        this.detalleMateriaSeleccionada = detalleMateriaSeleccionada;
    }

    public String getURLReporte() {
        String concat = "/SERVAGTMII/ReporteEquiparacion?p_equiparacion=" + equivalencia.getEqv_sol_numero();

        URLReporte = concat;
        return URLReporte;
    }

    public void setURLReporte(String URLReporte) {
        this.URLReporte = URLReporte;
    }

    public List<MateriasSolicitudBean> getFiltroMateriasSolicitudDisponibles() {
        return filtroMateriasSolicitudDisponibles;
    }

    public void setFiltroMateriasSolicitudDisponibles(List<MateriasSolicitudBean> filtroMateriasSolicitudDisponibles) {
        this.filtroMateriasSolicitudDisponibles = filtroMateriasSolicitudDisponibles;
    }

    public List<MateriasSolicitudBean> getArrayMateriasSolicitudSelected() {
        return arrayMateriasSolicitudSelected;
    }

    public void setArrayMateriasSolicitudSelected(List<MateriasSolicitudBean> arrayMateriasSolicitudSelected) {
        this.arrayMateriasSolicitudSelected = arrayMateriasSolicitudSelected;
    }

    public List<Dr_regt_historialBean> getFiltroMateriasRegtHistorialDisponibles() {
        return filtroMateriasRegtHistorialDisponibles;
    }

    public void setFiltroMateriasRegtHistorialDisponibles(List<Dr_regt_historialBean> filtroMateriasRegtHistorialDisponibles) {
        this.filtroMateriasRegtHistorialDisponibles = filtroMateriasRegtHistorialDisponibles;
    }

    public List<Dr_regt_historialBean> getArrayMateriasRegtHistorialSelected() {
        return arrayMateriasRegtHistorialSelected;
    }

    public void setArrayMateriasRegtHistorialSelected(List<Dr_regt_historialBean> arrayMateriasRegtHistorialSelected) {
        this.arrayMateriasRegtHistorialSelected = arrayMateriasRegtHistorialSelected;
    }

    
    
    
    
}

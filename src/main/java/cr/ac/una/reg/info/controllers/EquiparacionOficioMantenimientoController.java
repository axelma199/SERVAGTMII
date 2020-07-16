package cr.ac.una.reg.info.controllers;

import cr.ac.una.reg.info.beans.EquiparacionOficioBean;
import cr.ac.una.reg.info.beans.MateriaExtEquipOficioBean;
import cr.ac.una.reg.info.beans.MateriaExternaBean;
import cr.ac.una.reg.info.beans.MateriaIntEquipOficioBean;
import cr.ac.una.reg.info.beans.ScbcrseBean;
import cr.ac.una.reg.info.beans.UniversidadBean;
import cr.ac.una.reg.info.dao.EquiparacionOficioDao;
import cr.ac.una.reg.info.dao.MateriaExternaDao;
import cr.ac.una.reg.info.dao.ScbcrseDao;
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

@ManagedBean(name = "EqpOfiMantenimientoController")
@ViewScoped
public class EquiparacionOficioMantenimientoController {

    private EquiparacionOficioBean equiparacionOficioBean;
    private ArrayList<UniversidadBean> arrayUniversidades = new ArrayList<>();
    ArrayList<MateriaExtEquipOficioBean> arrayMatExtEqpOfi = new ArrayList<MateriaExtEquipOficioBean>();//seleccionadas
    ArrayList<MateriaIntEquipOficioBean> arrayMatIntEqpOfi = new ArrayList<MateriaIntEquipOficioBean>();//seleccionadas
    ArrayList<MateriaExternaBean> arrayMateriaExterna = new ArrayList<>();//materias externas en el pdialog
    List<MateriaExternaBean> arrayMateriasExternasSelected = new ArrayList<>();//materias externas en el pdialog
    ArrayList<MateriaExternaBean> arrayMateriasEquivalentes = new ArrayList<>();
    ArrayList<ScbcrseBean> arrayScbcrse = new ArrayList<>();//materias internas en pdialog
    List<ScbcrseBean> arrayScbcrseSelected = new ArrayList<>();//materias internas en pdialog
    ArrayList<ScbcrseBean> arrayScbcrseEquivalentes = new ArrayList<>();

    public EquiparacionOficioMantenimientoController() throws ExceptionConnection {
        equiparacionOficioBean = new EquiparacionOficioBean();
        arrayUniversidades = new ArrayList<>();
        arrayMateriaExterna = new ArrayList<>();
        arrayMateriasExternasSelected = new ArrayList<>();
        arrayScbcrse = new ArrayList<>();//materias internas en pdialog
        arrayScbcrseSelected = new ArrayList<>();//materias internas en pdialo
        listaUniversidad();
        listaMateriaExterna();
        listaScbrse();
    }

    //Muestra la lista de las universidades que pertenecen a las equiparaciones por oficio
    public void listaUniversidad() throws ExceptionConnection {
        try {
            UniversidadDao universidadDao = new UniversidadDao();
            arrayUniversidades = universidadDao.ListarUniversidad();
        } catch (ExceptionConnection ex) {
            Logger.getLogger(CarreraExternaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    //Lista las materia que de oficio de otras univerisidades
    public void listaMateriaExterna() throws ExceptionConnection {
        try {

            MateriaExternaDao materiaExternaDao = new MateriaExternaDao();
            arrayMateriaExterna = materiaExternaDao.ListarMateriaExterna(0);

        } catch (ExceptionConnection ex) {
            Logger.getLogger(CarreraExternaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Lista de los cursos o materias pertenecientes a la UNA
    public void listaScbrse() {
        arrayScbcrse = new ArrayList<>();
        try {
            ScbcrseDao scbcrseDao = new ScbcrseDao();
            arrayScbcrse = scbcrseDao.ListarScbcrse();
        } catch (ExceptionConnection ex) {
            Logger.getLogger(EquiparacionOficioMantenimientoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Carga la lista de materias de otras universidades asignadas a esta equiparacion
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

   //Carga la lista de materias de la UNA asignadas a esta equiparacion
    public void cargarScbcrseSelected() {
        Iterator iterador = arrayScbcrseSelected.listIterator();

        while (iterador.hasNext()) {
            ScbcrseBean materia = (ScbcrseBean) iterador.next(); //Obtengo el elemento contenido                    

            Iterator iteradorMaterias = arrayScbcrseEquivalentes.listIterator();
            boolean verificado = true;
            while (iteradorMaterias.hasNext()) {
                ScbcrseBean scbcrse = (ScbcrseBean) iteradorMaterias.next();
                if (scbcrse.getCodigo().equals(materia.getCodigo())) {
                    verificado = false;
                }
            }
            if (verificado) {
                arrayScbcrseEquivalentes.add(materia);
            }
        }
        arrayScbcrseSelected = new ArrayList<>();
    }
    
  //Guarda la equiparacion por oficio
    public void guardarEquiparacion() {
        try {
            EquiparacionOficioDao equiparacionOficioDao = new EquiparacionOficioDao();
            equiparacionOficioDao.dmlEquiparacionOficio(1, equiparacionOficioBean);
            addMessage("Guardado Exitosamente", 1);
        } catch (ExceptionConnection ex) {
            Logger.getLogger(ApelacionController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("Se produjo un error al insertar el registro, contacte al administrador del sistema", 1);
        }
    }

    public void quitarMateriaExterna(MateriaExternaBean item) {
        arrayMateriasEquivalentes.remove(item);
    }

    public void quitarScbcrse(ScbcrseBean item) {
        arrayScbcrseEquivalentes.remove(item);
    }

    public EquiparacionOficioBean getEquiparacionOficioBean() {
        return equiparacionOficioBean;
    }

    public void setEquiparacionOficioBean(EquiparacionOficioBean equiparacionOficioBean) {
        this.equiparacionOficioBean = equiparacionOficioBean;
    }

    public ArrayList<UniversidadBean> getArrayUniversidades() {
        return arrayUniversidades;
    }

    public void setArrayUniversidades(ArrayList<UniversidadBean> arrayUniversidades) {
        this.arrayUniversidades = arrayUniversidades;
    }

    public ArrayList<MateriaExtEquipOficioBean> getArrayMatExtEqpOfi() {
        return arrayMatExtEqpOfi;
    }

    public void setArrayMatExtEqpOfi(ArrayList<MateriaExtEquipOficioBean> arrayMatExtEqpOfi) {
        this.arrayMatExtEqpOfi = arrayMatExtEqpOfi;
    }

    public ArrayList<MateriaIntEquipOficioBean> getArrayMatIntEqpOfi() {
        return arrayMatIntEqpOfi;
    }

    public void setArrayMatIntEqpOfi(ArrayList<MateriaIntEquipOficioBean> arrayMatIntEqpOfi) {
        this.arrayMatIntEqpOfi = arrayMatIntEqpOfi;
    }

    public ArrayList<MateriaExternaBean> getArrayMateriasExternas() {
        return arrayMateriaExterna;
    }

    public void setArrayMateriasExternas(ArrayList<MateriaExternaBean> arrayMateriasExternas) {
        this.arrayMateriaExterna = arrayMateriasExternas;
    }

    public ArrayList<ScbcrseBean> getArrayScbcrse() {
        return arrayScbcrse;
    }

    public void setArrayScbcrse(ArrayList<ScbcrseBean> arrayScbcrse) {
        this.arrayScbcrse = arrayScbcrse;
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

    public List<ScbcrseBean> getArrayScbcrseSelected() {
        return arrayScbcrseSelected;
    }

    public void setArrayScbcrseSelected(List<ScbcrseBean> arrayScbcrseSelected) {
        this.arrayScbcrseSelected = arrayScbcrseSelected;
    }

    public ArrayList<MateriaExternaBean> getArrayMateriasEquivalentes() {
        return arrayMateriasEquivalentes;
    }

    public void setArrayMateriasEquivalentes(ArrayList<MateriaExternaBean> arrayMateriasEquivalentes) {
        this.arrayMateriasEquivalentes = arrayMateriasEquivalentes;
    }

    public ArrayList<ScbcrseBean> getArrayScbcrseEquivalentes() {
        return arrayScbcrseEquivalentes;
    }

    public void setArrayScbcrseEquivalentes(ArrayList<ScbcrseBean> arrayScbcrseEquivalentes) {
        this.arrayScbcrseEquivalentes = arrayScbcrseEquivalentes;
    }
    
    
//Contiene la lista de los mensajes que se le muestran en pantalla
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
}

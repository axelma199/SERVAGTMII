/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.beans;

/**
 *
 * @author ozkr
 */
public class MateriaExtEquipOficioBean {

    EquiparacionOficioBean equiparacionOficioBean;
    MateriaExternaBean materiaExternaBean;
    
    public MateriaExtEquipOficioBean() {
        equiparacionOficioBean =  new EquiparacionOficioBean();
        materiaExternaBean = new MateriaExternaBean();
    }

    public EquiparacionOficioBean getEquiparacionOficioBean() {
        return equiparacionOficioBean;
    }

    public void setEquiparacionOficioBean(EquiparacionOficioBean equiparacionOficioBean) {
        this.equiparacionOficioBean = equiparacionOficioBean;
    }

  

    public MateriaExternaBean getMateriaExternaBean() {
        return materiaExternaBean;
    }

    public void setMateriaExternaBean(MateriaExternaBean materiaExternaBean) {
        this.materiaExternaBean = materiaExternaBean;
    }
    
    

}

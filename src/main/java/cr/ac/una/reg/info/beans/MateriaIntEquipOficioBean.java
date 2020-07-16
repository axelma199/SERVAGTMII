/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.beans;

/**
 *
 * @author ozkr
 */
public class MateriaIntEquipOficioBean {

    EquiparacionOficioBean equiparacionOficioBean;
    ScbcrseBean scbcrseBean;

    public MateriaIntEquipOficioBean() {
        equiparacionOficioBean = new EquiparacionOficioBean();
        scbcrseBean = new ScbcrseBean();
    }

    public EquiparacionOficioBean getEquiparacionOficioBean() {
        return equiparacionOficioBean;
    }

    public void setEquiparacionOficioBean(EquiparacionOficioBean equiparacionOficioBean) {
        this.equiparacionOficioBean = equiparacionOficioBean;
    }

    public ScbcrseBean getScbcrseBean() {
        return scbcrseBean;
    }

    public void setScbcrseBean(ScbcrseBean scbcrseBean) {
        this.scbcrseBean = scbcrseBean;
    }

}

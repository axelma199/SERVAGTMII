
package cr.ac.una.reg.info.beans;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;


public class EquiparacionOficioBean {

    int eqp_ofi_id;
     SolicitanteBean solicitanteBean;
    String eqp_ofi_nombre;
    String eqp_ofi_descripcion;
    String eqp_ofi_annoActual;
    UniversidadBean universidad;
    List<MateriaExtEquipOficioBean> listaMaterias;
    
    String eqp_ofi_periodo;
    int eqp_ofi_estado;
    String eqp_ofi_descEstado;

    public EquiparacionOficioBean() {
        solicitanteBean = new SolicitanteBean();
        listaMaterias = new  ArrayList<>();
          
    }

    public int getEqp_ofi_id() {
        return eqp_ofi_id;
    }

    public void setEqp_ofi_id(int Eqp_ofi_id) {
        this.eqp_ofi_id = Eqp_ofi_id;
    }

    public String getEqp_ofi_nombre() {
        return eqp_ofi_nombre;
    }

    public void setEqp_ofi_nombre(String Eqp_ofi_nombre) {
        this.eqp_ofi_nombre = Eqp_ofi_nombre;
    }

    public String getEqp_ofi_descripcion() {
        return eqp_ofi_descripcion;
    }

    public void setEqp_ofi_descripcion(String Eqp_ofi_descripcion) {
        this.eqp_ofi_descripcion = Eqp_ofi_descripcion;
    }

    public UniversidadBean getUniversidad() {
        return universidad;
    }

    public void setUniversidad(UniversidadBean universidad) {
        this.universidad = universidad;
    }

    public SolicitanteBean getSolicitanteBean() {
        return solicitanteBean;
    }

    public void setSolicitanteBean(SolicitanteBean solicitanteBean) {
        this.solicitanteBean = solicitanteBean;
    }

    public String getEqp_ofi_annoActual() {
        return eqp_ofi_annoActual;
    }

    public void setEqp_ofi_annoActual(String eqp_ofi_annoActual) {
        this.eqp_ofi_annoActual = eqp_ofi_annoActual;
    }

    public String getEqp_ofi_periodo() {
        return eqp_ofi_periodo;
    }

    public void setEqp_ofi_periodo(String eqp_ofi_periodo) {
        this.eqp_ofi_periodo = eqp_ofi_periodo;
    }

    public int getEqp_ofi_estado() {
        return eqp_ofi_estado;
    }

    public void setEqp_ofi_estado(int eqp_ofi_estado) {
        this.eqp_ofi_estado = eqp_ofi_estado;
    }

    public String getEqp_ofi_descEstado() {
        return eqp_ofi_descEstado;
    }

    public void setEqp_ofi_descEstado(String eqp_ofi_descEstado) {
        this.eqp_ofi_descEstado = eqp_ofi_descEstado;
    }

    

}

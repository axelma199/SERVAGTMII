/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.beans;


import java.util.Date;

public class EquiparacionesBean {

    String eqp_sol_numero;
    SolicitanteBean solicitanteBean;
    String eqp_carrera_continuar_est;
    String eqp_grado_continuar;
    Date eqp_fecha_atencion_usuario;
    Date eqp_fecha_area_rec;
    Date eqp_fecha_envio_unidad;
    Date eqp_fecha_recepcion_final;
    Float eqp_total_pagar;
    String eqp_estado;
    String eqp_numero_sesion;
    Date eqp_fecha_sesion;
    String eqp_observaciones;
    String eqp_estado_desc;
    String annoActual;
    String eqp_periodo;
    int eqpCarreraProcedencia;
    String eqpSedeIngresar;
    Date eqpFechaRecepcionOrer;
    String DescripcionCarreraProcedencia;
    String uniProcedencia;

    public EquiparacionesBean() {
        solicitanteBean = new SolicitanteBean();
        String eqp_sol_numero = "";
        eqp_carrera_continuar_est = "";
        eqp_grado_continuar = "";
        eqp_fecha_atencion_usuario = null;

    }

    public SolicitanteBean getSolicitanteBean() {
        return solicitanteBean;
    }

    public void setSolicitanteBean(SolicitanteBean solicitanteBean) {
        this.solicitanteBean = solicitanteBean;
    }

    public String getEqp_sol_numero() {
        return eqp_sol_numero;
    }

    public void setEqp_sol_numero(String Eqp_sol_numero) {
        this.eqp_sol_numero = Eqp_sol_numero;
    }

   
    public String getEqp_carrera_continuar_est() {
        return eqp_carrera_continuar_est;
    }

    public void setEqp_carrera_continuar_est(String Eqp_carrera_continuar_est) {
        this.eqp_carrera_continuar_est = Eqp_carrera_continuar_est;
    }

    public String getEqp_grado_continuar() {
        return eqp_grado_continuar;
    }

    public void setEqp_grado_continuar(String Eqp_grado_continuar) {
        this.eqp_grado_continuar = Eqp_grado_continuar;
    }

    public Date getEqp_fecha_atencion_usuario() {
        return eqp_fecha_atencion_usuario;
    }

    public void setEqp_fecha_atencion_usuario(Date Eqp_fecha_atencion_usuario) {
        this.eqp_fecha_atencion_usuario = Eqp_fecha_atencion_usuario;
    }

    public Date getEqp_fecha_area_rec() {
        return eqp_fecha_area_rec;
    }

    public void setEqp_fecha_area_rec(Date Eqp_fecha_area_rec) {
        this.eqp_fecha_area_rec = Eqp_fecha_area_rec;
    }

    public Date getEqp_fecha_envio_unidad() {
        return eqp_fecha_envio_unidad;
    }

    public void setEqp_fecha_envio_unidad(Date Eqp_fecha_envio_unidad) {
        this.eqp_fecha_envio_unidad = Eqp_fecha_envio_unidad;
    }

    public Date getEqp_fecha_recepcion_final() {
        return eqp_fecha_recepcion_final;
    }

    public void setEqp_fecha_recepcion_final(Date Eqp_fecha_recepcion_final) {
        this.eqp_fecha_recepcion_final = Eqp_fecha_recepcion_final;
    }

    public Float getEqp_total_pagar() {
        return eqp_total_pagar;
    }

    public void setEqp_total_pagar(Float Eqp_total_pagar) {
        this.eqp_total_pagar = Eqp_total_pagar;
    }

    public String getEqp_estado() {
        return eqp_estado;
    }

    public void setEqp_estado(String Eqp_estado) {
        this.eqp_estado = Eqp_estado;
    }

    public String getEqp_numero_sesion() {
        return eqp_numero_sesion;
    }

    public void setEqp_numero_sesion(String Eqp_numero_sesion) {
        this.eqp_numero_sesion = Eqp_numero_sesion;
    }

    public Date getEqp_fecha_sesion() {
        return eqp_fecha_sesion;
    }

    public void setEqp_fecha_sesion(Date Eqp_fecha_sesion) {
        this.eqp_fecha_sesion = Eqp_fecha_sesion;
    }

    public String getEqp_observaciones() {
        return eqp_observaciones;
    }

    public void setEqp_observaciones(String Eqp_observaciones) {
        this.eqp_observaciones = Eqp_observaciones;
    }

    public String getEqp_estado_desc() {
        if (eqp_estado.equals("0")) {
            eqp_estado_desc = "Incompleto";
        } else if (eqp_estado.equals("1")) {
            eqp_estado_desc = "En tramite";
        } else if (eqp_estado.equals("2")) {
            eqp_estado_desc = "Resuelto";
        } else {
            eqp_estado_desc = "Anulado";
        }

        return eqp_estado_desc;
    }

    public void setEqp_estado_desc(String eqp_estado_desc) {
        this.eqp_estado_desc = eqp_estado_desc;
    }

    public String getAnnoActual() {
        return annoActual;
    }

    public void setAnnoActual(String annoActual) {
        this.annoActual = annoActual;
    }

    public String getEqp_periodo() {
        return eqp_periodo;
    }

    public void setEqp_periodo(String eqp_periodo) {
        this.eqp_periodo = eqp_periodo;
    }

    public int getEqpCarreraProcedencia() {
        return eqpCarreraProcedencia;
    }

    public void setEqpCarreraProcedencia(int eqpCarreraProcedencia) {
        this.eqpCarreraProcedencia = eqpCarreraProcedencia;
    }

    public String getEqpSedeIngresar() {
        return eqpSedeIngresar;
    }

    public void setEqpSedeIngresar(String eqpSedeIngresar) {
        this.eqpSedeIngresar = eqpSedeIngresar;
    }
    
    
    
    
//    @Override
//    public int hashCode() {
//        int hash = 0;
//	hash += (eqp_carrera_continuar_est!= null ? eqp_carrera_continuar_est.hashCode() : 0);
//	return hash;
//	}
//
//@Override
//    public boolean equals(Object object) {
//        if (!(object instanceof EquiparacionesBean)) {
//            return false;
//        }
//        EquiparacionesBean other = (EquiparacionesBean) object;
//        if ((this.eqp_carrera_continuar_est == null && other.eqp_carrera_continuar_est != null) || (this.eqp_carrera_continuar_est != null && !this.eqp_carrera_continuar_est.equals(other.eqp_carrera_continuar_est))) {
//            return false;
//        }
//        return true;
//    }
//    

    public Date getEqpFechaRecepcionOrer() {
        return eqpFechaRecepcionOrer;
    }

    public void setEqpFechaRecepcionOrer(Date eqpFechaRecepcionOrer) {
        this.eqpFechaRecepcionOrer = eqpFechaRecepcionOrer;
    }

    public String getDescripcionCarreraProcedencia() {
        return DescripcionCarreraProcedencia;
    }

    public void setDescripcionCarreraProcedencia(String DescripcionCarreraProcedencia) {
        this.DescripcionCarreraProcedencia = DescripcionCarreraProcedencia;
    }

    public String getUniProcedencia() {
        return uniProcedencia;
    }

    public void setUniProcedencia(String uniProcedencia) {
        this.uniProcedencia = uniProcedencia;
    }
    
    
    
}
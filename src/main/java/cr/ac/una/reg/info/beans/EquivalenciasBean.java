package cr.ac.una.reg.info.beans;

//import java.sql.Date;
import java.util.Date;

public class EquivalenciasBean {

    private String eqv_sol_numero;
    private SolicitanteBean solte_id;
    private String eqv_facultad_continuar;
    private String eqv_escuela_continuar;
    private String eqv_carrera_continuar_est;
    private String eqv_grado_continuar;
    private Date eqv_fecha_area_rec;
    private Date eqv_fecha_envio_unidad;
    private Date eqv_fecha_recepcion_final;
    private String eqv_estado;
    private String eqv_numero_sesion;
    private Date eqv_fecha_sesion;
    private String eqv_observaciones;
    private String eqv_estado_desc;

    public EquivalenciasBean() {
        eqv_sol_numero = null;
        solte_id = new SolicitanteBean();
        eqv_facultad_continuar = null;
        eqv_escuela_continuar = null;
        eqv_carrera_continuar_est = null;
        eqv_grado_continuar = null;
        eqv_fecha_area_rec = null;
        eqv_fecha_envio_unidad = null;
        eqv_fecha_recepcion_final = null;
        eqv_estado = null;
        eqv_numero_sesion = null;
        eqv_fecha_sesion = null;
        eqv_observaciones = null;
    }

    public EquivalenciasBean(String p_eqv_sol_numero, SolicitanteBean p_solte_id, String p_eqv_sol_tipo, String p_eqv_facultad_est_realizados, String p_eqv_escuela_est_realizados, String p_eqv_carrera_est_realizados, String p_eqv_facultad_continuar, String p_eqv_escuela_continuar, String p_eqv_carrera_continuar_est, String p_eqv_grado_continuar, Date p_eqv_fecha_atencion_usuario, Date p_eqv_fecha_area_rec, Date p_eqv_fecha_envio_unidad, Date p_eqv_fecha_recepcion_final, String p_eqv_estado, String p_eqv_numero_sesion, Date p_eqv_fecha_sesion, String p_eqv_observaciones) {
        eqv_sol_numero = p_eqv_sol_numero;
        solte_id = p_solte_id;
        eqv_facultad_continuar = p_eqv_facultad_continuar;
        eqv_escuela_continuar = p_eqv_escuela_continuar;
        eqv_carrera_continuar_est = p_eqv_carrera_continuar_est;
        eqv_grado_continuar = p_eqv_grado_continuar;
        eqv_fecha_area_rec = p_eqv_fecha_area_rec;
        eqv_fecha_envio_unidad = p_eqv_fecha_envio_unidad;
        eqv_fecha_recepcion_final = p_eqv_fecha_recepcion_final;
        eqv_estado = p_eqv_estado;
        eqv_numero_sesion = p_eqv_numero_sesion;
        eqv_fecha_sesion = p_eqv_fecha_sesion;
        eqv_observaciones = p_eqv_observaciones;
    }

    public String getEqv_sol_numero() {
        return eqv_sol_numero;
    }

    public void setEqv_sol_numero(String Eqv_sol_numero) {
        this.eqv_sol_numero = Eqv_sol_numero;
    }

    public SolicitanteBean getSolte_id() {
        return solte_id;
    }

    public void setSolte_id(SolicitanteBean Solte_id) {
        this.solte_id = Solte_id;
    }

    public String getEqv_facultad_continuar() {
        return eqv_facultad_continuar;
    }

    public void setEqv_facultad_continuar(String Eqv_facultad_continuar) {
        this.eqv_facultad_continuar = Eqv_facultad_continuar;
    }

    public String getEqv_escuela_continuar() {
        return eqv_escuela_continuar;
    }

    public void setEqv_escuela_continuar(String Eqv_escuela_continuar) {
        this.eqv_escuela_continuar = Eqv_escuela_continuar;
    }

    public String getEqv_carrera_continuar_est() {
        return eqv_carrera_continuar_est;
    }

    public void setEqv_carrera_continuar_est(String Eqv_carrera_continuar_est) {
        this.eqv_carrera_continuar_est = Eqv_carrera_continuar_est;
    }

    public String getEqv_grado_continuar() {
        return eqv_grado_continuar;
    }

    public void setEqv_grado_continuar(String Eqv_grado_continuar) {
        this.eqv_grado_continuar = Eqv_grado_continuar;
    }

    public Date getEqv_fecha_area_rec() {
        return eqv_fecha_area_rec;
    }

    public void setEqv_fecha_area_rec(Date Eqv_fecha_area_rec) {
        this.eqv_fecha_area_rec = Eqv_fecha_area_rec;
    }

    public Date getEqv_fecha_envio_unidad() {
        return eqv_fecha_envio_unidad;
    }

    public void setEqv_fecha_envio_unidad(Date Eqv_fecha_envio_unidad) {
        this.eqv_fecha_envio_unidad = Eqv_fecha_envio_unidad;
    }

    public Date getEqv_fecha_recepcion_final() {
        return eqv_fecha_recepcion_final;
    }

    public void setEqv_fecha_recepcion_final(Date Eqv_fecha_recepcion_final) {
        this.eqv_fecha_recepcion_final = Eqv_fecha_recepcion_final;
    }

    public String getEqv_estado() {
        return eqv_estado;
    }

    public void setEqv_estado(String Eqv_estado) {
        this.eqv_estado = Eqv_estado;
    }

    public String getEqv_numero_sesion() {
        return eqv_numero_sesion;
    }

    public void setEqv_numero_sesion(String Eqv_numero_sesion) {
        this.eqv_numero_sesion = Eqv_numero_sesion;
    }

    public Date getEqv_fecha_sesion() {
        return eqv_fecha_sesion;
    }

    public void setEqv_fecha_sesion(Date Eqv_fecha_sesion) {
        this.eqv_fecha_sesion = Eqv_fecha_sesion;
    }

    public String getEqv_observaciones() {
        return eqv_observaciones;
    }

    public void setEqv_observaciones(String Eqv_observaciones) {
        this.eqv_observaciones = Eqv_observaciones;
    }
    
    public String getEqv_estado_desc() {
        if(eqv_estado.equals("0")) {
            eqv_estado_desc = "Incompleto";
        } else if (eqv_estado.equals("1")) {
            eqv_estado_desc = "En tramite";
        } else if (eqv_estado.equals("2")) {
            eqv_estado_desc = "Resuelto";
        } else {
            eqv_estado_desc = "Anulado";
        }

        return eqv_estado_desc;
    }


    /**
     * @param eqv_estado_desc the eqv_estado_desc to set
     */
    public void setEqv_estado_desc(String eqv_estado_desc) {
        this.eqv_estado_desc = eqv_estado_desc;
    }
}

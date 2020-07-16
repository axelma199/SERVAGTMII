/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.beans;

import java.util.Date;

/**
 *
 * @author JGA
 */
public class ApelacionesBean {

    private String apel_sol_numero;
    private String apel_codApelar;
    private String apel_tipo;
    private String apel_observaciones;
    private String apel_num_sesion;
    private Date apel_fecha_sesion;
    private String apel_estado_apel;
    private String apel_estado_desc;
    private String apelId;
    private String apelNombre;

    public ApelacionesBean() {
        apel_sol_numero = null;
        apel_codApelar = null;
        apel_tipo = null;
        apel_observaciones = null;
        apel_num_sesion = null;
        apel_fecha_sesion = null;
        apel_estado_apel = null;
        apel_estado_desc = null;
    }

    public ApelacionesBean(String p_apel_sol_numero, String p_apel_apelar, String p_apel_tipo, String p_apel_observaciones, String p_apel_num_sesion, Date p_apel_fecha_sesion, String p_apel_estado_apel, String p_apel_estado_desc) {
        apel_sol_numero = p_apel_sol_numero;
        apel_codApelar = p_apel_apelar;
        apel_tipo = p_apel_tipo;
        apel_observaciones = p_apel_observaciones;
        apel_num_sesion = p_apel_num_sesion;
        apel_fecha_sesion = p_apel_fecha_sesion;
        apel_estado_apel = p_apel_estado_apel;
        apel_estado_desc = p_apel_estado_desc;
    }

    public String getApel_sol_numero() {
        return apel_sol_numero;
    }

    public void setApel_sol_numero(String Apel_sol_numero) {
        this.apel_sol_numero = Apel_sol_numero;
    }

    public String getApel_codApelar() {
        return apel_codApelar;
    }

    public void setApel_codApelar(String Apel_sol_Apelar) {
        this.apel_codApelar = Apel_sol_Apelar;
    }

    public String getApel_tipo() {
        return apel_tipo;
    }

    public void setApel_tipo(String Apel_tipo) {
        this.apel_tipo = Apel_tipo;
    }

    public String getApel_Observaciones() {
        return apel_observaciones;
    }

    public void setApel_Observaciones(String Apel_observaciones) {
        this.apel_observaciones = Apel_observaciones;
    }

    /**
     * @return the apel_num_sesion
     */
    public String getApel_num_sesion() {
        return apel_num_sesion;
    }

    /**
     * @param apel_num_sesion the apel_num_sesion to set
     */
    public void setApel_num_sesion(String apel_num_sesion) {
        this.apel_num_sesion = apel_num_sesion;
    }

    /**
     * @return the apel_fecha_sesion
     */
    public Date getApel_fecha_sesion() {
        return apel_fecha_sesion;
    }

    /**
     * @param apel_fecha_sesion the apel_fecha_sesion to set
     */
    public void setApel_fecha_sesion(Date apel_fecha_sesion) {
        this.apel_fecha_sesion = apel_fecha_sesion;
    }

    /**
     * @return the apel_estado_apel
     */
    public String getApel_estado_apel() {
        return apel_estado_apel;
    }

    /**
     * @param apel_estado_apel the apel_estado_apel to set
     */
    public void setApel_estado_apel(String apel_estado_apel) {
        this.apel_estado_apel = apel_estado_apel;
    }

    /**
     * @return the apel_estado_desc
     */
    public String getApel_estado_desc() {
        if(apel_estado_apel.equals("0")) {
            apel_estado_desc = "En tramite";
        }else{
            apel_estado_desc = "Resuelto";
        } 
        return apel_estado_desc;
    }

    /**
     * @param apel_estado_desc the apel_estado_desc to set
     */
    public void setApel_estado_desc(String apel_estado_desc) {
        this.apel_estado_desc = apel_estado_desc;
    }

    public String getApelId() {
        return apelId;
    }

    public void setApelId(String apelId) {
        this.apelId = apelId;
    }

    public String getApelNombre() {
        return apelNombre;
    }

    public void setApelNombre(String apelNombre) {
        this.apelNombre = apelNombre;
    }
    
    
    

}

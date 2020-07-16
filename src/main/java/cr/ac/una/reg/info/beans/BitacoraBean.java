/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.beans;

import java.sql.Date;

/**
 *
 * @author JGA
 */
public class BitacoraBean {

    String bita_id_usuario;
    String bita_usuario;
    String bita_transaccion;
    String bita_tabla_afectada;
    String bita_numero_solicitud;
    String bita_solicitud_tipo;
    String bita_solicitante_id;
    String bita_nomb_solicitante;
    Date bita_fecha;

    public BitacoraBean() {
    }

    public BitacoraBean(String p_bita_id_usuario, String p_bita_usuario, String p_bita_transaccion, String p_bita_tabla_afectada, String p_bita_numero_solicitud, String p_bita_solicitud_tipo, String p_bita_solicitante_id, String p_bita_nomb_solicitante, Date p_bita_fecha) {
        bita_id_usuario = p_bita_id_usuario;
        bita_usuario = p_bita_usuario;
        bita_transaccion = p_bita_transaccion;
        bita_tabla_afectada = p_bita_tabla_afectada;
        bita_numero_solicitud = p_bita_numero_solicitud;
        bita_solicitud_tipo = p_bita_solicitud_tipo;
        bita_solicitante_id = p_bita_solicitante_id;
        bita_nomb_solicitante = p_bita_nomb_solicitante;
        bita_fecha = p_bita_fecha;
    }

    public String getBita_id_usuario() {
        return bita_id_usuario;
    }

    public void setBita_id_usuario(String Bita_id_usuario) {
        this.bita_id_usuario = Bita_id_usuario;
    }

    public String getBita_usuario() {
        return bita_usuario;
    }

    public void setBita_usuario(String Bita_usuario) {
        this.bita_usuario = Bita_usuario;
    }

    public String getBita_transaccion() {
        return bita_transaccion;
    }

    public void setBita_transaccion(String Bita_transaccion) {
        this.bita_transaccion = Bita_transaccion;
    }

    public String getBita_tabla_afectada() {
        return bita_tabla_afectada;
    }

    public void setBita_tabla_afectada(String Bita_tabla_afectada) {
        this.bita_tabla_afectada = Bita_tabla_afectada;
    }

    public String getBita_numero_solicitud() {
        return bita_numero_solicitud;
    }

    public void setBita_numero_solicitud(String Bita_numero_solicitud) {
        this.bita_numero_solicitud = Bita_numero_solicitud;
    }

    public String getBita_solicitud_tipo() {
        return bita_solicitud_tipo;
    }

    public void setBita_solicitud_tipo(String Bita_solicitud_tipo) {
        this.bita_solicitud_tipo = Bita_solicitud_tipo;
    }

    public String getBita_solicitante_id() {
        return bita_solicitante_id;
    }

    public void setBita_solicitante_id(String Bita_solicitante_id) {
        this.bita_solicitante_id = Bita_solicitante_id;
    }

    public String getBita_nomb_solicitante() {
        return bita_nomb_solicitante;
    }

    public void setBita_nomb_solicitante(String Bita_nomb_solicitante) {
        this.bita_nomb_solicitante = Bita_nomb_solicitante;
    }

    public Date getBita_fecha() {
        return bita_fecha;
    }

    public void setBita_fecha(Date Bita_fecha) {
        this.bita_fecha = Bita_fecha;
    }

    public synchronized static String spBitacora() {
        String sql = "CALL SP_SERVAGTM_Bitacora(?,?,?,?,?,?,?,?,?,?)";
        return (sql);
    }
}

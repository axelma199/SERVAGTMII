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
public class BitacoraMateriasBean {

    String bitam_id_usuario;
    String bitam_usuario;
    String bitam_transaccion;
    String bitam_tabla_afectada;
    String bitam_numero_solicitud;
    String bitam_solicitud_tipo;
    String bitam_solicitante_id;
    String bitam_nomb_solicitante;
    String bitam_mat_modificada;
    String bitam_mat_estado;
    Date bitam_fecha;

    public BitacoraMateriasBean() {
    }

    public BitacoraMateriasBean(String p_bitam_id_usuario, String p_bitam_usuario, String p_bitam_transaccion, String p_bitam_tabla_afectada, String p_bitam_numero_solicitud, String p_bitam_solicitud_tipo, String p_bitam_solicitante_id, String p_bitam_nomb_solicitante, String p_bitam_mat_modificada, String p_bitam_mat_estado, Date p_bitam_fecha) {
        bitam_id_usuario = p_bitam_id_usuario;
        bitam_usuario = p_bitam_usuario;
        bitam_transaccion = p_bitam_transaccion;
        bitam_tabla_afectada = p_bitam_tabla_afectada;
        bitam_numero_solicitud = p_bitam_numero_solicitud;
        bitam_solicitud_tipo = p_bitam_solicitud_tipo;
        bitam_solicitante_id = p_bitam_solicitante_id;
        bitam_nomb_solicitante = p_bitam_nomb_solicitante;
        bitam_mat_modificada = p_bitam_mat_modificada;
        bitam_mat_estado = p_bitam_mat_estado;
        bitam_fecha = p_bitam_fecha;
    }

    public String getBitam_id_usuario() {
        return bitam_id_usuario;
    }

    public void setBitam_id_usuario(String Bitam_id_usuario) {
        this.bitam_id_usuario = Bitam_id_usuario;
    }

    public String getBitam_usuario() {
        return bitam_usuario;
    }

    public void setBitam_usuario(String Bitam_usuario) {
        this.bitam_usuario = Bitam_usuario;
    }

    public String getBitam_transaccion() {
        return bitam_transaccion;
    }

    public void setBitam_transaccion(String Bitam_transaccion) {
        this.bitam_transaccion = Bitam_transaccion;
    }

    public String getBitam_tabla_afectada() {
        return bitam_tabla_afectada;
    }

    public void setBitam_tabla_afectada(String Bitam_tabla_afectada) {
        this.bitam_tabla_afectada = Bitam_tabla_afectada;
    }

    public String getBitam_numero_solicitud() {
        return bitam_numero_solicitud;
    }

    public void setBitam_numero_solicitud(String Bitam_numero_solicitud) {
        this.bitam_numero_solicitud = Bitam_numero_solicitud;
    }

    public String getBitam_solicitud_tipo() {
        return bitam_solicitud_tipo;
    }

    public void setBitam_solicitud_tipo(String Bitam_solicitud_tipo) {
        this.bitam_solicitud_tipo = Bitam_solicitud_tipo;
    }

    public String getBitam_solicitante_id() {
        return bitam_solicitante_id;
    }

    public void setBitam_solicitante_id(String Bitam_solicitante_id) {
        this.bitam_solicitante_id = Bitam_solicitante_id;
    }

    public String getBitam_nomb_solicitante() {
        return bitam_nomb_solicitante;
    }

    public void setBitam_nomb_solicitante(String Bitam_nomb_solicitante) {
        this.bitam_nomb_solicitante = Bitam_nomb_solicitante;
    }

    public String getBitam_mat_modificada() {
        return bitam_mat_modificada;
    }

    public void setBitam_mat_modificada(String Bitam_mat_modificada) {
        this.bitam_mat_modificada = Bitam_mat_modificada;
    }

    public String getBitam_mat_estado() {
        return bitam_mat_estado;
    }

    public void setBitam_mat_estado(String Bitam_mat_estado) {
        this.bitam_mat_estado = Bitam_mat_estado;
    }

    public Date getBitam_fecha() {
        return bitam_fecha;
    }

    public void setBitam_fecha(Date Bitam_fecha) {
        this.bitam_fecha = Bitam_fecha;
    }

    public synchronized static String spBitacora_materias() {
        String sql = "CALL SP_SERVAGTM_BITACORA_MATERIAS(?,?,?,?,?,?,?,?,?,?,?,?)";
        return (sql);
    }
}

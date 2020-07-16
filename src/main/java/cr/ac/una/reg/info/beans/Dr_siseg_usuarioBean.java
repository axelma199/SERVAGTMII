/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JGA
 */
public class Dr_siseg_usuarioBean {

    private String codigo;
    private String descripcion;
    private String clave;
    private String estado;
    private String tipo;
    private Dr_siseg_rolBean tipoRol;
    private String sede;
    private String escuela;
    private MenuBean menuBean;
    private String roll;

    public Dr_siseg_usuarioBean() {
        codigo = null;
        descripcion = null;
        clave = null;
        estado = null;
        tipo = null;
        tipoRol = new Dr_siseg_rolBean();
        sede = null;
        escuela = null;
        menuBean=null;
        roll="";
    }

    public Dr_siseg_usuarioBean(String p_codigo, String p_descripcion, String p_clave, String p_estado, String p_tipo, Dr_siseg_rolBean p_tipoRol, String p_sede, String p_escuela) {
        codigo = p_codigo;
        descripcion = p_descripcion;
        clave = p_clave;
        estado = p_estado;
        tipo = p_tipo;
        tipoRol = p_tipoRol;
        sede = p_sede;
        escuela = p_escuela;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String Codigo) {
        this.codigo = Codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.descripcion = Descripcion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String Clave) {
        this.clave = Clave;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String Estado) {
        this.estado = Estado;
    }

    public Dr_siseg_rolBean getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(Dr_siseg_rolBean Tipo) {
        this.tipoRol = Tipo;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String Sede) {
        this.sede = Sede;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String Escuela) {
        this.escuela = Escuela;
    }

    public synchronized static String spDr_siseg_usuario() {
        String sql = "CALL sp_Dr_siseg_usuario(?,?,?,?,?,?,?)";
        return (sql);
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getTipo(){
        return tipo;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }
    
    
}

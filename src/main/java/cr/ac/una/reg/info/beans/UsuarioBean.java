/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cr.ac.una.reg.info.beans;

/**
 *
 * @author i111910530
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class UsuarioBean {
   private String usuario;
   private String clave;
   private String descripcion;
   private String estado;
   private String tipo;
   private String sede;
   private String escuela;
   private String facultad;
   private String usuarioRoll;

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public UsuarioBean(){
    }//constructor

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }
    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuarioRoll() {
        return usuarioRoll;
    }

    public void setUsuarioRoll(String usuarioRoll) {
        this.usuarioRoll = usuarioRoll;
    }

}
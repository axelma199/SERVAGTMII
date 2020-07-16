/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.beans;

/**
 *
 * @author JGA
 */
public class Dr_siseg_rolBean {

    private String codigo;
    private String descripcion;

    public Dr_siseg_rolBean(String p_codigo, String p_descripcion) {
        codigo = p_codigo;
        descripcion = p_descripcion;
    }
    
    public Dr_siseg_rolBean() {
        codigo = null;
        descripcion = null;
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

    public synchronized static String spDr_siseg_rol() {
        String sql = "CALL sp_Dr_siseg_rol(?,?)";
        return (sql);
    }
}

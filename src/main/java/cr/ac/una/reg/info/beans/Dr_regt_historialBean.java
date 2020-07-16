/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.beans;

/**
 *
 * @author JGA
 */
public class Dr_regt_historialBean {
    private String codigo;
    private String nombre;
    private int creditos;
    
    public Dr_regt_historialBean(){
        codigo = "";
        nombre = "";
        creditos = 0;
    }
    
    public Dr_regt_historialBean(String pCodigo, String pNombre, int p_creditos){
        codigo = pCodigo;
        nombre = pNombre;
        creditos = p_creditos;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the creditos
     */
    public int getCreditos() {
        return creditos;
    }

    /**
     * @param creditos the creditos to set
     */
    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.sql;

/**
 *
 * @author Mauricio Garita Gonzalez
 * Clase de consulta de las faclultades existentes en la base de datos institucional
 */
public class StvdivsSQL {

//Retorna  la lista de las escuelas existentes
    public synchronized static String CMD_CONSULTA_FACULTAD() {
        String sql = "SELECT stvdivs_code,stvdivs_desc FROM stvdivs";
        return (sql);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.sql;

/**
 *
 * @author Mauricio Garita Gonzalez
 * Clase de consulta de escuelas existentes en la base de datos institucional
 */
public class StvcollSQL {

    //Retorna los datos de una escuela en especifico
    public synchronized static String CMD_CONSULTA_ESCUELA() {
        String sql = "SELECT stvcoll_code, stvcoll_desc FROM stvcoll  WHERE stvcoll_statscan_cde3 =?";
        return (sql);
    }
}

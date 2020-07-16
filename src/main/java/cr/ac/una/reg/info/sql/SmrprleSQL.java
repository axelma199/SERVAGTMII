/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.sql;

/**
 *
 * @author Mauricio Garita
 * >Clase de consulta de las carreras de la UNA
 */
public class SmrprleSQL {

   
    //Retorna las carreras de la UNA petenesientes a una escuela
    public synchronized static String CMD_CONSULTA_CARRERA() {
        String sql = "Select  REPLACE(smrprle_program,'Ñ','N')  smrprle_program,  smrprle_program_desc FROM smrprle WHERE smrprle_coll_code=?";
        return (sql);
    }
}


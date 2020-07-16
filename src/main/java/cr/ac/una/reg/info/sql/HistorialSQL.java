/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.sql;

/**
 *
 * @author mauricio garita
 * 
 * Clase que retorna la lista de los cursos de la UNA
 */
public class HistorialSQL {
    
    public synchronized static String CargaMaterias() {
        //String sql = "Select DISTINCT SCBCRSE_SUBJ_CODE,SCBCRSE_CRSE_NUMB,SCBCRSE_TITLE,SCBCRSE_CREDIT_HR_LOW FROM scbcrse WHERE SCBCRSE_COLL_CODE=?";
        String sql = "Select DISTINCT SCBCRSE_SUBJ_CODE,SCBCRSE_CRSE_NUMB,SCBCRSE_TITLE,SCBCRSE_CREDIT_HR_LOW FROM scbcrse WHERE SCBCRSE_COLL_CODE='CV'";
        return (sql);
    }//
}

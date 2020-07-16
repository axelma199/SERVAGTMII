/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.sql;

import cr.ac.una.reg.info.beans.*;

/**
 *
 * @author Mauricio Garita
 * 
 * Clase de consulta de todas las materia existentes en la UNA
 */
public class ScbcrseSQL {

    
    //Retorna los registros de una materia en especifico
    public synchronized static String CMD_CONSULTA_MATERIA_NOMBRE() {
                String sql = "Select DISTINCT SCBCRSE_COLL_CODE,SCBCRSE_SUBJ_CODE,SCBCRSE_CRSE_NUMB,SCBCRSE_TITLE,SCBCRSE_CREDIT_HR_LOW FROM scbcrse WHERE SCBCRSE_SUBJ_CODE=? AND SCBCRSE_TITLE=?";
        return (sql);
    }

    //Retorna los regsitros las materias perteneciente a una escuela
    public synchronized static String CMD_CONSULTA_MATERIA_ESCUELA() {
        String sql = "Select DISTINCT SCBCRSE_COLL_CODE,SCBCRSE_SUBJ_CODE,SCBCRSE_CRSE_NUMB,SCBCRSE_TITLE,SCBCRSE_CREDIT_HR_LOW FROM scbcrse WHERE SCBCRSE_COLL_CODE=?";
        return (sql);
    }

   //Retorna los registros de las materias pertenecientes a una faculta y escuela
    public synchronized static String CMD_CONSULTA_MATERIAS() {
        String sql = "SELECT DISTINCT SCBCRSE_SUBJ_CODE, SCBCRSE_CRSE_NUMB, SCBCRSE_COLL_CODE, SCBCRSE_DIVS_CODE, SCBCRSE_TITLE, SCBCRSE_CREDIT_HR_LOW FROM SCBCRSE where SCBCRSE_COLL_CODE = ? and SCBCRSE_DIVS_CODE = ?;";
        return (sql);
    }
    
    //Retorna la lista de todas la materias existentes en la UNA a un periodo maximo
    public synchronized static String CMD_LISTAR() {
        String sql = "Select DISTINCT SCBCRSE_COLL_CODE,SCBCRSE_SUBJ_CODE,SCBCRSE_CRSE_NUMB,"+
                     "TRANSLATE(SCBCRSE_TITLE," +
                     "'ÑÁÉÍÓÚÀÈÌÒÙÃÕÂÊÎÔÛÄËÏÖÜÇ'," +
                     "'NAEIOUAEIOUAOAEIOOAEIOUC') SCBCRSE_TITLE,SCBCRSE_CREDIT_HR_LOW "+
                      "FROM scbcrse B "+
                      "where SCBCRSE_EFF_TERM=(SELECT MAX(SCBCRSE_EFF_TERM)FROM SCBCRSE C "+
                                              "where C.SCBCRSE_SUBJ_CODE= B.SCBCRSE_SUBJ_CODE "+
                                               "and C.SCBCRSE_CRSE_NUMB=B.SCBCRSE_CRSE_NUMB)  ";
        return (sql);
    } 
    //REPLACE(SCBCRSE_TITLE,',',' ')
}

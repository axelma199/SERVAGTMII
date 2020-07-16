/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.sql;

/**
 *
 * @author Mauricio Garita Gonzalez
 * Clase de consulta los paises existentes en la base de datos institucional
 */

public class StvnatnSQL {

    //Retorna la lista de los paises 
    public synchronized static String CMD_CONSULTA_PAISES() {
        String sql = "SELECT STVNATN_CODE, STVNATN_NATION FROM STVNATN ORDER BY STVNATN_NATION";
        return (sql);
    }
    
    //Busca un pais en especifico
     public synchronized static String buscaPais() {
        String sql = "SELECT STVNATN_CODE, STVNATN_NATION FROM STVNATN  where STVNATN_CODE=? ORDER BY STVNATN_NATION";
        return (sql);
    }
}

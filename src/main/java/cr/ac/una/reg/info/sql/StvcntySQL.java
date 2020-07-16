/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.sql;

/**
 *
 * @author Mauricio Garita
 * Clase que consulta la tabla de cantones en la base de datos institucional
 * 
 */
public class StvcntySQL {
    
    
    //Retorna la lista de los cantones pertenecientes a una provincia 
     public synchronized static String cargaCantones(){
            String sql="select stvcnty_code, "+
                               "stvcnty_desc "+
                               "from stvcnty "+
                               "where substr(stvcnty_code,1,1)=? "+
                               "and stvcnty_code not in('100','200','300','400','500','600','700')";
            return sql;
    }

    
}

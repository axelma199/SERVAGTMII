package cr.ac.una.reg.info.sql;
/*
 * Clase de consulta de datos de las persona existente en la base de datos institucional
 * 
 */

public class SpridenSQL {

    public SpridenSQL() {
    }

  //Retorna los datos de una persona en especifico de la base de datos de personas institucional
    public synchronized static String buscaSpriden() {
        String sql = "Select spriden_id,spriden_last_name,spriden_first_name from SPRIDEN where spriden_id = ? and SPRIDEN_CHANGE_IND is null";        return (sql);
    }

}

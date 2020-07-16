/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.sql;

/**
 * Autor Mauricio garita
 * Contiene los SQL necesarios para buscar, insertar y modoficar apelaciones
 * 
 */
public class ApelacionesSQL {
    
    /*SQL de busqueda de Apleaciones en un estado especiifco*/
     public synchronized static String cargaApelaciones() {
        
         
         //String sql = "SELECT APEL_SOL_NUMERO, APEL_COD_APELACION, APEL_TIPO, APEL_OBSERVACIONES, APEL_NUM_SESION, APEL_FECHA_SESION, APEL_ESTADO_APEL FROM DR_REGT_APELACIONES WHERE APEL_ESTADO = 1";
        
        /*MODIFICADO SE AGREGO CEDULA Y NOMBRE*/
        String sql = "SELECT APEL_SOL_NUMERO, APEL_COD_APELACION, APEL_TIPO, APEL_OBSERVACIONES, APEL_NUM_SESION, APEL_FECHA_SESION, APEL_ESTADO_APEL, "+
                     "SOLTE_ID   ID_BANNER, "+
                     "(SELECT SPRIDEN_FIRST_NAME ||' '||SPRIDEN_LAST_NAME "+
                     "FROM SPRIDEN "+
                     "WHERE SPRIDEN_CHANGE_IND IS NULL "+
                     "AND SPRIDEN_ID=SOLTE_ID) NOMBRE "+
                     "FROM DR_REGT_APELACIONES,DR_REGT_EQUIPARACIONES "+
                     "WHERE APEL_ESTADO = 1 "+
                     "AND APEL_COD_APELACION=EQP_SOL_NUMERO";
        return (sql);
    }//
     
     /*SQL para la modificacion de una apelacion*/
     public synchronized static String SP_Apelaciones() {
        String sql = "CALL DR_PKG_REGT.SP_SERVAGTM_Apelaciones( ?, ?, ?, ?, ?, ?, ?, ?)";

        return (sql);
    }//
     
     /*Sql registra una apelacion*/
     public synchronized static String SP_INSERTA_Apelaciones() {
        String sql = "{call ? := DR_PKG_REGT.FB_INSERTA_APELACIONES( ?, ?, ?, ?)}";

        return (sql);
    }//

     /*SQL de busqueda de Apleaciones epor numero y estado*/
       public synchronized static String buscaApelacionesNumero() {
        String sql = "SELECT APEL_SOL_NUMERO, APEL_COD_APELACION, APEL_TIPO, APEL_OBSERVACIONES, APEL_NUM_SESION, APEL_FECHA_SESION, APEL_ESTADO_APEL FROM DR_REGT_APELACIONES where APEL_SOL_NUMERO=? AND APEL_ESTADO = 1";
        return (sql);
    }//
}

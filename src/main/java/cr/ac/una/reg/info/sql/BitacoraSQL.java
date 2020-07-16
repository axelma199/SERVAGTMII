/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.sql;

/**
 *
 * @author Muricio Garita
 * clase la cual registra cada movimiento que se realiza sobre una solicituid de equiparacion
 */
public class BitacoraSQL {
    /*Retorna todos los movimientos realizados a una equiparacion*/
    public synchronized static String cargaBitacora() {
        String sql = "SELECT BITA_ID_USUARIO, BITA_USUARIO, BITA_TRANSACCION, BITA_TABLA_AFECTADA, BITA_NUMERO_SOLICITUD, BITA_SOLICITUD_TIPO, BITA_SOLICITANTE_ID, BITA_NOMB_SOLICITANTE, BITA_FECHA FROM BITACORA";
        return (sql);
    }//
    /*Registra todos los movimeintos realizados de una equiparacion*/
     public synchronized static String SP_BITACORA() {
        String sql = "CALL SP_SERVAGTM_BITACORA( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return (sql);
    }//

     //  /*Retorna todos los movimientos realizados a una equiparacion por un usuario*/
     public synchronized static String buscaBitacoraId() {
        String sql = "SELECT BITA_ID_USUARIO, BITA_USUARIO, BITA_TRANSACCION, BITA_TABLA_AFECTADA, BITA_NUMERO_SOLICITUD, BITA_SOLICITUD_TIPO, BITA_SOLICITANTE_ID, BITA_NOMB_SOLICITANTE, BITA_FECHA FROM BITACORA where BITA_ID_USUARIO=?";
        return (sql);
    }//
}

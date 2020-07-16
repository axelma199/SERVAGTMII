/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.sql;

/**
 *
 * @author Mauricio garita
 * clase que registra cada movimiento que se realiza sobre una materia
 * REVIZAR SI APLICA AL MODULO DE EQUIVALENCIAS
 */
public class BitacoraMateriasSQL {
    
    /* carga todos los movimiento registrados a las materias*/
    public synchronized static String cargaBitacoraMaterias() {
        String sql = "SELECT BITAM_ID_USUARIO, BITAM_USUARIO, BITAM_TRANSACCION, BITAM_TABLA_AFECTADA, BITAM_NUMERO_SOLICITUD, BITAM_SOLICITUD_TIPO, BITAM_SOLICITANTE_ID, BITAM_NOMB_SOLICITANTE, BITAM_MAT_MODIFICADA, BITAM_MAT_ESTADO, BITAM_FECHA FROM BITACORA_MATERIAS";
        return (sql);
    }//
     
    /*sql que registra todos los movimeintos realizados sobre una materias*/
    public synchronized static String SP_BITACORA_MATERIAS() {
        String sql = "CALL SP_SERVAGTM_BITACORA_MATERIAS( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return (sql);
    }//
 
 /* carga los movimientos realizados por un usuario en especifico*/
    public synchronized static String buscaBitacoraMaterias_Id() {
        String sql = "SELECT BITAM_ID_USUARIO, BITAM_USUARIO, BITAM_TRANSACCION, BITAM_TABLA_AFECTADA, BITAM_NUMERO_SOLICITUD, BITAM_SOLICITUD_TIPO, BITAM_SOLICITANTE_ID, BITAM_NOMB_SOLICITANTE, BITAM_MAT_MODIFICADA, BITAM_MAT_ESTADO, BITAM_FECHA FROM BITACORA_MATERIAS where BITAM_ID_USUARIO=?";
        return (sql);
    }//
}

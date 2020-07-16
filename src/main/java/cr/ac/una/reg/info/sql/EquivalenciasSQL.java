/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.sql;

/**
 *
 * @author Mauricio  Garita
 * Clase la cual registra las esquivalencias
 * Esta clase se encuentra en espera hasta habilitar el modulo de equivalencias
 * 
 */
public class EquivalenciasSQL {

   //Retorna una equivalencia en especifico filtrado por numero de equivalencia
    public synchronized static String cargaEquivalencias() {
        String sql = "SELECT EQV_SOL_NUMERO, EQV_ESTADO, DR_REGT_EQUIVALENCIAS.SOLTE_ID, SOLTE_NOMBRE, EQV_ESCUELA_CONTINUAR FROM DR_REGT_EQUIVALENCIAS, DR_REGT_SOLICITANTE where DR_REGT_EQUIVALENCIAS.SOLTE_ID = DR_REGT_SOLICITANTE.SOLTE_ID AND EQV_SOL_NUMERO LIKE 'EQV-%' AND EQV_ESTADO != 3";
        return (sql);
    }//
    
    //Llama al paquete de base de datos que Actualiza y Elimina equivalencias
        public synchronized static String SP_Equivalencias() {
        String sql = "CALL DR_PKG_REGT.SP_SERVAGTM_EQUIVALENCIAS(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return (sql);
    }

//    public synchronized static String buscaEquivalenciasNum() {
//        String sql = "SELECT EQV_SOL_NUMERO,EQV_SOL_TIPO,EQV_FACULTAD_EST_REALIZADOS,EQV_ESCUELA_EST_REALIZADOS,EQV_CARRERA_EST_REALIZADOS,EQV_FACULTAD_CONTINUAR,EQV_ESCUELA_CONTINUAR,EQV_CARRERA_CONTINUAR_EST,EQV_GRADO_CONTINUAR,EQV_FECHA_ATENCION_USUARIO,EQV_FECHA_AREA_REC,EQV_FECHA_ENVIO_UNIDAD,EQV_FECHA_RECEPCION_FINAL FROM EQUIVALENCIAS where EQV_SOL_NUMERO=?";
//        return (sql);
//    }//
    
        /*public synchronized static String SP_MATERIAS_SOLICITUD() {
        String sql = "CALL SP_SERVAGTM_MATERIAS_SOLICITUD(?,?,?,?,?,?,?,?,?)";
        return (sql);
    }*/

    /*public synchronized static String CMD_CONSULTA_SOLICITANTE() {
        String sql = "SELECT distinct SPRIDEN_ID,SPRIDEN_LAST_NAME,SPRIDEN_FIRST_NAME,SPRADDR_PIDM,SPRADDR_STREET_LINE1,SPRADDR_CITY FROM (SELECT SPRIDEN_PIDM,SPRIDEN_ID,SPRIDEN_LAST_NAME,SPRIDEN_FIRST_NAME FROM SPRIDEN WHERE SPRIDEN_ID = ?)alias1 , (SELECT SPRADDR_PIDM,SPRADDR_STREET_LINE1,SPRADDR_CITY FROM SPRADDR)alias2 WHERE SPRIDEN_PIDM = SPRADDR_PIDM";
        return (sql);
    }*/

    //Retorna la lista de los tipos de grado
        public synchronized static String CMD_CONSULTA_GRADO() {
        String sql = "SELECT grado_ID,grado_NOMBRE FROM grado";
        return (sql);
    }
    
        //Consulta por codigo de equivalencia
     public synchronized static String CMD_CONSULTA_EQUIVALENCIAS() {
        String sql = "select * from DR_REGT_EQUIVALENCIAS, DR_REGT_SOLICITANTE where DR_REGT_EQUIVALENCIAS.SOLTE_ID = DR_REGT_SOLICITANTE.SOLTE_ID and EQV_ESTADO != 3 and DR_REGT_EQUIVALENCIAS.EQV_SOL_NUMERO =?";
        return (sql);
    }
     //Inserta equivalencias 
     public synchronized static String SP_INSERTA_EQUIVALENCIAS() {
        String sql = "{call ? := DR_PKG_REGT.FB_INSERTA_EQUIVALENCIAS(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        return (sql);
    }
}

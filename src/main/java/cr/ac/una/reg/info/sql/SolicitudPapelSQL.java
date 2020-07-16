/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cr.ac.una.reg.info.sql;

/**
 *
  <b>DESCRIPCION:</b> Clase que  consulta y registra  los datos pertenecientes a una solicitud de papel
 */
public class SolicitudPapelSQL {


    /**
     *  <b>DESCRIPCION:</b> Genera un listado de todas las solicitudes en estado pendiente filtrado por identificacion
     */
    public synchronized static String listarSolicitudPapel() {
        String sql ="SELECT  ID_SOLICITUD, "+
                             "ID_SOLICITANTE, "+
                             "(SELECT  SPRIDEN_FIRST_NAME "+
                             "FROM  SPRIDEN "+
                             "WHERE   SPRIDEN_ID=ID_SOLICITANTE "+
                             "AND SPRIDEN_CHANGE_IND is null) SOLICITANTE, "+
                             "DECODE(SECCION,'ATENCION_USUARIO','ATENCION USUARIO',SECCION) SECCION, "+
                             "CODIGO_PAPEL, "+
                             "(SELECT DESCRIPCION "+
							 "FROM DR_CIPSEG_TIPO_PAPEL b "+
							 "WHERE b.CODIGO_PAPEL=a.CODIGO_PAPEL) DESCRIPCION_PAPEL, "+
                             "FECHA_SOLICITUD, "+
                             "NUMERO_OFICIO_SOLICITUD, "+
                             "FECHA_ENTREGA, "+
                             "CODIGO_PAPEL, "+
                             "NUMERO_OFICIO_ENTREGA, "+
                             "ESTADO_SOLICITUD,   "+
                             "CANTIDAD_SOLICITADA  "+
                             "FROM DR_CIPSEG_SOLICITUD_PAPEL a " +
                             "WHERE ESTADO_SOLICITUD IN ('RESUELTA','SOLICITADA') "+
                             "AND ID_SOLICITANTE=? "+
                             "ORDER BY FECHA_SOLICITUD DESC";
//"WHERE ESTADOSOLICITUD=? "+
        return sql;
    }

    /**
     *  <b>DESCRIPCION:</b> Genera un listado de todas las solicitudes Finalizadas filtrado por identificacion
     */
 public synchronized static String listarSolicitudPapelCerrado() {
        String sql ="SELECT  ID_SOLICITUD, "+
                             "ID_SOLICITANTE, "+
                             "(SELECT  SPRIDEN_FIRST_NAME "+
                             "FROM  SPRIDEN "+
                             "WHERE   SPRIDEN_ID=ID_SOLICITANTE) SOLICITANTE, "+
                             "DECODE(SECCION,'ATENCION_USUARIO','ATENCION USUARIO',SECCION) SECCION, "+
                             "(SELECT DESCRIPCION "+
							 "FROM DR_CIPSEG_TIPO_PAPEL b "+
							 "WHERE b.CODIGO_PAPEL=a.CODIGO_PAPEL) DESCRIPCION_PAPEL, "+
                             "FECHA_SOLICITUD, "+
                             "NUMERO_OFICIO_SOLICITUD, "+
                             "FECHA_ENTREGA, "+
                             "CODIGO_PAPEL, "+
                             "NUMERO_OFICIO_ENTREGA, "+
                             "ESTADO_SOLICITUD,   "+
                             "CANTIDAD_SOLICITADA  "+
                             "FROM DR_CIPSEG_SOLICITUD_PAPEL a " +
                             "WHERE ESTADO_SOLICITUD IN ('CERRADA','ANULADA') "+
                             "AND ID_SOLICITANTE=? "+
                             "ORDER BY FECHA_SOLICITUD DESC";
//"WHERE ESTADOSOLICITUD=? "+
        return sql;
    }



/**
     *  <b>DESCRIPCION:</b> Realiza la busqueda de una solicitud en especifico
     */
    public synchronized static String buscarSolicitudPapel() {
        String sql ="SELECT  ID_SOLICITUD, "+
                             "ID_SOLICITANTE, "+
                             "SECCION,       "+
                             "FECHA_SOLICITUD, "+
                             "NUMERO_OFICIO_SOLICITUD, "+
                             "FECHA_ENTREGA, "+
                             "CODIGO_PAPEL, "+
                             "NUMERO_FOLIO_INICIAL, "+
                             "NUMERO_FOLIO_FINAL, "+
                             "CANTIDAD_SOLICITADA, "+
                             "NUMERO_OFICIO_ENTREGA, "+
                             "ESTADO_SOLICITUD   "+
                             "FROM DR_CIPSEG_SOLICITUD_PAPEL "+
                             "WHERE ID_SOLICITUD=?";

        return sql;
    }


    /**
     *  <b>DESCRIPCION:</b> Registra una nueva solicitud
     */
     public synchronized static String crearSolicitudPapel() {
       String sql ="insert into DR_CIPSEG_SOLICITUD_PAPEL "+
                   "(ID_SOLICITUD, "+
		           "ID_SOLICITANTE, "+
		           "SECCION,  "+
		           "FECHA_SOLICITUD, "+
		           "NUMERO_OFICIO_SOLICITUD, "+
		           "CODIGO_PAPEL, "+
		           "ESTADO_SOLICITUD, CANTIDAD_SOLICITADA) "+
		           "values "+
                   "(DR_CIPSEG_SOLICITUD_PAPEL_SEQ.NextVal, "+
                   "?," +
                   " ?," +
                   "SYSDATE," +
                   "?" +
                   ",?," +
                   "'SOLICITADA'," +
                   "?)";
                  return sql;
                  }

 /**
     *  <b>DESCRIPCION:</b> Modifica o añade datos a una solicitud ya existente
     */
       public synchronized static String modificarSolicitudPapelSolicitante() {
       String sql ="update DR_CIPSEG_SOLICITUD_PAPEL "+
		           "set SECCION= ?," +
                   "FECHA_SOLICITUD=SYSDATE, " +
                   "NUMERO_OFICIO_SOLICITUD=?," +
                   "CODIGO_PAPEL=?, "+
                   "ESTADO_SOLICITUD='PENDIENTE' "+
		           "WHERE ID_SOLICITUD=?";
                  return sql;
                  }

     

     
/**
 * <b>DESCRIPCION:</b> Modifica solo el estado de una solicitud ya existente
 */
      public synchronized static String ModificarEstadoSolicitudPapel() {
       String sql ="update DR_CIPSEG_SOLICITUD_PAPEL "+
		           "set ESTADO_SOLICITUD= ? "+
		           "WHERE ID_SOLICITUD=?";
                  return sql;
                  }


   /**
 *  <b>DESCRIPCION:</b> En caso de que la solicitud este anulada carga el motivo de la anulacion
 */
      public synchronized static String cargaMotivoAnulacion(){
          String sql ="select MOTIVO "+
                      "from DR_CIPSEG_SOLICITUDES_ANULADAS "+
                      "where ID_SOLICITUD=?";
                       return sql;
      }


/**
 *  <b>DESCRIPCION:</b> Elimina una solicitud ya existente
 */
      public synchronized static String EliminarSolicitudPapel() {
       String sql ="DELETE DR_CIPSEG_SOLICITUD_PAPEL "+
                  "WHERE ID_SOLICITUD=?";

            return sql;
      }

}

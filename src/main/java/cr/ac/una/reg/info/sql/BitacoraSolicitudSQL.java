package cr.ac.una.reg.info.sql;

/*Clase la cual registra todos los movimientos realizadoas a las solicitudes de equiparacion
 Autor Mauricio Garita
 */

public class BitacoraSolicitudSQL {

    public BitacoraSolicitudSQL() {
    }

    //Retorna la lista de todos los movimientos realizados a una equiparacion
    public synchronized static String lista_Dr_regt_bitacora_solicitud() {
        String sql = "SELECT A.BIT_ID,  A.BIT_SOL_ID,  A.BIT_TIPO_MOVIMIENTO,  A.BIT_ID_USUARIO,  b.descripcion,  A.BIT_FECHA,  a.bit_detalle FROM DR_REGT_BITACORA_SOLICITUD A INNER JOIN dr_siseg_usuario B ON a.bit_id_usuario=b.codigo";
        return (sql);
    }

//registra todos los movimientos de la equiparacion
    public synchronized static String spDr_regt_bitacora_solicitud() {
        String sql = "CALL REGISTRO.DR_PKG_REGT.sp_Dr_regt_bitacora_solicitud(?,?,?,?)";
        return (sql);
    }
}

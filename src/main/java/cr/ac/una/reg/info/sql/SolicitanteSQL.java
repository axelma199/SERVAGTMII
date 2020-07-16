package cr.ac.una.reg.info.sql;

/*
 * Autor Mauricio GArita
 * Clase de mantenimiento de los datos de persona a la que se le realiza una equiparacion
 * 
 */


public class SolicitanteSQL {

    public SolicitanteSQL() {
    }

    //Retorna la lista de todos los solicitantes registrados
    public synchronized static String lista_Dr_regt_solicitante() {
        String sql = "Select a.solte_id,a.solte_nombre,a.solte_nacionalidad,b.stvnatn_nation,a.solte_direccion,a.solte_telefono,a.solte_email,a.gtvzipc_code, a.stvcnty_code, solte_apellidos_solicitante, solte_nombre_solicitante,nvl(solte_seg_nombre_solicitante,' ')solte_seg_nombre_solicitante  from DR_REGT_SOLICITANTE a inner join stvnatn b on a.solte_nacionalidad=b.stvnatn_code";
        return (sql);
    }
    
    //Retorna los datos de un solicitante en especifico
    public synchronized static String buscaSolicitante() {
        String sql = "Select a.solte_id,a.solte_nombre,a.solte_nacionalidad,b.stvnatn_nation,a.solte_direccion,a.solte_telefono,a.solte_email,a.gtvzipc_code, a.stvcnty_code,solte_apellidos_solicitante, solte_nombre_solicitante,nvl(solte_seg_nombre_solicitante,' ') solte_seg_nombre_solicitante from DR_REGT_SOLICITANTE a inner join stvnatn b on a.solte_nacionalidad=b.stvnatn_code where solte_id = ?";
        return (sql);
    }

    //Llamado a los procedimientos de Incluir Modificar y Eliminar a un Solicitante
    public synchronized static String spDr_regt_solicitante() {
        String sql = "CALL REGISTRO.DR_PKG_REGT.sp_Dr_regt_solicitante(?,?,?,?,?,?,?,?,?,?,?,?)";
        return (sql);
    }
    
         //Llamado a los procedimiento de insertar, modificar y eliminar registros
    public synchronized static String BuscarSolicitante(String cedula) {
        String sql = "select 'true' FROM DR_REGT_SOLICITANTE WHERE SOLTE_ID = ? ";
        return (sql);
    }
    
       //Llamado a los procedimiento de insertar, modificar y eliminar registros
    public synchronized static String BuscarEquiparacion(String cedula) {
        String sql = "select 'true' FROM DR_REGT_EQUIPARACIONES WHERE SOLTE_ID = ? ";
        return (sql);
    }
}

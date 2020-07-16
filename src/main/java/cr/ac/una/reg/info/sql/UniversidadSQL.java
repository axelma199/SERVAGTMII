package cr.ac.una.reg.info.sql;

/*
 * Autor Mauricio Garita
 * Clase la cual consulta y registra universidades
 * 
 */

public class UniversidadSQL {

    public UniversidadSQL() {
             }

    //Retorna la lista de universidades para ser asignadas a un equiparacion normal
    public synchronized static String lista_Dr_regt_universidad() {
        String sql = "Select a.uni_id,a.stvnatn_code,a.uni_descripcion,b.stvnatn_nation from DR_REGT_UNIVERSIDAD a inner join stvnatn b on a.stvnatn_code = b.stvnatn_code";
        return (sql);
    }

    //Retorna la lista de universidades para ser asignadas a un equiparacion por oficio
     public synchronized static String lista_Dr_regt_universidadPorOficio() {
        String sql = "Select a.uni_id,a.stvnatn_code,a.uni_descripcion,b.stvnatn_nation from DR_REGT_UNIVERSIDAD a inner join stvnatn b on a.stvnatn_code = b.stvnatn_code where a.uni_id in (2,3,4)";
        return (sql);
    }
    
     //Retorna los registros de una univeridad en especifico
    public synchronized static String buscaUniversidad() {
        String sql = "Select a.uni_id,a.stvnatn_code,a.uni_descripcion,b.stvnatn_nation from DR_REGT_UNIVERSIDAD a inner join stvnatn b on a.stvnatn_code = b.stvnatn_code where uni_id=?";
        return (sql);
    }

    
    //Llamado a los procedimiento de insertar, modificar y eliminar registros
    public synchronized static String spDr_regt_universidad() {
        String sql = "CALL DR_PKG_REGT.sp_Dr_regt_universidad(?,?,?,?)";
        return (sql);
    }
    
     //Llamado a los procedimiento de insertar, modificar y eliminar registros
    public synchronized static String BuscarUniversidad(String nombre,String pais) {
        String sql = "select 'true' FROM DR_REGT_UNIVERSIDAD WHERE STVNATN_CODE = ? AND UNI_DESCRIPCION = ?";
        return (sql);
    }

}

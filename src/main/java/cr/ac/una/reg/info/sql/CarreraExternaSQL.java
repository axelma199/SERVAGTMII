package cr.ac.una.reg.info.sql;

public class CarreraExternaSQL {
    
    /*Autor mauricio Garita
     * Registra las carreras pertenecientes a Universidades 
     * 
     * 
     */

    public CarreraExternaSQL() {
    }
//retorna la lista de todas las carreras
    public synchronized static String lista_Dr_regt_carrera_externa() {
        String sql = "Select A.car_id,A.uni_id,A.car_descripcion,B.uni_descripcion,b.stvnatn_code,c.stvnatn_nation from DR_REGT_CARRERA_EXTERNA A INNER JOIN DR_REGT_UNIVERSIDAD B ON A.uni_id = B.uni_id inner join stvnatn c on b.stvnatn_code = c.stvnatn_code";
        return (sql);
    }

    //retorna la lista de todas las carreras de una universidad especifica
    public synchronized static String listaCarreraxUniversidad() {
        String sql = "Select A.car_id,A.uni_id,A.car_descripcion,B.uni_descripcion,b.stvnatn_code,c.stvnatn_nation from DR_REGT_CARRERA_EXTERNA A INNER JOIN DR_REGT_UNIVERSIDAD B ON A.uni_id = B.uni_id inner join stvnatn c on b.stvnatn_code = c.stvnatn_code where A.uni_id=?";
        return (sql);
    }
//inserta borra y modifica carreras
    public synchronized static String spDr_regt_carrera_externa() {
        String sql = "CALL DR_PKG_REGT.sp_Dr_regt_carrera_externa(?,?,?,?)";
        return (sql);
    }
    
     public synchronized static String buscarCarreraExternaAsignada() {
        String sql = "select distinct(EQP_CARRERA_PROCEDENCIA)"+
                     "from DR_REGT_EQUIPARACIONES "+
                     "where EQP_CARRERA_PROCEDENCIA=?";
        return (sql);
    }
    
       public synchronized static String buscarCarreraExternaExistente() {
       String sql = "select CAR_ID,UNI_ID,CAR_DESCRIPCION from DR_REGT_CARRERA_EXTERNA "+
                  "where UNI_ID=? "+
                  "and CAR_DESCRIPCION=? ";
     
       return (sql);
       
       }
}

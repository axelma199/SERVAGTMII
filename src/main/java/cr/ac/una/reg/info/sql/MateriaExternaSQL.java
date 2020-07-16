/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.sql;

/*
     * Autor Mauricio Garita
     * Clase de mantenimiento de las materias de otra universidades
     * 
     */
     

public class MateriaExternaSQL {
    
    
    //Metodo que retorna la lista de materias de una universidad
    public synchronized static String listarMateriaExterna() {
       
        String sql ="SELECT A.MAT_EXT_ID,A.MAT_EXT_CODIGO, A.MAT_EXT_DESCRIPCION, A.MAT_EXT_OFICIO, A.MAT_EXT_UNI_ID,  c.uni_id, c.uni_descripcion,  c.stvnatn_code,  d.stvnatn_nation   FROM DR_REGT_MATERIA_EXTERNA A INNER JOIN DR_REGT_UNIVERSIDAD C ON A.MAT_EXT_UNI_ID =c.uni_id inner join stvnatn d on c.stvnatn_code = d.stvnatn_code";
        //antes de poner cod universidad en la tabla materias externas
        //String sql = "SELECT A.MAT_EXT_ID,A.MAT_EXT_CODIGO, A.MAT_EXT_DESCRIPCION,A.MAT_EXT_OFICIO, A.CAR_ID,b.car_descripcion,B.uni_id,c.uni_descripcion,c.stvnatn_code,d.stvnatn_nation FROM DR_REGT_MATERIA_EXTERNA A INNER JOIN dr_regt_carrera_externa B ON a.CAR_ID = B.CAR_ID INNER JOIN DR_REGT_UNIVERSIDAD C ON B.uni_id=c.uni_id inner join stvnatn d on c.stvnatn_code = d.stvnatn_code ";
        return (sql);
    }//
    
    
    
    /*** NO SE USA**/
    public synchronized static String listarMateriaExternaxCarrera() {
        String sql = "SELECT A.MAT_EXT_ID,A.MAT_EXT_CODIGO, A.MAT_EXT_DESCRIPCION,A.MAT_EXT_OFICIO, A.CAR_ID,b.car_descripcion,B.uni_id,c.uni_descripcion,c.stvnatn_code,d.stvnatn_nation FROM DR_REGT_MATERIA_EXTERNA A INNER JOIN dr_regt_carrera_externa B ON a.CAR_ID = B.CAR_ID INNER JOIN DR_REGT_UNIVERSIDAD C ON B.uni_id=c.uni_id inner join stvnatn d on c.stvnatn_code = d.stvnatn_code where a.CAR_ID = ? ";
        return (sql);
    }//
        
   
    //Metodo que retorna la lista de materias por oficio de otras univerisidaes
    public synchronized static String listarMateriaExternaPorOficio() {
        
        String sql = "SELECT A.MAT_EXT_ID,A.MAT_EXT_CODIGO, A.MAT_EXT_DESCRIPCION, A.MAT_EXT_OFICIO, A.MAT_EXT_UNI_ID,  c.uni_id, c.uni_descripcion,  c.stvnatn_code,  d.stvnatn_nation    FROM DR_REGT_MATERIA_EXTERNA A  INNER JOIN DR_REGT_UNIVERSIDAD C ON A.MAT_EXT_UNI_ID =c.uni_id inner join stvnatn d on c.stvnatn_code = d.stvnatn_code where A.MAT_EXT_UNI_ID = ? and A.MAT_EXT_OFICIO='S'";
          
          //antes de poner el codigo de universidad en la tabla materias externas
          //String sql = "SELECT A.MAT_EXT_ID,A.MAT_EXT_CODIGO, A.MAT_EXT_DESCRIPCION,A.MAT_EXT_OFICIO, A.CAR_ID,b.car_descripcion,B.uni_id,c.uni_descripcion,c.stvnatn_code,d.stvnatn_nation FROM DR_REGT_MATERIA_EXTERNA A INNER JOIN dr_regt_carrera_externa B ON a.CAR_ID = B.CAR_ID INNER JOIN DR_REGT_UNIVERSIDAD C ON B.uni_id=c.uni_id inner join stvnatn d on c.stvnatn_code = d.stvnatn_code where c.uni_id= ? and A.MAT_EXT_OFICIO='S'";
        return (sql);
    }//
    
    //Llama al paquete que contiene los metodos de Inserta, modificar y eliminar materias
    public synchronized static String spDr_regt_materia_externa() {
        String sql = "CALL DR_PKG_REGT.sp_Dr_regt_materia_externa(?,?,?,?,?,?)";
        return (sql);
    }
    
    //genera un consecutivo de codigo de materia extrangera en caso de no existir un codigo 
       public synchronized static String BuscarConsecutivoMateriaExternaExtrangero() {
        String sql = " select nvl(max(to_number(substr(mat_ext_codigo,4,4))),0)+1  consecutivoCME from dr_regt_materia_externa "+
                     "where substr(mat_ext_codigo,1,3)='CME'";
        return (sql);
    }//
   
    
       //Retorna las materias externas pertenecientes a una universidad
       public synchronized static String BuscarCodigpMateriaExternaExistente() {
        String sql = "select mat_ext_codigo from DR_REGT_MATERIA_EXTERNA "+
                     "where mat_ext_codigo=? and mat_ext_uni_id=?";
        return (sql);
        
        
    }//   
    
    //retorna una materias filtrada por codigo
    public synchronized static String BuscarCodigoMateriaAsignada() {
        String sql = "select MAT_SOL_EQUIVALENTE "+
                     "from DR_REGT_MATERIAS_SOLICITUD "+
                     "where MAT_SOL_EQUIVALENTE = ? ";
        return (sql);
    }
    
    //retorna una materias de oficio filtrada por codigo
       public synchronized static String BuscarCodigoMateriaOficioAsignada() {
        String sql = "select distinct(MAT_EXT_ID) FROM DR_REGT_MATERIA_EXT_EQP_OFI "+
                      "where MAT_EXT_ID=?";
        
        return (sql);
    }//   
    
   
         public synchronized static String buscarIdUniversidadPorDescripcion(String descrip) {
       String sql = "select UNI_ID from DR_REGT_UNIVERSIDAD WHERE UNI_DESCRIPCION = ? ";
     
       return (sql);
       
       }
}

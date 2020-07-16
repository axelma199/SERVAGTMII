package cr.ac.una.reg.info.sql;


/*
 * Autor Mauricio garita
 * Clase la cual realiza consulta de datos de la soliictud de una equiparacion
 * 
 * 
 */

public class MateriasSolicitudSQL{

    public MateriasSolicitudSQL() {
    }

    //Retorna los datos de una solicitud de equiparaion en especifico
    public synchronized static String listaMatSolEqp() {
        String sql = "Select mat_sol_numero,mat_sol_reconocer,mat_sol_reconocer_nom,mat_sol_creditos,mat_sol_equivalente,mat_sol_equivalente_nom,mat_sol_creditos_eqv,mat_sol_condicion from DR_REGT_MATERIAS_SOLICITUD_EQP where mat_sol_numero = ?";
        return (sql);
    }
    
    //Retorna los datos de una solicitud de equivalencia en especifico
    public synchronized static String listaMatSolEqv() {
        String sql = "Select mat_sol_numero,mat_sol_reconocer,mat_sol_reconocer_nom,mat_sol_creditos,mat_sol_equivalente,mat_sol_equivalente_nom,mat_sol_creditos_eqv,mat_sol_condicion from DR_REGT_MATERIAS_SOLICITUD_EQV where mat_sol_numero = ?";
        return (sql);
    }

    //Retorna los datos de las materias  de una solicitud de equiparacion filtrado por codigos de materias
    public synchronized static String buscaDr_regt_materias_solicitud() {
        String sql = "Select mat_sol_numero,mat_sol_reconocer,mat_sol_reconocer_nom,mat_sol_creditos,mat_sol_equivalente,mat_sol_equivalente_nom,mat_sol_creditos_eqv,mat_sol_condicion from DR_REGT_MATERIAS_SOLICITUD where mat_sol_numero = ? and mat_sol_reconocer = ?  and mat_sol_equivalente = ?";
        return sql;
    }
    
    //Retorna los datos de las materias  de una solicitud de equiparacion filtrado por codigos de materias
    public synchronized static String buscaMatSolEqp() {
        String sql = "Select mat_sol_numero,mat_sol_reconocer,mat_sol_reconocer_nom,mat_sol_creditos,mat_sol_equivalente,mat_sol_equivalente_nom,mat_sol_creditos_eqv,mat_sol_condicion from DR_REGT_MATERIAS_SOLICITUD_EQP where mat_sol_numero = ? and mat_sol_reconocer = ?  and mat_sol_equivalente = ?";
        return sql;
    }
    
    //Retorna los datos de las materias  de una solicitud de una equivalencia filtrado por codigos de materias
    public synchronized static String buscaMatSolEqv() {
        String sql = "Select mat_sol_numero,mat_sol_reconocer,mat_sol_reconocer_nom,mat_sol_creditos,mat_sol_equivalente,mat_sol_equivalente_nom,mat_sol_creditos_eqv,mat_sol_condicion from DR_REGT_MATERIAS_SOLICITUD_EQV where mat_sol_numero = ? and mat_sol_reconocer = ?  and mat_sol_equivalente = ?";
        return sql;
    }

    
    //llamado de procedimientos de Incluir, Eliminar y modificar registros de una equiparacion
    public synchronized static String spMatSolEqp() {
        String sql = "CALL DR_PKG_REGT.sp_Dr_regt_mat_sol_eqp(?,?,?,?,?,?,?,?,?)";
        return (sql);
    }
    
    //llamado de procedimientos de Incluir, Eliminar y modificar registros de una equivakencia
    public synchronized static String spMatSolEqv() {
        String sql = "CALL DR_PKG_REGT.sp_Dr_regt_mat_sol_eqv(?,?,?,?,?,?,?,?,?)";
        return (sql);
    }
    
     //llamado de procedimientos de Incluir, Eliminar y modificar registros de una solicitud de equiparacion
   public synchronized static String spMatSol() {
        String sql = "CALL DR_PKG_REGT.sp_Dr_regt_materias_solicitud(?,?,?,?,?,?,?,?,?)";
        return (sql);
    }
    
   //Retorna la lista de las  materias   de la UNA filtrada por escuela
   public synchronized static String CargaMaterias() {
        //String sql = "Select DISTINCT SCBCRSE_SUBJ_CODE,SCBCRSE_CRSE_NUMB,SCBCRSE_TITLE,SCBCRSE_CREDIT_HR_LOW FROM scbcrse WHERE SCBCRSE_COLL_CODE=?";
        String sql = "Select DISTINCT SCBCRSE_SUBJ_CODE,SCBCRSE_CRSE_NUMB,SCBCRSE_TITLE,SCBCRSE_CREDIT_HR_LOW FROM scbcrse WHERE SCBCRSE_COLL_CODE=?";
        return (sql);
    }//
    
    
//Retorna los datos de una equivalencia en especifico            
    public synchronized static String CargaDetalleEquivalencias() {
        String sql = "SELECT DISTINCT MAT_SOL_RECONOCER, MAT_SOL_RECONOCER_NOM, MAT_SOL_CREDITOS, MAT_SOL_CONDICION FROM DR_REGT_MATERIAS_SOLICITUD WHERE MAT_SOL_NUMERO =? order by MAT_SOL_RECONOCER ";
        return (sql);
    }//
    
  //Retorna detalle de materias de una solicitud de equiparacion
    public synchronized static String CargaDetalleMaterias() {
       // String sql = "SELECT MAT_SOL_NUMERO, MAT_SOL_RECONOCER, MAT_SOL_EQUIVALENTE, MAT_SOL_CREDITOS_MAT_EQV, MAT_SOL_CONDICION, MAT_SOL_NOMBRE_MAT_EQV, MAT_SOL_NOMBRE_MAT_REC, MAT_SOL_CREDITOS_MAT_REC FROM DR_REGT_MATERIAS_SOLICITUD WHERE MAT_SOL_RECONOCER =? AND MAT_SOL_NUMERO =?";
        String sql = "SELECT MAT_SOL_EQUIVALENTE, MAT_SOL_CREDITOS_EQV, MAT_SOL_EQUIVALENTE_NOM FROM DR_REGT_MATERIAS_SOLICITUD WHERE MAT_SOL_NUMERO =? AND MAT_SOL_RECONOCER =?";//crear tabla DR_REGT_MATERIAS_SOLICITUD_EQP
        return (sql);
    }//
    
   
    
    //retorna todos los registros de materias a reconocer filtrdo por numero de solicitud, codigo de materia a reconocer y equivalente
    public synchronized static String BuscaMateriasSolicitud() {
       
        String sql = "select * from DR_REGT_MATERIAS_SOLICITUD \n" +
                     " where MAT_SOL_NUMERO=? \n" +
                     " and  MAT_SOL_RECONOCER=? \n" +
                     " and  MAT_SOL_EQUIVALENTE=?";
        return (sql);
    }//
    
    
    
    
}

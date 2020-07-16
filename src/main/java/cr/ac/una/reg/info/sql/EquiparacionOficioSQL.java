/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.sql;

/**
 *
 * @author Mauricio Garita
 * Clase la cual registra las equiparaciones por oficio
 * 
 */
public class EquiparacionOficioSQL {

    // Carga la lista de equiparaciones por oficio
    public synchronized static String listaEquiparacionOficio() {
        String sql = "Select A.eqp_ofi_id,A.eqp_ofi_nombre,A.eqp_ofi_descripcion,A.uni_id,B.uni_descripcion, EXTRACT(YEAR FROM TO_DATE (sysdate)) annoActual, A.eqp_ofi_periodo,  A.eqp_ofi_estado from DR_REGT_EQUIPARACION_OFICIO A INNER JOIN DR_REGT_UNIVERSIDAD b ON a.uni_id=b.uni_id";
        return (sql);
    }
/* Se elimina por modifcacion*/
    public synchronized static String buscaDr_regt_equiparacion_oficio() {
        String sql = "Select eqp_ofi_id,eqp_ofi_nombre,eqp_ofi_descripcion,uni_id from DR_REGT_EQUIPARACION_OFICIO where ";
        return (sql);
    }

  
    /*Sql  modifica y elimina equiparaciones*///
    public synchronized static String sp_equiparacion_oficio() {
        String sql = "CALL DR_PKG_REGT.sp_equiparacion_oficio(?,?,?,?,?,?,?)";
        return (sql);
    }
    
     
    /* sql inserta equiparaciones */
    public synchronized static String fb_equiparacion_oficio() {
        String sql = "{call ? := DR_PKG_REGT.FB_INSERTA_EQUIPARACION_OFICIO(?,?,?,?,?)}";
        return (sql);
    }
    
    //retorna el año actual de la base de datos
      public synchronized static String cargaAnnoActualEqpOficio() {
       String sql="select EXTRACT(YEAR FROM TO_DATE (sysdate)) annoActual from dual";
       return (sql);
      }
}

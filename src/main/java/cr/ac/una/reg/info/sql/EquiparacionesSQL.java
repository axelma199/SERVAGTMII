/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.sql;

/**
 *
 * @author Mauricio Garita
 * Clase la cual registra las equiparaciones
 */
public class EquiparacionesSQL {

    
    //Retorna la lista de las equiparaciones registradas
    public synchronized static String lista_Dr_regt_equiparaciones() {
        String sql = "Select a.eqp_sol_numero,a.solte_id,B.solte_nombre,a.eqp_carrera_continuar_est,c.smrprle_program_desc,a.eqp_grado_continuar,a.eqp_fecha_atencion_usuario,a.eqp_fecha_area_rec,a.eqp_fecha_envio_unidad,a.eqp_fecha_recepcion_final,a.eqp_total_pagar,a.eqp_estado,a.eqp_numero_sesion,a.eqp_fecha_sesion,a.eqp_observaciones,a.eqp_periodo,  EXTRACT(YEAR FROM TO_DATE (sysdate)) annoActual, a.eqp_fecha_recepcion_orer,b.solte_apellidos_solicitante, b.Solte_nombre_solicitante, b.solte_seg_nombre_solicitante from DR_REGT_EQUIPARACIONES a inner join DR_REGT_SOLICITANTE B ON A.solte_id = B.solte_id inner join SMRPRLE c on REPLACE(a.eqp_carrera_continuar_est,'Ñ','N') = REPLACE(c.smrprle_program,'Ñ','N') WHERE EQP_ESTADO != 3 order by a.EQP_FECHA_ATENCION_USUARIO desc";
        return (sql);
    }

    
    //Busca una equiparacion 
    public synchronized static String buscaDr_regt_equiparaciones() {
        String sql = "Select eqp_sol_numero,solte_id,eqp_carrera_continuar_est,eqp_grado_continuar,eqp_fecha_atencion_usuario,eqp_fecha_area_rec,eqp_fecha_envio_unidad,eqp_fecha_recepcion_final,eqp_total_pagar,eqp_estado,eqp_numero_sesion,eqp_fecha_sesion,eqp_observaciones from DR_REGT_EQUIPARACIONES where eqp_sol_numero = ? and eqp_estado != 3";

        return (sql);
    }

    //Actualiza y elimina equiparaciones
    public synchronized static String spDr_regt_equiparaciones() {
        String sql = "CALL REGISTRO.DR_PKG_REGT.sp_Dr_regt_equiparaciones(?,?,?,?,?,?,?,?,?,?,?)";
        return (sql);
    }
    
    //inserta equiparaciones
     public synchronized static String fb_inserta_equiparaciones() {
        String sql = "{call ? := REGISTRO.DR_PKG_REGT.FB_INSERTA_EQUIPARACION(?,?,?,?,?)}";
        return (sql);
    }
}

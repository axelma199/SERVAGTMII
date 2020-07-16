/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.sql;

/**
 *
 * @author N00148096
 */
public class EquiparacionesModificacionDatosSQL {
    



 /*SQL para carga una equiparacion*/
     public synchronized static String CargaEquiparacion() {
        String sql = "select EQP_SOL_NUMERO, EQP_SEDE_INGRESAR, EQP_CARRERA_CONTINUAR_EST,EQP_GRADO_CONTINUAR,EQP_CARRERA_PROCEDENCIA,CAR_DESCRIPCION," +
                    "(select UNI_DESCRIPCION " +
                    "from dr_regt_universidad c " +
                    "where c.UNI_ID=b.UNI_ID) UPROCEDENCIA, "+
                    "d.SOLTE_ID ID, d.SOLTE_NOMBRE NOMBRE "+
                    "FROM DR_REGT_EQUIPARACIONES a " +
                    "INNER JOIN  DR_REGT_CARRERA_EXTERNA b ON CAR_ID=EQP_CARRERA_PROCEDENCIA " +
                    "INNER JOIN DR_REGT_SOLICITANTE d ON  d.SOLTE_ID = a.SOLTE_ID "+
                    "WHERE EQP_SOL_NUMERO = ? "+
                    "AND EQP_ESTADO <> 2";

        return (sql);
    }//
     
      public synchronized static String ActualizarDatosEquiparacion() {
       String sql = "update DR_REGT_EQUIPARACIONES set EQP_SEDE_INGRESAR= ?,EQP_CARRERA_CONTINUAR_EST=?,EQP_GRADO_CONTINUAR=?,EQP_CARRERA_PROCEDENCIA=? "+
                    "where  EQP_SOL_NUMERO = ? "+
                    "and EQP_ESTADO in (0,1)";
         return (sql);
      }
     
     

//    public synchronized static String CargaEquiparacionSolicitante()  
//     select a.SOLTE_ID, SOLTE_NOMBRE 
//from DR_REGT_SOLICITANTE a
//inner join DR_REGT_EQUIPARACIONES b ON a.SOLTE_ID=b.SOLTE_ID
//where b.EQP_SOL_NUMERO = 'EQP-3491'
//     
}



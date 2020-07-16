/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.sql;

/**
 *
 * @author Mauricio garita
 * Clase la cual registra los parametros  del costo de los creditos por año
 *
 */


public class FinancieroSQL {
    
    //retorna la lista de los parametros con estado activos
    public synchronized static String cargaFinanciero() {
        String sql = "SELECT FINANCIERO_COD, FINANCIERO_ANO, FINANCIERO_RUBRO, FINANCIERO_VALOR FROM DR_REGT_FINANCIERO WHERE FINANCIERO_ESTADO = 1";
        return (sql);
    }//
         
    //Llamada al procedimiento que inserta, elimina y modifica los parametros
     public synchronized static String SP_FINANCIERO() {
        String sql = "CALL DR_PKG_REGT.SP_SERVAGTM_FINANCIERO( ?, ?, ?, ?, ?)";

        return (sql);
    }//

       /*public synchronized static String buscaFinancieroAnno() {
        String sql = "SELECT FINANCIERO_ANO, FINANCIERO_RUBRO, FINANCIERO_VALOR FROM FINANCIERO where FINANCIERO_ANO=? AND FINANCIERO_ESTADO = 1";
        return (sql);
    }//*/
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.sql;

/**
 *
 * @author 401660077
 */
public class GtvzipcSQL {
    
    /**
     * Autor: Mauricio Garita
   * MÃ©todo que retorna el select para cargar provincias.
   * @return
   */
  public synchronized static String cargaProvincias() {
    String sql="select distinct(gtvzipc_stat_code) codigo_prov_letras," +
                      "substr(gtvzipc_code,1,1)codigo_prov_numero," +
                      "(select stvstat_desc from stvstat where stvstat_code=gtvzipc_stat_code) describ_provincia " +
                "from gtvzipc " +
                "where gtvzipc_stat_code <>'EXT'";
               return(sql);
  }//

  /**
   * MÃ©todo que retorna el sql para cargar los distritos.
   * @return
   */
  public synchronized static String cargaDistritos() {
     String sql= "select gtvzipc_code, "+
                        "gtvzipc_city "+
                   "from gtvzipc " +
                  "where substr(gtvzipc_code,1,3)=? "+
                  "and gtvzipc_code not in('10100','20100','30100','40100','50100','60100','70100') "+
                  "and substr(gtvzipc_code,4,2) <>'00'";
     return sql;
  }//

    
    
}

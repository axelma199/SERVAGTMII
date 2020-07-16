/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.sql;

/**
 *
 *  <b>DESCRIPCION:</b> Clase que  verifica si un usuario tiene acceso al sistema y retorna
 * tipo de rol asignado
 */
 
public class SecuritySQL {


    /**
 *
 *  <b>DESCRIPCION:</b> Sql que retorna si el usuario tiene acceso al sistema y el rol que tiene
 */
    public synchronized static String validaUsuario() {
           String sql="select 'true', "+
                    "(select r.id_rol from  DR_SISEG_USUARIO_ROL r "+
                                     "where r.id_rol in('GRP_CIPSEG_ADMIN','GRP_CIPSEG_PROC','GRP_CIPSEG_USER') "+
                                      "and r.id_usuario=codigo) roll "+
                    "from DR_SISEG_USUARIO u "+
                    "where CODIGO = ? "+
                    "and ESTADO = 'A' "+
                    "and u.CODIGO=(select r.id_usuario from  DR_SISEG_USUARIO_ROL r "+
                                   "where r.id_rol in('GRP_CIPSEG_ADMIN','GRP_CIPSEG_PROC','GRP_CIPSEG_USER') "+
                                   "and r.id_usuario=u.codigo)";
    
       return (sql);
    }
}
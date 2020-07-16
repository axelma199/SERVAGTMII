/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.UsuarioBean;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;

/**
 *
 * @author 401660077
 */
public interface SecurityDao {
  boolean validaUsuario(UsuarioBean usuarioBean) throws ExceptionConnection;
}

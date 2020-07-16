/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.dao;
import cr.ac.una.reg.info.beans.BitacoraBean;
import cr.ac.una.reg.info.sql.BitacoraSQL;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



/**
 *
 * @author Mauricio Garita
 * Clase la cual registra el historico de todos los movimeintos que se le rgistran a una equiparacion
 * 
 */
public class BitacoraDao extends Connector{

    public BitacoraDao() {
    }

   //Metodo el cual realiza el llamado de los procedimiento de Incluir los registros en la bitacora
    public void IME_BITACORA(BitacoraBean bitacoraBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(BitacoraSQL.SP_BITACORA());
                prepareStatement.setInt(1, 1);
                prepareStatement.setString(2, bitacoraBean.getBita_id_usuario());
                prepareStatement.setString(3, bitacoraBean.getBita_usuario());
                prepareStatement.setString(4, bitacoraBean.getBita_transaccion());
                prepareStatement.setString(5, bitacoraBean.getBita_tabla_afectada());
                prepareStatement.setString(6, bitacoraBean.getBita_numero_solicitud());
                prepareStatement.setString(7, bitacoraBean.getBita_solicitud_tipo());
                prepareStatement.setString(8, bitacoraBean.getBita_solicitante_id());
                prepareStatement.setString(9, bitacoraBean.getBita_nomb_solicitante());
                prepareStatement.setDate(10, bitacoraBean.getBita_fecha());
                prepareStatement.execute();
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al incertar", sqlex.toString(), 1, true, 3, "DaoImpl");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al incertar", ex.toString(), 1, true, 3, "admisionDaoImpl");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "admisionDaoImpl");
                }
            }
        }
    }
    
    //Metodo el cual retorna todos los registros historicos de las equiparaciones
     public ArrayList<BitacoraBean> cargaBitacoras() throws ExceptionConnection {
          PreparedStatement prepareStatement = null;
        ArrayList<BitacoraBean> arrayBitacora = new ArrayList<BitacoraBean>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(BitacoraSQL.cargaBitacora());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    BitacoraBean oBitacora = new BitacoraBean();
                    oBitacora.setBita_id_usuario(resultSet.getString("BITA_ID_USUARIO"));
                    oBitacora.setBita_usuario(resultSet.getString("BITA_USUARIO"));
                    oBitacora.setBita_transaccion(resultSet.getString("BITA_TRANSACCION"));
                    oBitacora.setBita_tabla_afectada(resultSet.getString("BITA_TABLA_AFECTADA"));
                    oBitacora.setBita_numero_solicitud(resultSet.getString("BITA_NUMERO_SOLICITUD"));
                    oBitacora.setBita_solicitud_tipo(resultSet.getString("BITA_SOLICITUD_TIPO"));
                    oBitacora.setBita_solicitante_id(resultSet.getString("BITA_SOLICITANTE_ID"));
                    oBitacora.setBita_nomb_solicitante(resultSet.getString("BITA_NOMB_SOLICITANTE"));
                    oBitacora.setBita_fecha(resultSet.getDate("BITA_FECHA"));
                    arrayBitacora.add(oBitacora);
                }//fin del while
            }//fin de if
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1000:" + "Error al cargar Titulos", sqlex.toString(), 1, true, 3, "GtvzipcDaoImpl");
        } catch (Exception ex) {
            throw new ExceptionConnection("1001:" + "Error al cargar Titulos", ex.toString(), 1, true, 3, "GtvzipcDaoImpl");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1002:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "GtvzipcDaoImpl");
                }//
            }//
        }//
        return arrayBitacora;
    }
     
     //Metodo que retorna los registros de un historico de una equiparacion en especifico
      public ArrayList<BitacoraBean> buscarBitacoraId (BitacoraBean bitacoraBean) throws ExceptionConnection {
          PreparedStatement prepareStatement = null;
        ArrayList<BitacoraBean> arrayBitacora = new ArrayList<BitacoraBean>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(BitacoraSQL.buscaBitacoraId());
                prepareStatement.setString(1, bitacoraBean.getBita_id_usuario());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    BitacoraBean oBitacora = new BitacoraBean();
                    oBitacora.setBita_id_usuario(resultSet.getString("BITA_ID_USUARIO"));
                    oBitacora.setBita_usuario(resultSet.getString("BITA_USUARIO"));
                    oBitacora.setBita_transaccion(resultSet.getString("BITA_TRANSACCION"));
                    oBitacora.setBita_tabla_afectada(resultSet.getString("BITA_TABLA_AFECTADA"));
                    oBitacora.setBita_numero_solicitud(resultSet.getString("BITA_NUMERO_SOLICITUD"));
                    oBitacora.setBita_solicitud_tipo(resultSet.getString("BITA_SOLICITUD_TIPO"));
                    oBitacora.setBita_solicitante_id(resultSet.getString("BITA_SOLICITANTE_ID"));
                    oBitacora.setBita_nomb_solicitante(resultSet.getString("BITA_NOMB_SOLICITANTE"));
                    oBitacora.setBita_fecha(resultSet.getDate("BITA_FECHA"));
                    arrayBitacora.add(oBitacora);
                }//fin del while
            }//fin de if
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1000:" + "Error al cargar Titulos", sqlex.toString(), 1, true, 3, "GtvzipcDaoImpl");
        } catch (Exception ex) {
            throw new ExceptionConnection("1001:" + "Error al cargar Titulos", ex.toString(), 1, true, 3, "GtvzipcDaoImpl");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1002:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "GtvzipcDaoImpl");
                }//
            }//
        }//
        return arrayBitacora;
    }
      
}

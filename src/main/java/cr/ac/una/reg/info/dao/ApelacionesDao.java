/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.dao;
import cr.ac.una.reg.info.beans.ApelacionesBean;
import cr.ac.una.reg.info.sql.ApelacionesSQL;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author MAuriico Garita
 * 
 * Clase la cual carga y registra los datos de las apelaciones a las equiparaciones
 */
public class ApelacionesDao extends Connector{

  //Inicializa la conexion con la base de datos
    public ApelacionesDao() throws ExceptionConnection  {
        this.inicializarDataSource();
    }
    
    //Procedimiento que Inserta los registros de las apelaciones
    public void INSERTA_APELACIONES(ApelacionesBean apelacionesBean) throws ExceptionConnection {
        CallableStatement prepareStatement = null;
        try {
            if (this.openConnection()) {
                prepareStatement = (CallableStatement) this.getConexion().prepareCall(ApelacionesSQL.SP_INSERTA_Apelaciones());
                prepareStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
                prepareStatement.setString(2, apelacionesBean.getApel_sol_numero());
                prepareStatement.setString(3, apelacionesBean.getApel_codApelar());
                prepareStatement.setString(4, apelacionesBean.getApel_tipo());
                prepareStatement.setString(5, apelacionesBean.getApel_Observaciones());
                prepareStatement.execute();
                
                String resultado = prepareStatement.getString(1);
                apelacionesBean.setApel_sol_numero(resultado);
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

    //Metodo que realiza el llamado de Insercion, Modificacion de las apelaciones
    public void IME_APELACIONES(int accion, ApelacionesBean apelacionesBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(ApelacionesSQL.SP_Apelaciones());
                prepareStatement.setInt(1, accion);
                prepareStatement.setString(2, apelacionesBean.getApel_sol_numero());
                prepareStatement.setString(3, apelacionesBean.getApel_codApelar());
                prepareStatement.setString(4, apelacionesBean.getApel_tipo());
                prepareStatement.setString(5, apelacionesBean.getApel_Observaciones());
                prepareStatement.setString(6, apelacionesBean.getApel_num_sesion());
                prepareStatement.setTimestamp(7, new java.sql.Timestamp(apelacionesBean.getApel_fecha_sesion().getTime()));
                prepareStatement.setString(8, apelacionesBean.getApel_estado_apel());
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
    
    //Metodo que retorna el listado de las apelaciones
    public ArrayList<ApelacionesBean> cargaApelaciones() throws ExceptionConnection {
          PreparedStatement prepareStatement = null;
        ArrayList<ApelacionesBean> arrayApelacion = new ArrayList<ApelacionesBean>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(ApelacionesSQL.cargaApelaciones());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    ApelacionesBean oApelacion = new ApelacionesBean();
                    oApelacion.setApel_sol_numero(resultSet.getString("APEL_SOL_NUMERO"));
                    oApelacion.setApel_codApelar(resultSet.getString("APEL_COD_APELACION"));
                    oApelacion.setApel_tipo(resultSet.getString("APEL_TIPO"));
                    oApelacion.setApel_Observaciones(resultSet.getString("APEL_OBSERVACIONES"));
                    oApelacion.setApel_num_sesion(resultSet.getString("APEL_NUM_SESION"));
                    oApelacion.setApel_fecha_sesion(resultSet.getDate("APEL_FECHA_SESION"));
                    oApelacion.setApel_estado_apel(resultSet.getString("APEL_ESTADO_APEL"));
                    oApelacion.setApelId(resultSet.getString("ID_BANNER"));
                    oApelacion.setApelNombre(resultSet.getString("NOMBRE"));
                    arrayApelacion.add(oApelacion);
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
        return arrayApelacion;
    }
      
    //Metodo el cual busca una apelacion en especifico  
      public boolean buscarApelacionMumero(ApelacionesBean apelacionBean) throws ExceptionConnection {
          boolean indicador = false;
          PreparedStatement prepareStatement = null;
        ArrayList<ApelacionesBean> arrayApelacion = new ArrayList<ApelacionesBean>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(ApelacionesSQL.buscaApelacionesNumero());
                prepareStatement.setString(1, apelacionBean.getApel_sol_numero());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    apelacionBean.setApel_sol_numero(resultSet.getString("APEL_SOL_NUMERO"));
                    apelacionBean.setApel_codApelar(resultSet.getString("APEL_COD_APELACION"));
                    apelacionBean.setApel_tipo(resultSet.getString("APEL_TIPO"));
                    apelacionBean.setApel_Observaciones(resultSet.getString("APEL_OBSERVACIONES"));
                    apelacionBean.setApel_num_sesion(resultSet.getString("APEL_NUM_SESION"));
                    apelacionBean.setApel_fecha_sesion(resultSet.getDate("APEL_FECHA_SESION"));
                    apelacionBean.setApel_estado_apel(resultSet.getString("APEL_ESTADO_APEL"));
                    indicador = true;
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
        return indicador;
    } 

}


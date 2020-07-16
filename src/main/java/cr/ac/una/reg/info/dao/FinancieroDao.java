/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.dao;
import cr.ac.una.reg.info.beans.FinancieroBean;
import cr.ac.una.reg.info.sql.FinancieroSQL;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Autor Mauricio Garita
 * Clase la cual registra los parametros de costos por cada credito
 * 
 */
public class FinancieroDao extends Connector{

    //iniicaliza la conexion con la base de datos
    public FinancieroDao() throws ExceptionConnection {
        this.inicializarDataSource();
    }

    //metodo el cual incerta el valor del credito por año
    public void IME_FINANCIERO(int opcion, FinancieroBean financieroBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(FinancieroSQL.SP_FINANCIERO());
                prepareStatement.setInt(1, opcion);
                prepareStatement.setString(2, financieroBean.getFinanciero_cod());
                prepareStatement.setString(3, financieroBean.getFinanciero_ano());
                prepareStatement.setString(4, financieroBean.getFinanciero_rubro());
                prepareStatement.setFloat(5, financieroBean.getFinanciero_valor());
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
    
    //Retorna la lista de todos los parametros registrados 
    public ArrayList<FinancieroBean> cargaFinanciero() throws ExceptionConnection {
          PreparedStatement prepareStatement = null;
        ArrayList<FinancieroBean> arrayFinanciero = new ArrayList<FinancieroBean>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(FinancieroSQL.cargaFinanciero());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    FinancieroBean oFinanciero = new FinancieroBean();
                    oFinanciero.setFinanciero_cod(resultSet.getString("FINANCIERO_COD"));
                    oFinanciero.setFinanciero_ano(resultSet.getString("FINANCIERO_ANO"));
                    oFinanciero.setFinanciero_rubro(resultSet.getString("FINANCIERO_RUBRO"));
                    oFinanciero.setFinanciero_valor(resultSet.getFloat("FINANCIERO_VALOR"));
                    arrayFinanciero.add(oFinanciero);
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
        return arrayFinanciero;
    }
      
      ////Se comento pq no se esta utilizando
        /*public ArrayList<FinancieroBean> buscarFinancieroAnno (FinancieroBean financieroBean) throws ExceptionConnection {
          PreparedStatement prepareStatement = null;
        ArrayList<FinancieroBean> arrayFinanciero = new ArrayList<FinancieroBean>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(FinancieroSQL.buscaFinancieroAnno());
                prepareStatement.setString(1, financieroBean.getFinanciero_ano());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    FinancieroBean oFinanciero = new FinancieroBean();
                    oFinanciero.setFinanciero_ano(resultSet.getString("FINANCIERO_ANO"));
                    oFinanciero.setFinanciero_rubro(resultSet.getString("FINANCIERO_RUBRO"));
                    oFinanciero.setFinanciero_valor(resultSet.getFloat("FINANCIERO_VALOR"));
                    arrayFinanciero.add(oFinanciero);
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
        return arrayFinanciero;
    }*/
 
}

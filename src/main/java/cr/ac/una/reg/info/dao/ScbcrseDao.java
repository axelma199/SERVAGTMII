/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.ScbcrseSQL;
import cr.ac.una.reg.info.beans.ScbcrseBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Mauriico Garita
 * Clase la cual muestra y registra en el sistema de equiparaciones los cursos existentes en BANNER
 */
public class ScbcrseDao extends Connector {

    
  //Inicializa los parametros de la base de datos
    public ScbcrseDao() throws ExceptionConnection {
        this.inicializarDataSource();
    }

    //Retorna las lista de los cursos de la tabla de BANNER SCBCRSE pertenecientes a una escuel en especifico
    public ArrayList<Object[]> Consulta_Materia(String codEscuela) throws SQLException, ExceptionConnection {
        ArrayList<Object[]> lista = new ArrayList<Object[]>();
PreparedStatement prepareStatement=null;
        // Abre una conexión a la base de datos y carga la lista de usuarios.
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(ScbcrseSQL.CMD_CONSULTA_MATERIA_ESCUELA());
               prepareStatement.setString(1, codEscuela);
                ResultSet rs = prepareStatement.executeQuery();

                int maxCols = rs.getMetaData().getColumnCount();
                while (rs.next()) {
                    Object[] registro = new Object[maxCols];
                    for (int i = 0; i < maxCols; i++) {
                        registro[i] = rs.getObject(i + 1);
                    }
                    lista.add(registro);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            //this.getConexion().close();
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "SCBCRSE");
                }
            }
        }
        return lista;
    }

    //Retorna las lista de todos los cursos de la tabla de BANNER SCBCRSE 
    public ArrayList<ScbcrseBean> ListarScbcrse() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<ScbcrseBean> arrayScbcrse = new ArrayList<>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(ScbcrseSQL.CMD_LISTAR());
               // prepareStatement.setString(1, codEscuela);
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    ScbcrseBean oScbcrse = new ScbcrseBean();
                    oScbcrse.setCodigo(resultSet.getString("SCBCRSE_SUBJ_CODE") + resultSet.getString("SCBCRSE_CRSE_NUMB"));
                    oScbcrse.setScbcrse_credit_hr_low(resultSet.getInt("scbcrse_credit_hr_low"));
                     oScbcrse.setScbcrse_coll_code(resultSet.getString("scbcrse_coll_code"));
                    oScbcrse.setScbcrse_crse_numb(resultSet.getString("scbcrse_crse_numb"));
                    oScbcrse.setScbcrse_title(resultSet.getString("scbcrse_title"));                    
                    oScbcrse.setScbcrse_subj_code(resultSet.getString("scbcrse_subj_code"));                   
 
                    arrayScbcrse.add(oScbcrse);
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al insertar", sqlex.toString(), 1, true, 3, "dmlSCBCRSE");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al insertar", ex.toString(), 1, true, 3, "dmlSCBCRSE");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "SCBCRSE");
                }
            }
        }
        return arrayScbcrse;
    }
}

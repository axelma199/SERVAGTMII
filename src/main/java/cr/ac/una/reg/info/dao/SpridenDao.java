package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.SpridenBean;
import cr.ac.una.reg.info.sql.SpridenSQL;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Autor Mauricio Garita
 * Clase la cual consulta informacion de los solicitantes en BANNER
 * 
 */


public class SpridenDao extends Connector {

   
    //Inicializa la base de datos
    public SpridenDao() throws ExceptionConnection {
        this.inicializarDataSource();

    }

    
    //Retorna los datos de un  solicitante en caso de que exista en BANNER
    public Boolean buscaSpriden(SpridenBean spridenBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        boolean encontrado = false;
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(SpridenSQL.buscaSpriden());
                prepareStatement.setString(1, spridenBean.getSpriden_id());
                ResultSet resultSet = prepareStatement.executeQuery();
                resultSet.next();
                if (resultSet.getRow() > 0) {                   
                    spridenBean.setSpriden_id(resultSet.getString("spriden_id"));
                    spridenBean.setSpriden_last_name(resultSet.getString("spriden_last_name"));
                    spridenBean.setSpriden_first_name(resultSet.getString("spriden_first_name"));
                    encontrado = true;
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("Se produjo un error al consultar", sqlex.toString(), 1, true, 3, "dmlSPRIDEN");
        } catch (Exception ex) {
            throw new ExceptionConnection("Se produjo un error al consultar", ex.toString(), 1, true, 3, "dmlSPRIDEN");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "SPRIDEN");
                }
            }
        }
        return encontrado;
    }
}

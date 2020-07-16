/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.StvnatnSQL;
import cr.ac.una.reg.info.beans.StvnatnBean;
import cr.ac.una.reg.info.sql.StvnatnSQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author JGA
 * Consulta de los paise registrados en BANNER
 * 
 */
public class StvnatnDao extends Connector {

   //Inicializa los parametros de la base de datos
    public StvnatnDao() throws ExceptionConnection {
        this.inicializarDataSource();
    }

    //Retorna la lista de los paises registrados en BANNER
    public ArrayList<StvnatnBean> ListarStvnatn() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<StvnatnBean> arrayStvnatn = new ArrayList<StvnatnBean>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(StvnatnSQL.CMD_CONSULTA_PAISES());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    StvnatnBean oStvnatn = new StvnatnBean();
                    oStvnatn.setStvnatn_code(resultSet.getString("stvnatn_code"));
                    oStvnatn.setStvnatn_nation(resultSet.getString("stvnatn_nation"));
                    arrayStvnatn.add(oStvnatn);
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al insertar", sqlex.toString(), 1, true, 3, "dmlSTVNATN");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al insertar", ex.toString(), 1, true, 3, "dmlSTVNATN");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "STVNATN");
                }
            }
        }
        return arrayStvnatn;
    }
    
     //Metodo el cual busca un pais en especifico  
    public Boolean bucaStvnatn(StvnatnBean stvnatnBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        boolean encontrado=false;
        
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(StvnatnSQL.buscaPais());
                prepareStatement.clearParameters();
                prepareStatement.setString(1, stvnatnBean.getStvnatn_code());
                ResultSet resultSet = prepareStatement.executeQuery();
                resultSet.next();
                if (resultSet.getRow()> 0) {                
                    stvnatnBean.setStvnatn_nation(resultSet.getString("stvnatn_nation"));
                    encontrado=true;
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al insertar", sqlex.toString(), 1, true, 3, "dmlSTVNATN");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al insertar", ex.toString(), 1, true, 3, "dmlSTVNATN");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "STVNATN");
                }
            }
        }
        return encontrado;
    }
}

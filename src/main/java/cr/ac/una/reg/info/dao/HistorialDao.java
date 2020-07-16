
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.Dr_regt_historialBean;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.HistorialSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JGA
 * Esta clase no se utiliza devido a que aun no se ha implantado el modulo de equivalencias
 * 
 */
public class HistorialDao extends Connector{
    
    public HistorialDao() throws ExceptionConnection {
        this.inicializarDataSource();
    }
        
    public ArrayList<Dr_regt_historialBean> cargaMateriasEquivaler() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<Dr_regt_historialBean> arrayMaterias = new ArrayList<Dr_regt_historialBean>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(HistorialSQL.CargaMaterias());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    Dr_regt_historialBean oMateria = new Dr_regt_historialBean();
                    oMateria.setCodigo(resultSet.getString("SCBCRSE_SUBJ_CODE") + resultSet.getString("SCBCRSE_CRSE_NUMB"));
                    oMateria.setNombre(resultSet.getString("SCBCRSE_TITLE"));
                    oMateria.setCreditos(resultSet.getInt("SCBCRSE_CREDIT_HR_LOW"));
                    arrayMaterias.add(oMateria);
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
        return arrayMaterias;
    }
}

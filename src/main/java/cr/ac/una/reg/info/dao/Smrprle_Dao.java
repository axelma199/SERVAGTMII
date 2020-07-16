/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.dao;
 
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.SmrprleSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JGA
 */
public class Smrprle_Dao extends Connector {
    
    public Smrprle_Dao() throws ExceptionConnection  {
        this.inicializarDataSource();
    }
    
     public ArrayList<Object[]> Consulta_Carrera(String Codigo) throws SQLException, ExceptionConnection {
        ArrayList<Object[]> lista = new ArrayList<Object[]>();
     PreparedStatement prepareStatement=null;
        // Abre una conexión a la base de datos y carga la lista de usuarios.
        try {
            if (this.openConnection()) {
                 prepareStatement = this.getConexion().prepareStatement(SmrprleSQL.CMD_CONSULTA_CARRERA());
                prepareStatement.setString(1, Codigo);
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
             if( prepareStatement != null ){
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:"+"Error al cerrar statement", sqlex.toString() , 1, true, 3, "SecurityDaoImpl");
                }
           }//
        }
        return lista;
    }
}

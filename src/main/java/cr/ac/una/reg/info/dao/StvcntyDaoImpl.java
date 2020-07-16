/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.CantonBean;
import cr.ac.una.reg.info.beans.ProvinciaBean;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.StvcntySQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author 401660077
 */
public class StvcntyDaoImpl  extends Connector {
    
       /**
     * Constructor recibe un usuario, esto para inicializar la conexión a la base de datos.
     * @param usuarioBean
     * @throws cr.ac.una.reg.info.exceptions.ExceptionConnection
     */
    public StvcntyDaoImpl() throws ExceptionConnection{
        this.inicializarDataSource();
    }//constructor

    /**
     * Método que permite cargar los cantones a partir de un número de provincia.
     * @param provinciaBean
     * @return
     * @throws cr.ac.una.reg.info.exceptions.ExceptionConnection
     */
    public ArrayList<CantonBean> cargaCantones(ProvinciaBean provinciaBean) throws ExceptionConnection {
      PreparedStatement prepareStatement = null;
       ArrayList<CantonBean> arrayCantones = new ArrayList<CantonBean>();
        try{
             if(this.openConnection()){
               prepareStatement =  (PreparedStatement) this.getConexion().prepareStatement( StvcntySQL.cargaCantones() );
               prepareStatement.setString(1, String.valueOf( provinciaBean.getNumero()  ));

               ResultSet resultset = prepareStatement.executeQuery();
               while(resultset.next()){
                  CantonBean cantonBeanTemp = new CantonBean();
                  cantonBeanTemp.setCodigo( resultset.getInt("STVCNTY_CODE") );
                  cantonBeanTemp.setDescripcion( resultset.getString("STVCNTY_DESC") );

                  arrayCantones.add( cantonBeanTemp );
               }//
             }//
        }catch(ExceptionConnection exc){
            throw exc;
        }catch(SQLException sqlex){
            throw new ExceptionConnection("1024:"+"Error al cargar los cantones", sqlex.toString() , 1, true, 3, "StvcntyDaoImpl");
        }catch(Exception ex){
            throw new ExceptionConnection("1025:"+"Error al cargar los cantones", ex.toString(), 1, true, 3, "StvcntyDaoImpl");
        }finally{
           if( prepareStatement != null ){
            try {
                prepareStatement.close();
                this.closeConnection();
            } catch (SQLException sqlex) {
                throw new ExceptionConnection("1026:"+"Error al cerrar statement", sqlex.toString() , 1, true, 3, "StvcntyDaoImpl");
            }//
           }//
        }//
        return arrayCantones;
    }//

    
}

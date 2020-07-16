/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.dao;





import cr.ac.una.reg.info.beans.CantonBean;
import cr.ac.una.reg.info.beans.DistritoBean;
import cr.ac.una.reg.info.beans.ProvinciaBean;
import cr.ac.una.reg.info.beans.UsuarioBean;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.GtvzipcSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import oracle.jdbc.OraclePreparedStatement;


/**
 *
 * @author 401660077
 */
public class GtvzipcDaoImpl extends Connector {
    public GtvzipcDaoImpl() throws ExceptionConnection{
 
      this.inicializarDataSource();
    }//constructor

    /**
     * Método que permite cargas las provincias.
     * @return
     * @throws cr.ac.una.reg.info.exceptions.ExceptionConnection
     */
    public ArrayList<ProvinciaBean> cargaProvincias() throws ExceptionConnection {
       PreparedStatement prepareStatement = null;
       ArrayList<ProvinciaBean> arrayProvincias = new ArrayList<ProvinciaBean>();
        try{
             if(this.openConnection()){
               prepareStatement =  (PreparedStatement) this.getConexion().prepareStatement( GtvzipcSQL.cargaProvincias() );        
               ResultSet resultset = prepareStatement.executeQuery();
               while(resultset.next()){
                  ProvinciaBean provinciaBeanTemp = new ProvinciaBean();
                  provinciaBeanTemp.setNumero( resultset.getInt("CODIGO_PROV_NUMERO") );
                  provinciaBeanTemp.setCodigo( resultset.getString("CODIGO_PROV_LETRAS") );
                  provinciaBeanTemp.setDescripcion( resultset.getString("DESCRIB_PROVINCIA") );
                  arrayProvincias.add( provinciaBeanTemp );
               }//
             }//
        }catch(ExceptionConnection exc){
            throw exc;
        }catch(SQLException sqlex){
            throw new ExceptionConnection("1000:"+"Error al cargar Provincias", sqlex.toString() , 1, true, 3, "GtvzipcDaoImpl");
        }catch(Exception ex){
            throw new ExceptionConnection("1001:"+"Error al cargar Provincias", ex.toString(), 1, true, 3, "GtvzipcDaoImpl");
        }finally{
           if( prepareStatement != null ){
            try {
                prepareStatement.close();
                this.closeConnection();
            } catch (SQLException sqlex) {
                throw new ExceptionConnection("1002:"+"Error al cerrar statement", sqlex.toString() , 1, true, 3, "GtvzipcDaoImpl");
            }//
           }//
        }//
        return arrayProvincias;
    }

    /**
     * Método que a partir del código de un cantón, carga los distritos.
     * @param cantonBean
     * @return
     * @throws cr.ac.una.reg.info.exceptions.ExceptionConnection
     */
    public ArrayList<DistritoBean> cargaDistritos(CantonBean cantonBean) throws ExceptionConnection {
       PreparedStatement prepareStatement = null;
       ArrayList<DistritoBean> arrayDistritos = new ArrayList<DistritoBean>();
        try{
             if(this.openConnection()){
               prepareStatement =  (PreparedStatement) this.getConexion().prepareStatement( GtvzipcSQL.cargaDistritos() );
               prepareStatement.setString(1, String.valueOf( cantonBean.getCodigo()  ));

               ResultSet resultset = prepareStatement.executeQuery();
               while(resultset.next()){
                  DistritoBean distritoBeanTemp = new DistritoBean();
                  distritoBeanTemp.setCodigo( resultset.getInt("GTVZIPC_CODE") );
                  distritoBeanTemp.setDescripcion( resultset.getString("GTVZIPC_CITY") );

                  arrayDistritos.add( distritoBeanTemp );
               }//
             }//
        }catch(ExceptionConnection exc){
            throw exc;
        }catch(SQLException sqlex){
            throw new ExceptionConnection("1003:"+"Error al cargar Distritos", sqlex.toString() , 1, true, 3, "GtvzipcDaoImpl");
        }catch(Exception ex){
            throw new ExceptionConnection("1004:"+"Error al cargar Distritos", ex.toString(), 1, true, 3, "GtvzipcDaoImpl");
        }finally{
           if( prepareStatement != null ){
            try {
                prepareStatement.close();
                this.closeConnection();
            } catch (SQLException sqlex) {
                throw new ExceptionConnection("1005:"+"Error al cerrar statement", sqlex.toString() , 1, true, 3, "GtvzipcDaoImpl");
            }//
           }//
        }//
        return arrayDistritos;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cr.ac.una.reg.info.connection;

import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import javax.naming.Context;





import javax.naming.InitialContext;
import oracle.jdbc.pool.OracleDataSource;
import static org.olap4j.driver.xmla.XmlaOlap4jDriver.Property.DataSource;
//import javax.sql.DataSource;

/**
 * @author i111910530
 *
 * Esta clase permite establecer una conexión a una base de datos, ésta posee un constructor protegido ya
 * que su única finalidad es ser heredada por otro clase.
 * Posee los métodos básicos para el control de la conexión.
 */

public class Connector {
    //variables que representan los mensajes de error que se podrían producir.
    private static final String CONNECTOR_ERROR_DS = "Error al inicializar DataSource";
    private static final String CONNECTOR_ERROR_OPEN_CONNECTION = "Error al abrir conexion";
    private static final String CONNECTOR_ERROR_CLOSE_CONNECTION = "Error al cerrar conexion";
    private static final String CONNECTOR_ERROR_ROLLBACK = "Error al hacer rollback";
    private static final String CONNECTOR_ERROR_COMMIT = "Error al hacer commit";

    private  Connection conexion;                                                        // objeto conexión
  private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";  // url para la conexión a la base de datos oracle
  // private static final String URL =  "jdbc:oracle:thin:@bannerdb.una.ac.cr:1521:PROD";  // url para la conexión a la base de datos oracle
 
    private static final String DRIVER_TYPE = "oracle.jdbc.OracleDriver";                // especificación del driver para conectar a oracle
//    private oracle.jdbc.pool.OracleDataSource dataSource;
     // datasource de la conexión


  OracleDataSource dataSource;
  //  DataSource dataSource;
   InitialContext ic;

    /**
     * constructor protegido, esto se debe a que la clase debe ser heredada para su utilización.
     */
    protected Connector(){
    }//constructor

    /**
     * Este método permite inicializar el datasource
     * @param user
     * @param password
     * @throws cr.ac.una.reg.info.exceptions.ExceptionConnection
     */
    protected void inicializarDataSource() throws ExceptionConnection{
        try {
         ic = new InitialContext();
////              this.dataSource =  (OracleDataSource) ic.lookup( "jdbc/banner_XE");
//                                                        //jdbc/banner_trng9x
//                                                       //jdbc/banner_desa
//              //jdbc/banner_trng9x
                        

//// Class.forName("oracle.jdbc.OracleDriver");
////        String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:XE";
////        conexion= DriverManager.getConnection(BaseDeDatos,"REGISTRO","ORACLE");
////Context initialContext; 
////Context environmentContext;
////initialContext = new InitialContext();
//// environmentContext =  (Context) initialContext.lookup("java:comp/env");
//// this.dataSource =  (OracleDataSource) environmentContext.lookup("banner_XE");    
//// 
 
 
 dataSource =  new oracle.jdbc.pool.OracleDataSource();
            dataSource.setURL(URL);

            dataSource.setDriverType(DRIVER_TYPE);
            dataSource.setUser("REGISTRO");
           dataSource.setPassword("ORACLE");
////        } catch (SQLException ex) {
////            throw new ExceptionConnection("500:"+CONNECTOR_ERROR_DS ,ex.toString(), 1, true, 3, "Connector");
        } catch (Exception ex){
            throw new ExceptionConnection("501:"+CONNECTOR_ERROR_DS ,ex.toString(), 1, true, 3, "Connector");
        }//
    }//


    /**
     * Este método permite verificar si la conexión se encuetra activa o no existe, en caso
     * de existir se pone en estado autocommit falso para posser un control sobre el commit.
     * @return boolean
     * @throws cr.ac.una.reg.info.exceptions.ExceptionConnection
     */
    protected boolean openConnection() throws ExceptionConnection{
        boolean estado = false;
        try {
            this.closeConnection();
            if(this.dataSource != null){
               this.conexion = this.dataSource.getConnection();
               
               //this.conexion.setAutoCommit(false);
               this.conexion.setAutoCommit(true);
               estado = true;
            }else{
               throw new ExceptionConnection("504:"+CONNECTOR_ERROR_OPEN_CONNECTION, "No inicializó el datasource", 1, true, 3, "Connector");
            }//
        } catch (SQLException ex) {
            throw new ExceptionConnection("502:"+CONNECTOR_ERROR_OPEN_CONNECTION, ex.toString(), 1, true, 3, "Connector");
        } catch(Exception ex){
            throw new ExceptionConnection("503:"+CONNECTOR_ERROR_OPEN_CONNECTION, ex.toString(), 1, true, 3, "Connector");
        }
        return estado;
    }//

     /**
      * Permite hacer un rollback parcial a las sentencias ejecutadas en la base.
      * @param savepoint
      * @throws cr.ac.una.reg.info.exceptions.ExceptionConnection
      */
     protected void rollbackSavepoint(Savepoint savepoint) throws ExceptionConnection{
       try{
           if(this.conexion != null && savepoint != null){
                this.conexion.rollback(savepoint);
           }
       }catch(Exception ex){
           throw new ExceptionConnection("505"+CONNECTOR_ERROR_ROLLBACK, ex.toString(), 1, true, 3, "Connector");
       }//
    }//

     /**
      * Permite realizar un rollback completo
      * @throws cr.ac.una.reg.info.exceptions.ExceptionConnection
      */
    protected void rollback() throws ExceptionConnection{
       try{
           if(this.conexion != null){
                this.conexion.rollback();
           }
       }catch(Exception ex){
           throw new ExceptionConnection("506"+CONNECTOR_ERROR_ROLLBACK, ex.toString(), 1, true, 3, "Connector");
       }//
    }//

    /**
     * Permite realizar un commit
     * @throws cr.ac.una.reg.info.exceptions.ExceptionConnection
     */
    protected void commit() throws ExceptionConnection{
      try{
          if(this.conexion != null){
              this.conexion.commit();
          }
      }catch(Exception ex){
         throw new ExceptionConnection("507"+CONNECTOR_ERROR_COMMIT, ex.toString(), 1, true, 3, "Connector");
      }//
    }//

    /**
     * Permite cerrar la conexión a la base de datos
     * @throws cr.ac.una.reg.info.exceptions.ExceptionConnection
     */
    protected void closeConnection() throws ExceptionConnection{
        try {
            if(this.conexion != null){
                this.conexion.close();
            }//
        } catch (SQLException ex) {
            throw new ExceptionConnection("508"+CONNECTOR_ERROR_CLOSE_CONNECTION, ex.toString(), 1, true, 3, "Connector");
        } catch(Exception ex){
            throw new ExceptionConnection("509"+CONNECTOR_ERROR_CLOSE_CONNECTION, ex.toString(), 1, true, 3, "Connector");
        }//
    }//

    public Connection getConexion() {
        return conexion;
    }

}//

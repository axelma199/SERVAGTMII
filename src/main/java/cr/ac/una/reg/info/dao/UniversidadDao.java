package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.StvnatnBean;
import cr.ac.una.reg.info.dao.StvnatnDao;
import cr.ac.una.reg.info.beans.UniversidadBean;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import cr.ac.una.reg.info.sql.UniversidadSQL;
import java.sql.PreparedStatement;


/*Autor Mauricio Garita
 * Clase la cual registra  Univeridades 
 * 
 */
public class UniversidadDao extends Connector {

    //inicializa los parametros de las bases de datos
    public UniversidadDao() throws ExceptionConnection {
        this.inicializarDataSource();
    }

    //Metodo el cual inserta, elimina y actuliza las Universidades
    public void dmlUniversidad(int p_opcion, UniversidadBean universidadBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(UniversidadSQL.spDr_regt_universidad());
                prepareStatement.setInt(1, p_opcion);
                prepareStatement.setInt(2, universidadBean.getUni_id());
                prepareStatement.setString(3, universidadBean.getStvnatn().getStvnatn_code());
                prepareStatement.setString(4, universidadBean.getUni_descripcion());
                prepareStatement.execute();
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
                throw new ExceptionConnection("1007:Error al incertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_UNIVERSIDAD");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al incertar", ex.toString(), 1, true, 3, "dmlDR_REGT_UNIVERSIDAD");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_UNIVERSIDAD");
                }
            }
        }
    }
    
    
    
    
    

   //Metodo el cual retorna la lista de las universidades 
    public ArrayList<UniversidadBean> ListarUniversidad() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        
        ArrayList<UniversidadBean> arrayUniversidad = new ArrayList<UniversidadBean>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(UniversidadSQL.lista_Dr_regt_universidad());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    StvnatnBean stvnatn = new StvnatnBean();
                    StvnatnDao stvnatnDao = new StvnatnDao();
                    UniversidadBean oUniversidad = new UniversidadBean();
                    oUniversidad.setUni_id(resultSet.getInt("uni_id"));
                    stvnatn.setStvnatn_code(resultSet.getString("stvnatn_code"));
                    stvnatn.setStvnatn_nation(resultSet.getString("stvnatn_nation"));
                    oUniversidad.setStvnatn(stvnatn);
                    oUniversidad.setUni_descripcion(resultSet.getString("uni_descripcion"));
                    arrayUniversidad.add(oUniversidad);
                }
            }
        } catch (ExceptionConnection exc) {            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al incertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_UNIVERSIDAD");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al incertar", ex.toString(), 1, true, 3, "dmlDR_REGT_UNIVERSIDAD");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_UNIVERSIDAD");
                }
            }
        }
        return arrayUniversidad;
    }
    
    
    //Retorna la lista  de las univerisdades que son propias de las equiparaciones por oficio
    public ArrayList<UniversidadBean> ListarUniversidadPorOficio() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        
        ArrayList<UniversidadBean> arrayUniversidad = new ArrayList<UniversidadBean>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(UniversidadSQL.lista_Dr_regt_universidadPorOficio());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    StvnatnBean stvnatn = new StvnatnBean();
                    StvnatnDao stvnatnDao = new StvnatnDao();
                    UniversidadBean oUniversidad = new UniversidadBean();
                    oUniversidad.setUni_id(resultSet.getInt("uni_id"));
                    stvnatn.setStvnatn_code(resultSet.getString("stvnatn_code"));
                    stvnatn.setStvnatn_nation(resultSet.getString("stvnatn_nation"));
                    oUniversidad.setStvnatn(stvnatn);
                    oUniversidad.setUni_descripcion(resultSet.getString("uni_descripcion"));
                    arrayUniversidad.add(oUniversidad);
                }
            }
        } catch (ExceptionConnection exc) {            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al incertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_UNIVERSIDAD");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al incertar", ex.toString(), 1, true, 3, "dmlDR_REGT_UNIVERSIDAD");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_UNIVERSIDAD");
                }
            }
        }
        return arrayUniversidad;
    }
    
    
      //
       public boolean buscarUniversidad( UniversidadBean universidadBean) throws ExceptionConnection {
       
            PreparedStatement prepareStatement = null;
        
         try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(UniversidadSQL.BuscarUniversidad(universidadBean.getUni_descripcion(),universidadBean.getStvnatn().getStvnatn_code()));
                prepareStatement.setString(1, universidadBean.getStvnatn().getStvnatn_code());
                prepareStatement.setString(2, universidadBean.getUni_descripcion());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                          return true;
                    
                 } 
            }
        } catch (ExceptionConnection exc) {            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al incertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_UNIVERSIDAD");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al incertar", ex.toString(), 1, true, 3, "dmlDR_REGT_UNIVERSIDAD");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_UNIVERSIDAD");
                }
            }
        }
            
           
           
            return false;
        
    }
    
       
       
}



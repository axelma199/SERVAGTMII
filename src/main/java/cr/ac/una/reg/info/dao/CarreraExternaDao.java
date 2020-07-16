package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.CarreraExternaBean;
import cr.ac.una.reg.info.beans.StvnatnBean;
import cr.ac.una.reg.info.beans.UniversidadBean;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.CarreraExternaSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



/*
 * Clase la ual contiene todos los metodos de listado, Insersion y eliminacion de Carreras 
 * de otras universidades
 * 
 */
public class CarreraExternaDao extends Connector{

    public CarreraExternaDao() throws ExceptionConnection  {
        this.inicializarDataSource();
    }

   //LLamado al los metodos de insercion,eliminacion y modificacion de carreras
    public void dmlCarreraExterna(int p_opcion, CarreraExternaBean carreraExternaBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(CarreraExternaSQL.spDr_regt_carrera_externa());
                prepareStatement.setInt(1, p_opcion);
                prepareStatement.setInt(2, carreraExternaBean.getCar_id());
                prepareStatement.setInt(3, carreraExternaBean.getUniversidad().getUni_id());
                prepareStatement.setString(4, carreraExternaBean.getCar_descripcion());
                prepareStatement.execute();
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            if (sqlex.getErrorCode() == 1) {
                throw new ExceptionConnection("Error, registro ya se encuentra registrado", sqlex.toString(), 1, true, 3, "dmlDR_REGT_CARRERA_EXTERNA externa");
            } else {
                throw new ExceptionConnection("1007:Error al insertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_CARRERA_EXTERNA");
            }
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error", ex.toString(), 1, true, 3, "dmlDR_REGT_CARRERA_EXTERNA");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_CARRERA_EXTERNA");
                }
            }
        }
    }

    //metodo el cual retorna el listado de las carreras
    public ArrayList<CarreraExternaBean> ListarCarreraExterna(int codUniversidad) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<CarreraExternaBean> arrayCarreraExterna = new ArrayList<>();
        try {
            if (this.openConnection()) {
                if(codUniversidad==0){
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(CarreraExternaSQL.lista_Dr_regt_carrera_externa());
                }else
                {
                    prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(CarreraExternaSQL.listaCarreraxUniversidad());
                    prepareStatement.setInt(1, codUniversidad);
                }
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    UniversidadBean oUniversidad=new UniversidadBean();
                    StvnatnBean stvnatnBean=new StvnatnBean();
                    CarreraExternaBean oCarreraExternaBean = new CarreraExternaBean();
                    oCarreraExternaBean.setCar_id(resultSet.getInt("car_id"));
                    oUniversidad.setUni_id(resultSet.getInt("uni_id"));
                    oUniversidad.setUni_descripcion(resultSet.getString("uni_descripcion"));
                    stvnatnBean.setStvnatn_code(resultSet.getString("stvnatn_code"));
                    stvnatnBean.setStvnatn_nation(resultSet.getString("stvnatn_nation"));
                    oUniversidad.setStvnatn(stvnatnBean);
                    oCarreraExternaBean.setUniversidad(oUniversidad);
                    oCarreraExternaBean.setCar_descripcion(resultSet.getString("car_descripcion"));
                    arrayCarreraExterna.add(oCarreraExternaBean);
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al insertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_CARRERA_EXTERNA");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al insertar", ex.toString(), 1, true, 3, "dmlDR_REGT_CARRERA_EXTERNA");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_CARRERA_EXTERNA");
                }
            }
        }
              
        
        return arrayCarreraExterna;
    }
    
   //metodo el cual retorna los registros de una carrera asignada a una equiparacion
    public boolean BuscaCarreraExternaAsignada( CarreraExternaBean carreraExternaBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<CarreraExternaBean> arrayCarreraExterna = new ArrayList<>();
        boolean codCarreraExiste=false;
        try {
            if (this.openConnection()) {
             
               prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(CarreraExternaSQL.buscarCarreraExternaAsignada());
               prepareStatement.setInt(1, carreraExternaBean.getCar_id());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                     codCarreraExiste=true;
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al incertar", sqlex.toString(), 1, true, 3, "listarMateriaExterna");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al incertar", ex.toString(), 1, true, 3, "listarMateriaExterna");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_MATERIA_EXTERNA");
                }
            }
        }
    
    return codCarreraExiste;
     }
    
    
    //metodo el cual retorna los registros de una carrera por descripcion de la carrera y la universidad
    public boolean buscaCarreraExternaExistente( CarreraExternaBean carreraExternaBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<CarreraExternaBean> arrayCarreraExterna = new ArrayList<>();
        boolean codCarreraExiste=false;
        try {
            if (this.openConnection()) {
             
               prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(CarreraExternaSQL.buscarCarreraExternaExistente());
                prepareStatement.setInt(1, carreraExternaBean.getUniversidad().getUni_id());
                prepareStatement.setString(2, carreraExternaBean.getCar_descripcion());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                     codCarreraExiste=true;
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al incertar", sqlex.toString(), 1, true, 3, "listarMateriaExterna");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al incertar", ex.toString(), 1, true, 3, "listarMateriaExterna");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_MATERIA_EXTERNA");
                }
            }
        }
    
    return codCarreraExiste;
     }
    
}

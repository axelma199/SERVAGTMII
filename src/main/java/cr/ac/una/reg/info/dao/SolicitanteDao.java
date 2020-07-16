package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.SolicitanteBean;
import cr.ac.una.reg.info.beans.StvnatnBean;
import cr.ac.una.reg.info.sql.SolicitanteSQL;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/*
 * Autor Mauricio Garita
 * Clase la cual registra todos los estudiantes que solicitan una equipracion
 * 
 * 
 */
public class SolicitanteDao extends Connector {

    //Inicializa la conexion de base de datos
    public SolicitanteDao() throws ExceptionConnection {
        this.inicializarDataSource();
    }

    //metodo el cual inserta,actualio y o elimina solicitantes
    public void dmlSolicitante(int p_opcion, SolicitanteBean solicitanteBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(SolicitanteSQL.spDr_regt_solicitante());
                prepareStatement.setInt(1, p_opcion);
                prepareStatement.setString(2, solicitanteBean.getSolte_id());
                prepareStatement.setString(3, solicitanteBean.getSolte_nombre());
                prepareStatement.setString(4, solicitanteBean.getStvnatnBean().getStvnatn_code());
                prepareStatement.setString(5, solicitanteBean.getSolte_direccion());
                prepareStatement.setString(6, solicitanteBean.getSolte_telefono());
                prepareStatement.setString(7, solicitanteBean.getSolte_email());
                prepareStatement.setString(8, solicitanteBean.getGtvzipcCode());
                prepareStatement.setString(9, solicitanteBean.getStvcntyCode());
                prepareStatement.setString(10, solicitanteBean.getSolteApellidos());
                prepareStatement.setString(11, solicitanteBean.getSolteNombre());
                prepareStatement.setString(12, solicitanteBean.getSolteSegNombre());
                prepareStatement.execute();
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            if (sqlex.getErrorCode() == 1) {
                throw new ExceptionConnection("Error, registro ya se encuentra registrado", sqlex.toString(), 1, true, 3, "dmlDR_REGT_SOLICITANTE externa");
            } else {
                throw new ExceptionConnection("1007:Error al insertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_SOLICITANTE");
            }
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al insertar", ex.toString(), 1, true, 3, "dmlDR_REGT_SOLICITANTE");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_SOLICITANTE");
                }
            }
        }
    }

    //Retorna la lista de los solicitante
    public ArrayList<SolicitanteBean> ListarDr_regt_solicitante() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<SolicitanteBean> arraySolicitante = new ArrayList<>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(SolicitanteSQL.lista_Dr_regt_solicitante());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    StvnatnBean stvnatnBean = new StvnatnBean();
                    SolicitanteBean oDr_regt_solicitante = new SolicitanteBean();
                    oDr_regt_solicitante.setSolte_id(resultSet.getString("solte_id"));
                    oDr_regt_solicitante.setSolte_nombre(resultSet.getString("solte_nombre"));
                    stvnatnBean.setStvnatn_code(resultSet.getString("solte_nacionalidad"));
                    stvnatnBean.setStvnatn_nation(resultSet.getString("stvnatn_nation"));
                    oDr_regt_solicitante.setStvnatnBean(stvnatnBean);
                    oDr_regt_solicitante.setSolte_direccion(resultSet.getString("solte_direccion"));
                    oDr_regt_solicitante.setSolte_telefono(resultSet.getString("solte_telefono"));
                    oDr_regt_solicitante.setSolte_email(resultSet.getString("solte_email"));
                    oDr_regt_solicitante.setGtvzipcCode(resultSet.getString("gtvzipc_code"));
                    oDr_regt_solicitante.setStvcntyCode(resultSet.getString("stvcnty_code"));
                    oDr_regt_solicitante.setSolteApellidos(resultSet.getString("solte_apellidos_solicitante"));
                    oDr_regt_solicitante.setSolteNombre(resultSet.getString("solte_nombre_solicitante"));
                    oDr_regt_solicitante.setSolteSegNombre(resultSet.getString("solte_seg_nombre_solicitante"));


                  
                        if (oDr_regt_solicitante.getSolte_nombre() == null) {
                            oDr_regt_solicitante.setSolte_nombre(oDr_regt_solicitante.getSolteApellidos() + ' ' + oDr_regt_solicitante.getSolteNombre() + ' ' + oDr_regt_solicitante.getSolteSegNombre());
                      }
                    
                    arraySolicitante.add(oDr_regt_solicitante);
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al insertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_SOLICITANTE");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al insertar", ex.toString(), 1, true, 3, "dmlDR_REGT_SOLICITANTE");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_SOLICITANTE");
                }
            }
        }
        return arraySolicitante;
    }

    //retorna la informacion de un solicitante en especifico por ID del mismo
    public Boolean buscaSolicitante(SolicitanteBean solicitanteBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        boolean encontrado = false;
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(SolicitanteSQL.buscaSolicitante());
                prepareStatement.setString(1, solicitanteBean.getSolte_id());
                ResultSet resultSet = prepareStatement.executeQuery();
                resultSet.next();
                if (resultSet.getRow() > 0) {
                    StvnatnBean stvnatnBean = new StvnatnBean();
                    solicitanteBean.setSolte_id(resultSet.getString("solte_id"));
                    solicitanteBean.setSolte_nombre(resultSet.getString("solte_nombre"));
                    stvnatnBean.setStvnatn_code(resultSet.getString("solte_nacionalidad"));
                    stvnatnBean.setStvnatn_nation(resultSet.getString("STVNATN_NATION"));
                    solicitanteBean.setStvnatnBean(stvnatnBean);
                    solicitanteBean.setSolte_direccion(resultSet.getString("solte_direccion"));
                    solicitanteBean.setSolte_telefono(resultSet.getString("solte_telefono"));
                    solicitanteBean.setSolte_email(resultSet.getString("solte_email"));
                    solicitanteBean.setGtvzipcCode(resultSet.getString("gtvzipc_code"));
                    solicitanteBean.setStvcntyCode(resultSet.getString("stvcnty_code"));
                    solicitanteBean.setSolteApellidos(resultSet.getString("solte_apellidos_solicitante"));
                    solicitanteBean.setSolteNombre(resultSet.getString("solte_nombre_solicitante"));
                    solicitanteBean.setSolteSegNombre(resultSet.getString("solte_seg_nombre_solicitante"));

                    if (solicitanteBean.getSolte_nombre() == null) {
                        solicitanteBean.setSolte_nombre(solicitanteBean.getSolteApellidos() + ' ' + solicitanteBean.getSolteNombre() + ' ' + solicitanteBean.getSolteSegNombre());
                    }


                    encontrado = true;
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al insertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_SOLICITANTE");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al insertar", ex.toString(), 1, true, 3, "dmlDR_REGT_SOLICITANTE");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_SOLICITANTE");
                }
            }
        }
        return encontrado;
    }
    
    
    
    
    
     //
       public boolean buscarSolicitante(SolicitanteBean universidadBean) throws ExceptionConnection {
       
            PreparedStatement prepareStatement = null;
        
         try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(SolicitanteSQL.BuscarSolicitante(universidadBean.getSolte_id()));
                prepareStatement.setString(1, universidadBean.getSolte_id());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                          return true;
                    
                 } 
            }
        } catch (ExceptionConnection exc) {            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al incertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_SOLICITANTE");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al incertar", ex.toString(), 1, true, 3, "dmlDR_REGT_SOLICITANTE");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "dmlDR_REGT_SOLICITANTE");
                }
            }
        }
            
           
           
            return false;
        
    }
    
         //
       public boolean buscarEquiparaciones( SolicitanteBean solicitanteBean) throws ExceptionConnection {
       
            PreparedStatement prepareStatement = null;
        
         try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(SolicitanteSQL.BuscarEquiparacion(solicitanteBean.getSolte_id()));
                prepareStatement.setString(1, solicitanteBean.getSolte_id());
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

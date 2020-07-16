package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.EquiparacionOficioBean;
import cr.ac.una.reg.info.beans.UniversidadBean;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.EquiparacionOficioSQL;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OraclePreparedStatement;

/*
 * Autor maricio Garita
 * Clase con los metodos necesarios para registrar e insertar las equiparaciones
 * por oficio
 * 
 * 
 */


public class EquiparacionOficioDao extends Connector {
    
    
//Inicializa la conexion con la base de datos
    public EquiparacionOficioDao() throws ExceptionConnection {
        this.inicializarDataSource();
    }

  //Metodo el cual registra actualiza o elimina una equiparacion por oficio
    public void dmlEquiparacionOficio(int p_opcion, EquiparacionOficioBean equiparacionOficioBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(EquiparacionOficioSQL.sp_equiparacion_oficio());
                prepareStatement.setInt(1, p_opcion);
                prepareStatement.setInt(2, equiparacionOficioBean.getEqp_ofi_id());
                prepareStatement.setString(3, equiparacionOficioBean.getEqp_ofi_nombre());
                prepareStatement.setString(4, equiparacionOficioBean.getEqp_ofi_descripcion());
                prepareStatement.setInt(5, equiparacionOficioBean.getUniversidad().getUni_id());
                prepareStatement.setString(6, equiparacionOficioBean.getEqp_ofi_periodo()); 
                prepareStatement.setInt(7, equiparacionOficioBean.getEqp_ofi_estado()); 
                prepareStatement.execute();
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al insertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_EQUIPARACION_OFICIO");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al insertar", ex.toString(), 1, true, 3, "dmlDR_REGT_EQUIPARACION_OFICIO");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_EQUIPARACION_OFICIO");
                }
            }
        }
    }
    
   //Metodo de solo insercion de una equiparacion por ofico
    public void insertaEquiparacionOficio(EquiparacionOficioBean equiparacionOficioBean) throws ExceptionConnection {
       
        CallableStatement prepareStatement=null;
        try {
            if (this.openConnection()) {
                prepareStatement = (CallableStatement) this.getConexion().prepareCall(EquiparacionOficioSQL.fb_equiparacion_oficio());
                //prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(EquiparacionesSQL.fb_inserta_equiparaciones());
                prepareStatement.registerOutParameter(1, java.sql.Types.INTEGER);               
                prepareStatement.setString(2, equiparacionOficioBean.getEqp_ofi_nombre());
                prepareStatement.setString(3, equiparacionOficioBean.getEqp_ofi_descripcion());
                prepareStatement.setInt(4, equiparacionOficioBean.getUniversidad().getUni_id()); 
                prepareStatement.setString(5, equiparacionOficioBean.getEqp_ofi_periodo()); 
                prepareStatement.setInt(6, equiparacionOficioBean.getEqp_ofi_estado()); 
                prepareStatement.execute();
                int resultado = prepareStatement.getInt(1);
                equiparacionOficioBean.setEqp_ofi_id(resultado);
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al insertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_EQUIPARACIONES");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al insertar", ex.toString(), 1, true, 3, "dmlDR_REGT_EQUIPARACIONES");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_EQUIPARACIONES");
                }
            }
        }
    }

    
    //Retorna un listado de todas las equiparaciones por oficio existentes
    public ArrayList<EquiparacionOficioBean> ListarEquiparacionOficio() throws ExceptionConnection {
       
        PreparedStatement prepareStatement = null;
        ArrayList<EquiparacionOficioBean> arrayDr_regt_equiparacion_oficio = new ArrayList<EquiparacionOficioBean>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(EquiparacionOficioSQL.listaEquiparacionOficio());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    EquiparacionOficioBean equiparacionOficioBean = new EquiparacionOficioBean();
                    equiparacionOficioBean.setEqp_ofi_id(resultSet.getInt("eqp_ofi_id"));
                    equiparacionOficioBean.setEqp_ofi_nombre(resultSet.getString("eqp_ofi_nombre"));
                    equiparacionOficioBean.setEqp_ofi_descripcion(resultSet.getString("eqp_ofi_descripcion"));
                      equiparacionOficioBean.setEqp_ofi_annoActual(resultSet.getString("annoActual"));
                      equiparacionOficioBean.setEqp_ofi_periodo(resultSet.getString("eqp_ofi_periodo"));
                    
                      try{
                        equiparacionOficioBean.setEqp_ofi_estado(resultSet.getInt("eqp_ofi_estado"));
                      }catch(NumberFormatException e){
                           equiparacionOficioBean.setEqp_ofi_estado(0);
                            equiparacionOficioBean.setEqp_ofi_descEstado("");
                      }
                       
                  if ( equiparacionOficioBean.getEqp_ofi_estado()!=0){
                      if (equiparacionOficioBean.getEqp_ofi_estado()==1){
                         equiparacionOficioBean.setEqp_ofi_descEstado("Pendiente");
                      }else{
                         equiparacionOficioBean.setEqp_ofi_descEstado("Resuelto");
                      }
                  }
                  
                     
                    UniversidadBean universidadBean = new UniversidadBean();
                    universidadBean.setUni_id(resultSet.getInt("uni_id"));
                    universidadBean.setUni_descripcion(resultSet.getString("uni_descripcion"));
                    equiparacionOficioBean.setUniversidad(universidadBean);
                    arrayDr_regt_equiparacion_oficio.add(equiparacionOficioBean);
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al insertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_EQUIPARACION_OFICIO");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al insertar", ex.toString(), 1, true, 3, "dmlDR_REGT_EQUIPARACION_OFICIO");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_EQUIPARACION_OFICIO");
                }
            }
        }
        return arrayDr_regt_equiparacion_oficio;
    }

  //Busca una equiparacion por oficio en especifico
    public Boolean buscaDr_regt_equiparacion_oficio(EquiparacionOficioBean equiparacionOficioBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        boolean encontrado = false;
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement)this.getConexion().prepareStatement(EquiparacionOficioSQL.buscaDr_regt_equiparacion_oficio());
                ResultSet resultSet = prepareStatement.executeQuery();
                resultSet.next();
                if (resultSet.getRow() > 0) {
                    equiparacionOficioBean.setEqp_ofi_id(resultSet.getInt("eqp_ofi_id"));
                    equiparacionOficioBean.setEqp_ofi_nombre(resultSet.getString("eqp_ofi_nombre"));
                    equiparacionOficioBean.setEqp_ofi_descripcion(resultSet.getString("eqp_ofi_descripcion"));
                    UniversidadBean universidadBean = new UniversidadBean();
                    universidadBean.setUni_id(resultSet.getInt("uni_id"));
                    equiparacionOficioBean.setUniversidad(universidadBean);
                    encontrado = true;
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al insertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_EQUIPARACION_OFICIO");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al insertar", ex.toString(), 1, true, 3, "dmlDR_REGT_EQUIPARACION_OFICIO");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_EQUIPARACION_OFICIO");
                }
            }
        }
        return encontrado;
    }
    
  //metodo que retorna el año en curso de la base de datos 
    //el mismo es necesaario para definir los periodos de las equiparaciones por oficio
    public String cargaAnnoActual() throws ExceptionConnection {
       String annoActual=""; 
       PreparedStatement prepareStatement = null;
        
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement)this.getConexion().prepareStatement(EquiparacionOficioSQL.cargaAnnoActualEqpOficio());
                ResultSet resultSet = prepareStatement.executeQuery();
                resultSet.next();
                if (resultSet.getRow() > 0) {
                    annoActual=resultSet.getString("annoActual");
              
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al insertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_EQUIPARACION_OFICIO");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al insertar", ex.toString(), 1, true, 3, "dmlDR_REGT_EQUIPARACION_OFICIO");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_EQUIPARACION_OFICIO");
                }
            }
        }
        return annoActual;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.EquiparacionesBean;
import cr.ac.una.reg.info.beans.SolicitanteBean;
import cr.ac.una.reg.info.beans.SolicitanteBean;
import cr.ac.una.reg.info.sql.EquiparacionesSQL;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/*
 * Autor mauricio garita
 * Clase con los metodos necesarios para registra y consultar 
 * las equiparaciones
 * 
 */

public class EquiparacionesDao extends Connector {

    
    //Inicializa los parametros de conexion con la base de datos
    public EquiparacionesDao() throws ExceptionConnection {
        this.inicializarDataSource();

    }

  
    //Metodo el cual registra actualiza o elimina una equiparacion normsl
    public void dmlDr_regt_equiparaciones(int p_opcion, EquiparacionesBean equiparacionesBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(EquiparacionesSQL.spDr_regt_equiparaciones());
                prepareStatement.setInt(1, p_opcion);
                prepareStatement.setString(2, equiparacionesBean.getEqp_sol_numero());
                
                
                if(p_opcion==2){
                
                prepareStatement.setTimestamp(3, null);
                prepareStatement.setTimestamp(4, null);
                prepareStatement.setTimestamp(5, null);
                prepareStatement.setString(6, null);
                prepareStatement.setTimestamp(7, null);
                prepareStatement.setString(8, null);
                prepareStatement.setString(9, equiparacionesBean.getEqp_estado());
                prepareStatement.setString(10, equiparacionesBean.getEqp_periodo());
                prepareStatement.setTimestamp(11, null);
                }                    
                else{  

               if (equiparacionesBean.getEqp_fecha_area_rec()!=null){ 
                   prepareStatement.setTimestamp(3, new java.sql.Timestamp(equiparacionesBean.getEqp_fecha_area_rec().getTime()));
               }else{             
                    prepareStatement.setTimestamp(3, null);
               }
               
               if (equiparacionesBean.getEqp_fecha_envio_unidad()!=null){
                   prepareStatement.setTimestamp(4, new java.sql.Timestamp(equiparacionesBean.getEqp_fecha_envio_unidad().getTime()));
                   
               }else{   
                  prepareStatement.setTimestamp(4, null);
               }
                   
               if (equiparacionesBean.getEqp_fecha_recepcion_final()!=null){
                  prepareStatement.setTimestamp(5, new java.sql.Timestamp(equiparacionesBean.getEqp_fecha_recepcion_final().getTime()));
               }else{
                  prepareStatement.setTimestamp(5,null);
               }
                  
                  
               prepareStatement.setString(6, equiparacionesBean.getEqp_numero_sesion());
               
               if (equiparacionesBean.getEqp_fecha_sesion()!=null){
                  prepareStatement.setTimestamp(7, new java.sql.Timestamp(equiparacionesBean.getEqp_fecha_sesion().getTime()));
                }else{     
                  prepareStatement.setTimestamp(7,null);
                }
                            
               
               
               prepareStatement.setString(8, equiparacionesBean.getEqp_observaciones());
                prepareStatement.setString(9, null);
                
                prepareStatement.setString(10, equiparacionesBean.getEqp_periodo());
                
                if (equiparacionesBean.getEqpFechaRecepcionOrer()!=null){
                  prepareStatement.setTimestamp(11, new java.sql.Timestamp(equiparacionesBean.getEqpFechaRecepcionOrer().getTime()));
                }else{     
                  prepareStatement.setTimestamp(11,null);
                }
                
                }
                
                prepareStatement.execute();
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
    
    
  //metodo que solo inserta una equiparacion por oficio
    public void insertaEquiparacion(EquiparacionesBean equiparacionesBean) throws ExceptionConnection {
        //PreparedStatement prepareStatement = null;
        CallableStatement prepareStatement=null;
        try {
            if (this.openConnection()) {
                prepareStatement = (CallableStatement) this.getConexion().prepareCall(EquiparacionesSQL.fb_inserta_equiparaciones());
                //prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(EquiparacionesSQL.fb_inserta_equiparaciones());
                prepareStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
                prepareStatement.setString(2, equiparacionesBean.getSolicitanteBean().getSolte_id());
                prepareStatement.setString(3, equiparacionesBean.getEqp_carrera_continuar_est());
                prepareStatement.setString(4, equiparacionesBean.getEqp_grado_continuar());       
                prepareStatement.setInt(5, equiparacionesBean.getEqpCarreraProcedencia());   
                 prepareStatement.setString(6, equiparacionesBean.getEqpSedeIngresar());      
                prepareStatement.execute();
                String resultado = prepareStatement.getString(1);
                equiparacionesBean.setEqp_sol_numero(resultado);
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

   //retorna la lista de todas las equiparaciones existentes
    public ArrayList<EquiparacionesBean> ListarDr_regt_equiparaciones() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<EquiparacionesBean> arrayDr_regt_equiparaciones = new ArrayList<>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(EquiparacionesSQL.lista_Dr_regt_equiparaciones());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    SolicitanteBean solicitanteBean = new SolicitanteBean();
                    EquiparacionesBean equiparacionesBean = new EquiparacionesBean();
                    equiparacionesBean.setEqp_sol_numero(resultSet.getString("eqp_sol_numero"));
                    solicitanteBean.setSolte_id(resultSet.getString("SOLTE_ID"));
                    solicitanteBean.setSolte_nombre(resultSet.getString("SOLTE_NOMBRE"));    
                    solicitanteBean.setSolteApellidos(resultSet.getString("solte_apellidos_solicitante"));
                    solicitanteBean.setSolteNombre(resultSet.getString("solte_nombre_solicitante"));
                    solicitanteBean.setSolteSegNombre(resultSet.getString("solte_seg_nombre_solicitante"));
                    
                    equiparacionesBean.setSolicitanteBean(solicitanteBean);
                    equiparacionesBean.setEqp_carrera_continuar_est(resultSet.getString("eqp_carrera_continuar_est"));
                    equiparacionesBean.setEqp_grado_continuar(resultSet.getString("eqp_grado_continuar"));
                    equiparacionesBean.setEqp_fecha_atencion_usuario(resultSet.getDate("eqp_fecha_atencion_usuario"));
                    equiparacionesBean.setEqp_fecha_area_rec(resultSet.getDate("eqp_fecha_area_rec"));
                    equiparacionesBean.setEqp_fecha_envio_unidad(resultSet.getDate("eqp_fecha_envio_unidad"));
                    equiparacionesBean.setEqp_fecha_recepcion_final(resultSet.getDate("eqp_fecha_recepcion_final"));
                    equiparacionesBean.setEqp_total_pagar(resultSet.getFloat("eqp_total_pagar"));
                    equiparacionesBean.setEqp_estado(resultSet.getString("eqp_estado"));
                    equiparacionesBean.setEqp_numero_sesion(resultSet.getString("eqp_numero_sesion"));
                    equiparacionesBean.setEqp_fecha_sesion(resultSet.getDate("eqp_fecha_sesion"));
                    equiparacionesBean.setEqp_observaciones(resultSet.getString("eqp_observaciones"));
                    equiparacionesBean.setEqp_periodo(resultSet.getString("eqp_periodo"));
                    equiparacionesBean.setAnnoActual(resultSet.getString("annoActual"));
                    equiparacionesBean.setEqpFechaRecepcionOrer(resultSet.getDate("eqp_fecha_recepcion_orer"));
                     
                     if (equiparacionesBean.getSolicitanteBean().getSolte_nombre()==null){
                        equiparacionesBean.getSolicitanteBean().setSolte_nombre(solicitanteBean.getSolteApellidos()+' '+solicitanteBean.getSolteNombre()+' '+ solicitanteBean.getSolteSegNombre());
                      } 
                     
                    arrayDr_regt_equiparaciones.add(equiparacionesBean);
                }
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
        return arrayDr_regt_equiparaciones;
    }

    //Retorna los datos de una equiparacion en especifico 
    public Boolean buscaDr_regt_equiparaciones(EquiparacionesBean equiparacionesBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        boolean encontrado = false;
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(EquiparacionesSQL.buscaDr_regt_equiparaciones());
                prepareStatement.setString(1, equiparacionesBean.getEqp_sol_numero());
                ResultSet resultSet = prepareStatement.executeQuery();
                resultSet.next();
                if (resultSet.getRow() > 0) {
                    SolicitanteBean solicitanteBean = new SolicitanteBean();
                    equiparacionesBean.setEqp_sol_numero(resultSet.getString("eqp_sol_numero"));
                    solicitanteBean.setSolte_id(resultSet.getString("eqp_sol_numero"));
                    equiparacionesBean.setSolicitanteBean(solicitanteBean);
                    equiparacionesBean.setEqp_carrera_continuar_est(resultSet.getString("eqp_carrera_continuar_est"));
                    equiparacionesBean.setEqp_grado_continuar(resultSet.getString("eqp_grado_continuar"));
                    equiparacionesBean.setEqp_fecha_atencion_usuario(resultSet.getDate("eqp_fecha_atencion_usuario"));
                    equiparacionesBean.setEqp_fecha_area_rec(resultSet.getDate("eqp_fecha_area_rec"));
                    equiparacionesBean.setEqp_fecha_envio_unidad(resultSet.getDate("eqp_fecha_envio_unidad"));
                    equiparacionesBean.setEqp_fecha_recepcion_final(resultSet.getDate("eqp_fecha_recepcion_final"));
                    equiparacionesBean.setEqp_total_pagar(resultSet.getFloat("eqp_total_pagar"));
                    equiparacionesBean.setEqp_estado(resultSet.getString("eqp_estado"));
                    equiparacionesBean.setEqp_numero_sesion(resultSet.getString("eqp_numero_sesion"));
                    equiparacionesBean.setEqp_fecha_sesion(resultSet.getDate("eqp_fecha_sesion"));
                    equiparacionesBean.setEqp_observaciones(resultSet.getString("eqp_observaciones"));
                    encontrado = true;
                }
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
        return encontrado;
    }
}
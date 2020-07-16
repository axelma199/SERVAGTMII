/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.dao;


import cr.ac.una.reg.info.beans.EquiparacionesBean;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.EquiparacionesModificacionDatosSQL;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author N00148096
 */
public class EquiparacionesModificacionDatosDao extends Connector{
    
     public EquiparacionesModificacionDatosDao() throws ExceptionConnection  {
        this.inicializarDataSource();
    }
    
      //Metodo el cual busca una apelacion en especifico  
      public boolean cargaEquiparacion(EquiparacionesBean equiparacionBean) throws ExceptionConnection {
          boolean indicador = false;
          PreparedStatement prepareStatement = null;
        ArrayList<EquiparacionesBean> arrayEquiparacion = new ArrayList<EquiparacionesBean>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(EquiparacionesModificacionDatosSQL.CargaEquiparacion());
                prepareStatement.setString(1, equiparacionBean.getEqp_sol_numero());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    equiparacionBean.setEqp_sol_numero(resultSet.getString("EQP_SOL_NUMERO"));
                    equiparacionBean.setEqpSedeIngresar(resultSet.getString("EQP_SEDE_INGRESAR"));
                    equiparacionBean.setEqp_carrera_continuar_est(resultSet.getString("EQP_CARRERA_CONTINUAR_EST"));
                    equiparacionBean.setEqp_grado_continuar(resultSet.getString("EQP_GRADO_CONTINUAR"));
                    equiparacionBean.setEqpCarreraProcedencia(resultSet.getInt("EQP_CARRERA_PROCEDENCIA"));
                    equiparacionBean.setDescripcionCarreraProcedencia(resultSet.getString("CAR_DESCRIPCION"));
                    equiparacionBean.setUniProcedencia(resultSet.getString("UPROCEDENCIA"));
                    equiparacionBean.getSolicitanteBean().setSolte_id(resultSet.getString("ID"));
                    equiparacionBean.getSolicitanteBean().setSolte_nombre(resultSet.getString("NOMBRE"));
                    indicador = true;
                }//fin del while 
            }//fin de if
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1000:" + "Error al cargar Equiparacion", sqlex.toString(), 1, true, 3, "GtvzipcDaoImpl");
        } catch (Exception ex) {
            throw new ExceptionConnection("1001:" + "Error al cargar Equiparacion", ex.toString(), 1, true, 3, "GtvzipcDaoImpl");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1002:" + "Error al cargar Equiparacion", sqlex.toString(), 1, true, 3, "GtvzipcDaoImpl");
                }//
            }//
        }//
        return indicador;
    } 

       //Procedimiento que Inserta los registros de las apelaciones
    public void ActualizardatosEquiparacion(EquiparacionesBean equiparacionesBean) throws ExceptionConnection {
        CallableStatement prepareStatement = null;
        try {
            if (this.openConnection()) {
                prepareStatement = (CallableStatement) this.getConexion().prepareCall(EquiparacionesModificacionDatosSQL.ActualizarDatosEquiparacion());
                prepareStatement.setString(1,equiparacionesBean.getEqpSedeIngresar() );
                prepareStatement.setString(2, equiparacionesBean.getEqp_carrera_continuar_est());
                prepareStatement.setString(3, equiparacionesBean.getEqp_grado_continuar());
                prepareStatement.setInt(4, equiparacionesBean.getEqpCarreraProcedencia());
                prepareStatement.setString(5, equiparacionesBean.getEqp_sol_numero());
                prepareStatement.execute();
             
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al incertar", sqlex.toString(), 1, true, 3, "DaoImpl");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al incertar", ex.toString(), 1, true, 3, "admisionDaoImpl");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "admisionDaoImpl");
                }
            }
        }
    }

     
}

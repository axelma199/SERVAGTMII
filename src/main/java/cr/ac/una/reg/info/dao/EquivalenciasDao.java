
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.EquivalenciasBean;
import cr.ac.una.reg.info.beans.SolicitanteBean;
import cr.ac.una.reg.info.beans.StvnatnBean;
import cr.ac.una.reg.info.sql.EquivalenciasSQL;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.sql.CallableStatement;
//import java.sql.Date;
//import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mauricio Garita
 *  Esta clase no se esta utilizando devido a que aun no se a implemetado el modulo de equivalencias
 */

public class EquivalenciasDao extends Connector {

    public EquivalenciasDao() throws ExceptionConnection {
        this.inicializarDataSource();
    }

    public void IME_EQUIVALENCIAS(int accion, EquivalenciasBean equivalenciasBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(EquivalenciasSQL.SP_Equivalencias());
                prepareStatement.setInt(1, accion);
                prepareStatement.setString(2, equivalenciasBean.getEqv_sol_numero());
                prepareStatement.setString(3, equivalenciasBean.getSolte_id().getSolte_id());
                prepareStatement.setString(4, equivalenciasBean.getEqv_facultad_continuar());
                prepareStatement.setString(5, equivalenciasBean.getEqv_escuela_continuar());
                prepareStatement.setString(6, equivalenciasBean.getEqv_carrera_continuar_est());
                prepareStatement.setString(7, equivalenciasBean.getEqv_grado_continuar());
                if (accion == 2) {
                    prepareStatement.setTimestamp(8, new java.sql.Timestamp(equivalenciasBean.getEqv_fecha_area_rec().getTime()));
                    prepareStatement.setTimestamp(9, new java.sql.Timestamp(equivalenciasBean.getEqv_fecha_envio_unidad().getTime()));
                    prepareStatement.setTimestamp(10, new java.sql.Timestamp(equivalenciasBean.getEqv_fecha_recepcion_final().getTime()));
                    prepareStatement.setTimestamp(13, new java.sql.Timestamp(equivalenciasBean.getEqv_fecha_sesion().getTime()));
                } else {
                    prepareStatement.setTimestamp(8, null);
                    prepareStatement.setTimestamp(9, null);
                    prepareStatement.setTimestamp(10, null);
                    prepareStatement.setTimestamp(13, null);
                }
                prepareStatement.setString(11, equivalenciasBean.getEqv_estado());
                prepareStatement.setString(12, equivalenciasBean.getEqv_numero_sesion());                
                prepareStatement.setString(14, equivalenciasBean.getEqv_observaciones());
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

    public void insertaEquivalencia(EquivalenciasBean equivalenciasBean) throws ExceptionConnection {
        CallableStatement prepareStatement = null;
        try {
            if (this.openConnection()) {
                prepareStatement = (CallableStatement) this.getConexion().prepareCall(EquivalenciasSQL.SP_INSERTA_EQUIVALENCIAS());
                prepareStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
                prepareStatement.setString(2, equivalenciasBean.getEqv_sol_numero());
                prepareStatement.setString(3, equivalenciasBean.getSolte_id().getSolte_id());
                prepareStatement.setString(4, equivalenciasBean.getEqv_facultad_continuar());
                prepareStatement.setString(5, equivalenciasBean.getEqv_escuela_continuar());
                prepareStatement.setString(6, equivalenciasBean.getEqv_carrera_continuar_est());
                prepareStatement.setString(7, equivalenciasBean.getEqv_grado_continuar());
                prepareStatement.setTimestamp(8, null);
                prepareStatement.setTimestamp(9, null);
                prepareStatement.setTimestamp(10, null);
                prepareStatement.setTimestamp(11, null);
                prepareStatement.setString(12, equivalenciasBean.getEqv_estado());
                prepareStatement.setString(13, equivalenciasBean.getEqv_numero_sesion());
                prepareStatement.setString(14, equivalenciasBean.getEqv_observaciones());
                prepareStatement.execute();
                
                String resultado = prepareStatement.getString(1);
                equivalenciasBean.setEqv_sol_numero(resultado);
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

    public ArrayList<EquivalenciasBean> cargaEquivalencias() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<EquivalenciasBean> arrayEquivalencias = new ArrayList<EquivalenciasBean>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(EquivalenciasSQL.cargaEquivalencias());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    EquivalenciasBean oEquivalencias = new EquivalenciasBean();
                    
                    SolicitanteBean oSolicitante = new SolicitanteBean();
                    oSolicitante.setSolte_id(resultSet.getString("SOLTE_ID"));
                    oSolicitante.setSolte_nombre(resultSet.getString("SOLTE_NOMBRE"));
                    
                    oEquivalencias.setEqv_sol_numero(resultSet.getString("EQV_SOL_NUMERO"));
                    oEquivalencias.setEqv_estado(resultSet.getString("EQV_ESTADO"));
                    oEquivalencias.setEqv_escuela_continuar(resultSet.getString("EQV_ESCUELA_CONTINUAR"));                    
                    oEquivalencias.setSolte_id(oSolicitante);
                    arrayEquivalencias.add(oEquivalencias);
                }//fin del while
            }//fin de if
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1000:" + "Error al cargar Titulos", sqlex.toString(), 1, true, 3, "GtvzipcDaoImpl");
        } catch (Exception ex) {
            throw new ExceptionConnection("1001:" + "Error al cargar Titulos", ex.toString(), 1, true, 3, "GtvzipcDaoImpl");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1002:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "GtvzipcDaoImpl");
                }//
            }//
        }//
        return arrayEquivalencias;
    }

    public EquivalenciasBean buscarEquivalenciasNum(EquivalenciasBean equivalenciasBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        EquivalenciasBean oEquivalencias = new EquivalenciasBean();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(EquivalenciasSQL.CMD_CONSULTA_EQUIVALENCIAS());
                prepareStatement.setString(1, equivalenciasBean.getEqv_sol_numero());
                ResultSet resultSet = prepareStatement.executeQuery();

                while (resultSet.next()) {
                    SolicitanteBean oSolicitante = new SolicitanteBean();
                    oSolicitante.setSolte_id(resultSet.getString("SOLTE_ID"));
                    oSolicitante.setSolte_nombre(resultSet.getString("SOLTE_NOMBRE"));
                    StvnatnBean pais = new StvnatnBean();
                    pais.setStvnatn_code(resultSet.getString("SOLTE_TELEFONO"));
                    oSolicitante.setStvnatnBean(pais);
                    oSolicitante.setSolte_telefono(resultSet.getString("SOLTE_TELEFONO"));
                    oSolicitante.setSolte_direccion(resultSet.getString("SOLTE_DIRECCION"));
                    oSolicitante.setSolte_email(resultSet.getString("SOLTE_EMAIL"));

                    oEquivalencias.setEqv_sol_numero(resultSet.getString("EQV_SOL_NUMERO"));
                    oEquivalencias.setSolte_id(oSolicitante);
                    oEquivalencias.setEqv_facultad_continuar(resultSet.getString("EQV_FACULTAD_CONTINUAR"));
                    oEquivalencias.setEqv_escuela_continuar(resultSet.getString("EQV_ESCUELA_CONTINUAR"));
                    oEquivalencias.setEqv_carrera_continuar_est(resultSet.getString("EQV_CARRERA_CONTINUAR_EST"));
                    oEquivalencias.setEqv_grado_continuar(resultSet.getString("EQV_GRADO_CONTINUAR"));
                    oEquivalencias.setEqv_fecha_area_rec(resultSet.getDate("EQV_FECHA_AREA_REC"));
                    oEquivalencias.setEqv_fecha_envio_unidad(resultSet.getDate("EQV_FECHA_ENVIO_UNIDAD"));
                    oEquivalencias.setEqv_fecha_recepcion_final(resultSet.getDate("EQV_FECHA_RECEPCION_FINAL"));
                    oEquivalencias.setEqv_estado(resultSet.getString("EQV_ESTADO"));
                    oEquivalencias.setEqv_numero_sesion(resultSet.getString("EQV_NUMERO_SESION"));
                    oEquivalencias.setEqv_fecha_sesion(resultSet.getDate("EQV_FECHA_SESION"));
                    oEquivalencias.setEqv_observaciones(resultSet.getString("EQV_OBSERVACIONES"));
                }
                //fin del while
            }//fin de if
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1000:" + "Error al cargar Titulos", sqlex.toString(), 1, true, 3, "GtvzipcDaoImpl");
        } catch (Exception ex) {
            throw new ExceptionConnection("1001:" + "Error al cargar Titulos", ex.toString(), 1, true, 3, "GtvzipcDaoImpl");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1002:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "GtvzipcDaoImpl");
                }//
            }//
        }//
        return oEquivalencias;
    }

}

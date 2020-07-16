/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.dao;
import cr.ac.una.reg.info.beans.BitacoraMateriasBean;
import cr.ac.una.reg.info.sql.BitacoraMateriasSQL;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author JGA
 * REVIZAR SI APLICA AL MODULO DE EQUIVALENCIAS
 * 
 */
public class BitacoraMateriasDao extends Connector {

    //public Bitacora_materiasDao() {
    //}

    
    //
    public void IME_BITACORA_MATERIAS(BitacoraMateriasBean bitacora_materiasBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(BitacoraMateriasSQL.SP_BITACORA_MATERIAS());
                prepareStatement.setInt(1, 1);
                prepareStatement.setString(2, bitacora_materiasBean.getBitam_id_usuario());
                prepareStatement.setString(3, bitacora_materiasBean.getBitam_usuario());
                prepareStatement.setString(4, bitacora_materiasBean.getBitam_transaccion());
                prepareStatement.setString(5, bitacora_materiasBean.getBitam_tabla_afectada());
                prepareStatement.setString(6, bitacora_materiasBean.getBitam_numero_solicitud());
                prepareStatement.setString(7, bitacora_materiasBean.getBitam_solicitud_tipo());
                prepareStatement.setString(8, bitacora_materiasBean.getBitam_solicitante_id());
                prepareStatement.setString(9, bitacora_materiasBean.getBitam_nomb_solicitante());
                prepareStatement.setString(10, bitacora_materiasBean.getBitam_mat_modificada());
                prepareStatement.setString(11, bitacora_materiasBean.getBitam_mat_estado());
                prepareStatement.setDate(12, bitacora_materiasBean.getBitam_fecha());
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
    
        public ArrayList<BitacoraMateriasBean> cargaBitacoraMaterias() throws ExceptionConnection {
          PreparedStatement prepareStatement = null;
        ArrayList<BitacoraMateriasBean> arrayBitacoraMaterias = new ArrayList<BitacoraMateriasBean>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(BitacoraMateriasSQL.cargaBitacoraMaterias());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    BitacoraMateriasBean oBitacoraMaterias = new BitacoraMateriasBean();
                    oBitacoraMaterias.setBitam_id_usuario(resultSet.getString("BITA_ID_USUARIO"));
                    oBitacoraMaterias.setBitam_usuario(resultSet.getString("BITA_USUARIO"));
                    oBitacoraMaterias.setBitam_transaccion(resultSet.getString("BITA_TRANSACCION"));
                    oBitacoraMaterias.setBitam_tabla_afectada(resultSet.getString("BITA_TABLA_AFECTADA"));
                    oBitacoraMaterias.setBitam_numero_solicitud(resultSet.getString("BITA_NUMERO_SOLICITUD"));
                    oBitacoraMaterias.setBitam_solicitud_tipo(resultSet.getString("BITA_SOLICITUD_TIPO"));
                    oBitacoraMaterias.setBitam_solicitante_id(resultSet.getString("BITA_SOLICITANTE_ID"));
                    oBitacoraMaterias.setBitam_nomb_solicitante(resultSet.getString("BITA_NOMB_SOLICITANTE"));
                    oBitacoraMaterias.setBitam_mat_modificada(resultSet.getString("BITA_NOMB_SOLICITANTE"));
                    oBitacoraMaterias.setBitam_mat_estado(resultSet.getString("BITA_NOMB_SOLICITANTE"));
                    oBitacoraMaterias.setBitam_fecha(resultSet.getDate("BITA_FECHA"));
                    arrayBitacoraMaterias.add(oBitacoraMaterias);
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
        return arrayBitacoraMaterias;
    }
        
        
        public ArrayList<BitacoraMateriasBean>buscarBitacoraMateriasId (BitacoraMateriasBean bitacoraMateriasBean) throws ExceptionConnection {
          PreparedStatement prepareStatement = null;
        ArrayList<BitacoraMateriasBean> arrayBitacoraMaterias = new ArrayList<BitacoraMateriasBean>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(BitacoraMateriasSQL.buscaBitacoraMaterias_Id());
                prepareStatement.setString(1, bitacoraMateriasBean.getBitam_id_usuario());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    BitacoraMateriasBean oBitacoraMaterias = new BitacoraMateriasBean();
                    oBitacoraMaterias.setBitam_id_usuario(resultSet.getString("BITA_ID_USUARIO"));
                    oBitacoraMaterias.setBitam_usuario(resultSet.getString("BITA_USUARIO"));
                    oBitacoraMaterias.setBitam_transaccion(resultSet.getString("BITA_TRANSACCION"));
                    oBitacoraMaterias.setBitam_tabla_afectada(resultSet.getString("BITA_TABLA_AFECTADA"));
                    oBitacoraMaterias.setBitam_numero_solicitud(resultSet.getString("BITA_NUMERO_SOLICITUD"));
                    oBitacoraMaterias.setBitam_solicitud_tipo(resultSet.getString("BITA_SOLICITUD_TIPO"));
                    oBitacoraMaterias.setBitam_solicitante_id(resultSet.getString("BITA_SOLICITANTE_ID"));
                    oBitacoraMaterias.setBitam_nomb_solicitante(resultSet.getString("BITA_NOMB_SOLICITANTE"));
                    oBitacoraMaterias.setBitam_mat_modificada(resultSet.getString("BITA_NOMB_SOLICITANTE"));
                    oBitacoraMaterias.setBitam_mat_estado(resultSet.getString("BITA_NOMB_SOLICITANTE"));
                    oBitacoraMaterias.setBitam_fecha(resultSet.getDate("BITA_FECHA"));
                    arrayBitacoraMaterias.add(oBitacoraMaterias);
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
        return arrayBitacoraMaterias;
    }

}

package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.BitacoraSolicitudBean;
import cr.ac.una.reg.info.beans.Dr_siseg_usuarioBean;
import cr.ac.una.reg.info.sql.BitacoraSolicitudSQL;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/*
 * Clase con los metodos necesarios para registrarlos el historico de los movimientos
 * de las solicitudes de las equiparaciones
 * 
 */

public class BitacoraSolicitudDao extends Connector {

   
    //metodo de inicializacion de conexion a la base de datos
    public BitacoraSolicitudDao() throws ExceptionConnection {
        this.inicializarDataSource();

    }

    
    //Inserta todos los movientos de una solicitud de equiparacion
    public void dmlDr_regt_bitacora_solicitud(BitacoraSolicitudBean bitacoraSolicitudBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(BitacoraSolicitudSQL.spDr_regt_bitacora_solicitud());
                Dr_siseg_usuarioBean usuarioBean = new Dr_siseg_usuarioBean();
                
                prepareStatement.setString(1, bitacoraSolicitudBean.getBit_sol_id());
                prepareStatement.setString(2, bitacoraSolicitudBean.getBit_tipo_movimiento());                
                prepareStatement.setString(3, bitacoraSolicitudBean.getUsuarioBean().getCodigo());                
                prepareStatement.setString(4, bitacoraSolicitudBean.getBit_detalle());
                prepareStatement.execute();
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al insertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_BITACORA_SOLICITUD");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al insertar", ex.toString(), 1, true, 3, "dmlDR_REGT_BITACORA_SOLICITUD");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_BITACORA_SOLICITUD");
                }
            }
        }
    }

    //retorna todos los movimiento realizados a las equiparaciones
    public ArrayList<BitacoraSolicitudBean> ListarDr_regt_bitacora_solicitud() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<BitacoraSolicitudBean> arrayDr_regt_bitacora_solicitud = new ArrayList<BitacoraSolicitudBean>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(BitacoraSolicitudSQL.lista_Dr_regt_bitacora_solicitud());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                  BitacoraSolicitudBean bitacoraSolicitud = new BitacoraSolicitudBean();
                    Dr_siseg_usuarioBean usuarioBean=new  Dr_siseg_usuarioBean();
                    bitacoraSolicitud.setBit_id(resultSet.getInt("bit_id"));
                    bitacoraSolicitud.setBit_sol_id(resultSet.getString("bit_sol_id"));
                    bitacoraSolicitud.setBit_tipo_movimiento(resultSet.getString("bit_tipo_movimiento"));
                    usuarioBean.setCodigo(resultSet.getString("bit_id_usuario"));
                    usuarioBean.setDescripcion(resultSet.getString("descripcion"));
                    bitacoraSolicitud.setUsuarioBean(usuarioBean);
                     bitacoraSolicitud.setBit_nom_usuario(resultSet.getString("descripcion"));
                    bitacoraSolicitud.setBit_fecha(resultSet.getDate("bit_fecha"));
                    bitacoraSolicitud.setBit_detalle(resultSet.getString("bit_detalle"));
                    arrayDr_regt_bitacora_solicitud.add(bitacoraSolicitud);
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al insertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_BITACORA_SOLICITUD");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al insertar", ex.toString(), 1, true, 3, "dmlDR_REGT_BITACORA_SOLICITUD");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_BITACORA_SOLICITUD");
                }
            }
        }
        return arrayDr_regt_bitacora_solicitud;
    }

   
}

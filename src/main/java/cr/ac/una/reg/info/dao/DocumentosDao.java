package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.DocumentosBean;
import cr.ac.una.reg.info.sql.DocumentosSQL;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Clase con el llamado a los metodos de Listado, Insercion, moficacion y eliminacion 
 * de los documentos requeridos para una equiparacion
  * 
 */
public class DocumentosDao extends Connector {

 //metodo que inicializa la conexion con la base de datos
    public DocumentosDao() throws ExceptionConnection {
        this.inicializarDataSource();

    }
    
    //Llamada para los metodos de insercion, eliminacion y modificacion de documentos
    public void dmlDocumentos(int p_opcion, DocumentosBean documentosBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(DocumentosSQL.spDocumentos());
                prepareStatement.setInt(1, p_opcion);
                prepareStatement.setString(2, documentosBean.getDoc_id());
                prepareStatement.setString(3, documentosBean.getDoc_descripcion());
                prepareStatement.setBoolean(4, documentosBean.getDoc_eqp());
                prepareStatement.setBoolean(5, documentosBean.getDoc_eqv());
                prepareStatement.execute();
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al insertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_DOCUMENTOS");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al insertar", ex.toString(), 1, true, 3, "dmlDR_REGT_DOCUMENTOS");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_DOCUMENTOS");
                }
            }
        }
    }

    
    //retorna un listado de todos los documentos requeridos para una equiparacion
    public ArrayList<DocumentosBean> ListarDocumentos() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<DocumentosBean> arrayDocumentos = new ArrayList<DocumentosBean>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(DocumentosSQL.listaDocumentos());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    DocumentosBean documentos = new DocumentosBean();
                    documentos.setDoc_id(resultSet.getString("doc_id"));
                    documentos.setDoc_descripcion(resultSet.getString("doc_descripcion"));
                    documentos.setDoc_eqp(resultSet.getBoolean("doc_eqp"));
                    documentos.setDoc_eqv(resultSet.getBoolean("doc_eqv"));
                    arrayDocumentos.add(documentos);
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al insertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_DOCUMENTOS");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al insertar", ex.toString(), 1, true, 3, "dmlDR_REGT_DOCUMENTOS");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_DOCUMENTOS");
                }
            }
        }
        return arrayDocumentos;
    }

    //retorna los registros de un documentos en especifico
    public Boolean buscaDr_regt_documentos(DocumentosBean documentosBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        boolean encontrado = false;
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(DocumentosSQL.buscaDocumentos());
                prepareStatement.setString(1, documentosBean.getDoc_id());
                ResultSet resultSet = prepareStatement.executeQuery();
                resultSet.next();
                if (resultSet.getRow() > 0) {
                    documentosBean.setDoc_id(resultSet.getString("doc_id"));
                    documentosBean.setDoc_descripcion(resultSet.getString("doc_descripcion"));
                    documentosBean.setDoc_eqp(resultSet.getBoolean("doc_eqp"));
                    documentosBean.setDoc_eqv(resultSet.getBoolean("doc_eqv"));
                    encontrado = true;
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al insertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_DOCUMENTOS");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al insertar", ex.toString(), 1, true, 3, "dmlDR_REGT_DOCUMENTOS");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_DOCUMENTOS");
                }
            }
        }
        return encontrado;
    }
    
  //consulta los documentos en un estado en especifico  
    public ArrayList<DocumentosBean> ConsultaDocumentos() throws SQLException, ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<DocumentosBean> arrayDocumento = new ArrayList<DocumentosBean>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(DocumentosSQL.CMD_CONSULTA_DOCUMENTOS());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    DocumentosBean oDocumento = new DocumentosBean();
                    oDocumento.setDoc_id(resultSet.getString("DOC_ID"));
                    oDocumento.setDoc_descripcion(resultSet.getString("DOC_DESCRIPCION"));
                    arrayDocumento.add(oDocumento);
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
        return arrayDocumento;
    }
}

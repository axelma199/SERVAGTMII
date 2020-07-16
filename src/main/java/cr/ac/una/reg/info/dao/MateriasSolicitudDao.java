package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.Dr_regt_historialBean;
import cr.ac.una.reg.info.beans.MateriasSolicitudBean;
import cr.ac.una.reg.info.sql.MateriasSolicitudSQL;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Esta clase pertenece al modulo de equivalencia de materias el cual no se encuentra en produccion
 * 
 * 
 */

public class MateriasSolicitudDao extends Connector {

    public MateriasSolicitudDao() throws ExceptionConnection {
        this.inicializarDataSource();

    }

    public void dmlMateriasSolicitud(int p_opcion, MateriasSolicitudBean materiasSolicitudBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(MateriasSolicitudSQL.spMatSol());
                prepareStatement.setInt(1, p_opcion);
                prepareStatement.setString(2, materiasSolicitudBean.getMat_sol_numero());
                prepareStatement.setString(3, materiasSolicitudBean.getMat_sol_reconocer());
                prepareStatement.setString(4, materiasSolicitudBean.getMat_sol_reconocer_nom());
                prepareStatement.setInt(5, materiasSolicitudBean.getMat_sol_creditos());
                prepareStatement.setString(6, materiasSolicitudBean.getMat_sol_equivalente());
                prepareStatement.setString(7, materiasSolicitudBean.getMat_sol_equivalente_nom());
                prepareStatement.setInt(8, materiasSolicitudBean.getMat_sol_creditos_eqv());
                prepareStatement.setString(9, materiasSolicitudBean.getMat_sol_condicion());
                prepareStatement.execute();
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al insertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_MATERIAS_SOLICITUD");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al insertar", ex.toString(), 1, true, 3, "dmlDR_REGT_MATERIAS_SOLICITUD");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_MATERIAS_SOLICITUD");
                }
            }
        }
    }

    public void IME_Materias(int accion, MateriasSolicitudBean materia) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        try {
            if (this.openConnection()) {
                for (int i = 0; i < materia.getMateriasEquivaler().size(); i++) {
                    prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(MateriasSolicitudSQL.spMatSol());
                    prepareStatement.setInt(1, accion);
                    prepareStatement.setString(2, materia.getMat_sol_numero());
                    prepareStatement.setString(3, materia.getMat_sol_reconocer());
                    prepareStatement.setString(4, materia.getMat_sol_reconocer_nom());
                    prepareStatement.setInt(5, materia.getMat_sol_creditos());
                    prepareStatement.setString(6, materia.getMateriasEquivaler().get(i).getCodigo());
                    prepareStatement.setString(7, materia.getMateriasEquivaler().get(i).getNombre());
                    prepareStatement.setInt(8, materia.getMateriasEquivaler().get(i).getCreditos());
                    prepareStatement.setString(9, materia.getMat_sol_condicion());
                    prepareStatement.execute();
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al insertar", sqlex.toString(), 1, true, 3, "DaoImpl");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al insertar", ex.toString(), 1, true, 3, "MateriasSolicitudnDaoImpl");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "MateriasSolicitudnDaoImpl");
                }
            }
        }
    }

    public ArrayList<MateriasSolicitudBean> cargaMateriasReconocer(String facultadContinuar) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<MateriasSolicitudBean> arrayMaterias = new ArrayList<MateriasSolicitudBean>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(MateriasSolicitudSQL.CargaMaterias());
                prepareStatement.setString(1, facultadContinuar);
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    MateriasSolicitudBean oMateria = new MateriasSolicitudBean();
                    oMateria.setMat_sol_reconocer(resultSet.getString("SCBCRSE_SUBJ_CODE") + resultSet.getString("SCBCRSE_CRSE_NUMB"));
                    oMateria.setMat_sol_reconocer_nom(resultSet.getString("SCBCRSE_TITLE"));
                    oMateria.setMat_sol_creditos(resultSet.getInt("SCBCRSE_CREDIT_HR_LOW"));
                    arrayMaterias.add(oMateria);
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
        return arrayMaterias;
    }
    

    public ArrayList<MateriasSolicitudBean> ListarDr_regt_materias_solicitud() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<MateriasSolicitudBean> arrayDr_regt_materias_solicitud = new ArrayList<>();
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(MateriasSolicitudSQL.buscaMatSolEqv());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    MateriasSolicitudBean materiasSolicitud = new MateriasSolicitudBean();
                    materiasSolicitud.setMat_sol_numero(resultSet.getString("mat_sol_numero"));
                    materiasSolicitud.setMat_sol_reconocer(resultSet.getString("mat_sol_reconocer"));
                    materiasSolicitud.setMat_sol_reconocer_nom(resultSet.getString("mat_sol_reconocer_nom"));
                    materiasSolicitud.setMat_sol_creditos(resultSet.getInt("mat_sol_creditos"));
                    materiasSolicitud.setMat_sol_equivalente(resultSet.getString("mat_sol_equivalente"));
                    materiasSolicitud.setMat_sol_equivalente_nom(resultSet.getString("mat_sol_equivalente_nom"));
                    materiasSolicitud.setMat_sol_creditos_eqv(resultSet.getInt("mat_sol_creditos_eqv"));
                    materiasSolicitud.setMat_sol_condicion(resultSet.getString("mat_sol_condicion"));
                    arrayDr_regt_materias_solicitud.add(materiasSolicitud);
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al insertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_MATERIAS_SOLICITUD");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al insertar", ex.toString(), 1, true, 3, "dmlDR_REGT_MATERIAS_SOLICITUD");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_MATERIAS_SOLICITUD");
                }
            }
        }
        return arrayDr_regt_materias_solicitud;
    }

    public Boolean buscaMateriasSolicitud(MateriasSolicitudBean materiasSolicitudBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        boolean encontrado = false;
        try {
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(MateriasSolicitudSQL.buscaDr_regt_materias_solicitud());
                prepareStatement.setString(1, materiasSolicitudBean.getMat_sol_numero());
                prepareStatement.setString(2, materiasSolicitudBean.getMat_sol_reconocer());
                prepareStatement.setString(3, materiasSolicitudBean.getMat_sol_equivalente());
                ResultSet resultSet = prepareStatement.executeQuery();
                resultSet.next();
                if (resultSet.getRow() > 0) {
                    materiasSolicitudBean.setMat_sol_numero(resultSet.getString("mat_sol_numero"));
                    materiasSolicitudBean.setMat_sol_reconocer(resultSet.getString("mat_sol_reconocer"));
                    materiasSolicitudBean.setMat_sol_reconocer_nom(resultSet.getString("mat_sol_reconocer_nom"));
                    materiasSolicitudBean.setMat_sol_creditos(resultSet.getInt("mat_sol_creditos"));
                    materiasSolicitudBean.setMat_sol_equivalente(resultSet.getString("mat_sol_equivalente"));
                    materiasSolicitudBean.setMat_sol_equivalente_nom(resultSet.getString("mat_sol_equivalente_nom"));
                    materiasSolicitudBean.setMat_sol_creditos_eqv(resultSet.getInt("mat_sol_creditos_eqv"));
                    materiasSolicitudBean.setMat_sol_condicion(resultSet.getString("mat_sol_condicion"));
                    encontrado = true;
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1007:Error al insertar", sqlex.toString(), 1, true, 3, "dmlDR_REGT_MATERIAS_SOLICITUD");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:Error al insertar", ex.toString(), 1, true, 3, "dmlDR_REGT_MATERIAS_SOLICITUD");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "DR_REGT_MATERIAS_SOLICITUD");
                }
            }
        }
        return encontrado;
    }

    public ArrayList<MateriasSolicitudBean> cargaDetalle(String equivalencia) throws ExceptionConnection {
        String materiaAux="";
        PreparedStatement prepareStatement = null;
        ArrayList<MateriasSolicitudBean> arrayMaterias = new ArrayList<>();
        try {
            if (this.openConnection()) {
              
                
                
                
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(MateriasSolicitudSQL.CargaDetalleEquivalencias());
                prepareStatement.setString(1, equivalencia);
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    MateriasSolicitudBean oMateria = new MateriasSolicitudBean();
                  
              
                    oMateria.setMat_sol_reconocer(resultSet.getString("MAT_SOL_RECONOCER"));
                    oMateria.setMat_sol_reconocer_nom(resultSet.getString("MAT_SOL_RECONOCER_NOM"));
                    oMateria.setMat_sol_creditos(resultSet.getInt("MAT_SOL_CREDITOS"));
                    oMateria.setMat_sol_condicion(resultSet.getString("MAT_SOL_CONDICION"));
                   // oMateria.setMat_sol_equivalente(resultSet.getString("MAT_SOL_EQUIVALENTE"));
                    
                   
                    prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(MateriasSolicitudSQL.CargaDetalleMaterias());
                    prepareStatement.setString(1, equivalencia);
                    prepareStatement.setString(2, oMateria.getMat_sol_reconocer());
                    ResultSet resultSet2 = prepareStatement.executeQuery();
                    while (resultSet2.next()) {
                        Dr_regt_historialBean oMateria2 = new Dr_regt_historialBean();
                        oMateria2.setCodigo(resultSet2.getString("MAT_SOL_EQUIVALENTE"));
                        oMateria2.setNombre(resultSet2.getString("MAT_SOL_EQUIVALENTE_NOM"));
                        oMateria2.setCreditos(resultSet2.getInt("MAT_SOL_CREDITOS_EQV"));
                        oMateria.getMateriasEquivaler().add(oMateria2);
                         if (  resultSet.getString("MAT_SOL_RECONOCER").substring(0, 2).equals("EG")){      
                             arrayMaterias.add(oMateria);
                             oMateria = new MateriasSolicitudBean();
                             oMateria.setMat_sol_reconocer(resultSet.getString("MAT_SOL_RECONOCER"));
                             oMateria.setMat_sol_reconocer_nom(resultSet.getString("MAT_SOL_RECONOCER_NOM"));
                             oMateria.setMat_sol_creditos(resultSet.getInt("MAT_SOL_CREDITOS"));
                             oMateria.setMat_sol_condicion(resultSet.getString("MAT_SOL_CONDICION"));
                             //oMateria.getMateriasEquivaler().add(oMateria2);
                             //oMateria.setMat_sol_equivalente(resultSet.getString("MAT_SOL_EQUIVALENTE"));
                         
                         }
                         }
                    
                    if (! resultSet.getString("MAT_SOL_RECONOCER").substring(0, 2).equals("EG")){     
                        arrayMaterias.add(oMateria);
                    }
                    
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
        return arrayMaterias;
    }
}

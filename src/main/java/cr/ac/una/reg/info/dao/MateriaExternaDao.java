package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.CarreraExternaBean;
import cr.ac.una.reg.info.beans.MateriaExternaBean;
import cr.ac.una.reg.info.beans.StvnatnBean;
import cr.ac.una.reg.info.beans.UniversidadBean;
import cr.ac.una.reg.info.sql.MateriaExternaSQL;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Autor Mauricio Garita
 * Esta clase contiene los metodos para registrar las materias de otras universidades 
 * 
 */

public class MateriaExternaDao extends Connector {

    
    //inicializa la conexion con la base de datos
    public MateriaExternaDao() throws ExceptionConnection {
        this.inicializarDataSource();
    }

    //metodo el cual se encarga de incluir, modifica y eliminar las materias externas
    public void dmlMateriaExterna(int p_opcion, MateriaExternaBean materiaExternaBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        try {        
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(MateriaExternaSQL.spDr_regt_materia_externa());
                prepareStatement.setInt(1, p_opcion);
                prepareStatement.setInt(2, materiaExternaBean.getMat_ext_id());
                prepareStatement.setString(3, materiaExternaBean.getMat_ext_codigo());
                
              //  if (materiaExternaBean.getCarreraExterna()!=null){
                prepareStatement.setInt(4, Integer.parseInt(materiaExternaBean.getMat_ext_uni_id()));
               // }else{
               //  prepareStatement.setInt(4, 0);
                //}
                prepareStatement.setString(5, materiaExternaBean.getMat_ext_descripcion());
                prepareStatement.setString(6, materiaExternaBean.getMat_ext_oficio());
                prepareStatement.execute();
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            if (sqlex.getErrorCode() == 1) {
                throw new ExceptionConnection("Error, registro ya se encuentra registrado", sqlex.toString(), 1, true, 3, "dmlDR_REGT_MATERIA_EXTERNA");
            } else {
                throw new ExceptionConnection("1007:Error", sqlex.toString(), 1, true, 3, "dmlDR_REGT_MATERIA_EXTERNA");
            }
        } catch (Exception ex) {

            throw new ExceptionConnection(ex.getMessage(), ex.toString(), 1, true, 3, "dmlDR_REGT_MATERIA_EXTERNA");

        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:Error al cerrar statement", sqlex.toString(), 1, true, 3, "dmlDR_REGT_MATERIA_EXTERNA");
                }
            }
        }
    }

  
    //Retorna la lista de materias externas registradas
    public ArrayList<MateriaExternaBean> ListarMateriaExterna(int codCarrera) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<MateriaExternaBean> arrayMateriaExterna = new ArrayList<>();
        try {
            if (this.openConnection()) {
                if (codCarrera==0) {
                    prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(MateriaExternaSQL.listarMateriaExterna());
                } else {
                    prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(MateriaExternaSQL.listarMateriaExternaxCarrera());
                    prepareStatement.setInt(1, codCarrera);
                }
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    StvnatnBean stvnatnBean = new StvnatnBean();
                    MateriaExternaBean omateria = new MateriaExternaBean();
                    CarreraExternaBean ocarrera = new CarreraExternaBean();
                    UniversidadBean ouniversidad = new UniversidadBean();

                    omateria.setMat_ext_id(resultSet.getInt("mat_ext_id"));
                    omateria.setMat_ext_codigo(resultSet.getString("mat_ext_codigo"));
                    omateria.setMat_ext_descripcion(resultSet.getString("mat_ext_descripcion"));
                    omateria.setMat_ext_oficio(resultSet.getString("mat_ext_oficio"));
                    omateria.setMat_ext_uni_id(resultSet.getString("mat_ext_uni_id"));
                   

//                    ocarrera.setCar_id(resultSet.getInt("car_id"));
//                    ocarrera.setCar_descripcion(resultSet.getString("car_descripcion"));

                    stvnatnBean.setStvnatn_code(resultSet.getString("stvnatn_code"));
                    stvnatnBean.setStvnatn_nation(resultSet.getString("stvnatn_nation"));

                    ouniversidad.setUni_id(resultSet.getInt("uni_id"));
                    ouniversidad.setUni_descripcion(resultSet.getString("uni_descripcion"));

                    ouniversidad.setStvnatn(stvnatnBean);
                    ocarrera.setUniversidad(ouniversidad);
                    omateria.setCarreraExterna(ocarrera);

                    arrayMateriaExterna.add(omateria);
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
        return arrayMateriaExterna;
    }
    
    
    
    
    
    
    
  //Retorna la lista de materias externas pertenecientes a las equiparaciones por oficio  
    public ArrayList<MateriaExternaBean> ListarMateriaExternaPorOficio(int codCarrera) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<MateriaExternaBean> arrayMateriaExterna = new ArrayList<>();
        try {
            if (this.openConnection()) {
                
                    prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(MateriaExternaSQL.listarMateriaExternaPorOficio());
                    prepareStatement.setInt(1, codCarrera);
                
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    StvnatnBean stvnatnBean = new StvnatnBean();
                    MateriaExternaBean omateria = new MateriaExternaBean();
                    CarreraExternaBean ocarrera = new CarreraExternaBean();
                    UniversidadBean ouniversidad = new UniversidadBean();

                    omateria.setMat_ext_id(resultSet.getInt("mat_ext_id"));
                    omateria.setMat_ext_codigo(resultSet.getString("mat_ext_codigo"));
                    omateria.setMat_ext_descripcion(resultSet.getString("mat_ext_descripcion"));
                    omateria.setMat_ext_oficio(resultSet.getString("mat_ext_oficio"));

//                    ocarrera.setCar_id(resultSet.getInt("car_id"));
//                    ocarrera.setCar_descripcion(resultSet.getString("car_descripcion"));
                    
//                    ocarrera.setCar_id(resultSet.getInt(""));
//                    ocarrera.setCar_descripcion(resultSet.getString(""));


                    stvnatnBean.setStvnatn_code(resultSet.getString("stvnatn_code"));
                    stvnatnBean.setStvnatn_nation(resultSet.getString("stvnatn_nation"));

                    ouniversidad.setUni_id(resultSet.getInt("uni_id"));
                    ouniversidad.setUni_descripcion(resultSet.getString("uni_descripcion"));

                    ouniversidad.setStvnatn(stvnatnBean);
                    ocarrera.setUniversidad(ouniversidad);
                    omateria.setCarreraExterna(ocarrera);

                    arrayMateriaExterna.add(omateria);
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
        return arrayMateriaExterna;
    }
    
    
    
  //metodo que asiga un consecutivo unico a la hora de registra una materia que no pertenece a la univerisidad  
     public int BuscaConsecutivoMateriaExternaExtrangero() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<MateriaExternaBean> arrayMateriaExterna = new ArrayList<>();
        int consecutivoCME=0;
        try {
            if (this.openConnection()) {
             
                    prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(MateriaExternaSQL.BuscarConsecutivoMateriaExternaExtrangero());
             
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                     consecutivoCME=resultSet.getInt("consecutivoCME");
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
        return  consecutivoCME;
    }
    
    
    //Retorna los datos de una materia externa en especifico perteneciente a una universidad en especifico
     public boolean BuscaMateriaExternaExtrangero( MateriaExternaBean materiaExternaBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<MateriaExternaBean> arrayMateriaExterna = new ArrayList<>();
        boolean codMateriasExiste=false;
        try {
            if (this.openConnection()) {
             
               prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(MateriaExternaSQL.BuscarCodigpMateriaExternaExistente());
               prepareStatement.setString(1, materiaExternaBean.getMat_ext_codigo());
               prepareStatement.setString(2, materiaExternaBean.getMat_ext_uni_id());
               
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                     codMateriasExiste=true;
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
        return codMateriasExiste;
    }
    
    //Retorna los datos de una materia externa en especifico
     public boolean BuscaMateriaExternaAsignada( MateriaExternaBean materiaExternaBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<MateriaExternaBean> arrayMateriaExterna = new ArrayList<>();
        boolean codMateriasExiste=false;
        try {
            if (this.openConnection()) {
             
               prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(MateriaExternaSQL.BuscarCodigoMateriaAsignada());
               prepareStatement.setString(1, materiaExternaBean.getMat_ext_codigo());
               
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                     codMateriasExiste=true;
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
        return codMateriasExiste;
    }
    
    //Busca una materia por oficio en especifico 
     public boolean BuscaMateriaExternaOficioAsignada( MateriaExternaBean materiaExternaBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<MateriaExternaBean> arrayMateriaExterna = new ArrayList<>();
        boolean codMateriasExiste=false;
        try {
            if (this.openConnection()) {
             
               prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(MateriaExternaSQL.BuscarCodigoMateriaOficioAsignada());
               prepareStatement.setInt(1, materiaExternaBean.getMat_ext_id());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                     codMateriasExiste=true;
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
        return codMateriasExiste;
    }
    
       //
       public int buscarUniversidad( String descripcion) throws ExceptionConnection {
       
            PreparedStatement prepareStatement = null;
         
         try {   
            if (this.openConnection()) {
                prepareStatement = (PreparedStatement) this.getConexion().prepareStatement(MateriaExternaSQL.buscarIdUniversidadPorDescripcion(descripcion));
                prepareStatement.setString(1, descripcion);
                 ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                          return Integer.parseInt(resultSet.getString("UNI_ID"));
                    
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
            
           
           
            return 0;
        
    }
    
       
    
}

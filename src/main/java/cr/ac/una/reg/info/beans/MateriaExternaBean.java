 package cr.ac.una.reg.info.beans;

public class MateriaExternaBean {

    int mat_ext_id;
    String mat_ext_codigo;
    CarreraExternaBean carreraExterna;
    String mat_ext_descripcion;
    int creditos;
    String mat_ext_oficio;
    String mat_ext_uni_id;
    
    

    public MateriaExternaBean() {
        carreraExterna = new CarreraExternaBean();
    }

    public CarreraExternaBean getCarreraExterna() {
        return carreraExterna;
    }

    public void setCarreraExterna(CarreraExternaBean carreraExterna) {
        this.carreraExterna = carreraExterna;
    }

    public String getMat_ext_descripcion() {
        return mat_ext_descripcion;
    }

    public void setMat_ext_descripcion(String Mat_ext_descripcion) {
        this.mat_ext_descripcion = Mat_ext_descripcion;
    }

    public int getMat_ext_id() {
        return mat_ext_id;
    }

    public void setMat_ext_id(int mat_ext_id) {
        this.mat_ext_id = mat_ext_id;
    }

    public String getMat_ext_codigo() {
        return mat_ext_codigo;
    }

    public void setMat_ext_codigo(String mat_ext_codigo) {
        this.mat_ext_codigo = mat_ext_codigo;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getMat_ext_oficio() {
        return mat_ext_oficio;
    }

    public void setMat_ext_oficio(String mat_ext_oficio) {
        this.mat_ext_oficio = mat_ext_oficio;
    }

    public String getMat_ext_uni_id() {
        return mat_ext_uni_id;
    }

    public void setMat_ext_uni_id(String mat_ext_uni_id) {
        this.mat_ext_uni_id = mat_ext_uni_id;
    }

     
       
}

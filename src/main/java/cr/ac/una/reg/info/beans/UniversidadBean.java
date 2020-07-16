package cr.ac.una.reg.info.beans;

public class UniversidadBean {

    int uni_id;
    StvnatnBean stvnatn;
    String uni_descripcion;

    public UniversidadBean() {
        stvnatn=new StvnatnBean();
    }


    public int getUni_id() {
        return uni_id;
    }

    public void setUni_id(int Uni_id) {
        this.uni_id = Uni_id;
    }

    public StvnatnBean getStvnatn() {
        return stvnatn;
    }

    public void setStvnatn(StvnatnBean stvnatn) {
        this.stvnatn = stvnatn;
    }

    public String getUni_descripcion() {
        return uni_descripcion;
    }

    public void setUni_descripcion(String Uni_descripcion) {
        this.uni_descripcion = Uni_descripcion;
    }
}

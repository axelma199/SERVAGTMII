package cr.ac.una.reg.info.beans;
import cr.ac.una.reg.info.beans.StvnatnBean;

public class SolicitanteBean {

    String solte_id;
    String solte_nombre;
    StvnatnBean stvnatnBean;
    String solte_direccion;
    String solte_telefono;
    String solte_email;
    String gtvzipcCode;
    String stvcntyCode;
    String solteApellidos;
    String solteNombre;
    String solteSegNombre;
    public SolicitanteBean() {
        stvnatnBean=new StvnatnBean();
    }

    public String getSolte_id() {
        return solte_id;
    }

    public void setSolte_id(String Solte_id) {
        this.solte_id = Solte_id;
    }

    public String getSolte_nombre() {
        return solte_nombre;
    }

    public void setSolte_nombre(String Solte_nombre) {
        this.solte_nombre = Solte_nombre;
    }

    public StvnatnBean getStvnatnBean() {
        return stvnatnBean;
    }

    public void setStvnatnBean(StvnatnBean stvnatnBean) {
        this.stvnatnBean = stvnatnBean;
    }


    public String getSolte_direccion() {
        return solte_direccion;
    }

    public void setSolte_direccion(String Solte_direccion) {
        this.solte_direccion = Solte_direccion;
    }

    public String getSolte_telefono() {
        return solte_telefono;
    }

    public void setSolte_telefono(String Solte_telefono) {
        this.solte_telefono = Solte_telefono;
    }

    public String getSolte_email() {
        return solte_email;
    }

    public void setSolte_email(String Solte_email) {
        this.solte_email = Solte_email;
    }

    public synchronized static String spDr_regt_solicitante() {
        String sql = "CALL sp_Dr_regt_solicitante(?,?,?,?,?,?)";
        return (sql);
    }

    public String getGtvzipcCode() {
        return gtvzipcCode;
    }

    public void setGtvzipcCode(String gtvzipcCode) {
        this.gtvzipcCode = gtvzipcCode;
    }

    public String getStvcntyCode() {
        return stvcntyCode;
    }

    public void setStvcntyCode(String stvcntyCode) {
        this.stvcntyCode = stvcntyCode;
    }

    public String getSolteApellidos() {
        return solteApellidos;
    }

    public void setSolteApellidos(String solteApellidos) {
        this.solteApellidos = solteApellidos;
    }

    public String getSolteNombre() {
        return solteNombre;
    }

    public void setSolteNombre(String solteNombre) {
        this.solteNombre = solteNombre;
    }

    public String getSolteSegNombre() {
        return solteSegNombre;
    }

    public void setSolteSegNombre(String solteSegNombre) {
        this.solteSegNombre = solteSegNombre;
    }
    
    
    
}

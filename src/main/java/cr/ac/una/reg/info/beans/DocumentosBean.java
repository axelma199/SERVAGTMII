package cr.ac.una.reg.info.beans;

public class DocumentosBean {

    String doc_id;
    String doc_descripcion;
    boolean doc_eqp;
    boolean doc_eqv;
    Boolean verificado;

    public DocumentosBean() {
        this.doc_id = null;
        this.doc_descripcion= null;
        this.doc_eqp = false;
        this.doc_eqv = false;
        verificado = false;
    }

    public DocumentosBean(String p_doc_id, String p_doc_descripcion, boolean p_doc_eqp, boolean p_doc_eqv) {
        doc_id = p_doc_id;
        doc_descripcion = p_doc_descripcion;
        doc_eqp = p_doc_eqp;
        doc_eqv = p_doc_eqv;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String Doc_id) {
        this.doc_id = Doc_id;
    }

    public String getDoc_descripcion() {
        return doc_descripcion;
    }

    public void setDoc_descripcion(String Doc_descripcion) {
        this.doc_descripcion = Doc_descripcion;
    }

    public boolean getDoc_eqp() {
        return doc_eqp;
    }

    public void setDoc_eqp(boolean Doc_eqp) {
        this.doc_eqp = Doc_eqp;
    }

    public boolean getDoc_eqv() {
        return doc_eqv;
    }

    public void setDoc_eqv(boolean Doc_eqv) {
        this.doc_eqv = Doc_eqv;
    }

    public Boolean getVerificado() {
        return verificado;
    }

    public void setVerificado(Boolean verificado) {
        this.verificado = verificado;
    }
    
}

package cr.ac.una.reg.info.sql;

public class DocumentosSQL {

    /*Autor Mauricio Garita
    *Clase la cual registra los diferentes tipos de documentos requeridos para una equiparacion
    */
    
    
    public DocumentosSQL() {
    }

    //retorna la lista de todos los tipos de  documentos
    public synchronized static String listaDocumentos() {
        String sql = "Select doc_id,doc_descripcion,doc_eqp,doc_eqv from DR_REGT_DOCUMENTOS where doc_estado = 1 order by doc_id";
        return (sql);
    }

    //busca un documento en especifico por codigo del mismo
    public synchronized static String buscaDocumentos() {
        String sql = "Select doc_id,doc_descripcion,doc_eqp,doc_eqv from DR_REGT_DOCUMENTOS where doc_id = p_doc_id";
        return (sql);
    }

    //Inserta,actualiza y elimina los tipos de documentos
    public synchronized static String spDocumentos() {
        String sql = "CALL DR_PKG_REGT.SP_SERVAGTM_DOCUMENTOS(?,?,?,?,?)";
        return (sql);
    }
    
   //retorna la lista de todos los tipos de  documentos a un estado determinado
    public synchronized static String CMD_CONSULTA_DOCUMENTOS() {
        String sql = "SELECT DOC_ID, DOC_DESCRIPCION FROM DR_REGT_DOCUMENTOS where DOC_EQV=1 AND DOC_ESTADO = 1";
        return (sql);
    }
    
    
}

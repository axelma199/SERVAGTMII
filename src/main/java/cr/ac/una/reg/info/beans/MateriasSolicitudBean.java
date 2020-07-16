package cr.ac.una.reg.info.beans;

import java.util.ArrayList;

public class MateriasSolicitudBean {

    String mat_sol_numero;
    String mat_sol_reconocer;
    String mat_sol_reconocer_nom;
    int mat_sol_creditos;
    String mat_sol_equivalente;
    String mat_sol_equivalente_nom;
    int mat_sol_creditos_eqv;
    String mat_sol_condicion;
    private ArrayList<Dr_regt_historialBean> mat_sol_materiasEquivaler;

    public MateriasSolicitudBean() {
         mat_sol_materiasEquivaler = new ArrayList();
    }

    public String getMat_sol_numero() {
        return mat_sol_numero;
    }

    public void setMat_sol_numero(String Mat_sol_numero) {
        this.mat_sol_numero = Mat_sol_numero;
    }

    public String getMat_sol_reconocer() {
        return mat_sol_reconocer;
    }

    public void setMat_sol_reconocer(String Mat_sol_reconocer) {
        this.mat_sol_reconocer = Mat_sol_reconocer;
    }

    public String getMat_sol_reconocer_nom() {
        return mat_sol_reconocer_nom;
    }

    public void setMat_sol_reconocer_nom(String Mat_sol_reconocer_nom) {
        this.mat_sol_reconocer_nom = Mat_sol_reconocer_nom;
    }

    public int getMat_sol_creditos() {
        return mat_sol_creditos;
    }

    public void setMat_sol_creditos(int Mat_sol_creditos) {
        this.mat_sol_creditos = Mat_sol_creditos;
    }

    public String getMat_sol_equivalente() {
        return mat_sol_equivalente;
    }

    public void setMat_sol_equivalente(String Mat_sol_equivalente) {
        this.mat_sol_equivalente = Mat_sol_equivalente;
    }

    public String getMat_sol_equivalente_nom() {
        return mat_sol_equivalente_nom;
    }

    public void setMat_sol_equivalente_nom(String Mat_sol_equivalente_nom) {
        this.mat_sol_equivalente_nom = Mat_sol_equivalente_nom;
    }

    public int getMat_sol_creditos_eqv() {
        return mat_sol_creditos_eqv;
    }

    public void setMat_sol_creditos_eqv(int Mat_sol_creditos_eqv) {
        this.mat_sol_creditos_eqv = Mat_sol_creditos_eqv;
    }

    public String getMat_sol_condicion() {
        return mat_sol_condicion;
    }

    public void setMat_sol_condicion(String Mat_sol_condicion) {
        this.mat_sol_condicion = Mat_sol_condicion;
    }
    
      /**
     * @return the mat_sol_materiasEquivaler
     */
    public ArrayList<Dr_regt_historialBean> getMateriasEquivaler() {
        return mat_sol_materiasEquivaler;
    }

    /**
     * @param mat_sol_materiasEquivaler the mat_sol_materiasEquivaler to set
     */
    public void setMateriasEquivaler(ArrayList<Dr_regt_historialBean> materiasEquivaler) {
        this.mat_sol_materiasEquivaler = materiasEquivaler;
    }
}
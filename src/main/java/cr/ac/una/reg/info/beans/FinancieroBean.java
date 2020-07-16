/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.beans;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped

public class FinancieroBean {
    private String financiero_cod;
    private String financiero_ano;
    private String financiero_rubro;
    private Float financiero_valor;

    public FinancieroBean(){
        financiero_cod = null;
        financiero_ano = null;
        financiero_rubro = null;
        financiero_valor = null;
    }
    
    public FinancieroBean(String p_financiero_cod, String p_financiero_ano, String p_financiero_rubro, Float p_financiero_valor) {
        financiero_cod = p_financiero_cod;
        financiero_ano = p_financiero_ano;
        financiero_rubro = p_financiero_rubro;
        financiero_valor = p_financiero_valor;
    }

    public String getFinanciero_ano() {
        return financiero_ano;
    }

    public void setFinanciero_ano(String Financiero_ano) {
        this.financiero_ano = Financiero_ano;
    }

    public String getFinanciero_rubro() {
        return financiero_rubro;
    }

    public void setFinanciero_rubro(String Financiero_rubro) {
        this.financiero_rubro = Financiero_rubro;
    }

    public Float getFinanciero_valor() {
        return financiero_valor;
    }

    public void setFinanciero_valor(Float Financiero_valor) {
        this.financiero_valor = Financiero_valor;
    }

    public synchronized static String spFinanciero() {
        String sql = "CALL SP_SERVAGTM_FINANCIERO(?,?,?,?)";
        return (sql);
    }

    /**
     * @return the financiero_cod
     */
    public String getFinanciero_cod() {
        return financiero_cod;
    }

    /**
     * @param financiero_cod the financiero_cod to set
     */
    public void setFinanciero_cod(String financiero_cod) {
        this.financiero_cod = financiero_cod;
    }
}

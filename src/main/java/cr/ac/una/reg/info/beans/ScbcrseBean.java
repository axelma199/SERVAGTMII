/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.beans;

import java.sql.Date;

/**
 *
 * @author JGA
 */
public class ScbcrseBean {

    private String codigo;
    private String scbcrse_subj_code;
    private String scbcrse_crse_numb;
    private String scbcrse_eff_term;
    private String scbcrse_coll_code;
    private String scbcrse_divs_code;
    private String scbcrse_dept_code;
    private String scbcrse_csta_code;
    private String scbcrse_title;
    private String scbcrse_cipc_code;
    private String scbcrse_credit_hr_ind;
    private int scbcrse_credit_hr_low;
    private int scbcrse_credit_hr_high;
    private String scbcrse_lec_hr_ind;
    private int scbcrse_lec_hr_low;
    private int scbcrse_lec_hr_high;
    private String scbcrse_lab_hr_ind;
    private int scbcrse_lab_hr_low;
    private int scbcrse_lab_hr_high;
    private String scbcrse_oth_hr_ind;
    private int scbcrse_oth_hr_low;
    private int scbcrse_oth_hr_high;
    private String scbcrse_bill_hr_ind;
    private int scbcrse_bill_hr_low;
    private int scbcrse_bill_hr_high;
    private String scbcrse_aprv_code;
    private int scbcrse_repeat_limit;
    private String scbcrse_pwav_code;
    private String scbcrse_tuiw_ind;
    private String scbcrse_add_fees_ind;
    private Date scbcrse_activity_date;
    private int scbcrse_cont_hr_low;
    private String scbcrse_cont_hr_ind;
    private int scbcrse_cont_hr_high;
    private String scbcrse_ceu_ind;
    private String scbcrse_reps_code;
    private int scbcrse_max_rpt_units;
    private String scbcrse_capp_prereq_test_ind;
    private String scbcrse_dunt_code;
    private int scbcrse_number_of_units;
    private String scbcrse_data_origin;
    private String scbcrse_user_id;

    public ScbcrseBean() {
        codigo = "";
        scbcrse_subj_code = "";
        scbcrse_crse_numb = "";
        scbcrse_title="";
        scbcrse_credit_hr_low=0;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        //codigo = scbcrse_subj_code + scbcrse_crse_numb;
        return codigo;
    }

    public String getScbcrse_subj_code() {
        return scbcrse_subj_code;
    }

    public void setScbcrse_subj_code(String Scbcrse_subj_code) {
        this.scbcrse_subj_code = Scbcrse_subj_code;
    }

    public String getScbcrse_crse_numb() {
        return scbcrse_crse_numb;
    }

    public void setScbcrse_crse_numb(String Scbcrse_crse_numb) {
        this.scbcrse_crse_numb = Scbcrse_crse_numb;
    }

    public String getScbcrse_eff_term() {
        return scbcrse_eff_term;
    }

    public void setScbcrse_eff_term(String Scbcrse_eff_term) {
        this.scbcrse_eff_term = Scbcrse_eff_term;
    }

    public String getScbcrse_coll_code() {
        return scbcrse_coll_code;
    }

    public void setScbcrse_coll_code(String Scbcrse_coll_code) {
        this.scbcrse_coll_code = Scbcrse_coll_code;
    }

    public String getScbcrse_divs_code() {
        return scbcrse_divs_code;
    }

    public void setScbcrse_divs_code(String Scbcrse_divs_code) {
        this.scbcrse_divs_code = Scbcrse_divs_code;
    }

    public String getScbcrse_dept_code() {
        return scbcrse_dept_code;
    }

    public void setScbcrse_dept_code(String Scbcrse_dept_code) {
        this.scbcrse_dept_code = Scbcrse_dept_code;
    }

    public String getScbcrse_csta_code() {
        return scbcrse_csta_code;
    }

    public void setScbcrse_csta_code(String Scbcrse_csta_code) {
        this.scbcrse_csta_code = Scbcrse_csta_code;
    }

    public String getScbcrse_title() {
        return scbcrse_title;
    }

    public void setScbcrse_title(String Scbcrse_title) {
        this.scbcrse_title = Scbcrse_title;
    }

    public String getScbcrse_cipc_code() {
        return scbcrse_cipc_code;
    }

    public void setScbcrse_cipc_code(String Scbcrse_cipc_code) {
        this.scbcrse_cipc_code = Scbcrse_cipc_code;
    }

    public String getScbcrse_credit_hr_ind() {
        return scbcrse_credit_hr_ind;
    }

    public void setScbcrse_credit_hr_ind(String Scbcrse_credit_hr_ind) {
        this.scbcrse_credit_hr_ind = Scbcrse_credit_hr_ind;
    }

    public int getScbcrse_credit_hr_low() {
        return scbcrse_credit_hr_low;
    }

    public void setScbcrse_credit_hr_low(int Scbcrse_credit_hr_low) {
        this.scbcrse_credit_hr_low = Scbcrse_credit_hr_low;
    }

    public int getScbcrse_credit_hr_high() {
        return scbcrse_credit_hr_high;
    }

    public void setScbcrse_credit_hr_high(int Scbcrse_credit_hr_high) {
        this.scbcrse_credit_hr_high = Scbcrse_credit_hr_high;
    }

    public String getScbcrse_lec_hr_ind() {
        return scbcrse_lec_hr_ind;
    }

    public void setScbcrse_lec_hr_ind(String Scbcrse_lec_hr_ind) {
        this.scbcrse_lec_hr_ind = Scbcrse_lec_hr_ind;
    }

    public int getScbcrse_lec_hr_low() {
        return scbcrse_lec_hr_low;
    }

    public void setScbcrse_lec_hr_low(int Scbcrse_lec_hr_low) {
        this.scbcrse_lec_hr_low = Scbcrse_lec_hr_low;
    }

    public int getScbcrse_lec_hr_high() {
        return scbcrse_lec_hr_high;
    }

    public void setScbcrse_lec_hr_high(int Scbcrse_lec_hr_high) {
        this.scbcrse_lec_hr_high = Scbcrse_lec_hr_high;
    }

    public String getScbcrse_lab_hr_ind() {
        return scbcrse_lab_hr_ind;
    }

    public void setScbcrse_lab_hr_ind(String Scbcrse_lab_hr_ind) {
        this.scbcrse_lab_hr_ind = Scbcrse_lab_hr_ind;
    }

    public int getScbcrse_lab_hr_low() {
        return scbcrse_lab_hr_low;
    }

    public void setScbcrse_lab_hr_low(int Scbcrse_lab_hr_low) {
        this.scbcrse_lab_hr_low = Scbcrse_lab_hr_low;
    }

    public int getScbcrse_lab_hr_high() {
        return scbcrse_lab_hr_high;
    }

    public void setScbcrse_lab_hr_high(int Scbcrse_lab_hr_high) {
        this.scbcrse_lab_hr_high = Scbcrse_lab_hr_high;
    }

    public String getScbcrse_oth_hr_ind() {
        return scbcrse_oth_hr_ind;
    }

    public void setScbcrse_oth_hr_ind(String Scbcrse_oth_hr_ind) {
        this.scbcrse_oth_hr_ind = Scbcrse_oth_hr_ind;
    }

    public int getScbcrse_oth_hr_low() {
        return scbcrse_oth_hr_low;
    }

    public void setScbcrse_oth_hr_low(int Scbcrse_oth_hr_low) {
        this.scbcrse_oth_hr_low = Scbcrse_oth_hr_low;
    }

    public int getScbcrse_oth_hr_high() {
        return scbcrse_oth_hr_high;
    }

    public void setScbcrse_oth_hr_high(int Scbcrse_oth_hr_high) {
        this.scbcrse_oth_hr_high = Scbcrse_oth_hr_high;
    }

    public String getScbcrse_bill_hr_ind() {
        return scbcrse_bill_hr_ind;
    }

    public void setScbcrse_bill_hr_ind(String Scbcrse_bill_hr_ind) {
        this.scbcrse_bill_hr_ind = Scbcrse_bill_hr_ind;
    }

    public int getScbcrse_bill_hr_low() {
        return scbcrse_bill_hr_low;
    }

    public void setScbcrse_bill_hr_low(int Scbcrse_bill_hr_low) {
        this.scbcrse_bill_hr_low = Scbcrse_bill_hr_low;
    }

    public int getScbcrse_bill_hr_high() {
        return scbcrse_bill_hr_high;
    }

    public void setScbcrse_bill_hr_high(int Scbcrse_bill_hr_high) {
        this.scbcrse_bill_hr_high = Scbcrse_bill_hr_high;
    }

    public String getScbcrse_aprv_code() {
        return scbcrse_aprv_code;
    }

    public void setScbcrse_aprv_code(String Scbcrse_aprv_code) {
        this.scbcrse_aprv_code = Scbcrse_aprv_code;
    }

    public int getScbcrse_repeat_limit() {
        return scbcrse_repeat_limit;
    }

    public void setScbcrse_repeat_limit(int Scbcrse_repeat_limit) {
        this.scbcrse_repeat_limit = Scbcrse_repeat_limit;
    }

    public String getScbcrse_pwav_code() {
        return scbcrse_pwav_code;
    }

    public void setScbcrse_pwav_code(String Scbcrse_pwav_code) {
        this.scbcrse_pwav_code = Scbcrse_pwav_code;
    }

    public String getScbcrse_tuiw_ind() {
        return scbcrse_tuiw_ind;
    }

    public void setScbcrse_tuiw_ind(String Scbcrse_tuiw_ind) {
        this.scbcrse_tuiw_ind = Scbcrse_tuiw_ind;
    }

    public String getScbcrse_add_fees_ind() {
        return scbcrse_add_fees_ind;
    }

    public void setScbcrse_add_fees_ind(String Scbcrse_add_fees_ind) {
        this.scbcrse_add_fees_ind = Scbcrse_add_fees_ind;
    }

    public Date getScbcrse_activity_date() {
        return scbcrse_activity_date;
    }

    public void setScbcrse_activity_date(Date Scbcrse_activity_date) {
        this.scbcrse_activity_date = Scbcrse_activity_date;
    }

    public int getScbcrse_cont_hr_low() {
        return scbcrse_cont_hr_low;
    }

    public void setScbcrse_cont_hr_low(int Scbcrse_cont_hr_low) {
        this.scbcrse_cont_hr_low = Scbcrse_cont_hr_low;
    }

    public String getScbcrse_cont_hr_ind() {
        return scbcrse_cont_hr_ind;
    }

    public void setScbcrse_cont_hr_ind(String Scbcrse_cont_hr_ind) {
        this.scbcrse_cont_hr_ind = Scbcrse_cont_hr_ind;
    }

    public int getScbcrse_cont_hr_high() {
        return scbcrse_cont_hr_high;
    }

    public void setScbcrse_cont_hr_high(int Scbcrse_cont_hr_high) {
        this.scbcrse_cont_hr_high = Scbcrse_cont_hr_high;
    }

    public String getScbcrse_ceu_ind() {
        return scbcrse_ceu_ind;
    }

    public void setScbcrse_ceu_ind(String Scbcrse_ceu_ind) {
        this.scbcrse_ceu_ind = Scbcrse_ceu_ind;
    }

    public String getScbcrse_reps_code() {
        return scbcrse_reps_code;
    }

    public void setScbcrse_reps_code(String Scbcrse_reps_code) {
        this.scbcrse_reps_code = Scbcrse_reps_code;
    }

    public int getScbcrse_max_rpt_units() {
        return scbcrse_max_rpt_units;
    }

    public void setScbcrse_max_rpt_units(int Scbcrse_max_rpt_units) {
        this.scbcrse_max_rpt_units = Scbcrse_max_rpt_units;
    }

    public String getScbcrse_capp_prereq_test_ind() {
        return scbcrse_capp_prereq_test_ind;
    }

    public void setScbcrse_capp_prereq_test_ind(String Scbcrse_capp_prereq_test_ind) {
        this.scbcrse_capp_prereq_test_ind = Scbcrse_capp_prereq_test_ind;
    }

    public String getScbcrse_dunt_code() {
        return scbcrse_dunt_code;
    }

    public void setScbcrse_dunt_code(String Scbcrse_dunt_code) {
        this.scbcrse_dunt_code = Scbcrse_dunt_code;
    }

    public int getScbcrse_number_of_units() {
        return scbcrse_number_of_units;
    }

    public void setScbcrse_number_of_units(int Scbcrse_number_of_units) {
        this.scbcrse_number_of_units = Scbcrse_number_of_units;
    }

    public String getScbcrse_data_origin() {
        return scbcrse_data_origin;
    }

    public void setScbcrse_data_origin(String Scbcrse_data_origin) {
        this.scbcrse_data_origin = Scbcrse_data_origin;
    }

    public String getScbcrse_user_id() {
        return scbcrse_user_id;
    }

    public void setScbcrse_user_id(String Scbcrse_user_id) {
        this.scbcrse_user_id = Scbcrse_user_id;
    }

    /*public synchronized static String spScbcrse() {
     String sql = "CALL sp_Scbcrse(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     return (sql);
     }*/
}

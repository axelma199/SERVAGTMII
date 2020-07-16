package cr.ac.una.reg.info.beans;

import java.sql.Date;

public class BitacoraSolicitudBean {

    int bit_id;
    String bit_sol_id;
    String bit_tipo_movimiento;
    Dr_siseg_usuarioBean usuarioBean;
    String bit_nom_usuario;
    Date bit_fecha;
    String bit_detalle;
    String bit_tipo_mov_desc;

    public BitacoraSolicitudBean() {
        usuarioBean=new Dr_siseg_usuarioBean();
    }

    public int getBit_id() {
        return bit_id;
    }

    public void setBit_id(int Bit_id) {
        this.bit_id = Bit_id;
    }

    public String getBit_sol_id() {
        return bit_sol_id;
    }

    public void setBit_sol_id(String Bit_sol_id) {
        this.bit_sol_id = Bit_sol_id;
    }

    public String getBit_tipo_movimiento() {
        return bit_tipo_movimiento;
    }

    public void setBit_tipo_movimiento(String Bit_tipo_movimiento) {
        this.bit_tipo_movimiento = Bit_tipo_movimiento;
    }

    public Date getBit_fecha() {
        return bit_fecha;
    }

    public void setBit_fecha(Date Bit_fecha) {
        this.bit_fecha = Bit_fecha;
    }

    public String getBit_detalle() {
        return bit_detalle;
    }

    public void setBit_detalle(String Bit_detalle) {
        this.bit_detalle = Bit_detalle;
    }

    public String getBit_tipo_mov_desc() {
         if (bit_tipo_movimiento.equals("0")||(bit_tipo_movimiento.equals("I"))) {
            bit_tipo_mov_desc = "INCLUIDA";
        } else if (bit_tipo_movimiento.equals("1")||(bit_tipo_movimiento.equals("M"))) {
            bit_tipo_mov_desc = "MODIFICADA";
        } else if (bit_tipo_movimiento.equals("2")||(bit_tipo_movimiento.equals("E"))) {
            bit_tipo_mov_desc = "ELIMINADA";
        }  else if (bit_tipo_movimiento.equals("F")) {
            bit_tipo_mov_desc = "FINALIZADA";
        }   else if (bit_tipo_movimiento.equals("R")) {
            bit_tipo_mov_desc = "RESULETA";
        }   else if (bit_tipo_movimiento.equals("RV")) {
            bit_tipo_mov_desc = "REVERSADA";
        }
         
            return bit_tipo_mov_desc;
        
        }

    public void setBit_tipo_mov_desc(String bit_tipo_mov_desc) {
        this.bit_tipo_mov_desc = bit_tipo_mov_desc;
    }

    public String getBit_nom_usuario() {
        return bit_nom_usuario;
    }

    public void setBit_nom_usuario(String bit_nom_usuario) {
        this.bit_nom_usuario = bit_nom_usuario;
    }

    public Dr_siseg_usuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(Dr_siseg_usuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    
    
}

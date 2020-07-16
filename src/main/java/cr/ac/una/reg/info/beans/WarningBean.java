/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cr.ac.una.reg.info.beans;

import cr.ac.una.reg.info.exceptions.ExceptionGeneral;

/**
 *
 * @author i111910530
 */
public class WarningBean {

    private ExceptionGeneral exceptionGeneral;
    private String mensajeSimple;


    public WarningBean(ExceptionGeneral exg){
        this.exceptionGeneral = exg;
    }//
    public WarningBean(){
        this.exceptionGeneral = new ExceptionGeneral();
    }//

    public ExceptionGeneral getExceptionGeneral() {
        return exceptionGeneral;
    }

    public void setExceptionGeneral(ExceptionGeneral exceptionGeneral) {
        this.exceptionGeneral = exceptionGeneral;
    }

    public String getMensajeSimple() {
        return mensajeSimple;
    }

    public void setMensajeSimple(String mensajeSimple) {
        this.mensajeSimple = mensajeSimple;
    }

    public void getExceptionGeneralFatal(){
       this.exceptionGeneral.isErrorGrave();
    }

}//

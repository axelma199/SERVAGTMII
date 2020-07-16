package cr.ac.una.reg.info.exceptions;

/**
 * @Autor Eduardo Gonz�lez Corrales.
 * @Compa�ia Universidad Nacional.
 * @FechaCreaci�n 6/04/2010.
 *
 * Esta clase se encarga del manejo de las exceptiones a un nivel más general.
 */

public class ExceptionGeneral extends Exception{

	private static final long serialVersionUID = 1L;
    private int identificadorDeError = 0;              //Identificación del error a traves de un número
	private String message = "";                       //Mensaje personalizado dirigido al usuario, o al t�cnico en caso de un error grave.
	private String classOrigen;                        //Clase que origina la excepción.
	private int numeroException = 0;                   //Número de la excepción.
	private int nivelException = 0;                    //Indica el nivel de la excepci�n, permite tomar decisiones en el flujo del manejo de errores.
	private boolean errorGrave = false;                //Es activado cuando se quiere avisar de un error por el cual el sistema no puede continuar.
    private String mensajeTecnico;                     //Mesaje que guarda el mensaje en lenguaje técnico.
    

	//CONSTRUCTOR A
	public ExceptionGeneral(){
		super();
	}//constructor A
	
	//CONSTRUCTOR B
	public ExceptionGeneral(String message){
		super(message);
		this.message = message;
	}//constructor B
	
	//CONSTRUCTOR C
	public ExceptionGeneral(String message, int numeroException){
		super(message);
		this.message = message;
		this.numeroException = numeroException;
	}//constructor C

	//CONSTRUCTOR D
	public ExceptionGeneral(String message, int numeroException, boolean errorGrave, int nivelException, String classOrigen){
		super(message);
		this.message = message;
		this.classOrigen = classOrigen;
		this.numeroException = numeroException;
		this.errorGrave = errorGrave;
		this.nivelException = 0;
	}//constructor D

    //CONSTRUCTOR E
	public ExceptionGeneral(String message, boolean errorGrave, String classOrigen){
		super(message);
		this.message = message;
		this.classOrigen = classOrigen;
		this.errorGrave = errorGrave;
		this.nivelException = 3;
	}//constructor E

    //CONSTRUCTOR F
	public ExceptionGeneral(String message, String mensajeTecnico, int numeroException, boolean errorGrave, int nivelException, String classOrigen){
		super(message);
		this.message = message;
        this.mensajeTecnico = mensajeTecnico;
		this.classOrigen = classOrigen;
		this.numeroException = numeroException;
		this.errorGrave = errorGrave;
		this.nivelException = 0;
	}//constructor F

     //CONSTRUCTOR G
	public ExceptionGeneral(String message, String mensajeTecnico, int numeroException, boolean errorGrave, int nivelException, String classOrigen, int identificadorDeError){
		super(message);
		this.message = message;
        this.mensajeTecnico = mensajeTecnico;
		this.classOrigen = classOrigen;
		this.numeroException = numeroException;
		this.errorGrave = errorGrave;
		this.nivelException = 0;
        this.identificadorDeError = identificadorDeError;
	}//constructor G
	
	
	public int getNivelException() {
		return nivelException;
	}

	public void setNivelException(int nivelException) {
		this.nivelException = nivelException;
	}

	public boolean isErrorGrave() {
		return errorGrave;
	}

	public void setErrorGrave(boolean errorGrave) {
		this.errorGrave = errorGrave;
	}

	public String obtenerAllError(){
		return "Numero Exception: " + this.numeroException + "Error Mensaje: " + this.message + "Clase Origen: " + this.classOrigen;
	}//

	public String getMensajeError(){
		return this.message;
	}//

	public void setMensajeError(String mensaje){
		this.message = mensaje;
	}//

        public int getNumeroException() {
            return numeroException;
        }//

        public void setNumeroException(int numeroException) {
            this.numeroException = numeroException;
        }//

    public String getMensajeTecnico() {
        return mensajeTecnico;
    }

    public void setMensajeTecnico(String mensajeTecnico) {
        this.mensajeTecnico = mensajeTecnico;
    }

    public int getIdentificadorDeError() {
        return identificadorDeError;
    }

    public void setIdentificadorDeError(int identificadorDeError) {
        this.identificadorDeError = identificadorDeError;
    }
        
}//

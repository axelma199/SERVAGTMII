/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cr.ac.una.reg.info.tools;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author sede interuniversita
 */
public class ManejoFacesContext {

  private static final Locale[] supportedLocales = {
                                                     new Locale("fr","FR"),
                                                     new Locale("de","DE"),
                                                     new Locale("en","US")
                                                    };                                  //este permite obtener las configuraciones para el archivos de properties


    /**
     * constructor
     */
	public ManejoFacesContext(){
	}///constructor

    /**
     * Se encarga de destruir un objeto de la session
     * @param nombreObjetoBean
     */
	public void destruirObjetoSession(String nombreObjetoBean){
		try{
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove( nombreObjetoBean );
		}catch(Exception ex){
		}//
	}//

    /**
     * Se encarga de destruir un objeto del request.
     * @param nombreObjetoBean
     */
	public void destruirObjetoRequest(String nombreObjetoBean){
		try{
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().remove( nombreObjetoBean );
		}catch(Exception ex){
		}//
	}//

    /**
     * Este permite incluir un objeto de session
     * @param objeto
     * @param descripcion
     */
	public void incluirObjetoSession(Object objeto, String descripcion){
		try{
			FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().getSessionMap().put(descripcion, objeto);
		}catch(Exception ex){
		}//
	}//

    /**
     * Este permite incluir un objeto en el request.
     * @param objeto
     * @param descripcion
     */
	public void incluirObjetoRequest(Object objeto, String descripcion){
		try{
			FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().getRequestMap().put(descripcion, objeto);
		}catch(Exception ex){
		}//
	}//

    /**
     * Permite obtener un objeto de session
     * @param descripcion
     * @return
     */
	public Object obtenerObjetoSession(String descripcion){
		Object objeto = null;
		try{
			FacesContext facesContext = FacesContext.getCurrentInstance();
            objeto = facesContext.getExternalContext().getSessionMap().get(descripcion);
		}catch(Exception ex){
		}//
		return objeto;
	}//

    /**
     * Permite obtener un objeto de request.
     * @param descripcion
     * @return
     */
    public Object obtenerObjetoApplication(String descripcion){
		Object objeto = null;
		try{
			FacesContext facesContext = FacesContext.getCurrentInstance();
            objeto = facesContext.getExternalContext().getApplicationMap().get(descripcion);
		}catch(Exception ex){
		}//
		return objeto;
	}//

    /**
     * Permite obtener un objeto de request.
     * @param descripcion
     * @return
     */
    public Object obtenerObjetoRequest(String descripcion){
		Object objeto = null;
		try{
			FacesContext facesContext = FacesContext.getCurrentInstance();
            objeto = facesContext.getExternalContext().getRequestMap().get(descripcion);
        }catch(Exception ex){
		}//
		return objeto;
	}//

    /**
     * Permite redireccionar el flujo Web, osea pasar de una pÃ¡gina Web a otra a travÃ©s de un URL
     * @param paginaWebDestino URL
     */
	public void redireccionarFlujoWeb(String paginaWebDestino ){
		boolean a=false;
        try{
			FacesContext facesContext = FacesContext.getCurrentInstance();
		    facesContext.getExternalContext().redirect(paginaWebDestino );


		}catch(Exception ex){
            a=true;

		}//
	}//

	/**
     * Permite agregar un mensaje
     * @param mensaje
     */
	public void addUserMensaje(String mensaje){
		FacesMessage facesMensaje = null;
		try{
		   facesMensaje = new FacesMessage(mensaje);
		   FacesContext.getCurrentInstance().addMessage("Mensaje", facesMensaje);
		}catch(Exception ex){
		}//
	}//

    /**
     * Permite agregar un mensaje utilizando un identificador de componente
     * @param id
     * @param mensaje
     */
    public void addUserMensaje(String id, String mensaje){
		FacesMessage facesMensaje = null;
		try{
		   facesMensaje = new FacesMessage(mensaje);
		   FacesContext.getCurrentInstance().addMessage( id, facesMensaje);
		}catch(Exception ex){
		}//
	}//

    /**
     * permite obtener el valor de un atributo en un archivo de propiedades.
     * @param fichero
     * @param atributo
     * @return
     */
    public String getStringFromPropertiesFile(String fichero, String atributo){
        String valor = "";
        try{
          //Locale currentLocale = supportedLocales[1];
          ResourceBundle labels = ResourceBundle.getBundle(fichero);
          valor = labels.getString(atributo);
        }catch(Exception ex){
        }//
        return valor;
    }//



}///

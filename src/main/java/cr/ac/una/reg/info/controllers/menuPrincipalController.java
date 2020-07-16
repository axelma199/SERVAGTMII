
package cr.ac.una.reg.info.controllers;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

@ManagedBean(name= menuPrincipalController.BEAN_NAME)
@CustomScoped(value = "#{window}")
public class menuPrincipalController implements Serializable 
{

    public static final String BEAN_NAME = "menuBarBean";
    public String getBeanName() { return BEAN_NAME; }
    public final String MENU_BAR_ID = "menuBar";
    private final String PATH_SEPARATOR = " > ";
    private String message;
    private Format formatter;
    
    public menuPrincipalController() {
        formatter = new SimpleDateFormat("HH:mm:ss");
        message = "please select any menu item";
    }
    
    public void fireAction(ActionEvent event)
    {
        
        boolean exitCondition = false;
//        UIComponent childComponent = event.getComponent();
//        UIComponent parentComponent = childComponent.getParent();
//        
//        String [] results = childComponent.getClientId().split(":");
//        String revertedPath = results[results.length-1].toUpperCase() + PATH_SEPARATOR;
//        //extract all component id's from current selection to menuBar component and save those results in the revertedPath variable
//        //For example if we choose menu item with id="tab" the result will look like: TAB > NEW > FILE
//        do
//        {
//            results = parentComponent.getClientId().split(":");
//            
//            if(results[results.length-1].toUpperCase().equals(MENU_BAR_ID.toUpperCase()))
//            {
//                exitCondition = true;
//            }
//            else
//            {
//                revertedPath += results[results.length-1].toUpperCase() + PATH_SEPARATOR;
//                parentComponent = parentComponent.getParent();
//            }
//        }
//        while(!exitCondition);
//        
//        //traverse revertedPath backwards and save final result in the message variable
//        //TAB > NEW > FILE will become FILE > NEW > TAB
//        results = revertedPath.split(PATH_SEPARATOR);
//        message = new String();
//        for(int index=results.length-1; index>=0; index--)
//        {
//            if(index>0) message += results[index]+ PATH_SEPARATOR; 
//            else message += results[index]; 
//        }
//        message += " - selected @ "+formatter.format(new Date()) + " (server time)";
    }

    public String getMessage() {return message;}
    public String getMenuBarId() { return MENU_BAR_ID; }
    
    public void setMessage(String message) {this.message = message;}
    
    
    
}

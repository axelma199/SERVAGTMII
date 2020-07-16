/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.beans;
import java.lang.Object;
import cr.ac.una.reg.info.tools.ManejoFacesContext;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.icefaces.ace.component.menuitem.MenuItem;
import org.icefaces.ace.component.submenu.Submenu;
import org.icefaces.ace.model.DefaultMenuModel;
import org.icefaces.ace.model.MenuModel;


/*import org.jboss.weld.context.SerializableContextualInstanceImpl;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;  */

@ManagedBean
@ViewScoped
public final class MenuBean {

    private MenuModel menu = new DefaultMenuModel();
    private String rol;
    
    public MenuBean(String p_rol) {
        rol=p_rol;
        
       
        if (rol.equals("GRP_SERVAGTMII_ADMIN")){
           Administrador();
        }else{
           if (rol.equals("GRP_SERVAGTMII_PF1")){
               AtencionEst();
           }
    }
       
        

//        if (rol.equals("GRP_REGT_ADMIN")) {
//            Administrador();
//        } else {
//            if (rol.equals("2")) {
//                AtencionEst();
//            } else {
//                if (rol.equals("3")) {
//                    EncargadoArea();
//                }
//            }
//
//        }
    }
    

        
    

    public void Administrador() {
        menu = new DefaultMenuModel();
        Submenu submenu = new Submenu();
        submenu.setLabel("Administración");

        MenuItem submenuItem = new MenuItem();

        submenuItem.setValue("Solicitantes");
        submenuItem.setUrl("/faces/paginas/Solicitante.xhtml");
        submenu.getChildren().add(submenuItem);
        submenuItem = new MenuItem();
        submenuItem.setValue("Universidades");
        submenuItem.setUrl("/faces/paginas/Universidades.xhtml");
        submenu.getChildren().add(submenuItem);
        submenuItem = new MenuItem();
        submenuItem.setValue("Carreras Externas");
        submenuItem.setUrl("/faces/paginas/CarreraExterna.xhtml");
        submenu.getChildren().add(submenuItem);
        submenuItem = new MenuItem();
        submenuItem.setValue("Materias Externas");
        submenuItem.setUrl("/faces/paginas/Materia.xhtml");
        submenu.getChildren().add(submenuItem); 
      
        submenuItem = new MenuItem();
        submenuItem.setValue("Documentos");
        submenuItem.setUrl("/faces/paginas/ListaDocumentos.xhtml");
        submenu.getChildren().add(submenuItem); 
        submenuItem = new MenuItem();
        submenuItem.setValue("Financiero");
        submenuItem.setUrl("/faces/paginas/ListaFinanciero.xhtml");
        submenu.getChildren().add(submenuItem); 
        menu.addSubmenu(submenu);

        submenu = new Submenu();
        submenu.setLabel("Equiparaciones");

        submenuItem = new MenuItem();
        submenuItem.setValue("Cursos");
        submenuItem.setUrl("/faces/paginas/ListarEquiparaciones.xhtml");
        submenu.getChildren().add(submenuItem);
        
        submenuItem = new MenuItem();
        submenuItem.setValue("Cursos (de Oficio)");
        submenuItem.setUrl("/faces/paginas/ListaEquiparacionesOficio.xhtml");
        submenu.getChildren().add(submenuItem); 
       menu.addSubmenu(submenu);
       
        //menu.addSeparator(null);
//        submenuItem = new MenuItem();
//        submenuItem.setValue("Reversar Estado Equiparacion");
//        submenuItem.setUrl("/faces/paginas/ListarEquiparacionesReversar.xhtml");
//        submenu.getChildren().add(submenuItem); 
//        menu.addSubmenu(submenu);
        
          submenuItem = new MenuItem();
        submenuItem.setValue("Modifcar Datos Generales");
        submenuItem.setUrl("/faces/paginas/EquiparacionesModificacionDatos.xhtml");
        submenu.getChildren().add(submenuItem); 
        menu.addSubmenu(submenu);
       

     
        
        
//        submenu = new Submenu();
//        submenu.setLabel("Equivalencias");

//        submenuItem = new MenuItem();
//        submenuItem.setValue("Mantenimiento");
//        submenuItem.setUrl("/faces/paginas/ListaEquivalencias.xhtml");
//        submenu.getChildren().add(submenuItem);
//        menu.addSubmenu(submenu);

        submenu = new Submenu();
        submenu.setLabel("Apelaciones");

        submenuItem = new MenuItem();
        submenuItem.setValue("Mantenimiento");
        submenuItem.setUrl("/faces/paginas/ListaApelaciones.xhtml");
        submenu.getChildren().add(submenuItem);
        menu.addSubmenu(submenu);
        
         submenu = new Submenu();
        submenu.setLabel("Seguridad");

//        submenuItem = new MenuItem();
//        submenuItem.setValue("Usuarios");
//        submenuItem.setUrl("/faces/paginas/ListaUsuarios.xhtml");
//        submenu.getChildren().add(submenuItem);
        submenuItem = new MenuItem();
        submenuItem.setValue("Bitacora");
        submenuItem.setUrl("/faces/paginas/BitacoraSolicitudes.xhtml");
        submenu.getChildren().add(submenuItem);
        menu.addSubmenu(submenu);
        
        
         submenu = new Submenu();
        submenu.setLabel("Reportes");
        
        submenuItem = new MenuItem();
        submenuItem.setValue("Equiparaciones Recibidas");
        submenuItem.setUrl("/faces/paginas/ReporteEquiparacionesRecibidas.xhtml");
        submenu.getChildren().add(submenuItem);
        submenuItem = new MenuItem();
        
        menu.addSubmenu(submenu);
        
        
        submenu = new Submenu();
        submenu.setLabel("Ayuda");
        
        submenuItem = new MenuItem();
        submenuItem.setValue("Manual de Usuario");
        submenuItem.setUrl("/faces/paginas/manual.pdf");
        submenu.getChildren().add(submenuItem);
        submenuItem = new MenuItem();
        
        menu.addSubmenu(submenu);
        
    }

    public void AtencionEst() {
        menu = new DefaultMenuModel();
        Submenu submenu = new Submenu();
        submenu.setLabel("Administración");

        MenuItem submenuItem = new MenuItem();

    
        submenuItem = new MenuItem();
        submenuItem.setValue("Solicitantes");
        submenuItem.setUrl("/faces/paginas/Solicitante.xhtml");
        submenu.getChildren().add(submenuItem);
//        submenuItem = new MenuItem();
//        submenuItem.setValue("Universidades");
//        submenuItem.setUrl("/faces/paginas/Universidades.xhtml");
//        submenu.getChildren().add(submenuItem);
        submenuItem = new MenuItem();
        submenuItem.setValue("Carreras Externas");
        submenuItem.setUrl("/faces/paginas/CarreraExterna.xhtml");
        submenu.getChildren().add(submenuItem);
        submenuItem = new MenuItem();
        submenuItem.setValue("Materias Externas");
        submenuItem.setUrl("/faces/paginas/Materia.xhtml");
        submenu.getChildren().add(submenuItem); 
      
      
        menu.addSubmenu(submenu);

        submenu = new Submenu();
        submenu.setLabel("Equiparaciones");

        submenuItem = new MenuItem();
        submenuItem.setValue("Cursos");
        submenuItem.setUrl("/faces/paginas/ListarEquiparaciones.xhtml");
        submenu.getChildren().add(submenuItem);
        
        submenuItem = new MenuItem();
        submenuItem.setValue("Cursos (de Oficio)");
        submenuItem.setUrl("/faces/paginas/ListaEquiparacionesOficio.xhtml");
        submenu.getChildren().add(submenuItem); 
        
        menu.addSubmenu(submenu);

     
        
        
//        submenu = new Submenu();
//        submenu.setLabel("Equivalencias");

//        submenuItem = new MenuItem();
//        submenuItem.setValue("Mantenimiento");
//        submenuItem.setUrl("/faces/paginas/ListaEquivalencias.xhtml");
//        submenu.getChildren().add(submenuItem);
//        menu.addSubmenu(submenu);

        submenu = new Submenu();
        submenu.setLabel("Apelaciones");

        submenuItem = new MenuItem();
        submenuItem.setValue("Mantenimiento");
        submenuItem.setUrl("/faces/paginas/ListaApelaciones.xhtml");
        submenu.getChildren().add(submenuItem);
        menu.addSubmenu(submenu);
        
//         submenu = new Submenu();
//        submenu.setLabel("Seguridad");

//        submenuItem = new MenuItem();
//        submenuItem.setValue("Usuarios");
//        submenuItem.setUrl("/faces/paginas/ListaUsuarios.xhtml");
//        submenu.getChildren().add(submenuItem);
//        submenuItem = new MenuItem();
//        submenuItem.setValue("Bitacora");
//        submenuItem.setUrl("/faces/paginas/BitacoraSolicitudes.xhtml");
//        submenu.getChildren().add(submenuItem);
//        menu.addSubmenu(submenu);
//        
//        submenu = new Submenu();
//        submenu.setLabel("Ayuda");
//        
//        submenuItem = new MenuItem();
//        submenuItem.setValue("Manual de Usuario");
//        submenuItem.setUrl("/faces/paginas/manual.pdf");
//        submenu.getChildren().add(submenuItem);
//        submenuItem = new MenuItem();
//        
//        menu.addSubmenu(submenu);
    }

    
    
    
    
    public void EncargadoArea() {
        menu = new DefaultMenuModel();
        Submenu submenu = new Submenu();
        submenu.setLabel("Administración");

        MenuItem submenuItem = new MenuItem();

        submenuItem.setValue("Solicitantes");
        submenuItem.setUrl("/faces/paginas/Solicitante.xhtml");
        submenu.getChildren().add(submenuItem);
        submenuItem = new MenuItem();
        submenuItem.setValue("Universidades");
        submenuItem.setUrl("/faces/paginas/Universidades.xhtml");
        submenu.getChildren().add(submenuItem);
        submenuItem = new MenuItem();
        submenuItem.setValue("Carreras Externas");
        submenuItem.setUrl("/faces/paginas/CarreraExterna.xhtml");
        submenu.getChildren().add(submenuItem);
        submenuItem = new MenuItem();
        submenuItem.setValue("Materias Externas");
        submenuItem.setUrl("/faces/paginas/Materia.xhtml");
        submenu.getChildren().add(submenuItem);
        menu.addSubmenu(submenu);

        submenu = new Submenu();
        submenu.setLabel("Equiparación");

        submenuItem = new MenuItem();
        submenuItem.setValue("Mantenimeinto");
        submenuItem.setUrl("/faces/paginas/ListarEquiparaciones.xhtml");
        submenu.getChildren().add(submenuItem);
        menu.addSubmenu(submenu);

        submenu = new Submenu();
        submenu.setLabel("Equivalencias");

        submenuItem = new MenuItem();
        submenuItem.setValue("Mantenimiento");
        submenuItem.setUrl("/faces/paginas/ListaEquivalencias.xhtml");
        submenu.getChildren().add(submenuItem);
        menu.addSubmenu(submenu);

        submenu = new Submenu();
        submenu.setLabel("Apelaciones");

        submenuItem = new MenuItem();
        submenuItem.setValue("Mantenimiento");
        submenuItem.setUrl("/faces/paginas/ListaApelaciones.xhtml");
        submenu.getChildren().add(submenuItem);
        menu.addSubmenu(submenu);

        submenu = new Submenu();
        submenu.setLabel("Ayuda");
        menu.addSubmenu(submenu);
    }

    public void Salir() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioBean");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("MenuBean");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/faces/login.jsp");
    }

    public void accionSalir() {

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        Iterator it = sessionMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry ent = (Map.Entry) it.next();
     /*       if (ent.getValue() instanceof SerializableContextualInstanceImpl) {
                SerializableContextualInstanceImpl impl = (SerializableContextualInstanceImpl) ent.getValue();
                if (impl.getInstance() instanceof Dr_siseg_usuarioBean || impl.getInstance() instanceof MenuBean) {
                    it.remove();
                }  
            }   */
        }//WHILE 
        try {
            context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/faces/paginas/Login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MenuBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MenuModel getMenu() {
        return menu;
    }

    public void setMenu(MenuModel menu) {
        this.menu = menu;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
package cr.ac.una.reg.info.beans;

public class CarreraExternaBean {

    private int car_id;
    private UniversidadBean universidad;
    private String car_descripcion;

    public CarreraExternaBean() {
        universidad = new UniversidadBean();
    }

    public CarreraExternaBean(int p_car_id, int p_uni_id, String p_car_descripcion) {
        car_id = p_car_id;
        car_descripcion = p_car_descripcion;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int Car_id) {
        this.car_id = Car_id;
    }

    public UniversidadBean getUniversidad() {
        return universidad;
    }

    public void setUniversidad(UniversidadBean universidad) {
        this.universidad = universidad;
    }

    public String getCar_descripcion() {
        return car_descripcion;
    }

    public void setCar_descripcion(String Car_descripcion) {
        this.car_descripcion = Car_descripcion;
    }
}

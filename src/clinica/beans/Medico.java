package clinica.beans;

public class Medico extends Usuario {

    private String especialidad = null;

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Medico{" + "especialidad=" + especialidad + "}   " +  super.toString();
    }
    
    
}

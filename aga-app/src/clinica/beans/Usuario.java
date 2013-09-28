package clinica.beans;
public abstract class Usuario {
  
    public String pass = null;
    public String rut = null;
    public String nombre = null;
    public String apellido = null;
    
    public String getPass(){
        return pass;
    }
    
    public void setPass(String pass){
        this.pass = pass;
    }
    
    public String getRut(){
        return rut;
    }
    
    public void setRut(String rut){
        this.rut = rut;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getApellido(){
        return apellido;
    }
    
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    
    

    @Override
    public String toString() {
        return "Usuario{" + "pass=" + pass + ", rut=" + rut + ", nombre=" + nombre + ", apellido=" + apellido +'}';
    }  
    
}
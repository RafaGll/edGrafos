package edLineares2020_21;

/**
 * Clase que nos permite crear personas añadiendo su nombre y edad así como modificarlos y obtener valores de ellos
 *
 * @author Iván Cantalejo, Rafael González y Daniel Martín. Universidad de Castilla la Mancha.
 *
 * @since 19/11/2020
 * @version 1.0
 */
public class Persona implements  Comparable<Persona> {
    String nombre;
    int edad;

    public Persona(String name, int age){
        nombre= name;
        edad=age;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int compareTo (Persona o) {
        int resultado = 0;
        if (this.edad<o.edad)
            resultado=-1;
        else
            resultado=1;
        return resultado;
    }

    public String toString() {
        return nombre + ", " + edad + "\n";
    }
}

import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private final String nombreApellido;
    private final int legajo;
    private final List<String> materiasAprobadas;

    public Estudiante(String nombreApellido, int legajo){
        this.nombreApellido = nombreApellido;
        this.legajo = legajo;
        this.materiasAprobadas = new ArrayList<>();
    }

    public String getNombreApellido() {
        return nombreApellido;
    }
    public int getLegajo() {
        return legajo;
    }
    public List<String> getMateriasAprobadas() {
        return materiasAprobadas;
    }

}

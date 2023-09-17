import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        try{
            String rutaArchivo = "src/aprobaciones.txt";
            mostrarMenu(armarDiccionario(leerArchivo(rutaArchivo)));
        }
        catch (Exception e){
            System.out.println("Ocurrio un ERROR desconocido.");
        }
    }

    public static List<String> leerArchivo(String rutaArchivo)
    {
        List<String> lineasDelArchivo = new ArrayList<>();
        try {
            FileReader fr = new FileReader(rutaArchivo);
            BufferedReader br = new BufferedReader(fr);

            //Leer linea por linea
            String linea;
            while ((linea = br.readLine()) != null){
                lineasDelArchivo.add(linea);
            }
            br.close();
        }catch (Exception e){
            System.out.println("Ocurrio un Error.");
        }
        return lineasDelArchivo;
    }

    public static HashMap<Integer, Estudiante> armarDiccionario(List<String> lineasDelArchivo)
    {
        // Crear un diccionario (HashMap) para almacenar a los estudiantes
        HashMap<Integer, Estudiante> diccionario = new HashMap<>();
        String[] datos;
        for (String linea: lineasDelArchivo) {
            datos = linea.split(",");
            int legajo = Integer.parseInt(datos[0]);
            String nombreApellido = datos[1];
            String materiaAprobada = datos[2];

            // Verificar si el estudiante ya existe en el diccionario
            if (diccionario.containsKey(legajo)) {
                diccionario.get(legajo).getMateriasAprobadas().add(materiaAprobada);
            } else {
                Estudiante estudiante = new Estudiante(nombreApellido,legajo);
                estudiante.getMateriasAprobadas().add(materiaAprobada);
                diccionario.put(legajo, estudiante);
            }
        }
        return diccionario;
    }


    public static void mostrarMenu(HashMap<Integer,Estudiante> diccionario)
    {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Menu de opciones");
            System.out.println("1) Ingreso de Legajo");
            System.out.println("2) Salir");
            int opcion = sc.nextInt();
            while (opcion != 2) {
                switch (opcion) {
                    case 1:
                        System.out.println("Elegir numero de legajo: ");
                        int legajo = sc.nextInt();
                        if (legajo < 0) {
                            throw new NumeroNegativoException();
                        }
                        else {
                            diccionario.forEach((key, value) -> {
                                if (key.equals(legajo)) {
                                    //mostrar en consola resultados
                                    System.out.println(key + " " + value.getNombreApellido() + " " + value.getMateriasAprobadas());
                                }
                                else {
                                    System.out.println("Legajo invalido");
                                }
                            });
                        }
                        break;
                    default:
                        System.out.println("Ingresaste una opcion incorrecta.");
                        break;
                }
                System.out.println("Menu de opciones");
                System.out.println("1) Ingreso de Legajo");
                System.out.println("2) Salir");
                opcion = sc.nextInt();
            }
            System.out.println("Aplicacion Finalizada.");
        }catch (InputMismatchException ime){
            System.out.println("Ingresaste caracter en lugar de numero.");
        }catch (NumeroNegativoException nne){
            System.out.println(nne.getMensaje());
        }catch (Exception e) {
            System.out.println("Ocurrio un ERROR desconocido.");
        }
    }


}
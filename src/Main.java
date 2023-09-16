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
        String[] infoEstudiante;
        //Armado del diccionario
        HashMap<Integer, Estudiante> diccionario = new HashMap<Integer, Estudiante>();
        for (String linea: lineasDelArchivo) {
            infoEstudiante = linea.split(",");
            Estudiante estudiante = new Estudiante(infoEstudiante[1], Integer.parseInt(infoEstudiante[0]));
            estudiante.getMateriasAprobadas().add(infoEstudiante[2]);
            diccionario.put(estudiante.getLegajo(),estudiante);
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
                        } else {
                            diccionario.forEach((key, value) -> {
                                if (key.equals(legajo)) { //si numero legajo esta en el diccionario
                                    //mostrar en consola resultados
                                    System.out.println(key + " " + value.getNombreApellido() + " " + value.getMateriasAprobadas());
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
                //verficar si hay nuevas materias aprobadas
                //si hay nuevas
                //agregar en la lista
                //mostrar datos (legajo - nombre - listado de materias)

                //si numero legajo no esta en el diccionario
                //agregar estudiante con sus materias aprobadas
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
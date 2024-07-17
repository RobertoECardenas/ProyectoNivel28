package SoloCesar;
import java.io.*;
import java.util.Scanner;

public class CifradoCesar {
    private final String abecedario = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáéíóúÁÉÍÓÚäëïöüÄËÏÖÜ0123456789¿?¡!()[]{}.,:;-_=<>#$%&/°|¬*+@ ";
    private final String acebedario = " @+*¬|°/&%$#><=_-;:,.}{][)(!¡?¿9876543210ÜÖÏËÄüöïëäÚÓÍÉÁúóíéáZYXWVUTSRQPONMLKJIHGFEDCBAzyxwvutsrqponmlkjihgfedcba";
    public String nombre;

    public void cifrado() throws IOException {
        System.out.print("Hola amigo, por favor introduce un texto (digite 'exit' al terminar)...\n"); //Solicitar un texto
        BufferedReader textoEntrada = new BufferedReader(new InputStreamReader(System.in)); //Introducción del texto
        String lectura = leerTexto(textoEntrada);
        System.out.println(lectura);


        //Guardar el texto ingresado (texto sin cifrar) en un .txt
        System.out.println("Dame un nombre para guardar tu archivo: \n");
        nombre = new BufferedReader(new InputStreamReader(System.in)).readLine();
        nombreArchivo(lectura, nombre);

        //Metodo 1. Validación de desplazamientos
        int des = desplazo();

        //Guardar el archivo con el numero de desplazamientos en un .txt
        archivoDesplazo(des);

        if (des>=0){
            //Metodo 2.1. Codificación de texto si desplazamiento es numero positivo
            String lineaCodificada = codificar(abecedario, lectura, des);
            System.out.println("El texto codificado es el siguiente: " + lineaCodificada);
            //Guardar el texto modificado (texto ya cifrado) en un .txt
            nombreEncriptado(lineaCodificada, nombre);
        }
        else {
            //Metodo 2.2. Codificación de texto si desplazamiento es numero negativo
            String lineaCodificada = codificarNegativo(acebedario, lectura, des);
            System.out.println("El texto codificado es el siguiente: " + lineaCodificada);
            //Guardar el texto modificado (texto ya cifrado) en un .txt
            nombreEncriptado(lineaCodificada, nombre);
        }
    }
    public String leerTexto(BufferedReader buff) throws IOException {
        StringBuilder texto = new StringBuilder();
        String linea;
        while ((linea = buff.readLine()) != null && !linea.equals("exit")) {
            texto.append(linea + "\n");
        }
        return texto.toString();
    }
    public void nombreArchivo(String linea, String nombre) throws IOException {
        String path = "C:/Users/ASRock/Desktop/" + nombre + ".txt";
        BufferedOutputStream bos = new BufferedOutputStream((new FileOutputStream(path)));
        bos.write(linea.getBytes());
        bos.flush();
        bos.close();
    }
    public int desplazo (){
        System.out.println("Por favor digita un número entero de desplazamientos: "); //Solicitar desplazamientos
        Scanner entrada = new Scanner(System.in); //Entrada de elección de desplazamientos
        int desplazamiento = 0;
        desplazamiento = entrada.nextInt();
        System.out.println("El número de desplazamientos son: " + desplazamiento + "\n");
        return desplazamiento;
    }
    public void archivoDesplazo(Integer desplazamiento) throws IOException {
        String path = "C:/Users/ASRock/Desktop/cambio.txt";
        BufferedOutputStream bos = new BufferedOutputStream((new FileOutputStream(path)));
        bos.write(("desplazamiento=" + desplazamiento).getBytes());
        bos.flush();
        bos.close();
    }
    private String codificar (String abecedario, String linea, Integer desplazamiento){
        String codificado = ""; //Nueva variable para el texto codificado
        char lecturaCaracter; //Inicio de variable del tipo char
        for(int i = 0; i < linea.length(); i++){
            lecturaCaracter = linea.charAt(i); //La variable char obtiene el valor del caracter de la linea de entrada en forma de iteración
            int posicion = abecedario.indexOf(lecturaCaracter); //Se guarda la posición del indice del caracter dentro del abecedario
            if(posicion == -1){ //Si el caracter no existe (da -1) se mantiene el mismo caracter
                codificado += lecturaCaracter;
            }
            else { //Si el caracter existe será reemplazado de acuerdo al numero de desplazamiento que el usuario entrega
                codificado += abecedario.charAt((posicion + desplazamiento) % abecedario.length()); //Se da vuelta por si es un caracter al final se repita la variable de abecedario
            } //Regresa a iterar al siguiente caracter dentro de la variable linea
        }
        return codificado; //retorno de variable ya codificada
    }
    private String codificarNegativo (String acebedario, String linea, Integer desplazamiento){
        String codificado = ""; //Nueva variable para el texto codificado
        char lecturaCaracter; //Inicio de variable del tipo char
        for(int i = 0; i < linea.length(); i++){
            lecturaCaracter = linea.charAt(i); //La variable char obtiene el valor del caracter de la linea de entrada en forma de iteración
            int posicion = acebedario.indexOf(lecturaCaracter); //Se guarda la posición del indice del caracter dentro del abecedario
            if(posicion == -1){ //Si el caracter no existe (da -1) se mantiene el mismo caracter
                codificado += lecturaCaracter;
            }
            else { //Si el caracter existe será reemplazado de acuerdo al numero de desplazamiento que el usuario entrega
                codificado += acebedario.charAt((posicion - desplazamiento) % acebedario.length()); //Se da vuelta por si es un caracter al final se repita la variable de abecedario
            } //Regresa a iterar al siguiente caracter dentro de la variable linea
        }
        return codificado; //retorno de variable ya codificada
    }
    public void nombreEncriptado(String lineaCodificada, String nombre) throws IOException {
        String path = "C:/Users/ASRock/Desktop/" + nombre + "_encriptado.txt";
        BufferedOutputStream bos = new BufferedOutputStream((new FileOutputStream(path)));
        bos.write(lineaCodificada.getBytes());
        bos.flush();
        bos.close();
    }

}


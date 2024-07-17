package Cifrado;

import SoloCesar.CifradoCesar;

import java.io.*;


public class DecifradoCesar {
    private final String abecedario = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáéíóúÁÉÍÓÚäëïöüÄËÏÖÜ0123456789¿?¡!()[]{}.,:;-_=<>#$%&/°|¬*+@ ";

    public void decrifrado(String nombre) throws IOException {
        System.out.println("Hola amigo, dame un documento para decodificarlo..."); //Solicitar nombre/archivo
        BufferedReader archivo = new BufferedReader(new InputStreamReader(System.in)); //Introducción del texto
        String path = archivo.readLine(); //Texto se guarda en variable "path"
        String textoEncriptado = leerArchivo(path); //Lee el archivo que se le pasa
        System.out.println(textoEncriptado);

        System.out.println("Ahora dame un documento donde se muestre el número de desplazamiento"); //Solicitar nombre/archivo
        BufferedReader clave = new BufferedReader(new InputStreamReader(System.in)); //Introducción del texto
        String path_clave = clave.readLine(); //Texto se guarda en variable "path_clave"
        int desplazos = leerNumDesplazamientos(path_clave); //Lee el archivo que se le pasa
        System.out.println("Los desplazamientos fueron: " + desplazos);


        if (desplazos>= 0){
            String lineaDecodificada = decodificarPositivo(abecedario, textoEncriptado, desplazos);
            System.out.println("El texto decodificado dice: " + lineaDecodificada);
            nombreDesencriptado(lineaDecodificada, nombre); //Agregar nombre
        }
        else{
            String lineaDecodificada = decodificarNegativo(abecedario, textoEncriptado, desplazos);
            System.out.println("El texto decodificado dice: \n" + lineaDecodificada);

            nombreDesencriptado(lineaDecodificada, nombre); //Agregar nombre
        }
    }

    public String leerArchivo(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File (path)); //Introducir un archivo
        BufferedInputStream bis = new BufferedInputStream((fileInputStream));
        byte[] allBytes = bis.readAllBytes(); //Leer el texto de la ubicación del archivo
        String output = new String(allBytes); //Guardar en la variable output
        return output;
    }
    public int leerNumDesplazamientos(String path_clave) throws IOException {
        int num_cambios = 0;
        FileInputStream fileInputStream = new FileInputStream(new File (path_clave)); //Introducir un archivo
        BufferedInputStream bis = new BufferedInputStream((fileInputStream));
        byte[] allBytes = bis.readAllBytes(); //Leer el texto de la ubicación del archivo
        String output = new String(allBytes); //Guardar en la variable output
        String numero = output.substring(15,output.length()); //Eliminar el inicio del texto para que solo quede el numero
        int des = Integer.parseInt(numero);
        return des;
    }
    private String decodificarPositivo (String abecedario, String linea, Integer desplazamiento){
        String codificado = ""; //Nueva variable para el texto codificado
        char lecturaCaracter; //Inicio de variable del tipo char
        for(int i = 0; i < linea.length(); i++){
            lecturaCaracter = linea.charAt(i); //La variable char obtiene el valor del caracter de la linea de entrada en forma de iteración
            int posicion = abecedario.indexOf(lecturaCaracter); //Se guarda la posición del indice del caracter dentro del abecedario
            if(posicion == -1){ //Si el caracter no existe (da -1) se mantiene el mismo caracter
                codificado += lecturaCaracter;
            }
            else { //Si el caracter existe será reemplazado de acuerdo al numero de desplazamiento que el usuario entrega
                if(posicion-desplazamiento<0){
                    codificado += abecedario.charAt( abecedario.length() + (posicion - desplazamiento));
                }
                else{
                    codificado += abecedario.charAt((posicion - desplazamiento) % abecedario.length()); //Se da vuelta por si es un caracter al final se repita la variable de abecedario
                }
            } //Regresa a iterar al siguiente caracter dentro de la variable linea
        }
        return codificado; //retorno de variable ya codificada
    }
    private String decodificarNegativo (String abecedario, String linea, Integer desplazamiento){
        String codificado = ""; //Nueva variable para el texto codificado
        char lecturaCaracter; //Inicio de variable del tipo char
        for(int i = 0; i < linea.length(); i++){
            lecturaCaracter = linea.charAt(i); //La variable char obtiene el valor del caracter de la linea de entrada en forma de iteración
            int posicion = abecedario.indexOf(lecturaCaracter); //Se guarda la posición del indice del caracter dentro del abecedario
            if(posicion == -1){ //Si el caracter no existe (da -1) se mantiene el mismo caracter
                codificado += lecturaCaracter;
            }
            else { //Si el caracter existe será reemplazado de acuerdo al numero de desplazamiento que el usuario entrega
                if(posicion-desplazamiento<0){
                    codificado += abecedario.charAt( abecedario.length() + (posicion - desplazamiento));
                }
                else{
                    codificado += abecedario.charAt((posicion - desplazamiento) % abecedario.length()); //Se da vuelta por si es un caracter al final se repita la variable de abecedario
                }
            } //Regresa a iterar al siguiente caracter dentro de la variable linea
        }
        return codificado; //retorno de variable ya codificada
    }
    public void nombreDesencriptado(String lineaCodificada, String nombre) throws IOException {
        String path = "C:/Users/ASRock/Desktop/" + nombre + "_desencriptado.txt";
        BufferedOutputStream bos = new BufferedOutputStream((new FileOutputStream(path)));
        bos.write(lineaCodificada.getBytes());
        bos.flush();
        bos.close();
    }
}

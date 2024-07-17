import Cifrado.DecifradoCesar;
import SoloCesar.CifradoCesar;

import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {


        CifradoCesar cifrado = new CifradoCesar();
        cifrado.cifrado();

        DecifradoCesar decifrado = new DecifradoCesar();
        decifrado.decrifrado(cifrado.nombre);

    }
}
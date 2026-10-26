import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Ejemplo de codificación UTF-8 en Java.
 *
 * Este programa escribe en un fichero la cadena "A ñ € 😀" utilizando la
 * codificación UTF-8. A continuación, lee los bytes del fichero y muestra
 * cada uno en formato hexadecimal y binario.
 *
 * Objetivo:
 * - Visualizar cómo los caracteres simples (ASCII) ocupan 1 byte.
 * - Ver que otros caracteres (como "ñ", "€" o emojis) ocupan 2, 3 o 4 bytes.
 * - Comprender cómo funciona la representación variable de UTF-8.
 */

public class UTF8Demo {
    public static void main(String[] args) {
        // Caracteres a probar
        String texto = "A ñ € 😀";

        // 1. Escribir el texto en un fichero con UTF-8
        try (FileOutputStream fos = new FileOutputStream("utf8_demo.txt")) {
            fos.write(texto.getBytes("UTF-8"));
            System.out.println("Texto escrito en utf8_demo.txt con codificación UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. Leer los bytes del fichero y mostrarlos en hexadecimal y binario
        try {
            byte[] bytes = Files.readAllBytes(Paths.get("utf8_demo.txt"));

            System.out.println("\nBytes leídos:");
            for (byte b : bytes) {
                // Convertir a valor sin signo
                int valor = b & 0xFF;

                // Mostrar en hexadecimal
                String hex = String.format("%02X", valor);

                // Mostrar en binario (relleno a 8 bits)
                String binario = String.format("%8s", Integer.toBinaryString(valor)).replace(' ', '0');

                System.out.println("Hex: " + hex + "  Bin: " + binario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

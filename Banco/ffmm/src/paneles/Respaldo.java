/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneles;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;


/**
 *
 * @author DarthZulu
 */
public class Respaldo {

    public static void Resp(String args[]) {
        try {
            Process p = Runtime.getRuntime().exec("mysqldump -u roor -p  tienda");

            InputStream is = p.getInputStream();//Pedimos la entrada
            FileOutputStream fos = new FileOutputStream("backup_tienda.sql"); //creamos el archivo para le respaldo
            byte[] buffer = new byte[1000]; //Creamos una variable de tipo byte para el buffer

            int leido = is.read(buffer); //Devuelve el número de bytes leídos o -1 si se alcanzó el final del stream.
            while (leido > 0) {
                fos.write(buffer, 0, leido);//Buffer de caracteres, Desplazamiento de partida para empezar a escribir caracteres, Número de caracteres para escribir
                leido = is.read(buffer);
            }

            fos.close();//Cierra respaldo
        } catch (Exception e) {

        }
    }

}

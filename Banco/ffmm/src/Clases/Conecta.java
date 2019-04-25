/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin
 */
public class Conecta {

    public static Connection conexion = null;
    public static Connection getConexion() {
        try {
            conexion = null;
            String servidorBD = "jdbc:mysql://localhost/ffmm";
            String usuarioDB = "root";
            String passwordDB = "";
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(servidorBD, usuarioDB, passwordDB);
            //System.out.println("Conectado a la BD");
        } catch (ClassNotFoundException | SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Base de datos no disponible", "Error", JOptionPane.INFORMATION_MESSAGE);
            //En caso de error si es que la base de datos no exista, o no esté xampp activado, simplemente cierra el sistema
            System.exit(0);
        }
        return conexion;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LabExamen;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainTest {
    public static void main(String[] args) {
        try {
            PSNUsers psnUsers = new PSNUsers("psn.dat");

            // Agregar usuarios
            psnUsers.addUser("usuario");
           
            System.out.println("SE AGREGO USUARIO");
            psnUsers.addUser("nicole");
            System.out.println("SE AGREGO NICOLE");

            

            // Agregar trofeos
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(new Date());
            System.out.println(date);

            psnUsers.addTrophyTo("NICOLE", "Juego1", "Trofeo1", Trophy.PLATINO, date);
            System.out.println("SE AGREGARON TROFEOS");
            psnUsers.addTrophyTo("usuario", "Juego2", "Trofeo2", Trophy.ORO, date);
            System.out.println("SE AGREGARON TROFEOS");

            // Obtener información de un usuario
            psnUsers.playerInfo("NICOLE");
            System.out.println("INFO NICOLE");
            
            // Desactivar un usuario
            psnUsers.deactivateUser("usuario");
            System.out.println("SE DESACTIVO USUARIO");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

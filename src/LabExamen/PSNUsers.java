package LabExamen;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author tomea
 */
public class PSNUsers {
   RandomAccessFile raf; 
   HashTable users;
   long tamaño=0;
   
  

    public PSNUsers() throws IOException {
        raf = new RandomAccessFile("psn.dat", "rw");
//        this.users=new HashTable();
        reloadHashTable();
    }

   private void reloadHashTable() throws IOException {
        raf.seek(0); 
        users=new HashTable();
        while (raf.getFilePointer() < raf.length()) {
                System.out.println("\nENTRO A WHILE DE RELOAD\n");
                String username = raf.readUTF();
                raf.skipBytes(9);
                long position = raf.getFilePointer();
                users.add(username, position);
                System.out.println(username);
        }
    }

   
   //LISTO
    public boolean addUser(String username) throws IOException {
        if (users.search(username) != -1) {
            System.out.println("no se hallo nombre");
            return false;
        }
        long position = raf.length(); // Obtener la posición al final del archivo
        raf.seek(position); // Mover al final del archivo
        raf.writeUTF(username); // Escribir el nombre de usuario
        raf.writeInt(0); // agrega puntos
        raf.writeInt(0); // agrega trofeos
        raf.writeBoolean(true); // usuario activo
        users.add(username, raf.getFilePointer());
        System.out.println("se agrego");
        return true;
    }
    
    //LISTO
    public void deactivateUser(String username) throws IOException {
        long position = users.search(username);
        if (users.search(username) == -1) {
            System.out.println("no se hallo");
            return;
        }
        if (position != -1) {
            raf.seek(position); // Mover al registro correspondiente
            raf.writeBoolean(false); // Marcar como inactivo
            users.remove(username); // Eliminar de la tabla hash
        }
    }
    
    public boolean addTrophyTo(String username, String trophyGame, String trophyName, Trophy type) throws IOException {
        if (users.search(username) == -1) {
            return false;
        }
        
        long position = users.search(username);
        if (position != -1) {
            raf.seek(position - 9);
            int points = raf.readInt();
            int trofeos = raf.readInt();
            points += type.points;
            System.out.println("Puntos" + points);
            trofeos++;            
            System.out.println("Trofeos" + trofeos);
            raf.seek(position - 9);
            raf.writeInt(points);
            raf.writeInt(trofeos);

            // write trophy to file
            RandomAccessFile rafTrophy = new RandomAccessFile("psn_trphy", "rw");
            rafTrophy.seek(rafTrophy.length());
            rafTrophy.writeUTF(username);
            rafTrophy.writeUTF(type.name());
            rafTrophy.writeUTF(trophyGame);
            rafTrophy.writeUTF(trophyName);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
            Date date = new Date();  
            rafTrophy.writeUTF(formatter.format(date));
        }
        return true;
    }

    public String playerInfo(String username) throws IOException {
         if (users.search(username) == -1) {
            System.out.println("no se hallo");
            return "";
        }
        long position = users.search(username);
        StringBuilder info = new StringBuilder();
        if (position != -1) {
            raf.seek(position - 9 - username.length() - 2); // 2 bytes for UTF string length
            info.append("User: " + raf.readUTF() + "\n");
            info.append("Puntos: " + raf.readInt() + "\n");
            int trofeos = raf.readInt();
            info.append("Trofeos: " + trofeos + "\n");
            System.out.println(trofeos);
            info.append("Activo: " + raf.readBoolean() + "\n");

            // print trophies
            RandomAccessFile trophyFile = new RandomAccessFile("psn_trphy", "rw");
            trophyFile.seek(0);
            while (trophyFile.getFilePointer() < trophyFile.length()) {
                if (trophyFile.readUTF().equals(username)) {
                    info.append(trophyFile.readUTF() + " - " + trophyFile.readUTF() + " - " + trophyFile.readUTF() + " - " + trophyFile.readUTF() + "\n");
                } else {
                    for (int i = 0; i < 3; i++) { // skip other user's trophies
                        trophyFile.readUTF();
                    }
                }
            }
        }
        return info.toString();
    }
}

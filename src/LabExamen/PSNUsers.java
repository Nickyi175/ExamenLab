package LabExamen;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author tomea
 */
public class PSNUsers {
   private RandomAccessFile raf; 
   private HashTable users;
  

    public PSNUsers(String fileName) throws IOException {
        raf = new RandomAccessFile(fileName, "rw");
        users = new HashTable(100); // Adjust the size as needed
        reloadHashTable();
    }

    private void reloadHashTable() throws IOException {
        raf.seek(0);
        while (raf.getFilePointer() < raf.length()) {
            String username = raf.readUTF();
            long position = raf.getFilePointer();
            users.add(username, position);
        }
    }

    public void addUser(String username) throws IOException {
        raf.seek(raf.length());
        raf.writeUTF(username);
        long position = raf.getFilePointer();
        users.add(username, position);
    }

    public void deactivateUser(String username) throws IOException {
        long position = users.Search(username);
        if (position != -1) {
            raf.seek(position - 2); // Move back to the "active" flag position
            raf.writeBoolean(false); // Mark the user as inactive
            users.remove(username);
        }
    }

    public void addTrophyTo(String username, String trophyGame, String trophyName, Trophy type, String date) throws IOException {
        long position = users.Search(username);
        if (position != -1) {
            raf.seek(position);
            raf.writeUTF(trophyGame);
            raf.writeUTF(trophyName);
            raf.writeUTF(type.name());
            raf.writeUTF(date);
        }
    }

    public void playerInfo(String username) throws IOException {
        long position = users.Search(username);
        if (position != -1) {
            raf.seek(position);
            boolean active = raf.readBoolean();
            int trophyCount = raf.readInt();
            int trophyPoints = raf.readInt();

            System.out.println("Username: " + username);
            System.out.println("Active: " + active);
            System.out.println("Trophy Count: " + trophyCount);
            System.out.println("Trophy Points: " + trophyPoints);

            while (raf.getFilePointer() < raf.length()) {
                String trophyGame = raf.readUTF();
                String trophyName = raf.readUTF();
                String trophyType = raf.readUTF();
                String date = raf.readUTF();
                System.out.println(date + " - " + trophyType + " - " + trophyGame + " - " + trophyName);
            }
        } else {
            System.out.println("User not found: " + username);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LabExamen;

/**
 *
 * @author tomea
 */
public class Entry {
    String user;
    long posicion;
    Entry siguiente;

    public Entry(String user,long posicion) {
        this.user=user;
        this.posicion = posicion;
        this.siguiente = null;
    }
 
}

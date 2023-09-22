package LabExamen;

public class HashTable {

    Entry[] lista;

    public HashTable(int tamaño) {
        lista = new Entry[tamaño];
    }

    void add(String username, long pos) {
        System.out.println("entro a add de hash");
        int index = Math.abs(username.hashCode() % lista.length);
        Entry entry = new Entry(username, pos);
        //como esta null se le agrega la primer persona a la primer posicion, en la que esta
        if (lista[index] == null) {
            System.out.println("la lista esta null");
            lista[index] = entry;
            System.out.println("se agrego primer persona ");
            /*
            sino esta null, toma la posicion actual en la lista y mientras (en el while) la siguiente posicion no este null, 
            lo mueve a la siguiente posicion hasta que llegue a null y ahi la variable toma la ultima posicion
            sale del while y ahi se guarda el nuevo registro
             */
        } else {
            System.out.println("la lista no esta null");
            Entry current = lista[index];
            System.out.println("tomo la posicion");
            while (current.siguiente != null) {
                current = current.siguiente;
                System.out.println("buscando posicion vacia...");
            }
            System.out.println("salio del while, tomo la posicion y agrego usuario");
            current.siguiente = entry;
        }
        System.out.println("sale del add");
    }

    void remove(String username) {
        int index = Math.abs(username.hashCode() % lista.length);
        Entry current = lista[index];
        Entry prev = null;
        while (current != null) {
            if (current.user.equals(username)) {
                if (prev == null) {
                    lista[index] = current.siguiente;
                } else {
                    prev.siguiente = current.siguiente;
                }
                return;
            }
            prev = current;
            current = current.siguiente;
        }

    }

}

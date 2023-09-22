package LabExamen;

public class HashTable {

    Entry[] lista;

    public HashTable(int tamaño) {
        lista = new Entry[tamaño];
    }

    //FUNCION ADD
    void add(String username, long posicion) {
        System.out.println("entro a add de hash");
        int indice = Math.abs(username.hashCode() % lista.length);
        Entry entry = new Entry(username, posicion);
        //como esta null se le agrega la primer persona a la primer posicion, en la que esta
        if (lista[indice] == null) {
            System.out.println("la lista esta null");
            lista[indice] = entry;
            System.out.println("se agrego primer persona ");
            /*
            sino esta null, toma la posicion actual en la lista y mientras (en el while) la siguiente posicion no este null, 
            lo mueve a la siguiente posicion hasta que llegue a null y ahi la variable toma la ultima posicion
            sale del while y ahi se guarda el nuevo registro
             */
        } else {
            System.out.println("la lista no esta null");
            Entry current = lista[indice];
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
    
    //FUNCION REMOVE
    void remove(String username) {
        System.out.println("entro a remove");
        int indice = Math.abs(username.hashCode() % lista.length);
        System.out.println("tomo indice");
        Entry actual = lista[indice];
        Entry anterior = null;
        while (actual != null) {
            System.out.println("entro a while mientra actual no sea null");
            if (actual.user.equals(username)) {
                System.out.println("entro a if si user concuerda con el user ingresado");
                if (anterior == null) {
                    System.out.println("entro a if si la anterior esta null");
                    lista[indice] = actual.siguiente;
                    System.out.println("movio la posicion de la lista");
                } else {
                    System.out.println("else, la posicion anterior no esta null entonces da nueva posicion");
                    anterior.siguiente = actual.siguiente;
                }
                System.out.println("sale del segundo if  y retorna");
                return;
            }
            System.out.println("sale del primer if");
            anterior = actual;
            System.out.println(anterior);
            actual = actual.siguiente;
            System.out.println(actual);
            System.out.println("sale del while");
        }
        System.out.println("Fin remove");
    }
    
    //FUNCION BUSCAR
    long Search(String username){
        System.out.println("entro a search");
        int indice = Math.abs(username.hashCode() % lista.length);
        Entry actual = lista[indice];
        while (actual != null) {
            System.out.println("entro a while, mientras el registro actual no esta null");
            if (actual.user.equals(username)) {
                System.out.println("entro a if si el user concuerda con el ingresado");
                 System.out.println("retorna la posicion actual"+actual.posicion);
                return actual.posicion;
            }
            System.out.println("salio del if");
            actual = actual.siguiente;
            System.out.println(actual);
        }
        System.out.println("salio del while y si ninguno no esta null retorna -1");
        return -1;
    }

}

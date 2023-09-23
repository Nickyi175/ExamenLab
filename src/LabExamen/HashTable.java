package LabExamen;

public class HashTable {

    Entry lista;


    //FUNCION ADD
     public void add(String username, long position){
         Entry entry=new Entry(username,position);
         if (search(username) != -1) {
             System.out.println("no se hallo nombre");
            return; 
        }
        if (lista==null){
            lista=entry;
            return;
        }
        Entry tmp=lista;
        
            
            
            while(tmp.siguiente!=null){
                    tmp=tmp.siguiente;
                }
                tmp.siguiente=entry;
        
        
             
    }
     
     public void remove(String username) {
        if (lista == null) 
            return;
        if(lista.user.equals(username)) {
            lista = lista.siguiente;
            return;
        }
        Entry temp = lista;
        while (temp.siguiente != null) {
            if (temp.siguiente.user.equals(username)){
                temp.siguiente = temp.siguiente.siguiente;
                return;
            }
            temp = temp.siguiente;
        }
    }
    
    public long search(String username) {
        Entry temp = lista;
        while (temp != null) {
            if (temp.user.equals(username)) {
                return temp.posicion;
            }
            temp = temp.siguiente;
        }
        return -1;
    }
    
    //FUNCION REMOVE
//    void remove(String username) {
//        System.out.println("entro a remove");
//        int indice = Math.abs(username.hashCode() % lista.length);
//        System.out.println("tomo indice");
//        Entry actual = lista[indice];
//        Entry anterior = null;
//        while (actual != null) {
//            System.out.println("entro a while mientra actual no sea null");
//            if (actual.user.equals(username)) {
//                System.out.println("entro a if si user concuerda con el user ingresado");
//                if (anterior == null) {
//                    System.out.println("entro a if si la anterior esta null");
//                    lista[indice] = actual.siguiente;
//                    System.out.println("movio la posicion de la lista");
//                } else {
//                    System.out.println("else, la posicion anterior no esta null entonces da nueva posicion");
//                    anterior.siguiente = actual.siguiente;
//                }
//                System.out.println("sale del segundo if  y retorna");
//                return;
//            }
//            System.out.println("sale del primer if");
//            anterior = actual;
//            System.out.println(anterior);
//            actual = actual.siguiente;
//            System.out.println(actual);
//            System.out.println("sale del while");
//        }
//        System.out.println("Fin remove");
//    }
//    
//    //FUNCION BUSCAR
//    long search(String username){
//        System.out.println("entro a search");
//        int indice = Math.abs(username.hashCode() % lista.length);
//        Entry actual = lista[indice];
//        while (actual != null) {
//            System.out.println("entro a while, mientras el registro actual no esta null");
//            if (actual.user.equals(username)) {
//                System.out.println("entro a if si el user concuerda con el ingresado");
//                 System.out.println("retorna la posicion actual"+actual.posicion);
//                return actual.posicion;
//            }
//            System.out.println("salio del if");
//            actual = actual.siguiente;
//            System.out.println(actual);
//        }
//        System.out.println("salio del while y si ninguno no esta null retorna -1");
//        return -1;
//    }

}

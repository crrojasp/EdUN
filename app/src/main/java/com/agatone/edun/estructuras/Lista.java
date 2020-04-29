package com.agatone.edun.estructuras;


import com.agatone.edun.Clases.archivo;

/**
 *
 * @author cristian
 */



public class Lista {

    private Nodo head;


    public Lista(){
        head=null;
    };


    public boolean insert(archivo arc){
        boolean insertado=false;
        Nodo node=new Nodo(arc);
        Nodo previo,actual;


        actual=head;
        previo=null;
        node=head.getNext();
        head.setNext(node);
        if(head.getNext()== node)
            insertado = true;

        return insertado;
    }


    public Nodo OrdenarNombre(){
        Nodo ordenador=head;
        Nodo antes=null,despues=null;
        while(ordenador!=null){
            if(ordenador.getArc().getNombre().
                    compareToIgnoreCase(ordenador.getNext().getArc().getNombre())<=0){

                if(antes==null){

                }


            }else{
                antes=ordenador;
                ordenador=ordenador.getNext();
            }



        }



        return head;
    }


    public boolean delete(int id){
        boolean del=false;
        Nodo prv,ptr=head;
        while(ptr!=null & ptr.getArc().getId()<id){
            prv=ptr;
            ptr=ptr.getNext();
        }
        if(ptr!=null&ptr.getArc().getId()==id){
            del=true;
            prv=ptr.getNext();
        }
        return del;
    }



    public Nodo buscarId(int id){
        Nodo nodo=head;
        while(nodo!=null){
            if(nodo.getArc().getId()==id){
                return nodo;
            }
            nodo=nodo.getNext();
        }
        return null;
    }


}


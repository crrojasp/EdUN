package com.agatone.edun.estructuras.Hash;


import android.content.Context;

import com.agatone.edun.Clases.archivo;

/**
 *
 * @author cristian
 */



public class List {

    private Node head;
    private Node back;
    private int count=0;

    public List(){
        head=null;
    };


    public boolean insert(archivo arc){
        boolean insertado=false;
        Node node=new Node(arc);
        Node previo,actual;


        actual=head;
        previo=null;
        node=head.getNext();
        head.setNext(node);
        if(head.getNext()== node)
            insertado = true;
        if(insertado)
            count++;
        return insertado;
    }

    public boolean delete(int id) {
        boolean del = false;
        Node prv, ptr = head;
        while (ptr != null & ptr.getArc().getId() < id) {
            prv = ptr;
            ptr = ptr.getNext();
        }
        if (ptr != null & ptr.getArc().getId() == id) {
            del = true;
            prv = ptr.getNext();
        }
        if (del)
            count--;
        return del;
    }

    public Node buscarId(int id){
        Node nodo=head;
        while(nodo!=null){
            if(nodo.getArc().getId()==id){
                return nodo;
            }
            nodo=nodo.getNext();
        }
        return null;
    }

    public Node buscarPosicion(int pos){
        if(count<=0)
            return null;
        if(count==1)
            return head.getNext();

        Node nodo=head;
        int i=0;

        while(i<pos){
            if(nodo==null)
                return null;
            nodo=nodo.getNext();
        }
        return nodo;
    };




    public int getCount(){
        return this.count;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getBack() {
        return back;
    }

    public void setBack(Node back) {
        this.back = back;
    }

}
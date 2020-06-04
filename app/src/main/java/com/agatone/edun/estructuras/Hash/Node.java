package com.agatone.edun.estructuras.Hash;

import com.agatone.edun.Clases.archivo;

/**
 *
 * @author cristian
 */
public class Node {
    private archivo arc;

    private Node next;
    private Node before;

    public  Node(archivo arc){
        this.arc=arc;
        this.next=null;
        this.before=null;
    }

    public archivo getArc(){
        return arc;
    }

    public void setArc(archivo arc) {
        this.arc = arc;
    }

    public Node getBefore() {
        return before;
    }

    public void setBefore(Node before) {
        this.before = before;
    }

    public Node getNext(){
        return next;
    }

    public void setNext(Node sig){
        this.next=sig;
    }
}
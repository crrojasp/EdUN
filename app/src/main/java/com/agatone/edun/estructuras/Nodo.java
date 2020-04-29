package com.agatone.edun.estructuras;

import com.agatone.edun.Clases.archivo;

/**
 *
 * @author cristian
 */
public class Nodo {
    private archivo arc;

    private Nodo next;
    private Nodo before;





    public  Nodo(archivo arc){
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

    public Nodo getBefore() {
        return before;
    }

    public void setBefore(Nodo before) {
        this.before = before;
    }

    public Nodo getNext(){
        return next;
    }

    public void setNext(Nodo sig){
        this.next=sig;
    }
}

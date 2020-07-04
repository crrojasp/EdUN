package com.agatone.edun.Clases;

public class Evento {

    private String EventoNb, caracteristica;

    private int dia, mes, ano, hora, minuto, id;

    public Evento(String eventoNb, String caracteristica, int dia, int mes, int ano, int hora, int minuto, int id) {
        this.EventoNb = eventoNb;
        this.caracteristica = caracteristica;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.hora = hora;
        this.minuto = minuto;
        this.id = id;
    }

    public String getEventoNb() {
        return EventoNb;
    }

    public void setEventoNb(String eventoNb) {
        EventoNb = eventoNb;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

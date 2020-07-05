package com.agatone.edun.Clases;

public class Evento {

    private String EventoNb;
    private String caracteristica;
    private String Creador;

    private int dia, mes, ano, hora, minuto, id, id_creador;


    public Evento(String eventoNb, String caracteristica, String creador, int dia, int mes, int ano, int hora, int minuto, int id, int id_creador) {
        EventoNb = eventoNb;
        this.caracteristica = caracteristica;
        Creador = creador;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.hora = hora;
        this.minuto = minuto;
        this.id = id;
        this.id_creador = id_creador;
    }

    public String getEventoNb() {
        return EventoNb;
    }

    public String getCreador() {
        return Creador;
    }

    public void setCreador(String creador) {
        Creador = creador;
    }

    public int getId_creador() {
        return id_creador;
    }

    public void setId_creador(int id_creador) {
        this.id_creador = id_creador;
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

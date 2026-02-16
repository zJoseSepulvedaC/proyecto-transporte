package com.transporte.transport_bff.dto;

public class ScheduleDataDTO {
    private String patente;
    private String idRuta;
    private String horaSalida;
    private String horaLlegada;

    public ScheduleDataDTO() {
    }

    public ScheduleDataDTO(String patente, String idRuta, String horaSalida, String horaLlegada) {
        this.patente = patente;
        this.idRuta = idRuta;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(String idRuta) {
        this.idRuta = idRuta;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }
}

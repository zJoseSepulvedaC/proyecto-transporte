package com.transporte.transport_bff.dto;

public class GpsDataDTO {
    private String patente;
    private double latitud;
    private double longitud;
    private String timestamp;

    public GpsDataDTO() {
    }

    public GpsDataDTO(String patente, double latitud, double longitud, String timestamp) {
        this.patente = patente;
        this.latitud = latitud;
        this.longitud = longitud;
        this.timestamp = timestamp;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}

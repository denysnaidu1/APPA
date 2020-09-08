package com.daredevil.appa.Model;

public class Event {
    String namaEvent,tanggal,gambar;

    public Event(){

    }

    public Event(String namaEvent, String tanggal,String gambar){
        this.namaEvent=namaEvent;
        this.tanggal=tanggal;
        this.gambar=gambar;
    }

    public String getNamaEvent() {
        return namaEvent;
    }

    public void setNamaEvent(String namaEvent) {
        this.namaEvent = namaEvent;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}

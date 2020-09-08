package com.daredevil.appa.Model;

import java.util.Date;


public class Transaksi {
    String id,user,noRek,qty,jenisBrg,jenis,tanggal;



    Integer jumlah;

    public Transaksi(){

    }
    public Transaksi(String jenis, String id, String user, String noRek, Integer jumlah, String tanggal) {
        this.jenis=jenis;
        this.id = id;
        this.jumlah=jumlah;
        this.user = user;
        this.noRek = noRek;
        this.tanggal=tanggal;
    }

    public Transaksi(String tanggal,String id, String user, String qty, String jenisBrg) {
        this.id = id;
        this.user = user;
        this.qty = qty;
        this.jenisBrg = jenisBrg;
        this.tanggal=tanggal;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getJenisBrg() {
        return jenisBrg;
    }

    public void setJenisBrg(String jenisBrg) {
        this.jenisBrg = jenisBrg;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNoRek() {
        return noRek;
    }

    public void setNoRek(String noRek) {
        this.noRek = noRek;
    }

}

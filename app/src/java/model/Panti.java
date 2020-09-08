package com.daredevil.appa.Model;

public class Panti {
    private String Nama,Alamat,image;
    Integer donasi;

    public Panti(){

    }

    public Panti(String nama, String alamat, String image, Integer donasi) {
        Nama = nama;
        Alamat = alamat;
        this.image = image;
        this.donasi = donasi;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public Integer getDonasi() {
        return donasi;
    }

    public void setDonasi(Integer donasi) {
        this.donasi = donasi;
    }
}

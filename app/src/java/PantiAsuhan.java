package com.daredevil.appa;

public class PantiAsuhan {
    private String Nama,Alamat,image;

    public PantiAsuhan(String nama, String alamat, String image) {
        Nama = nama;
        Alamat = alamat;
        this.image = image;
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
}

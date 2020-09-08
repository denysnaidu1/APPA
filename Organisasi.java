package com.daredevil.appa.Model;

public class Organisasi {

    String namaKampus,namaOrganisasi,image;

    public Organisasi(){

    }
    public Organisasi(String namaKampus, String namaOrganisasi, String image) {
        this.namaKampus = namaKampus;
        this.namaOrganisasi = namaOrganisasi;
        this.image = image;
    }

    public String getNamaKampus() {
        return namaKampus;
    }

    public void setNamaKampus(String namaKampus) {
        this.namaKampus = namaKampus;
    }

    public String getNamaOrganisasi() {
        return namaOrganisasi;
    }

    public void setNamaOrganisasi(String namaOrganisasi) {
        this.namaOrganisasi = namaOrganisasi;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

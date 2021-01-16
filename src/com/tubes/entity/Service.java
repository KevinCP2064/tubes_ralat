package com.tubes.entity;

import java.util.Date;

public class Service {
    private int id_nota;
    private Date tanggal;
    private String jns_service;
    private String keterangan;
    private Double harga;
    private Kendaraan kendaraan;

    public int getId_nota() {
        return id_nota;
    }

    public void setId_nota(int id_nota) {
        this.id_nota = id_nota;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getJns_service() {
        return jns_service;
    }

    public void setJns_service(String jns_service) {
        this.jns_service = jns_service;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public Kendaraan getKendaraan() {
        return kendaraan;
    }

    public void setKendaraan(Kendaraan kendaraan) {
        this.kendaraan = kendaraan;
    }
}

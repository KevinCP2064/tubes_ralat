package com.tubes.entity;

public class Kendaraan {
    private String no_plat;
    private String alamat;
    private String merek;
    private String warna;
    private String tahun_buat;
    private User user;

    public String getNo_plat() {
        return no_plat;
    }

    public void setNo_plat(String no_plat) {
        this.no_plat = no_plat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getMerek() {
        return merek;
    }

    public void setMerek(String merek) {
        this.merek = merek;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getTahun_buat() {
        return tahun_buat;
    }

    public void setTahun_buat(String tahun_buat) {
        this.tahun_buat = tahun_buat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

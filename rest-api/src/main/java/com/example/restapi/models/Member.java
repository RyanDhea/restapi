package com.example.restapi.models;

public class Member {

    private Integer memberId;
    private String name;
    private String tgl_lahir;
    private String alamat;
    private Integer saldo;

    public Member(Integer memberId, String name, String tgl_lahir, String alamat, Integer saldo) {
        this.memberId = memberId;
        this.name = name;
        this.tgl_lahir = tgl_lahir;
        this.alamat = alamat;
        this.saldo = saldo;
    }
    public Member(){

    }
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }
}

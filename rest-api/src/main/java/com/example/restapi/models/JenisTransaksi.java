package com.example.restapi.models;

public class JenisTransaksi {

    private Integer jenisTransaksiId;
    private String transactionDesc;

    public JenisTransaksi(Integer jenisTransaksiId, String transactionDesc) {
        this.jenisTransaksiId = jenisTransaksiId;
        this.transactionDesc = transactionDesc;
    }

    public Integer getJenisTransaksiId() {
        return jenisTransaksiId;
    }

    public void setJenisTransaksiId(Integer jenisTransaksiId) {
        this.jenisTransaksiId = jenisTransaksiId;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }
}

package com.example.restapi.models;

public class Transaction {

	private Integer transactionId;
	private Integer memberId;
	private Integer jenisTransaksiId;
	private String tgl_transaksi;
	private Integer nominal;

	public Transaction(Integer transactionId, Integer memberId, Integer jenisTransaksiId, String tgl_transaksi, Integer nominal) {
		this.transactionId = transactionId;
		this.memberId = memberId;
		this.jenisTransaksiId = jenisTransaksiId;
		this.tgl_transaksi = tgl_transaksi;
		this.nominal = nominal;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getJenisTransaksiId() {
		return jenisTransaksiId;
	}

	public void setJenisTransaksiId(Integer jenisTransaksiId) {
		this.jenisTransaksiId = jenisTransaksiId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getTgl_transaksi() {
		return tgl_transaksi;
	}

	public void setTgl_transaksi(String tgl_transaksi) {
		this.tgl_transaksi = tgl_transaksi;
	}

	public Integer getNominal() {
		return nominal;
	}

	public void setNominal(Integer nominal) {
		this.nominal = nominal;
	}
}

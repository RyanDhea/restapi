package com.example.restapi.repositories;

import java.util.List;

import com.example.restapi.exceptions.EtBadRequestException;
import com.example.restapi.exceptions.EtResourceNotFoundException;
import com.example.restapi.models.Transaction;

public interface TransactionRepository {

	List<Transaction> findAll();

	List<Transaction> findAllByMember(Integer memberId);
	
	Transaction findById(Integer transactionId) throws EtResourceNotFoundException;
	
	Integer create(Integer memberId, Integer jenisTransaksiId, String tgl_transaksi, Integer nominal);
	
	void update(Integer memberId, Integer jenisTransaksiId, Integer transactionId, Transaction transaction) throws EtBadRequestException;

	List<Transaction> findByDate(String tglTransaksi1, String tglTransaksi2);

	void remove(Integer memberId, Integer transactionId) throws EtResourceNotFoundException;
}

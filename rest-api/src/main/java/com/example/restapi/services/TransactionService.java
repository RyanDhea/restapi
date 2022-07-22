package com.example.restapi.services;

import java.util.List;

import com.example.restapi.exceptions.EtBadRequestException;
import com.example.restapi.exceptions.EtResourceNotFoundException;
import com.example.restapi.models.Transaction;

public interface TransactionService {

	List<Transaction> fetchAllTransactions();

	List<Transaction> fetchAllTransactionsByMember(Integer memberId);
	
	Transaction fetchTransactionById(Integer transactionId) throws EtResourceNotFoundException;
	
	Transaction addTransaction(Integer memberId, Integer jenisTransaksiId, String tgl_transaksi, Integer nominal) throws EtBadRequestException;
	
	void updateTransaction(Integer memberId, Integer jenisTransaksiId, Integer transactionId, Transaction transaction) throws EtBadRequestException;
	
	void removeTransaction(Integer userId, Integer memberId, Integer transactionId) throws EtResourceNotFoundException;
}

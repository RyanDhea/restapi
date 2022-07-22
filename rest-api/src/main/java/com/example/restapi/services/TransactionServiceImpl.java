package com.example.restapi.services;

import java.util.List;

import com.example.restapi.models.Member;
import com.example.restapi.repositories.MemberRepository;
import com.example.restapi.repositories.MemberRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restapi.exceptions.EtBadRequestException;
import com.example.restapi.exceptions.EtResourceNotFoundException;
import com.example.restapi.models.Transaction;
import com.example.restapi.repositories.TransactionRepository;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	MemberRepository memberRepository;

	@Override
	public List<Transaction> fetchAllTransactions() {
		return transactionRepository.findAll();
	}

	@Override
	public List<Transaction> fetchAllTransactionsByMember(Integer memberId) {
		return transactionRepository.findAllByMember(memberId);
	}

	@Override
	public Transaction fetchTransactionById(Integer transactionId)
			throws EtResourceNotFoundException {
		return transactionRepository.findById(transactionId);
	}

	@Override
	public Transaction addTransaction(Integer memberId, Integer jenisTransaksiId, String tgl_transaksi, Integer nominal)
			throws EtBadRequestException {
		int transactionId = transactionRepository.create(memberId, jenisTransaksiId, tgl_transaksi, nominal);
		try {
			Member member = new Member();
			member = memberRepository.checkSaldo(memberId);
			int calculate = 0;
			if (jenisTransaksiId == 1){
				calculate = nominal + member.getSaldo();
			} else {
				calculate = nominal - member.getSaldo();
			}
			memberRepository.updateSaldo(memberId, calculate);
		} catch (Exception e) {
			throw new EtBadRequestException("Invalid Request");
		}
		return transactionRepository.findById(transactionId);
	}

	@Override
	public void updateTransaction(Integer memberId, Integer jenisTransaksiId, Integer transactionId, Transaction transaction)
			throws EtBadRequestException {
		transactionRepository.update(memberId, jenisTransaksiId, transactionId, transaction);
		
	}

	@Override
	public void removeTransaction(Integer userId, Integer memberId, Integer transactionId)
			throws EtResourceNotFoundException {
		// TODO Auto-generated method stub
		
	}

}

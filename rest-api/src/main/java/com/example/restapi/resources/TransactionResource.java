package com.example.restapi.resources;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.restapi.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.restapi.models.Transaction;
import com.example.restapi.services.TransactionService;

@Controller
@RequestMapping("/api/transactions")
public class TransactionResource {

	@Autowired
	TransactionService transactionService;
	@Autowired
	TransactionRepository transactionRepository;
	
	@GetMapping("")
	public ResponseEntity<List<Transaction>> getAllTransaction(HttpServletRequest request) {
		List<Transaction> transaction = transactionService.fetchAllTransactions();
		return new ResponseEntity<>(transaction, HttpStatus.OK);
	}

	@GetMapping("/memberId/{memberId}")
	public ResponseEntity<List<Transaction>> getAllTransactionByMember(HttpServletRequest request, @PathVariable("memberId") Integer memberId) {
		List<Transaction> transaction = transactionService.fetchAllTransactionsByMember(memberId);
		return new ResponseEntity<>(transaction, HttpStatus.OK);
	}
	
	@GetMapping("/transactionId/{transactionId}")
	public ResponseEntity<Transaction> getTransaction(HttpServletRequest request, @PathVariable("transactionId") Integer transactionId){
		Transaction transaction = transactionService.fetchTransactionById(transactionId);
		return new ResponseEntity<>(transaction, HttpStatus.OK);
	}
	
	@PostMapping("/memberId/{memberId}/jenisTransaksiId/{jenisTransaksiId}")
	public ResponseEntity<Transaction> addTransaction(HttpServletRequest request, @PathVariable("memberId") Integer memberId, @PathVariable("jenisTransaksiId") Integer jenisTransaksiId, @RequestBody Map<String, Object> transactionMap){
		int nominal = Integer.valueOf(transactionMap.get("nominal").toString());
		SimpleDateFormat formatLengkap = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String tgl_transaksi = formatLengkap.format(date);
		Transaction transaction = transactionService.addTransaction(memberId, jenisTransaksiId, tgl_transaksi, nominal);
		return new ResponseEntity<>(transaction, HttpStatus.CREATED);
	}
	
	@PutMapping("/{transactionId}")
	public ResponseEntity<Map<String, Boolean>> updateTransaction(HttpServletRequest request, @PathVariable("memberId") Integer memberId, @PathVariable("memberId") Integer jenisTransaksiId, @PathVariable("transactionId") Integer transactionId, @RequestBody Transaction transaction){
		transactionService.updateTransaction(memberId, jenisTransaksiId, transactionId, transaction);
		Map<String, Boolean> map = new HashMap<>();
		map.put("success", true);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@GetMapping("/tglTransaksi1/{tglTransaksi1}/tglTransaksi2/{tglTransaksi2}")
	public ResponseEntity<List<Transaction>> getAllTransactionByDate(HttpServletRequest request, @PathVariable("tglTransaksi1") String tglTransaksi1, @PathVariable("tglTransaksi2") String tglTransaksi2) {
		List<Transaction> transaction = transactionRepository.findByDate(tglTransaksi1,tglTransaksi2);
		return new ResponseEntity<>(transaction, HttpStatus.OK);
	}
}

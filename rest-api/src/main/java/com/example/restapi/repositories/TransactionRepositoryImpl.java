package com.example.restapi.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import com.example.restapi.exceptions.EtAuthException;
import com.example.restapi.models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.restapi.exceptions.EtBadRequestException;
import com.example.restapi.exceptions.EtResourceNotFoundException;
import com.example.restapi.models.Transaction;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository{
	
	private static final String SQL_FIND_ALL = "SELECT * FROM ET_TRANSACTIONS";
	private static final String SQL_FIND_BY_MEMBER = "SELECT * FROM ET_TRANSACTIONS WHERE MEMBER_ID = ?";
	private static final String SQL_FIND_BY_ID = "SELECT * FROM ET_TRANSACTIONS WHERE TRANSACTION_ID = ?";
	private static final String SQL_CREATE = "INSERT INTO ET_TRANSACTIONS (TRANSACTION_ID, MEMBER_ID, JENIS_TRANSAKSI_ID, TGL_TRANSAKSI, NOMINAL) VALUES(NEXTVAL('ET_TRANSACTION_SEQ'), ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE ET_TRANSACTIONS SET JENIS_TRANSAKSI_ID = ?, TGL_TRANSAKSI = ?, NOMINAL = ? WHERE TRANSACTION_ID = ?";
	private static final String SQL_FIND_DATE = "SELECT * FROM ET_TRANSACTIONS WHERE TGL_TRANSAKSI between ? AND ?";

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Transaction> findAll() {
		return jdbcTemplate.query(SQL_FIND_ALL, new Object[] {}, transactionRowMapper);
	}

	@Override
	public List<Transaction> findAllByMember(Integer memberId) {
		return jdbcTemplate.query(SQL_FIND_BY_MEMBER, new Object[] {memberId}, transactionRowMapper);
	}

	@Override
	public Transaction findById(Integer transactionId)
			throws EtResourceNotFoundException {
		try {
			return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{transactionId}, transactionRowMapper);
		} catch (Exception e) {
			throw new EtResourceNotFoundException("Transaction Not Found");
		}
	}

	@Override
	public Integer create(Integer memberId, Integer jenisTransaksiId, String tgl_transaksi, Integer nominal) {
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(connection ->{
				PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, memberId);
				ps.setInt(2, jenisTransaksiId);
				ps.setString(3, tgl_transaksi);
				ps.setInt(4, nominal);
				return ps;
			}, keyHolder);
			return (Integer) keyHolder.getKeys().get("TRANSACTION_ID");
		} catch (Exception e) {
			throw new EtBadRequestException("Invalid Request");
		}
	}

	@Override
	public void update(Integer memberId, Integer jenisTransaksiId, Integer transactionId, Transaction transaction)
			throws EtBadRequestException {
		try {
			jdbcTemplate.update(SQL_UPDATE, new Object[] {transaction.getJenisTransaksiId(), transaction.getTgl_transaksi(), transaction.getNominal(), transactionId});
		} catch (Exception e) {
			throw new EtResourceNotFoundException("Invalid request");
		}
	}

	@Override
	public List<Transaction> findByDate(String tglTransaksi1, String tglTransaksi2) {
		return jdbcTemplate.query(SQL_FIND_DATE, new Object[] {tglTransaksi1, tglTransaksi2}, transactionRowMapper);
	}

	@Override
	public void remove(Integer memberId, Integer transactionId) throws EtResourceNotFoundException {
		// TODO Auto-generated method stub
		
	}

	private RowMapper<Transaction> transactionRowMapper = ((rs, rowNum) ->{
		return new Transaction(rs.getInt("TRANSACTION_ID"),
				rs.getInt("MEMBER_ID"),
				rs.getInt("JENIS_TRANSAKSI_ID"),
				rs.getString("TGL_TRANSAKSI"),
				rs.getInt("NOMINAL"));
	});
}

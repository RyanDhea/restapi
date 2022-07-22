package com.example.restapi.repositories;

import com.example.restapi.exceptions.EtBadRequestException;
import com.example.restapi.exceptions.EtResourceNotFoundException;
import com.example.restapi.models.Member;
import com.example.restapi.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

	private static final String SQL_FIND_ALL =  "SELECT MEMBER_ID, NAME, TGL_LAHIR, ALAMAT, SALDO FROM ET_MEMBERS";
	private static final String SQL_FIND_BY_ID = "SELECT MEMBER_ID, NAME, TGL_LAHIR, ALAMAT, SALDO FROM ET_MEMBERS WHERE MEMBER_ID = ?";
	private static final String SQL_CREATE_MEMBER = "INSERT INTO ET_MEMBERS (MEMBER_ID, NAME, TGL_LAHIR, ALAMAT, SALDO) VALUES (NEXTVAL('ET_MEMBER_SEQ'), ?, ?, ?, ?)";
	private static final String SQL_UPDATE_MEMBER = "UPDATE ET_MEMBERS SET NAME = ?, TGL_LAHIR = ?, ALAMAT = ?, SALDO = ? WHERE MEMBER_ID = ?";
	private static final String SQL_UPDATE_SALDO_MEMBER = "UPDATE ET_MEMBERS SET SALDO = ? WHERE MEMBER_ID = ?";
	private static final String SQL_FIND_SALDO =  "SELECT SALDO FROM ET_MEMBERS WHERE MEMBER_ID = ?";

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Member> findAll() throws EtResourceNotFoundException {
			return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{}, memberRowMapper);
	}

	@Override
	public Member findById(Integer memberId) throws EtResourceNotFoundException {
		try {
			return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{memberId}, memberRowMapper);
		} catch (Exception e) {
			throw new EtResourceNotFoundException("MEMBER not found");
		}
	}

	@Override
	public Integer crate(String name, String tgl_lahir, String alamat, Integer saldo) throws EtBadRequestException {
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(SQL_CREATE_MEMBER,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, name);
				ps.setString(2, tgl_lahir);
				ps.setString(3, alamat);
				ps.setInt(4, saldo);
				return ps;
			}, keyHolder);
			return (Integer) keyHolder.getKeys().get("MEMBER_ID");
		} catch (Exception e) {
			throw new EtBadRequestException("Invalid request");
		}
	}

	@Override
	public void update(Integer memberId, Member member) throws EtBadRequestException {
		try {
			jdbcTemplate.update(SQL_UPDATE_MEMBER, new Object[]{member.getName(), member.getTgl_lahir(), member.getAlamat(), member.getSaldo(), memberId});
		} catch (Exception e) {
			throw new EtBadRequestException("Invalid request");
		}
	}

	@Override
	public void updateSaldo(Integer memberId, Integer saldo) throws EtBadRequestException {
		try {
			jdbcTemplate.update(SQL_UPDATE_SALDO_MEMBER, new Object[]{saldo, memberId});
		} catch (Exception e) {
			throw new EtBadRequestException("Invalid request");
		}
	}

	@Override
	public Member checkSaldo(Integer memberId) throws EtResourceNotFoundException {
		try {
			return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{memberId}, memberRowMapper);
		} catch (Exception e) {
			throw new EtResourceNotFoundException("MEMBER not found");
		}
	}

	@Override
	public void removeById(Integer userId, Integer memberId) {
		// TODO Auto-generated method stub

	}

	private RowMapper<Member> memberRowMapper = ((rs, rowNum) -> {
		return new Member(rs.getInt("MEMBER_ID"),
				rs.getString("NAME"),
				rs.getString("TGL_LAHIR"),
				rs.getString("ALAMAT"),
				rs.getInt("SALDO"));
	});
}

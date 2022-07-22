package com.example.restapi.repositories;

import com.example.restapi.exceptions.EtBadRequestException;
import com.example.restapi.exceptions.EtResourceNotFoundException;
import com.example.restapi.models.Member;

import java.util.List;

public interface MemberRepository {

	List<Member> findAll() throws EtResourceNotFoundException;
	
	Member findById(Integer memberId) throws EtResourceNotFoundException;
	
	Integer crate(String name, String tgl_lahir, String alamat, Integer saldo) throws EtBadRequestException;
	
	void update(Integer memberId, Member member) throws EtBadRequestException;

	void updateSaldo(Integer memberId, Integer saldo) throws EtBadRequestException;

	Member checkSaldo(Integer memberId) throws EtResourceNotFoundException;

	void removeById(Integer userId, Integer memberId);
}

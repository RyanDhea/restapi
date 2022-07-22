package com.example.restapi.services;

import com.example.restapi.exceptions.EtBadRequestException;
import com.example.restapi.exceptions.EtResourceNotFoundException;
import com.example.restapi.models.Member;

import java.util.List;

public interface MemberService {

	List<Member> fetchAllMembers();
	
	Member fetchMemberById(Integer memberId) throws EtResourceNotFoundException;
	
	Member addMember(String name, String tgl_lahir, String alamat, Integer saldo) throws EtBadRequestException;
	
	void updateMember(Integer memberId, Member member) throws EtBadRequestException;
	
	void removeMemberWithAllTransaction(Integer userId, Integer memberId) throws EtResourceNotFoundException;
}

package com.example.restapi.services;

import com.example.restapi.exceptions.EtBadRequestException;
import com.example.restapi.exceptions.EtResourceNotFoundException;
import com.example.restapi.models.Member;
import com.example.restapi.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public List<Member> fetchAllMembers() {
		return memberRepository.findAll();
	}

	@Override
	public Member fetchMemberById(Integer memberId) throws EtResourceNotFoundException {
		return memberRepository.findById(memberId);
	}

	@Override
	public Member addMember(String name, String tgl_lahir, String alamat, Integer saldo) throws EtBadRequestException {
		int memberId = memberRepository.crate(name, tgl_lahir, alamat, saldo);
		return memberRepository.findById(memberId);
	}

	@Override
	public void updateMember(Integer memberId, Member member) throws EtBadRequestException {
		memberRepository.update(memberId, member);
	}

	@Override
	public void removeMemberWithAllTransaction(Integer userId, Integer memberId)
			throws EtResourceNotFoundException {
		// TODO Auto-generated method stub
		
	}

}

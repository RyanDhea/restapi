package com.example.restapi.resources;

import com.example.restapi.models.Member;
import com.example.restapi.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/members")
public class MemberResource {

	@Autowired
	MemberService memberService;
	
	@GetMapping("")
	public ResponseEntity<List<Member>> getAllMembers(HttpServletRequest request) {
		List<Member> members = memberService.fetchAllMembers();
		return new ResponseEntity<>(members, HttpStatus.OK);
	}
	
	@GetMapping("/{memberId}")
	public ResponseEntity<Member> getMemberById(HttpServletRequest request, @PathVariable("memberId") Integer memberId){
		Member member = memberService.fetchMemberById(memberId);
		return new ResponseEntity<>(member, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Member> addMember(HttpServletRequest request, @RequestBody Map<String, Object> memberMap){
		String name = (String) memberMap.get("name");
		String tgl_lahir = (String) memberMap.get("tgl_lahir");
		String alamat = (String) memberMap.get("alamat");
		Integer saldo = (Integer) memberMap.get("saldo");
		Member member = memberService.addMember(name, tgl_lahir, alamat, saldo);
		return new ResponseEntity<>(member, HttpStatus.CREATED);
	}
	
	@PutMapping("/{memberId}")
	public ResponseEntity<Map<String, Boolean>> updateMember(HttpServletRequest request, @PathVariable("memberId") Integer memberId, @RequestBody Member member){
		memberService.updateMember(memberId, member);
		Map<String, Boolean> map = new HashMap<>();
		map.put("success", true);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}

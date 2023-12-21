package com.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dao.MemberDAO;
import com.board.dto.AddressDTO;
import com.board.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {

	
	@Autowired 
	private MemberDAO dao;
	
	
	// 주소 검색
	@Override
	public List<AddressDTO> addrSearch(int startPoint, int postNum, String addrSearch) {
		
		return dao.addrSearch(startPoint,postNum, addrSearch);
	}

	@Override
	public int addTotalCount(String addrSearch) {
		return dao.addTotalCount(addrSearch);
		
	}
	
	
	// 회원 가입
	@Override
	public void memberInfoRegistry(MemberDTO member) {
		dao.memberInfoRegistry(member);
	}
	
	
	
	// 아이디 중복 확인
	public int idCheck(String userid) {
		return dao.idCheck(userid);
	}
	
	public MemberDTO memberInfo(String userid) {
		return dao.memberInfo(userid);
	}

	 // 마지막 로그인 날짜 등록하기
	public void lastlogindateUpdate(MemberDTO member) {
		dao.lastlogindateUpdate(member);
	}

	
	public void lastlogoutdateUpdate(MemberDTO member) {
		dao.lastlogoutdateUpdate(member);
	}
	// authkey 업데이트
	@Override
	public void authkeyUpdate(MemberDTO member) {
		dao.authkeyUpdate(member);
		
	}
	// authkey 존재여부 확인
	@Override
	public MemberDTO memberInfoByAuthkey(MemberDTO member) {
		return dao.memberInfoByAuthkey(member);
		
	}
	//패스워드 수정
	@Override
	public void memberPasswordModify(MemberDTO member) {
            dao.memberPasswordModify(member);		
	}

	// 아이디 찾기
	@Override
	public String searchID(MemberDTO member) {
		
		return dao.searchID(member);
	}
	
	
	
	
	
}

package com.board.service;

import java.util.List;
import java.util.Map;

import com.board.dto.AddressDTO;
import com.board.dto.MemberDTO;

public interface MemberService {

	
	// 주소검색
	public List<AddressDTO> addrSearch(int startPoint, int postNum, String addrSerach);

	// 주소 행 최대 값 계산
	public int addTotalCount(String addrSearch);
	
	public void memberInfoRegistry(MemberDTO member);
	
	public int idCheck(String userid);
	
	public MemberDTO memberInfo(String userid);
		
	
	public void lastlogindateUpdate(MemberDTO member);
	
	public void lastlogoutdateUpdate(MemberDTO member);
	
	// authkey 업데이트
	public void authkeyUpdate(MemberDTO member);
	// authkey 존재여부 확인
	public MemberDTO memberInfoByAuthkey(MemberDTO member);
	
	//패스워드 수정
	public void memberPasswordModify(MemberDTO member);
	// 아이디 찾기
	public String searchID(MemberDTO member);
	
}

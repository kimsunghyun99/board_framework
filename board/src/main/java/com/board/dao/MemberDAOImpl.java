package com.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.dto.AddressDTO;
import com.board.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sql;
	private static String namespace = "com.board.mappers.member";
	
	
	//주소 검색
	@Override
	public List<AddressDTO> addrSearch(int startPoint, int postNum, String addrSearch) {
		Map<String, Object>data = new HashMap<>();
		data.put("startPoint", startPoint);
		data.put("postNum", postNum);
		data.put("addrSearch", addrSearch);
		
		return sql.selectList(namespace + ".addrSearch", data);
	}

		// 주소 행 최대 갯수 계산
	@Override
	public int addTotalCount(String addrSearch) {
			
		return sql.selectOne(namespace + ".addTotalCount", addrSearch);
	}

	
	
		@Override
		public void memberInfoRegistry(MemberDTO member) {
			sql.insert(namespace + ".memberInfoRegistry", member);
			
		}

		@Override
		public int idCheck(String userid) {
			return sql.selectOne(namespace + ".idCheck", userid);
		}

		@Override
		public MemberDTO memberInfo(String userid) {
			
			return sql.selectOne(namespace + ".memberInfo", userid);
		}
		
	
		// 마지막 로그인 날짜 등록 하기 
		@Override
		public void lastlogindateUpdate(MemberDTO member) {
			
			sql.update(namespace + ".lastlogindateUpdate", member);
					
		}
	
		// 마지막 로그아웃 날짜 등록 하기 
				@Override
				public void lastlogoutdateUpdate(MemberDTO member) {
					
					sql.update(namespace + ".lastlogoutdateUpdate", member);
							
				}
				// authkey 업데이트
				@Override
				public void authkeyUpdate(MemberDTO member) {
					
					sql.update(namespace + ".authkeyUpdate", member);
				}

				// authkey 존재여부 확인
				@Override
				public MemberDTO memberInfoByAuthkey(MemberDTO member) {
					return sql.selectOne(namespace + ".memberInfoByAuthkey", member);
					
				}
				//패스워드 수정
				@Override
				public void memberPasswordModify(MemberDTO member) {
							sql.update(namespace + ".memberPasswordModify", member);
				}

				// 아이디 찾기
				@Override
				public String searchID(MemberDTO member) {
					
					return sql.selectOne(namespace + ".searchID", member);
				}
	
	

}

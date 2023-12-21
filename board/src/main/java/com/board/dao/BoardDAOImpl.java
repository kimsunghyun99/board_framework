package com.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.dto.BoardDTO;
import com.board.dto.FileDTO;
import com.board.dto.LikeDTO;
import com.board.dto.ReplyDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired  // 의존성 주입이라는 방법으로 XML 방식 (root-context.xml)을 통해 생성된 스프링빈인 SqlSession을 호출
	private SqlSession sql;
	
	private static String namespace = "com.board.mappers.board";
	
	//게시물 목록 보기
	@Override
	public List<BoardDTO> list(int startPoint, int postNum, String keyword) throws Exception {
		
		Map<String, Object> data = new HashMap<>();
		data.put("startPoint",startPoint);
		data.put("postNum",postNum);
		data.put("keyword", keyword);
		return sql.selectList(namespace + ".list", data); // com.board.mappers.board.list
		
	}
	
	public int getTotalCount(String keyword) throws Exception  {
		return sql.selectOne(namespace + ".getTotalCount", keyword);
	}

		//게시물 내용 보기 
	@Override
	public BoardDTO view(int seqno) throws Exception {
		return sql.selectOne(namespace + ".view", seqno);
	}
	
	// 게시물 내용 이전 보기
	@Override
	public int pre_seqno(int seqno, String keyword) throws Exception {
		Map<String,Object> data = new HashMap<>();
		data.put("seqno", seqno);
		data.put("keyword", keyword);
		
		return sql.selectOne(namespace + ".pre_seqno", data);
	}

	// 게시물 내용 다음 보기
	@Override
	public int next_seqno(int seqno, String keyword) throws Exception {
		Map<String,Object> data = new HashMap<>();
		data.put("seqno", seqno);
		data.put("keyword", keyword);
		return sql.selectOne(namespace + ".next_seqno", data);
	}
	
	
	
	// 게시물 조회수 증가
	@Override
	public void hitno(int seqno) throws Exception {
		
		sql.update(namespace + ".hitno", seqno);
		
	}

	 // 게시물 등록하기
	@Override
	public void write(BoardDTO board) throws Exception {
		sql.insert(namespace + ".write", board);
		
	}

	// 게시물 수정하기
	@Override
	public void modify(BoardDTO board) throws Exception {
		sql.update(namespace + ".modify", board);
		
	}
// 게시물 삭제하기
	@Override
	public void delete(int seqno) throws Exception {
		sql.delete(namespace + ".delete", seqno);
		
	}

	@Override
	public void total(int totalCount) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void fileInfoRegistry(Map<String,Object> data) throws Exception {	
	sql.insert(namespace + ".fileInfoRegistry", data);
		
		
	}

	// 첨부파일 목록보기
	@Override
	public List<FileDTO> fileInfoView(int seqno) throws Exception {
		
		return sql.selectList(namespace + ".fileInfoView", seqno);
	}

	
	// 다운로드를 위한 파일 정보 가져오기
	@Override
	public FileDTO fileInfo(int fileseqno) throws Exception {
		
		return sql.selectOne(namespace + ".fileInfo", fileseqno);
	}

	
    // 첨부 파일 삭제를 위한 checkfile 정보 변경
	@Override
	public void fileInfoUpdate(int seqno) throws Exception {
		sql.update(namespace + ".fileInfoUpdate", seqno);
	}

	
	
	// 댓글 목록 보기
	@Override
	public List<ReplyDTO> replyView(ReplyDTO reply) throws Exception {
		return sql.selectList(namespace + ".replyView", reply);
	}
	// 댓글 등록
	@Override
	public void replyRegistry(ReplyDTO reply) throws Exception {
		sql.insert(namespace + ".replyRegistry", reply);
		
	}
	// 댓글 수정
	@Override
	public void replyUpdate(ReplyDTO reply) throws Exception {
		sql.update(namespace + ".replyUpdate", reply);
		
	}
	// 댓글 삭제 
	@Override
	public void replyDelete(ReplyDTO reply) throws Exception {
		sql.delete(namespace + ".replyDelete", reply);
		
	}
	// 좋아요 / 싫어요 등록여부 확인

	@Override
	public LikeDTO likeCheckView(LikeDTO likeData) throws Exception {
		
		return sql.selectOne(namespace + ".likeCheckView", likeData);
	}

	
	// 좋아요 / 싫어요 신규 등록 
	@Override
	public void likeCheckRegistry(LikeDTO likeData) throws Exception {
		sql.insert(namespace + ".likeCheckRegistry", likeData);
		
	}
//  좋아요 / 싫어요 수정 
	@Override
	public void likeCheckUpdate(LikeDTO likeData) throws Exception {
		sql.update(namespace + ".likeCheckUpdate", likeData);
		
	}

	
	// 게시물 좋아요/싫어요 수정 
	@Override
	public void boardLikeUpdate(BoardDTO board) throws Exception {
		sql.update(namespace + ".boardLikeUpdate", board);
		
	}
     
	// 게시물 수정 시 파일 정보 수정(checkfile을 x로 변경)
	@Override
	public void deleteFileList(int fileseqno) throws Exception {
		sql.update(namespace + ".deleteFileList", fileseqno);
		
	}
	


}





	

	

	



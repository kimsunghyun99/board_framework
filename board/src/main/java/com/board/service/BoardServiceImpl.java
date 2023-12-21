package com.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dao.BoardDAO;
import com.board.dto.BoardDTO;
import com.board.dto.FileDTO;
import com.board.dto.LikeDTO;
import com.board.dto.ReplyDTO;

@Service
public class BoardServiceImpl implements BoardService {

	
	@Autowired
	BoardDAO dao;
	
	
	//게시물 목록 보기
	@Override
	public List<BoardDTO> list(int startPoint, int postNum, String keyword) throws Exception {
		
		return dao.list(startPoint,postNum ,keyword);
	}
	
	// 게시물 전체 개수 계산
	public int getTotalCount(String keyword) throws Exception {
		return dao.getTotalCount(keyword);
	}
    // 게시물 내용 보기
	@Override
	public BoardDTO view(int seqno) throws Exception {
		
		return dao.view(seqno);
	}
	
	
	  //게시물 내용 이전 보기
		@Override
		public int pre_seqno(int seqno,String keyword) throws Exception {
			
			return dao.pre_seqno(seqno,keyword);
		}
		//게시물 내용 다음 보기
		@Override
		public int next_seqno(int seqno,String keyword) throws Exception {
			return dao.next_seqno(seqno,keyword);
		}
		//게시물 내용 조회수 증가
		@Override
		public void hitno(int seqno) throws Exception {
			
			dao.hitno(seqno);
		}
	
	
    //게시물 등록 하기
	@Override
	public void write(BoardDTO board) throws Exception {
		
		dao.write(board);
	}
	//게시물 수정하기
	@Override
	public void modify(BoardDTO board) throws Exception {
		dao.modify(board);
	}
	//게시물 삭제하기
	@Override
	public void delete(int seqno) throws Exception {
		
		dao.delete(seqno);
	}
	
	// 첨부파일 등록하기
	@Override
	public void fileInfoRegistry(Map<String, Object> data) throws Exception  {
		dao.fileInfoRegistry(data);
		
	}
	// 첨부파일 목록보기
	@Override
	public List<FileDTO> fileInfoView(int seqno) throws Exception {
		
		return dao.fileInfoView(seqno);
	}
	// 다운로드를 위한 파일 정보 가져오기
	@Override
	public FileDTO fileInfo(int fileseqno) throws Exception {
		
		return dao.fileInfo(fileseqno);
	}
	// 첨부 파일 삭제를 위한 checkfile 정보 변경
	@Override
	public void fileInfoUpdate(int seqno) throws Exception {
		dao.fileInfoUpdate(seqno);
		
	}
	// 댓글 목록 보기
	@Override
	public List<ReplyDTO> replyView(ReplyDTO reply) throws Exception {
		
		return dao.replyView(reply);
	}
	// 댓글 등록
	@Override
	public void replyRegistry(ReplyDTO reply) throws Exception {
		dao.replyRegistry(reply);
		
	}
	// 댓글 수정
	@Override
	public void replyUpdate(ReplyDTO reply) throws Exception {
		dao.replyUpdate(reply);
		
	}
	// 댓글 삭제
	@Override
	public void replyDelete(ReplyDTO reply) throws Exception {
		dao.replyDelete(reply);
		
	}
	// 좋아요 / 싫어요 등록여부 확인
	@Override
	public LikeDTO likeCheckView(LikeDTO likeData) throws Exception {
		
		return dao.likeCheckView(likeData);
	}
	// 좋아요 / 싫어요 신규 등록 
	@Override
	public void likeCheckRegistry(LikeDTO likeData) throws Exception {
		dao.likeCheckRegistry(likeData);
		
	}
//  좋아요 / 싫어요 수정 
	@Override
	public void likeCheckUpdate(LikeDTO likeData) throws Exception {
		dao.likeCheckUpdate(likeData);
		
		
	}
	// 게시물 좋아요/싫어요 수정 
	@Override
	public void boardLikeUpdate(BoardDTO board) throws Exception {
		dao.boardLikeUpdate(board);
		
	}

	// 게시물 수정 시 파일 정보 수정(checkfile을 x로 변경)
	@Override
	public void deleteFileList(int fileseqno) throws Exception {
		dao.deleteFileList(fileseqno);		
	}
	
	
	     
  
	

	

}

package com.board.controller;

import java.io.File;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.board.dto.BoardDTO;
import com.board.dto.FileDTO;
import com.board.dto.LikeDTO;
import com.board.dto.ReplyDTO;
import com.board.service.BoardService;
import com.board.utils.Page;

@Controller
public class BoardController {

	
	//@Autowired
		BoardService service;  // 멤버변수
		
		public BoardController(BoardService service) {
			this.service = service;
		}  //  AUTOWIRED 랑 같음 
		
		
		// 게시물 목록 보기
			@GetMapping("/board/list")
			public void getList(Model model,@RequestParam("page") int pageNum, 
					@RequestParam(name="keyword",defaultValue="",required=false) String keyword ) 
					throws Exception {
				
				int postNum = 5; // 한 페이지의 게시물 수
				int startPoint = (pageNum-1)*postNum; 
				int pageListCount = 5; //  페이지 리스트 수 
				
				int totalCount = service.getTotalCount(keyword); // 전체 게시물 갯수
 				
				System.out.println("totalCount = " + totalCount);
				Page page = new Page();
				//String pageList = page.getPageList(pageNum, postNum, pageListCount, totalCount, keyword);
						
						
				model.addAttribute("list", service.list(startPoint,postNum,keyword));
				model.addAttribute("page",pageNum);
				model.addAttribute("keyword",keyword);
				model.addAttribute("pageList", page.getPageList(pageNum, postNum, pageListCount, totalCount, keyword));
			}
			
			
			// 게시물 등록 화면 보기
			@GetMapping("/board/write")
			public void getWrite() {}
			
			
			//게시물 등록 하기
			@ResponseBody
			@PostMapping("/board/write")
			public String postWrite(BoardDTO board) throws Exception {
				service.write(board);
				
				
		return "{\"message\":\"GOOD\"}";	
				
			//	return "redirect:/board/list?page=1";
			}
			
			
			//파일 업로드
			@ResponseBody
			@PostMapping("/board/fileUpload")
			public String postFileUpload(BoardDTO board,@RequestParam("kind") String kind,
					
					@RequestParam("sendToFileList") List<MultipartFile> multipartFile,
					@RequestParam(name="deleteFileList", required=false) int[] deleteFileList) throws Exception {
				
				String path = "/home/hjj6611/Repository/file/";
				if(kind.equals("I")) {     // 게시물 등록
					 service.write(board);
				}
				
				if(kind.equals("U")) {     // 게시물 수정
					service.modify(board);
					
					if(deleteFileList != null) {
						
						for(int i=0; i<deleteFileList.length; i++) {
							service.deleteFileList(deleteFileList[i]);
						}
						
					}
				}
				
				if(!multipartFile.isEmpty()) {
					
					File targetFile = null;
					Map<String,Object> fileInfo = null;
					
					for(MultipartFile mpr:multipartFile) {
						
						String org_filename = mpr.getOriginalFilename();
						String org_fileExtension = org_filename.substring(org_filename.lastIndexOf("."));
						String stored_filename = UUID.randomUUID().toString().replaceAll("-","") + org_fileExtension;
						long filesize = mpr.getSize();
						
						try {
							targetFile = new File(path + stored_filename);
							mpr.transferTo(targetFile);
							
							fileInfo = new HashMap<>(); 
							fileInfo.put("org_filename", org_filename);
							fileInfo.put("stored_filename", stored_filename);
							fileInfo.put("filesize", filesize);
							fileInfo.put("userid", board.getUserid());
							
							fileInfo.put("checkfile", "O"); // O : 파일 존재 , X: 파일 삭제 
							fileInfo.put("kind", kind);
							
							service.fileInfoRegistry(fileInfo);
							
						}catch(Exception e) {
							e.printStackTrace();
						}
						
					}
				}
				
				return "{\"message\":\"GOOD\"}";
						
				
			}
			
			
			//파일 다운로드
			@GetMapping("/board/fileDownload") 
			public void fileDownload(@RequestParam("fileseqno") int fileseqno, HttpServletResponse rs) throws Exception {
				
				String path = "/home/hjj6611/Repository/file/";
				
				FileDTO fileInfo = service.fileInfo(fileseqno);
				String org_filename = fileInfo.getOrg_filename();
				String stored_filename = fileInfo.getStored_filename();
				byte fileByte[] = FileUtils.readFileToByteArray(new File(path+stored_filename));
				
				// Content-Disposition 헤드를 구성하여 바이트 타입으로 http Response 메시지를 전송 한다는 것은
				// 이 파일을 다운받게끔 하는 것임
				// Content-Disposition 헤드 구성 예) Content-Disposition: attachment; filename="hello.jpg" 
				
				rs.setContentType("application/octet-stream");
				rs.setContentLength(fileByte.length);
				rs.setHeader("Content-Disposition","attachment; filename=\""+URLEncoder.encode(org_filename,"UTF-8") + "\";");
				rs.getOutputStream().write(fileByte);
				rs.getOutputStream().flush();
				rs.getOutputStream().close();
			}
			
			
			
			
			
			// 게시물 수정 화면 보기
			@GetMapping("/board/modify")
			public void getModify(@RequestParam("seqno") int seqno,@RequestParam("page") int pageNum,
					@RequestParam(name="keyword",defaultValue="",required=false) String keyword, Model model) throws Exception {
			model.addAttribute("view", service.view(seqno));
			model.addAttribute("page", pageNum);
			model.addAttribute("fileInfoView",service.fileInfoView(seqno-2));
			
			
		}
			
			// 게시물 수정 하기
			@ResponseBody
			@Transactional
			@PostMapping("/board/modify")
			public String postModify(BoardDTO board, @RequestParam("page") int pageNum,
					@RequestParam(name="keyword",defaultValue="",required=false) String keyword,
					@RequestParam(name="deleteFileList", required=false) int[] deleteFileList
					) throws Exception {
				
				
				service.modify(board);
                 
				 if(deleteFileList != null) {
					
					for(int i=0; i<deleteFileList.length; i++) {
						service.deleteFileList(deleteFileList[i]);
					}
					
				}
				
				
				return "{\"message\":\"GOOD\"}";
				//return "redirect:/board/view?seqno=" + board.getSeqno() + "&page=" + pageNum + "&keyword=" 
					//	+ URLEncoder.encode(keyword,"UTF-8");
			}	
			
			// 게시물 삭제하기
			@Transactional
			@GetMapping("/board/delete")
			public String getDelete(@RequestParam("seqno") int seqno) throws Exception {
				// transaction 시작
			   service.fileInfoUpdate(seqno-2); // tbl_file테이블의 checkfile column 값을 'X' 로 반환
				service.delete(seqno); // 게시물 행 삭제
				//transaction 끝
				
				return "redirect:/board/list?page=1";
			}
			
			
			// 게시물 내용 보기
	         @GetMapping("/board/view")
	         public void getView(@RequestParam("seqno") int seqno, @RequestParam("page") int pageNum,
	               @RequestParam(name="keyword",defaultValue="",required=false) String keyword,
	               Model model, HttpSession session) throws Exception {
	            
	            //좋아요 싫어요 처리
	            LikeDTO likeDTO = new LikeDTO();
	            String session_userid = (String)session.getAttribute("userid");
	            likeDTO.setSeqno(seqno);
	            likeDTO.setUserid(session_userid);
	            LikeDTO likeCheckView = service.likeCheckView(likeDTO);
	            
	            //초기에 좋아요 싫어요 등록이 안되어져 있을 경우 "N"으로 초기화
	            if(likeCheckView==null) {
	               model.addAttribute("myLikeCheck","N");
	               model.addAttribute("myDislikeCheck","N");
	            }
	            else if(likeCheckView !=null){
	               model.addAttribute("myLikeCheck",likeCheckView.getMylikecheck());
	               model.addAttribute("myDislikeCheck",likeCheckView.getMydislikecheck());
	            }
	            
	            model.addAttribute("view", service.view(seqno));
	            model.addAttribute("page", pageNum);
	            model.addAttribute("keyword",keyword);
	            model.addAttribute("pre_seqno", service.pre_seqno(seqno,keyword));
	            model.addAttribute("next_seqno", service.next_seqno(seqno,keyword));
	            model.addAttribute("fileInfoView", service.fileInfoView(seqno-2));
	            
	            //세션  userid 값 가져오기 
	            String sessionUserid = (String) session.getAttribute("userid");
	            //조회수 증가
	            if(!sessionUserid.equals(service.view(seqno).getUserid())) {
	               //조회수 증가
	               service.hitno(seqno);   
	            }
	            
	            
	         }
			//좋아요, 싫어요 처리
	         @ResponseBody
	         @PostMapping("/board/likeCheck")
	         public String postLikeCheck(@RequestBody Map<String,Object> likeCheckData) throws Exception {
	        	
	            //
	       // int seqno =likeCheckData.get("seqno").intValue();
	        //	System.out.println("dddd : " + likeCheckData.get("seqno").getClass().getName());
	        	// int seqno = 0;
	        	 
	        	 System.out.println("fdgjdfkglfd: "+ likeCheckData.get("seqno"));
	        	 System.out.println("cnt: "+likeCheckData.get("checkCnt"));
	           
	       	 int seqno = (int)likeCheckData.get("seqno");
	        //	 int seqno = (int)Math.floor((double)likeCheckData.get("seqno"));
	            String userid = (String)likeCheckData.get("userid");
	            String mylikecheck = (String)likeCheckData.get("mylikecheck");
	            String mydislikecheck = (String)likeCheckData.get("mydislikecheck");   
	           int checkCnt = (int)likeCheckData.get("checkCnt");
	       //   int checkCnt = (int)Math.floor((double)likeCheckData.get("checkCnt"));
	       
	    
	       
	           
	            
	            //현재 날짜, 시간 구해서 좋아요, 싫어요 한 날짜, 시간 입력 및 수정
	            String likeDate="";
	            String dislikeDate="";
	            LocalDateTime now = LocalDateTime.now();
	            if(likeCheckData.get("mylikecheck").equals("Y")) {
	               likeDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	            }
	            else if(likeCheckData.get("mydislikecheck").equals("Y")) {
	               dislikeDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	            }
	            
	            likeCheckData.put("likedate", likeDate);
	            likeCheckData.put("dislikedate", dislikeDate);
	               
	            LikeDTO likeData = new LikeDTO();
	            likeData.setSeqno(seqno);
	            likeData.setUserid(userid);
	            likeData.setMylikecheck(mylikecheck);
	            likeData.setMydislikecheck(mydislikecheck);
	            likeData.setLikedate(likeDate);
	            likeData.setDislikedate(dislikeDate);
	           
	            
	            LikeDTO likeCheckView = service.likeCheckView(likeData);
	            if(likeCheckView == null) {
	               service.likeCheckRegistry(likeData);
	               }
	               else {
	                  service.likeCheckUpdate(likeData);
	               }
	            int likeCnt=service.view(seqno).getLikecnt();
	            int dislikeCnt=service.view(seqno).getDislikecnt();
	            
	            switch(checkCnt) {
	            case 1: likeCnt--;
	               break;
	            case 2: likeCnt++; dislikeCnt--;
	               break;
	            case 3: likeCnt++;
	               break;
	            case 4: dislikeCnt--;
	               break;
	            case 5: likeCnt--; dislikeCnt++;
	               break;
	            case 6: dislikeCnt++;
	               break;
	            }
	            
	            BoardDTO board = new BoardDTO();
	            board.setSeqno(seqno);
	            board.setLikecnt(likeCnt);
	            board.setDislikecnt(dislikeCnt);
	         
	            service.boardLikeUpdate(board);
	            
	            return "{\"likeCnt\":\""+likeCnt+"\",\"dislikeCnt\":\""+dislikeCnt+"\"}";
	         }
			
			
			
			
			
			// 댓글 처리
			@ResponseBody 
			@PostMapping("/board/reply")
			public List<ReplyDTO> postReply(@RequestBody ReplyDTO reply, @RequestParam("option") String option) throws Exception {
				
				switch(option) {
				
				             case "I" : service.replyRegistry(reply); // 댓글 등록
				             break;
				             
				             case "U" : service.replyUpdate(reply);   // 댓글 수정
				            	 break;
				            	
				             case "D" : service.replyDelete(reply); // 댓글 삭제 
				            	 break;
				             	
				}
				return service.replyView(reply);
				
			}
			
	
}

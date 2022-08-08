package kr.co.project.board;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.project.comment.CommentService;
import kr.co.project.comment.CommentVO;
import kr.co.project.member.MemberVO;

@Controller
public class BoardController { //url매핑,포워딩기능 ->현재페이지에는 비지니스로직수정기능 없음
	@Autowired
	BoardService service; //서비스가 두개가 되서
	@Autowired
	CommentService cService;
	
	@GetMapping("/board/index.do")
	public String index(Model model,BoardVO vo) { //model 저장 / vo 받아오는값 -BoardVO vo ->커멘드 객체로 소문자로 앞에 변환해서 사용이가능
		model.addAttribute("data",service.index(vo));
		return "board/index"; //포워딩
	}
	
	@GetMapping("/board/write.do")
	public String write() {
		
		return "board/write";
	}
	
	@PostMapping("/board/insert.do")
	public String insert(BoardVO vo,Model model, @RequestParam MultipartFile filename, HttpServletRequest req) { //requestparam 으로도 사용가능하나 코드 길어짐
		//첨부파일 처리
		if(!filename.isEmpty()) {
			//파일명
			String org = filename.getOriginalFilename();//사용자가 첨부한파일명 가지고옴
			String ext =org.substring(org.lastIndexOf(".")); //확장자
			String real = new Date().getTime()+ext;
			
			
			//파일저장
			String pate =req.getRealPath("/upload/");//서버에서는 해당경로가 나옴 
			try {
				filename.transferTo(new File(pate+real));//D:\\kdigital\\java\\workspace\\proje...
			}catch(Exception e) {}
			
			vo.setFilename_org(org);
			vo.setFilename_real(real);
		}
		//member_no저장
		HttpSession sess = req.getSession();
		MemberVO mv = (MemberVO)sess.getAttribute("loginInfo");
		vo.setMember_no(mv.getNo());
		
		if(service.insert(vo)) {
			model.addAttribute("msg","정상적으로 저장되었습니다.");
			model.addAttribute("url","index.do");
			return "common/alert";
		}else {
			model.addAttribute("msg","저장에 실패하였습니다.");
			return "common/alert";
			}
	}
	
	//ajax로 안할때
//	@GetMapping("/board/view.do")
//	public String view(Model model,BoardVO vo,CommentVO cvo) { //파라미터필요,모델객체 필요
//		BoardVO data = service.view(vo.getNo());
//		model.addAttribute("data",data);
//		cvo.setBoard_no(vo.getNo());
//		cvo.setTablename("board");
//		model.addAttribute("comment",cService.index(cvo));
//		return "board/view";
//		
//	}
	
	@GetMapping("/board/view.do")
	public String view(Model model,BoardVO vo,CommentVO cvo) { //파라미터필요,모델객체 필요
		BoardVO data = service.view(vo.getNo());
		model.addAttribute("data",data);
		return "board/view";
		
	}
	
	@GetMapping("/board/edit.do")
	public String edit(BoardVO vo,Model model) {
		BoardVO data=service.edit(vo.getNo());
		model.addAttribute("data",data);
		return "board/edit";
	}
	
	@PostMapping("/board/update.do")
	public String update(BoardVO vo,Model model) {
		if(service.update(vo)) {
			model.addAttribute("msg","정상적으로 수정되었습니다.");
			model.addAttribute("url","view.do?no="+vo.getNo());
			return "common/alert";
		}else {
			model.addAttribute("msg","수정실패");
			return "common/alert";
		}
	}
	
	@GetMapping("/board/delete.do")
	public String delete(BoardVO vo,Model model) {
		if(service.delete(vo.getNo())) {
			model.addAttribute("msg","정상적으로 삭제되었습니다.");
			model.addAttribute("url","index.do");
			return "common/alert";
		}else {
			model.addAttribute("msg","삭제실패");
			return "common/alert";
		}
	}
	
	
}

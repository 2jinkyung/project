package kr.co.project.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.project.board.BoardMapper;
import kr.co.project.board.BoardVO;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/config/servlet-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Autowired 
	private BoardMapper mapper; // 타입만 boardMapper-> 인터페이스 이기떄문 -> 그래서 인터페이스를 구현한 xml의 값을 가지고와 사용
	
	//@Test //주입이 잘되었는지 테스트
	public void testObj() {
		log.info(mapper);
	}
	
	@Test
	public void testInsert() {
		BoardVO vo = new BoardVO(); //주입받는게 없어서 생성일단 해줌 /하단에서 vo를 사용해야하기떄문에
		vo.setTitle("게시물 제목");
		vo.setContent("게시물 내용");
		int r = 0;
		for(int i=0;i<100;i++) {
			r+= mapper.insert(vo);
		}
		log.info("등록갯수 "+ r );
	}
	
	//@Test
//	public void count() {
//		Map map = new HashMap();
//		map.put("stype","content");
//		map.put("sword","내용");
//		int totalCount = mapper.count(map);
//		log.info("총카운트 "+ totalCount);
//	}
	
//	@Test
//	public void list() {
//		Map map = new HashMap();
//		map.put("startIdx", 10);
//		map.put("pageRow", 10);
//		List<BoardVO> list = mapper.list(map);
//		list.forEach(vo ->log.info(vo));
//		log.info(list);
//	}
	//@Test
	public void view() {
		mapper.updateViewcount(5);
		log.info(mapper.view(5));
	}
	
	//@Test
	public void update() {
		BoardVO vo = new BoardVO();
		vo.setTitle("제목내용");
		vo.setContent("내용 수정");
		vo.setNo(1);
		log.info("update"+mapper.update(vo));
	}
	
	//@Test
	public void delete() {
		log.info("delete"+mapper.delete(3));
	}
	
}

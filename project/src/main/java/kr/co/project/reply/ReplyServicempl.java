package kr.co.project.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServicempl implements ReplyService {

	@Autowired
	ReplyMapper mapper;
	
	@Override
	public Map index(ReplyVO vo) {
		
		int totalCount = mapper.count(vo); 
		int totalPage = totalCount / vo.getPageRow() ; 
		if(totalCount % vo.getPageRow() > 0) totalPage++;
		
		
		int startIdx = (vo.getPage()-1) * vo.getPageRow();
		vo.setStartIdx(startIdx);
		
		List<ReplyVO> list =mapper.list(vo);
		
		
		int endPage = (int)(Math.ceil(vo.getPage()/10.0)*10);
		int startPage = endPage -9;
		if(endPage > totalPage) endPage = totalPage;
		boolean prev = startPage > 1 ? true : false;
		boolean next = endPage < totalPage ? true : false;
		
		Map map = new HashMap();
		map.put("totalCount", totalCount);
		map.put("totalPage", totalPage);
		map.put("endPage", endPage);
		map.put("startPage", startPage);
		map.put("list", list);
		map.put("prev", prev);
		map.put("next", next);
		
		return map;
	}

	@Override
	public ReplyVO view(int no) {
		mapper.updateViewcount(no);//조회수
		return mapper.view(no);
	}

	@Override
	public ReplyVO edit(int no) {
		return mapper.view(no);
	}

	@Override
	public boolean update(ReplyVO vo) {
		return mapper.update(vo) > 0 ?true:false;
	}

	@Override
	public boolean delete(int no) {
		return mapper.delete(no) > 0 ?true:false;
	}

	@Override
	public boolean insert(ReplyVO vo) {
		boolean r = mapper.insert(vo) > 0 ? true: false;
		if(r)mapper.gnoUpdate(vo.getNo());
		return r;
		
	}

	@Override
	public boolean reply(ReplyVO vo) {
		mapper.onoUpdate(vo);//부모의 gno와같고,부모의ono보다 큰 ono+1
		vo.setOno(vo.getOno()+1);
		vo.setNested(vo.getNested()+1);
		return mapper.reply(vo) >0 ? true :false;
	}

}

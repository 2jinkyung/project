package kr.co.project.reply;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapper{//src/main/resources 안의 xml폴더 경로와 일치해야함 /인터페이스 안에는 무조건 pulic
	int insert(ReplyVO vo); //-> 갯수이므로 int 사용
	int count(ReplyVO vo); //값은 title or contet 사용
	List<ReplyVO> list(ReplyVO vo);
	ReplyVO view(int no); //sql에서 no가 필요하므로 넘겨줌
	void updateViewcount(int no);
	int update(ReplyVO vo);
	int delete(int no);
	int gnoUpdate(int gno);
	int onoUpdate(ReplyVO vo);
	int reply(ReplyVO vo);

	
}

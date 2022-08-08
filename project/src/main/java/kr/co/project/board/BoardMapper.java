package kr.co.project.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {//src/main/resources 안의 xml폴더 경로와 일치해야함 /인터페이스 안에는 무조건 pulic
	int insert(BoardVO vo); //-> 갯수이므로 int 사용
	int count(BoardVO vo); //값은 title or contet 사용
	List<BoardVO> list(BoardVO vo);
	BoardVO view(int no); //sql에서 no가 필요하므로 넘겨줌
	void updateViewcount(int no);
	int update(BoardVO vo);
	int delete(int no);
}

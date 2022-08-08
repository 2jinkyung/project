package kr.co.project.comment;

import java.util.Map;

public interface CommentService {
		
	Map index(CommentVO vo); //목록 --카운트,리스트는 한꺼번에 리턴하기위해 map에 담아서 한번에 리턴되게
	int insert(CommentVO vo); 
	int delete(CommentVO vo);
}

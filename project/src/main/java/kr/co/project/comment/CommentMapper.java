package kr.co.project.comment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
	 int insert(CommentVO vo); //등록,수정,삭제는 변경된 갯수가 리턴됨
	 int count(CommentVO vo);
	 List<CommentVO> list(CommentVO vo); 
	 int delete(int no);
}

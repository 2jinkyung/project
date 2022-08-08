package kr.co.project.board;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter //data도 가능하지만 get,set 이외에 다른것도 생김
@ToString //?
public class BoardVO { //db sql로 사용하기위해
	private int no;
	private String title;
	private String content;
	private Timestamp regdate;
	private int viewcount;
	private int member_no;
	private String filename_org;
	private String filename_real;
	private String member_name;
	private int comment_count;
	
	private int page;
	private String stype;
	private String sword;
	private int startIdx;
	private int pageRow;
	
	public BoardVO() {//매개변수가 없을때 ? 값없이 사용할때
		this.page = 1;
		this.pageRow = 10;
		
		//this(1,10);
	}
	public BoardVO(int page,int pageRow) { //매개변수가 있을떄
		this.page = page;
		this.pageRow = pageRow;
	}
}

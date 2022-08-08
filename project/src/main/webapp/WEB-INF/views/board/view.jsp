<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import = "java.net.*" %> <!-- 다운로드할때 필요함 -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
    <meta name="format-detection" content="telephone=no, address=no, email=no">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>게시판 상세</title>
    <link rel="stylesheet" href="/project/css/reset.css"/>
    <link rel="stylesheet" href="/project/css/contents.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
    	function del(no){
    		if(confirm('삭제하시겠습니까?')){
    			location.href="delete.do?no="+no;
    		}
    	};
    	function getComment(page){ //스크립트가 실행되면 ajax가 실행
    		$.ajax({
    			url:"/project/comment/list.do",
    			data:{
    				board_no : ${data.no},
    				tablename : 'board',
    				page: page
    			},
    			success : function(res){
    				$("#commentArea").html(res);
    			}
    		})
    	};
    	$(function(){
    		getComment(1); //1은 return파일의 result값
    	});
    	function goSave(){
    		<c:if test="${empty loginInfo}">
    			alert("댓글은 로그인후 작성 가능합니다.")
    		</c:if>
   			<c:if test="${!empty loginInfo}">
   			if(confirm('댓글을 저장하시겠습니까?'))
    		$.ajax({
    			url:"/project/comment/insert.do",
    			data:{
    				board_no : ${data.no},// == param.no
    				tablename : 'board',
    				content:$("#contents").val(),
    				member_no:${loginInfo.no}
    			},
    			success : function(res){
    				if(res.trim()=='1'){
    					alert("정상적으로 댓글이 출력되었습니다.");
    					$("#contents").val('');
    					getComment(1);
    				}
    			}
    		})
    		</c:if>
    	}
    	function commentDel(no){
    		if(confirm("댓글을 삭제하시겠습니다.?")){
    			$.ajax({
    				url:'/project/comment/delete.do?no='+no,
    				success:function(res){
    					if(res.trim()=='1'){ //result값이 담김
    						alert("댓글이 정상적으로 삭제되었습니다.")
    						getComment(1);
    					}
    				}
    			})
    		}
    	}
    </script>
</head>
<body>
    
        <div class="sub">
            <div class="size">
                <h3 class="sub_title">게시판</h3>
                <div class="bbs">
                    <div class="view">
                        <div class="title">
                            <dl>
                                <dt>${data.title }</dt>
                                <dd class="date">작성일 : ${data.regdate } </dd>
                            </dl>
                        </div>
                        <div class="cont"><p>${data.content }</p> </div>
                        <dl class="file">
                            <dt>첨부파일 </dt>
                            <dd>
                            <a href="/project/common/download.jsp?oName=${URLEncoder.encode(data.filename_org,'UTF-8')}&sName=${ URLEncoder.encode(data.filename_real,'UTF-8') }" target="_blank">${data.filename_real }</a></dd>
                        </dl>
                                    
                        <div class="btnSet clear">
                            <div class="fl_l">
                            	<a href="index.do" class="btn">목록</a>
                            	<a href="edit.do?no=${data.no }" class="btn">수정</a>                            
                            	<a href="javascript:del(${data.no });" class="btn">삭제</a>                            
                            </div>
                        </div>
                 </div>
                 <!-- 댓글 -->
                 <div>
                    <form method="post" name="frm" id="frm" action="" enctype="multipart/form-data" >
                        <table class="board_write">
                            <colgroup>
                                <col width="*" />
                                <col width="100px" />
                            </colgroup>
                            <tbody>
                            <tr>
                                <td>
                                    <textarea name="contents" id="contents" style="height:50px;"></textarea>
                                </td>
                                <td>
                                    <div class="btnSet"  style="text-align:right;">
                                        <a class="btn" href="javascript:goSave();">저장 </a>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>

                  	<div id="commentArea"></div>
                  	
                    </div>
                </div>
            </div>
        </div>
        
</body>
</html>
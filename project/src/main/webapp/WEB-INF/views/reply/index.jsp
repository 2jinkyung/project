<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
    <meta name="format-detection" content="telephone=no, address=no, email=no">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>게시판목록</title>
    <link rel="stylesheet" href="/project/css/reset.css"/> <!-- 컨트롤러에 핸들러 매핑을 해야함 -->
    <link rel="stylesheet" href="/project/css/contents.css"/>
    <script>
    	function goWrite(){
    		<c:if test="${empty loginInfo}">
    			alert("로그인후 작성가능합니다.")
    		</c:if>
    		<c:if test="${!empty loginInfo}">
    			location.href="write.do"
    		</c:if>
    	}
    </script>
</head>
<body>
        <div class="sub">
            <div class="size">
                <h3 class="sub_title">게시판${loginInfo.email}</h3>
    
                <div class="bbs">
                    <table class="list">
                    <p><span><strong>총 ${data.totalCount}개</strong>  |  ${boardVO.page}/${data.totalPage}페이지</span></p> <!-- boardVO는 커멘드 객체임 -->
                        <caption>게시판 목록</caption>
                        <colgroup>
                            <col width="80px" />
                            <col width="*" />
                            <col width="*" />
                            <col width="100px" />
                            <col width="100px" />
                        </colgroup>
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>조회수</th>
                                <th>작성자</th>
                                <th>작성일</th>
                            </tr>
                        </thead>
                        <tbody>
                       	<c:if test="${empty data.list }">
                        	<tr>
                                <td class="first" colspan="5">등록된 글이 없습니다.</td>
                            </tr>
                       	</c:if>
                       	  <c:forEach items="${data.list}" var="board" varStatus="status">
                            <tr>
                                <td>${data.totalCount-status.index -(boardVO.page-1)*boardVO.pageRow } <!-- 총갯수-인덱스-(현재페이지번호-1)*패이지당갯수 --></td><!-- ${board.no } -->
                                <td class="txt_l" style="text-align:left;">
                                	<c:forEach begin="1" end="${board.nested }">&nbsp;&nbsp;&nbsp;</c:forEach>
                                	<c:if test="${board.nested > 0}"><img src="/project/img/answer_icon.gif"></c:if>
                               		<a href="view.do?no=${board.no}">${board.title } [${board.comment_count }]</a>
                               	</td>
                                <td>${board.viewcount }</td>
                                <td class="writer">${board.member_name }</td>
                                <td class="date"><fmt:formatDate value="${board.regdate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="btnSet"  style="text-align:right;">
	                        <a class="btn" href="javascript:goWrite();">글작성 </a>
                    </div>
                    <div class="pagenate clear">
                        <ul class='paging'>
                        	<c:if test="${data.prev ==true }">
                        		<li><a href="index.do?page=${data.startPage-1 }&stype=${param.stype}&sword=${param.sword}">&lt;</a></li>
                        	</c:if>
                        	<c:forEach var="p" begin="${data.startPage }" end="${data.endPage }">
                            <li><a href="index.do?page=${p }&stype=${param.stype}&sword=${param.sword}" <c:if test="${boardVO.page == p }">class='current'</c:if>>${p }</a></li>
                            </c:forEach>
                            <c:if test="${data.next ==true }">
                        		<li><a href="index.do?page=${data.endPage+1 }&stype=${param.stype}&sword=${param.sword}">&gt;</a></li>
                        	</c:if>
                        </ul> 
                    </div>
                
                    <!-- 페이지처리 -->
                    <div class="bbsSearch">
                        <form method="get" name="searchForm" id="searchForm" action="">
                            <span class="srchSelect">
                                <select id="stype" name="stype" class="dSelect" title="검색분류 선택">
                                    <option value="all">전체</option>
                                    <option value="title">제목</option>
                                    <option value="content">내용</option>
                                </select>
                            </span>
                            <span class="searchWord">
                                <input type="text" id="sval" name="sword" value="${param.sword }"  title="검색어 입력">
                                <input type="button" id="" value="검색" title="검색">
                            </span>
                        </form>
                        
                    </div>
                </div>
            </div>
        </div>
        
</body>
</html>
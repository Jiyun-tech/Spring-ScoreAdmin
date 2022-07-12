<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>선택 학생 조회</title>
</head>
<body>
<c:choose>
	<c:when test="${function eq \"addScoreItem\"}">
		<div class="title"> 선택 학생 성적 추가 완료 </div>
	</c:when>
	<c:when test="${function eq \"deleteScoreItem\"}">
		<div class="title"> 선택 학생 성적 삭제 완료 </div>
	</c:when>
	<c:when test="${function eq \"updateScoreItem\"}">
		<div class="title"> 선택 학생 성적 수정 완료 </div>
	</c:when>
	<c:otherwise>
		<div class="title"> 선택 학생 성적 조회 </div>
	</c:otherwise>
</c:choose>
<br>
<div class=small_title> 학번 : ${get_list.studentid}</div>
<div class=small_title> 이름 : ${get_list.name} </div>
<br>
<div class=small_title> 현재 페이지 : <c:out value="${ get_pagination.cPage }"/> </div>
<br>

		<div class="container">
		
		<form method="POST">
		<table class="add_button">
			<tr>
				<td align="right">
				<input type="submit" value="성적 추가" formaction="/addScoreItem?listId=${get_list.id}
																			&name=${get_list.name}
																			&studentid=${get_list.studentid}">
				</td>
			</tr>
		</table>
		</form>
		
		<form method="POST">
		<table class="content_table">
		<!-- 테이블 항목 출력 -> 내용 가운데 정렬 -->
		<tr>
		<td class="category_score"><p align=center>NO</p></td>
		<td class="category_id"><p align=center>시험</p></td>
		<td class="category_score"><p align=center>국어</p></td>
		<td class="category_score"><p align=center>영어</p></td>
		<td class="category_score"><p align=center>수학</p></td>
		<td class="category_score"><p align=center>수정</p></td>
		<td class="category_score"><p align=center>삭제</p></td>
		</tr>
		
		<c:forEach var="itemlist" items="${get_item}" varStatus="status">
			<c:choose>
			<c:when test="${target eq itemlist.id}">
				<tr class="updatedOne">
					<td class="category_score"><p align=center>${status.count}</p></td>
					<td class="category_id"><p align=center>${itemlist.test}</p></td>
					<td class="category_score"><p align=center>${itemlist.kor}</p></td>
					<td class="category_score"><p align=center>${itemlist.eng}</p></td>
					<td class="category_score"><p align=center>${itemlist.mat}</p></td>
					<td class="category_score"><input type="submit" value="수정" formaction="/updateScoreItem?listId=${get_list.id}
																									&itemId=${itemlist.id}
																									&name=${get_list.name}
																									&studentid=${get_list.studentid}
																									&test=${itemlist.test}"/></td>
					<td class="category_score"><input type="submit" value="삭제" formaction="/deleteScoreItem?listId=${get_list.id}
																									&itemId=${itemlist.id}
																									&name=${get_list.name}
																									&studentid=${get_list.studentid}
																									&test=${itemlist.test}"/></td>
				</tr> 
			</c:when>
			<c:otherwise> 
				<tr>
					<td class="category_score"><p align=center>${status.count + LineCount}</p></td>
					<td class="category_id"><p align=center>${itemlist.test}</p></td>
					<td class="category_score"><p align=center>${itemlist.kor}</p></td>
					<td class="category_score"><p align=center>${itemlist.eng}</p></td>
					<td class="category_score"><p align=center>${itemlist.mat}</p></td>
					<td class="category_score"><input type="submit" value="수정" formaction="/updateScoreItem?listId=${get_list.id}
																									&itemId=${itemlist.id}
																									&name=${get_list.name}
																									&studentid=${get_list.studentid}
																									&test=${itemlist.test}"/></td>
					<td class="category_score"><input type="submit" value="삭제" formaction="/deleteScoreItem?listId=${get_list.id}
																									&itemId=${itemlist.id}
																									&name=${get_list.name}
																									&studentid=${get_list.studentid}
																									&test=${itemlist.test}"/></td>
				</tr>
			</c:otherwise>
			</c:choose> 
		</c:forEach>
		</table>
		</form>
		
		<!-- Pagination --> 
		<table class="pager_table">
			<tr>
			<!-- 4-1. << 화살표, < 화살표 --> 
			<td width="50"><p align="center"><a class="alink" href="/viewOneStudent?listId=${listId}
																		&cPage=${get_pagination.ppPage}
																		&count=${get_pagination.countPerPage}
																		&target=${target}
																		&function=${function}"> << </a></p></td>
			<td width="50"><p align="center"><a class="alink" href="/viewOneStudent?listId=${listId}
																		&cPage=${get_pagination.pPage}
																		&count=${get_pagination.countPerPage}
																		&target=${target}
																		&function=${function}"> < </a></p></td>
	
			<c:forEach var="i" begin="${get_pagination.sList}" end="${get_pagination.eList}" step="1">
				<c:choose>
					<c:when test="${i eq get_pagination.cPage}">
					<td width=50 class="selectedPage"><p align=center><a class=alink href="viewOneStudent?listId=${listId}
																							&cPage=${i}
																							&count=${get_pagination.countPerPage}
																							&target=${target}
																							&function=${function}"> ${i} </a></p></td>
					</c:when>
					<c:otherwise>
					<td width=50><p align=center><a class=alink href="/viewOneStudent?listId=${listId}
																		&cPage=${i}
																		&count=${get_pagination.countPerPage}
																		&target=${target}
																		&function=${function}"> ${i} </a></p></td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
					
			<!-- 4-3. > 화살표, >> 화살표  --> 
			<!-- 페이지 이동 화살표 클릭 시의 Parameter 결정 (데이터 출력 시작점) --> 
			<td width=50><p align=center><a class=alink href="/viewOneStudent?listId=${listId}
																		&cPage=${get_pagination.nPage}
																		&count=${get_pagination.countPerPage}
																		&target=${target}
																		&function=${function}"> > </a></p></td>
			<td width=50><p align=center><a class=alink href="/viewOneStudent?listId=${listId}
																		&cPage=${get_pagination.nnPage}
																		&count=${get_pagination.countPerPage}
																		&target=${target}
																		&function=${function}"> >> </a></p></td>
			</tr>
		</table>
		
		</div>
	<style>
		body {
			background-color: #E5CCFF;
			color: black;
			font-weight: bold;
			margin: auto;
			margin-top: 2%;
		}
		.title {
			text-align: center;
			font-size: 30pt;
		}
		.small_title {
			font-size: 15pt;
			text-align: center;
		}
		.add_button {
			width: 50%;
			margin: auto;
			text-item: right;
		}
		.category_name {
			width: 100;
			border: 1px solid black;
		}
		.category_id {
			width: 100;
			border: 1px solid black;
		}
		.category_score {
			width: 50;
			border: 1px solid black;
		}
		.container {
			item-align: center;
			text-align: center;
		}
		.content_table {
			border-spacing: 0;
			border-collapse : collapse;
			width: 50%;
			border: 1px solid black;
			margin: auto;
		}
		.pager_table {
			border-spacing: 0;
			width: 50%;
			border-width: 0px;
			margin: auto;
		}
		
		.selectedPage {
			font-size: 25px;
			text-decoration-line: underline;
		}
		.updatedOne {
			background-color: #B266FF;
		}
		
		a:link {
			color: black;
			text-decoration-line: none;
		}
		a:visited {
			color: black;
			text-decoration-line: none;
		}
	</style>
</body>
</html>
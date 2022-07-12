<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>Student List</title>
</head>
<body>
	<c:choose>
		<c:when test="${function eq \"update\" }">
			<div class="title"> 선택 학생 조회 </div>
		</c:when>
		<c:when test="${function eq \"updateOne\" }">
			<div class="title"> 학생 정보 수정 완료 </div>
		</c:when>
		<c:when test="${function eq \"deleteOne\" }">
			<div class="title"> 학생 정보 삭제 완료 </div>
			<br> 
			<div class=small_title> 학번 : ${get_studentid}</div>
			<div class=small_title> 이름 : ${get_name} </div>
			<br>
		</c:when>
		<c:otherwise>
			<div class="title"> 전체 학생 조회 </div>
		</c:otherwise>
	</c:choose>
	<br>
	
	<div class=small_title> 현재 페이지 : <c:out value="${ get_pagination.cPage }"/> </div>
	<br>
	
	<div class="container">
	
		<table class="content_table">
		<!-- 테이블 항목 출력 -> 내용 가운데 정렬 -->
		<tr>
		<td class="category_score"><p align=center>NO</p></td>
		<td class="category_id"><p align=center>학번</p></td>
		<td class="category_name"><p align=center>이름</p></td>
		<td class="category_name" width="10%"><p align=center>성적 관리</p></td>
		<td class="category_name" width="10%"><p align=center>수정</p></td>
		<td class="category_name" width="10%"><p align=center>삭제</p></td>
		</tr>
		
		<!-- 선택 학생 상세 조회 (성적 / 수정 / 삭제)-->
		<c:forEach var="list" items="${get_list}" varStatus="status">
			<c:choose>
			<c:when test="${target eq list.id}">
				<form method="POST">
				<tr class="updatedOne">
					<td class="category_score"><p align=center>${status.count + LineCount}</p></td>
					<td class="category_id"><p align=center>${list.studentid}</p></td>
					<td class="category_name"><p align=center>${list.name}</p></td>
					<td class="category_name"><input type="submit" value="성적" formaction="/viewOneStudent?listId=${list.id}
																								&pageSize=${get_pagination.pageSize}"/></td>
					<td class="category_name"><input type="submit" value="수정" formaction="/updateScoreList?id=${list.id}"/></td>
					<td class="category_name"><input type="submit" value="삭제" formaction="/deleteScoreList?id=${list.id}&name=${list.name}&studentid=${list.studentid}"/></td>
				</tr>
				</form>
			</c:when>
			<c:otherwise>
				<form method="POST">
					<tr>
						<td class="category_score"><p align=center>${status.count + LineCount}</p></td>
						<td class="category_id"><p align=center>${list.studentid}</p></td>
						<td class="category_name"><p align=center>${list.name}</p></td>
						<td class="category_name"><input type="submit" value="성적" formaction="/viewOneStudent?listId=${list.id}
																								&pageSize=${get_pagination.pageSize}"/></td>
						<td class="category_name"><input type="submit" value="수정" formaction="/updateScoreList?id=${list.id}"/></td>
						<td class="category_name"><input type="submit" value="삭제" formaction="/deleteScoreList?id=${list.id}&name=${list.name}&studentid=${list.studentid}"/></td>
					</tr>
				</form>
			</c:otherwise>
			</c:choose>
		</c:forEach>
		</table>
				
		<!-- Pagination --> 
		<table class="pager_table">
			<tr>
			<!-- 4-1. << 화살표, < 화살표 --> 
			<td width="50"><p align="center">
				<a class="alink" href="/viewScoreList?cPage=${get_pagination.ppPage}
											&count=${get_pagination.countPerPage}
											&target=${target}
											&pageSize=${get_pagination.pageSize}
											&function=${function}
											&name=${get_name}
											&studentid=${get_studentid}"> << </a></p></td>
			<td width="50"><p align="center">
				<a class="alink" href="/viewScoreList?cPage=${get_pagination.pPage}
											&count=${get_pagination.countPerPage}
											&target=${target}
											&pageSize=${get_pagination.pageSize}
											&function=${function}
											&name=${get_name}
											&studentid=${get_studentid}"> < </a></p></td>
	
			<c:forEach var="i" begin="${get_pagination.sList}" end="${get_pagination.eList}" step="1">
				<c:choose>
					<c:when test="${i eq get_pagination.cPage}">
					<td width=50 class="selectedPage"><p align=center>
						<a class=alink href="viewScoreList?cPage=${i}
											&count=${get_pagination.countPerPage}
											&target=${target}
											&pageSize=${get_pagination.pageSize}
											&function=${function}
											&name=${get_name}
											&studentid=${get_studentid}"> ${i} </a></p></td>
					</c:when>
					<c:otherwise>
					<td width=50><p align=center>
					<a class=alink href="/viewScoreList?cPage=${i}
											&count=${get_pagination.countPerPage}
											&target=${target}
											&pageSize=${get_pagination.pageSize}
											&function=${function}
											&name=${get_name}
											&studentid=${get_studentid}"> ${i} </a></p></td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
					
			<!-- 4-3. > 화살표, >> 화살표  --> 
			<!-- 페이지 이동 화살표 클릭 시의 Parameter 결정 (데이터 출력 시작점) --> 
			<td width=50><p align=center>
				<a class=alink href="/viewScoreList?cPage=${get_pagination.nPage}
											&count=${get_pagination.countPerPage}
											&target=${target}
											&pageSize=${get_pagination.pageSize}
											&function=${function}
											&name=${get_name}
											&studentid=${get_studentid}"> > </a></p></td>
			<td width=50><p align=center>
				<a class=alink href="/viewScoreList?cPage=${get_pagination.nnPage}
											&count=${get_pagination.countPerPage}
											&target=${target}
											&pageSize=${get_pagination.pageSize}
											&function=${function}
											&name=${get_name}
											&studentid=${get_studentid}"> >> </a></p></td>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
 <head>
	 <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	 <!-- 인코딩 방식 선언 ==> UTF-8 ==> 한글 깨지지 않도록 함 -->
 </head>
 <body>
	<style>
		body{
			background-color: black;
		}
		table {
			width: 80%;
			align-items: center;
			margin: auto;
			margin-top: 2%;
		}
		td{
			width:10%;
			text-align: center;
			font-size: 20pt;
			font-family: "Gill Sans", sans-serif;
			font-family: "Gill Sans", sans-serif;
 			word-break:normal;	/*자동 줄바꿈 */
 			padding-top: 0.5%;
 			padding-bottom: 0.5%;
		}
		.menu {
			border: 2px solid #E5CCFF;
		}
		a:link {
			color: white;
			text-decoration-line: none;
		}
		a:visited {
			color: white;
			text-decoration-line: none;
		}	

	</style>
	<table>
		<!-- 메뉴 만들고, anchor 태그로 각각의 파일 연결. -->
		<!-- 이 때, 연결해서 보여줄 페이지 범위를 지정 ==> "main" (하단 frame)-->
		<tr>
			<td class="menu"><a href="./intro.html" target="main">　　🏡　　</a></td>
			<td class="menu"><a href="/viewScoreList" target="main">전체 학생 조회</a></td>
			<td class="menu"><a href="./selectScoreList.html" target="main">학생 검색</a></td>
			<td class="menu"><a href="./addScoreList.html" target="main">학생 추가</a></td>
		</tr>
	</table>

	<table>
 </body>
 <html>
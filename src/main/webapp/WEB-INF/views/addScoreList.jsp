<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>Result_Save New Student</title>
</head>
<body>
	<div class="title">학생 추가 완료</div>
	<br>
	<br>

	<div class="container">

<!-- 			<table class="button"> -->
<!-- 				<tr> -->
<!-- 					<td align="right"><input type="submit" value="추가"></td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
			<table class="content_table">
				<tr>
					<td class="input_category" width="100" align="center">이름</td>
					<td class="input_content" width="300" align="center">${get_name}</td>
				</tr>
				<tr>
					<td class="input_category" align="center">학번</td>
					<td class="input_content" align="center">${get_studentid}</td>
				</tr>
			</table>
			<br>
			<table class="notice_table">
				<tr>
					<td>* 이름 : 최대 20자</td>
				</tr>
				<tr>
					<td>* 학번 : 시스템 자동 부여</td>
				</tr>
			</table>

	</div>

	<style>
		body {
			background-color: #E5CCFF;
			color: black;
			font-weight: bold;
			margin: auto;
			padding-top: 2%;
			padding-bottom: 3%;
		}
		
		.title {
			font-size: 30pt;
			text-align: center;
		}
		
		.small_title {
			font-size: 20pt;
			text-align: center;
		}
		
		.input_category {
			width: 100;
			border: 1px solid black;
		}
		
		.input_content {
			width: 200;
			border: 1px solid black;
		}
		
		.container {
			item-align: center;
		}
		
		.button {
			width: 40%;
			margin: auto;
			text-align: center;
		}
		
		.content_table {
			border-spacing: 0;
			width: 40%;
			border: 1px solid black;
			margin: auto;
			text-align: center;
		}
		
		.notice_table {
			border-spacing: 0;
			width: 40%;
			border: 0px solid black;
			margin: auto;
		}
	</style>

</body>
</html>
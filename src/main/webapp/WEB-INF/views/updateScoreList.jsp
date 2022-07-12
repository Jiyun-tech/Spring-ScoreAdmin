<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>Update Student Information</title>
</head>
<body>
	<div class="title">학생 정보 수정</div>
	<br>
	<br>

	<div class="container">

		<form method="post">
			<table class="button">
				<tr>
					<td align="right">
						<input type="submit" value="수정" formaction="/viewScoreList?id=${get_scoreList.id}"/>
					</td>
				</tr>
			</table>
			<table class="content_table">
				<tr>
					<td class="input_category" width="100" align="center">구분</td>
					<td class="input_category" width="200" align="center">수정 전</td>
					<td class="input_category" width="200" align="center">수정 후</td>
				</tr>
				<tr>
					<td class="input_category" align="center">이름</td>
					<td class="input_category" align="center">${get_scoreList.name}</td>
					<td class="input_content" align="center"><input
						type="text" name="name" id="name" maxlength="20"
						onsubmit="characterCheck(this)" onkeyup="characterCheck(this)"
						onkeydown="characterCheck(this)"
						value="${get_scoreList.name}"></td>
				</tr>
				<tr>
					<td class="input_category" align="center">학번</td>
					<td class="input_category" align="center">${get_scoreList.studentid}</td>
					<td class="input_category" align="center" name="studentid">${get_scoreList.studentid}</td>
				</tr>
			</table>
			<br>
			<table class="notice_table">
				<tr>
					<td>* 이름 수정 가능, 학번 수정 불가</td>
				</tr>
				<tr>
					<td>* 이름 : 최대 20자</td>
				</tr>
			</table>
		</form>
	</div>
	<script>
	function characterCheck(obj) {
		let reg = /[\{\}\[\]\/?.,;:|\-_+<>@\#$%&\'\"\\\()\=`~!^*123456789\s]/gi;
		if (reg.test(obj.value)) {
			alert("특수 문자나 숫자, 띄어쓰기는 입력하실 수 없습니다.");
			obj.value = obj.value.substring(0, 0);
		}
	}
	
	function idCheck(obj) {
		let reg = /[\{\}\[\]\/?.,;:|\-_+<>@\#$%&\'\"\\\()\=`~!^*\s]/gi;
		if (reg.test(obj.value)) {
			alert("특수 문자나 띄어쓰기는 입력하실 수 없습니다.");
			obj.value = obj.value.substring(0, 0);
		}
	}

	window.onload = function() {
		document
				.querySelector('form')
				.addEventListener(
						'submit',
						function(e) {
							if (document.getElementById('name').value == '' 
									|| document.getElementById('studentid').value == '') {
								e.preventDefault()//이름,학번 미입력을 방지
								alert('이름, 학번 입력 필수')
							} 
						});
	}
	</script>
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
</html></html>
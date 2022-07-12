<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>Save New Student</title>
</head>
<body>
	<div class="title">학생 성적 추가</div>
	<br>
	<br>

	<div class="container">

		<form method="post" action="/addScoreItem_result?listId=${get_id}">
			<table class="button">
				<tr>
					<td align="right"><input type="submit" value="성적 추가 등록"></td>
				</tr>
			</table>
			<table class="content_table">
				<tr>
					<td class="input_category" width="100" align="center">이름</td>
					<td class="input_content" width="300" align="center"><input type="text" readonly name="name" id="name" value="${get_name}"></td>
				</tr>
				<tr>
					<td class="input_category" align="center">학번</td>
					<td class="input_content" align="center"><input type="number" readonly name="studentid" id="studentid" value="${get_studentid}"></td>
				</tr>
				<tr>
					<td class="input_category" align="center">시험</td>
					<td class="input_content" align="center">
						<select name="test" id="test">
							<option value="1-1중간고사">1-1중간고사</option>
							<option value="1-1기말고사">1-1기말고사</option>
							<option value="1-2중간고사">1-2중간고사</option>
							<option value="1-2기말고사">1-2기말고사</option>
							<option value="2-1중간고사">2-1중간고사</option>
							<option value="2-1기말고사">2-1기말고사</option>
							<option value="2-2중간고사">2-2중간고사</option>
							<option value="2-2기말고사">2-2기말고사</option>
							<option value="3-1중간고사">3-1중간고사</option>
							<option value="3-1기말고사">3-1기말고사</option>
							<option value="3-2중간고사">3-2중간고사</option>
							<option value="3-2기말고사">3-2기말고사</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="input_category" align="center">국어</td>
					<td class="input_content" align="center">
						<input type="number" name="kor" id="kor" min="0" max="100">
					</td>
				</tr>
				<tr>
					<td class="input_category" align="center">영어</td>
					<td class="input_content" align="center">
						<input type="number" name="eng" id="eng" min="0" max="100">
					</td>
				</tr>
				<tr>
					<td class="input_category" align="center">수학</td>
					<td class="input_content" align="center">
						<input type="number" name="mat" id="mat" min="0" max="100">
					</td>
				</tr>
			</table>
		</form>
			<br>
			<table class="notice_table">
				<tr>
					<td>*이름/학번 수정 불가</td>
				</tr>
				<tr>
					<td>*공란 등록 불가</td>
				</tr>
				<tr>
					<td>*성적 : 0점 ~ 100점</td>
				</tr>
			</table>
	</div>
	<script>
	window.onload = function() {
		document
				.querySelector('form')
				.addEventListener(
						'submit',
						function(e) {
							if (document.getElementById('kor').value == ''
								|| document.getElementById('eng').value == ''
								|| document.getElementById('mat').value == '') {
								e.preventDefault()//성적 미입력을 방지
								alert('성적을 입력하세요 (0점 ~ 100점)')
							} 
						});
	}
</script>
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
</html>
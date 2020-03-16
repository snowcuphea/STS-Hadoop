<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="kr.multi.bigdataShop.product.*"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="/bigdataShop/common/css/jqcloud.css" />
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script type="text/javascript"
	src="/bigdataShop/common/js/jqcloud-1.0.4.js"></script>



</head>
<body>


	<h1>인기 키워드 순위</h1>

	<script type="text/javascript">
		var word_array = new Array();

		var word_array = [];

		<c:forEach var="result" items="${resultlist}">
		word_array.push({
			text : "${result.keyword}",
			weight : "${result.counter}"
		});
		</c:forEach>

		$(function() {
			// When DOM is ready, select the container element and call the jQCloud method, passing the array of words as the first argument.
			$("#example").jQCloud(word_array);
		});
	</script>


	<div id="example" style="width: 550px; height: 350px;"></div>

	<hr />
	<table style="border-collapse: collapse; text-align: center; width: 500px;"
		class="table table-hover" >

		<thead>
			<tr style="text-align: center;">
				<th>Keyword</th>
				<th>Count</th>

			</tr>
		</thead>

		<c:if test="${!empty resultlist}">
			<c:forEach var="result" items="${resultlist}" varStatus="status"
				end="9">
				<tr>
					<td>${result.keyword}</td>
					<td>${result.counter}</td>
				</tr>
			</c:forEach>
		</c:if>

	</table>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fy" uri="/wezhao/fenye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Pagination</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/project.css" />
</head>
<body>
	<div>
		<div class="navbar">
			<ul class="nav">
				<li id="navHome" class="active"><a href=${pageContext.request.contextPath}/HomeServlet>Home</a></li>
			</ul>
		</div>
		<div class="content clearfix">
			<section class="build-list">
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="history" items="${histories}">
						<tr>
							<td>${history.id}</td>
							<td>${history.name}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</section>
			<div class="pagination">
				<ul>
					<fy:fenye currentpage="${cp}" sum="${allpage}" myrequest="${rp}" />
				</ul>
			</div>
		</div>
	</div>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Spring forms :: Employees</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"
	rel="stylesheet">

<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Employees app</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="<s:url value="/employees/" />"
						title="<s:message code="navbar.employees"></s:message>"><s:message
								code="navbar.employees"></s:message></a></li>
					<li><a href="<s:url value="/departments/" />"
						title="<s:message code="navbar.depart"></s:message>"><s:message
								code="navbar.depart"></s:message></a></li>
					<li><a href="<s:url value="/employees/new" />"
						title="<s:message code="navbar.newEm"></s:message>"><s:message
								code="navbar.newEm"></s:message></a></li>
				</ul>
				<div class="nav navbar-nav navbar-right">
					<c:url var="get_employees" value="/employees/search" />
					<sf:form class="navbar-form" method="post"
						modelAttribute="employeeS" action="${get_employees}">
						<div class="input-group">
							<sf:input path="name" class="form-control" placeholder="Search" />
							<div class="input-group-btn">
								<sf:button class="btn btn-default" type="submit">
									<i class="glyphicon glyphicon-search"></i>
								</sf:button>
							</div>
						</div>
					</sf:form>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="true"> <s:message
								code="navbar.language"></s:message> <span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="?locale=en"> <img src="blank.gif"
									class="flag flag-gb"
									alt="<s:message code="english"></s:message>" /> <s:message
										code="english"></s:message>
							</a></li>
							<li><a href="?locale=es"> <img src="blank.gif"
									class="flag flag-es"
									alt="<s:message code="spanish"></s:message>" /> <s:message
										code="spanish"></s:message>
							</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="jumbotron">
			<h1><s:message code="navbar.newEm"></s:message></h1>
		</div>
		<c:url var="post_employee" value="/employees/new" />
		<sf:form method="post" modelAttribute="employee"
			class="form-horizontal" action="${post_employee}">
			<div class="form-group">
				<label for="name" class="control-label col-sm-1"><s:message
								code="table.name"></s:message></label>
				<div class="col-sm-11">
					<sf:input path="name" class="form-control" placeholder="Name" />
					<sf:errors path="name" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label for="birthDate" class="control-label col-sm-1"><s:message
								code="table.date"></s:message></label>
				<div class="col-sm-11">
					<sf:input path="birthDate" class="form-control" type="text"
						placeholder="00-00-0000" />
					<sf:errors path="birthDate" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label for="telephone" class="control-label col-sm-1"><s:message
								code="table.telephone"></s:message></label>
				<div class="col-sm-11">
					<sf:input path="telephone" class="form-control" type="tel"
						placeholder="Telephone" />
					<sf:errors path="telephone" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label for="department" class="control-label col-sm-1"><s:message
								code="department"></s:message></label>
				<div class="col-sm-11">
					<sf:select path="idDepartment" class="form-control" >
						<sf:option value="0" label="" /> 
						<sf:options items="${department}" />
					</sf:select>
					<sf:errors path="idDepartment" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-11">
					<sf:button type="submit" class="btn btn-default"><s:message
								code="btn.submit"></s:message></sf:button>
				</div>
			</div>
		</sf:form>

		<footer>
			<hr />
			<div class="navbar container">
				<p class="text-muted">&copy; 2017 David Larrea</p>
			</div>
		</footer>
	</div>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</body>
</html>
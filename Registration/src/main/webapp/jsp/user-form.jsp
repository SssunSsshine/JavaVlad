<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>User Registration</title>
<link rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
<link href="<%=request.getContextPath()%>/resource/css/style.css" rel="stylesheet">
</head>
<body>
    <div id="bg">
        <img src="<%=request.getContextPath()%>/resource/back.jpg" alt="">
    </div>
    <header>
        <nav class="navbar navbar-expand-sm navbar-dark" style="background-color: #ef86c5">
          <div class="container-fluid ">
            <a class="navbar-brand " href="<%=request.getContextPath()%>/user/login">User Registration </a>
          </div>
        </nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${user != null && !isInsert}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user == null || isInsert}">
					<form action="<%=request.getContextPath()%>/insert" method="post">
				</c:if>

				<caption>
					<h2>
                        <c:if test="${user != null && !isInsert}">
                            Edit User
                        </c:if>
                        <c:if test="${user == null || isInsert}">
                            Sign Up New User
                        </c:if>
					</h2>
				</caption>

				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>

                <fieldset class="form-group">
					<label>User Surname</label> <input type="text"
						value="<c:out value='${user.surname}' />" class="form-control"
						name="surname" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>User Name</label> <input type="text"
						value="<c:out value='${user.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
                    <label>User Birthday</label> <input type="date"
                        value="<c:out value='${user.birthday}' />" class="form-control"
                        name="birthday" placeholder="yyyy-mm-dd" required="required">
                </fieldset>

                <fieldset class="form-group">
					<label>User Phone</label> <input type="tel"
						value="<c:out value='${user.phone}' />" class="form-control"
						name="phone" placeholder="+79103449422" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>User Email</label> <input type="email"
						value="<c:out value='${user.email}' />" class="form-control"
						name="email" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>User Password</label> <input type="password"
						value="<c:out value='${user.password}' />" class="form-control"
						name="password">
				</fieldset>
                <c:if test="${error != null}">
                    <font color="red"> <c:out value='${error}' /></font>
                </c:if>
                <div class="text-center">
				    <button type="submit" class="btn btn-success">Save</button>
                </div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
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
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
              <ul class="navbar-nav ">
                <li class="nav-item">
                  <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/user/page">Home</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link active" href="<%=request.getContextPath()%>/sign-out">Sign Out</a>
                </li>
              </ul>
            </div>
          </div>
        </nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${note != null}">
					<form action="<%=request.getContextPath()%>/note/update" method="post">
				</c:if>
				<c:if test="${note == null}">
					<form action="<%=request.getContextPath()%>/note/insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${note != null}">
            			    Edit Note
            		    </c:if>
						<c:if test="${note == null}">
            			    Insert New Note
            		    </c:if>
					</h2>
				</caption>

				<c:if test="${note != null}">
                    <input type="hidden" name="id" value="<c:out value='${note.id}' />" />
                </c:if>

                <fieldset class="form-group">
					<label>Note Text</label>
					<textarea rows="5" cols = "30" class="form-control"
                        name="text" required="required"><c:out value='${note.text}'/></textarea>
				</fieldset>

                <c:if test="${error != null}">
                    <font color="red"> <c:out value='${error}' /></font>
                </c:if>
                <div class="text-center">
				    <button type="submit" class="btn btn-success">Save</button>
				    <c:if test="${note != null}">
				        <a href="<%=request.getContextPath()%>/note/delete?id=${note.id}" class="btn btn-danger" role="button">Delete</a>
                    </c:if>
                </div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
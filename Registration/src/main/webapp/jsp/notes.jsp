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

	<div class="container col-md-5">
        <div class="card">
    	    <div class="card-body">
                <caption>
                    <h2>
                         Your notes
                    </h2>
                </caption>

                <c:if test="${notes != null}">
                    <table class="table">
                        <thead>
                          <tr>
                            <th scope="col">Information</th>
                            <th scope="col"></th>
                          </tr>
                        </thead>
                        <tbody>

                            <c:forEach var="note" items="${notes}">
                                <tr>
                                    <td>
                                        <p>ID: ${note.id}</p>
                                        <p>Text: ${note.text}</p>
                                        <p>Creation Time: ${note.timeCreation}</p>
                                    </td>
                                    <td>
                                        <a href="<%=request.getContextPath()%>/note/edit?id=${note.id}" class="btn btn-success" role="button">Edit</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                      </table>
                </c:if>
            </div>
        </div>
	</div>
</body>
</html>
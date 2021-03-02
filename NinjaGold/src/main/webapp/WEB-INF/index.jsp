<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
	<title>Ninja Gold</title>
</head>
<body>
	 <div id="container">
    <h1>Ninja Gold</h1>
    <header>
      <p>Your Gold: <span><c:out value="${ gold }"/></span></p>
    </header>
    <div id="contents">
      <div class="location">
        <h3>Farm</h3>
        <p>(earns 10-20 gold)</p>
        <a href="/process/?location=farm">Find Gold</a>
      </div>
      <div class="location">
        <h3>Cave</h3>
        <p>(earns 5-10 gold)</p>
        <a href="/process/?location=cave">Find Gold</a>
      </div>
      <div class="location">
        <h3>House</h3>
        <p>(earns 2-5 gold)</p>
        <a href="/process/?location=house">Find Gold</a>
      </div>
      <div class="location">
        <h3>Casino</h3>
        <p>(earns/take 0-50 gold)</p>
        <a href="/process/?location=casino">Find Gold</a>
      </div>
      <div class="location">
        <h3>Spa</h3>
        <p>(costs 5-20 gold)</p>
        <a href="/process/?location=spa">Enjoy Spa</a>
      </div>
    </div>
    <p>Activities:</p>
    <div id="action">
    	<c:forEach items="${actions}" var="action">
          <p class=<c:out value="${action[0]}"/>><c:out value="${action[1]}"/></p>
      	</c:forEach>
    </div>
    <p id="reset"><a href="/reset">Reset</a></p>
  </div>
</body>
</html>
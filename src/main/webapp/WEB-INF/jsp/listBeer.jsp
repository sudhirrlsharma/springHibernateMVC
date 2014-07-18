<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<title><fmt:message key="title" /></title>
</head>
<body>
<h1><fmt:message key="heading" /></h1>
<p><fmt:message key="ListBeer" /></p>
<h3>Beers</h3>
Total beers are  <c:out value="${fn:length(allBeer)}" /></i>
<br> 
<c:forEach items = "${allBeer}" var="beer">

<a href="<spring:url value="/myapp/editBeers/{beerId}"><spring:param name="beerId" value="${beer.id}" /></spring:url>">
	<b><font color="BLACK" size="2"><c:out value="${beer.brand}"/> </font></b>
</a>
	<i>$<c:out value="${beer.price}" /></i>
	<br>
	<br>
</c:forEach>

<a href="<c:url value="/myapp/editBeers/new"></c:url>">
  <b><font color="BLACK" size="2">New Beer</font></b>
</a>
</body>
</html>

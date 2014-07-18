<%@ include file="/WEB-INF/jsp/include.jsp"%>
	<head>
		<title>Beer Form</title>
	</head>
	<body>

		<h1>
			Edit this beer
		</h1>
	
<form:form method="post" action="." commandName="beer">
  <table>
  <form:hidden path="id" />
   <tr>
        <td><form:label path="brand"><spring:message code="label.brand"/></form:label></td>
        <td><form:input path="brand" /></td>
        <td><form:errors path="brand" /></td>
    </tr>
    <tr>
   <tr>   
      <td><form:label path="price"><spring:message code="label.price"/></form:label></td>
        <td><form:input path="price" /></td>
        <td><form:errors path="price" /></td>
    </tr>
     <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="label.addBeer"/>"/>
        </td>
    </tr>
  </table>

</form:form>
</body>
</html>

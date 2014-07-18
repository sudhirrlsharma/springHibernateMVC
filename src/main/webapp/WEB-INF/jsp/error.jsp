<%@ include file="/WEB-INF/jsp/include.jsp"%>
	<head>
		<title><h1>This is error page</h1></title>
	</head>
	<body>
		<table width="60%" align="center" border="0" cellpadding="3"
			cellspacing="1" bgcolor="#FFFF88">
			<tr bgcolor="#FFFF88">
				<td>
					Error message
				</td>
				<td>
					<c:out value="${errorMessage}" />
				</td>
			</tr>
			<tr bgcolor="#FFFF88">
				<td>
					StackTrace
				</td>
				<td>
					<c:out value="${stackTrace}" />
				</td>
			</tr>

		</table>

	</body>
</html>

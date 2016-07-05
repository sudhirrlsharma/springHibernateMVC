
<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Beer added successfully</title>
	</head>
	<body>
		<table width="60%" align="center" border="0" cellpadding="3"
			cellspacing="1" bgcolor="#FFFF88">
			<tr bgcolor="#FFFF88">
				<td>
					Brand
				</td>
				<td>
					<c:out value="${beer.brand}" />
				</td>
			</tr>
			<tr bgcolor="#FFFF88">
				<td>
					Price
				</td>
				<td>
					<c:out value="${beer.price}" />
				</td>
			</tr>

		</table>

	</body>
</html>

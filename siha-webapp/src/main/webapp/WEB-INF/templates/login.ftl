<html>
<head>
<style type="text/css">
body {
	height: 100%;
}

body>table {
	width: 100%;
	height: 100%;
}

body>table>tbody>tr>td {
	text-align: center;
}

form>table {
	margin-left: auto;
	margin-right: auto;
}

.error {
	font-weight: bold;
	color: red;
}

.login {
	width: 300px;
	top: 200px;
	position: relative;
	margin: auto auto;
}
</style>
</head>
<body>
	<div class="login">
		<fieldset>
			<h1>Bejelentkezés</h1>
			<form method="post" action="/j_spring_security_check">
				<table>
					<tr>
						<td><label for="username">Felhasználónév</label></td>
						<td><input type="text" id="username" name="j_username" required="true"/></td>
					</tr>
					<tr> 
						<td><label for="password">Jelszó</label></td>
						<td><input type="password" id="password" name="j_password"  required="true"/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><input type="submit" value="Bejelentkezés"></td>
					</tr>
				</table>
			</form>
			<#if isError?? && isError?string == "true">
			<div class="error">Hiba történt</div>
			</#if>
		</fieldset>
	</div>
</body>
</html>
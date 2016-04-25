<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Exemplo de Cadastro de Usu&#225;rios</title>
	<link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="ufrjagil.css">
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div id="mensagens">
		<c:if test="${not empty msgLevel}">
			<div id="msg" class="alert alert-${msgLevel}">
				<c:out value="${msg}" escapeXml="false"/>
			</div>
		</c:if>
	</div>
	<fieldset>
		<legend>Cadastro de novos usu&#225;rios</legend>
		<form action="./cadastroUsuario" method="post">
			<div>
				<label for="nome">Nome: </label>
				<input
					type="text"
					id="nome"
					name="nome"
					value="${nome}"/>
			</div>
			<div>
				<label for="endereco">Endere&#231;o: </label>
				<input
					type="text"
					id="endereco"
					name="endereco"
					value="${endereco}"/>
			</div>
			<div>
				<label for="dataDeNascimento">Data de Nascimento: </label>
				<input
					type="text"
					id="dataDeNascimento"
					name="dataDeNascimento"
					maxlength="10"
					class="dt"
					value="${dataDeNascimento}"/>
			</div>
			<div>
				<input type="submit" id="btnCadastrar" name="btnCadastrar">
			</div>
		</form>
	</fieldset>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>JMCLocadora - Atores</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/ator">JMCLocadora</a>
    </div>
</nav>

<div class="container">

    <h1 class="mb-4">Cadastro de Ator</h1>

    <!-- Mensagem -->
    <c:if test="${not empty msg}">
        <div class="alert alert-info">${msg}</div>
    </c:if>

    <!-- Formulário de cadastro/edição -->
    <div class="card mb-4">
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/ator" method="post" class="row g-3">
                <input type="hidden" id="id" name="id" value="${ator != null ? ator.id : ''}">
                <div class="col-md-8">
                    <label for="nome" class="form-label">Nome</label>
                    <input type="text" id="nome" name="txt_nome" value="${ator != null ? ator.nome : ''}" class="form-control" required>
                </div>
                <div class="col-md-4 d-flex align-items-end">
                    <button type="submit" class="btn btn-primary w-100">
                        <c:choose>
                            <c:when test="${ator != null && ator.id != null}">Atualizar</c:when>
                            <c:otherwise>Cadastrar</c:otherwise>
                        </c:choose>
                    </button>
                </div>
            </form>
        </div>
    </div>

    <br>

    <!-- Tabela com atores -->
    <h2 class="mb-3">Lista de Atores</h2>
    <div class="card">
        <div class="card-body p-0">
            <table class="table table-striped mb-0">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th class="text-center">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="a" items="${listaAtores}">
                        <tr>
                            <td>${a.id}</td>
                            <td>${a.nome}</td>
                            <td class="text-center">
                                <c:url var="editUrl" value="/ator">
                                    <c:param name="action" value="edit"/>
                                    <c:param name="id" value="${a.id}"/>
                                </c:url>
                                <c:url var="delUrl" value="/ator">
                                    <c:param name="action" value="delete"/>
                                    <c:param name="id" value="${a.id}"/>
                                </c:url>

                                <a href="${editUrl}" class="btn btn-sm btn-warning">Editar</a>
                                <a href="${delUrl}" class="btn btn-sm btn-danger"
                                   onclick="return confirm('Deseja realmente excluir este ator?');">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>

                    <c:if test="${empty listaAtores}">
                        <tr>
                            <td colspan="3" class="text-center text-muted">Nenhum ator cadastrado</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>

</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta lang="en">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<script src="/resources/js/openperiodical.js"></script>
<%@ include file="/WEB-INF/pages/header.jspf" %>

<%--IMPORTANT, CSRF IS REQUIRED FOR PUT AND DELETE, THIS IS BASIC SPRING SECURITY--%>
<input type="hidden" id="csrfToken" value="${_csrf.token}"/>
<input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>

<div style="padding-top: 51px;" class="container-fluid">
    <div class="col-md-offset-3 col-md-6">
        <h1>Welcome to Open Periodical</h1>

        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <td>Id</td>
                        <td>Title</td>
                        <td></td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="newspaper" items="${newspapers}">
                        <tr>
                            <td>${newspaper.id}</td>
                            <td>${newspaper.name}</td>
                            <td class="text-center">
                                <a class="btn btn-default" onclick="deleteNewspaper('${newspaper.id}')">
                                    <span class="glyphicon glyphicon-trash"></span>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <form action="/Create" method="post">
            <div class="form-group">
                <label for="title">Title:</label>
                <input class="form-control" name="title" id="title">
            </div>
            <div class="form-group">
                <input class="btn btn-primary" type="submit" value="Create">
            </div>

        </form>
    </div>
</div>
</body>
</html>
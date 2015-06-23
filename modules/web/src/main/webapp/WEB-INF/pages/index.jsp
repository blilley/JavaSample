<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta lang="en">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

<%@ include file="/WEB-INF/pages/header.jspf" %>
<div style="padding-top: 51px;" class="container-fluid">
    <div class="col-md-offset-3 col-md-6">
        <h1>Welcome to Open Periodical</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <td>Id</td>
                    <td>Title</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="newspaper" items="${newspapers}">
                    <tr>
                        <td>${newspaper.id}</td>
                        <td>
                            <span class="col-md-11">${newspaper.name}</span>
                            <a class="btn btn-default col-md-1" href="<c:url value="/Delete/${newspaper.id}" />">
                                <span class="glyphicon glyphicon-trash"></span>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

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
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Open Periodical - Create Newspaper</title>
  <meta lang="en">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"/>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/pages/header.jspf" %>

<%--IMPORTANT, CSRF IS REQUIRED FOR PUT AND DELETE, THIS IS BASIC SPRING SECURITY--%>
<input type="hidden" id="csrfToken" value="${_csrf.token}"/>
<input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>

<div style="padding-top: 51px;" class="container-fluid">
  <div class="col-md-offset-3 col-md-6">
    <h1>Welcome to Open Periodical</h1>

    <form action="<c:url value="/Create"/>" method="post">
      <div class="form-group">
        <label for="title">Title:</label>
        <input class="form-control" name="title" id="title">
      </div>
        <div class="form-group">
        <input id="createNewspaper" name="createNewspaper" class="btn btn-primary" type="submit" value="Create">
      </div>
    </form>
  </div>
</div>
</body>
</html>
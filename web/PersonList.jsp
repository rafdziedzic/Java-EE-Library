<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even){background-color: #f2f2f2}

        th {
            background-color: #4CAF50;
            color: white;
        }
    </style>
    <title>Persons Application</title>
</head>
<body>
<center>
    <h1>Persons Management</h1>
    <h2>
        <a href="new">Add New Person</a>
        &nbsp;&nbsp;&nbsp;
        <a href="list">List All Persons</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Persons</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="person" items="${listPerson}">
            <tr>
                <td><c:out value="${person.id}" /></td>
                <td><c:out value="${person.name}" /></td>
                <td><c:out value="${person.surname}" /></td>
                <td>
                    <a href="edit?id=<c:out value='${person.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="delete?id=<c:out value='${person.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 03/01/2026
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Title</title>
</head>
<body>
<h3>Registration</h3>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="username">Username:</label>
    <input type="text" name="username" id="username"><br>
    <label for="birthday">Birthday:</label>
    <input type="date" name="birthday" id="birthday"><br>
    <label for="email">Email:</label>
    <input type="email" name="email" id="email"><br>
    <label for="password">Password:</label>
    <input type="password" name="password" id="password"><br><br>


    <label for="role">Select user role:</label>
    <select name="role" id="role">
        <option value="admin">Admin</option>
        <option value="user">User</option>
    </select><br>

    <label>Select your gender:<br>
        <input type="radio" name="gender" value="male">male<br>
        <input type="radio" name="gender" value="female">female
    </label><br>


    <button type="submit">Submit</button>


</form>

</body>
</html>

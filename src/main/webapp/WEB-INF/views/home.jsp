<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<form name="Register" action="Register" method="POST" >
    <ul>
        <li><label>User:</label> <input type='text' name='Username' /></li>
        <li><label>email:</label> <input type='text' name='Email' /></li>
        <li><label>pass:</label> <input type='text' name='password' /></li>
        <li><label>phone:</label> <input type='text' name='Phone' /></li>
        <li><label>address:</label> <input type='text' name='Address' /></li>
        <li><label>&nbsp;</label> <input type="submit" value="OK" class="btn"></li>
    </ul>
</form>

</body>
</html>

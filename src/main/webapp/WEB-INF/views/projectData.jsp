<%@page import="javax.print.attribute.standard.Severity"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Spring MVC Interceptor example</title>
</head>
<body>
	<h3> you Submitted the following:</h3>
	<br>
	<%
		String p_name = request.getParameter("name");
		String description = request.getParameter("description");
		String location = request.getParameter("location");
		String campaignOrProject = request.getParameter("type");
		String object = request.getParameter("isObject");
		String volnteer = request.getParameter("isVolunteer");
		String money = request.getParameter("isDonation");
		String urg = request.getParameter("severity");
		String date = request.getParameter("deadline");
		String health = request.getParameter("health");
		String education = request.getParameter("education");
		String orphans = request.getParameter("orphans");
		String development = request.getParameter("development");
		String slums = request.getParameter("slums");
		String economy = request.getParameter("economy");
	%>
	<table>

		<tbody>
			<tr>
				<td>name</td>
				<td>:</td>
				<td><%=p_name%></td>
			</tr>
			<tr>
				<td>Campaign/Case</td>
				<td>:</td>
				<td><%=campaignOrProject%></td>
			</tr>
			<tr>
				<td>description</td>
				<td>:</td>
				<td><%=description%></td>
			</tr>
			<tr>
				<td>location</td>
				<td>:</td>
				<td><%=location%></td>
			</tr>
			<tr>
				<td>isObject</td>
				<td>:</td>
				<td><%=object%></td>
			</tr>
			<tr>
				<td>isvolunteer</td>
				<td>:</td>
				<td><%=volnteer%></td>
			</tr>
			<tr>
				<td>isMoney</td>
				<td>:</td>
				<td><%=money%></td>
			</tr>
			<tr>
				<td>urgency_id</td>
				<td>:</td>
				<td><%=urg%></td>
			</tr>
			<tr>
				<td>c_health</td>
				<td>:</td>
				<td><%=health%></td>
			</tr>
			<tr>
				<td>c_orphans</td>
				<td>:</td>
				<td><%=orphans%></td>
			</tr>
			<tr>
				<td>c_development</td>
				<td>:</td>
				<td><%=development%></td>
			</tr>
			<tr>
				<td>c_education</td>
				<td>:</td>
				<td><%=education%></td>
			</tr>
			<tr>
				<td>c_slums</td>
				<td>:</td>
				<td><%=slums%></td>
			</tr>
			<tr>
				<td>c_economy</td>
				<td>:</td>
				<td><%=economy%></td>
			</tr>
			
		</tbody>
	</table>
</body>
</html>
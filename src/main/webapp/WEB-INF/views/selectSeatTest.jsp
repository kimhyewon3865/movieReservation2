<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <p> movieId: <%= request.getParameter("movieId")%></p>
 	<p> theater id : <%= request.getParameter("theaterId")%> </p>
 	<p> scheduleId : <%= request.getParameter("scheduleId")%> </p> --%>
 	
 	<p> schedule Id: ${schedule} </p>
 	
 	<form action="addReservation" method="post">
 		 <ul> 
 			  <input type="hidden" name="scheduleId" value="${schedule}"> 
 			 <input type="hidden" name="userId" value="abc">  
 			
 			 <li>
 				<input id="1" name="seatIds" type="checkbox" value="1"/>
				<label for="1">1</label>
 			</li>
 			
 			<li>
 				<input id="2" name="seatIds" type="checkbox" value="2"/>
				<label for="2">2</label>
 			</li> 
 		 </ul>		
 		<input type="submit" value="Submit"/>
 	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
      <c:forEach var="reservation" items="${listReservation}" varStatus="status">
      		<tr>
            <td>
            ${reservation.id}
           	</td>
           	<td> <input type="button" value="취소하기" class="payBtn" onclick="cancelBtnClick(${reservation.id})"/></td>
      		</tr>
        </c:forEach>
    </table>
    <script>
    
   	 function cancelBtnClick(reservationId) {
   		alert(reservationId);
        window.location = "http://localhost:8080/movieReservation/cancelReservation?reservationId=" + reservationId;
   	 }

    </script>
</body>
</html>
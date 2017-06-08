<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/addReservation.css" />" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
<div class="nav">
    <ul>
        <li class="homeLeftFloat">
            <a href="#">hyewon's Movie Reservation</a>
        </li>
        <li>
            <a href="#" class="dropbtn" onclick="">logout</a>
        </li>
        <li>
            <a href="#" class="dropbtn" onclick="">상영중인 영화</a>
        </li>
        <li>
	       	<a href="reservationHistoryCancel" class="dropbtn" onclick="">예약내역/취소</a>
	    </li>
	   	<li>
	       	<a href="selectTest" onclick="">예약</a>
	    </li>
    </ul>
</div>

<h3>예약이 완료되었습니다.</h3>

<table style="width: 80%;" align="center">
    <tr>
        <th>영화</th>
        <th>극장</th>
        <th>날짜</th>
        <th>룸</th>
        <th>시간</th>
        <th>좌석</th>
        <th>가격</th>
        <th>대기/예약</th>
    </tr>
	 <c:forEach var="reservationView" items="${listReservationView}" varStatus="status">
		<tr>
		    <td>${reservationView.movieName}</td>
            <td>${reservationView.theaterName}</td>
            <td>${reservationView.date}</td>
            <td>${reservationView.roomId}</td>
            <td><span>${reservationView.startTime}~</span><span>${reservation.endTime}</span></td>
            <td>${reservationView.seatId}</td>
            <td>${reservationView.price}</td>
            <td>${reservationView.waitOrder}</td>
      	</tr>
    </c:forEach> 
	<%-- <% int[] seatIds = (int[])request.getAttribute("seatIds");
            for(int seatId: seatIds) { %>
             <tr>
		        <td>${movieName}</td>
		        <td>${theaterName }</td>
		        <td>${selectedSchedule.date}</td>
		        <td>${selectedSchedule.roomId}</td>
		        <td>${selectedSchedule.startTime}</td>
		        <td><%=seatId%></td>
		        <td>10000</td>
		        <td>예약</td>
		    </tr>
            <% } %>  --%>
</table>




<%--  <p> scheduleId: ${scheduleId }</p>
<p> seats : <% int[] seatIds = (int[])request.getAttribute("seatIds");
            for(int seatId: seatIds) {
               out.println(seatId);
            }
         %>
         </p> --%>
         
         
         
         
         
</body>
</html>
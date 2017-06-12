<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/navigation.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/checkReservationForManager.css"/>" rel="stylesheet">
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
            <a href="checkReservationForManager" class="dropbtn" onclick="">예약상황 </a>
        </li>
    </ul>
</div>

<div class="lists">
			<ul class="movie-list">
				<li>
                    <label>영화</label>
                    <select id="movie-select" name="movie-select">
                    	<option selected="selected" value="0">영화 </option>
						<c:forEach var="movie" items="${listMovie}" varStatus="status">
		            		<option value="movie${movie.id}">${movie.name}</option>
		        		</c:forEach>
	        		</select>
                </li>
            </ul>
            
            <ul class="theater-list">
                <li>
                    <label>극장 </label>
                    <select id="theater-select" name="theater-select">
                        <option selected="selected" value="0">극장 </option>
                        <c:forEach var="theater" items="${listTheater}" varStatus="status">
		            		<option value="theater${theater.id}">${theater.name}</option>
		        		</c:forEach>
                    </select>
                </li>
            </ul>
            
            <ul class="date-list">
                <li>
                    <label>날짜 </label>
                    <select id="schedule-select" name="schedule-select">
                        <option selected="selected" value="0">날짜 </option>
                       <c:forEach var="schedule" items="${listSchedule}" varStatus="status">
		            		<option value="schedule${schedule.id}">${schedule.date}</option>
		        		</c:forEach>
                    </select>
                </li>
            </ul>
            
            <button class="searchButton" onclick="searchButtonClick()">검색 </button>
</div>
<div calss="reservation-table">
<table style="width: 80%;" align="center">
    <tr class="table-header">
        <th>영화</th>
        <th>극장</th>
        <th>날짜</th>
        <th>룸</th>
        <th>시간</th>
        <th>좌석</th>
        <th>가격</th>
        <th>대기번호 </th>
    </tr>

	<c:forEach var="reservationView" items="${listReservationViews}" varStatus="status">
      	<tr class="reservation-list">
            <%-- <td>${reservationView.movieName}</td> --%>
            <td>${reservationView.reservationId}</td>
            <td>${reservationView.theaterName}</td>
            <td>${reservationView.date}</td>
            <td>${reservationView.roomId}</td>
            <td><span>${reservationView.startTime}~</span><span>${reservation.endTime}</span></td>
            <td>${reservationView.seatId}</td>
            <td>${reservationView.price}</td>
            <td>${reservationView.waitOrder}</td>
      	</tr>
    </c:forEach>
</table>
</div>
<script>
	function searchButtonClick() {
		var e1 = document.getElementById("movie-select");
		var selectedMovieValue = e1.options[e1.selectedIndex].value;
		
		var e2 = document.getElementById("theater-select");
		var selectedMovieValue = e2.options[e2.selectedIndex].value;
		
		var e3 = document.getElementById("schedule-select");
		var selectedMovieValue = e3.options[e3.selectedIndex].value;
	}
</script>
</body>
</html>
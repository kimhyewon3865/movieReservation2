<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/navigation.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/checkReservationForManager.css"/>" rel="stylesheet">
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/nicescroll/3.5.4/jquery.nicescroll.js'></script>
    <script src="http://ajax.microsoft.com/ajax/jquery.templates/beta1/jquery.tmpl.min.js"></script>
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
		            		<option value="${movie.id}">${movie.name}</option>
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
		            		<option value="${theater.id}">${theater.name}</option>
		        		</c:forEach>
                    </select>
                </li>
            </ul>
            
            <ul class="date-list">
                <li>
                    <label>날짜 </label>
                    <select id="date-select" name="date-select">
                        <option selected="selected" value="0">날짜 </option>
                       <c:forEach var="schedule" items="${listSchedule}" varStatus="status">
		            		<option value="${schedule.id}">${schedule.date}</option>
		        		</c:forEach>
                    </select>
                </li>
            </ul>
            
            <button class="searchButton" onclick="searchButtonClick()" id="searchButton">검색 </button>
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
		var selectedTheaterValue = e2.options[e2.selectedIndex].value;
		
		var e3 = document.getElementById("date-select");
		var selectedDateValue = e3.options[e3.selectedIndex].value;		
		var selectedDateText = e3.options[e3.selectedIndex].text;
		
		alert("m: " + selectedMovieValue + " t: " + selectedTheaterValue + " s: " + selectedDateValue + " s2: " + selectedDateText);
		
		$("#searchButton").click(function () {
			/* $(".movieLi").removeAttr("style");
            $(this).css("background-color", "lightblue"); */
            
             $.ajax({
                url : "http://localhost:8080/movieReservation/searchByManager.do",
                type: "get",
                data : { "movieId" : selectedMovieValue, "theaterId" : selectedTheaterValue, "dateId" : selectedDateValue, "date" : selectedDateText },
                success : function(responseData){
           	     	var reservationViews = responseData.reservationViews;
           	     	
                    $("#ajax").remove();
                    $(".reservation-list").remove(); //필수
                    
                    var html = '';
                    for( i in reservationViews) {
                    	//TODO: 한글 깨짐
                    	html += '<tr class="reservation-list">';
            					<%-- <td>${reservationView.movieName}</td> --%>
			            html += '<td>' + reservationViews[i].reservationId + '</td>';
			            html += '<td>' + reservationViews[i].theaterName + '</td>';
			            html += '<td>' + reservationViews[i].date + '</td>';
			            html += '<td>' + reservationViews[i].roomId + '</td>';
			            html += '<td><span>' + reservationViews[i].startTime + '~</span><span>' + reservationViews[i].endTime + '</span></td>';
			            html += '<td>' + reservationViews[i].seatId + '</td>';
			            html += '<td>' + reservationViews[i].price + '</td>';
			            html += '<td>' + reservationViews[i].waitOrder + '</td>';
			            html += '</tr>'; 
                    }
                    
                    
                    $(".table-header").after(html);
                }
            });
            
        });
		
	}
</script>
</body>
</html>
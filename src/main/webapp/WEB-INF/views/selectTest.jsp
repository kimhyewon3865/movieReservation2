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
<!-- <form action="selectSeatTest" method="post"> -->
<h3>영화 </h3>
     <ul>

      <c:forEach var="movie" items="${listMovie}" varStatus="status">
            <li style="display: list-item;" onclick="setSelectedMovie(${status.index})">
            <label><input type="radio" 
					              name="movieResponse[${status.index}]" value="${movie.name}" >
						${movie.name}</label>
           	</li>
        </c:forEach>
    </ul>
 
     <h3>영화관 </h3>
      <ul>
      <c:forEach var="theater" items="${ listTheater }" varStatus="status">
            <li onclick="setSelectedTeahterId(${status.index})">
            	<input type="radio" name="theaterId" id="theater1" onclick="">
                <label for="theater1"> ${theater.id}</label>
                
           	</li>
        </c:forEach>
    </ul>
    
    <%-- <h3>시간표- date </h3>
    <ul>
    	<c:forEach var="schedule" items="${ listSchedule }" varStatus="status">
            <li>
            	<input type="radio" name="schedule_Date" id="schedule_Date1" onclick="">
                <label for="schedule_Date1"> ${schedule.date}</label>
           	</li>
        </c:forEach>
    </ul>  --%>
    
    <h3>시간표- room, time </h3>
    <ul>
    	<c:forEach var="schedule" items="${ listSchedule }" varStatus="status">
            <li onclick="setSelectedScheduleId(${status.index})">
            	<input type="radio" name="scheduleId" id="schedule_Room1" onclick="">
                <label for="schedule_Room1"> ${schedule.id}
                	<span>${schedule.date}</span>
                	<span> ${ schedule.roomId}</span>
                </label>
           	</li>
        </c:forEach>
    </ul> 
	<!-- <input type="submit" value="주문완료"> -->
    
	<!-- </form> -->
	<input type="button" value="조회하기" class="searchSeatBtn" onclick="selectSeatClick()"/>
	
	<script>
	var selectedMovieId = 0;
    var selectedTheaterId = 0;
    /* var selectedDate = "";
    var selectedRoomId = 0;
    var selectedTime = 0; */
	var selectedScheduleId = 0;
    
    function setSelectedMovie(movieId) {
        selectedMovieId = movieId;
    }

    function setSelectedTeahterId(theaterId) {
        selectedTheaterId = theaterId;
    }

/*     function setSelectedDate(date) {
        selectedDate = date;
    } */

   /*  function setSelectedRoomIdTime(roomId, time) {
        selectedRoomId = roomId;
        selectedTime = time;
    } */
    
    function setSelectedScheduleId(scheduleId) {
    	selectedScheduleId = scheduleId;
    }

    function selectSeatClick() {
       /*  window.location = "http://localhost:8080/selectSeat?movie=" + selectedMovieId + "&theater=" + selectedTheaterId + "&date=" + selectedDate
        + "&room=" + selectedRoomId + "&time=" + selectedTime ; */
    	 window.location = "http://localhost:8080/movieReservation/selectSeatTest?movie=" + selectedMovieId + "&theater=" + selectedTheaterId + "&schedule=" + selectedScheduleId;
    }
	</script>
</body>
</html>
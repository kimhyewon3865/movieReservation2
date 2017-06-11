<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/selectSchedule.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/navigation.css" />" rel="stylesheet">
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
	            <a href="reservationHistoryCancel" class="dropbtn" onclick="">예약내역/취소</a>
	        </li>
	        <li>
	            <a href="selectTest" onclick="">예약</a>
	        </li>
	    </ul>
	</div>
 	
 	<br/>

	<div class="list-div">
	<h3>영화 </h3>
	
     <ul id="movie-list">
      <c:forEach var="movie" items="${listMovie}" varStatus="status">
            <li class="movieLi" onclick="setSelectedMovie(${movie.id})" id="movie${movie.id}">
            <label><input type="radio" 
					              name="movieResponse" value="${movie.name}" >
						${movie.name}</label>
           	</li>
        </c:forEach>
    </ul>
    </div>
 
 	<div class="list-div">
     <h3>영화관 </h3>
      <ul id="theater-list">
      <c:forEach var="theater" items="${ listTheater }" varStatus="status">
            <li class = "theaterLi" onclick="setSelectedTeahterId(${theater.id})">
            	<input type="radio" name="theaterId" id="theater1" onclick="">
                <label for="theater1"> ${theater.id}</label>
                
           	</li>
        </c:forEach>
    </ul>
    </div>
    
    <div class="list-div">
    <h3>날짜 - date </h3>
    <ul id="date-list">
    	<c:forEach var="schedule" items="${ listSchedule }" varStatus="status">
            <li class="dateLi" onclick="setSelectedScheduleId(${status.index})">
            	<input type="radio" name="scheduleId" id="schedule_Room1" onclick="">
                <label for="schedule_Room1"> ${schedule.id}
                	<span>${schedule.date}</span>
                	<span> ${ schedule.roomId}</span>
                </label>
           	</li>
        </c:forEach>
    </ul> 
    </div>
    
    <div class="list-div">
    <h3>시간표- room, time </h3>
    <ul id="schedule-list">
    	<c:forEach var="schedule" items="${ listSchedule }" varStatus="status">
            <li class="scheduleLi" onclick="setSelectedScheduleId(${status.index})">
            	<input type="radio" name="scheduleId" id="schedule_Room1" onclick="">
                <label for="schedule_Room1"> ${schedule.id}
                	<span>${schedule.date}</span>
                	<span> ${ schedule.roomId}</span>
                </label>
           	</li>
        </c:forEach>
    </ul> 
    </div>
    
	<input type="button" value="조회하기" class="searchSeatBtn" onclick="selectSeatClick()"/>
	
	<script>	
	var selectedMovieId = 0;
    var selectedTheaterId = 0;
    var selectedDate = "";
	var selectedScheduleId = 0;
    
    function setSelectedMovie(movieId) {
        selectedMovieId = movieId;
        
       	$("#movie"+movieId).click(function () {
        	$(".movieLi").removeAttr("style");
            $(this).css("background-color", "lightblue");
            
            $.ajax({
                url : "http://localhost:8080/movieReservation/selectMovie.do",
                type: "get",
                data : { "movieId" : movieId },
                success : function(responseData){
           	     	var theaters = responseData.theaters;
           	     	
                    $("#ajax").remove();
                    $(".theaterLi").remove(); //필수
                   	
                    var html = '';
                    for( i in theaters) {
                    	//TODO: 한글 깨짐
                    	html += '<li class = "theaterLi" onclick="setSelectedTeahterId(' + theaters[i].id + ')" id="theater' + theaters[i].id + '">';
                   		html += '<label><input type="radio" name="theaterId" value="' + theaters[i].name + '" >'+ theaters[i].name +'</label>';
                		html += '</li>'; 
                    }
                    $("#theater-list").after(html);
                }
            }); 
        });
    }
    
    
    function setSelectedTeahterId(theaterId) {
        selectedTheaterId = theaterId;
        
        $("#theater"+theaterId).click(function () {
        	$(".theaterLi").removeAttr("style");
            $(this).css("background-color", "lightblue");
            
            $.ajax({
                url : "http://localhost:8080/movieReservation/selectTheater.do",
                type: "get",
                data : { "movieId" : selectedMovieId, "theaterId": theaterId },
                success : function(responseData){
           	     	var schedules = responseData.schedules;
           	     	
                    $("#ajax").remove();
                    $(".dateLi").remove(); //필수
                   	
                    var html = '';
                    for( i in schedules) {
                    	//TODO: 한글 깨짐
                    	html += '<li class = "dateLi" onclick="setSelectedDate(\'' + schedules[i].date + '\', ' + i + ')" id="date' + i + '">';
                   		html += '<label><input type="radio" name ="dateId" value="\'' + schedules[i].date + '\'" >'+ schedules[i].date +'</label>';
                		html += '</li>'; 
                    }
                    $("#date-list").after(html);
                }
            }); 
        });
        
    }
    
    function setSelectedDate(date, i) {
    	selectedDate = date;
    	
    	$("#date"+i).click(function () {
        	$(".dateLi").removeAttr("style");
            $(this).css("background-color", "lightblue");
            
            $.ajax({
                url : "http://localhost:8080/movieReservation/selectDate.do",
                type: "get",
                data : { "movieId" : selectedMovieId, "theaterId": selectedTheaterId, "date" : date },
                success : function(responseData){
           	     	var schedules = responseData.schedules;
           	     		
                    $("#ajax").remove();
                    
                    $(".scheduleLi").remove(); //필수
                    
                    var html = '';
                    for( i in schedules) {
                    	//TODO: 한글 깨짐
                    	html += '<li class = "scheduleLi" onclick="setSelectedSchedule(' + schedules[i].id + ')" id="schedule' + schedules[i].id + '">';
                   		html += '<label><input type="radio" name="scheduleId" value="' + schedules[i].id + '" >'+ schedules[i].id +'</label>';
                		html += '</li>'; 
                    }
                    $("#schedule-list").after(html);
                }
            }); 
        });
    	
    	
    }
    
    function setSelectedSchedule(scheduleId) {
    	selectedScheduleId = scheduleId;
    	$("#schedule"+scheduleId).click(function () {
        	$(".scheduleLi").removeAttr("style");
            $(this).css("background-color", "lightblue");
    	});
    }

    function selectSeatClick() {
    	 window.location = "http://localhost:8080/movieReservation/selectSeatTest?movie=" + selectedMovieId + "&theater=" + selectedTheaterId + "&schedule=" + selectedScheduleId;
    }
    
    
 
    	</script>
</body>
</html>
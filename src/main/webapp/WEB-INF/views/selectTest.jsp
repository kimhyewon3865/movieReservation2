<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/nicescroll/3.5.4/jquery.nicescroll.js'></script>
    <script src="http://ajax.microsoft.com/ajax/jquery.templates/beta1/jquery.tmpl.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<!-- <form action="selectSeatTest" method="post"> -->
<%--  <h3>좌석대기테스트  </h3>
     <ul>

      <c:forEach var="seatWaitOrder" items="${seatWaitOrderList}" varStatus="status">
            <li>
            <label><input type="radio" 
					              name="seatWaitOrder" value="${seatWaitOrder}" >
						${seatWaitOrder}</label>
           	</li>
        </c:forEach>
    </ul>  --%>
<h3>영화 </h3>
     <ul>
      <c:forEach var="movie" items="${listMovie}" varStatus="status">
            <li onclick="setSelectedMovie(${movie.id})" id="movie${movie.id}">
            <label><input type="radio" 
					              name="movieResponse" value="${movie.name}" >
						${movie.name}</label>
           	</li>
        </c:forEach>
    </ul>
 
     <h3>영화관 </h3>
      <ul id="theater-list">
      <c:forEach var="theater" items="${ listTheater }" varStatus="status">
            <li class = "theaterLi" onclick="setSelectedTeahterId(${theater.id})">
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
    
    <h3>시간표- room, time </h3>
    <ul id="time-list">
    	<c:forEach var="schedule" items="${ listSchedule }" varStatus="status">
            <li class="timeLi" onclick="setSelectedScheduleId(${status.index})">
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
        
       	$("#movie"+movieId).click(function () {
        	/* $("#movie"+movieId).removeAttr("style"); */
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
                    	html += '<li class = "theaterLi" onclick="setSelectedTeahterId(' + theaters[i].id + ') id=theater' + theaters[i].id + '">';
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
        
        
        
    }

/*     function setSelectedDate(date) {
        selectedDate = date;
    } */

   /*  function setSelectedRoomIdTime(roomId, time) {
        selectedRoomId = roomId;
        selectedTime = time;
    } */
    function setSelectedDate(date) {
    	
    }
    
    function setSelectedScheduleId(scheduleId) {
    	selectedScheduleId = scheduleId;
    }

    function selectSeatClick() {
       /*  window.location = "http://localhost:8080/selectSeat?movie=" + selectedMovieId + "&theater=" + selectedTheaterId + "&date=" + selectedDate
        + "&room=" + selectedRoomId + "&time=" + selectedTime ; */
    	 window.location = "http://localhost:8080/movieReservation/selectSeatTest?movie=" + selectedMovieId + "&theater=" + selectedTheaterId + "&schedule=" + selectedScheduleId;
    }
    
    /* $("#movie").bind("click",function() {
    	alert("hi");
        $.ajax({
            url : "movieReservation/selectTest2",
            type: "get",
            data : { "movieId" : selectedMovieId },
            success : function(data) {
                $("#ajax").remove();
                alert(data);
            },
            error : function(request, status, error) {
                if (request.status != '0') {
                 alert("code : " + request.status + "\r\nmessage : "
                   + request.reponseText + "\r\nerror : " + error);
                }
               }
        });
    }) */
    
    
    function test(movieId) {
    	/* $("#movie"+movieId).bind("click",function(){
            $.ajax({
                url : "movieReservation/selectTest2",
                type: "get",
                data : { "movieId" : movieId },
                success : function(data){
                    $("#ajax").remove();
                    alert(data);
                    /* if(!data){
                        alert("존재하지 않는 ID입니다");
                        return false;
                    } */
                    /* var html = '';
                    html += '<form class="form-signin" action="" id="ajax">';
                    html += '이름<input type="text" class="form-control"  name="name" value="'+data.name+'">';
                    html += '아이디<input type="text" class="form-control" name=id" value="'+data.id+'">';
                    html += '이메일<input type="text" class="form-control"  name="email" value="'+data.email+'">';
                    html += '비밀번호<input type="text" class="form-control" name="password" value="'+data.password+'">';
                    html += '</form>';
                    $("#container").after(html);
                }
                error : function(request, status, error) {
                    if (request.status != '0') {
                     alert("code : " + request.status + "\r\nmessage : "
                       + request.reponseText + "\r\nerror : " + error);
                    }
                   }

                
            });

        }); */
    }
    
    
	</script>
</body>
</html>
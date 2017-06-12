<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/navigation.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/reservationHistoryCancel.css" />" rel="stylesheet">
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/nicescroll/3.5.4/jquery.nicescroll.js'></script>
    <script src="http://ajax.microsoft.com/ajax/jquery.templates/beta1/jquery.tmpl.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<%-- 	<table>
      <c:forEach var="reservation" items="${listReservation}" varStatus="status">
      		<tr>
            <td>
            ${reservation.id}
           	</td>
           	<td> <input type="button" value="취소하기" class="payBtn" onclick="cancelBtnClick(${reservation.id})"/></td>
      		</tr>
        </c:forEach>
    </table> --%>
    
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
            <a href="cancelReservation" class="dropbtn" onclick="">예약내역/취소</a>
        </li>
        <li>
            <a href="selectMovieTheaterDateTime" onclick="">예약</a>
        </li>
    </ul>
</div>
<br/><br/>

<table style="width: 80%;" align="center">
    <tr class="table-header">
        <th>영화</th>
        <th>극장</th>
        <th>날짜</th>
        <th>룸</th>
        <th>시간</th>
        <th>좌석</th>
        <th>가격</th>
        <th>대기/예약</th>
        <th>취소</th>
    </tr>

	<c:forEach var="reservationView" items="${listReservationView}" varStatus="status">
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

           	<td> <input type="button" id="cancelButton${reservationView.reservationId}" class="payBtn" onclick="cancelBtnClick(${reservationView.reservationId})" value="취소하기${reservationView.reservationId}"></td>
      		</tr>
    </c:forEach>
</table>
    
    <script>
   	 function cancelBtnClick(reservationId) {
   		alert(reservationId);
        //window.location = "http://localhost:8080/movieReservation/cancelReservation?reservationId=" + reservationId;
        alert(event.srcElement.id);
        
        $("#"+event.srcElement.id).click(function(){
        	$.ajax({
                url : "http://localhost:8080/movieReservation/cancelReservation.do",
                type: "get",
                data : { "reservationId" : reservationId },
                success : function(responseData){
           	     	var reservationViews = responseData.reservationViews;
           	     	
                    $("#ajax").remove();
                    $(".reservation-list").remove(); //필수
                    
                    var html = '';
                    for( i in reservationViews) {
                    	//TODO: 한글 깨짐
                    	html += '<tr class="reservation-list">';
            					<%-- <td>${reservationView.movieName}</td> --%>
			            html += '<td>' + reservationViews[i].movieName + '</td>';
			            html += '<td>' + reservationViews[i].theaterName + '</td>';
			            html += '<td>' + reservationViews[i].date + '</td>';
			            html += '<td>' + reservationViews[i].roomId + '</td>';
			            html += '<td><span>' + reservationViews[i].startTime + '~</span><span>' + reservationViews[i].endTime + '</span></td>';
			            html += '<td>' + reservationViews[i].seatId + '</td>';
			            html += '<td>' + reservationViews[i].price + '</td>';
			            html += '<td>' + reservationViews[i].waitOrder + '</td>';
			            html += '<td> <input type="button" id="cancelButton' + reservationViews[i].reservationId + '" class="payBtn" onclick="cancelBtnClick(' + reservationViews[i].reservationId + ')" value="취소하기' + reservationViews[i].reservationId + '"></td>';
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
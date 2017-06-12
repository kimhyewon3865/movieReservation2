<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- <link rel="stylesheet" href="/resources/css/selectSeat.css"> -->
    <link href="<c:url value="/resources/css/selectSeat.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/navigation.css"/>" rel="stylesheet">
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
<div class="content">
     <div class="reservation-information">
         <div class="seatcount">
            <ul>
                <li>
                    <label>일반</label>
                    <select id="seatCount" name="seatCount">
                        <option selected="selected" value="0">일반 0명</option>
                        <option value="1">일반 1명</option>
                        <option value="2">일반 2명</option>
                        <option value="3">일반 3명</option>
                        <option value="4">일반 4명</option>
                        <option value="5">일반 5명</option>
                        <option value="6">일반 6명</option>
                        <option value="8">일반 8명</option>
                        <option value="7">일반 7명</option>
                        <option value="9">일반 9명</option>
                    </select>
                </li>
            </ul>
            <p>총가격 :</p>
        </div>

        <div class="schedule">
             <!-- <div id="user-select-info"> -->
                <p>
                    <span class="site">영화 : ${movieName}</span>
                    <br/>
                    <span class="screen">극장 : ${theaterName}</span>
                    <!--TODO: 남은좌석여  -->
                    <!-- 	                <span class="seatNum">남은좌석  <b class="restNum">145</b>/<b class="totalNum">158</b></span>
                     -->	            </p>
                <p><b>날짜 : ${selectedSchedule.date}</b><b class="exe"></b><br/><b>상영시간 : ${selectedSchedule.startTime}~${selectedSchedule.endTime}</b></p>
            <!-- </div>  -->
        </div>
    </div>

    <div class = "seat-information" style="height: 60%;">
 		<form action="addReservation" method="post">
            <div class="seat-table">
                 <ol>
                    <input type="hidden" name="scheduleId" value="${schedule}">
                    <input type="hidden" name="userId" value="abc">
                    <c:forEach var="waitOrder" items="${seatWaitOrderList}" varStatus="status">
                        <c:choose>
                            <c:when test="${status.index % 10 == 0}">
                                <li>
                                <ol class="seats">
                                <c:choose>
                                    <c:when test="${waitOrder == 0}">
                                        <li class="seat_able seat">
                                            <input type="checkbox" id="${status.index}" name="seatIds" value="${status.index}"/>
                                            <label for="${status.index}">${status.index}</label>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="seat_wait seat">
                                            <input type="checkbox" id="${status.index}" name="seatIds" value="${status.index}"/>
                                            <label for="${status.index}">${status.index}</label>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:when test="${status.index % 10 == 9}">
                                <c:choose>
                                    <c:when test="${waitOrder == 0}">
                                        <li class="seat_able seat">
                                            <input type="checkbox" id="${status.index}" name="seatIds" value="${status.index}"/>
                                            <label for="${status.index}">${status.index}</label>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="seat_wait seat">
                                            <input type="checkbox" id="${status.index}" name="seatIds" value="${status.index}"/>
                                            <label for="${status.index}">${status.index}</label>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                                </ol>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${waitOrder == 0}">
                                        <li class="seat_able seat">
                                            <input type="checkbox" id="${status.index}" name="seatIds" value="${status.index}"/>
                                            <label for="${status.index}">${status.index}</label>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="seat_wait seat">
                                            <input type="checkbox" id="${status.index}" name="seatIds" value="${status.index}"/>
                                            <label for="${status.index}">${status.index}</label>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ol>
            </div>
            <div class="seat-type">
                <p style="background: deepskyblue;">예약 가능</p>
                <p style="background: #bada55;">대기</p>
                <p style="background: indianred;">선택</p>
            </div>

            <input class="payButton" type="submit" value="Submit"/>
        </form>  
    </div>
    <div class="button-information" style="height: 15%;">
        <input type="button" value="상영시간 변경하기" class="backButton" onclick="backBtnClick()"/>
    </div> 
</div>

<script>
    function backBtnClick() {
        window.location = "http://localhost:8080/movieReservation/selectTest";
    }
</script>
</body>
</html>
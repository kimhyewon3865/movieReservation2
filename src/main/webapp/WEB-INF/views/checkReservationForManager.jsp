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
        <li>
            <a href="selectTest" onclick="">예약</a>
        </li>
    </ul>
</div>

<div class="lists">
			<ul class="movie-list">
                <li>
                    <label>영화</label>
                    <select id="movie" name="movie">
                        <option selected="selected" value="0">a</option>
                        <option value="1">a</option>
                        <option value="2">b</option>
                        <option value="3">c</option>
                    </select>
                </li>
            </ul>
            
            <ul class="theater-list">
                <li>
                    <label>극장 </label>
                    <select id="movie" name="movie">
                        <option selected="selected" value="0">a</option>
                        <option value="1">a</option>
                        <option value="2">b</option>
                        <option value="3">c</option>
                    </select>
                </li>
            </ul>
            
            <ul class="date-list">
                <li>
                    <label>극장 </label>
                    <select id="movie" name="movie">
                        <option selected="selected" value="0">a</option>
                        <option value="1">a</option>
                        <option value="2">b</option>
                        <option value="3">c</option>
                    </select>
                </li>
            </ul>
            <button class="searchButton">검색 </button>
</div>
<div>
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
        <th>취소</th>
    </tr>

    <tr>
        <td>캐리비안해적</td>
        <td>강남</td>
        <td>5/29</td>
        <td>3관 8층</td>
        <td>8:00</td>
        <td>1A</td>
        <td>10000</td>
        <td>예약</td>
        <td><a href="#">취소</a> </td>
    </tr>
</table>
</div>

</body>
</html>
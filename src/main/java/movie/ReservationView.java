package movie;

import java.sql.Date;

public class ReservationView {
	
	int reservationId;
	String movieName;
	String theaterName;
	String date;
	int roomId;
	String startTime;
	String endTime;
	int seatId;
	int price;
	int waitOrder;
	String userId;
	
	public ReservationView(int reservationId, String movieName, String theaterName, String date, int roomId, String startTime, String endTime,
			int seatId, int price, int waitOrder, String userId) {
		super();
		this.reservationId = reservationId;
		this.movieName = movieName;
		this.theaterName = theaterName;
		this.date = date;
		this.roomId = roomId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.seatId = seatId;
		this.price = price;
		this.waitOrder = waitOrder;
		this.userId = userId;
	}
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getTheaterName() {
		return theaterName;
	}
	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getWaitOrder() {
		return waitOrder;
	}
	public void setWaitOrder(int waitOrder) {
		this.waitOrder = waitOrder;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}

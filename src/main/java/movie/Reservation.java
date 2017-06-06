package movie;

import java.sql.Date;

public class Reservation {
  
	private Long id;
    private int scheduleId;
    private String userId;
	private int seatId;
	private int waitOrder;
	
	public Reservation(int scheduleId, String userId, Integer seatId, int waitOrder) {
		this.scheduleId = scheduleId;
		this.userId = userId;
		this.seatId = seatId;
		this.waitOrder = waitOrder;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getScheduleId() {
		return scheduleId;
	}
	
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public int getSeatId() {
		return seatId;
	}
	
	public void setSeatIds(int seatId) {
		this.seatId = seatId;
	}
	
	public int getWaitOrder() {
		return waitOrder;
	}
	
	public void setWaitOrder(int waitOrder) {
		this.waitOrder = waitOrder;
	}
}
	
	

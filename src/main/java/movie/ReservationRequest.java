package movie;

public class ReservationRequest {
	
	private int scheduleId;
	private String userId;
	private int [] seatIds;
	
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
	
	public int[] getSeatIds() {
		return seatIds;
	}
	
	public void setSeatIds(int[] seatIds) {
		this.seatIds = seatIds;
	}
}

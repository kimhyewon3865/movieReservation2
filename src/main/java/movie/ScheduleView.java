package movie;

public class ScheduleView {
	String movieName;
	String theaterName;
	String date;
	int roomId;
	String startTime;
	String endTime;
	
	public ScheduleView(String movieName, String theaterName, String date, int roomId, String startTime,
			String endTime) {
		super();
		this.movieName = movieName;
		this.theaterName = theaterName;
		this.date = date;
		this.roomId = roomId;
		this.startTime = startTime;
		this.endTime = endTime;
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
	
	
}

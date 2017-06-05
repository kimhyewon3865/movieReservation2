package movie;

import java.sql.Date;

public class Schedule {
	int id;
    int movie;
    Date date;
    String startTime;
    String endTime;
    int theaterId;
    int roomId;
    
    public Schedule(int movie, Date date, String startTime, String endTime, int theaterId, int roomId) {
    	this.movie = movie;
    	this.date = date;
    	this.startTime = startTime;
    	this.endTime = endTime;
    	this.theaterId = theaterId;
    	this.roomId = roomId;
    }
    
    public void setId(int id) {
    	this.id = id;
    }

	public int getMovie() {
		return movie;
	}

	public void setMovie(int movie) {
		this.movie = movie;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public int getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(int theaterId) {
		this.theaterId = theaterId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getId() {
		return id;
	}
    
    
}

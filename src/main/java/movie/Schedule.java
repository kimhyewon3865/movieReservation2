package movie;

import java.sql.Date;

public class Schedule {
	int id;
    int movieId;
    String date;
    String startTime;
    String endTime;
    int theaterId;
    int roomId;
    
    public Schedule(int movieId, String date, String startTime, String endTime, int theaterId, int roomId) {
    	this.movieId = movieId;
    	this.date = date;
    	this.startTime = startTime;
    	this.endTime = endTime;
    	this.theaterId = theaterId;
    	this.roomId = roomId;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
	
    public int getId() {
		return id;
	}
	
	public int getMovie() {
		return movieId;
	}

	public void setMovie(int movie) {
		this.movieId = movie;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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


    
    
}

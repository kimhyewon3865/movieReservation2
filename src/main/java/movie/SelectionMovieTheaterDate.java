package movie;

import java.sql.Date;
import java.util.List;

public class SelectionMovieTheaterDate {
	List<String> movieResponses;
//	int theaterId;
//	Date date;
//	int scheduleId;
	
	public SelectionMovieTheaterDate(List<String> movieResponses) {
		super();
		this.movieResponses = movieResponses;
//		this.theaterId = theaterId;
//		this.date = date;
//		this.scheduleId = scheduleId;
	}
	
	public List<String> getMovieResponses() {
		return movieResponses;
	}
	public void setMovieResponses(List<String> movieResponses) {
		this.movieResponses = movieResponses;
	}
//	public int getTheaterId() {
//		return theaterId;
//	}
//	public void setTheaterId(int theaterId) {
//		this.theaterId = theaterId;
//	}
//	public Date getDate() {
//		return date;
//	}
//	public void setDate(Date date) {
//		this.date = date;
//	}
//	public int getScheduleId() {
//		return scheduleId;
//	}
//	public void setScheduleId(int scheduleId) {
//		this.scheduleId = scheduleId;
//	}
}

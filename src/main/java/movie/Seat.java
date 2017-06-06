package movie;

public class Seat {
	
	int seatId;
	int theaterId;
	int roomId;
	
	public Seat(int seatId, int theaterId, int roomId) {
		super();
		this.seatId = seatId;
		this.theaterId = theaterId;
		this.roomId = roomId;
	}
	
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
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

package movie;

public class Seat {
	
	int seatId;
	int theaterId;
	int roomId;
	int price;
	
	public Seat(int seatId, int theaterId, int roomId, int price) {
		super();
		this.seatId = seatId;
		this.theaterId = theaterId;
		this.roomId = roomId;
		this.price = price;
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
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}

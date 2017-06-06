package movie;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

public class ReservationService {
	private ReservationDao reservationDao;

    public ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

//    @Transactional
//    public void reservate(ReservationRequest req) {
//        for (Integer seatid: req.getSeatIds()) {
//            Reservation newReservation = new Reservation(req.getScheduleId(), req.getUserId(), seatid);
//            reservationDao.insert(newReservation);
//        }
//    }
    
    @Transactional
	public void reservate(ReservationRequest req) {

		for (int seatId: req.getSeatIds()) {
			int waitOrder = reservationDao.lastWaitOrderByScheduleIdSeatId(req.getScheduleId(), seatId);
			System.out.println("waitOrder: " + waitOrder);
			Reservation newReservation = new Reservation(req.getScheduleId(), req.getUserId(), seatId, waitOrder);
			System.out.println("newReservation: " + newReservation);
			reservationDao.insert(newReservation);
		}
	}
    
    @Transactional
	public void delete(ReservationRequest req) {
    	
//			Reservation newReservation = new Reservation(req.getScheduleId(), req.getUserId(), seatId);
//			reservationDao.deleteReservation(reservation.getId());
		
	}
}

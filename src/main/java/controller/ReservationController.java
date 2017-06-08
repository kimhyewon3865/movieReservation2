package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import movie.Movie;
import movie.MovieDao;
import movie.Reservation;
import movie.ReservationDao;
import movie.ReservationRequest;
import movie.ReservationService;
import movie.ReservationView;
import movie.ReservationViewDao;
import movie.Schedule;
import movie.ScheduleDao;
import movie.Seat;
import movie.SeatDao;
import movie.SelectionMovieTheaterDate;
import movie.Theater;
import movie.TheaterDao;

import java.util.List;
@Controller
public class ReservationController {
	private MovieDao movieDao;
	private TheaterDao theaterDao;
	private ScheduleDao scheduleDao; 
    private ReservationDao reservationDao;
    private SeatDao seatDao;
    private ReservationViewDao reservationViewDao;
    
	private ReservationService reservationService;

	public void setReservationService(
			ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	public void setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
	}
	
	public void setTheaterDao(TheaterDao theaterDao) {
		this.theaterDao = theaterDao;
	}
	
	public void setScheduleDao(ScheduleDao scheduleDao) {
		this.scheduleDao = scheduleDao;
	}
	
	public void setReservationDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}
	
	public void setSeatDao(SeatDao seatDao) {
		this.seatDao = seatDao;
	}
	
	public void setReservationViewDao(ReservationViewDao reservationViewDao) {
		this.reservationViewDao = reservationViewDao;
	}
	
	@RequestMapping("/")
    public String index(Model model) {
       
        return "index";
    }

	@RequestMapping("/selectTest")
    public String selectTest(Model model) {
		
		
        List<Movie> listMovie = movieDao.selectAll();
        List<Theater> listTheater = theaterDao.selectAll();
        List<Schedule> listSchedule = scheduleDao.selectAll();
        
//        //TODO: TEST
//        List<Integer> seatIdList = reservationDao.selectSeatIdByRoomIdTheaterId(1, 1);
//        model.addAttribute("seatIdList", seatIdList);
        List<Integer> seatWaitOrderList = reservationDao.selectSeatWaitOrdersByScheduleIdRoomIdTheaterId(8, 2, 2);
        model.addAttribute("seatWaitOrderList", seatWaitOrderList);
        
        model.addAttribute("listMovie", listMovie);
        model.addAttribute("listTheater", listTheater);
        model.addAttribute("listSchedule", listSchedule);
        
        return "selectTest";
    }
	
	@RequestMapping(value="/selectSeatTest")
	public String selectSeatTest(Model model, @RequestParam(value="movie", required=false) int movie,
											@RequestParam(value="theater", required=false) int theater,
											@RequestParam(value="schedule", required=false) int schedule) {
//		model.addAttribute("movieId", selection.getMovieId());
//		model.addAttribute("theaterId", selection.getTheaterId());
////		model.addAttribute("date", selection.getDate());
//		model.addAttribute("scheduleId", selection.getScheduleId());
		List<Integer> seatWaitOrderList = reservationDao.selectSeatWaitOrdersByScheduleIdRoomIdTheaterId(schedule, movie, theater);
        model.addAttribute("seatWaitOrderList", seatWaitOrderList);
		
		
        Schedule selectedSchedule = scheduleDao.selectScheduleById(schedule);
        model.addAttribute("selectedSchedule", selectedSchedule);
        
        String movieName = movieDao.selectMovieNameByMovieId(movie);
        String theaterName = theaterDao.selectTheaterNameByTheaterId(theater);
        model.addAttribute("movieName", movieName);
        model.addAttribute("theaterName", theaterName);
        
        
		model.addAttribute("movie", movie);
        model.addAttribute("theater", theater);
        model.addAttribute("schedule", schedule);
        
		return "selectSeatTest";
	}
	
//	@RequestMapping(value="/selectSeatTest2")
//	@ResponseBody
//	public String selectSeatTest2(@RequestParam(value = "movieId", required = false) int movieId){
////		Schedule schedules = (Schedule) scheduleDao.selectScheduleByMovieId(movieId);
//		return "selectSeatTest2"; 
//	}
	@RequestMapping(value= "/selectTest2", method=RequestMethod.GET)
	public @ResponseBody Schedule AjaxView( @RequestParam("movieId") int movieId)  {
		Schedule schedule = scheduleDao.selectScheduleByMovieId(movieId);
//	    SocialPerson person = dao.getPerson(id);
	    return schedule;
	}
	
	
	 @RequestMapping(value = "/addReservation", method = RequestMethod.POST)
	   public String addReservation(ReservationRequest reservationRequest, Model model) {
		  reservationService.reservate(reservationRequest); //insert
		  
		  int scheduleId = reservationRequest.getScheduleId(); 
		 		  
		  model.addAttribute("scheduleId", scheduleId);
		  model.addAttribute("userId", reservationRequest.getUserId());
		  model.addAttribute("seatIds", reservationRequest.getSeatIds());

	      List<ReservationView> listReservationView = reservationViewDao.selectByScheduleIdUserIdSeatIds(scheduleId, reservationRequest.getUserId(), reservationRequest.getSeatIds());
	      model.addAttribute("listReservationView", listReservationView);
	      
	      return "addReservation";
	   }
	 
	 @RequestMapping("/reservationHistoryCancel")
	 public String reservationHistoryCancel(Model model) {		
			List<ReservationView> listReservationView = reservationViewDao.selectByUserId("abc");
			model.addAttribute("listReservationView", listReservationView);
	       
	        return "reservationHistoryCancel";
	 }	 
	 
	 @RequestMapping("/cancelReservation")
	 public String cancelReservation(Model model, @RequestParam(value="reservationId", required=false) Long reservationId) {
		 reservationDao.update(reservationId);
		 reservationDao.deleteReservation(reservationId);
		 return "reservationHistoryCancel";
	 }
}

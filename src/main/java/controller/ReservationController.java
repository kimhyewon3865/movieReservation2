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
import movie.Schedule;
import movie.ScheduleDao;
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
	
	@RequestMapping("/")
    public String index(Model model) {
       
        return "index";
    }

	@RequestMapping("/selectTest")
    public String selectTest(Model model) {
        List<Movie> listMovie = movieDao.selectAll();
        List<Theater> listTheater = theaterDao.selectAll();
        List<Schedule> listSchedule = scheduleDao.selectAll();

        model.addAttribute("listMovie", listMovie);
        model.addAttribute("listTheater", listTheater);
        model.addAttribute("listSchedule", listSchedule);
        
        return "selectTest";
    }
	
	@RequestMapping(value="/selectSeatTest")
	public String selectSeatTest(Model model, @RequestParam(value="movie", required=false) String movie,
											@RequestParam(value="theater", required=false) String theater,
											@RequestParam(value="schedule", required=false) String schedule) {
//		model.addAttribute("movieId", selection.getMovieId());
//		model.addAttribute("theaterId", selection.getTheaterId());
////		model.addAttribute("date", selection.getDate());
//		model.addAttribute("scheduleId", selection.getScheduleId());
		
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
		  reservationService.reservate(reservationRequest);

		  model.addAttribute("scheduleId", reservationRequest.getScheduleId());
		  model.addAttribute("userId", reservationRequest.getUserId());
		  model.addAttribute("seatIds", reservationRequest.getSeatIds());
		  
	      return "addReservation";
	   }
	 
	 @RequestMapping("/reservationHistoryCancel")
	 public String reservationHistoryCancel(Model model) {
	        List<Reservation> listReservation = (List<Reservation>) reservationDao.selectByUserId("abc");
	        model.addAttribute("listReservation", listReservation);
	        
	        return "reservationHistoryCancel";
	 }
	 
	 @RequestMapping("/cancelReservation")
	 public String cancelReservation(Model model, @RequestParam(value="reservationId", required=false) Long reservationId) {
		 reservationDao.deleteReservation(reservationId);
		 return "reservationHistoryCancel";
	 }
	 

}

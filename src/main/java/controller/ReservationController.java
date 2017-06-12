package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import movie.Member;
import movie.MemberDao;
import movie.MemberRegisterService;
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
    private MemberDao memberDao;
   
	private ReservationService reservationService;
	private MemberRegisterService memberRegisterService;
	
	public void setReservationService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
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
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
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
        /////remove
       
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
		  System.out.println("test1 : " + scheduleId + " " + reservationRequest.getUserId() + " " + reservationRequest.getSeatIds());
	      List<ReservationView> listReservationView = reservationViewDao.selectByScheduleIdUserIdSeatIds(scheduleId, reservationRequest.getUserId(), reservationRequest.getSeatIds());
	      System.out.println("test2 : " + listReservationView);
	      model.addAttribute("listReservationView", listReservationView);
	      System.out.println("test3 : ");
	      return "addReservation";
	   }
	 
	 @RequestMapping(value= "/selectMovie.do", method=RequestMethod.GET)
	 public ModelAndView AjaxView( @RequestParam("movieId") Integer movieId)  {  
	     ModelAndView mav= new ModelAndView();
	     //필요없을 	     
	     Schedule schedule = scheduleDao.selectByMovieId(movieId);
	     mav.addObject("schedule", schedule);
	     
	     //필요없을
	     List<Schedule> schedules = scheduleDao.selectSchedulesByMovieId(movieId);
	     mav.addObject("schedules", schedules);
	     //필요없을
	     List<String> theaterNames = scheduleDao.selectTheaterNamesByMovieId(movieId);
	     mav.addObject("theaterNames", theaterNames);
	     
	     List<Theater> theaters = theaterDao.selectByMovieId(movieId);
	     mav.addObject("theaters", theaters);
	     
	     mav.setViewName("jsonView");
	     return mav;
	 }
	 
	 @RequestMapping(value= "/selectTheater.do", method=RequestMethod.GET)
	 public ModelAndView AjaxView2( @RequestParam("theaterId") Integer theaterId, @RequestParam("movieId") Integer movieId)  {  
	     ModelAndView mav= new ModelAndView();

	     List<Schedule> schedules = scheduleDao.selectTheaterNamesByMovieIdTheaterId(movieId, theaterId);
	     mav.addObject("schedules", schedules);
	     mav.setViewName("jsonView");

	     return mav;
	 }
	 
	 @RequestMapping(value= "/selectDate.do", method=RequestMethod.GET)
	 public ModelAndView AjaxView3( @RequestParam("theaterId") Integer theaterId, @RequestParam("movieId") Integer movieId, @RequestParam("date") String date)   {  
	     ModelAndView mav= new ModelAndView();

	     List<Schedule> schedules = scheduleDao.selectTheaterNamesByMovieIdTheaterIdDate(movieId, theaterId, date);
	     mav.addObject("schedules", schedules);
	     mav.setViewName("jsonView");

	     return mav;
	 }
	 
	 @RequestMapping(value= "/cancel.do", method=RequestMethod.GET)
	 public ModelAndView AjaxView4(@RequestParam("reservationId") Integer reservationId)   {  
	     ModelAndView mav = new ModelAndView();
	     System.out.println("reservationId : " + reservationId);
	     reservationDao.update(reservationId);
	     System.out.println("update success!!");
		 reservationDao.deleteReservation(reservationId);
		 System.out.println("delete success!!");
	     List<ReservationView> reservationViews = reservationViewDao.selectByReservationId(reservationId);
	     System.out.println("dao success!!" + reservationViews);
	     mav.addObject("reservationViews", reservationViews);
//	     List<Schedule> schedules = scheduleDao.selectTheaterNamesByMovieIdTheaterIdDate(movieId, theaterId, date);
//	     mav.addObject("schedules", schedules);
	     mav.setViewName("jsonView");

	     return mav;
	 }
	 
	 @RequestMapping("/checkReservationForManager")
	 public String checkReservationForManager(Model model) {		
		 List<ReservationView> listReservationViews = reservationViewDao.selectAll();
		 model.addAttribute("listReservationViews", listReservationViews);
	     
		 List<Movie> listMovie = movieDao.selectAll();
		 List<Theater> listTheater = theaterDao.selectAll();
		 List<Schedule> listSchedule = scheduleDao.selectAll();
	     model.addAttribute("listMovie", listMovie);
	     model.addAttribute("listTheater", listTheater);
	     model.addAttribute("listSchedule", listSchedule);
		 
	     return "checkReservationForManager";
	 }	

	 

	 
	 @RequestMapping("/reservationHistoryCancel")
	 public String reservationHistoryCancel(Model model) {		
			List<ReservationView> listReservationView = reservationViewDao.selectByUserId("abc");
			model.addAttribute("listReservationView", listReservationView);
	       
	        return "reservationHistoryCancel";
	 }	 
	 
	 @RequestMapping("/cancelReservation")
	 public String cancelReservation(Model model, @RequestParam(value="reservationId", required=false) Integer reservationId) {
		 reservationDao.update(reservationId);
		 reservationDao.deleteReservation(reservationId);
		 return "reservationHistoryCancel";
	 }
	 
//	 @RequestMapping("/signUpSignIn")
//	 public String signUpSignIn(Model model) {		
//	        return "signUpSignIn";
//	 }	 
//	 
	 @RequestMapping("/login")
	 public String login(Model model) {		
	        return "login";
	 }	
	 
	 @RequestMapping("/login.do")
	 public String loginDo(Model model, @RequestParam(value="id", required=false) String id, @RequestParam(value="password", required=false) String password) {		
		 System.out.println("id: " + id + "password: " + password);
		 if (memberDao.isMember(id, password) != 0 ) {
			 return "selectTest";
		 } else {
			 return "cancelReservation";
		 }
	 }

	 


}


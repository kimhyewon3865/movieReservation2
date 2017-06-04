package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import movie.Movie;
import movie.MovieDao;

import java.util.List;
@Controller
public class ReservationController {
	private MovieDao movieDao;
	
	public void setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
	}
	
	@RequestMapping("/")
    public String index(Model model) {
       
        return "index";
    }

	@RequestMapping("/selectTest")
    public String selectTest(Model model) {
        List<Movie> listMovie = movieDao.selectAll();
        model.addAttribute("listMovie", listMovie);

        return "selectTest";
    }
}

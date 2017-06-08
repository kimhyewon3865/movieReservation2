package movie;

import java.sql.*;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MovieDao {
	  private JdbcTemplate jdbcTemplate;

	    public MovieDao(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource);
	    }

	    public List<Movie> selectAll() {
	        List<Movie> results = jdbcTemplate.query("select * from movie", new RowMapper<Movie>() {
	                    @Override
	                    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
	                        Movie movie = new Movie(rs.getString("name"), rs.getInt("grade"), rs.getString("genre"), rs.getString("nation"), rs.getString("viewingTime"), rs.getDate("releaseDate"), rs.getString("director"));
	                        movie.setId(rs.getInt("id"));
	                        return movie;
	                    }
	                });
	        return results;
	    }
	    
	    public String selectMovieNameByMovieId(int movieId) {
	    	String query = "select name from movie where id = " + movieId;
	    	String name = jdbcTemplate.queryForObject(query, String.class);
	    	return name;
	    }
}

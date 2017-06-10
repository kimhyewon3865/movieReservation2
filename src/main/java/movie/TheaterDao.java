package movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class TheaterDao {
	  private JdbcTemplate jdbcTemplate;

	    public TheaterDao(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource);
	    }

	    public List<Theater> selectAll() {
	        List<Theater> results = jdbcTemplate.query("select * from theater", new RowMapper<Theater>() {
	                    @Override
	                    public Theater mapRow(ResultSet rs, int rowNum) throws SQLException {
	                    	Theater theater = new Theater(rs.getString("name"), rs.getString("location"));
	                    	theater.setId(rs.getInt("id"));
	                        return theater;
	                    }
	                });
	        return results;
	    }
	   
	    public String selectTheaterNameByTheaterId(int theaterId) {
	    	String query = "select name from theater where id = " + theaterId;
	    	String name = jdbcTemplate.queryForObject(query, String.class);
	    	return name;
	    }
	    
	    public List<Theater> selectByMovieId(int movieId) {
	        List<Theater> results = jdbcTemplate.query("SELECT distinct t.id, t.name, t.location FROM theater t, schedule s where  s.theaterId = t.id and movieId = ?", new RowMapper<Theater>() {
	                    @Override
	                    public Theater mapRow(ResultSet rs, int rowNum) throws SQLException {
	                    	Theater theater = new Theater(rs.getString("name"), rs.getString("location"));
	                    	theater.setId(rs.getInt("id"));
	                        return theater;
	                    }
	                }, movieId);
	        return results;
	    }
}


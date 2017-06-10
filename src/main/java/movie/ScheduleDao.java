package movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


public class ScheduleDao {
	private JdbcTemplate jdbcTemplate;

    public ScheduleDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Schedule> selectAll() {
        List<Schedule> results = jdbcTemplate.query("select * from schedule", new RowMapper<Schedule>() {
                    @Override
                    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                    	Schedule schedule = new Schedule(rs.getInt("movieId"), rs.getString("date"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("theaterId"), rs.getInt("roomId"));                    			
                    	schedule.setId(rs.getInt("id"));
                        return schedule;
                    }
                });
        return results;
    }
    
    public Schedule selectScheduleByMovieId(int movieId) {
		List<Schedule> results = jdbcTemplate.query(
				"select * from schedule where movieId = ?",
				new RowMapper<Schedule>() {
					@Override
					public Schedule mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Schedule schedule = new Schedule(
								rs.getInt("movieId"), rs.getString("date"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("theaterId"), rs.getInt("roomId"));
						schedule.setId(rs.getInt("id"));
						return schedule;
					}
				},
				movieId);

		return results.isEmpty() ? null : results.get(0);
	}
    
    public Schedule selectScheduleById(int scheduleId) {
		List<Schedule> results = jdbcTemplate.query("select * from schedule where id = ?",
				new RowMapper<Schedule>() {
					@Override
					public Schedule mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Schedule schedule = new Schedule(
								rs.getInt("movieId"), rs.getString("date"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("theaterId"), rs.getInt("roomId"));
						schedule.setId(rs.getInt("id"));
						return schedule;
					}
				},
				scheduleId);
		return results.isEmpty() ? null : results.get(0);
	}
    
    public String selectTheaterNameByscheduleId(int scheduleId) {
    	String query = "select t.name from theater t, schedule s where s.theaterId = t.id and s.id = " + scheduleId;
    	String name = jdbcTemplate.queryForObject(query, String.class);
    	return name;
    }
    
    public String selectMovieNameByscheduleId(int scheduleId) {
    	String query = "select m.name from theater m, schedule s where s.theaterId = m.id and s.id = " + scheduleId;
    	String name = jdbcTemplate.queryForObject(query, String.class);
    	return name;
    }
    
    public Movie selectMovieByScheduleId(int scheduleId) {
        List<Movie> results = jdbcTemplate.query("select m.id, m.name, m.grade, m.genre, m.nation, m.viewingTime, m.releaseDate, m.director from schedule s, movie m where m.id = s.movieId and s.id = ?", new RowMapper<Movie>() {
                    @Override
                    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
                    	Movie movie = new Movie(rs.getString("name"), rs.getInt("grade"), rs.getString("genre"), rs.getString("nation"), rs.getString("viewingTime"), rs.getString("releaseDate"), rs.getString("director"));
                    	movie.setId(rs.getInt("id"));
                        return movie;
                    }
                }, scheduleId);
        return results.isEmpty() ? null : results.get(0);
    }
    
    public Schedule selectByMovieId(int movieId) {
        List<Schedule> results = jdbcTemplate.query("select * from schedule where movieId = ?", new RowMapper<Schedule>() {
                    @Override
                    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                    	Schedule schedule = new Schedule(rs.getInt("movieId"), rs.getString("date"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("theaterId"), rs.getInt("roomId"));                    			
                    	schedule.setId(rs.getInt("id"));
                        return schedule;
                    }
                }, movieId);
//        return results;
        return results.isEmpty() ? null : results.get(0);
    }
    
    public List<Schedule> selectSchedulesByMovieId(int movieId) {
        List<Schedule> results = jdbcTemplate.query("select * from schedule where movieId = ?", new RowMapper<Schedule>() {
                    @Override
                    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                    	Schedule schedule = new Schedule(rs.getInt("movieId"), rs.getString("date"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("theaterId"), rs.getInt("roomId"));                    			
                    	schedule.setId(rs.getInt("id"));
                        return schedule;
                    }
                }, movieId);
        return results;
    }
    
    public List<String> selectTheaterNamesByMovieId(int movieId) {
    	List<String> results = jdbcTemplate.query("select distinct t.name from schedule s, theater t where s.theaterId = t.id and s.movieId = ?", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            	return rs.getString("name");
            }
        }, movieId);
    	return results;
    }
    
    public List<Schedule> selectTheaterNamesByMovieIdTheaterId(int movieId, int theaterId) {
        List<Schedule> results = jdbcTemplate.query("select * from schedule where movieId = ? and theaterId = ?", new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
            	Schedule schedule = new Schedule(rs.getInt("movieId"), rs.getString("date"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("theaterId"), rs.getInt("roomId"));                    			
            	schedule.setId(rs.getInt("id"));
                return schedule;
            }
        }, movieId, theaterId);
        return results;
    }
}

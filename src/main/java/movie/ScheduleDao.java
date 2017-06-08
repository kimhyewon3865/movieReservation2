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
                    	Schedule schedule = new Schedule(rs.getInt("movieId"), rs.getDate("date"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("theaterId"), rs.getInt("roomId"));                    			
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
								rs.getInt("movieId"), rs.getDate("date"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("theaterId"), rs.getInt("roomId"));
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
								rs.getInt("movieId"), rs.getDate("date"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("theaterId"), rs.getInt("roomId"));
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
    
//    public List<Schedule> selectScheduleByMovieId(int movieId) {
//        List<Schedule> results = jdbcTemplate.query("select * from schedule where movieId = ?", new RowMapper<Schedule>() {
//                    @Override
//                    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
//                    	Schedule schedule = new Schedule(rs.getInt("movieId"), rs.getDate("date"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("theaterId"), rs.getInt("roomId"));
//                    	schedule.setId(rs.getInt("id"));
//                        return schedule;
//                    }
//                }, movieId);
//        return results;
//    }
}

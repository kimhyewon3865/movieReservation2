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
    
    
}

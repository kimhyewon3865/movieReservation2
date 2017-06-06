package movie;

import java.sql.*;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class ReservationDao {
	private JdbcTemplate jdbcTemplate;

    public ReservationDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //insert
//    public void insert(final Reservation reservation) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection con)
//                    throws SQLException {
//
//                PreparedStatement pstmt = con.prepareStatement("INSERT INTO reservation (scheduleId, userId, seatId)" + "VALUES (?, ?, ?)");
//
//                pstmt.setInt(1,  reservation.getScheduleId());
//                pstmt.setString(2,  reservation.getUserId());
//                pstmt.setInt(3, reservation.getSeatId());
//               
//                return pstmt;
//            }
//        }, keyHolder);
//        Number keyValue = keyHolder.getKey();
//        reservation.setId(keyValue.longValue());
//    }
//    
    
    public void insert(final Reservation reservation) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) 
					throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into reservation (scheduleId, userId, seatId) "+
						"values (?, ?, ?)",
						new String[] {"ID"});
				pstmt.setInt(1,  reservation.getScheduleId());
				pstmt.setString(2,  reservation.getUserId());
				pstmt.setInt(3,  reservation.getSeatId());
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		reservation.setId(keyValue.longValue());
	}
}

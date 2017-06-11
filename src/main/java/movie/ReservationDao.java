package movie;

import java.sql.*;
import java.util.ArrayList;
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
    
    public void insert(final Reservation reservation) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) 
					throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into reservation (scheduleId, userId, seatId, waitOrder) "+
						"values (?, ?, ?, ?)",
						new String[] {"ID"});
				pstmt.setInt(1,  reservation.getScheduleId());
				pstmt.setString(2,  reservation.getUserId());
				pstmt.setInt(3,  reservation.getSeatId());
				pstmt.setInt(4,  reservation.getWaitOrder());
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		reservation.setId(keyValue.longValue());
	}
    
    public List<Reservation> selectByUserId(String userId) {
        List<Reservation> results = jdbcTemplate.query("select * from reservation where userId = ?", new RowMapper<Reservation>() {
                    @Override
                    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
                    	Reservation reservation = new Reservation(rs.getInt("scheduleId"), rs.getString("userId"), rs.getInt("seatId"), rs.getInt("waitOrder"));
                    	reservation.setId(rs.getLong("id"));
                        return reservation;
                    }
                }, userId);
        return results;
    }
    
    public void deleteReservation(int id) {
        String deleteStatement = "DELETE FROM reservation WHERE id=?";
        jdbcTemplate.update(deleteStatement, id);
    }
    
    public Integer lastWaitOrderByScheduleIdSeatId(int scheduleId, int seatId) {
    	String query = "select count(*)  from reservation where scheduleId = "+scheduleId + " AND seatId = " + seatId;
		Integer count = jdbcTemplate.queryForObject(query, Integer.class);
    	return count;
    }
    
    public List<Integer> selectSeatIdByRoomIdTheaterId(int roomId, int theaterId) {
        List<Integer> results = jdbcTemplate.query("select id from seat where roomId = ? and theaterId = ?", new RowMapper<Integer>() {
                    @Override
                    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                    	return rs.getInt("id");
                    }
                }, roomId, theaterId);
        return results;
    }
    
    
    public List<Integer> selectSeatWaitOrdersByScheduleIdRoomIdTheaterId(int scheduleId, int roomId, int theaterId) {
    	List<Integer> seatIds = selectSeatIdByRoomIdTheaterId(roomId, theaterId);
    	List<Integer> waitOrders = new ArrayList<Integer>();
    	for (int seatId: seatIds) {
    		Integer count = lastWaitOrderByScheduleIdSeatId(scheduleId, seatId);
    		waitOrders.add(count);
    	}
    	
    	return waitOrders;
    }
    
    public List<Integer> selectReservationIdsByScheduleIdSeatId(int reservationId) {
    	List<Integer> results = jdbcTemplate.query("select r2.id from reservation r1, reservation r2 where r1.scheduleId = r2.scheduleId and r1.seatId = r2.seatId and r1.id = ? and r2.id != ?;", new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            	return rs.getInt("id");
            }
        }, reservationId, reservationId);
    	return results;
    }
    
    
    public void update(int reservationId) {
    	List<Integer> reservationIds = selectReservationIdsByScheduleIdSeatId(reservationId);
    	
    	String waitOrderQuery = "select waitOrder from reservation where id = " + reservationId;
    	Integer waitOrder = jdbcTemplate.queryForObject(waitOrderQuery, Integer.class);
    	
    	for (int id: reservationIds) {
    		String query = "update reservation set waitOrder = waitOrder - 1 where id = " + id + " and waitOrder > " + waitOrder ;
    		jdbcTemplate.update(query);
    	}
    }

        
}

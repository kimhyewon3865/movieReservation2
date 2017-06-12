package movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ReservationViewDao {
	
	private JdbcTemplate jdbcTemplate;

    public ReservationViewDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public List<ReservationView> selectAll() {
        List<ReservationView> results = jdbcTemplate.query("select distinct r.id as reservationId, m.name as movieName, t.name as theaterName, c.date ,c.roomId , c.startTime, c.endTime, r.seatId, s.price, r.waitOrder, r.userId from movie m, reservation r, seat s, theater t, schedule c where r.scheduleId = c.id and r.seatId = s.id and c.movieId = m.id and c.theaterId = t.id", new RowMapper<ReservationView>() {
                    @Override
                    public ReservationView mapRow(ResultSet rs, int rowNum) throws SQLException {
                    	ReservationView reservationView = new ReservationView(rs.getInt("reservationId"), rs.getString("movieName"), rs.getString("theaterName"), rs.getString("date"), rs.getInt("roomId"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("seatId"), rs.getInt("price"), rs.getInt("waitOrder"), rs.getString("userId"));
                        return reservationView;
                    }
                });
        return results;
    }

    
    public List<ReservationView> selectByReservationId(int reservationId) {
    	String query = "select distinct r.id as reservationId, m.name as movieName, t.name as theaterName, c.date ,c.roomId , c.startTime, c.endTime, r.seatId, s.price, r.waitOrder, r.userId from movie m, reservation r, seat s, theater t, schedule c where r.scheduleId = c.id and r.seatId = s.id and c.movieId = m.id and c.theaterId = t.id and r.id = ?";
        List<ReservationView> results = jdbcTemplate.query(query, new RowMapper<ReservationView>() {
                    @Override
                    public ReservationView mapRow(ResultSet rs, int rowNum) throws SQLException {
                    	ReservationView reservationView = new ReservationView(rs.getInt("reservationId"), rs.getString("movieName"), rs.getString("theaterName"), rs.getString("date"), rs.getInt("roomId"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("seatId"), rs.getInt("price"), rs.getInt("waitOrder"), rs.getString("userId"));
                        return reservationView;
                    }
                }, reservationId);
        return results;
    }
    
    public List<ReservationView> selectByUserId(String userId) {
    	String query = "select distinct r.id as reservationId, m.name as movieName, t.name as theaterName, c.date ,c.roomId , c.startTime, c.endTime, r.seatId, s.price, r.waitOrder, r.userId from movie m, reservation r, seat s, theater t, schedule c where r.scheduleId = c.id and r.seatId = s.id and c.movieId = m.id and c.theaterId = t.id and r.userId = ?";
        List<ReservationView> results = jdbcTemplate.query(query, new RowMapper<ReservationView>() {
                    @Override
                    public ReservationView mapRow(ResultSet rs, int rowNum) throws SQLException {
                    	ReservationView reservationView = new ReservationView(rs.getInt("reservationId"), rs.getString("movieName"), rs.getString("theaterName"), rs.getString("date"), rs.getInt("roomId"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("seatId"), rs.getInt("price"), rs.getInt("waitOrder"), rs.getString("userId"));
                        return reservationView;
                    }
                }, userId);
        return results;
    }
    
    public List<ReservationView> selectByScheduleIdUserIdSeatIds(int scheduleId, String userId, int[] seatIds) {
    	List<ReservationView> listReservationView = new ArrayList<ReservationView>();
    	for (int seatId: seatIds) {
    		String query = "select distinct r.id as reservationId, m.name as movieName, t.name as theaterName, c.date ,c.roomId , c.startTime, c.endTime, r.seatId, s.price, r.waitOrder, r.userId from movie m, reservation r, seat s, theater t, schedule c where r.scheduleId = c.id and r.seatId = s.id and c.movieId = m.id and c.theaterId = t.id and r.scheduleId = ? and userId = ? and seatId = ?;";
            List<ReservationView> results = jdbcTemplate.query(query, new RowMapper<ReservationView>() {
                        @Override
                        public ReservationView mapRow(ResultSet rs, int rowNum) throws SQLException {
                        	ReservationView reservationView = new ReservationView(rs.getInt("reservationId"), rs.getString("movieName"), rs.getString("theaterName"), rs.getString("date"), rs.getInt("roomId"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("seatId"), rs.getInt("price"), rs.getInt("waitOrder"), rs.getString("userId"));
                            return reservationView;
                        }
                    }, scheduleId, userId, seatId);
            listReservationView.add(results.get(0));
    	}
    	return listReservationView;
//    	String query = "select distinct r.id as reservationId, m.name as movieName, t.name as theaterName, c.date ,c.roomId , c.startTime, c.endTime, r.seatId, s.price, r.waitOrder, r.userId from movie m, reservation r, seat s, theater t, schedule c where r.scheduleId = c.id and r.seatId = s.id and c.movieId = m.id and c.theaterId = t.id and r.id = ?";
//        List<ReservationView> results = jdbcTemplate.query(query, new RowMapper<ReservationView>() {
//                    @Override
//                    public ReservationView mapRow(ResultSet rs, int rowNum) throws SQLException {
//                    	ReservationView reservationView = new ReservationView(rs.getInt("reservationId"), rs.getString("movieName"), rs.getString("theaterName"), rs.getDate("date"), rs.getInt("roomId"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("seatId"), rs.getInt("price"), rs.getInt("waitOrder"), rs.getString("userId"));
//                        return reservationView;
//                    }
//                }, reservationId);
//        return results;
    }
    
    public List<ReservationView> selectByMovieId(int movieId) {
    	String query = "select distinct r.id as reservationId, m.name as movieName, t.name as theaterName, c.date ,c.roomId , c.startTime, c.endTime, r.seatId, s.price, r.waitOrder, r.userId from movie m, reservation r, seat s, theater t, schedule c where r.scheduleId = c.id and r.seatId = s.id and c.movieId = m.id and c.theaterId = t.id and c.movieId = ?";
        List<ReservationView> results = jdbcTemplate.query(query, new RowMapper<ReservationView>() {
                    @Override
                    public ReservationView mapRow(ResultSet rs, int rowNum) throws SQLException {
                    	ReservationView reservationView = new ReservationView(rs.getInt("reservationId"), rs.getString("movieName"), rs.getString("theaterName"), rs.getString("date"), rs.getInt("roomId"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("seatId"), rs.getInt("price"), rs.getInt("waitOrder"), rs.getString("userId"));
                        return reservationView;
                    }
                }, movieId);
        return results;
    }
    
    public List<ReservationView> selectByTheaterId(int theaterId) {
    	String query = "select distinct r.id as reservationId, m.name as movieName, t.name as theaterName, c.date ,c.roomId , c.startTime, c.endTime, r.seatId, s.price, r.waitOrder, r.userId from movie m, reservation r, seat s, theater t, schedule c where r.scheduleId = c.id and r.seatId = s.id and c.movieId = m.id and c.theaterId = t.id and c.theaterId = ?";
        List<ReservationView> results = jdbcTemplate.query(query, new RowMapper<ReservationView>() {
                    @Override
                    public ReservationView mapRow(ResultSet rs, int rowNum) throws SQLException {
                    	ReservationView reservationView = new ReservationView(rs.getInt("reservationId"), rs.getString("movieName"), rs.getString("theaterName"), rs.getString("date"), rs.getInt("roomId"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("seatId"), rs.getInt("price"), rs.getInt("waitOrder"), rs.getString("userId"));
                        return reservationView;
                    }
                }, theaterId);
        return results;
    }
    
    public List<ReservationView> selectByDate(String date) {
    	String query = "select distinct r.id as reservationId, m.name as movieName, t.name as theaterName, c.date ,c.roomId , c.startTime, c.endTime, r.seatId, s.price, r.waitOrder, r.userId from movie m, reservation r, seat s, theater t, schedule c where r.scheduleId = c.id and r.seatId = s.id and c.movieId = m.id and c.theaterId = t.id and c.date = ?";
        List<ReservationView> results = jdbcTemplate.query(query, new RowMapper<ReservationView>() {
                    @Override
                    public ReservationView mapRow(ResultSet rs, int rowNum) throws SQLException {
                    	ReservationView reservationView = new ReservationView(rs.getInt("reservationId"), rs.getString("movieName"), rs.getString("theaterName"), rs.getString("date"), rs.getInt("roomId"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("seatId"), rs.getInt("price"), rs.getInt("waitOrder"), rs.getString("userId"));
                        return reservationView;
                    }
                }, date);
        return results;
    }
    
    public List<ReservationView> selectByMovieIdTheaterId(int movieId, int theaterId) {
    	String query = "select distinct r.id as reservationId, m.name as movieName, t.name as theaterName, c.date ,c.roomId , c.startTime, c.endTime, r.seatId, s.price, r.waitOrder, r.userId from movie m, reservation r, seat s, theater t, schedule c where r.scheduleId = c.id and r.seatId = s.id and c.movieId = m.id and c.theaterId = t.id and c.movieId = ? and c.theaterId = ?";
        List<ReservationView> results = jdbcTemplate.query(query, new RowMapper<ReservationView>() {
                    @Override
                    public ReservationView mapRow(ResultSet rs, int rowNum) throws SQLException {
                    	ReservationView reservationView = new ReservationView(rs.getInt("reservationId"), rs.getString("movieName"), rs.getString("theaterName"), rs.getString("date"), rs.getInt("roomId"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("seatId"), rs.getInt("price"), rs.getInt("waitOrder"), rs.getString("userId"));
                        return reservationView;
                    }
                }, movieId, theaterId);
        return results;
    }
    
    public List<ReservationView> selectByMovieIdDate(int movieId, String date) {
    	String query = "select distinct r.id as reservationId, m.name as movieName, t.name as theaterName, c.date ,c.roomId , c.startTime, c.endTime, r.seatId, s.price, r.waitOrder, r.userId from movie m, reservation r, seat s, theater t, schedule c where r.scheduleId = c.id and r.seatId = s.id and c.movieId = m.id and c.theaterId = t.id and c.movieId = ? and c.date = ?";
        List<ReservationView> results = jdbcTemplate.query(query, new RowMapper<ReservationView>() {
                    @Override
                    public ReservationView mapRow(ResultSet rs, int rowNum) throws SQLException {
                    	ReservationView reservationView = new ReservationView(rs.getInt("reservationId"), rs.getString("movieName"), rs.getString("theaterName"), rs.getString("date"), rs.getInt("roomId"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("seatId"), rs.getInt("price"), rs.getInt("waitOrder"), rs.getString("userId"));
                        return reservationView;
                    }
                }, movieId, date);
        return results;
    }
    
    public List<ReservationView> selectByTheaterIdDate(int theaterId, String date) {
    	String query = "select distinct r.id as reservationId, m.name as movieName, t.name as theaterName, c.date ,c.roomId , c.startTime, c.endTime, r.seatId, s.price, r.waitOrder, r.userId from movie m, reservation r, seat s, theater t, schedule c where r.scheduleId = c.id and r.seatId = s.id and c.movieId = m.id and c.theaterId = t.id and c.theaterId = ? and c.date = ?";
        List<ReservationView> results = jdbcTemplate.query(query, new RowMapper<ReservationView>() {
                    @Override
                    public ReservationView mapRow(ResultSet rs, int rowNum) throws SQLException {
                    	ReservationView reservationView = new ReservationView(rs.getInt("reservationId"), rs.getString("movieName"), rs.getString("theaterName"), rs.getString("date"), rs.getInt("roomId"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("seatId"), rs.getInt("price"), rs.getInt("waitOrder"), rs.getString("userId"));
                        return reservationView;
                    }
                }, theaterId, date);
        return results;
    }
    
    public List<ReservationView> selectByMovieIdTheaterIdDate(int movieId, int theaterId, String date) {
    	String query = "select distinct r.id as reservationId, m.name as movieName, t.name as theaterName, c.date ,c.roomId , c.startTime, c.endTime, r.seatId, s.price, r.waitOrder, r.userId from movie m, reservation r, seat s, theater t, schedule c where r.scheduleId = c.id and r.seatId = s.id and c.movieId = m.id and c.theaterId = t.id and c.movieId = ? and c.theaterId = ? and c.date = ?";
        List<ReservationView> results = jdbcTemplate.query(query, new RowMapper<ReservationView>() {
                    @Override
                    public ReservationView mapRow(ResultSet rs, int rowNum) throws SQLException {
                    	ReservationView reservationView = new ReservationView(rs.getInt("reservationId"), rs.getString("movieName"), rs.getString("theaterName"), rs.getString("date"), rs.getInt("roomId"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("seatId"), rs.getInt("price"), rs.getInt("waitOrder"), rs.getString("userId"));
                        return reservationView;
                    }
                }, movieId, theaterId, date);
        return results;
    }
    
//    public List<ReservationView> selectAll() {
//        List<ReservationView> results = jdbcTemplate.query("select distinct r.id as reservationId, m.name as movieName, t.name as theaterName, c.date ,c.roomId , c.startTime, c.endTime, r.seatId, s.price, r.waitOrder, r.userId from movie m, reservation r, seat s, theater t, schedule c where r.scheduleId = c.id and r.seatId = s.id and c.movieId = m.id and c.theaterId = t.id ", new RowMapper<ReservationView>() {
//                    @Override
//                    public ReservationView mapRow(ResultSet rs, int rowNum) throws SQLException {
//                    	ReservationView reservationView = new ReservationView(rs.getInt("reservationId"), rs.getString("movieName"), rs.getString("theaterName"), rs.getString("date"), rs.getInt("roomId"), rs.getString("startTime"), rs.getString("endTime"), rs.getInt("seatId"), rs.getInt("price"), rs.getInt("waitOrder"), rs.getString("userId"));
//                        return reservationView;
//                    }
//                });
//        return results;
//    }
    
}

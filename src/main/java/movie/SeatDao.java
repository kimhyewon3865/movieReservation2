package movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class SeatDao {
	
	private JdbcTemplate jdbcTemplate;

    public SeatDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
//    public List<Integer> selectSeatPriceByIdsRoomIdTheaterId(int[] ids, int roomId, int theaterId) {
//    	List<Integer> prices = new ArrayList<Integer>();
//    	
//    	for (int id: ids) {
//	    	String query = "select price from seat where id = " + id + " and roomId = " + roomId + " and theaterId = " + theaterId ;
//	    	Integer price = jdbcTemplate.queryForObject(query, Integer.class);
//	    	prices.add(price);
//    	}
//    	
//        return prices;
//    }
    
    public List<Seat> selectSeatByIdRoomIdTheaterId(int[] ids, int roomId, int theaterId) {
    	List<Seat> seats = new ArrayList<Seat>();
		for (int id: ids) {
			List<Seat> results = jdbcTemplate.query("select * from seat where id = ? and roomId = ? and theaterId = ?", new RowMapper<Seat>() {
				@Override
				public Seat mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					Seat seat = new Seat( rs.getInt("id"), rs.getInt("theaterId"), rs.getInt("roomId"), rs.getInt("price")
							//id 설정 
							);
					return seat;
				}
			},
			id, roomId, theaterId);
			seats.add(results.get(0));
		}
		
		
		return seats.isEmpty() ? null : seats;
	}
}
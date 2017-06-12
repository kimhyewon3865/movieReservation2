package movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberDao {
	private JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Member selectById(String id) {
		List<Member> results = jdbcTemplate.query("select * from user where id = ?", new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member member = new Member(rs.getString("id"), rs.getString("password"));
						return member;
					}
				},
				id);
		return results.isEmpty() ? null : results.get(0);
	}

	public void insert(final Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement("insert into user (id, password) "+ "values (?, ?)", new String[] {"id"});
				pstmt.setString(1,  member.getId());
				pstmt.setString(2,  member.getPassword());
				
				return pstmt;
			}
		}, keyHolder);
//		Number keyValue = keyHolder.getKey();
//		member.setId(keyValue.longValue());
	}
	
	public int isMember(String id, String password) {
		Integer count = jdbcTemplate.queryForObject("select count(*) from user where id = '" + id + "' and password = '" + password + "';", Integer.class);
		return count;
	}
}

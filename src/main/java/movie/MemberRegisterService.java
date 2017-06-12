package movie;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

public class MemberRegisterService {
	private MemberDao memberDao;

	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Transactional
	public void regist(Member req) {
		Member member = memberDao.selectById(req.getId());
		if (member != null) {
//			throw new AlreadyExistingMemberException("dup email " + req.getEmail());
		}
		Member newMember = new Member(req.getId(), req.getPassword());
		memberDao.insert(newMember);
	}
}

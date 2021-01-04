package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import member.model.LoginInfo;
import member.model.Member;
import util.CookieBox;

public class MemberLoginService {

	// �̱���
	private MemberLoginService() {
	}

	private static MemberLoginService service = new MemberLoginService();

	public static MemberLoginService getInstance() {
		return service;
	}

	// request, response ���� �ް�
	// ����� �Է� ������ id/pw �α��� ó��, ���̵� ����
	// session = request.getSession()
	public void memberLogin(HttpServletRequest request, HttpServletResponse response) {

		// ������ �ޱ� : ���̵�, ��й�ȣ
		String userId = request.getParameter("userid");
		String pw = request.getParameter("pw");

		String chk = request.getParameter("chk");

		if (chk != null && chk.equals("on") && userId != null && !userId.isEmpty()) {
			// ��Ű ���� ����
			// uid=userId
			response.addCookie(CookieBox.createCookie("uid", userId, "/", 60 * 60 * 24 * 365));
		} else {
			response.addCookie(CookieBox.createCookie("uid", userId, "/", 0));
		}

		// session�� �̿��ؼ� �α��� ó��
		// id, pw�� �������� Member ���̺��� select �� ����� ������ ���� ����, ������ ���� ����
		// �α��� ���� üũ
		boolean loginChk = false;

		MemberDao dao = null;
		Connection conn = null;

		try {

			conn = ConnectionProvider.getConnection();
			dao = MemberDao.getInstance();

			Member member = dao.selectMemberLogin(conn, userId, pw);

			if (member != null) {

				// ������ �Ǿ� �α��� ó�� : session ��ü�� �α��� ������ �Ӽ��� ����
				// System.out.println(member);
				LoginInfo loginInfo = member.toLoginInfo();

				HttpSession session = request.getSession();  
				// getSession() -> session �� ������ �ִ� session�� ��ȯ, ������ ���ο� session ��ü�� �����ؼ� ��ȯ
				// getSession(flase) -> session �� ������ �ִ� session�� ��ȯ, ������ null�� ��ȯ
				// getSession(true) -> ���ο� session�� �����ؼ� ��ȯ

				session.setAttribute("loginInfo", loginInfo);

				// System.out.println(loginInfo);
				loginChk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("loginChk", loginChk);
	}
}
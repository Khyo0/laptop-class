package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;
import member.model.MemberListView;

public class MemberListService {

	// �̱��� ����
	private MemberListService() {
	}

	private static MemberListService service = new MemberListService();

	public static MemberListService getInstance() {
		return service;
	}

	// ������ ��ȣ�� request ���� �� �ִ�.
	// request�� ���� �޾� ������ �ϳ��� ǥ���� �����͸� ���� -> MemberListView ��ü ����
	// MemberListView ��ü�� view �������� ����
	public void getMemberListView(HttpServletRequest request) {

		MemberListView listView = null;

		Connection conn = null;
		MemberDao dao = null;

		try {

			conn = ConnectionProvider.getConnection();
			dao = MemberDao.getInstance();

			// ��ü ȸ���� ��
			int memberTotalCount = dao.selectMemberTotalCount(conn);

			// �������� ǥ���� ȸ���� ��
			int memberCountPerPage = 3;

			// ���� ������ ��ȣ
			int pageNumber = 1;
			try {
				pageNumber = Integer.parseInt(request.getParameter("page"));
			} catch (Exception e) {
			}

			// DB���� ���ϰ��� �ϴ� ������ ù ��° ��
			int firstRow = (pageNumber - 1) * memberCountPerPage;

			// Member ��ü�� �����ϴ� List
			List<Member> memberList = dao.selectMember(conn, firstRow, memberCountPerPage);

			listView = new MemberListView(memberTotalCount, memberCountPerPage, memberList, firstRow, pageNumber);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("listView", listView);

	}

}
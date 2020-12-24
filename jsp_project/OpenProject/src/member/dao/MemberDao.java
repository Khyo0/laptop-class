package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import member.Member;

public class MemberDao {

	// �̱��� ���� : �ڵ� ��Ÿ��
	// �ܺο��� �ν��Ͻ��� �������� ���Ѵ�. 
	// �ν��Ͻ��� ��ȯ ���ִ� �޼ҵ尡 �־� �ν��Ͻ��� �ʿ��� ���� �޼ҵ带 �̿��ؼ� ��´�.

	// �ν��Ͻ� ���� ���´� : �������� ���������� -> private
	private MemberDao() {}

	// ��ü�ϳ��� �����ؼ� ����Ѵ�.
	private static MemberDao dao = new MemberDao();

	// Dao ��ü�� ���������� ��ȯ���ִ� �޼ҵ� : �ܺ� Ŭ���� ������ ��� �����ؾ��Ѵ�.
	public static MemberDao getInstance() {
		return dao;
	}

	// Member ���̺��� Data�� CRUD
	// insert, select, update, delete

	// ������ �Է�
	public int insertMember(Connection conn, Member member) throws SQLException {

		int resultCnt = 0;

		PreparedStatement pstmt = null;

		String sqlInsert = "INSERT INTO member (memberid, password, membername, memberphoto) VALUES (?,?,?,?)";

		/* try { */
		pstmt = conn.prepareStatement(sqlInsert);
		pstmt.setString(1, member.getUserId());
		pstmt.setString(2, member.getPassword());
		pstmt.setString(3, member.getUserName());
		pstmt.setString(4, member.getUserPhoto());

		resultCnt = pstmt.executeUpdate();

		/*		} catch (SQLException e) {
			e.printStackTrace();
		}*/

		return resultCnt;
	}


	// �α����� ���� select
	public Member selectMemberLogin(Connection conn, String uid, String pw) {

		Member member = null;

		String sqlSelect = "SELECT * FROM member where memberid=? and password=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sqlSelect);
			pstmt.setString(1, uid);
			pstmt.setString(2, pw);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				member=makeMember(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return member;		
	}

	// ��ü ����Ʈ�� ��ȯ�ϴ� select
	public List<Member> selectMember(Connection conn){

		List<Member> list = new ArrayList<Member>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from member";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				list.add(makeMember(rs));
			}

			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	private Member makeMember(ResultSet rs) throws SQLException {
		return new Member(
				rs.getString("memberid"),
				rs.getString("password"),
				rs.getString("membername"),
				rs.getString("memberphoto"),
				rs.getTimestamp("regdate")
				);
	}

}
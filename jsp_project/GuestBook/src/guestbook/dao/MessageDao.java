package guestbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import guestbook.model.Message;
import jdbc.JdbcUtil;

public class MessageDao {

	// �̱��� ���� : Dao �ν��Ͻ��� ���� �� ������ �ʿ䰡 ����.
	private MessageDao() {
	}

	private static MessageDao dao = new MessageDao();

	public static MessageDao getInstance() {
		return dao;
	}

	// ���Ͽ� �����͸� ���� �޼ҵ�
	public int insertMessage(Connection conn, Message message) throws SQLException {
		int resultCnt = 0; // ��ȯ�� ��� ��
		PreparedStatement pstmt = null;
		try {
			// �Է��� ���� sql��
			String sql = "INSERT INTO guestbook_message (guest_name, password, message) " + " VALUES (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, message.getGuestName());
			pstmt.setString(2, message.getPassword());
			pstmt.setString(3, message.getMessage());
			resultCnt = pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
		return resultCnt;
	}

	// ��ü �Խù��� ������ ���ϴ� �޼ҵ�
	public int selectAllcount(Connection conn) throws SQLException {

		int totalCnt = 0;

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			String sql = "select count(*) from guestbook_message";
			rs=stmt.executeQuery(sql);

			if(rs.next()) {
				totalCnt = rs.getInt(1);
			}

		} finally {

			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);

		}

		return totalCnt;
	}

	public List<Message> selectList(
			Connection conn, 
			int firstRow, 
			int messageCountPerPage) throws SQLException {

		List<Message> list = new ArrayList<Message>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from guestbook_message order by message_id desc limit ?, ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, firstRow);
			pstmt.setInt(2, messageCountPerPage);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				list.add(makeMessage(rs));
			}

		} finally {

			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);

		}
		return list;
	}

	private Message makeMessage(ResultSet rs) throws SQLException {

		Message message = new Message();
		message.setId(rs.getInt(1));
		message.setGuestName(rs.getString(2));
		message.setPassword(rs.getString(3));
		message.setMessage(rs.getString(4));
		message.setWritedate(rs.getTimestamp(5));
		return message;
	}

}
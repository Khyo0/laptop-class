package guestbook.service;

import java.sql.Connection;
import java.sql.SQLException;

import guestbook.dao.MessageDao;
import guestbook.model.Message;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class WriteMessageService {

	// 教臂沛 菩畔 利侩
	private WriteMessageService() {}
	private static WriteMessageService service = new WriteMessageService();
	public static WriteMessageService getInstance() {
		return service;
	}

	// Message 按眉甫 罐绊
	// Connection 按眉甫 积己
	// MessageDao 按眉甫 积己
	// insertMessage(conn, message)
	public int writeMessage(Message message) {

		int resultCnt=0;

		Connection conn = null;
		MessageDao dao =null;

		try {
			conn = ConnectionProvider.getConnection();
			dao = MessageDao.getInstance();

			resultCnt = dao.insertMessage(conn, message);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}

		return resultCnt;
	}

}
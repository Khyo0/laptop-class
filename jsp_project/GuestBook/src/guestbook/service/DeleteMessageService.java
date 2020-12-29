package guestbook.service;

import java.nio.InvalidMarkException;
import java.sql.Connection;
import java.sql.SQLException;

import guestbook.dao.MessageDao;
import guestbook.exception.InvalidMessagePasswordException;
import guestbook.exception.MessageNotFoundException;
import guestbook.model.Message;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class DeleteMessageService {

	// �̱��� ����
	private DeleteMessageService() {
	}

	private static DeleteMessageService service = new DeleteMessageService();

	public static DeleteMessageService getInstance() {
		return service;
	}

	// �Խù��� ���̵�, ��й�ȣ�� �޾Ƽ� �����ϰ� ����� ��ȯ
	public int deleteMessage(int mid, String pw) 
			throws SQLException, MessageNotFoundException, InvalidMessagePasswordException {
		int resultCnt = 0;

		// Connection, MessageDao,
		// selectMessage(conn, mid) -> Message : �Խù� ���� ����, ��й�ȣ ��ġ üũ
		// deleteMessage(conn, mid) : �Խù� ����

		Connection conn = null;
		MessageDao dao = null;

		try {
			conn = ConnectionProvider.getConnection();

			// autoCommit -> false
			conn.setAutoCommit(false); // default -> true

			dao = MessageDao.getInstance();

			Message message = dao.selectMessage(conn, mid); // Message ��ü, null

			if (message == null) {
				// �޽����� �������� �ʴ´�.
				// ���ܹ߻�
				//throw new Exception("�޽����� �������� �ʽ��ϴ�.");
				throw new MessageNotFoundException();

			} else if (message.getPassword().equals(pw)) {
				// �޽����� �����ϰ� ��й�ȣ�� ����. -> �Խù��� ����
				dao.deleteMessage(conn, mid);

				conn.commit();
			} else {
				// �޽����� ���������� ��й�ȣ�� Ʋ����.
				// ���� �߻�
				//throw new Exception("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				throw new InvalidMessagePasswordException();
			}

		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
			throw e;			
		} catch (MessageNotFoundException e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
			throw e;
		} catch (InvalidMessagePasswordException e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
			throw e;
		} 

		return resultCnt;
	}

}
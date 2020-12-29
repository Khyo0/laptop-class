package guestbook.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import guestbook.dao.MessageDao;
import guestbook.model.Message;
import guestbook.model.MessageListView;
import jdbc.ConnectionProvider;

public class GetMessageListService {

	private final int messageCountPerPage = 3;

	// �̱��� ���� ����
	private GetMessageListService() {}
	private static GetMessageListService service = new GetMessageListService();
	public static GetMessageListService getInstance() {
		return service;
	}

	// MessageListView ��ü�� ��ȯ�ϴ� �޼ҵ�
	// ��������ȣ�� �޾Ƽ� �ش� �������� ����� �����͸� ������ �Ѵ�.
	public MessageListView getMessageList(int pageNumber) {

		MessageListView listView = null;

		// MessageListView �� ������ ������ �����͵��� MessageDao�� �̿��ؼ� ����
		Connection conn = null;
		MessageDao dao = null;

		try {
			conn = ConnectionProvider.getConnection();
			dao = MessageDao.getInstance();

			// �Խù��� ��ü ���� -> ������ ����
			int totalMessageCount = dao.selectAllcount(conn);

			// ���� �������� �޽��� ����Ʈ ���ϱ�
			List<Message> messageList = null;
			int firstRow = 0;
			int endRow = 0;

			firstRow = (pageNumber-1)*messageCountPerPage;
			endRow = firstRow+messageCountPerPage-1;

			messageList = dao.selectList(conn, firstRow, messageCountPerPage);

			listView = new MessageListView(totalMessageCount, pageNumber, messageList, messageCountPerPage, firstRow, endRow);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listView;
	}

}
package jdbc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

// ������ ���̽� ����̹� �ε�
public class Loader extends HttpServlet {

	@Override
	public void init() throws ServletException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("�Խ�Ʈ�� : Mysql ����̹� �ε� ����");
		} catch (ClassNotFoundException e) {
			System.out.println("�Խ�Ʈ�� : Mysql ����̹� �ε� ����");
			e.printStackTrace();
		}

	}

}
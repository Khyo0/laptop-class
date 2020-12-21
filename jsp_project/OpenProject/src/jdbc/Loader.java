package jdbc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class Loader extends HttpServlet {

	// ���� ��ü�� ������ �� ó�� �� �� �������ִ� �޼ҵ�
	// ������ web.xml�� ����ϰ� �����̳ʰ� ������ �� �� �� ������ �����ϵ��� ����
	@Override
	public void init() throws ServletException {

		// Mysql�� ����̹��� �ε�
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Mysql ����̹� �ε� ����");
		} catch (ClassNotFoundException e) {
			System.out.println("Mysql ����̹� �ε� ����");
			e.printStackTrace();
		}
	}
	
}
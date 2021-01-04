package jdbc;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class Loader extends HttpServlet {

	// ���� ��ü�� �����Ҷ� ó�� �ѹ� �������ִ� �޼ҵ�
	// ������ web.xml�� ����ϰ� �����̳ʰ� ������ �� �ѹ� ������ �����ϵ��� ����
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {

		String driver = servletConfig.getInitParameter("driver");
		System.out.println(driver);

		if(driver == null) {
			driver = "com.mysql.cj.jdbc.Driver";
		}

		// Mysql�� ����̹��� �ε�
		try {
			//Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName(driver);
			System.out.println("openProject MVC : Mysql ����̹� �ε� ����!");

		} catch (ClassNotFoundException e) {
			System.out.println("Mysql ����̹� �ε� ����!");
			e.printStackTrace();
		}
	}
}
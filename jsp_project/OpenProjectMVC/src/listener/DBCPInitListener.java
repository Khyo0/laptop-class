package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DBCPInitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {

		ServletContext context = contextEvent.getServletContext();

		String driver = context.getInitParameter("jdbcDriver");

		try {
			Class.forName(driver);
			System.out.println("tomcat Listener ���� �����ͺ��̽� ����̹� �ε� ����");
		} catch (ClassNotFoundException e) {
			System.out.println("tomcat Listener ���� �����ͺ��̽� ����̹� �ε� ����");
			e.printStackTrace();
		}
		System.out.println("�ʱ�ȭ �Ϸ�.");
	}

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
	}
}
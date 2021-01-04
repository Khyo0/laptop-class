package mvc.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.service.MemberService;

@WebServlet(
		urlPatterns = { "*.do" }, 
		initParams = { 
				@WebInitParam(name = "config", 
						value = "/WEB-INF/commandService.propertise")
		},
		loadOnStartup=1
		)
public class FrontController extends HttpServlet {

	private Map<String, MemberService> commands = new HashMap<String, MemberService>();

	public void init(ServletConfig config) throws ServletException {
		
		String configfile = config.getInitParameter("config");
		
		Properties prop = new Properties();
		
		FileInputStream fis = null;
		String configFilePath = config.getServletContext().getRealPath(configfile);
		
		System.out.println(configFilePath);

		try {
			fis = new FileInputStream(configFilePath);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Iterator itr = prop.keySet().iterator();
		
		while (itr.hasNext()) {
			String command = (String) itr.next(); // ����� ��û URI
			String serviceClassName = prop.getProperty(command); // ���� Ŭ���� �̸�

			System.out.println(command + ":" + serviceClassName);
		}

		while (itr.hasNext()) {
			String command = (String) itr.next(); // ����� ��û URI
			String serviceClassName = prop.getProperty(command); // ���� Ŭ���� �̸�

			System.out.println(command + ":" + serviceClassName);

			// commands Map �� ���� <String, GuestBookService>

			// prop �� �ִ� Ŭ���� �̸����� �ν��Ͻ� ����

			try {
				Class serviceClass = Class.forName(serviceClassName);
				// ��ü ����
				MemberService service = (MemberService) serviceClass.newInstance();

				// commands Map �� ���� <String, GuestBookService>
				commands.put(command, service);

				System.out.println(command + " : " + service);

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. ����� ��û �м�

		String commandUri = request.getRequestURI(); // /guest/guestWriteForm

		if (commandUri.indexOf(request.getContextPath()) == 0) {
			commandUri = commandUri.substring(request.getContextPath().length());
		}

		System.out.println(commandUri);

		request.setCharacterEncoding("utf-8");

		// 2. ����� ��û�� �´� �� ���� ( ����.�޼��� ���� ) -> view ������ ��ȯ

		String viewPage = "/WEB-INF/view/null.jsp";

		MemberService service = commands.get(commandUri); // null ���� ��ȯ�ϱ⵵ �Ѵ�.
		if (service != null) {
			viewPage = service.getViewName(request, response);
		}

		// 3. view �� ������

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);

	}

}
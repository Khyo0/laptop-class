package mvc.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleController extends HttpServlet {

	// 1. ��� ��û�� �޵��� ó��
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	// ��� ��û�� ó���а� view �������� ������
	private void processRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		// 2. ��û�� �м� : �Ķ���͸� �̿��ؼ� ��û�� �м�
		// request ��ü�� �̿��ؼ� ������� ��û�� �м�
		// http://localhost:8080/mvc/simple?type=[ greeting | date | ....]
		String type = request.getParameter("type");

		// 3. ��û�� �´� ��� ���� -> ��� ������
		Object resultObj = null;

		if (type == null || type.equals("greeting")) {
			resultObj = "�ȳ��ϼ���";
		} else if (type.equals("date")) {
			resultObj = new Date();
		} else {
			resultObj = "Invalid Type";
		}

		// 4. ��� �����͸� �⺻ ��ü�� �Ӽ��� �����ؼ� View �������� �����͸� ����
		request.setAttribute("result", resultObj);

		// 5. View �������� �����ϰ�, forward
		RequestDispatcher dispatcher = request.getRequestDispatcher("/simpleView.jsp");
		dispatcher.forward(request, response);

	}

}
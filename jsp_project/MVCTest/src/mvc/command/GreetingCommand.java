package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GreetingCommand extends Command {

	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {
		// ������� ��û�� ó��, ���信 �ʿ��� �����͸� request �Ӽ��� ����, ���信 �ʿ��� ����
		// �� ��û�� ���� ó���� ���� view ������ ��θ� ��ȯ
		//String viewPage = null;

		// ����� ��û�� ó��
		String result = "ȯ���մϴ�. ��û�Ͻ� ��δ� " + request.getRequestURI();

		// request �Ӽ��� ����� ����
		request.setAttribute("result", result);

		return "/view/greeting.jsp";
	}

}
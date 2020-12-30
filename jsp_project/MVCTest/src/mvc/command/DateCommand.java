package mvc.command;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DateCommand extends Command {

	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {
		// ������� ��û�� ó��, ���信 �ʿ��� �����͸� request �Ӽ��� ����, ���信 �ʿ��� ����
		// �� ��û�� ���� ó���� ���� view ������ ��θ� ��ȯ
		//String viewPage = null;

		// ����� ��û�� ó��
		Date result = new Date();

		// request �Ӽ��� ����� ����
		request.setAttribute("result", result);

		return "/view/date.jsp";
	}

}
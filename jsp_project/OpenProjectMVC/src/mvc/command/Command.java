package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

	// ��û�� ó��, �Ӽ��� ����� ����, view �������� ��θ� ��ȯ
	String getViewPage(HttpServletRequest request, HttpServletResponse response);

}
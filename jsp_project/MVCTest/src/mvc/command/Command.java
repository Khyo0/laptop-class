package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {

	// ��û�� ó��, �Ӽ��� ����� ����, view �������� ��θ� ��ȯ
	abstract public String getViewPage(HttpServletRequest request, HttpServletResponse response);

}
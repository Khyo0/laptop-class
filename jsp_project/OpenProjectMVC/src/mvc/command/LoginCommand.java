package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.MemberLoginService;

public class LoginCommand implements Command {

	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {

		// ����� ��û�� �´� ���� Ŭ������ �ν��Ͻ� ���� -> �޼ҵ� ����
		// ����� ��û�� �´� View�� �����ؼ� Forward
		MemberLoginService service = MemberLoginService.getInstance();
		service.memberLogin(request, response);

		return "/WEB-INF/view/member/loginView.jsp";
	}

}
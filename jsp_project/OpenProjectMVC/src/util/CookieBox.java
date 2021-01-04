package util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

// ��Ű ��ü�� �����ϰ�, �����ϰ�, ����� ��Ű�� ���� ���� ��� 
public class CookieBox {

	// Cookie ��ü�� �����ϴ� Map ��ü�� ����
	Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();

	// �ʱ�ȭ : cookieMap�� Cookie �����͸� ����
	// ������
	public CookieBox(HttpServletRequest request) {
		// request�� ���� Cookie ������ ���� �� �ִ�.
		Cookie[] cookies = request.getCookies();

		if(cookies!=null && cookies.length>0) {
			for(int i=0; i<cookies.length; i++) {
				// cookieMap�� Cookie ��ü�� ����
				cookieMap.put(cookies[i].getName(), cookies[i]);
			}
		}
	}

	// �̸����� ��Ű ��ü�� ��ȯ
	public Cookie getCookie(String name) {
		return cookieMap.get(name);		
	}

	// �̸����� ��Ű�� ���� ���� ��ȯ
	public String getValue(String name) throws IOException {

		Cookie cookie = cookieMap.get(name);// Map�� name �� Ű�� ���� ��� null ��ȯ�� ����

		if(cookie==null) {
			return null;
		}
		return URLDecoder.decode(cookie.getValue(), "utf-8");		
	}

	// cookieMap �� Ư�� �̸��� ��Ű�� �����ϴ��� ���� Ȯ��
	public boolean exists(String name) {
		return cookieMap.get(name) != null;
	}

	// ��Ű ��ü�� �������ִ� �޼ҵ� : ��ü�� �������� �ʰ� ����� �� �ִ� �޼ҵ�� ���� : static ����� ����
	// �̸�, ���� ������ Cookie ��ü ���� 
	public static Cookie createCookie(String name, String value) {
		Cookie cookie = new Cookie(name, value);
		return cookie;
	}

	// �̸�, ��, ���, ���� �ð��� ������ Cookie��ü�� ����
	public static Cookie createCookie(String name, String value, String path, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath(path);  // ��� ����    
		cookie.setMaxAge(maxAge); // �����ð� ����
		return cookie;
	}

	// �̸�, ��, ���, ���� �ð�, �������� ������ Cookie��ü�� ����
	public static Cookie createCookie(
			String name, 
			String value, 
			String path, 
			int maxAge, 
			String domain) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath(path);  // ��� ����    
		cookie.setMaxAge(maxAge); // �����ð� ����
		cookie.setDomain(domain); // ������ ����
		return cookie;
	}

}
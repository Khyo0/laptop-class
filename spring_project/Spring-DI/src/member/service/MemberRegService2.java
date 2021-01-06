
package member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import member.dao.Dao;

public class MemberRegService2 implements MemberService  {

	@Autowired(required = false)
	@Qualifier("member")
	private Dao dao; // 주입 받아야 하는 참조 변수

	@Override
	public Object process() {
		System.out.println("MemberRegService 실행");
		dao.insert();
		return null;
	}

}
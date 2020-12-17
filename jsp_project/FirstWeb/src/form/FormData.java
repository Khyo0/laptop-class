package form;

import java.util.Arrays;

// Form ���� ���۵Ǵ� �����͸� �����ϴ� beans ����
public class FormData {

	// �Ӽ� : ������ private
	private String name; // ������� �̸� name=null
	private String job; // ������� ���� job=null
	private String[] interest; // ���ɻ���

	// beans �� �����̳ʰ� ��ü�� �����ϱ� ������ �⺻ �����ڴ� �ʼ�
	public FormData() {
	}

	public FormData(String name, String job, String[] interest) {
		this.name = name;
		this.job = job;
		this.interest = interest;
	}

	// �� �������� getter/setter �޼ҵ带 ����
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String[] getInterest() {
		return interest;
	}

	public void setInterest(String[] interest) {
		this.interest = interest;
	}

	@Override
	public String toString() {
		return "FormData [name=" + name + ", job=" + job + ", interest=" + Arrays.toString(interest) + "]";
	}

}
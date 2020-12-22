package member;

// beans ����
public class Member {

	// ���̵�, ���, �̸�, ����
	private String userId; // ȸ���� ���̵�
	private String pw; // ȸ���� ��й�ȣ
	private String userName; // ȸ���� �̸�
	private String photo; // ȸ���� ������ ����

	// �⺻ ������ �ʼ�
	public Member() {
	}

	public Member(String userId, String pw, String userName, String photo) {
		this.userId = userId;
		this.pw = pw;
		this.userName = userName;
		this.photo = photo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", pw=" + pw + ", userName=" + userName + ", photo=" + photo + "]";
	}

}
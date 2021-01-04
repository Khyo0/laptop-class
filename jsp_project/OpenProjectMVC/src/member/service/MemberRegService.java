package member.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class MemberRegService {

	// �̱��� ����
	private MemberRegService() {
	}

	private static MemberRegService service = new MemberRegService();

	public static MemberRegService getInstance() {
		return service;
	}

	// ������� ��û�� �޾Ƽ� ���� ���ε�, �����ͺ��̽��� �����͸� ����
	public int insertMember(HttpServletRequest request) {

		int resultCnt = 0;

		// DAO ��ü�� insert �޼ҵ�� member�������� ����,
		// Connection ��ü�� �������� ����
		Connection conn = null;

		// MemberDao dao = new MemberDao();
		MemberDao dao = null;

		Member member = null;

		try {
			conn = ConnectionProvider.getConnection();
			dao = MemberDao.getInstance();

			if (conn != null) {

				// ���� �Է��� ����� �Է� �������� �ѱ� ó��
				// request.setCharacterEncoding("utf-8");

				String userId = null;
				String pw = null;
				String userName = null;
				String userPhoto = null;

				// /upload/member
				String dir = request.getSession().getServletContext().getRealPath("/upload/member");

				// FileUpload ���̺귯���� �̿��ؼ� DB�� �Է��� �����͸� �޾ƿ;��Ѵ�.
				if (ServletFileUpload.isMultipartContent(request)) {

					DiskFileItemFactory factory = new DiskFileItemFactory();
					ServletFileUpload upload = new ServletFileUpload(factory);
					List<FileItem> items = upload.parseRequest(request);

					Iterator<FileItem> itr = items.iterator();

					while (itr.hasNext()) {

						FileItem item = itr.next();

						// type=file ���� Ȯ�� -> ���� �ʵ��� ���� ����
						if (item.isFormField()) { // �Ϲ� �ʵ�

							String fName = item.getFieldName();

							if (fName.equals("userid")) {
								userId = item.getString("utf-8");
							}
							if (fName.equals("pw")) {
								pw = item.getString("utf-8");
							}
							if (fName.equals("username")) {
								userName = item.getString("utf-8");
							}

						} else { // file �ʵ�

							if (item.getFieldName().equals("userPhoto") && !item.getName().isEmpty()
									&& item.getSize() > 0) {

								// ��� �����ϴ� File ��ü�� ����
								File saveFilePath = new File(dir);

								// ������ �����ϴ��� ����
								if (!saveFilePath.exists() || !saveFilePath.isDirectory()) {
									saveFilePath.mkdir();
								}

								// a ����ڰ� photo.jpg b ����ڰ� photo.jpg
								// mini.jpg --> {"mini","jpg"}
								// ���ο� ���� �̸� : �ߺ��ϴ� ������ �̸��� ������ ����� ->
								System.out.println(item.getName());
								String newFileName = System.nanoTime() + "." + item.getName().split("\\.")[1];

								// ���� ����
								item.write(new File(saveFilePath, newFileName));
								// DB �� ������ ���� �̸�
								userPhoto = newFileName;
							}
						}
					}

					member = new Member();
					member.setUserId(userId);
					member.setPassword(pw);
					member.setUserName(userName);
					member.setUserPhoto(userPhoto);

					System.out.println(member);

					try {
						// DB�� ������ ����
						resultCnt = dao.insertMember(conn, member);
						// SQLException -> DB ���� �ȵȴ�. ������ ������ �̹� ������ �Ǿ��ִ�.
					} catch (Exception e) {
						System.out.println("����~!!!!");
						File delFile = new File(dir, userPhoto);
						System.out.println(delFile.exists());
						if (delFile.exists()) {
							// ������ ����
							delFile.delete();
						}

					}

				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("result", resultCnt);
		request.setAttribute("member", member);

		return resultCnt;

	}
}
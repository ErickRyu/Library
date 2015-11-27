package Control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.Book;
import Model.Member;

public class MemberManager {
	
	public static Member loginUser;			//���� �α����� ȸ���� �����ϱ� ���� MemberŸ�� ����
	
	public static ArrayList<Member> mAList = new ArrayList();	//������ ȸ������ �����ϱ� ���� ArrayList

	FileOutputStream fout = null;
	ObjectOutputStream oos = null;
	FileInputStream fin = null;
	ObjectInputStream ois = null;

	
	//ȸ������
	public int join(String id, String pw, String name, String birth,
			String phone) {
		int res = 0;
		if (id != null && pw != null && name != null && birth != null
				&& phone != null) {
			Member memb = new Member();
			for (Member m : mAList) {
				if (m.id.equals(id)) {
					return res;
				}
			}
			memb.id = id;
			memb.pw = pw;
			memb.name = name;
			memb.birth = Integer.parseInt(birth);
			memb.phone = phone;

			mAList.add(memb);
			res = 1;

		}
		return res;
	}
	
	
	//ȸ������
	public int join(Member mb) {
		int res = 0;
			mAList.add(mb);
			res = 1;
		return res;
	}

	//�α���
	public int Login(String id, String pw) {
		int res = 0;
		
		for(Member m : mAList){
			if(m.id.equals(id) && m.pw.equals(pw)){
				loginUser = m;
				res = 1;
				DateManager dMng = new DateManager();
				dMng.updateLatePay(loginUser);
			}
		}
		return res;
	}
	
	//ȸ���� �� ��ü�Ḧ ���� ȸ������ �����ϱ����� �Լ�
	public ArrayList<Member> lateList(){
		ArrayList<Member> latePeople = new ArrayList();
		for(Member m : mAList){
			if(m.latePay > 0){
				latePeople.add(m);
			}
		}
		return latePeople;
	}

	//���ArrayList�� ������ dat���� ����
	public void fileOpen() {
		try {
			
			
			fin = new FileInputStream("memberlist.dat");
			ois = new ObjectInputStream(fin);

			ArrayList list = (ArrayList) ois.readObject();
			mAList = new ArrayList<Member>();

			for (int i = 0; i < list.size(); i++)
				mAList.add((Member) list.get(i));

			sendMsg("memberlist.dat������ �ҷ����µ� �����߽��ϴ�.");
		} catch (Exception ex) {
			sendMsg("�ҷ����⿡ �����߽��ϴ�.");
			ex.printStackTrace();
		} finally {
			try {
				ois.close();
				fin.close();
			} catch (IOException ioe) {
			}
		} // finally

	}

	//���ArrayList�� ����
	public void fileSave() {
		try {
			fout = new FileOutputStream("memberlist.dat");
			oos = new ObjectOutputStream(fout);

			oos.writeObject(mAList);
			oos.close();

			sendMsg("����Ǿ����ϴ�.");
		} catch (Exception ex) {
			sendMsg("���忡 �����߽��ϴ�.");
			ex.printStackTrace();
		} finally {
			try {
				oos.close();
				fout.close();
			} catch (IOException ioe) {
			}
		} // finally
	}

	//�޽��� ����
	public void sendMsg(String msg){
		JOptionPane.showMessageDialog(null, msg, "Message", JOptionPane.INFORMATION_MESSAGE);
	}
}

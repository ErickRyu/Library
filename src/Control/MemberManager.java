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
	
	public static Member loginUser;			//현재 로그인한 회원을 저장하기 위한 Member타입 변수
	
	public static ArrayList<Member> mAList = new ArrayList();	//가입한 회원들을 저장하기 위한 ArrayList

	FileOutputStream fout = null;
	ObjectOutputStream oos = null;
	FileInputStream fin = null;
	ObjectInputStream ois = null;

	
	//회원가입
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
	
	
	//회원가입
	public int join(Member mb) {
		int res = 0;
			mAList.add(mb);
			res = 1;
		return res;
	}

	//로그인
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
	
	//회원등 중 연체료를 가진 회원들을 리턴하기위한 함수
	public ArrayList<Member> lateList(){
		ArrayList<Member> latePeople = new ArrayList();
		for(Member m : mAList){
			if(m.latePay > 0){
				latePeople.add(m);
			}
		}
		return latePeople;
	}

	//멤버ArrayList를 저장한 dat파일 열기
	public void fileOpen() {
		try {
			
			
			fin = new FileInputStream("memberlist.dat");
			ois = new ObjectInputStream(fin);

			ArrayList list = (ArrayList) ois.readObject();
			mAList = new ArrayList<Member>();

			for (int i = 0; i < list.size(); i++)
				mAList.add((Member) list.get(i));

			sendMsg("memberlist.dat파일을 불러오는데 성공했습니다.");
		} catch (Exception ex) {
			sendMsg("불러오기에 실패했습니다.");
			ex.printStackTrace();
		} finally {
			try {
				ois.close();
				fin.close();
			} catch (IOException ioe) {
			}
		} // finally

	}

	//멤버ArrayList를 저장
	public void fileSave() {
		try {
			fout = new FileOutputStream("memberlist.dat");
			oos = new ObjectOutputStream(fout);

			oos.writeObject(mAList);
			oos.close();

			sendMsg("저장되었습니다.");
		} catch (Exception ex) {
			sendMsg("저장에 실패했습니다.");
			ex.printStackTrace();
		} finally {
			try {
				oos.close();
				fout.close();
			} catch (IOException ioe) {
			}
		} // finally
	}

	//메시지 전송
	public void sendMsg(String msg){
		JOptionPane.showMessageDialog(null, msg, "Message", JOptionPane.INFORMATION_MESSAGE);
	}
}

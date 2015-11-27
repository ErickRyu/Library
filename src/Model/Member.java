package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Member implements Serializable{
	public String id;											//로그인용 아이디
	public String pw;											//로그인용 비밀번호
	public String name;											//이름
	public int birth;											//생일
	public String phone;										//전화번호
	public String isSupervisor;									//관리자인지 체크(Y/N)
	public int latePay;											//연체료
	public ArrayList<Book> borrowedBook = new ArrayList();		//자신이 빌린 책 목록
}

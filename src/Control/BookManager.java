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
import UI.SearchPanel;

public class BookManager {
	
	
	//똑같은 도서가 여러개 있을 경우를 대비해 bookID를 지정하고 insert시 1씩 증가시킴
	public static int bookId;
	
	//도서목록 저장을 위한 ArrayList
	public static ArrayList<Book> bList = new ArrayList();

	//JTable column 이름들을 final로 저장
	public final String colNames[] = { "도서ID", "ISBN", "책 제목", "저자", "출판년도",
			"출판사", "대출여부" };

	FileOutputStream fout = null;
	ObjectOutputStream oos = null;
	FileInputStream fin = null;
	ObjectInputStream ois = null;

	
	//도서추가
	public int insert(String isbn, String title, String author, String year,
			String publisher, String isBorrowed) {
		Book book = new Book();
		book.isbn = isbn;
		book.title = title;
		book.author = author;
		book.year = year;
		book.publisher = publisher;
		book.isBorrowed = isBorrowed;
		book.bookId = bookId++;
		bList.add(book);
		return 1;
	}
	
	
	//도서파일 열기
	public void fileOpen() {
		// toDo
		try {
			fin = new FileInputStream("booklist.dat");
			ois = new ObjectInputStream(fin);

			ArrayList list = (ArrayList) ois.readObject();
			bList = new ArrayList<Book>();

			for (int i = 0; i < list.size(); i++)
				bList.add((Book) list.get(i));

			sendMsg("booklist.dat파일을 불러오는데 성공했습니다.");
		} catch (Exception ex) {
			sendMsg("불러오기에 실패했습니다.");
		} finally {
			try {
				ois.close();
				fin.close();
			} catch (IOException ioe) {
			}
		} // finally
	}

	
	//도서파일 저장
	public void fileSave() {
		try {
			fout = new FileOutputStream("booklist.dat");
			oos = new ObjectOutputStream(fout);

			oos.writeObject(bList);
			oos.reset();

			sendMsg("booklist.dat파일이 성공적으로 저장되었습니다.");
		} catch (Exception ex) {
			sendMsg("파일 저장에 실패했습니다.");
		} finally {
			try {
				oos.close();
				fout.close();
			} catch (IOException ioe) {
			}
		} // finally
	}

	
	public ArrayList<Book> searchBook(int type, String toSearch) { // 도서 검색
		ArrayList<Book> books = new ArrayList();
		switch (type) {
		case (0):
			// 제목검색
			for (Book b : bList) {
				if (b.title.equals(toSearch)) {
					books.add(b);
				}
			}
			break;
		case (1):
			// isbn검색
			for (Book b : bList) {
				if (b.isbn.equals(toSearch)) {
					books.add(b);
				}
			}
			break;
		case (2):
			// 저자검색
			for (Book b : bList) {
				if (b.author.equals(toSearch)) {
					books.add(b);
				}
			}
			break;
		case (3):
			// 전체도서 검색
			books = bList;

			break;
		case (4):
			// 내가 빌린 도서 목록
			books = MemberManager.loginUser.borrowedBook;
			break;
		}
		return books;
	}

	
	//도서 대출
	public void borrowBook(int bookId) {

		for (Book b : bList) {
			if (b.bookId == bookId) {		//자신이 빌리고자 하는 도서의ID와 일치하는 도서찾기
				MemberManager mMng = new MemberManager();
				DateManager dMng = new DateManager();
				MemberManager.loginUser.borrowedBook.add(b);
				b.isBorrowed = "Y";
				b.due = dMng.dueTo();
				break;
			}
		}
	}
	
	//도서반납
	public void backBook(Book toBack) {
		int index = -1;
		for(Book b : MemberManager.loginUser.borrowedBook){
			index++;
			
			if(toBack == b){		//자신이 빌린 책이 맞는지 확인
				MemberManager.loginUser.latePay -= toBack.latePay;			//로그인한 회원이 가지고 있던 전체 연체료에서 반납하는 도서의 연체로를 차감
				toBack.isBorrowed = "N";		//해당 도서의 대여 여부를 N으로 변경
				toBack.latePay = 0;				//해당 도서에 저장 되어 있던 연체료를 0을로 설정
				break;
			}
		}
		MemberManager.loginUser.borrowedBook.remove(index);
	}
	
	
	//메시지 전송
	public void sendMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Message",
				JOptionPane.INFORMATION_MESSAGE);
	}
}

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
	
	
	//�Ȱ��� ������ ������ ���� ��츦 ����� bookID�� �����ϰ� insert�� 1�� ������Ŵ
	public static int bookId;
	
	//������� ������ ���� ArrayList
	public static ArrayList<Book> bList = new ArrayList();

	//JTable column �̸����� final�� ����
	public final String colNames[] = { "����ID", "ISBN", "å ����", "����", "���ǳ⵵",
			"���ǻ�", "���⿩��" };

	FileOutputStream fout = null;
	ObjectOutputStream oos = null;
	FileInputStream fin = null;
	ObjectInputStream ois = null;

	
	//�����߰�
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
	
	
	//�������� ����
	public void fileOpen() {
		// toDo
		try {
			fin = new FileInputStream("booklist.dat");
			ois = new ObjectInputStream(fin);

			ArrayList list = (ArrayList) ois.readObject();
			bList = new ArrayList<Book>();

			for (int i = 0; i < list.size(); i++)
				bList.add((Book) list.get(i));

			sendMsg("booklist.dat������ �ҷ����µ� �����߽��ϴ�.");
		} catch (Exception ex) {
			sendMsg("�ҷ����⿡ �����߽��ϴ�.");
		} finally {
			try {
				ois.close();
				fin.close();
			} catch (IOException ioe) {
			}
		} // finally
	}

	
	//�������� ����
	public void fileSave() {
		try {
			fout = new FileOutputStream("booklist.dat");
			oos = new ObjectOutputStream(fout);

			oos.writeObject(bList);
			oos.reset();

			sendMsg("booklist.dat������ ���������� ����Ǿ����ϴ�.");
		} catch (Exception ex) {
			sendMsg("���� ���忡 �����߽��ϴ�.");
		} finally {
			try {
				oos.close();
				fout.close();
			} catch (IOException ioe) {
			}
		} // finally
	}

	
	public ArrayList<Book> searchBook(int type, String toSearch) { // ���� �˻�
		ArrayList<Book> books = new ArrayList();
		switch (type) {
		case (0):
			// ����˻�
			for (Book b : bList) {
				if (b.title.equals(toSearch)) {
					books.add(b);
				}
			}
			break;
		case (1):
			// isbn�˻�
			for (Book b : bList) {
				if (b.isbn.equals(toSearch)) {
					books.add(b);
				}
			}
			break;
		case (2):
			// ���ڰ˻�
			for (Book b : bList) {
				if (b.author.equals(toSearch)) {
					books.add(b);
				}
			}
			break;
		case (3):
			// ��ü���� �˻�
			books = bList;

			break;
		case (4):
			// ���� ���� ���� ���
			books = MemberManager.loginUser.borrowedBook;
			break;
		}
		return books;
	}

	
	//���� ����
	public void borrowBook(int bookId) {

		for (Book b : bList) {
			if (b.bookId == bookId) {		//�ڽ��� �������� �ϴ� ������ID�� ��ġ�ϴ� ����ã��
				MemberManager mMng = new MemberManager();
				DateManager dMng = new DateManager();
				MemberManager.loginUser.borrowedBook.add(b);
				b.isBorrowed = "Y";
				b.due = dMng.dueTo();
				break;
			}
		}
	}
	
	//�����ݳ�
	public void backBook(Book toBack) {
		int index = -1;
		for(Book b : MemberManager.loginUser.borrowedBook){
			index++;
			
			if(toBack == b){		//�ڽ��� ���� å�� �´��� Ȯ��
				MemberManager.loginUser.latePay -= toBack.latePay;			//�α����� ȸ���� ������ �ִ� ��ü ��ü�ῡ�� �ݳ��ϴ� ������ ��ü�θ� ����
				toBack.isBorrowed = "N";		//�ش� ������ �뿩 ���θ� N���� ����
				toBack.latePay = 0;				//�ش� ������ ���� �Ǿ� �ִ� ��ü�Ḧ 0���� ����
				break;
			}
		}
		MemberManager.loginUser.borrowedBook.remove(index);
	}
	
	
	//�޽��� ����
	public void sendMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Message",
				JOptionPane.INFORMATION_MESSAGE);
	}
}

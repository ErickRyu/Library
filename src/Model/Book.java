package Model;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable{
	public String isbn;				//ISBN
	public String title;			//제목
	public String author;			//저자
	public String year;				//출판년도
	public String publisher;		//출판사
	public String isBorrowed;		//대출여부 (Y/N)
	public int bookId;				//동일 책 구별을 위한 ID
	public String due;				//반납예정일
	public int latePay;				//해당 도서의 연체료
}

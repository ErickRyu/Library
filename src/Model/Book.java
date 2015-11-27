package Model;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable{
	public String isbn;				//ISBN
	public String title;			//����
	public String author;			//����
	public String year;				//���ǳ⵵
	public String publisher;		//���ǻ�
	public String isBorrowed;		//���⿩�� (Y/N)
	public int bookId;				//���� å ������ ���� ID
	public String due;				//�ݳ�������
	public int latePay;				//�ش� ������ ��ü��
}

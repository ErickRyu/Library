package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Member implements Serializable{
	public String id;											//�α��ο� ���̵�
	public String pw;											//�α��ο� ��й�ȣ
	public String name;											//�̸�
	public int birth;											//����
	public String phone;										//��ȭ��ȣ
	public String isSupervisor;									//���������� üũ(Y/N)
	public int latePay;											//��ü��
	public ArrayList<Book> borrowedBook = new ArrayList();		//�ڽ��� ���� å ���
}

package Control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Model.Book;
import Model.Member;

public class DateManager {
	SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
	
	
	//���� �ݳ� ������(7��) ����
	public String dueTo() {
		Date today = new Date();
		Date due = new Date(today.getTime() + (long) (1000 * 60 * 60 * 24 * 7));

		return sdf.format(due);
	}

	
	//���� ��¥ ����
	public String today() {
		Date today = new Date();
		return sdf.format(today);
	}
	
	
	//�α����� ȸ���� ��ü�� ������Ʈ
	public void updateLatePay(Member mb) {
		Date Today = new Date();
		int lateDay = 0;
		mb.latePay = 0;
		for (Book b : mb.borrowedBook) {
			try {
				if(b.due != null){
					long late = Today.getTime() - sdf.parse(b.due).getTime();
					
					if (late > 0) {
						lateDay = (int)(late)/(1000*60*60*24);
						b.latePay = lateDay * 100;						//��ü�� 1�ϴ� 100��
						mb.latePay += b.latePay;						//������ ��ü�� �ջ�
					}
				}
			} catch (ParseException e) {
				continue;
			}
		}
	}
	
	
	
	//��ü ȸ���� ���� ��ü�� ������Ʈ
	public void updateLatePay() {
		Date Today = new Date();
		int lateDay = 0;
		MemberManager mMng = new MemberManager();
		
		for(Member m : mMng.mAList){
			for (Book b : m.borrowedBook) {
				try {
					if(b.due != null){
						long late = Today.getTime() - sdf.parse(b.due).getTime();
						
						if (late > 0) {
							m.latePay = 0;
							lateDay = (int)(late)/(1000*60*60*24);
							b.latePay = lateDay * 100;						//��ü�� 1�ϴ� 100��
							m.latePay += b.latePay;							//������ ��ü�� �ջ�
						}
					}
				} catch (ParseException e) {
					continue;
				}
			}
		}
	}

}

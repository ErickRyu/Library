package Control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Model.Book;
import Model.Member;

public class DateManager {
	SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
	
	
	//도서 반납 예정일(7일) 리턴
	public String dueTo() {
		Date today = new Date();
		Date due = new Date(today.getTime() + (long) (1000 * 60 * 60 * 24 * 7));

		return sdf.format(due);
	}

	
	//오늘 날짜 리턴
	public String today() {
		Date today = new Date();
		return sdf.format(today);
	}
	
	
	//로그인한 회원의 연체료 업데이트
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
						b.latePay = lateDay * 100;						//연체료 1일당 100원
						mb.latePay += b.latePay;						//도서당 연체료 합산
					}
				}
			} catch (ParseException e) {
				continue;
			}
		}
	}
	
	
	
	//전체 회원에 관해 연체료 업데이트
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
							b.latePay = lateDay * 100;						//연체료 1일당 100원
							m.latePay += b.latePay;							//도서당 연체료 합산
						}
					}
				} catch (ParseException e) {
					continue;
				}
			}
		}
	}

}

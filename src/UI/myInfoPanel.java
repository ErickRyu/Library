package UI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Control.DateManager;
import Control.MemberManager;
import Model.Book;

//회원정보를 출력하기위한 프레임
public class myInfoPanel extends JFrame{
	myInfoPanel(){
		setSize(400,400);
		JPanel panel = new JPanel(new BorderLayout(30,30));
		JTextArea jta = new JTextArea();
		jta.setEditable(false);

		MemberManager mMng = new MemberManager();
		DateManager dMng = new DateManager();
		jta.setText("");

		jta.append("이 름 \t");
		jta.append(mMng.loginUser.name + "\n");
		jta.append("생 일 \t");
		jta.append(mMng.loginUser.birth + "\n");
		jta.append("전 화 \t");
		jta.append(mMng.loginUser.phone + "\n");
		jta.append("연체료 \t");
		jta.append(mMng.loginUser.latePay + "\n");
		jta.append("대출도서 목록 \n\n");
		jta.append("제목\t\t반납예정일\t연체료\n");
		for(Book b : mMng.loginUser.borrowedBook){
			jta.append(b.title + "\t\t" + b.due + "\t" + b.latePay + "\n");
		}
		panel.add(jta);
		add(panel);
		setVisible(true);
	}
}

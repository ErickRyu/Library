package UI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Control.DateManager;
import Control.MemberManager;
import Model.Book;

//ȸ�������� ����ϱ����� ������
public class myInfoPanel extends JFrame{
	myInfoPanel(){
		setSize(400,400);
		JPanel panel = new JPanel(new BorderLayout(30,30));
		JTextArea jta = new JTextArea();
		jta.setEditable(false);

		MemberManager mMng = new MemberManager();
		DateManager dMng = new DateManager();
		jta.setText("");

		jta.append("�� �� \t");
		jta.append(mMng.loginUser.name + "\n");
		jta.append("�� �� \t");
		jta.append(mMng.loginUser.birth + "\n");
		jta.append("�� ȭ \t");
		jta.append(mMng.loginUser.phone + "\n");
		jta.append("��ü�� \t");
		jta.append(mMng.loginUser.latePay + "\n");
		jta.append("���⵵�� ��� \n\n");
		jta.append("����\t\t�ݳ�������\t��ü��\n");
		for(Book b : mMng.loginUser.borrowedBook){
			jta.append(b.title + "\t\t" + b.due + "\t" + b.latePay + "\n");
		}
		panel.add(jta);
		add(panel);
		setVisible(true);
	}
}

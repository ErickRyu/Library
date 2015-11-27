package UI;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Model.Member;


//연체회원 목록을 띄우기 위한 프레임
public class latePeoplePanel extends JFrame{
	latePeoplePanel(ArrayList<Member> mL){
		setSize(400,400);
		JPanel panel = new JPanel(new BorderLayout());
		JTextArea jta = new JTextArea();
		jta.append("이름\t생일\t전화번호\t연체료\n");
		for(Member m : mL){
			jta.append(m.name + "\t" + m.birth + "\t" + m.phone + "\t" + m.latePay + "\n");
		}
		panel.add(jta);
		add(panel);
		setVisible(true);
		
	}
}

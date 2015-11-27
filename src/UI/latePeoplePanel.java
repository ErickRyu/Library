package UI;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Model.Member;


//��üȸ�� ����� ���� ���� ������
public class latePeoplePanel extends JFrame{
	latePeoplePanel(ArrayList<Member> mL){
		setSize(400,400);
		JPanel panel = new JPanel(new BorderLayout());
		JTextArea jta = new JTextArea();
		jta.append("�̸�\t����\t��ȭ��ȣ\t��ü��\n");
		for(Member m : mL){
			jta.append(m.name + "\t" + m.birth + "\t" + m.phone + "\t" + m.latePay + "\n");
		}
		panel.add(jta);
		add(panel);
		setVisible(true);
		
	}
}

package UI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Control.MemberManager;


//�α����� ���� ������
public class LoginPanel extends JFrame{
	JTextField id;
	JTextField pw;
	
	LoginPanel(){
		
		setSize(200,200);
		JPanel panel = new JPanel(new GridLayout(3,2,30,30));
		
		panel.add(new JLabel("���̵�"));
		id = new JTextField();
		panel.add(id);
		panel.add(new JLabel("��й�ȣ"));
		pw = new JTextField();
		panel.add(pw);

		JButton submitBtn = new JButton("�α���");
		panel.add(submitBtn);
		submitBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MemberManager mMng = new MemberManager();
				int res = 0;
				res = mMng.Login(id.getText(), pw.getText());
				if(res != 0){
					JOptionPane.showMessageDialog(null, "�α��� ����", "�α��� ����", JOptionPane.INFORMATION_MESSAGE);
					
					ShowGui.logIO.setLabel("LogOut");
					setVisible(false);
					
					
				}else{
					JOptionPane.showMessageDialog(null, "�α��� ����", "�α��� ����", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		JButton cancelBtn = new JButton("���");
		cancelBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(false);
			}
		});
		panel.add(cancelBtn);
		
		add(panel);
		
		
		setVisible(true);
	}
	

}

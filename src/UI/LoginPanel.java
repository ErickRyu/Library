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


//로그인을 위한 프레임
public class LoginPanel extends JFrame{
	JTextField id;
	JTextField pw;
	
	LoginPanel(){
		
		setSize(200,200);
		JPanel panel = new JPanel(new GridLayout(3,2,30,30));
		
		panel.add(new JLabel("아이디"));
		id = new JTextField();
		panel.add(id);
		panel.add(new JLabel("비밀번호"));
		pw = new JTextField();
		panel.add(pw);

		JButton submitBtn = new JButton("로그인");
		panel.add(submitBtn);
		submitBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MemberManager mMng = new MemberManager();
				int res = 0;
				res = mMng.Login(id.getText(), pw.getText());
				if(res != 0){
					JOptionPane.showMessageDialog(null, "로그인 성공", "로그인 성공", JOptionPane.INFORMATION_MESSAGE);
					
					ShowGui.logIO.setLabel("LogOut");
					setVisible(false);
					
					
				}else{
					JOptionPane.showMessageDialog(null, "로그인 실패", "로그인 실패", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		JButton cancelBtn = new JButton("취소");
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

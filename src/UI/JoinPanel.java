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

//회원가입 패널
public class JoinPanel extends JFrame {
	private JTextField idField;
	private JTextField pwField;
	private JTextField nameField;
	private JTextField birthField;
	private JTextField phoneField;
	
	public JoinPanel() {
		setSize(200, 230);

		JPanel panel = new JPanel(new GridLayout(6, 2, 20, 10));
		panel.add(new JLabel("아이디"));
		idField = new JTextField();
		panel.add(idField);
		panel.add(new JLabel("비밀번호"));
		pwField = new JTextField();
		panel.add(pwField);
		panel.add(new JLabel("이름"));
		nameField = new JTextField();
		panel.add(nameField);
		panel.add(new JLabel("생년월일"));
		birthField = new JTextField();
		panel.add(birthField);
		panel.add(new JLabel("전화번호"));
		phoneField = new JTextField();
		panel.add(phoneField);

		JButton join = new JButton("가입"); // join 눌렀을 경우 MemberManager실행
		join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				int res = 0;
				MemberManager mMng = new MemberManager();
				try{
				res = mMng.join(idField.getText(), pwField.getText(), nameField.getText(), birthField.getText(), phoneField.getText());
				if(res != 0){
					sendMsg("회원가입 성공");	
					setVisible(false);
				}
				
				else
					sendMsg("회원가입오류");
				}catch(Exception ex){
					//생년월일과 전화번호에 문자 입력으로 인해 오류가 뜰 경우
					sendMsg("회원가입오류");
				}
					
			}
		});
		
		
		JButton cancel = new JButton("취소");
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(false);
			}
		});

		panel.add(join);
		panel.add(cancel);

		add(panel);

		setVisible(true);

	}	
	public void sendMsg(String msg){
		JOptionPane.showMessageDialog(null, msg, "Message", JOptionPane.INFORMATION_MESSAGE);
	}

}

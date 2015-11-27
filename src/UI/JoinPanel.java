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

//ȸ������ �г�
public class JoinPanel extends JFrame {
	private JTextField idField;
	private JTextField pwField;
	private JTextField nameField;
	private JTextField birthField;
	private JTextField phoneField;
	
	public JoinPanel() {
		setSize(200, 230);

		JPanel panel = new JPanel(new GridLayout(6, 2, 20, 10));
		panel.add(new JLabel("���̵�"));
		idField = new JTextField();
		panel.add(idField);
		panel.add(new JLabel("��й�ȣ"));
		pwField = new JTextField();
		panel.add(pwField);
		panel.add(new JLabel("�̸�"));
		nameField = new JTextField();
		panel.add(nameField);
		panel.add(new JLabel("�������"));
		birthField = new JTextField();
		panel.add(birthField);
		panel.add(new JLabel("��ȭ��ȣ"));
		phoneField = new JTextField();
		panel.add(phoneField);

		JButton join = new JButton("����"); // join ������ ��� MemberManager����
		join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				int res = 0;
				MemberManager mMng = new MemberManager();
				try{
				res = mMng.join(idField.getText(), pwField.getText(), nameField.getText(), birthField.getText(), phoneField.getText());
				if(res != 0){
					sendMsg("ȸ������ ����");	
					setVisible(false);
				}
				
				else
					sendMsg("ȸ�����Կ���");
				}catch(Exception ex){
					//������ϰ� ��ȭ��ȣ�� ���� �Է����� ���� ������ �� ���
					sendMsg("ȸ�����Կ���");
				}
					
			}
		});
		
		
		JButton cancel = new JButton("���");
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

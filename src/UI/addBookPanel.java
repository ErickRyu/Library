package UI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Control.BookManager;


//도서추가패널
public class addBookPanel extends JFrame{
	JTextField isbn;
	JTextField title;
	JTextField author;
	JTextField year;
	JTextField publisher;
	
	addBookPanel(){
		//to Do
		setSize(300,300);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6,2, 10, 10));
		panel.add(new JLabel("ISBN"));
		isbn = new JTextField();
		panel.add(isbn);
		panel.add(new JLabel("제목"));
		title = new JTextField();
		panel.add(title);
		panel.add(new JLabel("저자"));
		author = new JTextField();
		panel.add(author);
		panel.add(new JLabel("출판년도"));
		year = new JTextField();
		panel.add(year);
		panel.add(new JLabel("출판사"));
		publisher = new JTextField();
		panel.add(publisher);
		
		JButton submit = new JButton("등록");
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				BookManager bMng = new BookManager();
				int res = 0;
				res = bMng.insert(isbn.getText(), title.getText(), author.getText(), year.getText(), publisher.getText(), "N");
				if(res==1){
					setVisible(false);
				}else{
					
				}
			}
		});
		panel.add(submit);
		JButton cancel = new JButton("취소");
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(false);
			}
		});
		panel.add(cancel);
		
		add(panel);
		setVisible(true);
		
	}
}

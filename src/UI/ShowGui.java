package UI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Control.BookManager;
import Control.MemberManager;

public class ShowGui extends JFrame{
	
	
	BookManager bMng = new BookManager();
	MemberManager mMng = new MemberManager();
	public static MenuItem logIO;
	public ShowGui(){
		
		setSize(700, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		MenuBar mb = new MenuBar();
		Menu menu = new Menu("Menu");
		
		MenuItem bookOpen = new MenuItem("File Open(Book)");			//book file open
		bookOpen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bMng.fileOpen();
			}
		});
		MenuItem bookSave = new MenuItem("File Save(Book)");			//book file open
		bookSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bMng.fileSave();
			}
		});
		
		MenuItem memberOpen = new MenuItem("File Open(Member)");		//memeber file opne
		memberOpen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mMng.fileOpen();
			}
		});
		
		MenuItem memberSave = new MenuItem("File Save(Member)");		//memeber file opne
		memberSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mMng.fileSave();			
			}
		});
		
		logIO = new MenuItem("LogIn");									//login or logout
		logIO.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MemberManager mMng = new MemberManager();
					if(MemberManager.loginUser==null){			//로그인이 안 됐을 경우			
						LoginPanel lP = new LoginPanel();
					}else{										//로그인이 된 경우
						mMng.loginUser = null;
						logIO.setLabel("LogIn");
					}
			}
		});
		
		
		menu.add(bookOpen);
		menu.add(bookSave);
		menu.add(memberOpen);
		menu.add(memberSave);
		menu.add(logIO);
		mb.add(menu);
		setMenuBar(mb);
		
		
		JLabel label = new JLabel("도  서  대  출", JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		label.setFont(new Font("Serif", Font.BOLD, 40));
		getContentPane().add(label, BorderLayout.NORTH);	
		
		SearchPanel sPanel = new SearchPanel();
		sPanel.sPanel();
		BtnPanel bPanel = new BtnPanel();
		
		
		getContentPane().add(sPanel, BorderLayout.CENTER);
		getContentPane().add(bPanel, BorderLayout.EAST);
		
		
		setVisible(true);
	}
}

package UI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Control.BookManager;
import Control.DateManager;
import Control.MemberManager;
import Model.Book;

public class BtnPanel extends JPanel {
	SearchPanel sPanel = new SearchPanel();

	BtnPanel() {
		setLayout(new GridLayout(10, 1, 10, 10));
		JPanel empty = new JPanel();
		JPanel empty2 = new JPanel();
		JPanel empty3 = new JPanel();

		JButton searchBtn = new JButton("검색");
		add(searchBtn);
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// to Do
				BookManager bMng = new BookManager();
				ArrayList<Book> books = new ArrayList();
				books = bMng.searchBook(sPanel.toSearch.getSelectedIndex(),
						sPanel.searchField.getText());

				sPanel.setTable(books);

			}
		});

		add(empty);

		JButton borrowBtn = new JButton("대출");
		add(borrowBtn);
		borrowBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 로그인 여부 확인
				if (MemberManager.loginUser != null) {
					MemberManager mMng = new MemberManager();
					Book toBorrow = new Book();
					SearchPanel sP = new SearchPanel();
					int row = sP.jTable.getSelectedRow();

					Object value = sP.jTable.getValueAt(row, 6);
					if (value.equals("N")) {
						// 도서 대출
						value = sP.jTable.getValueAt(row, 0);
						int bookId = Integer.parseInt(value.toString());
						BookManager bMng = new BookManager();
						bMng.borrowBook(bookId);
						sP.updateTable();
					} else {
						sendMsg("대출이 불가능합니다.");
					}

				} else {
					sendMsg("로그인 하세요");
				}
			}
		});

		JButton backBtn = new JButton("도서반납");
		add(backBtn);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 로그인 여부 확인
				if (MemberManager.loginUser != null) {
					MemberManager mMng = new MemberManager();
					Book toBack = new Book();
					SearchPanel sP = new SearchPanel();
					int row = sP.jTable.getSelectedRow();

					Object value = sP.jTable.getValueAt(row, 0);
					boolean wasIBorrowed = false;
					for (Book b : mMng.loginUser.borrowedBook) {
						if (Integer.parseInt(value.toString()) == b.bookId) {
							wasIBorrowed = true;
							BookManager bMng = new BookManager();
							bMng.backBook(b);
							sP.updateTable();
							break;

						}

					}
					if (!wasIBorrowed) {
						sendMsg("본인이 대여한 책이 아닙니다.");
					}

				} else {
					sendMsg("로그인 하세요");
				}
			}
		});

		JButton myInfoBtn = new JButton("내정보");
		add(myInfoBtn);
		myInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 로그인 여부 확인
				if (MemberManager.loginUser != null) {
					DateManager dMng = new DateManager();
					dMng.updateLatePay(MemberManager.loginUser);
					myInfoPanel mIP = new myInfoPanel();
				} else {
					sendMsg("로그인 하세요");
				}
			}
		});

		JButton joinBtn = new JButton("회원가입");
		add(joinBtn);
		joinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 회원가입 패널 열기
				JoinPanel join = new JoinPanel();

			}
		});

		JButton insertBtn = new JButton("도서추가"); // Member.isSupervisor == "Y"
													// 일경우 실행
		add(insertBtn);
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 로그인 여부 확인
				if (MemberManager.loginUser != null) {
					// 관리자 아이디 인지 확인
					if (MemberManager.loginUser.isSupervisor != null) {
						if (MemberManager.loginUser.isSupervisor.equals("Y")) {
							// 도서 추가
							addBookPanel abp = new addBookPanel();
						}
					} else {
						sendMsg("관리자만 도서추가가 가능합니다.");
					}
				} else {
					sendMsg("로그인 하세요");
				}

			}
		});

		JButton delayListBtn = new JButton("연체자 목록");
		add(delayListBtn);
		delayListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 로그인 여부 확인
				if (MemberManager.loginUser != null) {
					// 관리자 아이디 인지 확인
					if (MemberManager.loginUser.isSupervisor != null) {
						if (MemberManager.loginUser.isSupervisor.equals("Y")) {
							// 연체자 목록 확인
							DateManager dMng = new DateManager();
							dMng.updateLatePay();
							MemberManager mMng = new MemberManager();
							latePeoplePanel lpp = new latePeoplePanel(mMng
									.lateList());
						}
					} else {
						sendMsg("관리자만 확인이 가능합니다.");
					}
				} else {
					sendMsg("로그인 하세요");
				}

			}
		});
	}

	public void sendMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Message",
				JOptionPane.INFORMATION_MESSAGE);
	}
}

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

		JButton searchBtn = new JButton("�˻�");
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

		JButton borrowBtn = new JButton("����");
		add(borrowBtn);
		borrowBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �α��� ���� Ȯ��
				if (MemberManager.loginUser != null) {
					MemberManager mMng = new MemberManager();
					Book toBorrow = new Book();
					SearchPanel sP = new SearchPanel();
					int row = sP.jTable.getSelectedRow();

					Object value = sP.jTable.getValueAt(row, 6);
					if (value.equals("N")) {
						// ���� ����
						value = sP.jTable.getValueAt(row, 0);
						int bookId = Integer.parseInt(value.toString());
						BookManager bMng = new BookManager();
						bMng.borrowBook(bookId);
						sP.updateTable();
					} else {
						sendMsg("������ �Ұ����մϴ�.");
					}

				} else {
					sendMsg("�α��� �ϼ���");
				}
			}
		});

		JButton backBtn = new JButton("�����ݳ�");
		add(backBtn);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �α��� ���� Ȯ��
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
						sendMsg("������ �뿩�� å�� �ƴմϴ�.");
					}

				} else {
					sendMsg("�α��� �ϼ���");
				}
			}
		});

		JButton myInfoBtn = new JButton("������");
		add(myInfoBtn);
		myInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �α��� ���� Ȯ��
				if (MemberManager.loginUser != null) {
					DateManager dMng = new DateManager();
					dMng.updateLatePay(MemberManager.loginUser);
					myInfoPanel mIP = new myInfoPanel();
				} else {
					sendMsg("�α��� �ϼ���");
				}
			}
		});

		JButton joinBtn = new JButton("ȸ������");
		add(joinBtn);
		joinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ȸ������ �г� ����
				JoinPanel join = new JoinPanel();

			}
		});

		JButton insertBtn = new JButton("�����߰�"); // Member.isSupervisor == "Y"
													// �ϰ�� ����
		add(insertBtn);
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �α��� ���� Ȯ��
				if (MemberManager.loginUser != null) {
					// ������ ���̵� ���� Ȯ��
					if (MemberManager.loginUser.isSupervisor != null) {
						if (MemberManager.loginUser.isSupervisor.equals("Y")) {
							// ���� �߰�
							addBookPanel abp = new addBookPanel();
						}
					} else {
						sendMsg("�����ڸ� �����߰��� �����մϴ�.");
					}
				} else {
					sendMsg("�α��� �ϼ���");
				}

			}
		});

		JButton delayListBtn = new JButton("��ü�� ���");
		add(delayListBtn);
		delayListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �α��� ���� Ȯ��
				if (MemberManager.loginUser != null) {
					// ������ ���̵� ���� Ȯ��
					if (MemberManager.loginUser.isSupervisor != null) {
						if (MemberManager.loginUser.isSupervisor.equals("Y")) {
							// ��ü�� ��� Ȯ��
							DateManager dMng = new DateManager();
							dMng.updateLatePay();
							MemberManager mMng = new MemberManager();
							latePeoplePanel lpp = new latePeoplePanel(mMng
									.lateList());
						}
					} else {
						sendMsg("�����ڸ� Ȯ���� �����մϴ�.");
					}
				} else {
					sendMsg("�α��� �ϼ���");
				}

			}
		});
	}

	public void sendMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Message",
				JOptionPane.INFORMATION_MESSAGE);
	}
}

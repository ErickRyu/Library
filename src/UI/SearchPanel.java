package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Control.BookManager;
import Model.Book;

public class SearchPanel extends JPanel {
	public static JTable jTable;
	private static DefaultTableModel model;
	public static final JComboBox<String> toSearch = new JComboBox(new String[] {"����", "ISBN", "����", "��ü ����" , "��������"});
	public static JTextField searchField = new JTextField();
	BookManager bMng = new BookManager();
	
	private static ArrayList<Book> preBook;		//JTable������Ʈ�� ���� ������ �˻��ߴ� Book����� preBook�� ����
	
	
	//SearchPanel Ʋ ����
	public void sPanel() {
		setLayout(new BorderLayout(20, 20));
		JPanel northPanel = new JPanel(new FlowLayout());
		JPanel CenterPanel = new JPanel(new BorderLayout());

		
		searchField.setColumns(30);

		northPanel.add(toSearch);
		northPanel.add(searchField);

		Object rowData[][] = new Object[0][7];
		model = new DefaultTableModel(rowData, bMng.colNames);
		jTable = new JTable(model);
		JScrollPane jsp = new JScrollPane(jTable);
		CenterPanel.add(jsp);

		add(northPanel, BorderLayout.NORTH);
		add(CenterPanel, BorderLayout.CENTER);

	}

	//JTable�� ����� book����Ʋ �޾� �� �� ���
	public void setTable(ArrayList<Book> books) {
		// to Do
		preBook = books;
		model.setNumRows(0);
		int idx = 0;
		Object rowData[][] = new Object[books.size()][7];
		for (Book b : books) {
			rowData[idx][0] = b.bookId;
			rowData[idx][1] = b.isbn;
			rowData[idx][2] = b.title;
			rowData[idx][3] = b.author;
			rowData[idx][4] = b.year;
			rowData[idx][5] = b.publisher;
			rowData[idx][6] = b.isBorrowed;
			
			idx++;
		}
		
		for (Book b : books) {
			String[] row = {Integer.toString(b.bookId), b.isbn, b.title, b.author, b.year, b.publisher,
					b.isBorrowed };
			model.addRow(row);
		}
	}
	
	
	public void updateTable(){
		//model.fireUpdatedDate ������ �ȵż� preBook�� ������ �˻��� ������ BookŸ�� ArrayList�� ���� �� �ٽ� ���
		setTable(preBook);
	}
}

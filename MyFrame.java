package frame;
////���棬����Ǹı��Ѿ�����������ݣ�����Ҫ�����Ի���
////�رյ�ʱ�������ȡ����Ҫ��ô����
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MyFrame extends JFrame implements DocumentListener{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param args
	 */
	
	public static MyFrame myFrame;
////	����private
	public static JMenuItem newbuild;
	public static JMenuItem open;
	public static JMenuItem save;
	public static JMenuItem another;
	public static JMenuItem exit;
	public static JMenuItem undo;
	public static JMenuItem cut;
	public static JMenuItem copy;
	public static JMenuItem paste;
	public static JMenuItem delete;
	public static JMenuItem all;
	public static JMenuItem font;
	public static JMenuItem status;
	public static JMenuItem lookforhelp;
	public static JMenuItem about;
	public static JTextArea area;
	public static boolean flag = false;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		myFrame = new MyFrame();
		myFrame.initFrame();
		myFrame.addMenu();
		new AddListener().addListener();
		
		area.getDocument().addDocumentListener(myFrame);
		myFrame.addWindowListener(new WindowAdapter(){

			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				if(MyFrame.flag==true){//���JTextArea���ݸı���
					int res = JOptionPane.showConfirmDialog(null, "�Ƿ񱣴�","���±�",JOptionPane.YES_NO_CANCEL_OPTION);
					if(res == JOptionPane.YES_OPTION){/////���ȷ����ȷ�����棩
						AddListener.save();
					}else if(res == JOptionPane.CANCEL_OPTION){/////�����ȡ��
						
					}else if(res == JOptionPane.NO_OPTION){/////���ѡ�񲻱���
						System.exit(0);
					}
				}
				else{
					MyFrame.area.setText("");
				}
			}
		});
		
		myFrame.setVisible(true);
	}
	public void initFrame(){
		Toolkit toolKit = Toolkit.getDefaultToolkit();
		Dimension screen = toolKit.getScreenSize();
		int width = screen.width;
		int height = screen.height;
		myFrame.setSize(700,500);
		myFrame.setLocation(width/4,height/5);
		Image icon = new ImageIcon("icon.jpg").getImage();
		myFrame.setIconImage(icon);
		/*****************����******************/
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/*��Ӳ˵�����д��*/
	public void addMenu(){
		JMenuBar mb = new JMenuBar();
		/****************JMenuBar�е�JMenu******************/
		JMenu file = new JMenu("�ļ�(F)");
		JMenu edit = new JMenu("�༭(E)");
		JMenu format = new JMenu("��ʽ(O)");
		JMenu view = new JMenu("�鿴(V)");
		JMenu help =new JMenu("����(H)");
		/*****************JMenu�е�JMenuItem****************/
		//file�е�JMenuItem
		newbuild = new JMenuItem("�½�(N)");
		open = new JMenuItem("��(O)");
		save = new JMenuItem("����(S)");
		another = new JMenuItem("���Ϊ(A)");
		exit = new JMenuItem("�˳�(E)");
		//edit�е�JMenuItem
		undo = new JMenuItem("����(U)");
		cut = new JMenuItem("����(X)");
		copy = new JMenuItem("����(C)");
		paste = new JMenuItem("ճ��(P)");
		delete = new JMenuItem("ɾ��(L)");
		all = new JMenuItem("ȫѡ(A)");
		//format�е�JMenuItem
		font = new JMenuItem("����(F)");
		//view�е�JMenuItem
		status = new JMenuItem("״̬��(S)");
		//help�е�JMenuItem
		lookforhelp = new JMenuItem("�鿴����(H)");
		about = new JMenuItem("���ڼ��±�(A)");
		/***********��frame�����JMenuBar***********/
		myFrame.setJMenuBar(mb);
		/***********��JMenuBar�����JMenu***********/
		mb.add(file);
		mb.add(edit);
		mb.add(format);
		mb.add(view);
		mb.add(help);
		/***********�ֱ���JMenu�����JMenuItem********/
		//��file�����
		file.add(newbuild);
		file.add(open);
		file.add(save);
		file.add(another);
		file.addSeparator();
		file.add(exit);
	    //��edit�����
		edit.add(undo);
		edit.addSeparator();
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(delete);
		edit.addSeparator();
		edit.add(all);
		//��format�����
		format.add(font);
		//��view�����
		view.add(status);
		//��help�����
		help.add(lookforhelp);
		help.addSeparator();
		help.add(about);
		
		/************JTextArea***************/
		area = new JTextArea("");
		myFrame.add(area);
		JScrollPane scroller = new JScrollPane(area);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		myFrame.add(BorderLayout.CENTER,scroller);
		
	}
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		flag = true;
	}
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		flag = true;
	}
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		flag = true;
	}
	
}
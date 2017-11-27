package frame;
/*待改进*/
////保存，如果与该目录下另一文件重名，就弹出提示.
////保存文本时，没法把对应改后的字体也保存，并下次打开的时候也显示出该字体来.
////布局
/**新建.没问题了.MyFrame.flag是用来检测JTextArea内容是否改变
 * 			 AddListener.flag是用来检测是不是已经存在的文件.目前只在 打开 里改变了flag,一打开就说明文件存在，点保存不需要弹出对话框，直接保存.
 * 
 * 
 * **/
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.undo.UndoManager;

public class MyFrame extends JFrame implements DocumentListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param args
	 */

	public static MyFrame myFrame;
	// // 换成private
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
	public static JLabel label;
	public static boolean flag = false;
	public static UndoManager undoMgr = new UndoManager();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		myFrame = new MyFrame();
		myFrame.initFrame();
		myFrame.addMenu();
		new AddListener().addListener();
		// 给JTextArea添加监听，与众不同.
		area.getDocument().addDocumentListener(myFrame);
		area.getDocument().addUndoableEditListener(undoMgr);
		area.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				// TODO Auto-generated method stub
				try {
					int pos = area.getCaretPosition();
					int rows = area.getLineOfOffset(pos) + 1;
					int columns = pos - area.getLineStartOffset(rows - 1) + 1;
					int strSize = area.getText().toString().length();
					SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
					label.setText(date.format(new Date())+"             第" + rows + "行，第" + columns + "列"+"        总字数："+strSize+"      ");
					label.setHorizontalAlignment(JLabel.RIGHT); 
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		myFrame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				if (MyFrame.flag == true) {// 如果JTextArea内容改变了
					int res = JOptionPane.showConfirmDialog(null, "是否保存",
							"记事本", JOptionPane.YES_NO_CANCEL_OPTION);
					if (res == JOptionPane.YES_OPTION) {// ///如果确定保存，如果是已经存在的文档
						if (AddListener.flag == true) {
							AddListener.save();
						} else {
							AddListener.showDiaSave();
						}
						System.exit(0);
					} else if (res == JOptionPane.CANCEL_OPTION) {// ///如果点取消
						myFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					} else if (res == JOptionPane.NO_OPTION) {// ///如果选择不保存
						System.exit(0);
					}
				} else {
					MyFrame.area.setText("");
				}
			}
		});

		myFrame.setVisible(true);
	}

	public void initFrame() {
		Toolkit toolKit = Toolkit.getDefaultToolkit();
		Dimension screen = toolKit.getScreenSize();
		int width = screen.width;
		int height = screen.height;
		myFrame.setSize(700, 500);
		myFrame.setLocation(width / 4, height / 5);
		Image icon = new ImageIcon("icon.jpg").getImage();
		myFrame.setIconImage(icon);
		/***************** 标题 ******************/
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/* 添加菜单和书写区 */
	public void addMenu() {
		JMenuBar mb = new JMenuBar();
		/**************** JMenuBar中的JMenu ******************/
		JMenu file = new JMenu("文件(F)");
		JMenu edit = new JMenu("编辑(E)");
		JMenu format = new JMenu("格式(O)");
		JMenu view = new JMenu("查看(V)");
		JMenu help = new JMenu("帮助(H)");
		/***************** JMenu中的JMenuItem ****************/
		// file中的JMenuItem
		newbuild = new JMenuItem("新建(N)");
		open = new JMenuItem("打开(O)");
		save = new JMenuItem("保存(S)");
		another = new JMenuItem("另存为(A)");
		exit = new JMenuItem("退出(E)");
		// edit中的JMenuItem
		undo = new JMenuItem("撤销(U)");
		cut = new JMenuItem("剪切(X)");
		copy = new JMenuItem("复制(C)");
		paste = new JMenuItem("粘贴(P)");
		delete = new JMenuItem("删除(L)");
		all = new JMenuItem("全选(A)");
		// format中的JMenuItem
		font = new JMenuItem("字体(F)");
		// view中的JMenuItem
		status = new JMenuItem("状态栏(S)");
		// help中的JMenuItem
		lookforhelp = new JMenuItem("查看帮助(H)");
		about = new JMenuItem("关于记事本(A)");
		/*********** 在frame中添加JMenuBar ***********/
		myFrame.setJMenuBar(mb);
		/*********** 在JMenuBar中添加JMenu ***********/
		mb.add(file);
		mb.add(edit);
		mb.add(format);
		mb.add(view);
		mb.add(help);
		/*********** 分别在JMenu中添加JMenuItem ********/
		// 在file中添加
		file.add(newbuild);
		file.add(open);
		file.add(save);
		file.add(another);
		file.addSeparator();
		file.add(exit);
		// 在edit中添加
		edit.add(undo);
		edit.addSeparator();
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(delete);
		edit.addSeparator();
		edit.add(all);
		// 在format中添加
		format.add(font);
		// 在view中添加
		view.add(status);
		// 在help中添加
		help.add(lookforhelp);
		help.addSeparator();
		help.add(about);

		/************ JTextArea ***************/
		area = new JTextArea("");
//		Preferences p = FontDia.prefs;
//		if(p.get(FontDia.fontKind, "")!=""){
//			area.setFont(new Font(p.get(FontDia.fontKind,""),p.getInt(FontDia.styleKind,0),p.getInt(FontDia.sizeKind, 0)));;
//		}
//		else
//			area.setFont(new Font("宋体",Font.PLAIN,18));
		area.setFont(new Font("宋体",Font.PLAIN,18));
		
		myFrame.add(area);
		JScrollPane scroller = new JScrollPane(area);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		myFrame.add(BorderLayout.CENTER, scroller);

		label = new JLabel();
		myFrame.add(BorderLayout.SOUTH, label);
		label.setVisible(false);

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

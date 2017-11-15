package frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class MyFrame {

	/**
	 * @param args
	 */
	public static JFrame myFrame;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFrame mf = new MyFrame();
		mf.initFrame();
		mf.addMenu();
		
		
		myFrame.setVisible(true);
	}
	public void initFrame(){//可以用myFrame
		myFrame = new JFrame();
		Toolkit toolKit = Toolkit.getDefaultToolkit();
		Dimension screen = toolKit.getScreenSize();
		int width = screen.width;
		int height = screen.height;
		myFrame.setSize(700,500);
		myFrame.setLocation(width/4,height/5);
		Image icon = new ImageIcon("icon.jpg").getImage();
		myFrame.setIconImage(icon);
		/*****************标题******************/
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	/*添加菜单和书写区*/
	public void addMenu(){
		JMenuBar mb = new JMenuBar();
		/****************JMenuBar中的JMenu******************/
		JMenu file = new JMenu("文件(F)");
		JMenu edit = new JMenu("编辑(E)");
		JMenu format = new JMenu("格式(O)");
		JMenu view = new JMenu("查看(V)");
		JMenu help =new JMenu("帮助(H)");
		/*****************JMenu中的JMenuItem****************/
		//file中的JMenuItem
		JMenuItem newbuild = new JMenuItem("新建(N)");
		JMenuItem open = new JMenuItem("打开(O)");
		JMenuItem save = new JMenuItem("保存(S)");
		JMenuItem another = new JMenuItem("另存为(A)");
		JMenuItem exit = new JMenuItem("退出(E)");
		//edit中的JMenuItem
		JMenuItem undo = new JMenuItem("撤销(U)");
		JMenuItem cut = new JMenuItem("剪切(X)");
		JMenuItem copy = new JMenuItem("复制(C)");
		JMenuItem paste = new JMenuItem("粘贴(P)");
		JMenuItem delete = new JMenuItem("删除(L)");
		JMenuItem all = new JMenuItem("全选(A)");
		//format中的JMenuItem
		JMenuItem font = new JMenuItem("字体(F)");
		//view中的JMenuItem
		JMenuItem status = new JMenuItem("状态栏(S)");
		//help中的JMenuItem
		JMenuItem lookforhelp = new JMenuItem("查看帮助(H)");
		JMenuItem about = new JMenuItem("关于记事本(A)");
		/***********在frame中添加JMenuBar***********/
		myFrame.setJMenuBar(mb);
		/***********在JMenuBar中添加JMenu***********/
		mb.add(file);
		mb.add(edit);
		mb.add(format);
		mb.add(view);
		mb.add(help);
		/***********分别在JMenu中添加JMenuItem********/
		//在file中添加
		file.add(newbuild);
		file.add(open);
		file.add(save);
		file.add(another);
		file.addSeparator();
		file.add(exit);
	    //在edit中添加
		edit.add(undo);
		edit.addSeparator();
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(delete);
		edit.addSeparator();
		edit.add(all);
		//在format中添加
		format.add(font);
		//在view中添加
		view.add(status);
		//在help中添加
		help.add(lookforhelp);
		help.addSeparator();
		help.add(about);
		
		/************JTextArea***************/
		JTextArea area = new JTextArea();
		myFrame.add(area);
		JScrollPane scroller = new JScrollPane(area);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//frame.getContentPane().add(BorderLayout.CENTER,scroller);
		myFrame.add(BorderLayout.CENTER,scroller);
		
		
	}
}

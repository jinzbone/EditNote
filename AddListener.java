package frame;

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

class AddListener implements ActionListener{

	File file;
	FileReader fr;
	BufferedReader br;
	public static JFileChooser chooser;
	public static int result;
	public static File openFile;
	public static boolean flag = false;
	public void addListener() {
		MyFrame.exit.addActionListener(this);
		MyFrame.about.addActionListener(this);
		MyFrame.all.addActionListener(this);
		MyFrame.another.addActionListener(this);
		MyFrame.copy.addActionListener(this);
		MyFrame.cut.addActionListener(this);
		MyFrame.delete.addActionListener(this);
		MyFrame.font.addActionListener(this);
		MyFrame.lookforhelp.addActionListener(this);
		MyFrame.newbuild.addActionListener(this);
		MyFrame.open.addActionListener(this);
		MyFrame.undo.addActionListener(this);
		MyFrame.status.addActionListener(this);
		MyFrame.save.addActionListener(this);
		MyFrame.paste.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(MyFrame.exit)) {
			if (MyFrame.flag == true) {// 如果改变了JTextArea内容，要询问是否保存
				int res = JOptionPane.showConfirmDialog(null, "是否保存", "记事本",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (res == JOptionPane.YES_OPTION) {
					if (flag == true) {// 如果是已经存在的文档，不需要弹出对话框，直接保存进该文档.
						AddListener.save();
					} else {//
						AddListener.showDiaSave();
					}
					System.exit(0);
				} else if (res == JOptionPane.CANCEL_OPTION) {

				} else if (res == JOptionPane.NO_OPTION) {
					System.exit(0);
				}
			} else {
				System.exit(0);
			}
		} else if (e.getSource().equals(MyFrame.about)) {
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			int width = screen.width;
			int height = screen.height;
			JFrame frame = new JFrame("关于记事本");
			frame.setSize(400,400);
			frame.setLocation(width / 4+100, height / 5+100);
			JLabel label = new JLabel("本软件已合法授权！");
			label.setHorizontalAlignment(JLabel.CENTER);
			frame.add(label);
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			frame.setVisible(true);
		}
		/*
		 * 全选 JTextArea有selectAll()方法来实现全选.
		 */
		else if (e.getSource().equals(MyFrame.all)) {
			MyFrame.area.selectAll();
		}
		// 另存为：是不管是不是之前的，都弹出保存对话框来保存.所以，你的保存方法，应该是另存为的方法才对。晓得了吗。
		// ////另外，如果选择覆盖已经存在的文件，则要弹出对话框是否覆盖。
		else if (e.getSource().equals(MyFrame.another)) {
			AddListener.showDiaSave();
		}
		/* 复制功能 */
		else if (e.getSource().equals(MyFrame.copy)) {
			MyFrame.area.copy();
		}
		/* 剪切功能 */// 记录下位置，将start--end替换成空串
		else if (e.getSource().equals(MyFrame.cut)) {
			MyFrame.area.cut();

		}
		/*删除功能*/
		else if (e.getSource().equals(MyFrame.delete)) {
			if(MyFrame.area.getSelectedText()!=null){//如果选中了内容
				int start = MyFrame.area.getSelectionStart();
				int end = MyFrame.area.getSelectionEnd();
				MyFrame.area.replaceRange("", start, end);
			}
		}
		/*点击字体，弹出字体对话框.自己写的字体对话框类.*/ 
		else if (e.getSource().equals(MyFrame.font)) {
			FontDia.fontDia = new FontDia();
			FontDia.fontDia.addComponent();
			FontDia.fontDia.setVisible(true);
//			FontChooserDialog f = new FontChooserDialog();
//			f.setVisible(true);
		} else if (e.getSource().equals(MyFrame.lookforhelp)) {

		}
		/*** 新建文本文档 ***/
		else if (e.getSource().equals(MyFrame.newbuild)) {
			/**** 如果原文档没被改变，就直接将其隐藏，再新建。 ****/
			// //// 否则，就先询问是否保存，再将其隐藏，再新建
			if (MyFrame.flag == true) {// 如果JTextArea内容改变了
				int res = JOptionPane.showConfirmDialog(null, "是否保存", "记事本",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (res == JOptionPane.YES_OPTION) {// 如果确定（确定保存）
					AddListener.showDiaSave();
					MyFrame.area.setText("");
					MyFrame.flag = false;
				} else if (res == JOptionPane.CANCEL_OPTION) {// ///如果点取消

				} else if (res == JOptionPane.NO_OPTION) {// ///如果选择不保存
					MyFrame.area.setText("");
					MyFrame.flag = false;
				}
			} else {
				MyFrame.area.setText("");
			}
		} else if (e.getSource().equals(MyFrame.undo)) {
			if(!MyFrame.undoMgr.canUndo()){
				
			}
			else{
				MyFrame.undoMgr.undo();
			}
			
		} else if (e.getSource().equals(MyFrame.status)) {
			
			if(MyFrame.label.isVisible()){//如果它可见，就把它设置为不可见的
				MyFrame.label.setVisible(false);
			}
			else{//如果不可见，就把它设置成可见的
				MyFrame.label.setVisible(true);
			}
		}
		/* 实现保存文件 */
		// /////保存：如果是在已经打开的文件里改，点保存就不用弹出对话框.

		else if (e.getSource().equals(MyFrame.save)) {
			if (flag == true) {// 如果是已经存在的文件修改，不需要弹出对话框.
				// 可能在 Open时，保存下这个文件，flag设置为true.还有初始打开文件的时候，flag设置为true
				/* 直接保存，不需要弹出对话框 */
				AddListener.save();
				MyFrame.flag = false;
			} else {// 否则，跟另存为的功能一样
				AddListener.showDiaSave();
				MyFrame.flag = false;
			}

		}
		/* （将剪贴板上的内容）粘贴 */
		else if (e.getSource().equals(MyFrame.paste)) {
			MyFrame.area.paste();
		}
		// 打开文件
		else if (e.getSource().equals(MyFrame.open)) {
			/*** 打开文件的对话框 ***/
			if (MyFrame.flag == true) {// 如果JTextArea内容改变
				int res = JOptionPane.showConfirmDialog(null, "是否保存", "记事本",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (res == JOptionPane.YES_OPTION) {
					if (flag == true) {// 如果是已经存在的文件
						AddListener.save();
					} else {
						AddListener.showDiaSave();
					}
					new AddListener().openFile();
					MyFrame.flag = false;// 打开之后才设置为false
				} else if (res == JOptionPane.CANCEL_OPTION) {

				} else if (res == JOptionPane.NO_OPTION) {
					new AddListener().openFile();
					MyFrame.flag = false;// 打开之后才设置为false
				}
			} else {
				new AddListener().openFile();
				MyFrame.flag = false;// 打开之后才设置为false
			}
		}
	}

	/* showDiaSave是用来：如果不是已经存在的文件，则需要弹出一个另存为对话框来保存. */
	public static void showDiaSave() {
		chooser = new JFileChooser();
		result = chooser.showSaveDialog(MyFrame.myFrame);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				bw.write(MyFrame.area.getText());
				
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/* save是用来：如果是已经存在的文件，就直接保存而不需要弹出对话框. */
	public static void save() {
		if (result == JFileChooser.APPROVE_OPTION) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(openFile));
				bw.write(MyFrame.area.getText());
				MyFrame.area.setFont(FontDia.areaFont);
				
				bw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/* 打开文件的方法 */
	public void openFile() {
		StringBuffer text = new StringBuffer();
		FileDialog fileDia = new FileDialog(MyFrame.myFrame, "打开文件",
				FileDialog.LOAD);
		fileDia.setResizable(false);
		fileDia.setVisible(true);
		String dir = fileDia.getDirectory();// 获取文件目录
		String fil = fileDia.getFile();// 获取文件
		// 如果在打开文件对话框内不想打开了，点取消，保证不会出错。（如果没有下边这一行代码就会出错）
		if (dir == null || fil == null)
			return;
		openFile = new File(fileDia.getDirectory(), fileDia.getFile());
		flag = true;// 表示已经存在
		try {
			file = new File(fileDia.getDirectory(), fileDia.getFile());
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String s = "";
			while ((s = br.readLine()) != null)
				text.append(s + "\n");
			br.close();
			fr.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MyFrame.area.setFont(FontDia.areaFont);
		MyFrame.area.setText(text.toString());
		
	}
}

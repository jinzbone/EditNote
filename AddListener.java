package frame;

import java.awt.FileDialog;
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
import javax.swing.JOptionPane;

class AddListener implements ActionListener {
	
	File file;
	FileReader fr;
	BufferedReader br;
	
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
			System.exit(0);
		} else if (e.getSource().equals(MyFrame.about)) {
			
		} else if (e.getSource().equals(MyFrame.all)) {

		}
		//另存为：是不管是不是之前的，都弹出保存对话框来保存.所以，你的保存方法，应该是另存为的方法才对。晓得了吗。
		else if (e.getSource().equals(MyFrame.another)) {
			AddListener.save();
		} else if (e.getSource().equals(MyFrame.copy)) {

		} else if (e.getSource().equals(MyFrame.cut)) {

		} else if (e.getSource().equals(MyFrame.delete)) {

		} else if (e.getSource().equals(MyFrame.font)) {

		} else if (e.getSource().equals(MyFrame.lookforhelp)) {

		}
		/***新建文本文档***/ 
		else if (e.getSource().equals(MyFrame.newbuild)) {
			/****如果原文档没被改变，就直接将其隐藏，再新建。****/
//////		否则，就先询问是否保存，再将其隐藏，再新建 
			if(MyFrame.flag==true){//如果JTextArea内容改变了
				int res = JOptionPane.showConfirmDialog(null, "是否保存","记事本",JOptionPane.YES_NO_CANCEL_OPTION);
				if(res==JOptionPane.YES_OPTION){//如果确定（确定保存）
					AddListener.save();
				}
				else if(res == JOptionPane.CANCEL_OPTION){/////如果点取消
					
				}else if(res == JOptionPane.NO_OPTION){/////如果选择不保存
					MyFrame.area.setText("");
				}
			}
			else{
				MyFrame.area.setText("");
			}
		} else if (e.getSource().equals(MyFrame.undo)) {

		} else if (e.getSource().equals(MyFrame.status)) {

		}
		/*实现保存文件*/
		///////保存：如果是在已经打开的文件里改，点保存就不用弹出对话框.
			 
		else if (e.getSource().equals(MyFrame.save)) {
			AddListener.save();
		} else if (e.getSource().equals(MyFrame.paste)) {

		} 
//		打开文件
		else if (e.getSource().equals(MyFrame.open)) {
			/***打开文件的对话框***/
			StringBuffer  text = new StringBuffer();
			FileDialog fileDia = new FileDialog(MyFrame.myFrame, "打开文件", FileDialog.LOAD);
			fileDia.setResizable(false);
			fileDia.setVisible(true);
			
			try {
				file = new File(fileDia.getDirectory(),fileDia.getFile());
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String s = "";
				while((s = br.readLine())!=null)
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
			MyFrame.area.setText(text.toString());
			
		}
	}
	/*保存文件**单独出一个方法来*/
	public static void save(){
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showSaveDialog(MyFrame.myFrame);
		if(result == JFileChooser.APPROVE_OPTION){
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
}

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
		//���Ϊ���ǲ����ǲ���֮ǰ�ģ�����������Ի���������.���ԣ���ı��淽����Ӧ�������Ϊ�ķ����Ŷԡ���������
		else if (e.getSource().equals(MyFrame.another)) {
			AddListener.save();
		} else if (e.getSource().equals(MyFrame.copy)) {

		} else if (e.getSource().equals(MyFrame.cut)) {

		} else if (e.getSource().equals(MyFrame.delete)) {

		} else if (e.getSource().equals(MyFrame.font)) {

		} else if (e.getSource().equals(MyFrame.lookforhelp)) {

		}
		/***�½��ı��ĵ�***/ 
		else if (e.getSource().equals(MyFrame.newbuild)) {
			/****���ԭ�ĵ�û���ı䣬��ֱ�ӽ������أ����½���****/
//////		���򣬾���ѯ���Ƿ񱣴棬�ٽ������أ����½� 
			if(MyFrame.flag==true){//���JTextArea���ݸı���
				int res = JOptionPane.showConfirmDialog(null, "�Ƿ񱣴�","���±�",JOptionPane.YES_NO_CANCEL_OPTION);
				if(res==JOptionPane.YES_OPTION){//���ȷ����ȷ�����棩
					AddListener.save();
				}
				else if(res == JOptionPane.CANCEL_OPTION){/////�����ȡ��
					
				}else if(res == JOptionPane.NO_OPTION){/////���ѡ�񲻱���
					MyFrame.area.setText("");
				}
			}
			else{
				MyFrame.area.setText("");
			}
		} else if (e.getSource().equals(MyFrame.undo)) {

		} else if (e.getSource().equals(MyFrame.status)) {

		}
		/*ʵ�ֱ����ļ�*/
		///////���棺��������Ѿ��򿪵��ļ���ģ��㱣��Ͳ��õ����Ի���.
			 
		else if (e.getSource().equals(MyFrame.save)) {
			AddListener.save();
		} else if (e.getSource().equals(MyFrame.paste)) {

		} 
//		���ļ�
		else if (e.getSource().equals(MyFrame.open)) {
			/***���ļ��ĶԻ���***/
			StringBuffer  text = new StringBuffer();
			FileDialog fileDia = new FileDialog(MyFrame.myFrame, "���ļ�", FileDialog.LOAD);
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
	/*�����ļ�**������һ��������*/
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

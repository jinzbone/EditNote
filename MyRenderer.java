package frame;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class MyRenderer extends DefaultListCellRenderer{
	/**
	 * 
	 */
	private static final long serialVersionUID = -887747211450991855L;
	
	public static final int FONTSIZE = 16;
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if(list.equals(FontDia.fontList)){
			if(index == 0)setFont(new Font("楷体",Font.PLAIN,FONTSIZE));
			else if(index == 1)setFont(new Font("黑体",Font.PLAIN,FONTSIZE));
			else if(index == 2)setFont(new Font("宋体",Font.PLAIN,FONTSIZE));
			else if(index == 3)setFont(new Font("微软雅黑",Font.PLAIN,FONTSIZE));
			else if(index == 4)setFont(new Font("仿宋",Font.PLAIN,FONTSIZE));
			return this;
		}
		else if(list.equals(FontDia.styleList)){
			if(index == 0)setFont(new Font("宋体",Font.PLAIN,FONTSIZE));
			else if(index == 1)setFont(new Font("宋体",Font.BOLD,FONTSIZE));
			else if(index == 2)setFont(new Font("宋体",Font.ITALIC,FONTSIZE));
			else if(index == 3)setFont(new Font("宋体",Font.BOLD + Font.ITALIC,FONTSIZE));
			return this;
		}
		else{
			setFont(new Font("Calibri",Font.PLAIN,14));
			return this;
		}
		
	}
	
}

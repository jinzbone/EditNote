/*****�Լ�д������Ի������������Ժ����.
 * 	     ����Ϥ���ֺ����
 * *****/
/*����Ի�����*/
////������JComboBox������.��JList��JTextField
////������������ٴ�����Ի���Ĭ����ʾ��Ӧ�����ϴε�����.
////������������ٴ����廹��ԭ��Ĭ�ϵģ�û�б���������.-->bug
////���߽�ֹ�ı�Ի����С;���߸ı��С���Ҳ��Ӧ����С.
////�Ի���������JList�еĸ����������������������ʾ����.
package frame;
/*****�Լ�д������Ի������������Ժ����.
 * 	     ����Ϥ���ֺ����
 * *****/
/*����Ի�����*/
////������������ٴ����廹��ԭ��Ĭ�ϵģ�û�б���������.-->bug
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**********����Ի�����**********/
public class FontDia extends JDialog implements ListSelectionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 50;
	private static final int HEIGHT = 100;
	public static FontDia fontDia;
	public static Font areaFont;
//	public static String lastFont = "����";
//	public static String lastStyle = "����";
//	public static String lastSize = "18";
	public static JPanel panel1;
	public static JPanel panel2;
	public static JPanel panel3;
	public static JPanel panel4;
	public static JPanel panel5;
	public static JList fontList;
	public static JList styleList;
	public static JList sizeList;
	public static JLabel fontLabel;
	public static JLabel styleLabel;
	public static JLabel sizeLabel;
	public static JLabel showLabel;
	public static JTextField fontField;
	public static JTextField styleField;
	public static JTextField sizeField;
	public static JButton okButton;
	public static JButton cancleButton;
	public static Font font = new Font("����",Font.PLAIN,15);
	private static String[] fontItems = {"����","����","����","΢���ź�","����"};
	private static String[] styleItems = {"����","����","��б","��ƫб��"};
	private static String[] sizeItems = {"8","10","12","14","16","18","20"};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		fontDia = new FontDia();
		fontDia.addComponent();
		fontDia.setVisible(true);
	}
	public FontDia(){
		this.setLocation(400,200);
		this.setSize(500,500);
		this.setTitle("����");	
		this.setResizable(false);
	}
	public void addComponent(){
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		//����JList��ÿһ�е�����
		fontList = new JList(fontItems);
		MyRenderer fontListRenderer = new MyRenderer();
		fontList.setCellRenderer(fontListRenderer);
		
		styleList = new JList(styleItems);
		MyRenderer styleListRenderer = new MyRenderer();
		styleList.setCellRenderer(styleListRenderer);
		
		sizeList = new JList(sizeItems);
		MyRenderer sizeListRenderer = new MyRenderer();
		sizeList.setCellRenderer(sizeListRenderer);
		
		fontList.setVisibleRowCount(4);
		styleList.setVisibleRowCount(4);
		sizeList.setVisibleRowCount(4);
		fontLabel = new JLabel("����");
		styleLabel = new JLabel("����");
		sizeLabel = new JLabel("�ֺ�");
		showLabel = new JLabel("ʾ���ı���");
//		fontField = new JTextField(lastFont);
//		styleField = new JTextField(lastStyle);
//		sizeField = new JTextField(lastSize);
		fontField = new JTextField("����");
		styleField = new JTextField("����");
		sizeField = new JTextField("18");
		showLabel.setFont(getFont());
		okButton = new JButton("ȷ��");
		cancleButton = new JButton("ȡ��");
		/*������*/
		JScrollPane fontScroller = new JScrollPane(fontList);
		JScrollPane styleScroller = new JScrollPane(styleList);
		JScrollPane sizeScroller = new JScrollPane(sizeList);
		fontScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		fontScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		styleScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		styleScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sizeScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sizeScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		/*����*/
		this.setLayout(null);
		panel1.setBounds(0,0,this.getWidth(),20);
		panel1.setLayout(new GridLayout(1,3));
		panel2.setBounds(0,20,this.getWidth(),20);
		panel2.setLayout(new GridLayout(1,3));
		panel3.setBounds(0,40,this.getWidth(),85);
		panel3.setLayout(new GridLayout(1,3));
		panel4.setBounds(0,125,this.getWidth(),100);
		panel5.setBounds(0,225,this.getWidth(),50);
		/*add*/
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.add(panel5);
		panel1.add(fontLabel);
		panel1.add(styleLabel);
		panel1.add(sizeLabel);
		panel2.add(fontField);
		panel2.add(styleField);
		panel2.add(sizeField);
		panel3.add(fontScroller);
		panel3.add(styleScroller);
		panel3.add(sizeScroller);
		panel4.add(showLabel);
		panel5.add(okButton);
		panel5.add(cancleButton);
		/*����*/
		fontList.addListSelectionListener(this);
		styleList.addListSelectionListener(this);
		sizeList.addListSelectionListener(this);
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MyFrame.area.setFont(getFont());
				FontDia.areaFont = getFont();
				fontDia.dispose();
			}
		});
		cancleButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fontDia.dispose();
			}
		});
}
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == fontList){
			fontField.setText(fontList.getSelectedValue().toString());
		}else if(e.getSource() == styleList){
			styleField.setText(styleList.getSelectedValue().toString());
		}else if(e.getSource() == sizeList){
			sizeField.setText(sizeList.getSelectedValue().toString());
		}
		showLabel.setFont(getFont());
		showLabel.setText("ʾ���ı���");
	}
	public Font getFont(){
		String font = fontField.getText();
//		lastFont = font;
		int style = Font.PLAIN;
		if(styleField.getText().equals("����")){
			style = Font.PLAIN;
//			lastStyle = "����";
		}
		else if(styleField.getText().equals("����")){
			style = Font.BOLD;
//			lastStyle = "����";
		}
		else if(styleField.getText().equals("��б")){
			style = Font.ITALIC;
//			lastStyle = "��б";
		}
		else if(styleField.getText().equals("��ƫб��")){
			style = Font.BOLD + Font.ITALIC;
//			lastStyle = "��ƫб��"; 
		}
		int size = Integer.parseInt(sizeField.getText());
//		lastSize = Integer.toString(size);
		Font myFont = new Font(font,style,size);
		return myFont;
	}
}
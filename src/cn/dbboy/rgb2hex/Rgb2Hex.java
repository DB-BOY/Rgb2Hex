package cn.dbboy.rgb2hex;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Rgb2Hex {

	public static void main(String[] args) {
		Rgb2Hex r = new Rgb2Hex();
		r.createView();
	}

	JFrame f;
	JTextArea tAlpha;
	JTextArea tRed;
	JTextArea tGreen;
	JTextArea tBlue;
	JTextArea tfHex;
	JLabel left,right,dot1,dot2,dot3;

	private void createView() {
		f = new JFrame("Rgb2Hex");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.setLayout(null);

		JPanel p1 = new JPanel();
		p1.setBounds(50, 50, 300, 60);
		// 输入框
		tfHex = new JTextArea("#ffffff");
		tfHex.setFont(new java.awt.Font("Default", 0, 25));
		tfHex.setForeground(Color.GRAY);
		tfHex.setOpaque(false); // 把panel的背景色设为透明
		tfHex.setEditable(false);
		tfHex.setBorder(new EmptyBorder(8, 8, 8, 8));
		p1.add(tfHex);

		JPanel p2 = new JPanel();
		p2.setBounds(10, 150, 300, 60);

		 left = new JLabel("argb(");
		// 把按钮加入面板
		p2.add(left);
		// alpha
		tAlpha = createTextField("255");
		p2.add(tAlpha);

		dot1 = new JLabel(",");
		p2.add(dot1);

		// red
		tRed = createTextField("255");
		p2.add(tRed);

		dot2 = new JLabel(",");
		p2.add(dot2);

		// green
		tGreen = createTextField("255");
		p2.add(tGreen);

		dot3 = new JLabel(",");
		p2.add(dot3);

		// blue
		tBlue = createTextField("255");
		p2.add(tBlue);

		right = new JLabel(");");
		p2.add(right);

		p1.setBackground(Color.white);
		p1.setOpaque(false); // 把panel的背景色设为透明
		p2.setBackground(Color.BLUE);
		p2.setOpaque(false); // 把panel的背景色设为透明

		// 设置面板背景颜色

		setBackGround();
		// 把面板加入窗口
		f.add(p1);
		f.add(p2);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	private JTextArea createTextField(String name) {
		JTextArea tBlue = new JTextArea(name);
		tBlue.setFont(new java.awt.Font("Default", 0, 13));
		tBlue.setForeground(Color.GRAY);
		tBlue.setBorder(new EmptyBorder(5, 5, 5, 5));
		setListener(tBlue);
		tBlue.setOpaque(false); // 把panel的背景色设为透明
		return tBlue;
	}

	private void setBackGround() {
		String r = tRed.getText().trim();
		String a = tAlpha.getText().trim();
		String g = tGreen.getText().trim();
		String b = tBlue.getText().trim();

		System.out.println(a + " " + r + " " + g + " " + b);
		int ca = getColor(a);
		int cr = getColor(r);
		int cg = getColor(g);
		int cb = getColor(b);

		System.out.println(ca + " " + cr + " " + cg + " " + cb);
		Color c = new Color(cr, cg, cb, ca);

		f.getContentPane().setBackground(c);
		
		c = new Color(255-cr,255-cg,255-cb);
		tfHex.setForeground(c);
		tfHex.setText(OperaColor.toHex(ca, cr, cg, cb));
		
		tRed.setForeground(c);
		tBlue.setForeground(c);
		tGreen.setForeground(c);
		tAlpha.setForeground(c);
		
		left.setForeground(c);
		right.setForeground(c);
		dot1.setForeground(c);
		dot2.setForeground(c);
		dot3.setForeground(c);
		
	}

	private int getColor(String color) {
		int result = 255;
		try{
			result = Integer.valueOf(color);	
		}catch(NumberFormatException e){
			result =255;
		}
		if(result>255){
			result=255;
		}
		return result;
	}

	private void setListener(JTextArea text) {
		text.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				System.out.println("remove " + text.getText());

				setBackGround();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				String x = text.getText();
				if(x.length()>3){
					return;
				}
				setBackGround();
				System.out.println("insert " + x);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				System.out.println("changed " + text.getText());

				setBackGround();
			}
		});
		text.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {
					System.out.println(text.getText());
					if(text.getText().length()>=3){
						text.setText(text.getText().substring(0,2));
					}
				} else {
					e.consume(); // 关键，屏蔽掉非法输入
				}
			}
		});
		// text.setDocument(new NumberLenghtLimitedDmt(3));
	}

}

package cn.dbboy.rgb2hex;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Rgb2Hex {

    public static void main(String[] args) {
        Rgb2Hex r = new Rgb2Hex();
        r.createView();
    }

    JFrame f;
    JTextArea numAlpha;
    JTextArea numRed;
    JTextArea numGreen;
    JTextArea numBlue;
    JTextArea hexArea;
    JTextArea alphaP;
    JLabel leftLabel, rightLabel, dot1Label, dot2Label, dot3Label, alphaLabel;
    JPanel hexPanel, numPanel, alphaPanel,colorPanel;

    private void createView() {
        f = new JFrame("Rgb2Hex");
        f.setSize(400, 500);
        f.setLocation(300, 200);
        f.setLayout(null);

        hexPanel = createPanel();
        hexPanel.setBounds(50, 10, 180, 50);
        // 输入框
        hexArea = new JTextArea("#ffffff");
        hexArea.setFont(new java.awt.Font("Default", 0, 25));
        hexArea.setForeground(Color.black);
        hexArea.setEditable(false);
        hexArea.setBorder(new EmptyBorder(8, 8, 8, 8));
        hexPanel.add(hexArea);

        //alpha百分比计算
        alphaPanel = createPanel();
        alphaPanel.setBounds(50, 70, 120, 32);
        alphaLabel = createLabel("Alpha百分比");

        alphaPanel.add(alphaLabel);
        alphaP = createAlphaText("100");
        alphaPanel.add(alphaP);

        //数字盘
        numPanel = createPanel();
        numPanel.setBounds(50, 100, 260, 32);
        leftLabel = createLabel("argb(");
        // 把按钮加入面板
        numPanel.add(leftLabel);
        // alpha
        numAlpha = createTextField("255");
        numPanel.add(numAlpha);

        dot1Label = createLabel(",");
        numPanel.add(dot1Label);

        // red
        numRed = createTextField("255");
        numPanel.add(numRed);

        dot2Label = createLabel(",");
        numPanel.add(dot2Label);

        // green
        numGreen = createTextField("255");
        numPanel.add(numGreen);

        dot3Label = createLabel(",");
        numPanel.add(dot3Label);

        // blue
        numBlue = createTextField("255");
        numPanel.add(numBlue);

        rightLabel = createLabel(");");
        numPanel.add(rightLabel);


		colorPanel = createPanel();
		colorPanel.setBounds(0, 132,400,500);
		colorPanel.setOpaque(true);

        setBackGround();
        // 把面板加入窗口
        f.add(hexPanel);
        f.add(numPanel);
        f.add(alphaPanel);
        f.add(colorPanel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setBackground(Color.white);
        f.setVisible(true);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false); // 把panel的背景色设为透明
        return panel;
    }

    private JLabel createLabel(String s) {
        JLabel label = new JLabel(s);
        label.setForeground(Color.black);
        return label;
    }

    private JTextArea createAlphaText(String name) {
        JTextArea textArea = new JTextArea(name);
        textArea.setFont(new java.awt.Font("Default", 0, 13));
        textArea.setForeground(Color.black);
        textArea.setBorder(new EmptyBorder(5, 5, 5, 5));
        textArea.setOpaque(false); // 把panel的背景色设为透明
        textArea.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                System.out.println("alpha-------remove " + textArea.getText());
                setAlpha(textArea.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                String x = textArea.getText();
                if (x.length() > 3) {
                    return;
                }
                setAlpha(x);
                System.out.println("alpha-------insert " + x);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println("alpha-------rchanged " + textArea.getText());
                setAlpha(textArea.getText());
            }
        });
        textArea.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {
                    System.out.println(textArea.getText());
                    if (textArea.getText().length() >= 3) {
                        textArea.setText(textArea.getText().substring(0, 2));
                    }
                } else {
                    e.consume(); // 关键，屏蔽掉非法输入
                }
            }
        });
        return textArea;
    }

    private void setAlpha(String text) {
        System.out.println(text);
        if (text == null || "".equals(text)) {
            return;
        }
        int number = Integer.valueOf(text);
        int tmp = (int) (number * 1f / 100 * 255);
        numAlpha.setText(String.valueOf(tmp));
    }

    private JTextArea createTextField(String name) {
        JTextArea textArea = new JTextArea(name);
        textArea.setFont(new java.awt.Font("Default", 0, 13));
        textArea.setForeground(Color.black);
        textArea.setBorder(new EmptyBorder(5, 5, 5, 5));
        setListener(textArea);
        textArea.setOpaque(false);
        return textArea;
    }

    /**
     * 设置背景颜色
     */
    private void setBackGround() {
        String r = numRed.getText().trim();
        String a = numAlpha.getText().trim();
        String g = numGreen.getText().trim();
        String b = numBlue.getText().trim();

        System.out.println("before_____" + a + " " + r + " " + g + " " + b);
        int ca = getColor(a);
        int cr = getColor(r);
        int cg = getColor(g);
        int cb = getColor(b);

        System.out.println("after_____" + ca + " " + cr + " " + cg + " " + cb);
        //RGBA
        Color c = new Color(cr, cg, cb, ca);

		colorPanel.setBackground(c);
        hexArea.setText(OperaColor.toHex(ca, cr, cg, cb));
//		c = new Color(255 - cr, 255 - cg, 255 - cb);
//		hexArea.setForeground(c);

//		numRed.setForeground(c);
//        numBlue.setForeground(c);
//        numGreen.setForeground(c);
//        numAlpha.setForeground(c);
//        alphaP.setForeground(c);
    }

    private int getColor(String color) {
        int result = 255;
        try {
            result = Integer.valueOf(color);
        } catch (NumberFormatException e) {
            result = 255;
        }
        if (result > 255) {
            result = 255;
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
                if (x.length() > 3) {
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
                    if (text.getText().length() >= 3) {
                        text.setText(text.getText().substring(0, 2));
                    }
                } else {
                    e.consume(); // 关键，屏蔽掉非法输入
                }
            }
        });
    }

}

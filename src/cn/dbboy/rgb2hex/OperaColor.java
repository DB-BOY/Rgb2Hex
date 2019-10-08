package cn.dbboy.rgb2hex;

import java.awt.Color;

public class OperaColor extends Color {  
    public OperaColor(int r, int g, int b) {  
        super(r, g, b);  
    }  

    public String getHex() {  
        return toHex(getAlpha(),getRed(), getGreen(), getBlue());  
    }  
  
    public static String toHex(int a ,int r, int g, int b) {  
        return "#"+toBrowserHexValue(a) + toBrowserHexValue(r) + toBrowserHexValue(g)  
                + toBrowserHexValue(b);  
    }  
    public static String toHex(int r, int g, int b) {  
        return toHex(0,r,g,b);
    }

    public static String toBrowserHexValue(int number) {
        StringBuilder builder = new StringBuilder(Integer.toHexString(number & 0xff));
        if(builder.length()<2){
            while (builder.length() < 2) {
                builder.append("0");
            }
            builder.reverse();
        }
        return builder.toString().toUpperCase();
    }  
    public static void main(String[] args) {  
        System.out.println(toHex(22,11,160,219));
        System.out.println(toHex(112,160,219));
        for (int i = 0; i < 256; i++) {
            System.out.println(toBrowserHexValue(i));
        }
    }
}  
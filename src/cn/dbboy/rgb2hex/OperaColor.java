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
  
    private static String toBrowserHexValue(int number) {  
        StringBuilder builder = new StringBuilder(  
                Integer.toHexString(number & 0xff));  
        while (builder.length() < 2) {  
            builder.append("0");  
        }  
        return builder.toString().toUpperCase();  
    }  
    public static void main(String[] args) {  
        System.out.println(toHex(22,112,160,219));  
        System.out.println(toHex(112,160,219));  
    }  
}  
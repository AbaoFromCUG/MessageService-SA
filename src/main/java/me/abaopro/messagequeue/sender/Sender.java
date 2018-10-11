package me.abaopro.messagequeue.sender;

import me.abaopro.messagequeue.messagequeque.HelloMS;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: abao
 */
public class Sender {

    public static void main(String[] args) {


        initGobalFont(new Font(Font.SANS_SERIF, Font.LAYOUT_NO_LIMIT_CONTEXT, 18));
        initLogic();
        JFrame frame = new JFrame("发布管理平台");
        frame.move(100, 100);
        frame.setContentPane(new SenderFrame().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static void initLogic() {

        HelloMS.getInstance("LTAIFW21KXf7LkWD",
                "c0uTjrg8vMGuNwXnNkBMcvkcQyutei", "https://1357729245860456.mns.cn-hangzhou.aliyuncs.com/");
//        HelloMS.getInstance().createQueue("ithome");
//        HelloMS.getInstance().createQueue("cughome");
//        HelloMS.getInstance().createQueue("javahome");
//        HelloMS.getInstance().createQueue("wuhanhome");
    }


    public static void initGobalFont(Font font) {
        FontUIResource fontResource = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
//                System.out.println(key);
                UIManager.put(key, fontResource);
            }
        }
    }


}


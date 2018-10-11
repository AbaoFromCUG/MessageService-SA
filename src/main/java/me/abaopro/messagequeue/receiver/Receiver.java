package me.abaopro.messagequeue.receiver;

import me.abaopro.messagequeue.messagequeque.HelloMS;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: abao
 */
public class Receiver {
    public static void main(String[] args) {
        HelloMS.getInstance("LTAIFW21KXf7LkWD",
                "c0uTjrg8vMGuNwXnNkBMcvkcQyutei", "https://1357729245860456.mns.cn-hangzhou.aliyuncs.com/");

        JFrame frame = new JFrame("接收订阅端");
        frame.move(100,100);

        frame.setContentPane(new ReceiverFrame().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

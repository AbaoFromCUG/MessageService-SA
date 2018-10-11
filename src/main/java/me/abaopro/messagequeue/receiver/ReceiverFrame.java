package me.abaopro.messagequeue.receiver;

import me.abaopro.messagequeue.messagequeque.HelloMS;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: abao
 */
public class ReceiverFrame {

    public ReceiverFrame() {


        topicMap = new HashMap<>();
        topicMap.put( "ithome","IT世界");
        topicMap.put( "cughome","地大之家");
        topicMap.put( "javahome","Java博客");
        topicMap.put( "wuhanhome","武汉风云");

        String[] headers = {"主题", "消息内容", "接收时间"};
        Object[][] data = {

        };
        table.setModel(new DefaultTableModel(data, headers));

        btnit.setSelected(true);
        btnjava.setSelected(true);
        btncug.setSelected(true);
        btnwuhan.setSelected(true);


        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] headers = {"主题", "消息内容", "接收时间"};
                Object[][] data = {

                };
                table.setModel(new DefaultTableModel(data, headers));
            }
        });
        startThread();
    }

    public void startThread() {
        thread_it = creakeReceiveThread("ithome");
        thread_it.start();
        thread_cug = creakeReceiveThread("cughome");
        thread_cug.start();
        thread_java = creakeReceiveThread("javahome");
        thread_java.start();
        thread_wuhan = creakeReceiveThread("wuhanhome");
        thread_wuhan.start();
    }

    private void invokeAdd(final String t, final String m) {
        //子线程的数据变更显示到界面层
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> newMessage = new ArrayList<>();
                newMessage.add(t);
                newMessage.add(m);
                addRow(newMessage);
            }
        });
    }

    private Thread creakeReceiveThread(final String topic) {
        return new Thread() {
            @Override
            public void run() {
                while (true) {
                    String msg;
                    try {
                        msg = HelloMS.getInstance().popMessage(topic);
                    } catch (NullPointerException e) {
                        System.out.println("线程:暂无新消息，接收超时");
                        continue;
                    }
                    System.out.println(topic + msg);

                    final String t =topic;
                    final String m = msg;
                    if (t.isEmpty()) {
                        System.out.println("线程:空的消息");
                        continue;
                    }
                    invokeAdd(t, m);

                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }

            }
        };
    }

    public void addRow(ArrayList<String> strings) {
        if (strings.get(0) == "ithome" && btnit.isSelected()) {
            strings.set(0, topicMap.get(strings.get(0)));
            strings.add(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date())));
            ((DefaultTableModel) table.getModel()).addRow(strings.toArray());
        }
        if (strings.get(0) == "cughome" && btncug.isSelected()) {
            strings.set(0, topicMap.get(strings.get(0)));
            strings.add(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date())));
            ((DefaultTableModel) table.getModel()).addRow(strings.toArray());
        }
        if (strings.get(0) == "javahome" && btnjava.isSelected()) {
            strings.set(0, topicMap.get(strings.get(0)));
            strings.add(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date())));
            ((DefaultTableModel) table.getModel()).addRow(strings.toArray());
        }
        if (strings.get(0) == "wuhanhome" && btnwuhan.isSelected()) {
            strings.set(0, topicMap.get(strings.get(0)));
            strings.add(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date())));
            ((DefaultTableModel) table.getModel()).addRow(strings.toArray());
        }





    }

    public JPanel panel1;
    private JRadioButton btnit;
    private JRadioButton btncug;
    private JRadioButton btnjava;
    private JRadioButton btnwuhan;
    private JTable table;
    private JButton btnClear;

    private Thread thread_it;
    private Thread thread_cug;
    private Thread thread_java;
    private Thread thread_wuhan;

    private HashMap<String, String> topicMap;
    private String[] topicList = {"ithome", "cughome", "javahome", "wuhanhome"};
}

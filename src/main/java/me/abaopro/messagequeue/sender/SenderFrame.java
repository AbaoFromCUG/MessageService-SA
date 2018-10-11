package me.abaopro.messagequeue.sender;

import me.abaopro.messagequeue.messagequeque.HelloMS;

import javax.management.InstanceNotFoundException;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: abao
 */
public class SenderFrame {
    public JPanel mainPanel;
    private JTable sendedTable;
    private JTextArea textInput;
    private JComboBox topicList;
    private JButton btn_send;
    private HashMap<String,String> topicMap;
    /*
    IT世界
地大之家
Java博客
武汉风云
     */
    public SenderFrame(){

        String[] headers = {"主题", "消息内容", "发布时间"};
        Object[][] data = {

        };
        sendedTable.setModel(new DefaultTableModel(data,headers));

        topicMap = new HashMap<>();
        topicMap.put("IT世界","ithome");
        topicMap.put("地大之家","cughome");
        topicMap.put("Java博客","javahome");
        topicMap.put("武汉风云","wuhanhome");

        btn_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String topic=topicList.getSelectedItem().toString();
                String content=textInput.getText();
                System.out.println(content);
                if(content.trim().equals("")){
                    JOptionPane.showMessageDialog(mainPanel,"请不要发送空的消息","警告",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    HelloMS.getInstance().sendMessage(topicMap.get(topic),content);
                    ArrayList<String> list = new ArrayList<>();
                    list.add(topic);
                    list.add(content);
                    addRow(list);
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(mainPanel, "what!获取消息失败", "错误", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    public void addRow(ArrayList<String> strings) {
        strings.add((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
        ((DefaultTableModel) sendedTable.getModel()).addRow(strings.toArray());
    }



}

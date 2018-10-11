package me.abaopro.messagequeue.messagequeque;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.QueueMeta;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: abao
 */
public class HelloMS {
    static CloudAccount account;
    static MNSClient mnsClient;
    static HelloMS ms;

    private HelloMS() {

    }

    static public HelloMS getInstance()

    {
        return ms;
    }

    static public HelloMS getInstance(String accessId, String accessKey, String accessEndpoint) {
        if (ms != null) {
            return ms;
        }

        ms = new HelloMS();
        account = new CloudAccount(accessId,accessKey,accessEndpoint);
        mnsClient = account.getMNSClient();
        return ms;
    }

    static public void close() {
        mnsClient.close();
    }

    public void createQueue(String queueName) {
        QueueMeta meta = new QueueMeta(); //生成本地QueueMeta属性，有关队列属性详细介绍见https://help.aliyun.com/document_detail/27476.html
        meta.setQueueName(queueName);  // 设置队列名
        meta.setPollingWaitSeconds(15);
        meta.setMaxMessageSize(2048L);


        CloudQueue queue = mnsClient.createQueue(meta);
    }

    public void sendMessage(String s, String messageContent) {

        CloudQueue queue = mnsClient.getQueueRef(s);
        Message newMessage = new Message();
        newMessage.setMessageBody(messageContent);
//        message.setPriority(8);
        Message putMsg = queue.putMessage(newMessage);
    }

    public String receiveMessage(String s) {
        CloudQueue queue = mnsClient.getQueueRef(s);
        Message message = queue.popMessage();
        return message.getMessageBodyAsString();
    }


    public String popMessage(String s) {
        CloudQueue queue = mnsClient.getQueueRef(s);
        Message message = queue.popMessage();
        String content = message.getMessageBodyAsString();
        queue.deleteMessage(message.getReceiptHandle());
        return content;
    }

    public List<String> getAllQueue(){
        List<String> ret=new ArrayList<>();
        return ret;
    }

}

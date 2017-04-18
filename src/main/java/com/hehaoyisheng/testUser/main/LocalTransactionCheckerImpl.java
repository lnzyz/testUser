package com.hehaoyisheng.testUser.main;

import java.util.zip.CRC32;

import org.slf4j.Logger;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionChecker;
import com.aliyun.openservices.ons.api.transaction.TransactionStatus;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.log.ClientLogger;


public class LocalTransactionCheckerImpl implements LocalTransactionChecker{
	private final static Logger log = ClientLogger.getLog();
    
	public TransactionStatus check(Message msg) {
		 //消息ID(有可能消息体一样，但消息ID不一样, 当前消息属于Half 消息，所以消息ID在控制台无法查询)
        String msgId = msg.getMsgID();
        //消息体内容进行crc32, 也可以使用其它的方法如MD5
        long crc32Id = HashUtil.crc32Code(msg.getBody());
        //消息ID、消息本 crc32Id主要是用来防止消息重复
        //如果业务本身是幂等的, 可以忽略, 否则需要利用msgId或crc32Id来做幂等
        //如果要求消息绝对不重复, 推荐做法是对消息体使用crc32或md5来防止重复消息.
        //业务自己的参数对象, 这里只是一个示例, 实际需要用户根据情况来处理
        Object businessServiceArgs = new Object();
        TransactionStatus transactionStatus = TransactionStatus.Unknow;
        /*try {
            if (isCommit) {
                //本地事务已成功、提交消息
                transactionStatus = TransactionStatus.CommitTransaction;
            } else {
                //本地事务已失败、回滚消息
                transactionStatus = TransactionStatus.RollbackTransaction;
            }
        } catch (Exception e) {
            log.error("Message Id:{}", msgId, e);
        }
        log.warn("Message Id:{}transactionStatus:{}", msgId, transactionStatus.name());*/
        return transactionStatus;
	}

}

class HashUtil {
    public static long crc32Code(byte[] bytes) {
        CRC32 crc32 = new CRC32();
        crc32.update(bytes);
        return crc32.getValue();
    }
}

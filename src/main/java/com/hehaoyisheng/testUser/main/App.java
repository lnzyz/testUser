package com.hehaoyisheng.testUser.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hehaoyisheng.testSeller.main.QueryStock;

/**
 * Hello world!
 * 
 */
public class App {
	private static ClassPathXmlApplicationContext context = null;
	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("Dubbo-Consumer.xml");  
		context.start();
		
		QueryStock demoService = (QueryStock) context.getBean("demoService"); 
		System.out.println(demoService.queryStockCount(1));
	}
	
	/*context = new ClassPathXmlApplicationContext("Producer.xml");
	Producer producer = (Producer) context.getBean("producer");
	Message msg = new Message("buy_hehao", "A", "test".getBytes());
    //send message
    try {
        SendResult sendResult = producer.send(msg);
        assert sendResult != null;
        System.out.println("send success: " + sendResult.getMessageId());
    }catch (ONSClientException e) {
        System.out.println("send failed");
    }*/
}

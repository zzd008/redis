package cn.jxust.bigdata.redis.string;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * ������
 * ��Ϊredis�Ĳ�����ԭ���Եģ�����̷߳��ʵ��Ǹ��º�Ľ��
 */
public class Counter {
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		pool.execute(new Increase("counter"));//�����߳�ȥ��1
		pool.execute(new Increase("counter"));
		pool.execute(new ShowCounter("counter"));//һ���߳�ȥ��
		
	}
}

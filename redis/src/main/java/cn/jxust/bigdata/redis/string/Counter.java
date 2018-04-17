package cn.jxust.bigdata.redis.string;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * 计数器
 * 因为redis的操作是原子性的，多个线程访问的是更新后的结果
 */
public class Counter {
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		pool.execute(new Increase("counter"));//两个线程去加1
		pool.execute(new Increase("counter"));
		pool.execute(new ShowCounter("counter"));//一个线程去读
		
	}
}

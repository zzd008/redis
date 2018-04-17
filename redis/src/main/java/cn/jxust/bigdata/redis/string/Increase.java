package cn.jxust.bigdata.redis.string;

import redis.clients.jedis.Jedis;

public class Increase implements Runnable {

	private String key;
	private Jedis jedis;
	
	public Increase(String key) {
		this.key=key;
	}

	public void run() {
		jedis=new Jedis("localhost", 6379);
		while(true){
			System.out.println(Thread.currentThread().getName()+"-----"+"¼Ó1");
			jedis.incr(key);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

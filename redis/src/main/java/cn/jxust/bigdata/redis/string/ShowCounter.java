package cn.jxust.bigdata.redis.string;

import redis.clients.jedis.Jedis;

public class ShowCounter implements Runnable {

	private String key;
	private Jedis jedis;
	
	public ShowCounter(String string) {
		this.key=key;
	}

	public void run() {
		jedis=new Jedis("localhost", 6379);
		while(true){
			String counter = jedis.get("counter");
			System.out.println("当前计数器为："+counter);
			
		}
	}

}

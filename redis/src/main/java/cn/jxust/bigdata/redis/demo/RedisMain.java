package cn.jxust.bigdata.redis.demo;

import redis.clients.jedis.Jedis;

/*
 * redis客户端api使用--jedis
 */
public class RedisMain {
	public static void main(String[] args) {
		Jedis jedis=new Jedis("localhost", 6379);
		String response = jedis.ping();//ping服务端
		System.out.println(response);
		jedis.del("zzd");//删除key
	}
}

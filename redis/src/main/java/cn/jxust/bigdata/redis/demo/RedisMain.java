package cn.jxust.bigdata.redis.demo;

import redis.clients.jedis.Jedis;

/*
 * redis�ͻ���apiʹ��--jedis
 */
public class RedisMain {
	public static void main(String[] args) {
		Jedis jedis=new Jedis("localhost", 6379);
		String response = jedis.ping();//ping�����
		System.out.println(response);
		jedis.del("zzd");//ɾ��key
	}
}

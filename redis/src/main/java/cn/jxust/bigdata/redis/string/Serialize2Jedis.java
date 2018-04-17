package cn.jxust.bigdata.redis.string;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;

/*
 * 序列化对象到jedis
 */
public class Serialize2Jedis {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Jedis jedis=new Jedis("localhost",6379);
		Person p=new Person("zzd", "21");
		
		//第一种，将person对象toString，放到jedis中，没办法反序列化
		jedis.set("user:zzd:string", p.toString());
		System.out.println(jedis.get("user:zzd:string"));
		
		//第二种，使用java的序列化,存二进制数组
		jedis.set("user:zzd:obj".getBytes(), person2Byte(p));
		byte[] bs = jedis.get("user:zzd:obj".getBytes());
		Person p1=byte2Person(bs);//反序列化
		System.out.println(p1);
		
		//第三种，将对象转成json
		String json=new Gson().toJson(p);
		jedis.set("user:zzd:json",json );
		String value=jedis.get("user:zzd:json");
		Person p2=(Person)new Gson().fromJson(value, Person.class);
		System.out.println(p2);
		
		
	}

	//反序列化
	private static Person byte2Person(byte[] bs) throws IOException, ClassNotFoundException {
		ObjectInputStream ois=new ObjectInputStream(new ByteArrayInputStream(bs));
		Person p = (Person) ois.readObject();
		return p;
	}

	//序列化
	private static byte[] person2Byte(Person p) throws FileNotFoundException, IOException {
//		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("C:/obj.txt"));//将对象序列化到文件中
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos=new ObjectOutputStream(bos);
		oos.writeObject(p);//将对象序列化到二进制输出流中
		return bos.toByteArray();//返回二进制数组
	}
	
	
}

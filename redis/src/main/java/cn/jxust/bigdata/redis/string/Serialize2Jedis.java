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
 * ���л�����jedis
 */
public class Serialize2Jedis {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Jedis jedis=new Jedis("localhost",6379);
		Person p=new Person("zzd", "21");
		
		//��һ�֣���person����toString���ŵ�jedis�У�û�취�����л�
		jedis.set("user:zzd:string", p.toString());
		System.out.println(jedis.get("user:zzd:string"));
		
		//�ڶ��֣�ʹ��java�����л�,�����������
		jedis.set("user:zzd:obj".getBytes(), person2Byte(p));
		byte[] bs = jedis.get("user:zzd:obj".getBytes());
		Person p1=byte2Person(bs);//�����л�
		System.out.println(p1);
		
		//�����֣�������ת��json
		String json=new Gson().toJson(p);
		jedis.set("user:zzd:json",json );
		String value=jedis.get("user:zzd:json");
		Person p2=(Person)new Gson().fromJson(value, Person.class);
		System.out.println(p2);
		
		
	}

	//�����л�
	private static Person byte2Person(byte[] bs) throws IOException, ClassNotFoundException {
		ObjectInputStream ois=new ObjectInputStream(new ByteArrayInputStream(bs));
		Person p = (Person) ois.readObject();
		return p;
	}

	//���л�
	private static byte[] person2Byte(Person p) throws FileNotFoundException, IOException {
//		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("C:/obj.txt"));//���������л����ļ���
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos=new ObjectOutputStream(bos);
		oos.writeObject(p);//���������л����������������
		return bos.toByteArray();//���ض���������
	}
	
	
}

package cn.jxust.bigdata.redis.list;

import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * redis数据模型：<key,List(xxx,xxx)>
 * 天龙八部外传-麦当劳风云
 */
public class ListMain {
    public static void main(String[] args) {
        //创建一个Redis的客户端
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.del("柜台1");

        //鸠摩智，虚竹，段誉，乔峰 排队买肯德基
        jedis.lpush("柜台1", "乔峰", "段誉", "虚竹", "鸠摩智");//从左面放
        for (String name : jedis.lrange("柜台1", 0, -1)) {//从左面读取，从第1个开始，-1表示读取全部
            System.out.print(name + "  ");
        }
        System.out.println();

        //剧情：新来一个人 王语嫣，插队，到第一名。
        jedis.rpush("柜台1", "王语嫣");
        List<String> list = jedis.lrange("柜台1", 0, -1);
        for (String name : list) {
            System.out.print(name + "  ");
        }
        System.out.println();

        //剧情：鸠摩智很不高兴，正好慕容复来了，说：慕容兄，你插我前面
        jedis.linsert("柜台1", BinaryClient.LIST_POSITION.AFTER, "鸠摩智", "慕容复");//从左面-->，在鸠摩智后面插入慕容复
        List<String> list1 = jedis.lrange("柜台1", 0, -1);
        for (String name : list1) {
            System.out.print(name + "  ");
        }
        System.out.println();

        //剧情：看到慕容复插队大家很生气，正好阿紫和游坦之。让阿紫和游坦之依次插到虚竹的后面
        jedis.linsert("柜台1", BinaryClient.LIST_POSITION.BEFORE, "虚竹", "阿紫");
        jedis.linsert("柜台1", BinaryClient.LIST_POSITION.BEFORE, "阿紫", "游坦之");
        List<String> list2 = jedis.lrange("柜台1", 0, -1);
        for (String name : list2) {
            System.out.print(name + "  ");
        }
        System.out.println();

        //剧情：插队不文明，为了遏制这种不文明的现象，大决决定打一架。  鸠摩智被打跑了。
        jedis.lpop("柜台1");//弹出最左面的一个
        for (String name : jedis.lrange("柜台1", 0, -1)) {
            System.out.print(name + "  ");
        }
        System.out.println();

        //剧情：慕容复一看情况不好，以表哥的身份忽悠王语嫣，把王语嫣打伤。
        jedis.rpop("柜台1");
        for (String name : jedis.lrange("柜台1", 0, -1)) {
            System.out.print(name + "  ");
        }
        System.out.println();

        //剧情：在大家打架的时候，我偷偷插队，买了肯德基。
        jedis.rpush("柜台1", "井中月");
        for (String name : jedis.lrange("柜台1", 0, -1)) {
            System.out.print(name + "  ");
        }
        System.out.println();

        //剧情；等我买了肯德基，慕容复被打跑了
        jedis.lpop("柜台1");
        for (String name : jedis.lrange("柜台1", 0, -1)) {
            System.out.print(name + "  ");
        }
        System.out.println();

        //剧情：星宿老怪 突然来了，把 阿紫和游坦之同时弄走了。
        String result = jedis.ltrim("柜台1", 2, 5);//修剪，从左边，第三个到第6个留下，其他的不要了
        if ("OK".equals(result)) {
            for (String name : jedis.lrange("柜台1", 0, -1)) {
                System.out.print(name + "  ");
            }
        }
        System.out.println("");

        //剧情：这时候，乔峰三人发现了我，与我大战三百回合，我全身而退
        String res = jedis.ltrim("柜台1", 0, 2);
        if ("OK".equals(res)) {
            for (String name : jedis.lrange("柜台1", 0, -1)) {
                System.out.print(name + "  ");
            }
        }
        
        jedis.lset("柜台1", 0, "zzd");//给list中左面第一个设置值
    }
}

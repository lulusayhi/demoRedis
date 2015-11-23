package demoRedis;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class DemoKyeValue {
	 public static void main(String[] args) {
	      //连接本地的 Redis 服务
	      Jedis jedis = new Jedis("localhost");
	      jedis.auth("20051225");
	      System.out.println("Connection to server sucessfully");
	      
	      jedis.blpop(1, "hello");
	      jedis.blpop(1, "hello1");
	     // 获取数据并输出
	      Set<String> keys = jedis.keys("*");
        Iterator<String> t1=keys.iterator() ;  
        while(t1.hasNext()){  
            Object obj1=t1.next();  
            System.out.println(obj1);  
        }
}
}
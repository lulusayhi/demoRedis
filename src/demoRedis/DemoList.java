package demoRedis;

import java.util.List;

import redis.clients.jedis.Jedis;

public class DemoList {
	public static void main(String[] args) {
	      //���ӱ��ص� Redis ����
	      Jedis jedis = new Jedis("localhost");
	      jedis.auth("20051225");
	      System.out.println("Connection to server sucessfully");
	      //�洢���ݵ��б���
	      jedis.lpush("tutorial-list", "Redis");
	      jedis.lpush("tutorial-list", "Mongodb");
	      jedis.lpush("tutorial-list", "Mysql");
	     // ��ȡ�洢�����ݲ����
	     List<String> list = jedis.lrange("tutorial-list", 0 ,5);
	     for(int i=0; i<list.size(); i++) {
	       System.out.println("Stored string in redis:: "+list.get(i));
	     }
	 }
}

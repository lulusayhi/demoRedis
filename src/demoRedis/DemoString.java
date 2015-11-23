package demoRedis;

import redis.clients.jedis.Jedis;

public class DemoString {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1",6379);
		jedis.auth("20051225");
	    jedis.set("foo", "bar");  
	    String value = jedis.get("foo");  
	    System.out.println(value);
	}

}

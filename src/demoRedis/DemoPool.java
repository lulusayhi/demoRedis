package demoRedis;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class DemoPool {
	public static void main(String[] args) {
		JedisPoolConfig config = new JedisPoolConfig();
		//控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        config.setMaxActive(500);
        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
        config.setMaxIdle(5);
        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
        config.setMaxWait(1000 * 100);
        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        config.setTestOnBorrow(true);
        
		JedisPool pool = new JedisPool(config, "localhost",6379,1000 * 100,"20051225");
		
        Jedis jedis = pool.getResource();
		try {
		  /// ... do stuff here ... for example
		  jedis.set("foo", "bar");
		  String foobar = jedis.get("foo");
		  System.out.println(foobar);
		  jedis.zadd("sose", 0, "car"); jedis.zadd("sose", 0, "bike");
		  Set<String> sose = jedis.zrange("sose", 0, -1);
		  Iterator<String> t1=sose.iterator() ;  
	        while(t1.hasNext()){  
	            Object obj1=t1.next();  
	            System.out.println(obj1);  
	        }
		} catch (Exception e) {
		    // returnBrokenResource when the state of the object is unrecoverable
		    if (null != jedis) {
		        pool.returnBrokenResource(jedis);
		        jedis = null;
		    }
		} finally {
		  /// ... it's important to return the Jedis instance to the pool once you've finished using it
		  if (null != jedis)
		    pool.returnResource(jedis);
		}
	}
}

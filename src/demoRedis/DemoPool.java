package demoRedis;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class DemoPool {
	public static void main(String[] args) {
		JedisPoolConfig config = new JedisPoolConfig();
		//����һ��pool�ɷ�����ٸ�jedisʵ����ͨ��pool.getResource()����ȡ��
        //�����ֵΪ-1�����ʾ�����ƣ����pool�Ѿ�������maxActive��jedisʵ�������ʱpool��״̬Ϊexhausted(�ľ�)��
        config.setMaxActive(500);
        //����һ��pool����ж��ٸ�״̬Ϊidle(���е�)��jedisʵ����
        config.setMaxIdle(5);
        //��ʾ��borrow(����)һ��jedisʵ��ʱ�����ĵȴ�ʱ�䣬��������ȴ�ʱ�䣬��ֱ���׳�JedisConnectionException��
        config.setMaxWait(1000 * 100);
        //��borrowһ��jedisʵ��ʱ���Ƿ���ǰ����validate���������Ϊtrue����õ���jedisʵ�����ǿ��õģ�
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

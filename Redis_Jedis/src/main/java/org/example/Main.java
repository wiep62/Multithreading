package org.example;

import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import redis.clients.jedis.Jedis;

public class Main {
    public static void main(String[] args) {
//docker run --rm --name skill-redis -p 127.0.0.1:6379:6379/tcp -d redis
        Jedis jedis = new Jedis("localhost", 6379);

        jedis.set("key","value");
        System.out.println(jedis.get("key"));
    }
        // connects to 127.0.0.1:6379 by default
     /*   RedissonClient redisson = Redisson.create();

        // implements java.util.List
        RList<String> list = redisson.getList("myList");
        list.add("1");
        list.add("2");
        list.add("3");

        boolean contains = list.contains("1");

        System.out.println("List size: " + list.size());
        System.out.println("Is list contains value '1': " + contains);

        for (String element : list) {
            System.out.println("List element: " + element);
        }

        redisson.shutdown();    }*/
}
package com.people.config;


import com.people.domain.entity.PeopleEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
    @Bean
    RedisTemplate<String, PeopleEntity> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, PeopleEntity> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}

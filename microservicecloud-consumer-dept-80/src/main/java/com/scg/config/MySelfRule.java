package com.scg.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Eliot
 * @date: 2020/10/10
 **/

@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule() {
        return new RoundRobinRule();// Ribbon默认是轮询，我自定义为随机
        //return new RandomRuleMy();// 我自定义为每台机器5次
    }
}

package com.example.SPRING_MYSQL.Configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Map_Ans {


    @Bean
    @Scope("prototype")
    public Map<String, Object> getAns()
    {
        return new HashMap<>();
    }
}

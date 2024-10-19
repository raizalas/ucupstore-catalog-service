package com.ucupstore.catalogservice.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "book")
public class GreetingProperties {
    /**
     * A message to welcome users.
     */
    private String greeting;

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

}

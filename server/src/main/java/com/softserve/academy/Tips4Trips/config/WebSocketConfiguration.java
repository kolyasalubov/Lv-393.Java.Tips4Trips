package com.softserve.academy.Tips4Trips.config;
import com.softserve.academy.Tips4Trips.interceptor.HttpHandshakeInterceptor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import static com.softserve.academy.Tips4Trips.config.PropertiesConfig.getPropertyValue;


@Configuration
@EnableWebSocketMessageBroker
@Component
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    private static final Logger logger = Logger.getLogger(WebSocketConfiguration.class);


    private HttpHandshakeInterceptor httpHandshakeInterceptor;

    @Autowired
    public WebSocketConfiguration(HttpHandshakeInterceptor httpHandshakeInterceptor){
        this.httpHandshakeInterceptor = httpHandshakeInterceptor;

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/chat");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/ws")
                //todo Fix Acess - Controll
                //.setAllowedOrigins("*")
                .setAllowedOrigins(getPropertyValue("Access-Control-Allow-Origin"))
                .withSockJS()
                .setInterceptors(httpHandshakeInterceptor);
    }
}

package br.com.github.rxjava.productapi.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfiguration {

    @Autowired
    RequestMappingHandlerAdapter requestMappingHandlerAdapter;


    @PostConstruct
    public void init() {
        List<HandlerMethodReturnValueHandler> spring = new ArrayList(requestMappingHandlerAdapter.getReturnValueHandlers());
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>(spring);
        handlers.add(0,observableReturnValueHandler());
        requestMappingHandlerAdapter.setReturnValueHandlers(handlers);
    }

    @Bean
    public HandlerMethodReturnValueHandler observableReturnValueHandler() {
        return new ObservableReturnValueHandler();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}

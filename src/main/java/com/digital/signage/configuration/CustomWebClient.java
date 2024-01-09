package com.digital.signage.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
public class CustomWebClient {

    private Logger logger = LoggerFactory.getLogger(CustomWebClient.class);
    @Value("${service-gateway.baseUrl}")
    private String baseUrl;

    @Bean
    public WebClient webClient() {

        baseUrl = "http://k8s-neuro-ingressp-74011e45bf-569147679.ap-southeast-1.elb.amazonaws.com";
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofMillis(10000))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                .doOnConnected(connection -> connection
                        .addHandlerLast(new ReadTimeoutHandler(10000, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(10000, TimeUnit.MILLISECONDS)));

        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.getSubtype())
                .filter(logRequest())
                .clientConnector(new ReactorClientHttpConnector(httpClient)).build();
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            logger.info("Request: {} {} ", clientRequest.method(), clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> logger.info("{}: {}", name, value)));
            if (clientRequest.body() != null) {
                logger.info("Request Body: {}", clientRequest.body());
                try {
                    String requestBodyStr = new ObjectMapper().writeValueAsString(clientRequest.body());
                    logger.info("Request Body: {}", requestBodyStr);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return Mono.just(clientRequest);
        });
    }

}

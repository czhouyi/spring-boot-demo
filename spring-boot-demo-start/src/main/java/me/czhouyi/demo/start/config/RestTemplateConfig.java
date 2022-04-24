package me.czhouyi.demo.start.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * RestTemplate配置类
 *
 * @author czhouyi@gmail.com
 */
@Configuration
public class RestTemplateConfig {

    @Value("${rest.connect.timeout:5000}")
    private Integer connectTimeout;
    @Value("${rest.read.timeout:65000}")
    private Integer readTimeout;
    @Value("${rest.short-read.timeout:5000}")
    private Integer shortReadTimeout;
    @Value("${rest.max-idle-connections:200}")
    private Integer maxIdleConnections;
    @Value("${rest.keep-alive-duration:300000}")
    private Integer keepAliveDuration;

    @Bean
    public RestTemplate restTemplate() {
        OkHttpClient httpClient = new OkHttpClient().newBuilder()
                .connectionPool(pool())
                .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .hostnameVerifier((hostname, session) -> true)
                .build();
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory(httpClient));
    }

    @Bean
    public RestTemplate shortRestTemplate() {
        OkHttpClient httpClient = new OkHttpClient().newBuilder()
                .connectionPool(pool())
                .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(shortReadTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(shortReadTimeout, TimeUnit.MILLISECONDS)
                .hostnameVerifier((hostname, session) -> true)
                .build();
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory(httpClient));
    }

    private ConnectionPool pool() {
        return new ConnectionPool(maxIdleConnections, keepAliveDuration, TimeUnit.MILLISECONDS);
    }

}
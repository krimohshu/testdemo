package com.aryeet.demo.bdd.webdriver;

import com.aryeet.pages.AppLandingPage;
import com.aryeet.pages.TvInfoCardPage;
import com.aryeet.pages.WhichReviewHomePage;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Spring configuration for autowired objects
 */
@Configuration
@PropertySources( {
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:application-${spring.profiles.active:qa}.properties")
})

public class TestConfigPageObjects {

   /* @Autowired
    TestProperties properties;*/

    @Autowired
    private Environment environment;

    @Autowired
    private SharedDriver sharedDriver;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient()));
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClientBuilder.create().build();
    }


    @Bean
    public AppLandingPage appLandingPage() {
        System.out.println("I am into AppLandingPage page" );
        return new AppLandingPage(environment.getProperty("base.url"), sharedDriver, 30);
    }

    @Bean
    public WhichReviewHomePage whichReviewHomePage() {
        System.out.println("I am into AppLandingPage page" );
        return new WhichReviewHomePage(environment.getProperty("base.url"), sharedDriver, 30);
    }
    @Bean
    public TvInfoCardPage tvInfoCardPage() {
        System.out.println("I am into TvInfoCardPage page" );
        return new TvInfoCardPage(environment.getProperty("base.url"), sharedDriver, 30);
    }

}

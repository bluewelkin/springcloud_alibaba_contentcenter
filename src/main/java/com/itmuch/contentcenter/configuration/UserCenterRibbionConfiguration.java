package com.itmuch.contentcenter.configuration;


import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;
import ribbionconfiguration.RibbonConfiguration;

@Configuration
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class UserCenterRibbionConfiguration {
}

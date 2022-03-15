package com.ubb.licenta.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.ubb.licenta.repository", "com.ubb.licenta.service", "com.ubb.licenta.controller"})
public class ClientConfig {


}
package com.example.vaultdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties("credentials")
@Data
public class VaultSecrets {

  private String user;
  private String password;
}

package com.example.vaultdemo.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vaultdemo.config.AppConfig;
import com.example.vaultdemo.config.VaultSecrets;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class VaultResource {

  private final VaultSecrets secrets;
  private final AppConfig appConfig;

  @GetMapping("/config")
  private ResponseEntity<String> getAllConfigs() {

    String responseMessage = String
        .format("Ok %s, your user secret in vault is: %s, and your password: %S",
            appConfig.getName(), secrets.getUser(), secrets.getPassword());
    return ResponseEntity.ok(responseMessage);
  }
}

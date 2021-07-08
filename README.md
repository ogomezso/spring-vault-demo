 Getting Started

### SPRING VAULT CONFIG

Demo APP using hashicorp Vault as config server for secrets

## Running it Locally

You will find under Environment folder a start script that will run a Vault Container and will place the secrets and ACL there for you.

## Understanding the  vault config:

* You will need to create (or use existing secret engine on vault), in our case we will use **kafka**
* Create a path that may correspond with your application name, this will contain all your application secrets.
* Create secret itself, spring boot uses a prefix type configuration so to easily use it on a configuration class you can name it as: <prefix>.<secretName. *Example: credentials.user*
* Afterwards you need to create an ACL for your app/engine:
````
path "kafka/*"
{
  capabilities = [ "read", "list"]
}
````

## Understanding Spring Vault Config configuration

All the vault connection will be set up on bootstrap.yml config file:

````
spring:
  application:
    name: vault-demo
  cloud:
    vault:
      kv:
        enabled: true
        backend: kafka
        profile-separator: '/'
        default-context: example
      scheme: http
      token: vault-plaintext-root-token
      host: localhost
      port: 8200
````

For doing that we need to add this dependency to our pom file (due a spring cloud restriction):

````
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-bootstrap</artifactId>
    </dependency>
````

This config will do:

* Set up Vault Connection (scheme, host, port)
* Set up Vault Authentication: The easiest way is doing that via token (you can create one per application with the proper permission)

## Using Vault and Application based config together

On this example we have to configuration classes: *AppConfig* , *VaultSecrets*

both of them are annotated with @Config and @ConfigurationProperties, with the proper prefix name (remember the one we put on out secret)

Springboot will use its configuration preference chain to set the proper value. [Official Documentation](https://docs.spring.io/spring-boot/docs/1.2.2.RELEASE/reference/html/boot-features-external-config.html)

More info about Spring Vault Config Starter [here](https://cloud.spring.io/spring-cloud-vault/reference/html/)



package com.zlargon.springdemo.config;

import org.jooq.conf.RenderQuotedNames;
import org.springframework.boot.autoconfigure.jooq.DefaultConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JooqConfig {

  @Bean
  DefaultConfigurationCustomizer jooqSettings() {
    // https://stackoverflow.com/a/58701946/2802074
    // https://blog.jooq.org/how-to-customise-a-jooq-configuration-that-is-injected-using-spring-boot/
    // https://www.jooq.org/doc/3.18/manual/sql-building/dsl-context/custom-settings/setting-qualification/
    // https://www.jooq.org/doc/3.18/manual/sql-building/dsl-context/custom-settings/settings-name-style/
    return config ->
      config
        .settings() // prettier-break-line
        .withRenderSchema(false)
        .withRenderQuotedNames(RenderQuotedNames.EXPLICIT_DEFAULT_UNQUOTED);
  }
}

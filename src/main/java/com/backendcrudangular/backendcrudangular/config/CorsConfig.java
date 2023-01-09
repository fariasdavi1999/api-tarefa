package com.backendcrudangular.backendcrudangular.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableScheduling
public class CorsConfig extends CorsFilter {



//		@Autowired
//		private Environment environment;
	//
//		private String urlOrigem = environment.getProperty("spring.application.url.origem");

		@Value("${spring.profiles.active}")
		private String activeProfile;

		public CorsConfig() {

			super(configurationSource());
		}

		@Bean
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {

					CorsRegistration cors = registry.addMapping("/**");
					cors.allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS");
					if (activeProfile.equals("local")) {
						cors.allowedOrigins("http://localhost:4200");
						
					} else {

					cors.allowedOrigins("https://primeiro-frontend-angular.vercel.app"); //prodFake
//					cors.allowedOrigins("http://localhost:4200");

					}
					cors.allowedHeaders("*");
				}
			};
		}

		private static UrlBasedCorsConfigurationSource configurationSource() {

			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			CorsConfiguration config = new CorsConfiguration();
			config.setAllowCredentials(true);

			config.addAllowedOrigin("https://primeiro-frontend-angular.vercel.app"); //prodFake
//			config.addAllowedOrigin("http://localhost:4200"); // local


			config.addAllowedHeader("*");
			config.addAllowedMethod("OPTIONS");
			config.addAllowedMethod("HEAD");
			config.addAllowedMethod("GET");
			config.addAllowedMethod("PUT");
			config.addAllowedMethod("POST");
			config.addAllowedMethod("DELETE");
			config.addAllowedMethod("PATCH");
			config.addAllowedMethod("CONNECT");
			source.registerCorsConfiguration("/**", config);
			return source;
		}

	}
	


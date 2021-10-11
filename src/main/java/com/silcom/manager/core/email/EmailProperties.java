package com.silcom.manager.core.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@ConfigurationProperties("silcom.mail.smtp")
public class EmailProperties {

	private String host;
	private Integer port;
	private String username;
	private String password;
	
}

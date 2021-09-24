package com.silcom.manager.api.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ProblemTypeEnum {

    INVALID_DATA(HttpStatus.BAD_REQUEST, "/invalid-data", "Invalid data"),
    BUSINESS_ERROR(HttpStatus.BAD_REQUEST, "/business-error", "Business rule violation"),
    SYSTEM_ERROR (HttpStatus.BAD_REQUEST, "/system-error", "System-error"),
    INCOMPREHENSIBLE_MESSAGE (HttpStatus.BAD_REQUEST, "/incomprehensible-message", "Incomprehensible message"),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "/invalid-parameter", "Invalid parameter"),

    RESOURCE_IN_USE(HttpStatus.CONFLICT, "/resource-in-use", "Resource in use"),
    DUPLICATE_KEY(HttpStatus.CONFLICT, "/duplicate-key", "Duplicate key"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "/resource-not-found", "Resource not found");

	private HttpStatus status;
	private String title;
	private String uri;
	
	private ProblemTypeEnum(HttpStatus status, String path, String title) {
		this.status = status;
        this.uri = "https://www.silcom.com.br" + path;
		this.title = title;
	}
    
}
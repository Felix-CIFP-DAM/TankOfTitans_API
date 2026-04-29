package com.tankOfTitans.model.dto.response;

public class LoginResponse {
    private String token;
    private Long userId;
    private String nickname;
    private String message;
    
	public LoginResponse() {	
	}

	public LoginResponse(String token, Long userId, String nickname, String message) {
		super();
		this.token = token;
		this.userId = userId;
		this.nickname = nickname;
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
    
    
}    

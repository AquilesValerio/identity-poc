package br.com.infrastructure.DTO.OUT;

public record LoginOut(String token, int expiresIn) {

	public LoginOut {
		new LoginOut(token, expiresIn);
	}
}

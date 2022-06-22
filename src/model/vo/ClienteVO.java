package model.vo;

import java.util.List;

public class ClienteVO {
	private String nome;
	private String cpf;
	private String login;
	private String senha;
	private Long clienteId;
	private List<ConversaoVO> ConversaoVO;

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		if (login == null || login.equals("") || login.length() < 2) {

		} else {
			this.login = login;
		}
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		if (senha == null || senha.equals("") || senha.length() < 3) {

		} else {
			this.senha = senha;
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome == null || nome.equals("") || nome.length() < 2) {

		} else {
			this.nome = nome;
		}
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if (cpf == null || cpf.equals("") || cpf.length() < 2) {

		} else {
			this.cpf = cpf;
		}
	}

	public List<ConversaoVO> getConversaoVO() {
		return ConversaoVO;
	}

	public void setConversaoVO(List<ConversaoVO> conversaoVO) {
		ConversaoVO = conversaoVO;
	}

}
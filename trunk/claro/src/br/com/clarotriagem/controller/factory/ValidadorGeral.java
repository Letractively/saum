package br.com.clarotriagem.controller.factory;

import java.io.Serializable;

public class ValidadorGeral implements Serializable{

	private static final long serialVersionUID = -9027013254669323719L;

	
	public boolean validaEMAIL(String email){
		boolean ret = true;
		if(email != null && email.trim().length() > 1){
			if(!email.contains("@") || !email.contains(".")){
				ret = false;
			}
		}else{
			ret = false;
		}
		
		return ret;
	}
	
	public boolean validaURL(String url){
		boolean ret = true;
		if(url != null && url.trim().length() > 1){
			if(!url.contains(".")){
				ret = false;
			}
		}else{
			ret = false;
		}
		
		return ret;
	}
	
	public boolean validaTEL(String tel){
		boolean ret = true;
		if(tel != null && tel.trim().length() < 14){
			ret = false;
		}
		
		return ret;
	}
	public boolean validaCEP(String cep){
		boolean ret = true;
		if(cep != null && cep.trim().length() != 9){
			ret = false;
		}
		
		return ret;
	}
	public boolean validaCPF(String cpf){
		boolean ret = true;
		return ret;
	}
	public boolean validaCNPJ(String cnpj){
		boolean ret = true;
		return ret;
	}
	
}

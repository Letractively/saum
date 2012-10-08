package br.com.meganet.util;

public class ValidaMenu {
	
	public boolean validaMenu(int idMenu, String menusCliente[]){
		boolean retorno = false;
		for (int i = 0; i < menusCliente.length; i++) {
			if(idMenu == Integer.parseInt(menusCliente[i])){
				retorno = true;
			}
		}
		return retorno;
	}

}

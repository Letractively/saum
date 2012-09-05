package br.com.clarotriagem.dao;

import org.springframework.stereotype.Repository;

import br.com.clarotriagem.dao.factory.DAO;
import br.com.clarotriagem.entitades.Empresa;

@Repository("empresaDAO")
public class EmpresaDAO extends DAO<Empresa>{

	public EmpresaDAO(){
		super(Empresa.class);
	}

}

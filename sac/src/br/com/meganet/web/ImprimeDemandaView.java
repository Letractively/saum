package br.com.meganet.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import br.com.meganet.facade.AdministracaoFacade;
import br.com.meganet.hbm.vo.Demandas;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.ValidaMenu;

public class ImprimeDemandaView extends Controlador {

	private final int idMenu = 29;
	ValidaMenu validaMenu = new ValidaMenu();
	private AdministracaoFacade administracaoFacade;

	public ModelAndView executaBrowserComum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario != null) {
			if (validaMenu.validaMenu(idMenu, usuario.getUsuarioMenu().split(","))) {
				try{
					Long id = new Long(request.getParameter("id"));
					Demandas d = administracaoFacade.adiquirirDemandaPeloID(id);
					
					Map<String, Object> ret = new HashMap<String, Object>();
					ret.put("numero", d.getDemandasId());
					ret.put("abertura", new SimpleDateFormat("dd/MM/yyyy").format(d.getDemandasDataAbertura()));
					ret.put("previsao", new SimpleDateFormat("dd/MM/yyyy").format(d.getDemandasDataPrevistaAtendimento()));
					ret.put("nome", d.getUsuarioSolicitante().getUsuarioNome());
					ret.put("cpf", d.getUsuarioSolicitante().getUsuarioCpf());
					ret.put("endereco", d.getUsuarioSolicitante().getUsuarioEndereco() + " - " + d.getUsuarioSolicitante().getUsuarioComplementoEndereco() + " - " + d.getUsuarioSolicitante().getUsuarioBairro() + " - " + d.getUsuarioSolicitante().getUsuarioCep());
					ret.put("cidade", d.getUsuarioSolicitante().getUsuarioCidade());
					ret.put("estado", d.getUsuarioSolicitante().getUsuarioEstado());
					ret.put("telefone", d.getUsuarioSolicitante().getUsuarioTelefone1());
					ret.put("celular", d.getUsuarioSolicitante().getUsuarioTelefone2());
					ret.put("idcliente", d.getUsuarioSolicitante().getUsuarioId());
					ret.put("mac", d.getUsuarioSolicitante().getUsuarioMac());
					ret.put("servidor", d.getUsuarioSolicitante().getServidor().getServidorNome());
					if(d.getUsuarioSolicitante().getEnderecoIp() != null){
						ret.put("ip", d.getUsuarioSolicitante().getEnderecoIp().getEnderecoipEndereco());
					}
					
					ret.put("descricao_abr", d.getDemandasDescricao());
					ret.put("abriu", d.getUsuarioAbriu().getUsuarioNome());
					
					if(d.getDemandasDataEncerramento() != null){
						ret.put("fechou", d.getUsuarioFechou().getUsuarioNome());
						if(d.getUsuarioResponsavel() != null){
							ret.put("responsavel", d.getUsuarioResponsavel().getUsuarioNome());
						}
						ret.put("descricao_enc", d.getDemandasDescricaoEncerramento());
						ret.put("encerramento", new SimpleDateFormat("dd/MM/yyyy").format(d.getDemandasDataEncerramento()));
						
						if("Defeito técnico".equalsIgnoreCase(d.getDemandasTipoErro())){
							ret.put("defeito", " X ");
						}else if("Mal uso do equipamento".equalsIgnoreCase(d.getDemandasTipoErro())){
							ret.put("maluso", " X ");
						}else if("Não conseguiu reproduzir o erro".equalsIgnoreCase(d.getDemandasTipoErro())){
							ret.put("reproduzir", " X ");
						}else if("Mudança de endereço".equalsIgnoreCase(d.getDemandasTipoErro())){
							ret.put("mudanca", " X ");
						}						
						
					}else{
						ret.put("fechou", "&nbsp;");
						ret.put("responsavel", "&nbsp;");
						ret.put("descricao_enc", "&nbsp;");
						ret.put("encerramento", "&nbsp;");

						ret.put("mudanca", "&nbsp;");
						ret.put("defeito", "&nbsp;");
						ret.put("maluso", "&nbsp;");
						ret.put("reproduzir", "&nbsp;");
					}
					
					return new ModelAndView("os_print", ret);
				}catch (Exception e) {
					return new ModelAndView("index2");
				}
			} else {
				return new ModelAndView("index2");
			}
		} else {
			return new ModelAndView("index2");
		}
	}

	public void setAdministracaoFacade(AdministracaoFacade administracaoFacade) {
		this.administracaoFacade = administracaoFacade;
	}
}

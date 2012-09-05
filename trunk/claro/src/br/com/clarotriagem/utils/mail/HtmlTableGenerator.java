package br.com.clarotriagem.utils.mail;

import java.util.LinkedHashMap;
import java.util.Map;

import br.com.clarotriagem.controller.BaseBean;
import br.com.clarotriagem.utils.singleton.mapas.ConfigUtil;

public class HtmlTableGenerator {
	
	private String tituloTabela;
	private String rodapeTabela;
	private String corAtual = "FFFFFF";
	private LinkedHashMap<String, Object> dados;
	private boolean temRodape = true;
	
	public String montaTabela(){
		String html = "";
		html += getCabecalho();
		html += tituloTabela;
		html += montaLinhas();
		if(temRodape){
			html += getRodapeTabela();
		}
		html += getFimTabela();
		return html;
	}
	
	public String montaTabelaSemRodape(){
		String html = "";
		html += getCabecalho();
		html += tituloTabela;
		html += montaLinhas();
		return html;
	}
	
	private String montaLinhas() {
		String ret = "";
		for ( String key : dados.keySet() ){
			String linhaNova = getLinha();
			Object value = dados.get(key);
			linhaNova = linhaNova.replace("{1}", key);
			linhaNova = linhaNova.replace("{2}", value.toString());
			if(corAtual.equalsIgnoreCase("FFFFFF")){
				linhaNova = linhaNova.replace("{3}", "FFFFFF");
				linhaNova = linhaNova.replace("{4}", "FFFFFF");
				corAtual = "F7F7F7";
			}else{
				linhaNova = linhaNova.replace("{3}", "F7F7F7");
				linhaNova = linhaNova.replace("{4}", "F7F7F7");
				corAtual = "FFFFFF";
			}
			ret += linhaNova;
        }
		return ret;
	}

	public Map<String, Object> getDados() {
		if(dados == null){
			dados = new LinkedHashMap<String, Object>();
		}
		return dados;
	}
	
	public void setDados(LinkedHashMap<String, Object> dados) {
		this.dados = dados;
	}
	
	public String getCabecalho() {
		String html = "";
		html += "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#CCE3FD\"><tr>";
		html += "<td><table width=\"100%\" border=\"0\" cellspacing=\"2\" cellpadding=\"1\">";
		return html;
	}

	private String getLinha() {
		String html = "";
	    html += "<tr><td bgcolor=\"#{3}\" width='25%' align=\"right\"><span align=\"right\" nowrap=\"nowrap\"><strong>{1}</strong></span></td>";
	    html += "<td bgcolor=\"#{4}\" align=\"left\"><span align=\"left\" style=\"margin-left:10px\">{2}</span></td></tr>";
		return html;
	}
	
	public void setTituloTabelaSemNomeEmpresa(String msg){
		String html = "";
		html += "<tr align=\"center\"><td colspan='2' align=\"center\"><span align=\"center\" style=\"font-size:16px\"><strong>"+ msg +"</strong></span></td></tr>";
		tituloTabela = html;
	}
	public void setTituloTabela(String msg){
		String html = "";
	    html += "<tr align=\"center\"><td align=\"center\"><span style=\"font-size: 29px; font-weight: bold;\">"+ ConfigUtil.getInstance().getProperty("empresa","Meganet") +"</span></td>";
	    html += "<td><span align=\"center\"><strong>"+ msg +"</strong></span></td></tr>";
		tituloTabela = html;
	}

	public void setRodapeTabela(String msg){
		if(msg != null){
			temRodape = true;
			String html = "";
			html += "<tr><td bgcolor=\"#"+ corAtual +"\" colspan=\"2\"><div align=\"center\">"+ msg +"<br><b>"+ BaseBean.getBundeExterno("atencao_nao_responda_esse_email") +".</b></div></td></tr>";
			rodapeTabela = html;
		}else{
			rodapeTabela = null;
		}
	}
	public void setRodapeTabelaSemAviso(String msg){
		if(msg != null){
			temRodape = true;
			String html = "";
			html += "<tr><td bgcolor=\"#"+ corAtual +"\" colspan=\"2\"><div align=\"center\">"+ msg +"</div></td></tr>";
			rodapeTabela = html;
		}else{
			rodapeTabela = null;
		}
	}
	public String getRodapeTabela(){
		if(!(this.rodapeTabela != null && !"".equalsIgnoreCase(this.rodapeTabela))){
			setRodapeTabela();
		}
		return this.rodapeTabela;
	}
	public void setRodapeTabela(){
		String html = "";
		html += "<tr><td bgcolor=\"#"+ corAtual +"\" colspan=\"2\"><div align=\"center\"><br><b>ATENÇÃO: Não responda esse e-mail.</b></div></td></tr>";
		rodapeTabela = html;
	}
	
	public String getTituloTabela() {
		return tituloTabela;
	}
	
	private String getFimTabela() {
		return "</table></td></tr></table>";
	}
}

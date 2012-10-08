package br.com.meganet.util;

import java.util.LinkedHashMap;
import java.util.Map;

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
	    html += "<tr><td bgcolor=\"#{3}\"><div align=\"right\"><strong>{1}</strong></div></td>";
	    html += "<td bgcolor=\"#{4}\"><div align=\"left\" style=\"margin-left:10px\">{2}</div></td></tr>";
		return html;
	}
	
	public void setTituloTabela(String msg){
		String html = "";
	    html += "<tr><td width=\"17%\" align=\"center\"><span style=\"font-size: 29px; font-weight: bold;\">"+ ConfigUtil.getInstance().getProperty("empresa","Meganet") +"</span></td>";
	    html += "<td width=\"83%\"><div align=\"center\"><strong>"+ msg +"</strong></div></td></tr>";
		tituloTabela = html;
	}

	public void setTituloTabelaSemEMpresa(String msg){
		String html = "";
		html += "<tr><td width=\"25%\" align=\"center\"><span style=\"font-size: 29px; font-weight: bold;\">&nbsp;</span></td>";
		html += "<td width=\"75%\"><div align=\"center\"><strong>"+ msg +"</strong></div></td></tr>";
		tituloTabela = html;
	}
	
	public void setRodapeTabela(String msg){
		temRodape = true;
		String html = "";
		html += "<tr><td bgcolor=\"#"+ corAtual +"\" colspan=\"2\"><div align=\"center\">"+ msg +"<br><b>ATENÇÃO: Não responda esse e-mail. ele é utilizado apenas pelo sistema de controle.</b></div></td></tr>";
		rodapeTabela = html;
	}
	public String getRodapeTabela(){
		if(!(this.rodapeTabela != null && !"".equalsIgnoreCase(this.rodapeTabela))){
			setRodapeTabela();
		}
		return this.rodapeTabela;
	}
	public void setRodapeTabela(){
		String html = "";
		html += "<tr><td bgcolor=\"#"+ corAtual +"\" colspan=\"2\"><div align=\"center\"><br><b>ATENÇÃO: Não responda este e-mail. Ele é gerado automaticamente pelo sistema de controle.</b></div></td></tr>";
		rodapeTabela = html;
	}
	
	public String getTituloTabela() {
		return tituloTabela;
	}
	
	private String getFimTabela() {
		return "</table></td></tr></table>";
	}
}

package br.com.clarotriagem.utils.enums;

import java.util.SortedMap;
import java.util.TreeMap;

public enum SintomaConstatadoAparelho {
	
	NAL("NAL - nao liga", 1, TiposResultadosTriagem.DOA),
	DES("DES - Desliga sozinho", 1, TiposResultadosTriagem.DOA),
	LDA("LDA - Liga/Desliga aleatoriamente", 1, TiposResultadosTriagem.DOA),
	DEI("DEI - Desliga intermitente", 1, TiposResultadosTriagem.DOA),
	SIP("SIP - Sem indicacao no painel", 1, TiposResultadosTriagem.DOA),
	DII("DII - Display intermitente", 1, TiposResultadosTriagem.DOA),
	SUD("SUD - Sujeira no display", 1, TiposResultadosTriagem.DOA),
	PAQ("PAQ - Pixel apagado/queimado", 1, TiposResultadosTriagem.DOA),
	DIF("DIF - Dispaly fraco", 1, TiposResultadosTriagem.DOA),
	IMD("IMD - Imagem distorcida", 1, TiposResultadosTriagem.DOA),
	LLF("LLF - Lampada/LED nao funciona", 1, TiposResultadosTriagem.DOA),
	LSI("LSI - LCD sem imagem", 1, TiposResultadosTriagem.DOA),
	NSD("NSD - Indicador de nivel de sinal nao aparece no display", 1, TiposResultadosTriagem.DOA),
	RNA("RNA - Indicacao da rede nao aparece", 1, TiposResultadosTriagem.DOA),
	NBA("NBA - Indicador de nivel de bateria nao aparece", 1, TiposResultadosTriagem.DOA),
	OIN("OIN - Outra indicacao nao funciona", 1, TiposResultadosTriagem.DOA),
	BNC("BNC - Bateria nao carrega", 1, TiposResultadosTriagem.DOA),
	BCP("BCP - Bateria com pouca duracao", 1, TiposResultadosTriagem.DOA),
	SEA("SEA - sem audio", 1, TiposResultadosTriagem.DOA),
	CAL("CAL - Cai a ligacao", 1, TiposResultadosTriagem.DOA),
	MIE("MIE - Microfonia / eco", 1, TiposResultadosTriagem.DOA),
	SOP("SOP - Som picotado", 1, TiposResultadosTriagem.DOA),
	INR("INR - Interferencia / ruido", 1, TiposResultadosTriagem.DOA),
	AUD("AUD - Audio distorcido", 1, TiposResultadosTriagem.DOA),
	CVN("CVN - Controle de volume nao funciona", 1, TiposResultadosTriagem.DOA),
	SET("SET - sem tom", 1, TiposResultadosTriagem.DOA),
	SMA("SMA - Som muito alto", 1, TiposResultadosTriagem.DOA),
	SOF("SOF - Som fraco", 1, TiposResultadosTriagem.DOA),
	SOC("SOC - Fone de ouvido com problemas", 1, TiposResultadosTriagem.DOA),
	FAT("FAT - Falha no teclado", 1, TiposResultadosTriagem.DOA),
	FFS("FFS - Falha no folder/slide", 1, TiposResultadosTriagem.DOA),
	SME("SME - Não envia/recebe SMS/MMS/email", 1, TiposResultadosTriagem.DOA),
	LMN("LMN - liga mas nao funciona - travando", 1, TiposResultadosTriagem.DOA),
	SES("SES - sem servico", 1, TiposResultadosTriagem.DOA),
	NAD("NAD - Nao desliga", 1, TiposResultadosTriagem.DOA),
	PFS("PFS - Para de funcionar sozinho", 1, TiposResultadosTriagem.DOA),
	ATI("ATI - Aparelho travando intermitente", 1, TiposResultadosTriagem.DOA),
	FBI("FBI - Funcionamento do botao intermitente", 1, TiposResultadosTriagem.DOA),
	NAR("NAR - Nao recebe (so transmite)", 1, TiposResultadosTriagem.DOA),
	NAT("NAT - Nao transmite (so recebe)", 1, TiposResultadosTriagem.DOA),
	NRT("NRT - Nao recebe e nao transmite", 1, TiposResultadosTriagem.DOA),
	REI("REI - Recepcao intermitente", 1, TiposResultadosTriagem.DOA),
	TRI("TRI - Transmissao intermitente", 1, TiposResultadosTriagem.DOA),
	FIF("FIF - Sinal fraco", 1, TiposResultadosTriagem.DOA),
	NAC("NAC - Nao disca", 1, TiposResultadosTriagem.DOA),
	DIT("DIT - Disca incorretamente as teclas", 1, TiposResultadosTriagem.DOA),
	ADF("ADF - Auto-discagem nao funciona", 1, TiposResultadosTriagem.DOA),
	RDF("RDF - Rediscagem nao funciona", 1, TiposResultadosTriagem.DOA),
	FRA("FRA - Falha na resposta automatica", 1, TiposResultadosTriagem.DOA),
	FAM("FAM - Falha ao acessar as mensagens", 1, TiposResultadosTriagem.DOA),
	FFL("FFL - falha ao fazer ligação", 1, TiposResultadosTriagem.DOA),
	VNF("VNF - Vibrador nao funciona", 1, TiposResultadosTriagem.DOA),
	ACP("ACP - Apaga caixa postal", 1, TiposResultadosTriagem.DOA),
	DAS("DAS - Dados se alteram sozinhos", 1, TiposResultadosTriagem.DOA),
	RNF("RNF - Reset nao funciona", 1, TiposResultadosTriagem.DOA),
	SCP("SCP - Sem comunicacao com o PC", 1, TiposResultadosTriagem.DOA),
	FOD("FOD - Foto distorcida", 1, TiposResultadosTriagem.DOA),
	RAF("RAF - Ruido no alto falante", 1, TiposResultadosTriagem.DOA),
	RUI("RUI - Ruido intermitente", 1, TiposResultadosTriagem.DOA),
	EFF("EFF - Edicao da foto nao funciona", 1, TiposResultadosTriagem.DOA),
	VFF("VFF - Visualizacao da foto nao funciona", 1, TiposResultadosTriagem.DOA),
	FCF("FCF - Flash da camera nao funciona", 1, TiposResultadosTriagem.DOA),
	OFC("OFC - Outra falha da camera", 1, TiposResultadosTriagem.DOA),
	CDF("CDF - Cabo de dados nao funciona", 1, TiposResultadosTriagem.DOA),
	CNF("CNF - Carregador nao funciona", 1, TiposResultadosTriagem.DOA),
	OAP("OAP - Outro acessorio com problema", 1, TiposResultadosTriagem.DOA),
	MP3("MP3 - MP3 nao funciona", 1, TiposResultadosTriagem.DOA),
	INF("INF - Infravermelho nao funciona", 1, TiposResultadosTriagem.DOA),
	BNF("BNF - Bluetooth nao funciona", 1, TiposResultadosTriagem.DOA),
	CMF("CMF - Cartao de memoria nao funciona", 1, TiposResultadosTriagem.DOA),
	WNF("WMF - WiFi nao funciona", 1, TiposResultadosTriagem.DOA),
	PCO("PCO - Problema com a operadora", 1, TiposResultadosTriagem.DOA),
	OFP("OFP - Outra funcionalidade com problema", 1, TiposResultadosTriagem.DOA),
	NDE("NDE - Nenhum defeito encontrado", 1, TiposResultadosTriagem.DOA),
	AQD("AQD - Aquece demais", 1, TiposResultadosTriagem.DOA),
	REF("REF - Relogio não funciona", 1, TiposResultadosTriagem.DOA),
	NAS("NAS - Nao salva dados", 1, TiposResultadosTriagem.DOA),
	GNF("GNF - Gravador nao funciona", 1, TiposResultadosTriagem.DOA),
	AED("AED - Apresenta erro no display", 1, TiposResultadosTriagem.DOA),
	NRS("NRS - Nao reconhece o SIM card", 1, TiposResultadosTriagem.DOA),
	OTR("OTR - Outro problema", 1, TiposResultadosTriagem.DOA),
	ASD("ASD - aparelho sem defeito", 1, TiposResultadosTriagem.DOA);
	

	private String descricao;
	private Integer cod;
	private TiposResultadosTriagem tiposResultadosTriagem;
	
	private static SortedMap<String, SintomaConstatadoAparelho> rotulosPorDescricao = new TreeMap<String, SintomaConstatadoAparelho>();
	private static SortedMap<Integer, SintomaConstatadoAparelho> rotulosPorCod = new TreeMap<Integer, SintomaConstatadoAparelho>();
	private static SortedMap<String, Integer> mapaRotulos = new TreeMap<String, Integer>();

	private SintomaConstatadoAparelho(String descricao, Integer cod, TiposResultadosTriagem tiposResultadosTriagem) {
		this.descricao = descricao;
		this.cod = cod;
		this.tiposResultadosTriagem = tiposResultadosTriagem;
	}

	static {
		for (SintomaConstatadoAparelho e : SintomaConstatadoAparelho.values()) {
			rotulosPorCod.put(e.getCod(), e);
		}
		for (SintomaConstatadoAparelho e : SintomaConstatadoAparelho.values()) {
			rotulosPorDescricao.put(e.getDescricao(), e);
		}
		for (SintomaConstatadoAparelho e : SintomaConstatadoAparelho.values()) {
			mapaRotulos.put(e.getDescricao(), e.getCod());
		}
	}

	public String toString() {
		return "[" + descricao + "] " + cod;
	}

	public static SintomaConstatadoAparelho getRotuloPorCod(Integer codigo) {
		return rotulosPorCod.get(codigo);
	}

	public static SintomaConstatadoAparelho getRotuloPorSigla(String desc) {
		return rotulosPorDescricao.get(desc);
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public static SortedMap<String, Integer> getMapaRotulos() {
		return mapaRotulos;
	}

	public static void setMapaRotulos(SortedMap<String, Integer> mapaRotulos) {
		SintomaConstatadoAparelho.mapaRotulos = mapaRotulos;
	}

	public TiposResultadosTriagem getTiposResultadosTriagem() {
		return tiposResultadosTriagem;
	}

}
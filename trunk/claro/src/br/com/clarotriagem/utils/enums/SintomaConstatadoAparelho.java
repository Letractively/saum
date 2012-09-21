package br.com.clarotriagem.utils.enums;

import java.util.SortedMap;
import java.util.TreeMap;

public enum SintomaConstatadoAparelho {
	
	NAL("NAL - nao liga", 1, TiposResultadosTriagem.DOA),
	DES("DES - Desliga sozinho", 2, TiposResultadosTriagem.DOA),
	LDA("LDA - Liga/Desliga aleatoriamente", 3, TiposResultadosTriagem.DOA),
	DEI("DEI - Desliga intermitente", 4, TiposResultadosTriagem.DOA),
	SIP("SIP - Sem indicacao no painel", 5, TiposResultadosTriagem.DOA),
	DII("DII - Display intermitente", 6, TiposResultadosTriagem.DOA),
	SUD("SUD - Sujeira no display", 7, TiposResultadosTriagem.DOA),
	PAQ("PAQ - Pixel apagado/queimado", 8, TiposResultadosTriagem.DOA),
	DIF("DIF - Dispaly fraco", 9, TiposResultadosTriagem.DOA),
	IMD("IMD - Imagem distorcida", 10, TiposResultadosTriagem.DOA),
	LLF("LLF - Lampada/LED nao funciona", 11, TiposResultadosTriagem.DOA),
	LSI("LSI - LCD sem imagem", 12, TiposResultadosTriagem.DOA),
	NSD("NSD - Indicador de nivel de sinal nao aparece no display", 13, TiposResultadosTriagem.DOA),
	RNA("RNA - Indicacao da rede nao aparece", 14, TiposResultadosTriagem.DOA),
	NBA("NBA - Indicador de nivel de bateria nao aparece", 15, TiposResultadosTriagem.DOA),
	OIN("OIN - Outra indicacao nao funciona", 16, TiposResultadosTriagem.DOA),
	BNC("BNC - Bateria nao carrega", 17, TiposResultadosTriagem.DOA),
	BCP("BCP - Bateria com pouca duracao", 18, TiposResultadosTriagem.DOA),
	SEA("SEA - sem audio", 19, TiposResultadosTriagem.DOA),
	CAL("CAL - Cai a ligacao", 20, TiposResultadosTriagem.DOA),
	MIE("MIE - Microfonia / eco", 21, TiposResultadosTriagem.DOA),
	SOP("SOP - Som picotado", 22, TiposResultadosTriagem.DOA),
	INR("INR - Interferencia / ruido", 23, TiposResultadosTriagem.DOA),
	AUD("AUD - Audio distorcido", 24, TiposResultadosTriagem.DOA),
	CVN("CVN - Controle de volume nao funciona", 2, TiposResultadosTriagem.DOA),
	SET("SET - sem tom", 25, TiposResultadosTriagem.DOA),
	SMA("SMA - Som muito alto", 26, TiposResultadosTriagem.DOA),
	SOF("SOF - Som fraco", 27, TiposResultadosTriagem.DOA),
	SOC("SOC - Fone de ouvido com problemas", 28, TiposResultadosTriagem.DOA),
	FAT("FAT - Falha no teclado", 29, TiposResultadosTriagem.DOA),
	FFS("FFS - Falha no folder/slide", 30, TiposResultadosTriagem.DOA),
	SME("SME - Não envia/recebe SMS/MMS/email", 31, TiposResultadosTriagem.DOA),
	LMN("LMN - liga mas nao funciona - travando", 32, TiposResultadosTriagem.DOA),
	SES("SES - sem servico", 33, TiposResultadosTriagem.DOA),
	NAD("NAD - Nao desliga", 34, TiposResultadosTriagem.DOA),
	PFS("PFS - Para de funcionar sozinho", 35, TiposResultadosTriagem.DOA),
	ATI("ATI - Aparelho travando intermitente", 36, TiposResultadosTriagem.DOA),
	FBI("FBI - Funcionamento do botao intermitente", 37, TiposResultadosTriagem.DOA),
	NAR("NAR - Nao recebe (so transmite)", 38, TiposResultadosTriagem.DOA),
	NAT("NAT - Nao transmite (so recebe)", 40, TiposResultadosTriagem.DOA),
	NRT("NRT - Nao recebe e nao transmite", 41, TiposResultadosTriagem.DOA),
	REI("REI - Recepcao intermitente", 42, TiposResultadosTriagem.DOA),
	TRI("TRI - Transmissao intermitente", 43, TiposResultadosTriagem.DOA),
	FIF("FIF - Sinal fraco", 44, TiposResultadosTriagem.DOA),
	NAC("NAC - Nao disca", 45, TiposResultadosTriagem.DOA),
	DIT("DIT - Disca incorretamente as teclas", 46, TiposResultadosTriagem.DOA),
	ADF("ADF - Auto-discagem nao funciona", 47, TiposResultadosTriagem.DOA),
	RDF("RDF - Rediscagem nao funciona", 48, TiposResultadosTriagem.DOA),
	FRA("FRA - Falha na resposta automatica", 49, TiposResultadosTriagem.DOA),
	FAM("FAM - Falha ao acessar as mensagens", 50, TiposResultadosTriagem.DOA),
	FFL("FFL - falha ao fazer ligação", 51, TiposResultadosTriagem.DOA),
	VNF("VNF - Vibrador nao funciona", 52, TiposResultadosTriagem.DOA),
	ACP("ACP - Apaga caixa postal", 53, TiposResultadosTriagem.DOA),
	DAS("DAS - Dados se alteram sozinhos", 54, TiposResultadosTriagem.DOA),
	RNF("RNF - Reset nao funciona", 55, TiposResultadosTriagem.DOA),
	SCP("SCP - Sem comunicacao com o PC", 56, TiposResultadosTriagem.DOA),
	FOD("FOD - Foto distorcida", 57, TiposResultadosTriagem.DOA),
	RAF("RAF - Ruido no alto falante", 58, TiposResultadosTriagem.DOA),
	RUI("RUI - Ruido intermitente", 59, TiposResultadosTriagem.DOA),
	EFF("EFF - Edicao da foto nao funciona", 60, TiposResultadosTriagem.DOA),
	VFF("VFF - Visualizacao da foto nao funciona", 61, TiposResultadosTriagem.DOA),
	FCF("FCF - Flash da camera nao funciona", 62, TiposResultadosTriagem.DOA),
	OFC("OFC - Outra falha da camera", 63, TiposResultadosTriagem.DOA),
	CDF("CDF - Cabo de dados nao funciona", 64, TiposResultadosTriagem.DOA),
	CNF("CNF - Carregador nao funciona", 65, TiposResultadosTriagem.DOA),
	OAP("OAP - Outro acessorio com problema", 66, TiposResultadosTriagem.DOA),
	MP3("MP3 - MP3 nao funciona", 67, TiposResultadosTriagem.DOA),
	INF("INF - Infravermelho nao funciona", 68, TiposResultadosTriagem.DOA),
	BNF("BNF - Bluetooth nao funciona", 69, TiposResultadosTriagem.DOA),
	CMF("CMF - Cartao de memoria nao funciona", 70, TiposResultadosTriagem.DOA),
	WNF("WMF - WiFi nao funciona", 71, TiposResultadosTriagem.DOA),
	PCO("PCO - Problema com a operadora", 72, TiposResultadosTriagem.DOA),
	OFP("OFP - Outra funcionalidade com problema", 73, TiposResultadosTriagem.DOA),
	NDE("NDE - Nenhum defeito encontrado", 74, TiposResultadosTriagem.DOA),
	AQD("AQD - Aquece demais", 75, TiposResultadosTriagem.DOA),
	REF("REF - Relogio não funciona", 76, TiposResultadosTriagem.DOA),
	NAS("NAS - Nao salva dados", 77, TiposResultadosTriagem.DOA),
	GNF("GNF - Gravador nao funciona", 78, TiposResultadosTriagem.DOA),
	AED("AED - Apresenta erro no display", 79, TiposResultadosTriagem.DOA),
	NRS("NRS - Nao reconhece o SIM card", 80, TiposResultadosTriagem.DOA),
	OTR("OTR - Outro problema", 81, TiposResultadosTriagem.DOA),
	ASD("ASD - aparelho sem defeito", 82, TiposResultadosTriagem.DOA);
	

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
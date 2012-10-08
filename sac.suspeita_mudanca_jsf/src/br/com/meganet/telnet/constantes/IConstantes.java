/**
 * Projeto: ASAP - Sistema de Reconciliacao Ativa (SRA)
 * 
 * Tipo : IConstantes
 */
package br.com.meganet.telnet.constantes;

import java.io.File;

/**
 * Interface com as constantes comuns do SRA.
 * 
 * @author Adson Costa
 * @since 01/09/2006
 */
public interface IConstantes {

	public static final String PROVISION10_142_184_111 = "10.142.184.111provision";
	public static final String PROVISION10_142_184_112 = "10.142.184.112provision";
	public static final String PROVISION10_142_184_34 = "10.142.184.34provision";
	public static final String PROVISION10_48_254_100 = "10.48.254.100provision";
	public static final String PROVISION10_61_254_100 = "10.61.254.100provision";
	public static final String PROVISION200_193_136_202 = "200.193.136.202provision";
	public static final String PROVISION200_199_241_49 = "200.199.241.49provision";
	public static final String ASAPUSER10_142_184_98 = "10.142.184.98ASAPuser";
	public static final String ASAP10_151_51_252 = "10.151.51.252asap";
	public static final String ASAP10_61_254_100 = "10.61.254.100asap";
	public static final String POOL10_142_184_12 = "10.142.184.12";
	public static final String MAXLENGTH_POOL = "TAMANHO_MAXIMO_POOL";
	public static final String MINLENGTH_POOL = "TAMANHO_MINIMO_POOL";
	public static final String WAITTIME_POOL = "TEMPO_ESPERA_POOL";
	public static final String TIMEOUT_POOL = "TEMPO_SAIDA_POOL";
	public static final int NUMERO_SUBTRAIDO_ANO_PARA_COMBO  = 0;
	public static final int NUMERO_SOMADO_ANO_PARA_COMBO = 3;

	//Constantes dos tipos de Relatorios
	public static final String REL_VELOCIDADE_OBJECTEL_MAIOR = "1";
	public static final String REL_VELOCIDADE_OBJECTEL_MENOR = "2";
	public static final String REL_PROMPT_GERENCIA_DIFERENTE_ESPERADO = "3";
	public static final String REL_PORTA_CONFIGURADA_OBJECTEL_NAO_GERENCIA = "4";
	public static final String REL_PORTA_CONFIGURADA_GERENCIA_NAO_OBJECTEL = "5";
	public static final String REL_DSLAM_NAO_ALCANCAVEL = "6";
	public static final String REL_AUSENCIA_CADASTRO_USUARIO_SENHA_GERENCIA = "7";
	public static final String REL_AUSENCIA_CADASTRO_USUARIO_SENHA_DSLAM = "8";
	public static final String REL_USUARIO_SENHA_INVALIDOS_GERENCIA = "9";
	public static final String REL_USUARIO_SENHA_INVALIDOS_DSLAM = "10";
	public static final String REL_DIVERGENCIA_BASTIDOR = "11";
	public static final String REL_SHELF_CONFIGURADO_OBJECTEL_NAO_GERENCIA = "12";
	public static final String REL_SHELF_CONFIGURADO_GERENCIA_NAO_OBJECTEL = "13";
	public static final String REL_PLACA_CONFIGURADO_OBJECTEL_NAO_GERENCIA = "14";
	public static final String REL_PLACA_CONFIGURADO_GERENCIA_NAO_OBJECTEL = "15";
	public static final String REL_IP_DSLAM_CONFIGURADO_OBJECTEL_NAO_GERENCIA = "16";
	public static final String REL_IP_DSLAM_CONFIGURADO_GERENCIA_NAO_OBJECTEL = "17";
	public static final String REL_MESMO_IP_DSLAM_GERENCIA = "18";
	public static final String REL_MESMO_IP_DSLAM_OBJECTEL = "19";
	public static final String REL_DENTRO_DATA_CORTE = "20";
	public static final String REL_FALTA_ACTION_ALIAS = "21";
	public static final String REL_SLOT_CONFIGURADO_GERENCIA_NAO_OBJECTEL = "22";
	public static final String REL_SLOT_CONFIGURADO_OBJECTEL_NAO_GERENCIA = "23";
	
	//Dados Vitria
	public static final String SRA_PROCESSO_VITRIA = "DDRSRDRECDADTER";

	//Millisegundos do periodo de 1 dia
	public static final int MILLIS_DIA = 86400000;
	
	//Constantes dos tipos da acoes do historico
	public static final Integer ACAO_INCLUIR = new Integer(1);
	public static final Integer ACAO_ALTERAR = new Integer(2);
	public static final Integer ACAO_EXCLUIR = new Integer(3);

	//IP LocalHost
	public static final String IP_LOCALHOST = "127.0.0.1";
	
	//Nome Aplicacao
	public static final String NOME_SISTEMA_SRA = "SRDT";

	/*
	 * Constantes do tipo de estrutura(Objeto/Classe) que esto sendo gravado
	 * na tabela Histotico
	*/
	public static final String ESTRUTURA_PARAMETRO = "ESTRUTURA_PARAMETRO";
	public static final String ESTRUTURA_DSLAM = "ESTRUTURA_DSLAM";
	public static final String ESTRUTURA_DIVERGENCIA = "ESTRUTURA_DIVERGENCIA";

	/*
	 * Constantes dos valores dos parametros 
	 */
	public static final String NOME_PARAMETRO_DATA_CORTE = "DATA_CORTE";
	public static final String NOME_PARAMETRO_LIMITE_DIVER_OMS = "LIMITE_DIVER_OMS";
	
	/*
	 * Constantes do utilizada no TelnetConnection
	 */
	public static final int VALOR_TIMEOUT_NAO_ENVIADO = -1;

	/*
	 * Constantes de exception da TelnetConnection
	 */
	public static final String MSG_ERRO_USUARIO_SENHA_INVALIDO_GERENCIA = "Usuario ou Senha invalido";
	public static final String MSG_PROMPT_DIVERGENTE_GERENCIA = "Prompt da gerencia divergente";

	/*
	 * Constantes Referentes ao properties dos DAO's
    */
	public static final String PROPERTIES_DAO_BASE = "DAOBase";
	public static final String PROPERTIES_DAO_OBJECTEL = "DAOObjectel";
	public static final String PROPERTIES_DAO_PARAMETRO = "DAOParametro";
	public static final String PROPERTIES_DAO_PARAMETRO_GERENCIA = "DAOParametroGerencia";
	public static final String PROPERTIES_DAO_PERSISTENCE = "DAOPersistence";
	public static final String PROPERTIES_DAO_ASAP = "DAOAsap";
	public static final String PROPERTIES_DAO_FILIAL = "DAOFilial";
	public static final String PROPERTIES_DAO_OPERADORA = "DAOOperadora";
	public static final String PROPERTIES_DAO_REGIAO = "DAORegiao";
	public static final String PROPERTIES_DAO_LOCALIDADE = "DAOLocalidade";
	public static final String PROPERTIES_DAO_ESTACAO = "DAOEstacao";
	public static final String PROPERTIES_DAO_VITRIA = "DAOVitria";
	public static final String PROPERTIES_DAO_ATUALIZAO_SOFTWARE = "DAOAtualizaSoftware";
	
	public static final String DATASOURCE_NAME = "jdbc/asapEJBDS";

	/*
	 * Codigos de erros da divergencia
	 */
	public static final int COD_STATUS_ERRO_OK = 0;
	public static final int COD_STATUS_ERRO_GENERICO = -1;
	public static final int COD_STATUS_ERRO_FALTA_USUARIO_SENHA_DSLAM = -10;
	public static final int COD_STATUS_ERRO_DSLAM_NAO_ALCANCAVEL = -11;
	public static final int COD_STATUS_ERRO_USUARIO_SENHA_INVALIDO_DSLAM = -12;
	public static final int COD_STATUS_ERRO_FALTA_USUARIO_SENHA_GERENCIA = -13;
	public static final int COD_STATUS_ERRO_FALTA_ACTION_ALIAS = -14;
	public static final int COD_STATUS_ERRO_USUARIO_SENHA_INVALIDO_GERENCIA = -15;
	public static final int COD_STATUS_ERRO_PROMPT_DIVERGENTE_GERENCIA = -16;
	public static final int COD_STATUS_ERRO_IP_DSLAM_GERENCIA_NAO_INFORMADO = -17;
	public static final int COD_STATUS_ERRO_COD_ENABLE_DSLAM_INVALIDO = -17;

	/*
	 * Codigos para Status da porta
	 */
	public static final String COD_DSLAM_STATUS_ATIVO = "A";
	public static final String COD_DSLAM_STATUS_INATIVO = "D";
	
	/*
	 * Codigos para Status na hora de atualizar a versao de software no Objectel.
	 */
	public static final String COD_ATUALIZA_SOFTWARE_ATUALIZAR = "AT";
	public static final String COD_ATUALIZA_SOFTWARE_ENVIADO = "EV";
	public static final String COD_OPERACAO_ATUALIZA_SOFTWARE_ATUALIZAR = "ATUALIZAR_VERSAO_SW_DSLAM";
	public static final String COD_SERVICO_ATUALIZA_SOFTWARE_ATUALIZAR = "ADSL";
    
	/*
     * Status Placa 
     */
	public static String STATUSPLACA = "Normal";
	
	/*
	 * Codigos para tipos de Dslam 
	 */
	public static final String VERSAO_DSLAM_ATM_5100V200R001 = "MA5100V200R001";
	public static final String VERSAO_DSLAM_ATM_5100V200R005 = "MA5100V200R005";
	public static final String VERSAO_DSLAM_ATM_5103V100R003 = "V100R003";
	public static final String VERSAO_DSLAM_ATM_5105V100R008 = "V100R008";
	public static final String VERSAO_DSLAM_ATM_5100V200R002 = "MA5100V200R002";
	public static final String VERSAO_DSLAM_ETH_5100 = "MA5100";
	public static final String VERSAO_DSLAM_ETH_5300 = "MA5300 ";
	public static final String VERSAO_DSLAM_ETH_5600 = "MA5600";

	/*
	 * Constantes referentes ao formatos de mês e ano 
	 */
	public static final String FORMATO_DIA = "dd";
	public static final String FORMATO_MES = "MM";
	public static final String FORMATO_ANO = "yyyy";
	
	/*
	 * Constante referente ao primeiro dia do mês. 
	 */
	public static final String PRIMEIRO_DIA_MES = "01";
	
	/*
	 * Constantes que guardam o caminho do properties que contem parametros para atualizacao
	 * da versao de software do DSLAM no Objectel. 
	 */
	public static final String CAMINHO_PROPERTIES_ATUALIZA_SOFTWARE = "properties" + File.separator + "atualizaSoftware.properties";
	public static final String PROPERTIES_OBJECTEL_IP = "objectel.ip";
	public static final String PROPERTIES_OBJECTEL_PORT = "objectel.port";	
	
	//Tratamento especial do Dslam Ericsson
	public static final String PROMPT_ERICSSON = "Enter DSLAM Name>";
	public static final String FABRICANTE_DSLAM_ERICSSON = "ERICSSON";
	
	//Tratamento especial do Dslam Alcatel
	public static final String FABRICANTE_DSLAM_ALCATEL = "ALCATEL";
	
	/* Constante para o tipo de filial */
	public static final String IDENTIFICADOR_SRA= "O";
}
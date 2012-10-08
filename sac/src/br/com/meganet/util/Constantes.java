package br.com.meganet.util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Constantes {
	
	private static final String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String numeros = "0123456789";
	public final static String CHAVE_CRIPTOGRAFIA = "sP81h6rB1wr2qoieqwrl5zW3";
	public final static String URL_BOLETO = "geraBoleto.do?id=";
	private static Map<Integer, String> mapaAjuda; 
	
	/*
	 * Para o status do cliente sao definidos quatro estagios
	 * lembrando que quando o cliente esta desativado, não importa
	 * se ele esta bloquado ou com mensagem de advertencia, pois desativado
	 * possui hierarquia superior aos demais
	 * 
	 *   1           2               3
	 * ativo	desbloqueado	sem mensagem
	 * ativo	desbloqueado	mensagem
	 * ativo	bloqueado	
	 * desativado
	 */
	public static final Short USR_ATIVO_DESBLOQUEADO_SEMMENSAGEM = new Short("0");
	public static final Short USR_ATIVO_DESBLOQUEADO_MENSAGEM = new Short("1");
	public static final Short USR_ATIVO_BLOQUEADO = new Short("2");
	public static final Short USR_DESATIVADO = new Short("3");
	
	public static final Long CMD_IDT_0L = 0L;
	
	public static final Long CMD_ADD = 1L;
	public static final Long CMD_ALT = 2L;
	public static final Long CMD_DEL = 3L;
	public static final Long CMD_GERAL = 4L;

	public static final Long STATUS_CLIENTE = 14L;
	public static final Long STATUS_TORRE = 15L;
	public static final Long COMANDO_QUIT = 16L;
	public static final Long MONITORA_NOISE_FLOOR = 17L;
	public static final Long TROCA_CANAL_TORRE = 18L;
	public static final Long MEDE_INTERFACE_REDE = 20L;
	public static final Long ESTADO_ETHERNET = 21L;
	public static final Long ESTADO_PPPOE_CLIENT = 22L;
	
	public static final String[] LOG_TIPO_RETORNO = {"Retorno", "Login", "Alteração", "Erro", "Envio boleto"};
	public static final String[] TIPO_GASTO = {"Conta telefônica", "Aluguel", "Impressão boleto", "Funcionário", "Outros"};
	public static final String MENSAGEM_DESBLOQUEIO_TEMPORARIO = "Desbloqueio realizado com sucesso";
	public static final Long TIPOS_ERRO = 1L;
	public static final Long VALOR_VISITA_IMPRODUTIVA = 2L;
	
	public static final String CT_GLOBAL_10 = "187-246-38-247-249-129-86-22-109-179-79-203-205-170-223-9";//rodape.jsp
	public static final String CT_GLOBAL_11 = "144-111-117-185-82-1-45-129-148-158-69-221-100-99-16-157";//index.jsp
	public static final String CT_GLOBAL_12 = "114-185-166-167-3-58-236-45-184-193-123-81-193-122-128-148";//rodape.old.jsp
	
	public static final String CT_GLOBAL_40 = "196-2-119-48-181-20-16-73-117-128-64-149-223-38-";//burlar
	public static final String CT_GLOBAL_41 = "104-178-34-231-105-255-195-21-68-182-1-113-65-48-204-";//burlar
	public static final String CT_GLOBAL_42 = "242-65-76-51-81-124-199-101-72-64-201-65-201-162-228-10-111-230-196";//burlar

	public static final String CT_GLOBAL_50 = "18-113-13-29-83-241-50-108";///sac/
	public static final String CT_GLOBAL_51 = "217-41-232-157-130-154-144-191-207-227-236-0-73-198-122-116";///WEB-INF
	public static final String CT_GLOBAL_52 = "70-125-24-242-198-209-113-178-71-191-199-33-48-237-133-221";//WEB-INF/jsp/

	public static final String CT_GLOBAL_60 = "233-139-229-24-243-89-163-81-119-171-60-186-65-6-217-6-4-164-136-159-177-198-87-2-204-157-186-19-114-224-108-170-115-53-163-3-58-247-1-108-43-45-22-112-174-109-204-106";//<jsp:include page="/rodape.jsp"></jsp:include>
	
//	public static final String CT_GLOBAL_001 = "<%@page import=\"br.com.meganet.util.ConfigUtil\"%>";
//	public static final String CT_GLOBAL_002 = "<style type=\"text/css\">";
//	public static final String CT_GLOBAL_003 = ".{1} {position: relative;overflow: hidden;width: 100%;}";
//	public static final String CT_GLOBAL_004 = ".{1},.{1} p,.{1} a,.{1} a:link,.{1} a:visited,.{1} a:hover{color: #212121;font-size: 11px;}";
//	public static final String CT_GLOBAL_005 = ".{1} a,.{1} a:link {color: #286471;text-decoration: underline;}";
//	public static final String CT_GLOBAL_006 = ".{1} a:visited {color: #666666;}";
//	public static final String CT_GLOBAL_007 = ".{1} a:hover {color: #09C1E1;text-decoration: none;}";
//	public static final String CT_GLOBAL_008 = "</style>";
//	public static final String CT_GLOBAL_009 = "	<div class=\"cleared\"></div>";
//	public static final String CT_GLOBAL_010 = "   <div class=\"{1}\">";
//	public static final String CT_GLOBAL_011 = "       <div class=\"art-footer-t\"></div>";
//	public static final String CT_GLOBAL_012 = "       <div class=\"art-footer-l\"></div>";
//	public static final String CT_GLOBAL_013 = "       <div class=\"art-footer-b\"></div>";
//	public static final String CT_GLOBAL_014 = "       <div class=\"art-footer-r\"></div>";
//	public static final String CT_GLOBAL_015 = "       <div style=\"position: relative; padding: 8px\">";
//	public static final String CT_GLOBAL_016 = "           <div style=\"padding: 0; text-align: center; margin: 0 10px 0 10px;\">";
//	public static final String CT_GLOBAL_017 = "				<p style=\"margin: 0; padding: 0; text-align: center;\">";
//	public static final String CT_GLOBAL_018 = "					<%=ConfigUtil.getInstance().getProperty(\"empresa\",\"Meganet\") %><br />";
//	public static final String CT_GLOBAL_019 = "					<a href=\"http://www.gnu.org/licenses/lgpl.html\" target=\"blank\">Copyright &copy; 2010 --- Meganet - Efren Junior.(LGPL License)</a>";
//	public static final String CT_GLOBAL_020 = "				</p>";
//	public static final String CT_GLOBAL_021 = "           </div>";
//	public static final String CT_GLOBAL_022 = "   		<div class=\"cleared\"></div>";
//	public static final String CT_GLOBAL_023 = "       </div>";
//	public static final String CT_GLOBAL_024 = "   </div>";
//	public static final String CT_GLOBAL_025 = "<div class=\"cleared\"></div>";
//	public static final String CT_GLOBAL_026 = "<script type=\"text/javascript\">";
//	public static final String CT_GLOBAL_027 = "	function ajustaRodape(){";
//	public static final String CT_GLOBAL_028 = "		var tamJanela = document.body.parentNode.clientHeight;";
//	public static final String CT_GLOBAL_029 = "		var tamScroll = document.body.scrollHeight;";
//	public static final String CT_GLOBAL_030 = "		var corpo = document.getElementById(\"content-cliente\");";
//	public static final String CT_GLOBAL_031 = "		var tamanho = (tamJanela - <%=ConfigUtil.getTamanhoBrowser(pageContext)%>);";
//	public static final String CT_GLOBAL_032 = "		if(tamScroll <= tamJanela){";
//	public static final String CT_GLOBAL_033 = "			<%if(!ConfigUtil.ehIE6(pageContext)){%>";
//	public static final String CT_GLOBAL_034 = "			corpo.style.minHeight = (tamanho) + \"px\";";
//	public static final String CT_GLOBAL_035 = "			<%}else{%>";
//	public static final String CT_GLOBAL_036 = "			corpo.style.height = (tamanho) + \"px\";";
//	public static final String CT_GLOBAL_037 = "			<%}%>";
//	public static final String CT_GLOBAL_038 = "		}";
//	public static final String CT_GLOBAL_039 = "	}";
//	public static final String CT_GLOBAL_040 = "	ajustaRodape();";
//	public static final String CT_GLOBAL_041 = "</script>";

	public static final String CT_GLOBAL_001 = "26-186-132-46-63-118-62-37-160-213-62-44-171-100-232-109-106-166-32-88-0-131-30-14-136-168-217-123-42-40-0-223-104-151-49-172-237-194-118-219-130-78-36-48-163-41-186-158-44-197-252-134-195-156-153-20";
	public static final String CT_GLOBAL_002 = "226-39-31-238-5-150-41-239-11-207-134-212-19-46-197-73-189-187-152-45-99-231-27-74";
	public static final String CT_GLOBAL_003 = "52-241-224-181-119-47-133-47-108-61-237-132-78-160-102-233-206-124-81-166-230-197-226-27-10-10-82-14-102-132-206-242-184-47-95-237-42-123-216-255-44-139-174-52-58-27-79-63-58-183-184-139-252-127-133-7";
	public static final String CT_GLOBAL_004 = "142-4-16-13-238-40-16-141-25-101-175-204-70-126-41-38-217-217-8-94-233-207-142-220-134-43-61-170-214-178-168-110-21-193-221-179-23-225-143-110-173-101-55-148-129-200-22-149-187-111-7-149-145-253-210-165-147-174-235-106-171-145-195-107-93-121-253-28-135-61-153-83-82-36-118-125-245-128-228-160-249-117-255-77-198-23-12-62-11-165-44-226-100-3-244-157";
	public static final String CT_GLOBAL_005 = "162-194-89-63-56-164-199-56-144-147-44-129-251-44-7-172-11-171-25-26-244-1-207-68-85-209-22-195-61-126-179-134-147-225-62-141-198-42-116-109-74-82-14-98-224-65-216-66-125-23-255-1-14-34-103-241-169-98-113-128-166-210-42-88";
	public static final String CT_GLOBAL_006 = "179-226-46-3-5-175-80-205-10-115-2-54-249-216-249-137-48-213-174-113-147-90-55-234-205-196-61-126-101-214-188-110-207-227-236-0-73-198-122-116";
	public static final String CT_GLOBAL_007 = "160-5-61-55-209-136-46-18-237-44-190-150-52-7-5-82-232-147-11-252-237-85-221-172-121-82-207-106-126-179-217-72-108-221-162-226-19-0-153-167-247-242-211-148-66-44-83-30-134-73-232-45-214-116-142-25";
	public static final String CT_GLOBAL_008 = "246-219-44-13-145-155-153-243-207-227-236-0-73-198-122-116";
	public static final String CT_GLOBAL_009 = "213-46-197-45-92-82-9-210-189-176-55-161-29-209-90-139-66-138-215-132-183-124-142-158-161-94-127-115-71-28-214-107";
	public static final String CT_GLOBAL_010 = "151-31-192-89-240-214-156-93-167-177-146-74-52-153-236-163-174-89-61-45-113-23-203-189";
	public static final String CT_GLOBAL_011 = "235-97-18-211-230-38-227-166-35-25-28-247-130-138-110-2-156-141-23-186-130-102-235-215-90-195-223-86-7-154-54-164-6-149-97-32-2-214-95-177";
	public static final String CT_GLOBAL_012 = "235-97-18-211-230-38-227-166-35-25-28-247-130-138-110-2-156-141-23-186-130-102-235-215-83-77-4-30-133-248-126-233-6-149-97-32-2-214-95-177";
	public static final String CT_GLOBAL_013 = "235-97-18-211-230-38-227-166-35-25-28-247-130-138-110-2-156-141-23-186-130-102-235-215-75-75-19-176-10-221-131-63-6-149-97-32-2-214-95-177";
	public static final String CT_GLOBAL_014 = "235-97-18-211-230-38-227-166-35-25-28-247-130-138-110-2-156-141-23-186-130-102-235-215-30-109-40-126-116-83-228-66-6-149-97-32-2-214-95-177";
	public static final String CT_GLOBAL_015 = "235-97-18-211-230-38-227-166-161-105-105-170-10-227-173-24-90-33-188-12-99-92-126-163-139-26-205-219-178-175-114-71-195-233-156-113-170-125-119-35-8-224-48-217-134-118-246-249-19-108-45-54-40-191-85-174";
	public static final String CT_GLOBAL_016 = "176-75-17-82-118-182-86-111-151-31-192-89-240-214-156-93-69-116-131-20-103-120-171-44-8-224-48-217-134-118-246-249-92-43-175-181-85-174-105-239-143-201-201-112-227-15-34-68-3-171-14-79-188-40-208-94-159-17-64-252-33-171-250-100-133-114-33-182-143-25-141-233-215-165-142-223-82-208-41-160";
	public static final String CT_GLOBAL_017 = "99-23-91-147-172-232-17-225-255-132-15-151-119-246-167-114-35-140-36-164-202-225-242-71-201-179-222-219-34-81-160-158-156-137-27-131-149-144-125-122-49-161-135-233-220-82-214-139-9-163-177-91-118-84-85-238-139-235-241-234-201-22-0-194";
	public static final String CT_GLOBAL_018 = "116-19-242-8-168-225-220-176-128-151-139-136-55-239-165-251-79-225-249-1-17-124-130-164-117-163-14-189-86-233-208-10-205-176-169-182-6-106-34-220-215-217-163-175-59-97-231-121-36-154-233-21-106-11-88-153-42-33-37-191-193-109-8-54-84-232-227-107-46-45-25-138-115-151-124-136-203-229-10-28";
	public static final String CT_GLOBAL_019 = "246-229-252-200-19-247-55-191-14-103-149-30-4-73-146-20-235-228-160-145-44-250-173-99-43-86-109-69-72-163-81-155-150-226-203-251-242-52-157-236-190-75-25-165-210-87-190-219-236-168-67-73-120-184-47-208-201-213-80-218-210-229-177-204-249-91-142-253-119-202-59-123-0-25-122-132-61-243-68-0-236-79-229-206-100-151-205-124-116-12-162-35-52-65-250-179-148-155-230-16-3-135-113-38-80-169-30-243-76-110-243-145-181-147-179-10-231-160-117-17-78-234-18-58-68-52-121-197-71-223-115-193-121-247-62-179";
	public static final String CT_GLOBAL_020 = "73-24-46-201-23-74-210-94-207-227-236-0-73-198-122-116";
	public static final String CT_GLOBAL_021 = "176-75-17-82-118-182-86-111-234-39-206-137-91-87-186-60-44-197-252-134-195-156-153-20";
	public static final String CT_GLOBAL_022 = "192-222-61-94-91-17-201-57-198-81-245-139-219-202-116-181-192-223-14-6-72-95-145-32-197-255-233-139-107-255-48-1-207-227-236-0-73-198-122-116";
	public static final String CT_GLOBAL_023 = "235-97-18-211-230-38-227-166-96-112-242-208-203-222-152-89";
	public static final String CT_GLOBAL_024 = "234-39-206-137-91-87-186-60-44-197-252-134-195-156-153-20";
	public static final String CT_GLOBAL_025 = "170-64-44-161-179-177-228-181-215-204-51-128-200-230-213-44-220-109-174-84-235-77-128-143-248-22-107-109-192-132-146-119";
	public static final String CT_GLOBAL_026 = "48-14-6-42-202-128-153-19-74-235-141-36-201-47-200-124-31-168-82-40-49-37-241-229-42-69-97-18-221-184-161-126";
	public static final String CT_GLOBAL_027 = "115-222-244-75-58-68-216-61-20-72-239-157-252-215-180-235-142-86-118-163-26-41-127-54-144-37-194-98-168-183-225-28";
	public static final String CT_GLOBAL_028 = "250-51-110-202-228-16-65-210-123-245-158-157-145-147-188-19-216-174-236-85-187-228-102-71-218-183-249-48-38-128-41-239-37-23-236-63-155-204-21-223-156-243-206-165-74-213-15-38-126-130-152-202-245-217-167-92-207-227-236-0-73-198-122-116";
	public static final String CT_GLOBAL_029 = "250-51-110-202-228-16-65-210-60-204-199-15-109-150-35-28-216-174-236-85-187-228-102-71-218-183-249-48-38-128-41-239-215-20-210-0-46-196-73-93-228-197-178-198-37-205-129-77";
	public static final String CT_GLOBAL_030 = "25-89-217-26-221-61-66-187-99-74-19-163-180-3-129-84-145-183-126-228-190-183-144-142-62-13-30-228-240-135-168-108-3-192-152-243-182-31-20-181-33-102-180-166-46-9-0-59-194-36-55-6-33-34-165-240-17-166-242-204-161-202-123-42";
	public static final String CT_GLOBAL_031 = "250-51-110-202-228-16-65-210-39-116-184-8-58-100-163-153-197-197-237-143-92-56-202-121-143-51-228-52-139-159-203-216-128-151-139-136-55-239-165-251-157-172-30-194-213-208-165-132-14-47-161-210-220-46-82-146-223-31-4-73-255-26-34-77-244-240-58-236-126-170-89-49-215-81-149-179-14-98-49-234";
	public static final String CT_GLOBAL_032 = "71-150-222-111-98-158-201-47-164-152-239-129-50-142-160-2-250-180-26-9-86-107-89-64-105-227-35-153-0-62-198-115";
	public static final String CT_GLOBAL_033 = "203-41-72-50-161-159-70-26-41-106-29-78-230-195-49-26-160-12-81-16-14-208-91-91-255-216-3-180-187-16-99-171-82-214-155-18-253-73-131-165-152-141-104-209-61-29-196-23";
	public static final String CT_GLOBAL_034 = "223-95-67-219-99-137-250-253-1-211-17-231-145-153-107-56-148-240-84-142-92-121-70-228-80-64-196-154-82-53-246-183-159-180-26-211-83-180-189-130-194-64-248-3-89-2-67-54";
	public static final String CT_GLOBAL_035 = "12-31-15-192-234-188-90-112-185-20-252-94-224-169-23-124";
	public static final String CT_GLOBAL_036 = "223-95-67-219-99-137-250-253-157-227-142-31-13-144-248-83-140-99-77-84-52-79-64-0-39-53-21-176-89-110-54-94-61-95-4-72-109-246-85-133-17-166-242-204-161-202-123-42";
	public static final String CT_GLOBAL_037 = "184-150-251-215-205-65-212-252-207-227-236-0-73-198-122-116";
	public static final String CT_GLOBAL_038 = "111-221-252-50-183-38-225-5";
	public static final String CT_GLOBAL_039 = "185-131-171-40-56-243-71-95";
	public static final String CT_GLOBAL_040 = "183-195-55-155-215-69-203-247-43-150-239-140-17-233-154-60-207-227-236-0-73-198-122-116";
	public static final String CT_GLOBAL_041 = "108-136-200-113-86-209-57-118-44-197-252-134-195-156-153-20";
	public static final Long ID_INF_BOLETO = 1L;
	
	public static TreeMap<Character, Character> getLetras() {
		TreeMap<Character, Character> retorno = new TreeMap<Character, Character>();
		for (int i = 0; i < letras.length(); i++) {
			retorno.put(letras.charAt(i), letras.charAt(i));
		}
		return retorno;
	}

	public static TreeMap<Integer, Integer> getNumeros() {
		TreeMap<Integer, Integer> retorno = new TreeMap<Integer, Integer>();
		for (int i = 0; i < numeros.length(); i++) {
			retorno.put(Integer.parseInt(numeros.substring(i, i+1)), Integer.parseInt(numeros.substring(i, i+1)));
		}
		return retorno;
	}
	
	public static String arrayAjuda(int pos){
		if(mapaAjuda == null){
			mapaAjuda = new HashMap<Integer, String>();
			mapaAjuda.put(0, "<p>&#187; Utilize seu e-mail de cadastro e sua senha.</p><p>&#187; Caso não possua uma, verifique seu e-mail ou insira apenas seu e-mail e clique em &quot;esqueci minha senha&quot;, para que seja enviada uma nova senha para seu e-mail.</p>");
			mapaAjuda.put(1, "<p>&#187; Para imprimir a segunda via de sua conta, basta apenas informar o CPF do titular da conta no campo abaixo, clicar no botão &quot;OK&quot;.</p><p>&#187; Será exibido todos os boletos em aberto, você escolhe o que deseja pagar e imprime.</p>");
			mapaAjuda.put(2, "<p>&#187; Basta apenas você digitar sua nova senha e confirmar para que sua senha seja trocada.</p>");
			mapaAjuda.put(3, "<p>&#187; Você pode alterar todos os usuários do site. Clientes ou administradores podem ser alterados. </p><p>&#187; <strong>Dica</strong>: para ajudar na procura de endereço, basta apenas digitar o CEP e clicar no botãozinho de check ao lado do campo que é preenchido automaticamente.</p>");
			mapaAjuda.put(4, "<p>&#187; Marcar apenas se o cliente for administrativo. Isso serve quando o usuário cadastrado não possuir ponto de acesso.</p>");
			mapaAjuda.put(5, "<p>&#187; Habilita o usuário para pagar boleto em mão. Se o cliente não puder, não será permitido dar baixa em seu boleto manualmente.</p>");
			mapaAjuda.put(6, "<p>&#187; Habilita o usuário para receber e-mails monetários, como de pagamento de boletos e lucros.</p>");
			mapaAjuda.put(7, "<p>&#187; Você pode visualizar todos os boletos que foram pagos em mãos pelos seus clientes, basta digitar a data inicial e a data final da pesquisa.</p><p>&#187; Se o dinheiro deve ser entregue à algum administrador, você pode clicar no campo da última coluna da tabela, para marcar o pagamento como entregue ao administrador.</p>");
			mapaAjuda.put(8, "<p>&#187; Relatório que contém alguns LOG dos serviços efetuados, como leitura de arquivo de retorno dos boletos e erros</p>");
			mapaAjuda.put(9, "<p>&#187; Você pode visualizar a posição de todos os clientes em um mapa</p><p>&#187; Pode, também, traçar uma rota entre sua empresa e a casa do cidadão.</p>");
			mapaAjuda.put(10, "<p>&#187; Botão habilitado apenas para rota de um usuário</p>");
			mapaAjuda.put(11, "<p>&#187; Além de visualizar as demandas abertas pelo sistema, você pode detalhar clicando no nome do cliente que solicitou a demanda.</p>");
			mapaAjuda.put(12, "<p>&#187; Colocar um nome, será visto apenas demandas do cliente escolhido, deixe em branco para visualizar se filtro por cliente.</p>");
			mapaAjuda.put(13, "<p>&#187; Lista todas mensagens de contatos que seu sistema recebeu. Para detalhar clique no botão \"ver\" da linha desejada.</p>");
			mapaAjuda.put(14, "<p>&#187; Lista todos os boletos abertos até o momento, abertos e pagos.</p><p>&#187; Se estiver aberto, você poderá imprimir clicando na linha correspondente.</p>");
			mapaAjuda.put(15, "<p>&#187; Calcula um estimativa de movimentação financeira ocorrida em um determinado mês.</p><p>&#187; Cálculo feito com base em todos os boletos pagos, descontando pagamentos de fornecedores, serviços e taxas.</p>");
			mapaAjuda.put(16, "<p>&#187; Para localizar e detalhar uma demanda, você deve possuir o número da mesma, caso não possua, vá até relatório de demandas e clique sobre a demanda desejada que será redirecionado para cá</p>");
			mapaAjuda.put(17, "<p>&#187; Para solicitar uma demanda, você deve digitar o cliente que você deseja.</p>");
			mapaAjuda.put(18, "<p>&#187; Se não for atendida até essa data será convertida em demanda pendente de resolução.</p>");
			mapaAjuda.put(19, "<p>&#187; Peão que vai receber um e-mail convidando-o a atender essa demanda.</p>");
			mapaAjuda.put(20, "<p>Esse é pedreira.... </p><p>&#187; torre não é o mesmo que servidor, você pode possuir várias torres em um único servidor.</p><p>&#187; Cada torre possui uma antena, pos assim dizer, que fará parte de um ou mais servidores.</p><p>&#187; Em outro menu, GRUPOS DE TORRES, você adiciona torres ao grupo, cada torre tem uma função específica, pode ser wireless, hotspot ou PPPoE.</p><p>&#187; Coloquei o nome como torre para facilitar na memorização.</p>");
			mapaAjuda.put(21, "<p>&#187; Atenção: esse nome deverá ser o mesmo utilizado no campo NAME, seja uma interface, serviço de HOTSPOT o de DHCP.</p>");
			mapaAjuda.put(22, "<p>&#187; Ip de conexão que o servidor que tem esse sistema instalado consegue enxergar.</p>");
			mapaAjuda.put(23, "<p>&#187; Ip de conexão secundário que o servidor que tem esse sistema instalado consegue enxergar, se o primeiro IP não atender esse passa a ser o principal e o primeiro vira reserva.</p>");
			mapaAjuda.put(24, "<p>&#187; Se você tiver utilizando o intermediador de requisições, colocar seu IP neste campo.</p>");
			mapaAjuda.put(25, "<p>&#187; Nesta página você adiciona as imagens de topo tanto da web com do mobile.</p>");
			mapaAjuda.put(26, "<p>&#187; Nessa página você ira fazer a junção de torres e criará um servidor, essa junção é lógica.</p><p>&#187; Esse servidor é a junção de serviços que formaram um único serviço de internet. Você pode configurar vários servidores, cada um com planos e serviços específicos.</p>");
			mapaAjuda.put(27, "<p>&#187; Aqui será apenas um nome que você irá utilizar para identificar o servidor.</p>");
			mapaAjuda.put(28, "<p>&#187; Aqui você agrupa as torres(serviços) que farão parte de um servidor.</p><p>&#187; Ex.: Se você tiver em uma torre apenas autenticação hotspot, em outro você tem DHCP e em outro a Conexão wireless, esse é o campo que você faz a junção desses serviços e/ou torres.</p>");
			mapaAjuda.put(29, "<p>&#187; Aqui você configura a faixa de endereço IP para um determinado servidor, logicamente deverá possuir um servidor criado para que haja um cadastro de IP para o mesmo.</p><p>&#187; Atenção: Aqui é necessário conhecer sobre máscara e subrede para configurar o IP.</p>");
			mapaAjuda.put(30, "<p>&#187; Não é necessário preencher o IP de gateway ou broadcast, o cálculo é feito pelo sistema. Apenas insira um de exemplo.</p>");
			mapaAjuda.put(31, "<p>&#187; Você configura os comandos que cada torre irá receber.</p><p>&#187; Para facilitar o preenchimento dessa tela, sugiro que você simule em seu serviço o cadastro de um cliente. Copie todos os comandos utilizados.</p><p>&#187; Você configura os comandos que cada torre irá receber.</p><p>&#187; Você pode inserir até 6 comandos deiferentes para cada ação. trocando as palavras reservadas pelos símbolos da tabela no fim da página.</p>");
			mapaAjuda.put(32, "<p>&#187; Com todas as torres criadas e juntadas a um grupo, selecione o grupo que deseja configurar</p>");
			mapaAjuda.put(33, "<p>&#187; Escolha qual será a ação adotada pelos comandos. lembrando que o tipo \"altera\" possui três atributos, quando o cliente está habilitado, com mensagem e bloqueado.</p><p>&#187; O modo desativar cliente esclui ele do servidor.</p>");
			mapaAjuda.put(34, "<p>&#187; Aqui você pode visualizar os detalhes de um boleto pago ou aberto.</p>");
			mapaAjuda.put(35, "<p>&#187; Aqui você pode visualizar os detalhes de um boleto pago ou aberto. Pode também dar baixa em boleto. A baixa será computada como pagamento manual.</p>");
			mapaAjuda.put(36, "<p>&#187; Faz a manipulação de custos, Por medida de segurança um custo lançado não pode ser alterado.</p>");
			mapaAjuda.put(37, "<p>&#187; Aqui você pode realizar várias ações em boletos de clientes. Como criar, conceder crédito ou débito, entre outros.</p>");
			mapaAjuda.put(38, "<p>&#187; Você pode criar boleto extra, imprimir por data ou por usuário.</p>");
			mapaAjuda.put(39, "<p>&#187; Esses comandos são os que são disparados para consultar cliente e servidor, aconselhável não mexer.</p>");
			mapaAjuda.put(40, "<p>&#187; Página utilizada para adiar a data de pagamento dos boletos, você irá gerar novos boletos com vencimento adiado, mesmo utilizando a F2B como gateway o boleto deverá ser impresso por aqui e entregue pessoalmente aos clientes..</p><p>&#187; O boleto da F2B não é alterado nem cancelado, porque o cliente pode pagar antes e você não sabe.</p>");
			mapaAjuda.put(41, "<p>&#187; Você só pode alterar seu número de telefone, email e escolher se deseja receber os boletos em casa, para alteração de outros dados entre em contato conosco.</p>");
			mapaAjuda.put(42, "<p>&#187; Caso você desmarque essa opção você não receberá seus boletos em casa, portanto você deverá imprimir seu boleto diretamente conosco.</p>");
			mapaAjuda.put(43, "<p>&#187; Um cliente só poderá ser inserido após o cadastro do AP.</p><p>&#187; Se não possuir um AP vinculado o usuário será cadastrado mas não poderá possuir funções de clientes.</p>");
			mapaAjuda.put(44, "<p>&#187; Basta preencher o CEP e clicar no botão ao lado para que o endereço seja preenchido automaticamente.</p>");
			mapaAjuda.put(45, "<p>&#187; Escolha o banco que você deverá utilizar para emissão de cobrança.</p>");
			mapaAjuda.put(46, "<p>&#187; Usuário e senha fornecido pela F2B para o serviço de Web Service. Não é sua senha de login no site.</p>");
			mapaAjuda.put(47, "<p>&#187; Informação fornecida pelo banco.</p>");
			mapaAjuda.put(48, "<p>&#187; Se o seu banco fornece um arquivo de retorno dos boletos pagos o sistema necessita apenas das informações abaixo para que os boletos sejam processados como pago.</p><p>&#187; O arquivo geralmente é posicional, então você deve informar a posição inicial e a final que o campo está no arquivo.</p>");
			mapaAjuda.put(49, "<p>&#187; Cada campo possui uma informação na frente, leia cada campo para saber sua função.</p>");
			mapaAjuda.put(50, "<p>&#187; Mostra o tráfego por cada torre medida no decorrer do dia.</p>");
			mapaAjuda.put(51, "<p>&#187; Este relatório serve para podermos analisar a qualidade de sinal de cada cliente.</p>");
			mapaAjuda.put(52, "<p>&#187; Caso você pesquise apenas pela data, sem informar um cliente, será calculado a média para cada cliente, para visualizar um cliente basta clicar no nome do cidadão ou preenchê-lo ao lado que será exibido todo histórico do mesmo.</p>");
			mapaAjuda.put(53, "<p>&#187; Página utilizada para realizar diversos tipos de consulta e teste de um cliente.</p>");
			mapaAjuda.put(54, "<p>&#187; Utilizado para cadastrar um ponto de acesso, esse ponto de acesso (AP) não estará vinculado a nenhum cliente. O vínculo será feito na inserção de usuário.</p>");
			mapaAjuda.put(55, "<p>&#187; Endereço IP relativo à torre escolhida.</p>");
			mapaAjuda.put(56, "<p>&#187; Qual a porcentagem que este usuário ficará ao distribuir os lucros.</p>");
			mapaAjuda.put(57, "<p>&#187; A criação do boleto depende da data de vencimento. Se o vencimento for maior que "+ ConfigUtil.getInstance().getIntProperty("dias_envio_antecipado_boleto", "15") +" dias o boleto é apenas criado, e seguirá o fluxo normal de boletos da mensalidade.</p>");
		}
		
		return mapaAjuda.get(pos);
	}
}

//alterado
function carregaDemanda(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var id = $$("id").value;
	if(id != null && id != ""){
		AdministracaoJS.localizaDemanda(id, carregaclientes);
	}else{
		modalMensagem.escondeModal();
	}
}
function carregaclientes(d){
	if(d != null){
		$$("nome").innerHTML = "&nbsp;" + d.usuarioSolicitante.usuarioNome;
		$$("cpf").innerHTML = "&nbsp;" + d.usuarioSolicitante.usuarioCpf;
		$$("endereco").innerHTML = "&nbsp;" + d.usuarioSolicitante.usuarioEndereco + " - " + d.usuarioSolicitante.usuarioComplementoEndereco + " - " + d.usuarioSolicitante.usuarioBairro;
		$$("cidade").innerHTML = "&nbsp;" + d.usuarioSolicitante.usuarioCidade;
		$$("estado").innerHTML = "&nbsp;" + d.usuarioSolicitante.usuarioEstado;
		$$("telefone").innerHTML = "&nbsp;" + d.usuarioSolicitante.usuarioTelefone1;
		$$("celular").innerHTML = "&nbsp;" + d.usuarioSolicitante.usuarioTelefone2;
		$$("idcliente").innerHTML = "&nbsp;" + d.usuarioSolicitante.usuarioId;
		$$("mac").innerHTML = "&nbsp;" + d.usuarioSolicitante.usuarioMac;
		$$("servidor").innerHTML = "&nbsp;" + d.usuarioSolicitante.servidor.servidorNome;
		if(d.usuarioSolicitante.enderecoIp != null){
			$$("ip").innerHTML = "&nbsp;" + d.usuarioSolicitante.enderecoIp.enderecoipEndereco;
		}
		
		$$("abertura").innerHTML = "&nbsp;" + d.dataAbertura;
		$$("previsao").innerHTML = "&nbsp;" + d.dataPrevistaAtendimento;
		$$("responsavel").innerHTML = "&nbsp;" + (d.usuarioResponsavel == null ? "" : d.usuarioResponsavel.usuarioNome);
		$$("encerramento").innerHTML = "&nbsp;" + (d.dataEncerramento == null ? "" : d.dataEncerramento);
		$$("abriu").innerHTML = "&nbsp;" + d.usuarioAbriu.usuarioNome;
		$$("descricao-enc").innerHTML = "&nbsp;" + d.demandasDescricaoEncerramento;
		$$("descricao-abr").innerHTML = "&nbsp;" + d.demandasDescricao;
		$$("imprime").disabled = false;
	}else{
		$$("imprime").disabled = true;
		alert("Demanda não encontrada");
	}
	modalMensagem.escondeModal();
}

function imprimeDemanda(){
	var larguraTela = screen.width - 50;
	var alturaTela = screen.height - 120;

	var top = 10;
	var left = (screen.width / 2) - (770 / 2);
	
	var popup = window.open('imprimeDemanda.do?id=' + $$("id").value, 'Localiza','width=770,height='+ alturaTela +',top='+ top +',left='+ left +',toolbar=no,location=no,status=no,menubar=yes,scrollbars=yes,resizable=yes');
	
	if(popup == null){
		alert("Bloqueador de pop ativado.\nDesative para visualizar a página.");
	}
}

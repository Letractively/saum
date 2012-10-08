//alterado
function carregaUsuarios(){
		var cliente = $$("cliente").value;
		if(cliente != "" && cliente.length > 0){
			modalMensagem.criaMosca();
			modalMensagem.criaDivConteudo();
			if($$("tp").checked){
				AdministracaoJS.buscaUsuarioPeloCPF(cliente, carregaUsuariosParaAlteraccaoCallBack);
			}else{
				AdministracaoJS.buscaUsuarioPeloNome(cliente, carregaUsuariosParaAlteraccaoCallBack);
			}
		}
}
function carregaUsuariosParaAlteraccaoCallBack(usuario){
	if(usuario != null){
		if(!usuario.usuarioAdministrativo){
			carregaclientes(usuario);
		}else{
			alert("Atendimento para usuário administrativo\nnão permitido.");
		}
	}else{
		alert("Não encontrado!");
	}
	modalMensagem.escondeModal();
}
function carregaclientes(usuario){
	$$("id").value = usuario.usuarioId;
	$$("nome").innerHTML = "&nbsp;" + usuario.usuarioNome;
	$$("mac").innerHTML = "&nbsp;" + usuario.usuarioMac;
	$$("cpf").innerHTML = "&nbsp;" + usuario.usuarioCpf;
	$$("telefone").innerHTML = "&nbsp;" + usuario.usuarioTelefone1;
	$$("celular").innerHTML = "&nbsp;" + usuario.usuarioTelefone2;
	$$("endereco").innerHTML = "&nbsp;" + usuario.usuarioEndereco;
	$$("complemento").innerHTML = "&nbsp;" + usuario.usuarioComplementoEndereco;
	$$("bairro").innerHTML = "&nbsp;" + usuario.usuarioBairro;
	$$("cidade").innerHTML = "&nbsp;" + usuario.usuarioCidade;
	$$("estado").innerHTML = "&nbsp;" + usuario.usuarioEstado;
	$$("email").innerHTML = "&nbsp;" + usuario.usuarioEmail;
}
function gravaDemanda(){
	var idCliente = $$("id").value;
	var descricao = $$("descricao-demanda").value;
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var data = $$("data").value;
	var dia = data.substring(0, 2);
	var mes = data.substring(3, 5);
	var ano = data.substring(6, 10);
	
	var oHora = $$("hora");
	var hora = oHora.options[oHora.selectedIndex].value;
	
	var oResponsavel = $$("responsavel");
	var responsavel = oResponsavel.options[oResponsavel.selectedIndex].value;
	
	var oMinuto = $$("minuto");
	var minuto = oMinuto.options[oMinuto.selectedIndex].value;
	AdministracaoJS.gravaDemanda(idCliente, descricao, dia, mes, ano, hora, minuto, responsavel, gravaDemandaCallBack);
}
function novo(){
	fnSolicitarDemanda();
}

function gravaDemandaCallBack(ret){
	if(ret > 0){
		if($$("grava") != null){
			$$("grava").disabled = true;
		}
		var pass = false;
		if($$("novo") != null){
			$$("novo").disabled = false;
			pass = true;
		}
		if($$("imprime") != null){
			$$("imprime").disabled = false;
			pass = true;
		}
		if($$("id-demanda") != null){
			$$("id-demanda").value = ret;
			pass = true;
		}
		if(pass != true){
			fnSolicitarDemanda();
		}
	}else if (ret == -1){
		alert("Não gravado.\nData menor ou igual ao dia atual");
	}else{
		alert("Não gravado.");
	}
	modalMensagem.escondeModal();
}
function imprimeDemanda(){
	var larguraTela = screen.width - 50;
	var alturaTela = screen.height - 120;

	var top = 10;
	var left = (screen.width / 2) - (770 / 2);
	
	var popup = window.open('imprimeDemanda.do?id=' + $$("id-demanda").value, 'Localiza','width=770,height='+ alturaTela +',top='+ top +',left='+ left +',toolbar=no,location=no,status=no,menubar=yes,scrollbars=yes,resizable=yes');
	
	if(popup == null){
		alert("Bloqueador de pop ativado.\nDesative para visualizar a página.");
	}
}
function iniciaCalendario() {
	Calendar.setup({
		inputField : "data",
		button     : "bt-data",
		ifFormat   : "%d/%m/%Y"
	});
}
function buscaUsuariosAdministrativosCallBack(lista){
	var oComboBox = $$('responsavel');
	for (var i = 0; i < lista.length ; i++){
		oComboBox.options[i] = new Option(lista[i].usuarioNome, lista[i].usuarioId);
	}
}
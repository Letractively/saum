//alterado
//comeca o mosca
var Modal = function(parametros){
	var objeto = this;
	var body = document.documentElement;
	var alturaConteiner = parametros.altura;
	var larguraConteiner = parametros.largura + "px";
	var endereco = parametros.endereco;
	var temp = (screen.width - parametros.largura) /2;
	var distanciaEsquerda =  parseInt(temp) + "px";
	if(alturaConteiner == 0){
		var alturaIframe = "95%";
		alturaConteiner = "94%";
	}else{
		var alturaIframe = (alturaConteiner - 25) + "px";
		alturaConteiner = alturaConteiner + "px";
	}

this.criaDivConteudo = function(){
		var oDiv = document.getElementById("div-content-janela");
		if(oDiv != null){
			
			var oDivTopo = document.createElement("div");
			var iframe = document.createElement("iframe");
			var botao = document.createElement("input");
			botao.type = "button";
			
			oDiv.style.left = distanciaEsquerda;
			oDiv.style.width = larguraConteiner;
			oDiv.style.height = alturaConteiner;
			oDiv.style.top = (this.getScroll() + 10) + "px";
			oDiv.style.border = "#888888 2px solid";
			
			botao.value = "X";
			botao.onclick = escondeModal;
			botao.className = "button-fecha-mosca";
			
			oDivTopo.id = "div-content-topo";
			oDivTopo.className = "topo-modal";	
			oDivTopo.appendChild(botao);

			iframe.id = "iframe-conteudo";
			iframe.className = "iframe-conteudo";
			iframe.src = endereco;
			iframe.scrolling = "auto";
			iframe.frameBorder = 0;
			iframe.style.height = alturaIframe;
			
			oDiv.appendChild(oDivTopo);
			oDiv.appendChild(iframe);
			
			oDiv.style.display = "block";

		}else{
			
			oDiv = document.createElement("div");
			var oDivTopo = document.createElement("div");
			var iframe = document.createElement("iframe");
			var botao = document.createElement("input");
			botao.type = "button";
			
			oDiv.id = "div-content-janela";
			oDiv.className = "conteudo-modal";
			oDiv.style.top = (this.getScroll() + 10) + "px";
			oDiv.style.left = distanciaEsquerda;
			oDiv.style.width = larguraConteiner;
			oDiv.style.height = alturaConteiner;
			oDiv.style.border = "#888888 1px solid";
			
			botao.value = "X";
			botao.onclick = escondeModal;
			botao.className = "button-fecha-mosca";
			
			oDivTopo.id = "div-content-topo";
			oDivTopo.className = "topo-modal";	
			oDivTopo.appendChild(botao);
						
			iframe.id = "iframe-conteudo";
			iframe.className = "iframe-conteudo";
			iframe.src = endereco;
			iframe.scrolling = "auto";
			iframe.frameBorder = 0;
			iframe.style.height = alturaIframe;
			
			oDiv.appendChild(oDivTopo);
			oDiv.appendChild(iframe);
			
			window.document.body.appendChild(oDiv);
		}
	};
	
	this.criaMosca = function (){
			var iframe = document.getElementById("iframe-mosca");
		if(iframe){
			iframe.style.display = "block";
			iframe.style.height = this.getAlturaPagina();
		}else{
			iframe = document.createElement("iframe");
			iframe.id = "iframe-mosca";
			iframe.className = "mosca";
			iframe.style.height = this.getAlturaPagina();
			iframe.src = "iframe.html";
			iframe.frameBorder = 0;
			window.document.body.appendChild(iframe);
		}
	};
	
	var escondeModal = function(funcao){
		var conteudo = document.getElementById("div-content-janela");
		body.style.overflow = "auto";
		var mosca = document.getElementById("iframe-mosca");
		if(conteudo){
			if(conteudo.hasChildNodes()){
				while( conteudo.hasChildNodes() ) { conteudo.removeChild( conteudo.lastChild ); }
			}
			conteudo.style.display = "none";
		}
		objeto.quandoFechar();
		mosca.style.display = "none";
	};
	
	this.getScroll = function (){
		var the_x;
		var the_y;
		if (self.pageYOffset) {
		// browsers other than internet explorer
			the_x = self.pageXOffset;
			the_y = self.pageYOffset;
		}else if (document.documentElement && document.documentElement.scrollTop){
		// internet explorer 6      
			the_x = document.documentElement.scrollLeft;
			the_y = document.documentElement.scrollTop;
		}else if (document.body){
		 // all other internet explorer versions
			the_x = document.body.scrollLeft;
			the_y = document.body.scrollTop;
		}
	
		return the_y;
	};

	this.getAlturaPagina = function(){
		
		var alturaIE = document.body.offsetHeight;
		var alturaFX = document.body.scrollHeight;
		var alturaSemScroll = document.body.scrollHeight + 4;
		
		if(alturaIE < alturaFX){
			if (alturaFX < alturaSemScroll){
				return alturaSemScroll + "px";
			}else{
				return alturaFX + "px";
			}
		} else {
			if (alturaIE < alturaSemScroll){
				return alturaSemScroll + "px";
			}else{
				return alturaIE + "px";
			}
		}
	};
	
	this.quandoFechar = function(){};
};
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
//comeca o mosca de tela de mensagem
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
/*	
	var variavel = new modalMsg();
	var parametros = {
	msg:"dfadf da afsdf sdf",
	tipo:1
	};
	
	modal.setParametros(parametros);
	modal.criaMosca();
	modal.criaDivConteudo();
	modal.escondeModal();
	
	template modal carregando
	<script type="text/javascript">
		modalMensagem.criaMosca();
		modalMensagem.criaDivConteudo();
	</script>
	modalMensagem.escondeModal();
*/
var modalMsg = function(){
	
	var tipo = "";
	var msg = "";
	var objeto = this;
	var body = document.documentElement;
	var alturaConteiner = "150px";
	var larguraConteiner = "290px";
	var esq = (screen.width - 300) / 2;
	var distanciaEsquerda = parseInt(esq) + "px";

	this.criaDivConteudo = function(){
		var oDiv = document.getElementById("div-content-janela");
		if(oDiv != null){
			oDiv.id = "div-content-janela";
			oDiv.className = "conteudo-modal-msg";
			oDiv.style.left = distanciaEsquerda;
			oDiv.style.width = larguraConteiner;
			oDiv.style.height = alturaConteiner;
			oDiv.style.top = parseInt(this.getScroll() + 100) + "px";
			oDiv.style.border = "#cccccc 2px solid";
			oDiv.innerHTML = this.getMensagem();
			oDiv.style.display = "block";
		}else{
			oDiv = document.createElement("div");			
			oDiv.id = "div-content-janela";
			oDiv.className = "conteudo-modal-msg";
			oDiv.style.top = parseInt(this.getScroll() + 100) + "px";
			oDiv.style.left = distanciaEsquerda;
			oDiv.style.width = larguraConteiner;
			oDiv.style.height = alturaConteiner;
			oDiv.style.border = "#cccccc 2px solid";
			oDiv.innerHTML = this.getMensagem();
			window.document.body.appendChild(oDiv);
		}
	};
	
	this.setParametros = function(parametros){
		tipo = parametros.tipo;
		msg = parametros.msg;
	};
	
	this.criaMosca = function (){
			var iframe = document.getElementById("iframe-mosca");
		if(iframe){
			iframe.style.display = "block";
			iframe.style.height = this.getAlturaPagina();
		}else{
			iframe = document.createElement("iframe");
			iframe.id = "iframe-mosca";
			iframe.className = "mosca";
			iframe.style.height = this.getAlturaPagina();
			iframe.src = "iframe.html";
			iframe.frameBorder = 0;
			window.document.body.appendChild(iframe);
		}
	};
	
	this.escondeModal = function(){
		var conteudo = document.getElementById("div-content-janela");
		body.style.overflow = "auto";
		var mosca = document.getElementById("iframe-mosca");
		if(conteudo != null){
			if(conteudo.hasChildNodes()){
				while( conteudo.hasChildNodes() ) { conteudo.removeChild( conteudo.lastChild ); }
			}
			conteudo.style.display = "none";
		}
		mosca.style.display = "none";
	};
	
	this.getScroll = function (){
		var the_x;
		var the_y;
		if (self.pageYOffset) {
		// browsers other than internet explorer
			the_x = self.pageXOffset;
			the_y = self.pageYOffset;
		}else if (document.documentElement && document.documentElement.scrollTop){
		// internet explorer 6      
			the_x = document.documentElement.scrollLeft;
			the_y = document.documentElement.scrollTop;
		}else if (document.body){
		 // all other internet explorer versions
			the_x = document.body.scrollLeft;
			the_y = document.body.scrollTop;
		}
	
		return the_y;
	};
	
	this.getAlturaPagina = function(){
		
		var alturaIE = document.body.offsetHeight;
		var alturaFX = document.body.scrollHeight;
		var alturaSemScroll = document.body.scrollHeight + 4;
		var ret;
		if(alturaIE < alturaFX){
			if (alturaFX < alturaSemScroll){
				ret = alturaSemScroll + "px";
			}else{
				ret = alturaFX + "px";
			}
		} else {
			if (alturaIE < alturaSemScroll){
				ret = alturaSemScroll + "px";
			}else{
				ret = alturaIE + "px";
			}
		}
		return ret;
	};
	
	this.quandoFechar = function(){};
	
	this.getMensagem = function(){
	var msgTipo = "";
	var img = "";
		switch (tipo){
			case 1:
				img = "img/tempo_carregando.gif";
				msgTipo = "Aguarde carregando...";
				break;
			case 2:
				img = "img/alerta.gif";
				msgTipo = "Atenção!";
				break;
			case 3:
				img = "img/erro.gif";
				msgTipo = "Um erro ocorreu!";
				break;
		
		}
		
		var html = "<table height=\"100%\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr>";
		html += "<td width=\"26%\" height=\"50\" align=\"center\" valign=\"middle\">";
		html += "<img src=\"" + img + "\" alt=\"...\"/>";
		html += "</td><td align=\"center\" valign=\"middle\"><b>"+ msgTipo +"</b>";
		html += "</td></tr><tr><td colspan=\"2\" align=\"center\" valign=\"middle\" id=\"mensagem\">";
		html += msg;
		html += "</td></tr><tr><td colspan=\"2\" align=\"center\" valign=\"middle\" height=\"11\">";
		html += "<table height=\"10\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
		html += "<tr><td width=\"5%\" id=\"tb1\"><img src=\"#\" height=\"1\" width=\"1\" alt=\"\" /></td>";
		html += "<td width=\"5%\" id=\"tb2\"><img src=\"#\" height=\"1\" width=\"1\" alt=\"\" /></td>";
		html += "<td width=\"5%\" id=\"tb3\"><img src=\"#\" height=\"1\" width=\"1\" alt=\"\" /></td>";
		html += "<td width=\"5%\" id=\"tb4\"><img src=\"#\" height=\"1\" width=\"1\" alt=\"\" /></td>";
		html += "<td width=\"5%\" id=\"tb5\"><img src=\"#\" height=\"1\" width=\"1\" alt=\"\" /></td>";
		html += "<td width=\"5%\" id=\"tb6\"><img src=\"#\" height=\"1\" width=\"1\" alt=\"\" /></td>";
		html += "<td width=\"5%\" id=\"tb7\"><img src=\"#\" height=\"1\" width=\"1\" alt=\"\" /></td>";
		html += "<td width=\"5%\" id=\"tb8\"><img src=\"#\" height=\"1\" width=\"1\" alt=\"\" /></td>";
		html += "<td width=\"5%\" id=\"tb9\"><img src=\"#\" height=\"1\" width=\"1\" alt=\"\" /></td>";
		html += "<td width=\"5%\" id=\"tb10\"><img src=\"#\" height=\"1\" width=\"1\" alt=\"\" /></td>";
		html += "<td width=\"5%\" id=\"tb11\"><img src=\"#\" height=\"1\" width=\"1\" alt=\"\" /></td>";
		html += "<td width=\"5%\" id=\"tb12\"><img src=\"#\" height=\"1\" width=\"1\" alt=\"\" /></td>";
		html += "<td width=\"5%\" id=\"tb13\"><img src=\"#\" height=\"1\" width=\"1\" alt=\"\" /></td>";
		html += "<td width=\"5%\" id=\"tb14\"><img src=\"#\" height=\"1\" width=\"1\" alt=\"\" /></td>";
		html += "<td width=\"5%\" id=\"tb15\"><img src=\"#\" height=\"1\" width=\"1\" alt=\"\" /></td>";
		html += "<td width=\"5%\" id=\"tb16\"><img src=\"#\" height=\"1\" width=\"1\" alt=\"\" /></td>";
		html += "<td width=\"5%\" id=\"tb17\"><img src=\"#\" height=\"1\" width=\"1\" alt=\"\" /></td>";
		html += "<td width=\"5%\" id=\"tb18\"><img src=\"#\" height=\"1\" width=\"1\" alt=\"\" /></td>";
		html += "<td width=\"5%\" id=\"tb19\"><img src=\"#\" height=\"1\" width=\"1\" alt=\"\" /></td>";
		html += "<td width=\"5%\" id=\"tb20\"><img src=\"#\" height=\"1\" width=\"1\" alt=\"\" /></td>";
		html += "</tr></table></td></tr></table>";
		return html;
	};
};
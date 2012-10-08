function abreMapaEqp(){
	
	var parametros = {
	altura:400,
	largura:750,
	endereco:"esquema/esquema.htm"};
	var modal = new Modal(parametros);
	
	modal.criaMosca();
	modal.criaDivConteudo();
}

function abreAccessPoint(){
	if($$("fieldset-ap").style.display == "block"){
		$$("fieldset-ap").style.display = "none";
	}else{
		$$("fieldset-ap").style.display = "block";
	}
	$$("fieldset-antena").style.display = "none";
	$$("fieldset-cabo").style.display = "none";
	$$("fieldset-pig").style.display = "none";
}

function abreAntena(){
	if($$("fieldset-antena").style.display == "block"){
		$$("fieldset-antena").style.display = "none";
	}else{
		$$("fieldset-antena").style.display = "block";
	}
	$$("fieldset-ap").style.display = "none";
	$$("fieldset-cabo").style.display = "none";
	$$("fieldset-pig").style.display = "none";
}

function abreCabo(){
	if($$("fieldset-cabo").style.display == "block"){
		$$("fieldset-cabo").style.display = "none";
	}else{
		$$("fieldset-cabo").style.display = "block";
	}
	$$("fieldset-pig").style.display = "none";
	$$("fieldset-ap").style.display = "none";
	$$("fieldset-antena").style.display = "none";
}

function abrePig(){
	if($$("fieldset-pig").style.display == "block"){
		$$("fieldset-pig").style.display = "none";
	}else{
		$$("fieldset-pig").style.display = "block";
	}
	$$("fieldset-cabo").style.display = "none";
	$$("fieldset-ap").style.display = "none";
	$$("fieldset-antena").style.display = "none";
}
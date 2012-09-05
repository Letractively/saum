function _(nome){
	return document.getElementById(nome);
}
function inicializaMenu(){
	$('#lateral-esquerda span').stop().animate({'marginLeft' : '-110px' }, 1000);
	$('#lateral-esquerda > li').hover(
		function() {$('span', $(this)).stop().animate({'marginLeft' : '-2px'}, 200);}, 
		function() {$('span', $(this)).stop().animate({'marginLeft' : '-110px'}, 200);}
	);
}
var objD;var objE;var nivelD;var nivelE;var orient;function iniciaEsq_Dir(e,d){iniciaEsq_Di(e,d,true);}
function iniciaEsq_Di(e,d,h){objE=e;objD=d;nivelE=-1*(window.innerWidth/2);nivelD=window.innerWidth/2;$$(d).style.display="block";slide();window.scrollTo(0,1);if(h){UsuariosPortalJS.setHistoricoMobile(1,e,d);}}
function slide(){var e=$$(objE);var d=$$(objD);e.style.left=nivelE+"px";d.style.left=nivelD+"px";orient=window.innerWidth;nivelD=nivelD-65;nivelE=nivelE-65;if(nivelE>=-1*(orient+60)){window.setTimeout("slide();",100);}else{d.style.left="0px";e.style.display="none";}}
function iniciaDir_Esq(e,d){iniciaDir_Es(e,d,true);}
function iniciaDir_Es(e,d,h){objE=e;objD=d;nivelE=-1*(window.innerWidth/2);nivelD=window.innerWidth/2;$$(e).style.display="block";slide2();window.scrollTo(0,1);if(h){UsuariosPortalJS.setHistoricoMobile(2,e,d);}}
function slide2(){var e=$$(objE);var d=$$(objD);e.style.left=nivelE+"px";d.style.left=nivelD+"px";orient=window.innerWidth;nivelD=nivelD+65;nivelE=nivelE+65;if(nivelE<=0){window.setTimeout("slide2();",100);}else{e.style.left="0px";d.style.display="none";}}
function verificaHistorico(){UsuariosPortalJS.getHistoricoMobile(redirecionaCallBack);}
function redirecionaCallBack(ret){if(ret!=null){var tipo;var de;var para;for(var key in ret){if(key=="tipo"){tipo=ret[key];}else if(key=="de"){de=ret[key];}else if(key=="para"){para=ret[key];}}
if(tipo!=null&&tipo==1){iniciaEsq_Di(de,para,true);}else if(tipo!=null&&tipo==2){iniciaDir_Es(de,para,false);}}}
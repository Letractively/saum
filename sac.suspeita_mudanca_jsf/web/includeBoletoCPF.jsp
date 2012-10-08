<div class="art-layout-cell art-sidebar1">
                            <div class="art-block">
                                <div class="art-block-tl"></div>
                                <div class="art-block-tr"></div>
                                <div class="art-block-bl"></div>
                                <div class="art-block-br"></div>
                                <div class="art-block-tc"></div>
                                <div class="art-block-bc"></div>
                                <div class="art-block-cl"></div>
                                <div class="art-block-cr"></div>
                                <div class="art-block-cc"></div>
                                <div class="art-block-body">
                                            <div class="art-blockheader">
                                                <div class="l"></div>
                                                <div class="r"></div>
                                                 <div class="t">
							                        <div id="content-msg-logado" style="display: block">
													<span style="font-size: 11px; padding-right: 10px">
														<span id="span-login" style="float: left;">Login</span><span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(0, this);"> [?]</span><span style="display: none;cursor: pointer; text-decoration: underline; color: blue;float: left" id="link-logoff" onclick="javascript: logoff();">Logoff</span>
													</span>
													</div>
                                                 </div>
                                            </div>
                                            <div class="art-blockcontent" id="content-menu">
                                                <div class="art-blockcontent-body">
                                            <!-- block-content -->
													<div style="font-size: 12px; background-color: #FF0000; width: 180px;color:#FFFFFF; font-weight:bolder ;text-align: center; margin-bottom: 5px" id="content-msg-login">&nbsp;</div>
													<label for="cmp-usuario" style="font-size: 10px">E-mail: </label><input value="" type="text" id="cmp-usuario" style="width: 176px" /><BR>
													<label for="cmp-senha" style="font-size: 10px">Senha: </label><input type="password" value="" id="cmp-senha" maxlength="10" style="width: 176px" /><BR>
													<div style="margin-top: 2px; margin-bottom: 3px; width: 170px">
														<div style="float: left;"><input type="checkbox" id="cb-permanecer" style="margin-top: 0px"/></div>
														<div style="float: left"><label for="cb-permanecer" style="margin-top: 2px">&nbsp;&nbsp;Permanecer logado: </label></div>
													</div>
													<br/><br/>
													<button style="margin-left: 0px; width: 50px; font-size: 11px;" onclick="javascript: login();">Logar</button>
													<button style="margin-left: 1px; width: 110px; font-size: 11px;" onclick="javascript: esqueci();">Esqueci a senha</button>
                                            <!-- /block-content -->
                                            
                                            		<div class="cleared"></div>
                                                </div>
                                            </div>
                            		<div class="cleared"></div>
                                </div>
                            </div>
                            <div class="art-block">
                                <div class="art-block-tl"></div>
                                <div class="art-block-tr"></div>
                                <div class="art-block-bl"></div>
                                <div class="art-block-br"></div>
                                <div class="art-block-tc"></div>
                                <div class="art-block-bc"></div>
                                <div class="art-block-cl"></div>
                                <div class="art-block-cr"></div>
                                <div class="art-block-cc"></div>
                                <div class="art-block-body">
                                            <div class="art-blockheader">
                                                <div class="l"></div>
                                                <div class="r"></div>
                                                 <div class="t">2&ordf; via de boleto<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(1, this);"> [?]</span></div>
                                            </div>
                                            <div class="art-blockcontent">
                                                <div class="art-blockcontent-body">
                                            <!-- block-content -->
													<div id="content-boleto" style="margin-bottom: 2px">
														<label for="cpf-boleto" style="font-size: 10px">CPF/CNPJ:</label><br/>
														<input name="cpf-boleto" type="text" id="cpf-boleto" maxlength="18" onkeyup="return formataCPF(this, event);" style="width: 140px" onblur="return formataCPF(this, event);"/>
														<button style="width: 30px; font-size: 10px;" onclick="adiquirirBoletosEmAberto();" id="bt-boletos-rel"> OK </button>
													</div>
                                            <!-- /block-content -->
                                            		<div id="conteudoBoletos" style="text-align: center; font-weight: bold;"></div>
                                            		<div class="cleared"></div>
                                                </div>
                                            </div>
                            		<div class="cleared"></div>
                                </div>
                            </div>
                        </div>
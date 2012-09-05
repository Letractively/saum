<html>
<head>
<title>AIGUILLES - PRD</title>
<LINK REL="stylesheet" HREF="estilo.css" TYPE="text/css">
<script language="javascript" src="basicos.js"></script>
<script language="javascript" src="scriptMascara.js"></script>
<script language="javascript" src="scriptClassificacao.js"></script>
<script language="javascript">
		function query(acao){
			document.form1.action="grava_triagem.php";
			document.form1.acao.value = acao;
			if(acao == 1){
				f = pegaNoh(form1, 'triagem');
				if (verificaForm(f)){
					document.form1.submit();
				}
			}else{
				document.form1.submit();
			}
		}
	</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" alink="#0000FF"
	vlink="#0000FF" link="#0000FF" onLoad="document.form1.caixa.focus(); ">
	<table border='0' cellpadding='0' cellspacing='0'
		style='border-collapse: collapse' bordercolor='#111111' width='950'
		bgcolor='#A5B4B0'>
		<tr>
			<td for='pesqCli' width='20%'><p align='left'>
					<img border='0' src='../i/logo_aiguilles.jpg'></td>
			<td width='20%' align='center'>
				<table border='0' cellpadding='0' cellspacing='0'
					style='border-collapse: collapse' bordercolor='#111111'
					width='100%'>
					<tr>
						<td width='15%'></td>
						<td width='46%'>
							<table border='0' cellpadding='0' cellspacing='0'
								style='border-collapse: collapse' bordercolor='#111111'
								width='90%'>
								<label for='pesq_ficha_cli'>
									<tr>
										<td width='28%' for='pesq_ficha_cli' onmouseover=style.cursor='hand'><a
											id='pesq_ficha_cli' href='menu_pesquisas.php' class='link7'><img
												border='0' src='../i/btn_pesqfclientes.bmp' width='39'
												height='55'></a></td>
										<td width='72%' for='pesq_ficha_cli' class='link7'
											onmouseover=style.cursor='hand'>Pesquisas Diversas</td>
									</tr>
								</label>
							</table>
						</td>
						<td width='2%'></td>
					</tr>
				</table>
			</td>
			<td width='20%' align='center'>
				<!--
			<table border='0' cellpadding='0' cellspacing='0' style='border-collapse: collapse' bordercolor='#111111' width='100%'>
				<tr>
					<td width='15%'></td>
					<td width='46%'>
						<table border='0' cellpadding='0' cellspacing='0' style='border-collapse: collapse' bordercolor='#111111' width='90%'>
							<label for='pesq_os'>
							<tr>
								<td width='28%' for='pesq_os' onmouseover=style.cursor='hand'><a id='pesq_os' href='pesq_os.php' class='link7'><img border='0' src='../i/btn_pesqos.bmp' width='39' height='55'></a></td>
								<td width='72%' for='pesq_os' class='link7' onmouseover=style.cursor='hand'>XXX XXX</td>
      						</tr>
							</label>
						</table>
        			</td>
        			<td width='2%'></td>
				</tr>
    		</table>
		-->
			</td>
			<td width='20%' align='center' valign='top'>
				<table border='0' cellpadding='0' cellspacing='0'
					style='border-collapse: collapse' bordercolor='#111111'
					width='100%'>
					<label for='telaInicial'>
						<tr>
							<td width='65%' class='link7' onmouseover=style.cursor='hand'><p
									align='right'>
									<a id='telaInicial' class='link7' href='../modulos.php'><img
										border='0' src='../i/cantoDiagDirSup.gif' width='20'><img
										border='0' src='../i/btn_telaInicial.bmp' width='44'></a></td>
							<td width='35%' bgcolor='#C2CECC' class='link7'
								onmouseover=style.cursor='hand'><a id='telaInicial'
								class='link7' href='../modulos.php'>Tela Inicial</a></td>
						</tr>
					</label>
				</table>
			</td>
		</tr>
	</table>
	<table border="0" cellspacing="0" cellpadding="0" width="950"
		bgcolor="#BCC8C5" height="25">
		<tr>
			<td width="150"></td>
			<td colspan="4" class="link4"><b>| <a href="index.php"
					class="link4">TRIAGEM - HOME</a> | <a href="triagensPendentes.php"
					class="link4">Triagens em Aberto</a> | Triagem |
			</b></td>
		</tr>
	</table>
	<table border="0" width="950" cellpadding="0" cellspacing="0">
		<tr>
			<td width="150" valign="top" bgcolor="#BCC8C5">
				<table border="0" width="100%" cellpadding="0" cellspacing="0"
					bgcolor="#BCC8C5">
					<tr>
						<td width="5"><img src="../i/spacer.gif" width=5 height=50
							border="0"></td>
						<td class="link4"><a href="#" onclick=javascript:window.open(
							'../alteraDadosUsuario.php','newwin','width=400,height=330,scrollbars=no
							') class='link4'> FRANCISCO CARLOS DA MATA</a><BR>COORD.</td>
					</tr>
				</table>
				<table border="0" cellspacing="0" cellpadding="0" width="150"
					bgcolor="#A5B4B0">
					<tr onmouseover=style.cursor='hand'>
						<td width="150" valign="top">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<!-- Inicio Gera Lote EXT -->
								<tr
									onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'"
									onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<label for="agendamentosPendentes">
										<td width="52" class="link4"><a
											id='agendamentosPendentes' href="agendamentosPendentes.php"><img
												src="../i/btn_buffer1.gif" alt="" width=52 height=60
												border="0" align="absmiddle"></a></td>
										<td class="link4" for="agendamentosPendentes">Agendamentos<br>(Lotes
											Externo)
									</td>
									</label>
								</tr>
								<!-- Fim Gera Lote EXT -->
								<tr
									onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'"
									onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<td height="3" colspan="2" bgcolor="#BECDC9"></td>
								</tr>
								<!-- Inicio Gera Lote INT -->
								<tr
									onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'"
									onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<label for="menu_lotesInternos">
										<td width="52" class="link4"><a id='menu_lotesInternos'
											href="menu_lotesInternos.php"><img
												src="../i/btn_buffer.gif" alt="" width=52 height=60
												border="0" align="absmiddle"></a></td>
										<td class="link4" for="menu_lotesInternos">N.F. Devolução<BR>(Lotes
											Interno)
									</td>
									</label>
								</tr>
								<!-- Fim Gera Lote INT -->
								<tr
									onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'"
									onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<td height="3" colspan="2" bgcolor="#BECDC9"></td>
								</tr>
								<!-- Inicio Triagem -->
								<tr
									onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'"
									onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<label for="triagensPendentes">
										<td width="52" class="link4"><a id='triagensPendentes'
											href="triagensPendentes.php"><img
												src="../i/btn_buscaAT.gif" alt="" width=52 height=60
												border="0" align="absmiddle"></a></td>
										<td class="link4" for="atendReceptivo">Triagens<br>em
											Aberto
									</td>
									</label>
								</tr>
								<!-- Fim Triagem -->
								<tr
									onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'"
									onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<td height="3" colspan="2" bgcolor="#BECDC9"></td>
								</tr>
								<!-- Inicio GRADES alterados -->
								<tr
									onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'"
									onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<label for="gradesAlterados">
										<td width="52" class="link4"><a id='gradesAlterados'
											href="gradesAlterados.php"><img
												src="../i/btn_vaivolta.gif" alt="" width=52 height=60
												border="0" align="absmiddle"></a></td>
										<td class="link4" for="gradesAlterados">GRADES Alterados<br>(Alterar
											Físico)
									</td>
									</label>
								</tr>
								<!-- Fim GRADES alterados -->
								<tr
									onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'"
									onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<td height="3" colspan="2" bgcolor="#BECDC9"></td>
								</tr>
								<!-- Inicio APROVAÇAO -->
								<tr
									onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'"
									onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<label for="pendAprovaLote">
										<td width="52" class="link4"><a id='pendAprovaLote'
											href="pendAprovaLote.php"><img src="../i/btn_nfEntr.gif"
												alt="" width=52 height=60 border="0" align="absmiddle"></a></td>
										<td class="link4" for="pendAprovaLote">Confirmação de
											Lotes</td>
									</label>
								</tr>
								<!-- Fim APROVAÇAO -->
								<tr
									onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'"
									onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<td height="3" colspan="2" bgcolor="#BECDC9"></td>
								</tr>
								<!-- Inicio GRAFICOS -->
								<tr
									onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'"
									onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<label for="graficos_triagem">
										<td width="52" class="link4"><a id='graficos_triagem'
											href="graficos_triagem.php"><img
												src="../i/btn_graficos.gif" width=52 height=60 border="0"
												align="absmiddle"></a></td>
										<td class="link4" for="graficos_triagem">Gráficos</td>
									</label>
								</tr>
								<!-- Fim GRAFICOS -->
								<tr
									onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'"
									onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<td height="3" colspan="2" bgcolor="#BECDC9"></td>
								</tr>
								<!-- Inicio RELATORIOS -->
								<tr
									onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'"
									onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<label for="relatorios_triagem">
										<td width="52" class="link4"><a id='relatorios_triagem'
											href="relatorios_triagem.php"><img
												src="../i/btn_relatinf.gif" width=52 height=60 border="0"
												align="absmiddle"></a></td>
										<td class="link4" for="relatorios_at">Relat&oacute;rios
											Gerenciais</td>
									</label>
								</tr>
								<!-- Fim RELATORIOS -->
								<tr
									onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'"
									onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<td height="3" colspan="2" bgcolor="#BECDC9"></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
			<td width="950" valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top"><img src="../i/juncao1.gif" width="15"
							height="15" border="0"></td>
					</tr>
					<tr>
						<td style="padding-left: 15px;"><b class="link3"><img
								src="../i/seta1.gif" width="15" height="15" border="0"
								align="absmiddle" hspace="0" vspace="5">TRIAGEM</b><br></td>
					<tr>
						<td style="padding-left: 15px;">
							<form name='form1' id='triagem' method='POST'>
								<table border="1" cellpadding="2" width="785"
									bordercolorlight="#C0C0C0" bordercolordark="#F0F0F0">
									<tr>
										<td class='subTituloFicha' colspan='10' align='center'>Dados
											do Modelo</td>
									</tr>
									<tr align="left">
										<td width="60" class="labelFicha">Cliente:</td>
										<td width="110" class="link1">SAMSUNG&nbsp;</td>
										<td width="70" class="labelFicha">Warehouse:</td>
										<td colspan="3" class="link1">FAST SHOP DF -
											SAMSUNG&nbsp;</td>
										<td width="55" class="labelFicha">Rede:</td>
										<td width="153" class="link1">FAST SHOP&nbsp;</td>
									</tr>
									<tr align="left">
										<td class="labelFicha">Marca:</td>
										<td class="link1">SAMSUNG&nbsp;</td>
										<td class="labelFicha">Familia:</td>
										<td width="130" class="link1">TC&nbsp;</td>
										<td width="40" class="labelFicha">Linha:</td>
										<td width="100" class="link1">HHP&nbsp;</td>
										<td class="labelFicha">Regional:</td>
										<td class="link1">SAMSUNG - SP&nbsp;</td>
									</tr>
									<tr align="left">
										<td class="labelFicha">Código:</td>
										<td class="link1">GT-I9000B&nbsp;</td>
										<td class="labelFicha">Modelo:</td>
										<td colspan="3" class="link1">GT-I9000B&nbsp;</td>
										<td class="labelFicha">Qtd.:</td>
										<td class="link1">1/1&nbsp;</td>
									</tr>
									<tr>
										<td colspan='10' align='center'
											style='background-color: #00FF00; font-family: Arial, Helvetica, sans-serif; font-size: 14px;'
											height='30'><b>DOA - LIKE NEW</b></td>
									</tr>
								</table>

								<table border="1" cellpadding="2" width="785"
									bordercolorlight="#C0C0C0" bordercolordark="#F0F0F0">
									<tr>
										<td class='subTituloFicha' colspan='4' align='center'>Triagem</td>
									</tr>
									<tr align='left'>
										<td width='187' class='labelFicha'>SSN:</td>
										<td width='187' class='link1' colspan="3">RSEZ837310B</td>
									</tr>
									<tr align='left'>
										<td width='187' class='labelFicha'>Consta Autorizaçao de
											CCC:</td>
										<td width='187' class='link1'>Sim</td>
										<td width='187' class='labelFicha'>Caixa:</td>
										<td width='188' class='link1'><input type='text'
											name='caixa' id='caixa' style='width: 185px;'
											onKeyPress='if ( (event.keyCode < 48) || (event.keyCode > 57) ) event.returnValue = false;'
											class='textbox' required='yes' /></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>IMEI</td>
										<td class='link1'>353940040038861&nbsp;</td>
										<td class='labelFicha'>COR</td>
										<td class='link1'>PRETO&nbsp;</td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>POSSUI CHECK LIST?</td>
										<td class='link1'>Sim&nbsp;</td>
										<td class='labelFicha'>OPERADORA</td>
										<td class='link1'>OPEN&nbsp;</td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>CÓD MODELO CLIENTE</td>
										<td class='link1'>&nbsp;</td>
										<td class='labelFicha'>TEMPO DE USO (HHH:MM:SS)</td>
										<td class='link1'>100:00:00&nbsp;</td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>NR. NOTA FISCAL (VENDA)</td>
										<td class='link1'>&nbsp;</td>
										<td class='labelFicha'>DATA NF (VENDA)</td>
										<td class='link1'>&nbsp;</td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>NR. NOTA FISCAL (TROCA)</td>
										<td class='link1'>&nbsp;</td>
										<td class='labelFicha'>DATA NF (TROCA)</td>
										<td class='link1'>&nbsp;</td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>ASC</td>
										<td class='link1'>ABAD INFO CELL&nbsp;</td>
										<td class='labelFicha'>ACESSÓRIO FALTANTE</td>
										<td class='link1'>KIT COMPLETO OK&nbsp;</td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>SINTOMA INFORMADO</td>
										<td class='link1'>SEM SERVIÇO&nbsp;</td>
										<td class='labelFicha'>SINTOMA CONSTATADO</td>
										<td class='link1'>TD1 (sem audio)&nbsp;</td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>APARÊNCIA</td>
										<td class='link1'>BOM (DOA)&nbsp;</td>
										<td class='labelFicha'>APARELHO ESTÁ BLOQUEADO?</td>
										<td class='link1'>Não&nbsp;</td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>NR. ORDEM SERVIÇO 1</td>
										<td class='link1'>&nbsp;</td>
										<td class='labelFicha'>CÓD PEÇA DA O.S. 1</td>
										<td class='link1'>&nbsp;</td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>DATA ABERTURA O.S. 1</td>
										<td class='link1'>&nbsp;</td>
										<td class='labelFicha'>DATA ENTREGA O.S. 1</td>
										<td class='link1'>&nbsp;</td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>NR. ORDEM SERVIÇO 2</td>
										<td class='link1'>&nbsp;</td>
										<td class='labelFicha'>CÓD PEÇA DA O.S. 2</td>
										<td class='link1'>&nbsp;</td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>DATA ABERTURA O.S. 2</td>
										<td class='link1'>&nbsp;</td>
										<td class='labelFicha'>DATA ENTREGA O.S. 2</td>
										<td class='link1'>&nbsp;</td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>NR. ORDEM SERVIÇO 3</td>
										<td class='link1'>&nbsp;</td>
										<td class='labelFicha'>CÓD PEÇA O.S. 3</td>
										<td class='link1'>&nbsp;</td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>DATA ABERTURA O.S. 3</td>
										<td class='link1'>&nbsp;</td>
										<td class='labelFicha'>DATA ENTREGA O.S. 3</td>
										<td class='link1'>&nbsp;</td>
									</tr>
									<tr align='left'></tr>
									<tr align='left'>
										<td width='187' class='labelFicha' valign='top'>Observações:</td>
										<td colspan="3" class='link1'>&nbsp;</td>
									</tr>
									<tr>
										<td bgcolor="#F0F0F0" height="35" align="center" colspan="4"
											style="padding-top: 0px; padding-bottom: 0px;" id="teste">
											<input type='button' name='button_2' class='button1'
											style='width: 150px;' value='Gravar Triagem'
											onClick='query(1);'> <input type="button"
											name="button_3" class="button1" style='width: 150px;'
											value="Desfazer Triagem" onClick='query(2);'>
										</td>
									</tr>
								</table>
								<input type="hidden" name="lote_id" value="16905"> <input
									type="hidden" name="modelo_id" value="12369"> <input
									type="hidden" name="triagem" value="1478558"> <input
									type="hidden" name="doa" value="1"> <input
									type="hidden" name="motivo" value="0"> <input
									type="hidden" name="msg"
									value="Você deve preencher os campos indicados em vermelho.">
								<input type="hidden" name="acao" value="3">
							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>

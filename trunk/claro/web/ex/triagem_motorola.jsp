<html>
<head>
<title>AIGUILLES - PRD</title>
<LINK REL="stylesheet" HREF="../include/estilo.css" TYPE="text/css">
<script language="javascript" src="../include/basicos.js"></script>
<script language="javascript" src="include/scriptMascara.js"></script>
<!-- Auto complete -->
<script type="text/javascript" src="../include/jquery.js"></script>
<script type="text/javascript" src="../include/jquery.autocomplete.js"></script>
<link rel="stylesheet" href="../include/jquery.autocomplete.css" type="text/css" />

<script language="javascript">
		var serial = new Array() ;
		var imei = new Array() ;
		var asc = new Array() ;
		var incompleto = new Array();
		var apc = new Array();
	asc[0]='9128620000185';
asc[1]='5533325000153';
asc[2]='1136374000100';
asc[3]='9271503000176';
asc[4]='8844649000109';
asc[5]='9150419000102';
asc[6]='4220101000129';
asc[7]='8331060000107';
asc[8]='9258815000140';
asc[9]='1206815000275';
asc[10]='70178363000105';
asc[11]='10555142000179';
asc[12]='3207912000127';
asc[13]='8726606000110';
asc[14]='3913612000163';
asc[15]='10904464000186';
asc[16]='7308407000120';
asc[17]='3307999000104';
asc[18]='6228469000169';
asc[19]='69888303000199';
asc[20]='69931400000117';
asc[21]='3574622000111';
asc[22]='70021027000146';
asc[23]='6224460000180';
asc[24]='6187198000140';
asc[25]='40795742000175';
asc[26]='4972456000174';
asc[27]='2503919000123';
asc[28]='1845466000150';
asc[29]='42183442000160';
asc[30]='5627263000149';
asc[31]='4637599000120';
asc[32]='2623638000104';
asc[33]='6336678000126';
asc[34]='30818520000177';
asc[35]='31644271000103';
asc[36]='27595834000143';
asc[37]='7348313000184';
asc[38]='6859264000181';
asc[39]='4947250000194';
asc[40]='35935535000139';
asc[41]='1886620000132';
asc[42]='3735506000137';
asc[43]='920681000106';
asc[44]='3849581000129';
asc[45]='7437991000113';
asc[46]='3865781000175';
asc[47]='2092675000133';
asc[48]='1384634000158';
asc[49]='3892269000118';
asc[50]='29910502000131';
asc[51]='1591208000195';
asc[52]='39245022000157';
asc[53]='27882802000129';
asc[54]='8047008000115';
asc[55]='5100589000113';
asc[56]='9077261000184';
asc[57]='4965579000340';
asc[58]='3921243000150';
asc[59]='36524247000154';
asc[60]='31153158000125';
asc[61]='29383007000111';
asc[62]='27839695000156';
asc[63]='34104240000158';
asc[64]='10238332000162';
asc[65]='8668160000115';
asc[66]='34107847000191';
asc[67]='4769215000122';
asc[68]='7830235000150';
asc[69]='7052085000109';
asc[70]='9498965000120';
asc[71]='32921728000142';
asc[72]='5671132000169';
asc[73]='2737563000192';
asc[74]='2749306000170';
asc[75]='2489087000138';
asc[76]='7106906000134';
asc[77]='9416832000168';
asc[78]='9498965000201';
asc[79]='4443112000178';
asc[80]='30007504000102';
asc[81]='4755011000132';
asc[82]='4362200000145';
asc[83]='8221018000125';
asc[84]='7563999000126';
asc[85]='3770053000260';
asc[86]='27046168000194';
asc[87]='7420046000109';
asc[88]='29461704000143';
asc[89]='8026250000102';
asc[90]='27929926000112';
asc[91]='30699508000190';
asc[92]='28583656000101';
asc[93]='31181563000157';
asc[94]='28194652000123';
asc[95]='31587819000120';
asc[96]='6789449000167';
asc[97]='933263000315';
asc[98]='37118890000140';
asc[99]='39309398000188';
asc[100]='5004557000114';
asc[101]='5842191000152';
asc[102]='27725779000240';
asc[103]='4240824000190';
asc[104]='36318236000118';
asc[105]='1776659000105';
asc[106]='5325834000190';
asc[107]='39306675000107';
asc[108]='32501678000144';
asc[109]='854942000137';
asc[110]='37623782000125';
asc[111]='4179340000182';
asc[112]='4275538000160';
asc[113]='7742023000110';
asc[114]='9239487000134';
asc[115]='262923000111';
asc[116]='1015760000216';
asc[117]='9305985000138';
asc[118]='28194652000980';
asc[119]='2643468000120';
asc[120]='5306706000108';
asc[121]='6305109000113';
asc[122]='6239775000109';
asc[123]='57913725000198';
asc[124]='67904201000194';
asc[125]='3904528000183';
asc[126]='5978107000122';
asc[127]='1801056000108';
asc[128]='10246790000143';
asc[129]='65478877000138';
asc[130]='55183883000104';
asc[131]='4934662000190';
asc[132]='52408911000156';
asc[133]='53140943000186';
asc[134]='5736940000167';
asc[135]='4294573000126';
asc[136]='1626115000159';
asc[137]='68077510000109';
asc[138]='53575742000101';
asc[139]='50050004000152';
asc[140]='47602370000162';
asc[141]='1879445000156';
asc[142]='1403387000190';
asc[143]='409563000138';
asc[144]='1493098000129';
asc[145]='50784636000140';
asc[146]='53770186000124';
asc[147]='7742120000103';
asc[148]='1157865000129';
asc[149]='2011812000168';
asc[150]='3640479000119';
asc[151]='50401645000104';
asc[152]='62226808000149';
asc[153]='1711749000100';
asc[154]='61016119000147';
asc[155]='44317204000126';
asc[156]='1004412000162';
asc[157]='48001937000108';
asc[158]='46748554000172';
asc[159]='1798765000181';
asc[160]='3003067000178';
asc[161]='71710263000132';
asc[162]='74480906000197';
asc[163]='47545405000179';
asc[164]='1515719000128';
asc[165]='7841006000130';
asc[166]='3488619000185';
asc[167]='3984060000346';
asc[168]='3984060000184';
asc[169]='5833565000173';
asc[170]='50314756000183';
asc[171]='382941000137';
asc[172]='8116629000103';
asc[173]='3722628000199';
asc[174]='2003444000106';
asc[175]='58812231000180';
asc[176]='3910901000109';
asc[177]='4844410000170';
asc[178]='4023725000156';
asc[179]='9223846000165';
asc[180]='58430802000111';
asc[181]='51951895000180';
asc[182]='5142422000115';
asc[183]='5296498000103';
asc[184]='939175000169';
asc[185]='8027440000144';
asc[186]='1427565000112';
asc[187]='74450578000500';
asc[188]='7513965000127';
asc[189]='61961884000135';
asc[190]='71530810000106';
asc[191]='74317363000191';
asc[192]='49591787000100';
asc[193]='48815690000163';
asc[194]='67254664000158';
asc[195]='62520929000107';
asc[196]='57252892000135';
asc[197]='2597372000172';
asc[198]='58264409000103';
asc[199]='51052652000100';
asc[200]='59095505000120';
asc[201]='5251994000132';
asc[202]='765140000150';
asc[203]='7050838000139';
asc[204]='56252083000160';
asc[205]='8347249000180';
asc[206]='5457156000110';
asc[207]='1602491000103';
asc[208]='3389002000102';
asc[209]='66590647000129';
asc[210]='2066289000177';
asc[211]='55469613000164';
asc[212]='57303562000121';
asc[213]='4399865000123';
asc[214]='4094721000169';
asc[215]='6045713000158';
asc[216]='6865032000136';
asc[217]='4990740000173';
asc[218]='82416793000199';
asc[219]='397494000190';
asc[220]='483905000160';
asc[221]='92669613000134';
asc[222]='75529545000199';
asc[223]='8586610000120';
asc[224]='59433979000134';
asc[225]='7785861000171';
asc[226]='9179253000149';
asc[227]='64054778000166';
asc[228]='62894993000140';
asc[229]='51940559000132';
asc[230]='2907438000264';
asc[231]='8096692000125';
asc[232]='4691136000146';
asc[233]='48780464000194';
asc[234]='5209620000159';
asc[235]='382181000168';
asc[236]='3807589000122';
asc[237]='7696937000192';
asc[238]='1103634000132';
asc[239]='52307923000194';
asc[240]='60819166000166';
asc[241]='60391521000149';
asc[242]='57988867000114';
asc[243]='35928779000275';
asc[244]='5757716000151';
asc[245]='67992347000139';
asc[246]='4671853000106';
asc[247]='22552921000108';
asc[248]='1057655000169';
asc[249]='2638882000140';
asc[250]='6033653000153';
asc[251]='19940840000162';
asc[252]='21306816000118';
asc[253]='7776366000104';
asc[254]='5148110000119';
asc[255]='16541336000100';
asc[256]='42896860000103';
asc[257]='5899517000188';
asc[258]='6288983000190';
asc[259]='19570803000100';
asc[260]='65215048000162';
asc[261]='25805557000120';
asc[262]='1691895000110';
asc[263]='8530650000150';
asc[264]='26339234000151';
asc[265]='25313602000120';
asc[266]='25673773000160';
asc[267]='4178267000124';
asc[268]='5629581000149';
asc[269]='26397166000187';
asc[270]='7643453000185';
asc[271]='5398869000150';
asc[272]='5505189000198';
asc[273]='28194652001367';
asc[274]='28194652002924';
asc[275]='28194652003220';
asc[276]='7194922000126';
asc[277]='7865836000106';
asc[278]='20891628000131';
asc[279]='21524996000104';
asc[280]='3713262000191';
asc[281]='2969072000177';
asc[282]='71507370000168';
asc[283]='1316095000110';
asc[284]='7225772000170';
asc[285]='4515097000126';
asc[286]='9437544000190';
asc[287]='4758766000190';
asc[288]='28194652002096';
asc[289]='74007543000177';
asc[290]='8369286000199';
asc[291]='3979561000172';
asc[292]='32951535000134';
asc[293]='2467329000192';
asc[294]='941393000138';
asc[295]='8172408000152';
asc[296]='4614644000201';
asc[297]='15071103000110';
asc[298]='28194652001286';
asc[299]='3147899000168';
asc[300]='1730829000102';
asc[301]='84838846000195';
asc[302]='3067265000103';
asc[303]='3087130000100';
asc[304]='8889036000180';
asc[305]='72042856000130';
asc[306]='397494000190';
asc[307]='138572000131';
asc[308]='79428355000136';
asc[309]='78002128000262';
asc[310]='77705309000101';
asc[311]='7204153000108';
asc[312]='230360000180';
asc[313]='82337585000102';
asc[314]='3617259000174';
asc[315]='4078642000164';
asc[316]='81430126000289';
asc[317]='5331549000182';
asc[318]='81430126000106';
asc[319]='7130111000161';
asc[320]='81122905000136';
asc[321]='10139891000115';
asc[322]='7663147000100';
asc[323]='9513107000108';
asc[324]='73367617000113';
asc[325]='7573828000188';
asc[326]='93070894000177';
asc[327]='90411505000131';
asc[328]='2844559000123';
asc[329]='88825641000398';
asc[330]='7519670000168';
asc[331]='88243449000121';
asc[332]='88960240000189';
asc[333]='90954843000110';
asc[334]='89431209000113';
asc[335]='5412790000136';
asc[336]='90282963000118';
asc[337]='93064509000189';
asc[338]='90149196000173';
asc[339]='90037003000192';
asc[340]='87057568000127';
asc[341]='2082463000175';
asc[342]='87288726000150';
asc[343]='8476291000281';
asc[344]='9058054000182';
asc[345]='5786379000120';
asc[346]='95024600000104';
asc[347]='3865479000117';
asc[348]='90461187000113';
asc[349]='91229039000130';
asc[350]='73917452000106';
asc[351]='138139000104';
asc[352]='89786149000151';
asc[353]='453538000151';
asc[354]='3458787000128';
asc[355]='87649182000104';
asc[356]='602675000100';
asc[357]='75489039000113';
asc[358]='83625590000175';
asc[359]='81863391000170';
asc[360]='909409000125';
asc[361]='79817292000100';
asc[362]='7079959000103';
asc[363]='82942988000172';
asc[364]='5338331000150';
asc[365]='85240869000166';
asc[366]='6555141000157';
asc[367]='3658584000185';
asc[368]='3658584000347';
asc[369]='4447492000208';
asc[370]='85305639000138';
asc[371]='82785585000167';
asc[372]='397494000190';
asc[373]='82965609000240';
asc[374]='3687982000120';
asc[375]='3658584000690';
asc[376]='3658584000266';
asc[377]='8586610000201';
asc[378]='1780919000108';
asc[379]='78631603000189';
asc[380]='73528895000105';
asc[381]='8144338000129';
asc[382]='7673491000180';
asc[383]='4851779000100';
asc[384]='7901750000183';
asc[385]='8431009000169';
asc[386]='9002803000150';
asc[387]='28194652001952';
asc[388]='634125000173';
asc[389]='3443401000104';
asc[390]='3628734000108';
asc[391]='28194652001529';
asc[392]='7981134000180';
asc[393]='84421080000149';
asc[394]='3901023000165';
asc[395]='5863659000195';
asc[396]='6161413000134';
asc[397]='40540304000166';
asc[398]='4769215000203';
asc[399]='40566085000194';
asc[400]='13201751000118';
asc[401]='34375097000139';
asc[402]='7238552000181';
asc[403]='7268440000254';
asc[404]='856078000102';
asc[405]='590445000250';
asc[406]='13521513000190';
asc[407]='15652621000127';
asc[408]='3421888000205';
asc[409]='5960114000105';
asc[410]='14880132000160';
asc[411]='2352386000126';
asc[412]='34261008000123';
asc[413]='9247384000116';
asc[414]='7473057000157';
asc[415]='3207606000190';
asc[416]='40564270000140';
asc[417]='2759644000193';
asc[418]='6201744000150';
asc[419]='3993093000190';
asc[420]='6178984000181';
asc[421]='8957930000140';
asc[422]='5265664000287';
asc[423]='10353027000111';
asc[424]='8799605000104';
asc[425]='3155743000129';
asc[426]='2161228000199';
asc[427]='12564282000139';
asc[428]='7072680000106';
asc[429]='23679947002462';
asc[430]='2547502000162';
asc[431]='10446656000196';
asc[432]='69556215000190';
asc[433]='1824107000117';
asc[434]='28194652003572';
asc[435]='6229980000185';
asc[436]='3327559000119';
asc[437]='2445723000120';
asc[438]='6341355000120';
asc[439]='34896258000130';
asc[440]='83385195000162';
asc[441]='769962000100';
asc[442]='4049497013686';
asc[443]='28194652001600';
asc[444]='5137667000235';
asc[445]='4680405000179';
asc[446]='4129397000177';
asc[447]='15257900000196';
asc[448]='6862627000138';
asc[449]='6871719000184';
asc[450]='23638729000193';
asc[451]='28194652003653';
asc[452]='6375100000189';
asc[453]='73644858000162';
asc[454]='84634336000104';
asc[455]='864545000146';
asc[456]='3701678000190';
asc[457]='28194652003300';
asc[458]='4851779000290';
asc[459]='8955628000152';
asc[460]='7193405000132';
asc[461]='3480546000185';
asc[462]='7199427000100';
asc[463]='13358254000128';
asc[464]='28194652001790';
asc[465]='9493439000178';
asc[466]='3814894000141';
asc[467]='61.099.834/0189-95';
asc[468]='00.090.848/0001-59';
asc[469]='12.669.124/0001-43';
asc[470]='04.755.011/0001-32';
asc[471]='04.755.011/0001-32';
asc[472]='04.755.011/0001-32';
asc[473]='04.755.011/0001-32';
asc[474]='04.755.011/0001-32';
asc[475]='04.755.011/0001-32';
asc[476]='04.755.011/0001-32';
asc[477]='04.755.011/0001-32';
asc[478]='04.755.011/0001-32';
asc[479]='04.755.011/0001-32';
asc[480]='350.445.276-53';
apc[13430]='M35';
incompleto[0]='75';
incompleto[1]='150';
incompleto[2]='102';
incompleto[3]='174';
incompleto[4]='182';
incompleto[5]='82';
incompleto[6]='200';
incompleto[7]='196';
incompleto[8]='74';
incompleto[9]='90';
incompleto[10]='183';
incompleto[11]='137';
incompleto[12]='177';
incompleto[13]='175';
incompleto[14]='79';
incompleto[15]='172';
incompleto[16]='88';
incompleto[17]='76';
incompleto[18]='85';
incompleto[19]='142';
incompleto[20]='187';
incompleto[21]='107';
incompleto[22]='151';
incompleto[23]='80';
incompleto[24]='104';
incompleto[25]='105';
incompleto[26]='106';
incompleto[27]='103';
incompleto[28]='128';
incompleto[29]='81';
incompleto[30]='153';
incompleto[31]='139';
incompleto[32]='146';
incompleto[33]='84';
incompleto[34]='189';
incompleto[35]='86';
incompleto[36]='140';
incompleto[37]='89';
incompleto[38]='185';
incompleto[39]='173';
incompleto[40]='149';
incompleto[41]='176';
incompleto[42]='87';
incompleto[43]='78';
incompleto[44]='145';
incompleto[45]='144';
incompleto[46]='152';
incompleto[47]='77';
incompleto[48]='188';
incompleto[49]='141';
incompleto[50]='138';
incompleto[51]='184';
incompleto[52]='178';
incompleto[53]='73';
incompleto[54]='143';
incompleto[55]='83';
incompleto[56]='92';
incompleto[57]='186';
incompleto[58]='136';
incompleto[59]='91';
 
		function serialLote(sn){
			var x = 0;
			for( i = 0; i < serial.length; i++ ) {
				if(serial[i] == sn){
					x = 1;
				}
			}
			return x;
		}
 
		function verASC(sn){
			var x = 0;
			for( i = 0; i < asc.length; i++ ) {
				if(asc[i] == sn.value){
					x = 1;
				}
			}
			if (x==0){
				alert('ASC não localizada!!');
				sn.focus();
			}
		}
		
		function verIncompleto(){
			if(parseInt(document.getElementById('faltante').value) > 0){
				var x = 0;
				for( i = 0; i < incompleto.length; i++ ) {
					if(document.getElementById('resposta['+incompleto[i]+']').checked){
						x++;
					}
				}
				if (x==0)
					return true;
				else
					return false;
			}
			else{
				return false;
			}
		}
 
		
		function verifica_incompleto(){
			f = pegaNoh(form1, 'triagem');
			for (i = (f.length - 1); i >= 0; i--){
				var tempobj = f.elements[i];
				if (tempobj.getAttribute('name').substr(0,13) == 'resposta_kit['){
					if(tempobj.getAttribute('checked')){
						document.getElementById('resposta['+tempobj.getAttribute('value')+']').disabled = false;
					}
					else{
						document.getElementById('resposta['+tempobj.getAttribute('value')+']').disabled = true;
						document.getElementById('resposta['+tempobj.getAttribute('value')+']').checked = false;
					}
				}
			}
		}
	
		function query(){
			document.form1.action="cont_triagem.php";
			f = pegaNoh(form1, 'triagem');
			var mensagem = '';
			var	res23 = 0;
			var	res24 = 0;
			var	res25 = 0;
			var	res26 = 0;
			var	res29 = 0;
			var	res30 = 0;
			var	res31 = 0;
			var	res32 = 0;
			var	res42 = 0;
			var	res43 = 0;
			var	res44 = 0;
			var	res45 = 0;
			var	res46 = 0;
			var	res47 = 0;
			var	res48 = 0;
			var	res49 = 0;
 
			if((verIncompleto()) && (parseInt(document.getElementById('faltante').value) > 0)){
				alert('Verificar itens faltantes!');
			}
			else{
				for (i = (f.length - 1); i >= 0; i--){
					var tempobj=f.elements[i];
					
					if (tempobj.getAttribute('name') == 'resposta[23]')
						res23 = tempobj.getAttribute('value');
					if (tempobj.getAttribute('name') == 'resposta[24]')
						res24 = tempobj.getAttribute('value');
					if (tempobj.getAttribute('name') == 'resposta[25]')
						res25 = tempobj.getAttribute('value');
					if (tempobj.getAttribute('name') == 'resposta[26]')
						res26 = tempobj.getAttribute('value');
						
					if (tempobj.getAttribute('name') == 'resposta[29]')
						res29 = tempobj.getAttribute('value');
					if (tempobj.getAttribute('name') == 'resposta[30]')
						res30 = tempobj.getAttribute('value');
					if (tempobj.getAttribute('name') == 'resposta[31]')
						res31 = tempobj.getAttribute('value');
					if (tempobj.getAttribute('name') == 'resposta[32]')
						res32 = tempobj.getAttribute('value');
						
					if (tempobj.getAttribute('name') == 'resposta[42]')
						res42 = tempobj.getAttribute('value');
					if (tempobj.getAttribute('name') == 'resposta[43]')
						res43 = tempobj.getAttribute('value');
					if (tempobj.getAttribute('name') == 'resposta[44]')
						res44 = tempobj.getAttribute('value');
					if (tempobj.getAttribute('name') == 'resposta[45]')
						res45 = tempobj.getAttribute('value');
						
					if (tempobj.getAttribute('name') == 'resposta[46]')
						res46 = tempobj.getAttribute('value');
					if (tempobj.getAttribute('name') == 'resposta[47]')
						res47 = tempobj.getAttribute('value');
					if (tempobj.getAttribute('name') == 'resposta[48]')
						res48 = tempobj.getAttribute('value');
					if (tempobj.getAttribute('name') == 'resposta[49]')
						res49 = tempobj.getAttribute('value');
				}
 
				// Dados das Notas Fiscais
				if (((res23) || (res24) || (res25) || (res26)) && !((res23) && (res24) && (res25) && (res26))){
					mensagem += "Verificar dados das Notas Fiscais!";
				}
				else{
					if ((res24 != '') && (res26 !='')){
						var dataV = parseInt(res24.split("/")[2].toString() + res24.split( "/" )[1].toString() + res24.split( "/" )[0].toString());
						var dataT = parseInt(res26.split("/")[2].toString() + res26.split( "/" )[1].toString() + res26.split( "/" )[0].toString());
 
						if(dataV > dataT)
							mensagem += "A data NF de troca tem que ser maior que a NF de venda!" ;
					}
				}
 
				//OS 1
				if (((res29) || (res30) || (res31) || (res32)) && !((res29) && (res30) && (res31) && (res32))){
					if (mensagem.length > 0)
						mensagem += "\n";
					mensagem += "ERRO OS1 \nDados da Ordem de Serviço 1 incompletos! \n";
				}
				else{
					data1 = res30;
					data2 = res31;
					if ((res30 != '') && (res31 !='')){
						if ( parseInt( data2.split( "/" )[2].toString() + data2.split( "/" )[1].toString() + data2.split( "/" )[0].toString() ) < parseInt( data1.split( "/" )[2].toString() + data1.split( "/" )[1].toString() + data1.split( "/" )[0].toString())){
							if (mensagem.length > 0)
								mensagem += "\n";
							mensagem += "ERRO OS1  \nData de abertura da OS1 não pode ser maior que data de entrega da OS1.\n";
						}
					}
				}
 
				//OS 2
				if (((res42) || (res43) || (res44) || (res45)) && !((res42) && (res43) && (res44) && (res45))){
					if (mensagem.length > 0)
						mensagem += "\n";
					mensagem += "ERRO OS2 \nDados da Ordem de Serviço 2 incompletos!\n ";
						
				}else{
					data1 = res44;
					data2 = res45;
					
					if ((res44 != '') && (res45 !='')){
						if ( parseInt( data2.split( "/" )[2].toString() + data2.split( "/" )[1].toString() + data2.split( "/" )[0].toString() ) < parseInt( data1.split( "/" )[2].toString() + data1.split( "/" )[1].toString() + data1.split( "/" )[0].toString())){
							if (mensagem.length > 0)
								mensagem += "\n";
							mensagem += "ERRO OS2 \nData de abertura da OS2 não pode ser maior que data de entrega da OS2.\n ";
						}
					}
				}
						
				//OS 3
				if (((res46) || (res47) || (res48) || (res49)) && !((res46) && (res47) && (res48) && (res49))){
					if (mensagem.length > 0)
						mensagem += "\n";
					mensagem += "ERRO OS3 \nDados da Ordem de Serviço 3 incompletos!\n ";
				}
				else{
					data1 = res48;
					data2 = res49;
					if ((res48 != '') && (res49 !='')){
						if ( parseInt( data2.split( "/" )[2].toString() + data2.split( "/" )[1].toString() + data2.split( "/" )[0].toString() ) < parseInt( data1.split( "/" )[2].toString() + data1.split( "/" )[1].toString() + data1.split( "/" )[0].toString())){
							if (mensagem.length > 0)
								mensagem += "\n";
							mensagem += "ERRO OS3 \nData de abertura da OS3 não pode ser maior que data de entrega OS3.\n ";
						}
					}
				}
					
				//Varifica datas 
				var data1 = res30;
				var data2 = res44;
				if ((res30 != '') && (res44 !='')){
					if ( parseInt( data2.split( "/" )[2].toString() + data2.split( "/" )[1].toString() + data2.split( "/" )[0].toString() ) <	parseInt( data1.split( "/" )[2].toString() + data1.split( "/" )[1].toString() + data1.split( "/" )[0].toString())){
						if (mensagem.length > 0)
							mensagem += "\n";
						mensagem += "ERRO OS2 \nData de abertura da OS2 não pode ser maior que data de entrega da OS1.\n" ;
					}
				}
 
				var data1 = res30;
				var data2 = res48;
				if ((res30 != '') && (res48 !='')){
					if ( parseInt( data2.split( "/" )[2].toString() + data2.split( "/" )[1].toString() + data2.split( "/" )[0].toString() ) <	parseInt( data1.split( "/" )[2].toString() + data1.split( "/" )[1].toString() + data1.split( "/" )[0].toString() ) ){
						if (mensagem.length > 0)
							mensagem += "\n";
						mensagem += "ERRO OS3 \nData de abertura da OS3 não pode ser maior que data de entrega da OS1.\n " ;
					}
				}
 
				var data1 = res44;
				var data2 = res48;
				if ((res44 != '') && (res48 !='')){
					if ( parseInt( data2.split( "/" )[2].toString() + data2.split( "/" )[1].toString() + data2.split( "/" )[0].toString() ) <	parseInt( data1.split( "/" )[2].toString() + data1.split( "/" )[1].toString() + data1.split( "/" )[0].toString() ) ){
						if (mensagem.length > 0)
							mensagem += "\n";
						mensagem += "ERRO OS3 \nData de abertura da OS3 não pode ser maior que data de entrega da OS2.\n " ;
					}
				}
 
				if (mensagem.length > 0)
					alert(mensagem);
				else if (verificaForm(f)){
					document.form1.submit();
				}
			}
		}
		function valida_kit(valor)
		{
			if(valor == 1){
				document.getElementById('itens_faltantes').style.display = 'none';
				document.getElementById('faltante').value = 0;
			}
			else if(valor == 0){
				document.getElementById('itens_faltantes').style.display = 'block';
				document.getElementById('faltante').value = 1;
			}
		}
	function carrega_combo(id){
		document.getElementById('resposta[135]').checked = id;
	}
	</script>


</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" alink="#0000FF" vlink="#0000FF" link="#0000FF">
	<table border='0' cellpadding='0' cellspacing='0' style='border-collapse: collapse' bordercolor='#111111' width='950' bgcolor='#A5B4B0'>
		<tr>
			<td for='pesqCli' width='20%'><p align='left'>
					<img border='0' src='../i/logo_aiguilles.jpg'></td>
			<td width='20%' align='center'>
				<table border='0' cellpadding='0' cellspacing='0' style='border-collapse: collapse' bordercolor='#111111' width='100%'>
					<tr>
						<td width='15%'></td>
						<td width='46%'>
							<table border='0' cellpadding='0' cellspacing='0' style='border-collapse: collapse' bordercolor='#111111' width='90%'>
								<label for='pesq_ficha_cli'>
									<tr>
										<td width='28%' for='pesq_ficha_cli' onmouseover=style.cursor='hand'><a id='pesq_ficha_cli' href='menu_pesquisas.php' class='link7'><img border='0' src='../i/btn_pesqfclientes.bmp'
												width='39' height='55'></a></td>
										<td width='72%' for='pesq_ficha_cli' class='link7' onmouseover=style.cursor='hand'>Pesquisas Diversas</td>
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
				<table border='0' cellpadding='0' cellspacing='0' style='border-collapse: collapse' bordercolor='#111111' width='100%'>
					<label for='telaInicial'>
						<tr>
							<td width='65%' class='link7' onmouseover=style.cursor='hand'><p align='right'>
									<a id='telaInicial' class='link7' href='../modulos.php'><img border='0' src='../i/cantoDiagDirSup.gif' width='20'><img border='0' src='../i/btn_telaInicial.bmp' width='44'></a></td>
							<td width='35%' bgcolor='#C2CECC' class='link7' onmouseover=style.cursor='hand'><a id='telaInicial' class='link7' href='../modulos.php'>Tela Inicial</a></td>
						</tr>
					</label>
				</table>
			</td>
		</tr>
	</table>
	<table border="0" cellspacing="0" cellpadding="0" width="950" bgcolor="#BCC8C5" height="25">
		<tr>
			<td width="150"></td>
			<td colspan="4" class="link4"><b>| <a href="index.php" class="link4">TRIAGEM - HOME</a> | <a href="triagensPendentes.php" class="link4">Triagens em Aberto</a> | Triagem |
			</b></td>
		</tr>
	</table>
	<table border="0" width="950" cellpadding="0" cellspacing="0">
		<tr>
			<td width="150" valign="top" bgcolor="#BCC8C5">
				<table border="0" width="100%" cellpadding="0" cellspacing="0" bgcolor="#BCC8C5">
					<tr>
						<td width="5"><img src="../i/spacer.gif" width=5 height=50 border="0"></td>
						<td class="link4"><a href="#" onclick=javascript:window.open( '../alteraDadosUsuario.php','newwin','width=400,height=330,scrollbars=no ') class='link4'> FRANCISCO CARLOS DA MATA</a><BR>COORD.
						</td>
					</tr>
				</table>
				<table border="0" cellspacing="0" cellpadding="0" width="150" bgcolor="#A5B4B0">
					<tr onmouseover=style.cursor='hand'>
						<td width="150" valign="top">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<!-- Inicio Gera Lote EXT -->
								<tr onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'" onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<label for="agendamentosPendentes">
										<td width="52" class="link4"><a id='agendamentosPendentes' href="agendamentosPendentes.php"><img src="../i/btn_buffer1.gif" alt="" width=52 height=60 border="0" align="absmiddle"></a></td>
										<td class="link4" for="agendamentosPendentes">Agendamentos<br>(Lotes Externo)
									</td>
									</label>
								</tr>
								<!-- Fim Gera Lote EXT -->
								<tr onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'" onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<td height="3" colspan="2" bgcolor="#BECDC9"></td>
								</tr>
								<!-- Inicio Gera Lote INT -->
								<tr onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'" onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<label for="menu_lotesInternos">
										<td width="52" class="link4"><a id='menu_lotesInternos' href="menu_lotesInternos.php"><img src="../i/btn_buffer.gif" alt="" width=52 height=60 border="0" align="absmiddle"></a></td>
										<td class="link4" for="menu_lotesInternos">N.F. Devolução<BR>(Lotes Interno)
									</td>
									</label>
								</tr>
								<!-- Fim Gera Lote INT -->
								<tr onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'" onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<td height="3" colspan="2" bgcolor="#BECDC9"></td>
								</tr>
								<!-- Inicio Triagem -->
								<tr onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'" onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<label for="triagensPendentes">
										<td width="52" class="link4"><a id='triagensPendentes' href="triagensPendentes.php"><img src="../i/btn_buscaAT.gif" alt="" width=52 height=60 border="0" align="absmiddle"></a></td>
										<td class="link4" for="atendReceptivo">Triagens<br>em Aberto
									</td>
									</label>
								</tr>
								<!-- Fim Triagem -->
								<tr onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'" onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<td height="3" colspan="2" bgcolor="#BECDC9"></td>
								</tr>
								<!-- Inicio GRADES alterados -->
								<tr onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'" onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<label for="gradesAlterados">
										<td width="52" class="link4"><a id='gradesAlterados' href="gradesAlterados.php"><img src="../i/btn_vaivolta.gif" alt="" width=52 height=60 border="0" align="absmiddle"></a></td>
										<td class="link4" for="gradesAlterados">GRADES Alterados<br>(Alterar Físico)
									</td>
									</label>
								</tr>
								<!-- Fim GRADES alterados -->
								<tr onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'" onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<td height="3" colspan="2" bgcolor="#BECDC9"></td>
								</tr>
								<!-- Inicio APROVAÇAO -->
								<tr onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'" onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<label for="pendAprovaLote">
										<td width="52" class="link4"><a id='pendAprovaLote' href="pendAprovaLote.php"><img src="../i/btn_nfEntr.gif" alt="" width=52 height=60 border="0" align="absmiddle"></a></td>
										<td class="link4" for="pendAprovaLote">Confirmação de Lotes</td>
									</label>
								</tr>
								<!-- Fim APROVAÇAO -->
								<tr onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'" onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<td height="3" colspan="2" bgcolor="#BECDC9"></td>
								</tr>
								<!-- Inicio GRAFICOS -->
								<tr onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'" onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<label for="graficos_triagem">
										<td width="52" class="link4"><a id='graficos_triagem' href="graficos_triagem.php"><img src="../i/btn_graficos.gif" width=52 height=60 border="0" align="absmiddle"></a></td>
										<td class="link4" for="graficos_triagem">Gráficos</td>
									</label>
								</tr>
								<!-- Fim GRAFICOS -->
								<tr onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'" onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<td height="3" colspan="2" bgcolor="#BECDC9"></td>
								</tr>
								<!-- Inicio RELATORIOS -->
								<tr onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'" onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<label for="relatorios_triagem">
										<td width="52" class="link4"><a id='relatorios_triagem' href="relatorios_triagem.php"><img src="../i/btn_relatinf.gif" width=52 height=60 border="0" align="absmiddle"></a></td>
										<td class="link4" for="relatorios_at">Relat&oacute;rios Gerenciais</td>
									</label>
								</tr>
								<!-- Fim RELATORIOS -->
								<tr onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'" onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
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
						<td valign="top"><img src="../i/juncao1.gif" width="15" height="15" border="0"></td>
					</tr>
					<tr>
						<td style="padding-left: 15px;"><b class="link3"><img src="../i/seta1.gif" width="15" height="15" border="0" align="absmiddle" hspace="0" vspace="5">TRIAGEM</b><br></td>
					<tr>
						<td style="padding-left: 15px;">
							<form name='form1' id='triagem' method='POST' onSubmit="return false;">
								<input type='hidden' name='warehouse_id' id='warehouse_id' value='758'>
								<table border="1" cellpadding="2" width="785" bordercolorlight="#C0C0C0" bordercolordark="#F0F0F0">
									<tr>
										<td class='subTituloFicha' colspan='10' align='center'>Dados do Modelo</td>
									</tr>
									<tr align="left">
										<td width="60" class="labelFicha">Cliente:</td>
										<td width="110" class="link1">MOTOROLA</td>
										<td width="70" class="labelFicha">Warehouse:</td>
										<td colspan="3" class="link1">FAST SHOP DF - MOTOROLA</td>
										<td width="55" class="labelFicha">Rede:</td>
										<td width="153" class="link1">FAST SHOP</td>
									</tr>
									<tr align="left">
										<td class="labelFicha">Marca:</td>
										<td class="link1">MOTOROLA</td>
										<td class="labelFicha">Familia:</td>
										<td width="130" class="link1">TC</td>
										<td width="40" class="labelFicha">Linha:</td>
										<td width="100" class="link1">HHP</td>
										<td class="labelFicha">Regional:</td>
										<td class="link1">MOTOROLA - SP</td>
									</tr>
									<tr align="left">
										<td class="labelFicha">Código:</td>
										<td class="link1">XT316</td>
										<td class="labelFicha">Modelo:</td>
										<td class="link1" colspan="3">XT316</td>
										<td class="labelFicha">Qtd.:</td>
										<td class="link1">0/1&nbsp;</td>
									</tr>
								</table>
								<table border="1" cellpadding="2" width="785" bordercolorlight="#C0C0C0" bordercolordark="#F0F0F0">
									<tr>
										<td class='subTituloFicha' colspan='4' align='center'>Triagem</td>
									</tr>
									<tr align='left'>
										<td width='187' class='labelFicha'>Triador:</td>
										<td width='187' class='link1'><select name='triador_id' id='triador_id' required='yes' class='link1'>
												<option value='0'>-- Selecione --</option>
												<option value='ABARROS'>ADALBERTO BARROS GOMES JUNIOR</option>
												<option value='admin'>ADMINISTRADOR</option>
												<option value='AALMEIDA'>ALCIDES FERNANDO DE ALMEIDA</option>
												<option value='ALESILVA'>ALECSANDRO DA SILVA ZAMAQUIZOMARE</option>
												<option value='ACUNHA'>ALEX GOMES DA SILVA CUNHA</option>
												<option value='ALAINE'>ALICE LAINE</option>
												<option value='AAMORIM'>ALINE AMORIM DE SOUZA</option>
												<option value='AELUZ'>ALUIZIO EPFANIO DA LUZ</option>
												<option value='AAUGUSTO'>ALVARO AUGUSTO COSTA GOMES</option>
												<option value='AFERRAS'>AMANDA FERRAS VIVI</option>
												<option value='ANALUCIA'>ANA LUCIA</option>
												<option value='ARODRIGUES'>ANA PAULA LIMA RODRIGUES</option>
												<option value='AALVES'>ANDERSON DE LIRA ALVES</option>
												<option value='AGOMES'>ANDERSON GOMES DA SILVA CUNHA</option>
												<option value='AGUSTAVO'>ANDERSON GUSTAVO</option>
												<option value='AROVEDER'>ANDRE ROBERTO ROVEDER</option>
												<option value='acosta'>ARTHUR DA SILVA COSTA</option>
												<option value='ASILVA'>AURELIO CLEMENTINO RAMOS DA SILVA</option>
												<option value='ALIRA'>AURINO DE LIRA ALVES</option>
												<option value='BCORREA'>BIANCA LIMA CORRÊA</option>
												<option value='BAMARANTE'>BRENDA DOS SANTOS AMARANTE</option>
												<option value='BSANTANA'>BRUNO SANTANA</option>
												<option value='CSILVA'>CAIO GONÇALVES DA SILVA</option>
												<option value='CASILVA'>CAMILA DA SILVA CAVALHEIRO</option>
												<option value='CMOREIRA'>CARLA BRAGA MOREIRA</option>
												<option value='COLIVEIRA'>CARLA DE OLIVEIRA</option>
												<option value='CCRUZ'>CARLOS ALBERTO CRUZ</option>
												<option value='CMUNIZ'>CAROLINA MUNIZ</option>
												<option value='CSOUSA'>CAROLINE DE SOUSA OLIVEIRA</option>
												<option value='CVICENTE'>CELSO VICENTE LOPES</option>
												<option value='CSOARES'>CINTIA SOARES BARBOSA</option>
												<option value='CNASCIMENTO'>CLAITON LUIS NASCIMENTO ARAUJO</option>
												<option value='CGONCALVES'>CLAUDEMIR PEREIRA GONÇALVES</option>
												<option value='CANJOS'>CLAUDSON CARVALHO DOS ANJOS</option>
												<option value='CDAMASCENO'>CLAYTON APARECIDO DAMASCENO</option>
												<option value='CSANTANA'>CRISTIANE SANTANA CONCEIÇÃO</option>
												<option value='DGABRIEL'>DAILOR GABRIEL HEIBUTCKER</option>
												<option value='dmaciel'>DANIEL MACIEL PAULINO DA SILVA</option>
												<option value='danielmaciel'>DANIEL MACIEL PAULINO DA SILVA</option>
												<option value='DPAULO'>DANIEL PAULO</option>
												<option value='DMARQUES'>DANIELA CRISTINA MARQUES</option>
												<option value='DSIQUEIRA'>DENIS BATISTA DE SIQUEIRA</option>
												<option value='DPIRES'>DIEGO PIRES DE MORAES CHAUTZ</option>
												<option value='DINACIO'>DJALMIR INÁCIO DA SILVA</option>
												<option value='DFIDENCIO'>DOUGLAS GONÇALVES FIDENCIO</option>
												<option value='ESANTOS'>EDENILSON NASCIMENTO DOS SANTOS</option>
												<option value='EMARCONDES'>EDUARDO AGOSTINHO MARCONDES</option>
												<option value='erocha'>EDUARDO ALMEIDA ROCHA</option>
												<option value='EFREIS'>EDUARDO FURTADO DOS REIS</option>
												<option value='ELOPES'>ELIZANGELA DE LIMA LOPES</option>
												<option value='ECORDEIRO'>ENAILE DA MATA CORDEIRO</option>
												<option value='eafonso'>ERICA MARIA DE SOUZA AFONSO</option>
												<option value='ESILVA'>EUGENIO PEREIRA SILVA</option>
												<option value='ENASCIMENTO'>EVANDRO ANTONIO DO NASCIMENTO</option>
												<option value='ecocis'>EVANDRO COCIS</option>
												<option value='ESANTANA'>EVERTON ESCUDERO SANTANA</option>
												<option value='FGOMES'>FABIANA GOMES</option>
												<option value='FCRISTINA'>FABIANE CRISTINA DA SILVA</option>
												<option value='FMORAES'>FABIO HEIJI MORAES</option>
												<option value='FOLIVEIRA'>FABIO MARTINS DE OLIVEIRA</option>
												<option value='FRAMOS'>FABIO RAMOS DA SILVA</option>
												<option value='FMACEDO'>FELIPE DE SOUZA MACEDO</option>
												<option value='FLIETTE'>FELIPE LIETTE</option>
												<option value='FFONSECA'>FELIPE SILVEIRA FONSECA DA SILVA</option>
												<option value='FSANTOS'>FELIPE WESLEY DOS SANTOS PAIVA</option>
												<option value='FGONÇALVES'>FERNANDA DA SILVA GONÇALVES</option>
												<option value='FCSOUZA'>FLAVIO CANDIDO DE SOUZA</option>
												<option value='FBATISTA'>FLÁVIO DE PAULA BATISTA</option>
												<option value='FFERNANDA'>FRANCIELE FERNANDA SILVA</option>
												<option value='FCARLOS'>FRANCISCO CARLOS DA MATA</option>
												<option value='GALMEIDA'>GABRIEL DE ALMEIDA</option>
												<option value='gribeiro'>GABRIELA SILVA RIBEIRO</option>
												<option value='GNOGUEIRA'>GEANE GODOY NOGUEIRA</option>
												<option value='GPALMA'>GIOVANNI DI PALMA</option>
												<option value='GBRITO'>GIRLEIDE GONÇALVES DE BRITO JESUS</option>
												<option value='GUSTAVOA'>GUSTAVO ANSAY</option>
												<option value='GUTEMBERG'>GUTEMBERG</option>
												<option value='GREZENDE'>GUTEMBERG REZENDE DE OLIVEIRA FILHO</option>
												<option value='HELIO'>HELIO ALEXANDRE</option>
												<option value='ISANTOS'>IGOR SIQUEIRA DOS SANTOS</option>
												<option value='ILIMA'>ILBRENER MOREIRA DE LIMA</option>
												<option value='IPEREIRA'>IRAN AMORIM PEREIRA</option>
												<option value='JFERREIRA'>JAIRO NATAN FERREIRA GOMES</option>
												<option value='JRMOREIRA'>JEFFERSON REDONDO MOREIRA</option>
												<option value='JMOTA'>JESSICA JESUS DA MOTA SIQUEIRA</option>
												<option value='JCARLOS'>JOÃO CARLOS RODRIGUES ALVES</option>
												<option value='JTAVARES'>JOAO DANIEL TAVARES</option>
												<option value='JPCRUZ'>JOAO PAULO DA CRUZ NASCIMENTO</option>
												<option value='JMORETI'>JOAO PAULO MORETI</option>
												<option value='JGOULART'>JOSE FARIA GOULART</option>
												<option value='JRAFAEL'>JOSÉ RAFAEL</option>
												<option value='JDAVILA'>JOSELAINE D AVILA</option>
												<option value='JBUENO'>JOSIANE BUENO AFONSO</option>
												<option value='jvieira'>JOYCE FRANCINI VIEIRA</option>
												<option value='JSNEVES'>JULIANA SILVA DAS NEVES</option>
												<option value='JFSANTOS'>JULIANO FERNANDES DOS SANTOS</option>
												<option value='JMACIEL'>JURANDIR GOMES MACIEL</option>
												<option value='KPEREIRA'>KARINA PEREIRA CRISANTE DA SILVA</option>
												<option value='LGRIPPA'>LAURO SIQUEIRA GRIPPA</option>
												<option value='LSANTOS'>LEANDRO MOREIRA DOS SANTOS</option>
												<option value='LSOUSA'>LEOMIR SOUSA SANTOS</option>
												<option value='LCORREA'>LEONARDO DE OLIVEIRA CORREA</option>
												<option value='LUCOSTA'>LUANA SIQUEIRA COSTA</option>
												<option value='LMENDONÇA'>LUCAS DA ROCHA MENDONÇA</option>
												<option value='LSILVA'>LUCAS DOS SANTOS SILVA</option>
												<option value='LCAMARGO'>LUCAS SOSSI CAMARGO</option>
												<option value='LCLAUDIO'>LUIZ CLAUDIO NOBRE NEVES</option>
												<option value='MSILVA'>MADSON FELIPE LEITE DA SILVA</option>
												<option value='MARIANEO'>MARIANE DE OLIVEIRA</option>
												<option value='msouza'>MARIANE SOUZA</option>
												<option value='mmarquezini'>MEIRE ISSIS MARQUEZINI</option>
												<option value='MANUNCIACAO'>MOISÉS COSTA DA ANUNCIAÇÃO</option>
												<option value='PROCHA'>PATRICIA MACHADO DE JESUS ROCHA</option>
												<option value='PNASCIMENTO'>PATRICIA NASCIMENTO</option>
												<option value='PLIMA'>PAULO RAFAEL MELO LIMA</option>
												<option value='PRIBEIRO'>PAULO SÉRGIO RIBEIRO</option>
												<option value='rhsilva'>RAFAEL HENRIQUE SANTOS DA SILVA</option>
												<option value='RPAIVA'>RAFAELA DE QUADROS PAIVA</option>
												<option value='RLIMA'>RAFAELA LIMA</option>
												<option value='ROLIVEIRA'>REGIANE LIMA OLIVEIRA</option>
												<option value='RGUIDO'>RENALDO GUIDO</option>
												<option value='RANJOS'>RENATO BARBOSA DOS ANJOS</option>
												<option value='RICARDORS'>RICARDO RODRIGUES DA SILVA</option>
												<option value='RRSANTOS'>RICARDO RODRIGUES SANTOS</option>
												<option value='ROROCHA'>RICHARD OLIVEIRA DA ROCHA</option>
												<option value='RGAVINO'>ROBERTO GAVINO</option>
												<option value='RVIRGINIO'>RODRIGO DE LIMA VIRGÍNIO</option>
												<option value='RSOUSA'>RODRIGO SOUSA DE AQUINO</option>
												<option value='RGAMA'>RONALDO SANTOS GAMA</option>
												<option value='RJUNIOR'>ROQUE DO CARMO DE FARIA JUNIOR</option>
												<option value='RFREITAS'>RUAN CARLOS DE FREITAS</option>
												<option value='SSAMPAIO'>SAVIO DOS SANTOS SAMPAIO</option>
												<option value='SFEITOSA'>SÉRGIO ALVES FEITOSA</option>
												<option value='SERGIOA'>SÉRGIO ARRUDA</option>
												<option value='TBARROS'>SERVIO TULIO CARVALHO BARROS</option>
												<option value='SIMONE'>SIMONE MARTINIANO</option>
												<option value='TMOURA'>TATIANE PRISCILA MOURA</option>
												<option value='TCIAEXTERNA'>TCIA EXTERNA</option>
												<option value='TDUBEAU'>THAMIRYS DUBEAU</option>
												<option value='TSANTOS'>THAYAN PEREIRA DOS SANTOS</option>
												<option value='TFRANCISCO'>THIAGO AUGUSTO C. FRANCISCO</option>
												<option value='TBALANCIERE'>THIAGO BALANCIERE</option>
												<option value='TAMORIM'>TIAGO AMORIM</option>
												<option value='TLOPES'>TIAGO LOPES AMORIM</option>
												<option value='TRIAGEMTO'>TRIAGEM TO</option>
												<option value='valter'>VALTER BESERRA</option>
												<option value='voliveira'>VANIA MARIA DE OLIVEIRA</option>
												<option value='VSILVA'>VICTOR DA SILVA TRINDADE</option>
												<option value='VROBERTO'>VINICIUS ROBERTO CHAGAS</option>
												<option value='WRODRIGUES'>WELDOM RODRIGUES CATENHEDE</option>
												<option value='WSILVA'>WILLIAM SOUSA E SILVA</option>
												<option value='WAMORIM'>WILLIANS AMORIM</option>
										</select></td>
										<td class='labelFicha'>&nbsp;</td>
										<td class='link1'>&nbsp;</td>
									</tr>
									<tr align='left'>
										<td width='187' class='labelFicha'>MSN:</td>
										<td width='187' class='link1'><input type='text' name='serial' id='serial' class='textbox' style='width: 185px; text-transform: uppercase;' required='yes'
											onBlur="if(this.value){this.value = this.value.toUpperCase(); if(!isSerial(this.value, 'MSN')){ window.alert('O MSN digitado está incorreto'); this.focus(); } else if(serialLote(this.value)){ window.alert('MSN lançado já existe neste lote.'); this.value = ''; this.focus(); }}"
											maxlength="25"></td>
										<td width='187' class='labelFicha'>Consta Autorização de CCC:</td>
										<td width='188' class='link1'><select class='textbox' name='autorizacaoCCC' required='yes'>
												<option selected value='-1'>Escolha...</option>
												<option value='S'>Sim</option>
												<option value='N'>Nao</option>
												<!--<option value='A'>Aguardando</option>-->
										</select></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>CÓDIGO LYESA</td>
										<td class='link1'><input type='TEXT' name='resposta[98]' class='ac_input' id='resposta[98]' style='width: 120px;' class='textbox' maxlength='5' onKeyPress='soNum(event);' required='yes'>&nbsp;<a
											href='javascript:void(0)' onclick='window.open("../help/98.jpg", "JANELA", "toolbar = no,width=800,height=600,resizable=auto "); '><img src='../i/interroga.gif' border=0
												style='padding-top: 5px; padding-left: 5px'></a></td>
										<td class='labelFicha'>CÓD MODELO CLIENTE</td>
										<td class='link1'><div id='cod_cliente_kit'>
												<input type='TEXT' name='resposta[21]' id='resposta[21]' style='width: 90px;' class='textbox' maxlength='14' required='yes'>
											</div></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>IMEI</td>
										<td class='link1'><input type='TEXT' name='resposta[20]' id='resposta[20]' style='width: 150px;' class='textbox' maxlength='15' onKeyPress='soNum(event);' onBlur='ehImei(this);'
											required='yes'>&nbsp;<a href='javascript:void(0)' onclick='window.open("../help/20.jpg", "JANELA", "toolbar = no,width=800,height=600,resizable=auto "); '><img
												src='../i/interroga.gif' border=0 style='padding-top: 5px; padding-left: 5px'></a></td>
										<td class='link1'><table width='185' border='0' cellpadding='0' cellspacing='0'>
												<tr>
													<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[35]' id='resposta[35]' value='35' class='textbox'></td>
													<td valign='middle' class='link1' width='162'><b>APARELHO ESTÁ BLOQUEADO?</b></td>
												</tr>
											</table></td>
										<td width='187' class='labelFicha' colspan='1'>&nbsp;</td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>OPERADORA</td>
										<td class='link1'><select class='textbox' name='resposta[108]' required='yes'>
												<option selected value='-1'>Escolha...</option>
												<option value='904'>BRT</option>
												<option value='901'>CLARO</option>
												<option value='903'>OI</option>
												<option value='906'>OPEN</option>
												<option value='905'>TELEMIG</option>
												<option value='902'>TIM</option>
												<option value='900'>VIVO</option>
										</select></td>
										<td class='labelFicha'>Sinais de oxidação</td>
										<td class='link1'><select class='textbox' name='resposta[70]' required='yes'>
												<option selected value='-1'>Escolha...</option>
												<option value='1'>Sim</option>
												<option value='0'>Não</option>
										</select></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>Acessórios Originais</td>
										<td class='link1'><select class='textbox' name='resposta[94]' required='yes'>
												<option selected value='-1'>Escolha...</option>
												<option value='1'>Sim</option>
												<option value='0'>Não</option>
										</select></td>
										<td class='labelFicha'>Troca de Placa - (PCB)</td>
										<td class='link1'><select class='textbox' name='resposta[71]' required='yes'>
												<option selected value='-1'>Escolha...</option>
												<option value='1'>Sim</option>
												<option value='0'>Não</option>
										</select></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>Aparência (MT)</td>
										<td class='link1'><select class='textbox' name='resposta[95]' required='yes'>
												<option selected value='-1'>Escolha...</option>
												<option value='856'>Aparência OK</option>
												<option value='852'>Riscos Leves</option>
												<option value='853'>Riscos Profundos</option>
												<option value='855'>Sinais de mau uso</option>
										</select></td>
										<td class='labelFicha'>Defeito é igual ao Checklist?</td>
										<td class='link1'><select class='textbox' name='resposta[99]' required='yes'>
												<option selected value='-1'>Escolha...</option>
												<option value='1'>Sim</option>
												<option value='0'>Não</option>
										</select></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>COR - Motorola</td>
										<td class='link1'><div id='kit_cor'>
												<select class='textbox' name='resposta[135]' required='yes'>
													<option selected value='-1'>Escolha...</option>
													<option value='1118'>TABLET CINZA</option>
													<option value='1119'>TABLET PRETO</option>
													<option value='882'>TELEFONE AZUL</option>
													<option value='886'>TELEFONE BRANCO</option>
													<option value='887'>TELEFONE BRANCO E AMARELO</option>
													<option value='888'>TELEFONE BRANCO E AZUL</option>
													<option value='909'>TELEFONE BRANCO E LARANJA</option>
													<option value='1163'>TELEFONE BRANCO E PRETO</option>
													<option value='889'>TELEFONE BRANCO E ROSA</option>
													<option value='890'>TELEFONE BRANCO E VERMELHO</option>
													<option value='891'>TELEFONE CAFE</option>
													<option value='892'>TELEFONE CINZA</option>
													<option value='893'>TELEFONE CINZA CHUMBO</option>
													<option value='894'>TELEFONE CINZA CHUMBO E PRETO</option>
													<option value='907'>TELEFONE CINZA E AZUL</option>
													<option value='936'>TELEFONE CINZA E PRETO</option>
													<option value='895'>TELEFONE CINZA E VERDE</option>
													<option value='1080'>TELEFONE CINZA E VERMELHO</option>
													<option value='1263'>TELEFONE CINZA ESCURO</option>
													<option value='1212'>TELEFONE CROMO</option>
													<option value='908'>TELEFONE DOURADO</option>
													<option value='881'>TELEFONE GRAFITE</option>
													<option value='1084'>TELEFONE GRAFITE E AZUL</option>
													<option value='896'>TELEFONE GRAFITE E VERMELHO</option>
													<option value='897'>TELEFONE LARANJA</option>
													<option value='883'>TELEFONE PLATINA</option>
													<option value='885'>TELEFONE PRATA</option>
													<option value='868'>TELEFONE PRETO</option>
													<option value='869'>TELEFONE PRETO E AZUL</option>
													<option value='1141'>TELEFONE PRETO E CINZA</option>
													<option value='870'>TELEFONE PRETO E DOURADO</option>
													<option value='871'>TELEFONE PRETO E LARANJA</option>
													<option value='872'>TELEFONE PRETO E PRATA</option>
													<option value='873'>TELEFONE PRETO E VERDE</option>
													<option value='874'>TELEFONE PRETO E VERMELHO</option>
													<option value='875'>TELEFONE ROSA</option>
													<option value='876'>TELEFONE ROSA GELATO</option>
													<option value='877'>TELEFONE ROXO</option>
													<option value='935'>TELEFONE TITANIO</option>
													<option value='920'>TELEFONE VERDE</option>
													<option value='878'>TELEFONE VERMELHO</option>
													<option value='879'>TELEFONE VERMELHO E BRANCO</option>
													<option value='880'>TELEFONE VINHO</option>
											</div>
											</select></td>
										<td class='labelFicha'>Defeito Informado(MT)</td>
										<td class='link1'><select class='textbox' name='resposta[93]' required='yes'>
												<option selected value='-1'>Escolha...</option>
												<option value='833'>C0002 - Campainha não toca</option>
												<option value='835'>C0003 - Acessório com defeito</option>
												<option value='836'>C0004 - Camera com Problema/Inoperante</option>
												<option value='837'>C0007 - Problemas com Chamadas</option>
												<option value='838'>C0008 - Sem Audio</option>
												<option value='839'>C0009 - Bluetooth</option>
												<option value='840'>C0010 - Conector c/ Problema Inoperante</option>
												<option value='841'>C0011 - Display Externo</option>
												<option value='842'>C0012 - Display Principal</option>
												<option value='843'>C0019 - Desliga Sozinho</option>
												<option value='844'>C0019 - Não Liga</option>
												<option value='845'>C0020 - Tecla/Teclado Inoperante</option>
												<option value='1164'>C0022 - Sem Defeito</option>
												<option value='846'>C0023 - Bateria</option>
												<option value='848'>C0025 - Não Reconhece SIM Card</option>
												<option value='849'>C0026 - Funções com Problema/Inoperante</option>
												<option value='850'>C0027 - Check List inconsistente</option>
												<option value='851'>C0028 - Não Recarrega</option>
										</select></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>TEMPO DE USO (HHH:MM:SS)</td>
										<td class='link1'><input type='TEXT' name='resposta[22]' id='resposta[22]' style='width: 60px;' class='textbox' maxlength='9' onKeyPress='formatHora(this,event,4);'
											onBlur='horaErr(this,4);' required='yes'></td>
										<td class='labelFicha'>Defeito Constatado (MT)</td>
										<td class='link1'><select class='textbox' name='resposta[193]' required='yes'>
												<option selected value='-1'>Escolha...</option>
												<option value='1165'>C0002 - Campainha não toca</option>
												<option value='1166'>C0003 - Acessorio com defeito</option>
												<option value='1167'>C0004 - Camera com Problema Inoperante</option>
												<option value='1168'>C0007 - Problemas com Chamadas</option>
												<option value='1169'>C0008 - Sem Audio</option>
												<option value='1170'>C0009 - Bluetooth</option>
												<option value='1171'>C0010 - Conector c/ Problema Inoperante</option>
												<option value='1172'>C0011 - Display Externo</option>
												<option value='1173'>C0012 - Display Principal</option>
												<option value='1174'>C0019 - Desliga Sozinho</option>
												<option value='1175'>C0019 - Não Liga</option>
												<option value='1176'>C0020 - Tecla/Teclado Inoperante</option>
												<option value='1162'>C0022 - Sem Defeito</option>
												<option value='1177'>C0023 - Bateria</option>
												<option value='1178'>C0025 - Não Reconhece SIM Card</option>
												<option value='1179'>C0026 - Funções com Problema/Inoperante</option>
												<option value='1180'>C0028 - Não Recarrega</option>
										</select></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>Selo Assitência Técnica</td>
										<td class='link1'><select class='textbox' name='resposta[97]' required='yes'>
												<option selected value='-1'>Escolha...</option>
												<option value='1'>Sim</option>
												<option value='0'>Não</option>
										</select></td>
										<td class='labelFicha'>Kit Completo (MT)</td>
										<td class='link1'><select class='textbox' name='resposta[96]' required='yes' onBlur='valida_kit(this.value);'>
												<option selected value='-1'>Escolha...</option>
												<option value='1'>Sim</option>
												<option value='0'>Não</option>
										</select></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>DATA NF (VENDA)</td>
										<td class='link1'><input type='TEXT' name='resposta[24]' id='resposta[24]' style='width: 70px;' class='textbox' maxlength='10' onKeyPress='formatData(this,event);'
											onBlur='dataErr(this,1);'></td>
										<td class='labelFicha'>NR. NOTA FISCAL (VENDA)</td>
										<td class='link1'><input type='TEXT' name='resposta[23]' id='resposta[23]' style='width: 60px;' class='textbox' maxlength='6'></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>DATA NF (TROCA)</td>
										<td class='link1'><input type='TEXT' name='resposta[26]' id='resposta[26]' style='width: 70px;' class='textbox' maxlength='10' onKeyPress='formatData(this,event);'
											onBlur='dataErr(this,1);'></td>
										<td class='labelFicha'>NR. NOTA FISCAL (TROCA)</td>
										<td class='link1'><input type='TEXT' name='resposta[25]' id='resposta[25]' style='width: 60px;' class='textbox' maxlength='6'></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>DATA ABERTURA O.S. 1</td>
										<td class='link1'><input type='TEXT' name='resposta[30]' id='resposta[30]' style='width: 70px;' class='textbox' maxlength='10'></td>
										<td class='labelFicha'>CÓD PEÇA DA O.S. 1</td>
										<td class='link1'><input type='TEXT' name='resposta[32]' id='resposta[32]' style='width: 130px;' class='textbox' maxlength='25'></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>NR. ORDEM SERVIÇO 1</td>
										<td class='link1'><input type='TEXT' name='resposta[29]' id='resposta[29]' style='width: 130px;' class='textbox' maxlength='15'></td>
										<td class='labelFicha'>DATA ENTREGA O.S. 1</td>
										<td class='link1'><input type='TEXT' name='resposta[31]' id='resposta[31]' style='width: 70px;' class='textbox' maxlength='10'></td>
									</tr>
									<tr align='left'>
										<td width='187' class='labelFicha' valign='top' style='padding-top: 3px;'>Observações:</td>
										<td colspan="3" class='link1'><textarea id='obs' name='obs' class='textbox' style='width: 100%; height: 50px;' onKeyPress='soNumLetras(event);'></textarea></td>
									</tr>

									<tr>
										<td colspan=4 align=center style='color: #ff0000'><B>ITENS KIT</B></td>
									</tr>
									<tr>
										<td colspan=4>
											<div id='alvo'>
												<table>

													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[75]' id='resposta_kit[75]' value='75' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>02 BATERIAS</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[150]' id='resposta_kit[150]' value='150' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>02 CAPAS DE BATERIA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[102]' id='resposta_kit[102]' value='102' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>02 RECARREGADORES DE PAREDE</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[174]' id='resposta_kit[174]' value='174' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>03 CAPAS DE BATERIA</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[182]' id='resposta_kit[182]' value='182' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>ACTIVE SYNC CD</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[82]' id='resposta_kit[82]' value='82' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>ADAPTADOR</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[200]' id='resposta_kit[200]' value='200' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>ADAPTADOR WEBTOP</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[196]' id='resposta_kit[196]' value='196' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>APONTADOR PLASTICO STYLUS</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[74]' id='resposta_kit[74]' value='74' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>BATERIA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[90]' id='resposta_kit[90]' value='90' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>BOLSA DE MICRO-FIBRA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[183]' id='resposta_kit[183]' value='183' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>BOLSA ECOLOGICA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[137]' id='resposta_kit[137]' value='137' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CABO A/V</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[177]' id='resposta_kit[177]' value='177' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CABO HDMI</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[175]' id='resposta_kit[175]' value='175' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CABO PARA RECARREGADOR</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[79]' id='resposta_kit[79]' value='79' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CABO PARA SINCRONISMO</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[172]' id='resposta_kit[172]' value='172' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CABO Y</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[88]' id='resposta_kit[88]' value='88' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CANETA AGULHA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[76]' id='resposta_kit[76]' value='76' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CAPA DE BATERIA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[85]' id='resposta_kit[85]' value='85' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CAPA DE COURO</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[142]' id='resposta_kit[142]' value='142' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CAPA DE SIMCARD</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[187]' id='resposta_kit[187]' value='187' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CARTAO DE MEMORIA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[107]' id='resposta_kit[107]' value='107' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CARTAO DE MEMORIA 128MB</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[151]' id='resposta_kit[151]' value='151' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CARTAO DE MEMORIA 16GB</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[80]' id='resposta_kit[80]' value='80' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CARTAO DE MEMORIA 1GB</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[104]' id='resposta_kit[104]' value='104' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CARTAO DE MEMORIA 256MB</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[105]' id='resposta_kit[105]' value='105' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CARTAO DE MEMORIA 2GB</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[106]' id='resposta_kit[106]' value='106' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CARTAO DE MEMORIA 4GB</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[103]' id='resposta_kit[103]' value='103' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CARTAO DE MEMORIA 512MB</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[128]' id='resposta_kit[128]' value='128' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CARTAO DE MEMORIA 8GB</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[81]' id='resposta_kit[81]' value='81' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CD DE INSTALACAO</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[153]' id='resposta_kit[153]' value='153' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CD ITUNES</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[139]' id='resposta_kit[139]' value='139' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CD MICROSOFT GS</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[146]' id='resposta_kit[146]' value='146' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CD MOTOROLA MEDIA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[84]' id='resposta_kit[84]' value='84' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CD MPT</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[189]' id='resposta_kit[189]' value='189' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CD MPT/KODAK EASYSHARE</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[86]' id='resposta_kit[86]' value='86' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CD WINDOWS MEDIA PLAYER 11</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[140]' id='resposta_kit[140]' value='140' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CD-ROM PARA SINCRONISMO</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[89]' id='resposta_kit[89]' value='89' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>CORDAO</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[185]' id='resposta_kit[185]' value='185' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>DOCK VEICULAR</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[173]' id='resposta_kit[173]' value='173' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>DVD</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[149]' id='resposta_kit[149]' value='149' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>ESTAÇÃO MULTIMÍDIA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[176]' id='resposta_kit[176]' value='176' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>ESTACAO MULTIMIDIA C/ CONTROLE REMOTO</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[87]' id='resposta_kit[87]' value='87' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>FONE DE OUVIDO BLUETOOTH</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[78]' id='resposta_kit[78]' value='78' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>FONE DE OUVIDO STEREO</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[145]' id='resposta_kit[145]' value='145' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>GUIA DE INICIO RAPIDO</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[144]' id='resposta_kit[144]' value='144' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>KIT BOAS VINDAS</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[152]' id='resposta_kit[152]' value='152' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>LEITOR DE CARTAO DE MEMORIA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[77]' id='resposta_kit[77]' value='77' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>MANUAL</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[188]' id='resposta_kit[188]' value='188' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>PANO DE LIMPEZA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[141]' id='resposta_kit[141]' value='141' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>PC SYNC CD</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[138]' id='resposta_kit[138]' value='138' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>PRESILHA PARA CINTO</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[184]' id='resposta_kit[184]' value='184' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>RECARREGADOR</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[178]' id='resposta_kit[178]' value='178' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>RECARREGADOR DA ESTAÇÃO MULTIMIDIA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[73]' id='resposta_kit[73]' value='73' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>RECARREGADOR DE PAREDE</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[143]' id='resposta_kit[143]' value='143' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>RECARREGADOR RAPIDO</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[83]' id='resposta_kit[83]' value='83' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>RECARREGADOR VEICULAR</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[92]' id='resposta_kit[92]' value='92' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>SUPORTE PARA BRACO</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[186]' id='resposta_kit[186]' value='186' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>SUPORTE PARA DOCK</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[136]' id='resposta_kit[136]' value='136' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>SUPORTE PARA FONE DE OUVIDO STEREO</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta_kit[91]' id='resposta_kit[91]' value='91' class='textbox' onclick='verifica_incompleto()'></td>
																	<td valign='middle' class='link1' width='162'><b>TECIDO LUSTRA TELA</b></td>
																</tr>
															</table>
														</td>
												</table>
											</div>
										</td>
									</tr>

									<tr>
										<td colspan=4>
											<div id="itens_faltantes" style="display: none;">
												<table width=100%>
													<tr>
														<td colspan=4 align=center style='color: #ff0000'><b>ITENS FALTANTES</b></td>
													</tr>
													<td class='link1'>
														<table width='185' border='0' cellpadding='0' cellspacing='0'>
															<tr>
																<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[75]' id='resposta[75]' value='75' class='textbox' disabled></td>
																<td valign='middle' class='link1' width='162'><b>02 BATERIAS</b></td>
															</tr>
														</table>
													</td>
													<td class='link1'>
														<table width='185' border='0' cellpadding='0' cellspacing='0'>
															<tr>
																<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[150]' id='resposta[150]' value='150' class='textbox' disabled></td>
																<td valign='middle' class='link1' width='162'><b>02 CAPAS DE BATERIA</b></td>
															</tr>
														</table>
													</td>
													<td class='link1'>
														<table width='185' border='0' cellpadding='0' cellspacing='0'>
															<tr>
																<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[102]' id='resposta[102]' value='102' class='textbox' disabled></td>
																<td valign='middle' class='link1' width='162'><b>02 RECARREGADORES DE PAREDE</b></td>
															</tr>
														</table>
													</td>
													<td class='link1'>
														<table width='185' border='0' cellpadding='0' cellspacing='0'>
															<tr>
																<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[174]' id='resposta[174]' value='174' class='textbox' disabled></td>
																<td valign='middle' class='link1' width='162'><b>03 CAPAS DE BATERIA</b></td>
															</tr>
														</table>
													</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[182]' id='resposta[182]' value='182' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>ACTIVE SYNC CD</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[82]' id='resposta[82]' value='82' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>ADAPTADOR</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[200]' id='resposta[200]' value='200' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>ADAPTADOR WEBTOP</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[196]' id='resposta[196]' value='196' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>APONTADOR PLASTICO STYLUS</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[74]' id='resposta[74]' value='74' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>BATERIA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[90]' id='resposta[90]' value='90' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>BOLSA DE MICRO-FIBRA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[183]' id='resposta[183]' value='183' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>BOLSA ECOLOGICA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[137]' id='resposta[137]' value='137' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CABO A/V</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[177]' id='resposta[177]' value='177' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CABO HDMI</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[175]' id='resposta[175]' value='175' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CABO PARA RECARREGADOR</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[79]' id='resposta[79]' value='79' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CABO PARA SINCRONISMO</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[172]' id='resposta[172]' value='172' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CABO Y</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[88]' id='resposta[88]' value='88' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CANETA AGULHA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[76]' id='resposta[76]' value='76' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CAPA DE BATERIA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[85]' id='resposta[85]' value='85' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CAPA DE COURO</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[142]' id='resposta[142]' value='142' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CAPA DE SIMCARD</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[187]' id='resposta[187]' value='187' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CARTAO DE MEMORIA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[107]' id='resposta[107]' value='107' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CARTAO DE MEMORIA 128MB</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[151]' id='resposta[151]' value='151' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CARTAO DE MEMORIA 16GB</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[80]' id='resposta[80]' value='80' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CARTAO DE MEMORIA 1GB</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[104]' id='resposta[104]' value='104' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CARTAO DE MEMORIA 256MB</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[105]' id='resposta[105]' value='105' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CARTAO DE MEMORIA 2GB</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[106]' id='resposta[106]' value='106' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CARTAO DE MEMORIA 4GB</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[103]' id='resposta[103]' value='103' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CARTAO DE MEMORIA 512MB</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[128]' id='resposta[128]' value='128' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CARTAO DE MEMORIA 8GB</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[81]' id='resposta[81]' value='81' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CD DE INSTALACAO</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[153]' id='resposta[153]' value='153' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CD ITUNES</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[139]' id='resposta[139]' value='139' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CD MICROSOFT GS</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[146]' id='resposta[146]' value='146' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CD MOTOROLA MEDIA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[84]' id='resposta[84]' value='84' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CD MPT</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[189]' id='resposta[189]' value='189' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CD MPT/KODAK EASYSHARE</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[86]' id='resposta[86]' value='86' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CD WINDOWS MEDIA PLAYER 11</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[140]' id='resposta[140]' value='140' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CD-ROM PARA SINCRONISMO</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[89]' id='resposta[89]' value='89' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>CORDAO</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[185]' id='resposta[185]' value='185' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>DOCK VEICULAR</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[173]' id='resposta[173]' value='173' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>DVD</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[149]' id='resposta[149]' value='149' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>ESTAÇÃO MULTIMÍDIA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[176]' id='resposta[176]' value='176' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>ESTACAO MULTIMIDIA C/ CONTROLE REMOTO</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[87]' id='resposta[87]' value='87' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>FONE DE OUVIDO BLUETOOTH</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[78]' id='resposta[78]' value='78' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>FONE DE OUVIDO STEREO</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[145]' id='resposta[145]' value='145' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>GUIA DE INICIO RAPIDO</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[144]' id='resposta[144]' value='144' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>KIT BOAS VINDAS</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[152]' id='resposta[152]' value='152' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>LEITOR DE CARTAO DE MEMORIA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[77]' id='resposta[77]' value='77' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>MANUAL</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[188]' id='resposta[188]' value='188' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>PANO DE LIMPEZA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[141]' id='resposta[141]' value='141' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>PC SYNC CD</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[138]' id='resposta[138]' value='138' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>PRESILHA PARA CINTO</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[184]' id='resposta[184]' value='184' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>RECARREGADOR</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[178]' id='resposta[178]' value='178' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>RECARREGADOR DA ESTAÇÃO MULTIMIDIA</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[73]' id='resposta[73]' value='73' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>RECARREGADOR DE PAREDE</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[143]' id='resposta[143]' value='143' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>RECARREGADOR RAPIDO</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[83]' id='resposta[83]' value='83' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>RECARREGADOR VEICULAR</b></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align='left'>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[92]' id='resposta[92]' value='92' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>SUPORTE PARA BRACO</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[186]' id='resposta[186]' value='186' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>SUPORTE PARA DOCK</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[136]' id='resposta[136]' value='136' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>SUPORTE PARA FONE DE OUVIDO STEREO</b></td>
																</tr>
															</table>
														</td>
														<td class='link1'>
															<table width='185' border='0' cellpadding='0' cellspacing='0'>
																<tr>
																	<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[91]' id='resposta[91]' value='91' class='textbox' disabled></td>
																	<td valign='middle' class='link1' width='162'><b>TECIDO LUSTRA TELA</b></td>
																</tr>
															</table>
														</td>
												</table>
											</div>
										</td>
									</tr>







									<tr>
										<td bgcolor="#F0F0F0" height="35" align="center" colspan="4" style="padding-top: 0px; padding-bottom: 0px;" id="teste"><input type='button' name='button_1' class='button1'
											style='width: 150px;' value='Processar Triagem' onClick='query();'></td>
									</tr>
								</table>
								<input type="hidden" name="lote_id" value="17000"> <input type="hidden" name="modelo_id" value="13430"> <input type="hidden" name="msg"
									value="Você deve preencher os campos indicados em vermelho."> <input type="hidden" name="faltante" id="faltante" value="0">
							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<script type="text/javascript">  
	function findValue(li) 
	{  	
		if( li == null ) 
			return alert("No match!");  	 // if coming from an AJAX call, let's use the CityId as the value  	
		
		if( !!li.extra ) 
			var sValue = li.extra[0];  	// otherwise, let's just display the value in the text box  	
		else 
			var sValue = li.selectValue;  	//alert("The value you selected was: " + sValue);  
	} 
	function selectItem(li) {    	
		findValue(li);  
	}  
	
	function formatItem(row){    	
		return row[0] + " (id: " + row[1] + ")";  
	}  
	
	function lookupAjax(){  
			var oSuggest = $(".ac_input")[0].autocompleter;    
			oSuggest.findValue();  	
			return false;  
	}  
	function lookupLocal(){    	
		var oSuggest = $(".ac_input")[0].autocompleter;    	
		oSuggest.findValue();    	
		return false;  
	}        
 
	$(".ac_input").blur(function(){
		lyesa = $(".ac_input").attr('value');
		if(lyesa != undefined){
			if(lyesa.length != 5){
				alert('Lyesa Invalido!'); 
			   	this.focus();
				this.value = '';
			}
			else{
				var codigo=lyesa.split("-"); 
				lyesa = this.value ;
				if (lyesa !=''){
					$(".ac_input").attr('value',codigo[0]);
					Ajax("./triagem.php?tr=c_cor&lyesa="+codigo[0]+"&wr="+document.form1.warehouse_id.value,document.getElementById("kit_cor"));
//					Ajax("./triagem.php?tr=c_ccl&lyesa="+codigo[0]+"&wr="+document.form1.warehouse_id.value,document.getElementById("cod_cliente_kit"));
//					Ajax('./triagem.php?tr=c_cor&lyesa=".$_GET['lyesa']."',document.getElementById('kit_cor'))
				}
			}
		}
	});
	
	$(".ac_input").autocomplete("autocomplete.php",{
		delay:10,
		minChars:2,
		matchSubset:1,
		matchContains:1,
		cacheLength:10,
		onItemSelect:selectItem,
		onFindValue:findValue,
		extraParams:{
						id:758					},
		formatItem:false,	
		autoFill:true  		
	});  
	
	
	
	
	</script>
</body>
</html>


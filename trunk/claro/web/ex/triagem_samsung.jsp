
<html>
<head>
<title>AIGUILLES - PRD</title>
<LINK REL="stylesheet" HREF="estilo.css" TYPE="text/css">
<script language="javascript" src="basicos.js"></script>
<script language="javascript" src="scriptMascara.js"></script>
<!-- Auto complete -->
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="jquery.autocomplete.js"></script>
<link rel="stylesheet" href="jquery.autocomplete.css" type="text/css" />

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
							mensagem += "ERRO OS2 \nData de abertura da OS2 nao pode ser maior que data de entrega da OS2.\n ";
						}
					}
				}
						
				//OS 3
				if (((res46) || (res47) || (res48) || (res49)) && !((res46) && (res47) && (res48) && (res49))){
					if (mensagem.length > 0)
						mensagem += "\n";
					mensagem += "ERRO OS3 \nDados da Ordem de Servi- 3 incompletos!\n ";
				}
				else{
					data1 = res48;
					data2 = res49;
					if ((res48 != '') && (res49 !='')){
						if ( parseInt( data2.split( "/" )[2].toString() + data2.split( "/" )[1].toString() + data2.split( "/" )[0].toString() ) < parseInt( data1.split( "/" )[2].toString() + data1.split( "/" )[1].toString() + data1.split( "/" )[0].toString())){
							if (mensagem.length > 0)
								mensagem += "\n";
							mensagem += "ERRO OS3 \nData de abertura da OS3 n- pode ser maior que data de entrega OS3.\n ";
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
						mensagem += "ERRO OS2 \nData de abertura da OS2 n- pode ser maior que data de entrega da OS1.\n" ;
					}
				}

				var data1 = res30;
				var data2 = res48;
				if ((res30 != '') && (res48 !='')){
					if ( parseInt( data2.split( "/" )[2].toString() + data2.split( "/" )[1].toString() + data2.split( "/" )[0].toString() ) <	parseInt( data1.split( "/" )[2].toString() + data1.split( "/" )[1].toString() + data1.split( "/" )[0].toString() ) ){
						if (mensagem.length > 0)
							mensagem += "\n";
						mensagem += "ERRO OS3 \nData de abertura da OS3 n- pode ser maior que data de entrega da OS1.\n " ;
					}
				}

				var data1 = res44;
				var data2 = res48;
				if ((res44 != '') && (res48 !='')){
					if ( parseInt( data2.split( "/" )[2].toString() + data2.split( "/" )[1].toString() + data2.split( "/" )[0].toString() ) <	parseInt( data1.split( "/" )[2].toString() + data1.split( "/" )[1].toString() + data1.split( "/" )[0].toString() ) ){
						if (mensagem.length > 0)
							mensagem += "\n";
						mensagem += "ERRO OS3 \nData de abertura da OS3 n- pode ser maior que data de entrega da OS2.\n " ;
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
										<td width='72%' for='pesq_ficha_cli' class='link7' onmouseover="style.cursor='hand'">Pesquisas Diversas</td>
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
						<td class="link4"><a href="#" onclick="javascript:window.open('../alteraDadosUsuario.php','newwin','width=400,height=330,scrollbars=no')" class='link4'> FRANCISCO CARLOS DA MATA</a><BR>COORD.</td>
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
										<td class="link4" for="menu_lotesInternos">N.F. Devolu-o<BR>(Lotes Interno)
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
										<td class="link4" for="gradesAlterados">GRADES Alterados<br>(Alterar F-ico)
									</td>
									</label>
								</tr>
								<!-- Fim GRADES alterados -->
								<tr onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'" onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<td height="3" colspan="2" bgcolor="#BECDC9"></td>
								</tr>
								<!-- Inicio APROVA-AO -->
								<tr onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'" onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<label for="pendAprovaLote">
										<td width="52" class="link4"><a id='pendAprovaLote' href="pendAprovaLote.php"><img src="../i/btn_nfEntr.gif" alt="" width=52 height=60 border="0" align="absmiddle"></a></td>
										<td class="link4" for="pendAprovaLote">Confirma-o de Lotes</td>
									</label>
								</tr>
								<!-- Fim APROVA-AO -->
								<tr onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'" onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<td height="3" colspan="2" bgcolor="#BECDC9"></td>
								</tr>
								<!-- Inicio GRAFICOS -->
								<tr onMouseOver="javascript: this.style.backgroundColor = '#CED7D5'" onMouseOut="javascript: this.style.backgroundColor = '#A5B4B0';">
									<label for="graficos_triagem">
										<td width="52" class="link4"><a id='graficos_triagem' href="graficos_triagem.php"><img src="../i/btn_graficos.gif" width=52 height=60 border="0" align="absmiddle"></a></td>
										<td class="link4" for="graficos_triagem">Gr-icos</td>
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
								<input type='hidden' name='warehouse_id' id='warehouse_id' value='757'>
								<table border="1" cellpadding="2" width="785" bordercolorlight="#C0C0C0" bordercolordark="#F0F0F0">
									<tr>
										<td class='subTituloFicha' colspan='10' align='center'>Dados do Modelo</td>
									</tr>
									<tr align="left">
										<td width="60" class="labelFicha">Cliente:</td>
										<td width="110" class="link1">SAMSUNG</td>
										<td width="70" class="labelFicha">Warehouse:</td>
										<td colspan="3" class="link1">FAST SHOP DF - SAMSUNG</td>
										<td width="55" class="labelFicha">Rede:</td>
										<td width="153" class="link1">FAST SHOP</td>
									</tr>
									<tr align="left">
										<td class="labelFicha">Marca:</td>
										<td class="link1">SAMSUNG</td>
										<td class="labelFicha">Familia:</td>
										<td width="130" class="link1">TC</td>
										<td width="40" class="labelFicha">Linha:</td>
										<td width="100" class="link1">HHP</td>
										<td class="labelFicha">Regional:</td>
										<td class="link1">SAMSUNG - SP</td>
									</tr>
									<tr align="left">
										<td class="labelFicha">C-igo:</td>
										<td class="link1">GT-I9000B</td>
										<td class="labelFicha">Modelo:</td>
										<td class="link1" colspan="3">GT-I9000B</td>
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
												<option value='WSILVA'>WILLIAM SOUSA E SILVA</option>
												<option value='WAMORIM'>WILLIANS AMORIM</option>
										</select></td>
										<td class='labelFicha'>&nbsp;</td>
										<td class='link1'>&nbsp;</td>
									</tr>
									<tr align='left'>
										<td width='187' class='labelFicha'>SSN:</td>
										<td width='187' class='link1'><input type='text' name='serial' id='serial' class='textbox' style='width: 185px; text-transform: uppercase;' required='yes'
											onBlur="if(this.value){this.value = this.value.toUpperCase(); if(!isSerial(this.value, 'SSN')){ window.alert('O SSN digitado est- incorreto'); this.focus(); } else if(serialLote(this.value)){ window.alert('SSN lando j existe neste lote.'); this.value = ''; this.focus(); }}"
											maxlength="25"></td>
										<td width='187' class='labelFicha'>Consta Autoriza-o de CCC:</td>
										<td width='188' class='link1'><select class='textbox' name='autorizacaoCCC' required='yes'>
												<option selected value='-1'>Escolha...</option>
												<option value='S'>Sim</option>
												<option value='N'>Nao</option>
												<!--<option value='A'>Aguardando</option>-->
										</select></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>IMEI</td>
										<td class='link1'><input type='TEXT' name='resposta[20]' id='resposta[20]' style='width: 150px;' class='textbox' maxlength='15' onKeyPress='soNum(event);' onBlur='ehImei(this);'
											required='yes'>&nbsp;<a href='javascript:void(0)' onclick='window.open("../help/20.jpg", "JANELA", "toolbar = no,width=800,height=600,resizable=auto "); '><img
												src='../i/interroga.gif' border=0 style='padding-top: 5px; padding-left: 5px'></a></td>
										<td class='labelFicha'>COR</td>
										<td class='link1'><div id='kit_cor'>
												<select class='textbox' name='resposta[40]'>
													<option selected value='-1'>Escolha...</option>
													<option value='934'>AMARELO</option>
													<option value='52'>AZUL</option>
													<option value='53'>AZUL E CINZA</option>
													<option value='54'>AZUL E VERDE</option>
													<option value='55'>BRANCO</option>
													<option value='56'>BRANCO E PRATA</option>
													<option value='865'>BRANCO E VERMELHO</option>
													<option value='57'>CARAMELO E LARANJA</option>
													<option value='58'>CINZA</option>
													<option value='59'>DOURADO</option>
													<option value='60'>GELO E AZUL</option>
													<option value='867'>LARANJA</option>
													<option value='922'>MARRON</option>
													<option value='61'>PEROLA E PRETO</option>
													<option value='62'>PRATA</option>
													<option value='63'>PRETO</option>
													<option value='910'>PRETO COM AZUL</option>
													<option value='864'>PRETO E LARANJA</option>
													<option value='866'>PRETO E VERMELHO</option>
													<option value='64'>ROSA</option>
													<option value='861'>ROXO</option>
													<option value='65'>VERDE</option>
													<option value='66'>VERMELHO</option>
													<option value='67'>VINHO</option>
													<option value='68'>VIOLETA</option>
											</div> </select></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>POSSUI CHECK LIST?</td>
										<td class='link1'><select class='textbox' name='resposta[19]' required='yes'>
												<option selected value='-1'>Escolha...</option>
												<option value='1'>Sim</option>
												<option value='0'>N-</option>
										</select></td>
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
									</tr>
									<tr align='left'>
										<td class='labelFicha'>CD MODELO CLIENTE</td>
										<td class='link1'><div id='cod_cliente_kit'>
												<input type='TEXT' name='resposta[21]' id='resposta[21]' style='width: 90px;' class='textbox' maxlength='14'>
											</div></td>
										<td class='labelFicha'>TEMPO DE USO (HHH:MM:SS)</td>
										<td class='link1'><input type='TEXT' name='resposta[22]' id='resposta[22]' style='width: 60px;' class='textbox' maxlength='9' onKeyPress='formatHora(this,event,4);'
											onBlur='horaErr(this,4);' required='yes'></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>NR. NOTA FISCAL (VENDA)</td>
										<td class='link1'><input type='TEXT' name='resposta[23]' id='resposta[23]' style='width: 60px;' class='textbox' maxlength='6' onKeyPress='soNum(event);'></td>
										<td class='labelFicha'>DATA NF (VENDA)</td>
										<td class='link1'><input type='TEXT' name='resposta[24]' id='resposta[24]' style='width: 70px;' class='textbox' maxlength='10' onKeyPress='formatData(this,event);'
											onBlur='dataErr(this,2);'></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>NR. NOTA FISCAL (TROCA)</td>
										<td class='link1'><input type='TEXT' name='resposta[25]' id='resposta[25]' style='width: 60px;' class='textbox' maxlength='6' onKeyPress='soNum(event);'></td>
										<td class='labelFicha'>DATA NF (TROCA)</td>
										<td class='link1'><input type='TEXT' name='resposta[26]' id='resposta[26]' style='width: 70px;' class='textbox' maxlength='10' onKeyPress='formatData(this,event);'
											onBlur='dataErr(this,2);'></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>ASC</td>
										<td class='link1'><select class='textbox' name='resposta[28]'>
												<option selected value='-1'>Escolha...</option>
												<option value='686'>A & V TELECOMUNICACOES LTDA</option>
												<option value='792'>A DE OLIVEIRA NETO</option>
												<option value='748'>ABAD INFO CELL</option>
												<option value='487'>ABC SAM</option>
												<option value='589'>ACAO ELETRONICA LTDA</option>
												<option value='488'>ADILSON ELETRONICA</option>
												<option value='365'>ADILSON FABRICIO GOMES</option>
												<option value='489'>ADV COM. E SER. DE PR. ELE.LTDA EPP</option>
												<option value='441'>ADVANCE EXPRESS ASS TEC LTDA</option>
												<option value='590'>AFRICA CINE FOTO</option>
												<option value='374'>AFT SABOIA</option>
												<option value='611'>AITEC AUDIO VIDEO INFORMATICA</option>
												<option value='687'>ALGO MAIS PECAS</option>
												<option value='490'>ALL TECH ELETRONICA E COMERCIO</option>
												<option value='591'>ALLSERVICE SERVICOS E EQUIP LTDA</option>
												<option value='721'>ALTIMAS</option>
												<option value='688'>ANNAYX</option>
												<option value='762'>APOLLO SOM</option>
												<option value='375'>APOLLO SOM-PETROLINA</option>
												<option value='826'>ARACAJU REFRIGERACAO</option>
												<option value='793'>ARAUJO SERVICE</option>
												<option value='666'>ARG ELETRONICA</option>
												<option value='813'>ARMAZEM PARAIBA</option>
												<option value='366'>ART CELL</option>
												<option value='827'>ARTVIDEO</option>
												<option value='1'>ASC 1</option>
												<option value='3'>ASC 2</option>
												<option value='832'>ASC TESTE 1</option>
												<option value='464'>ASSISTEC TELECOM</option>
												<option value='491'>ASSISTEL</option>
												<option value='757'>ASSISTENCIA TECNICA OMURA</option>
												<option value='788'>ASSISTENCIA TELEPHILKEN</option>
												<option value='801'>ASTEBRAS</option>
												<option value='492'>ASTEC CELL ASSIST.TEC CELU LTDA</option>
												<option value='493'>ASTECH ASSIST</option>
												<option value='592'>ASTES ASSISTENCIA TECNICA</option>
												<option value='494'>ATHENAS TELEFONIA</option>
												<option value='495'>AUDIO SOM</option>
												<option value='722'>AUTO SOM CSE</option>
												<option value='689'>BARIZON REFRIGERACAO</option>
												<option value='667'>BELASKTEL TELECOM</option>
												<option value='391'>BIT HELP INFORMATICA</option>
												<option value='723'>BLUCOLOR ASSISTENCIA TECNICA</option>
												<option value='802'>BORGES INFORMATICA</option>
												<option value='655'>BOX COPIADORAS</option>
												<option value='496'>BRASPLAN INFORMATICA</option>
												<option value='593'>CAMP LAR</option>
												<option value='648'>CANADA REFRIGERACAO</option>
												<option value='794'>CASA SAMPAIO</option>
												<option value='749'>CASTEC REFRIGERACAO LTDA</option>
												<option value='587'>CAT ELETRO</option>
												<option value='750'>CAVALCANTI ELETRONICA</option>
												<option value='690'>CEGRA</option>
												<option value='758'>CELL SHOP</option>
												<option value='751'>CELLINS</option>
												<option value='691'>CELLULAR PIONEER</option>
												<option value='795'>CELULAR & CIA</option>
												<option value='392'>CENSEL - CENTRAL DE SERV DE ELETR</option>
												<option value='724'>CENTER PANAVISION ASSIST.TEC.LTDA</option>
												<option value='612'>CENTER SON</option>
												<option value='497'>CENTERFFIL</option>
												<option value='475'>CENTERVIDEO</option>
												<option value='613'>CENTRAL AUDIO E VIDEO</option>
												<option value='614'>CENTRAL AUDIO E VIDEO</option>
												<option value='367'>CENTRAL DOS ELETROS</option>
												<option value='668'>CENTRO ELETRONICO</option>
												<option value='433'>CENTRO TEC. ELET. DUSO LTDA</option>
												<option value='763'>CENTRO TECNOLOGICO LTDA.</option>
												<option value='393'>CHAVE ELETRO</option>
												<option value='725'>CHROMA INFORMATICA</option>
												<option value='669'>CICLO2 TECNOLOGIA LTDA</option>
												<option value='670'>CISO INFORMATICA</option>
												<option value='764'>CLIMARX ART FRIO</option>
												<option value='692'>COGE</option>
												<option value='394'>COLORTEL</option>
												<option value='395'>COMCELL</option>
												<option value='754'>COMPUCELL</option>
												<option value='671'>CONDIT AIR SPRING IND COM REF LTDA</option>
												<option value='693'>CONNECT ELETRONICA</option>
												<option value='436'>DGM LTDA</option>
												<option value='656'>DIGITAL CELULAR</option>
												<option value='396'>DIGITAL ELETRONICA</option>
												<option value='476'>DIGITAL SERVICE-RIO VERDE</option>
												<option value='498'>DIGITEC ELETRONICA</option>
												<option value='499'>DIGITO CELULAR</option>
												<option value='500'>DIGITRON</option>
												<option value='376'>E G SANTANDA</option>
												<option value='397'>E&M SOM</option>
												<option value='398'>EASY CELL</option>
												<option value='765'>ECO ELETRO ELETRONICA</option>
												<option value='501'>EDSEL ELETRONICA</option>
												<option value='377'>EDY VIDEO SOM</option>
												<option value='465'>EINTEL</option>
												<option value='502'>ELBAR ELETRONICA</option>
												<option value='503'>ELECON SERVICOS AUTORIZADO</option>
												<option value='766'>ELETR NOSSA SEN DA CONCEICAO</option>
												<option value='694'>ELETROLUZ</option>
												<option value='657'>ELETROMOVEIS MARTINELLO</option>
												<option value='615'>ELETRONICA A TELEVIDEO</option>
												<option value='789'>ELETRONICA ALPHA SETE</option>
												<option value='504'>ELETRONICA AMERICA</option>
												<option value='695'>ELETRONICA ASSISTECOM</option>
												<option value='505'>ELETRONICA ASSITEC</option>
												<option value='506'>ELETRONICA AUDIO E VIDEO SERVICE</option>
												<option value='726'>ELETRONICA BARTH</option>
												<option value='767'>ELETRONICA BOMFIM</option>
												<option value='696'>ELETRONICA CALDART</option>
												<option value='616'>ELETRONICA CAMPOS</option>
												<option value='768'>ELETRONICA CAMPOS-FSA</option>
												<option value='769'>ELETRONICA CAMPOS-IRECE</option>
												<option value='697'>ELETRONICA CANSIAN</option>
												<option value='466'>ELETRONICA CENTRAL</option>
												<option value='617'>ELETRONICA COLORSOM</option>
												<option value='759'>ELETRONICA CONCORD</option>
												<option value='658'>ELETRONICA CONCORDE</option>
												<option value='618'>ELETRONICA DIEGO LTDA</option>
												<option value='672'>ELETRONICA DIGITAL</option>
												<option value='507'>ELETRONICA DONADELI</option>
												<option value='698'>ELETRONICA DUSO</option>
												<option value='399'>ELETRONICA E INFORMATICA UESUGI</option>
												<option value='619'>ELETRONICA ELETROMAR</option>
												<option value='508'>ELETRONICA ELISANGELA</option>
												<option value='699'>ELETRONICA ERECHIM</option>
												<option value='368'>ELETRONICA FABRICIO</option>
												<option value='509'>ELETRONICA GILDA</option>
												<option value='700'>ELETRONICA GOMES MOREL LTDA</option>
												<option value='510'>ELETRONICA JAMAS LTDA</option>
												<option value='511'>ELETRONICA LEMENSE</option>
												<option value='512'>ELETRONICA LUCENA</option>
												<option value='727'>ELETRONICA MANCHESTER</option>
												<option value='513'>ELETRONICA MARINHO</option>
												<option value='477'>ELETRONICA MARISTA</option>
												<option value='514'>ELETRONICA MARISTELA LTDA</option>
												<option value='378'>ELETRONICA MAXWELL</option>
												<option value='728'>ELETRONICA MORONI</option>
												<option value='673'>ELETRONICA MUNDIAL</option>
												<option value='379'>ELETRONICA MUNDIAL</option>
												<option value='515'>ELETRONICA NISHI</option>
												<option value='803'>ELETRONICA NISSEI</option>
												<option value='516'>ELETRONICA NOVE DE JULHO</option>
												<option value='659'>ELETRONICA NOVO TEMPO</option>
												<option value='814'>ELETRONICA O CORISCO</option>
												<option value='594'>ELETRONICA ONISHI</option>
												<option value='517'>ELETRONICA OZAWA</option>
												<option value='467'>ELETRONICA PANAMIX</option>
												<option value='701'>ELETRONICA PAROBE</option>
												<option value='518'>ELETRONICA PUPPIN</option>
												<option value='445'>ELETRONICA QUEOPS LTDA</option>
												<option value='519'>ELETRONICA RECORD</option>
												<option value='804'>ELETRONICA REIS</option>
												<option value='520'>ELETRONICA RENAZA</option>
												<option value='521'>ELETRONICA RO-TECH</option>
												<option value='369'>ELETRONICA ROBIER</option>
												<option value='770'>ELETRONICA ROCHA</option>
												<option value='819'>ELETRONICA SAO CARLOS</option>
												<option value='805'>ELETRONICA SASAKI</option>
												<option value='796'>ELETRONICA SATELITE</option>
												<option value='522'>ELETRONICA SIDERAL</option>
												<option value='461'>ELETRONICA SION</option>
												<option value='523'>ELETRONICA SISCAR</option>
												<option value='702'>ELETRONICA SPITZER</option>
												<option value='579'>ELETRONICA SUPERSOM</option>
												<option value='674'>ELETRONICA TELE COR</option>
												<option value='703'>ELETRONICA TENDE LTDA</option>
												<option value='704'>ELETRONICA TEVELAR LTDA</option>
												<option value='705'>ELETRONICA TRANSVIDEO</option>
												<option value='660'>ELETRONICA TV COLOR</option>
												<option value='428'>ELETRONICA TV VIDEO</option>
												<option value='524'>ELETRONICA VAILLANT - GUARATINGUETA</option>
												<option value='525'>ELETRONICA VAILLANT - SJC</option>
												<option value='526'>ELETRONICA VAILLANT - TAUBATE</option>
												<option value='706'>ELETRONICA VARGAS</option>
												<option value='771'>ELETRONICA VARJAO</option>
												<option value='675'>ELETRONICA VIDEOCOLOR</option>
												<option value='828'>ELETRONICA VIDEOSOM</option>
												<option value='527'>ELETRONICA XV</option>
												<option value='707'>ELETRONICA ZILLMER</option>
												<option value='400'>ELETRONIL</option>
												<option value='528'>ELETROREMES</option>
												<option value='772'>ELETROSANTOS</option>
												<option value='380'>ELETROTECNICA PAULISTA</option>
												<option value='529'>ELITE CELULARES</option>
												<option value='401'>EQUIPMAK</option>
												<option value='620'>ERM ELETRONICA LTDA</option>
												<option value='595'>ESC ANGELICA</option>
												<option value='446'>ESC BARRA RIO</option>
												<option value='437'>ESC BRASILIA</option>
												<option value='596'>ESC CAMPINAS</option>
												<option value='597'>ESC CAMPO BELO</option>
												<option value='447'>ESC CENTRO RIO</option>
												<option value='588'>ESC FLORIANOPOLIS</option>
												<option value='434'>ESC FORTALEZA</option>
												<option value='598'>ESC JARDINS</option>
												<option value='429'>ESC PITUBA</option>
												<option value='585'>ESC PORTO ALEGRE</option>
												<option value='582'>ESC REBOUCAS</option>
												<option value='442'>ESC RECIFE</option>
												<option value='599'>ESC TATUAPE</option>
												<option value='676'>EXCLUSIVA CELULARES</option>
												<option value='448'>EXPRESS CEL MADUREIRA</option>
												<option value='773'>FEIRA TEC</option>
												<option value='600'>FEMATEC - CENTRO DE REPARO AVANCADO</option>
												<option value='443'>FG MOREIRA RECIFE</option>
												<option value='530'>FRONTCELL ASSISTENCIA TECNICA LTDA</option>
												<option value='370'>FVL INFORMATICA LTDA</option>
												<option value='531'>GASPAR ELETRONICA</option>
												<option value='532'>GAUSS TELECOM</option>
												<option value='533'>GAUSS TELECOM - GUARULHOS</option>
												<option value='534'>GAUSS TELECOM - PIRACICABA</option>
												<option value='774'>GELASUL</option>
												<option value='708'>GEMIR CAXIAS DO SUL</option>
												<option value='435'>GERAFRIO</option>
												<option value='1218'>Global Express</option>
												<option value='601'>GLOBAL TECNO</option>
												<option value='535'>GOODTEC</option>
												<option value='536'>GOUVEA E GOUVEA CELULAR</option>
												<option value='537'>GR ASSISTENCIA TECNICA</option>
												<option value='649'>GR ELETRONICA LTDA</option>
												<option value='677'>GUERINO VARELA DA SILVA ME</option>
												<option value='729'>HELPCELL</option>
												<option value='678'>HILTON SIMIONATO & CIA LTDA</option>
												<option value='775'>HITECH ELETRONICA</option>
												<option value='402'>IGUACU EXPRESS</option>
												<option value='403'>IGUASERVICE</option>
												<option value='730'>ILHA SERVICE INFORMATICA</option>
												<option value='538'>INFOCELL CELULARES</option>
												<option value='731'>INFORMAC-SOLUCOES AVANCADAS</option>
												<option value='539'>INFORMATICA RIO PRETO</option>
												<option value='540'>INFORTRON</option>
												<option value='386'>INFOTEC COMPUTACAO</option>
												<option value='661'>INTERCOMM</option>
												<option value='776'>J A DE OLIVEIRA E CIA LTDA ME</option>
												<option value='806'>J L ELETRONICA</option>
												<option value='709'>J M COMUNICACOES</option>
												<option value='462'>JDT LOGISTICA E TRANSPORTES LTDA</option>
												<option value='621'>JOAO NUNES GUIMARAES FILHO</option>
												<option value='541'>JORGE LUIS RISSATTO ELETRONICA ME</option>
												<option value='1144'>JOTA 9 CELULARES</option>
												<option value='542'>JUND LUX</option>
												<option value='404'>JUSTEN ESPINDOLA LTDA ME</option>
												<option value='602'>K & S SERVICE</option>
												<option value='679'>K&S CASCAVEL</option>
												<option value='583'>K&S CURITIBA</option>
												<option value='680'>K&S FOZ</option>
												<option value='681'>K&S GUARAPUAVA</option>
												<option value='733'>K&S LAGES</option>
												<option value='682'>K&S PARANAGUA</option>
												<option value='683'>K&S PONTA GROSSA</option>
												<option value='732'>K&S- JOINVILLE</option>
												<option value='710'>KLEY & KLEY</option>
												<option value='711'>LIDER CENTRO TECNICO</option>
												<option value='622'>LINEAR INFORMATICA LTDA</option>
												<option value='543'>LPZIGLIO INFORMATICA</option>
												<option value='544'>LPZIGLIO INFORMATICA BAURU</option>
												<option value='405'>MAGISTRAL ELET RES AUD & VIDEO LTDA</option>
												<option value='406'>MARCA INFORMATICA E SISTEMAS</option>
												<option value='623'>MARCIA ELISA DE OLIVEIRA</option>
												<option value='760'>MARCO ZERO REFRIGERACAO</option>
												<option value='777'>MARCOS SOM</option>
												<option value='545'>MARIO VIDEO</option>
												<option value='546'>MARUYA ELETRONICA</option>
												<option value='407'>MASTER SERVICE- PETROPOLIS</option>
												<option value='603'>MATERQUIP INFORMATICA LTDA</option>
												<option value='734'>MCR CELULARES</option>
												<option value='547'>MEGA MOBILE TELECOM</option>
												<option value='548'>MEGACENTER CELULARES</option>
												<option value='549'>MIC-CENTER INFORMATICA</option>
												<option value='408'>MICRO & CIA TECNOLOGIA E INFOR</option>
												<option value='449'>MICRO FREE REFRIGERACAO</option>
												<option value='624'>MICROCITY</option>
												<option value='735'>MICROSERVICE</option>
												<option value='409'>MICROWARE</option>
												<option value='625'>MIRA INFORMATICA</option>
												<option value='550'>MONITEC ELETRONICA</option>
												<option value='551'>MULTI VIDEO ELETRONICA</option>
												<option value='552'>MULTICEL</option>
												<option value='450'>MULTIFIX INFORMATICA LTDA</option>
												<option value='712'>MULTIMARCAS CELULARES LTDA</option>
												<option value='626'>MULTIPRINTERS</option>
												<option value='478'>MULTITEC DIGITAL</option>
												<option value='627'>MUNDIAL ELETRONICA</option>
												<option value='604'>MUNDIAL SERVICE</option>
												<option value='381'>NEW CELL SERVICE - PETROLINA</option>
												<option value='553'>NIHONTEC ELETRONICA</option>
												<option value='410'>NITEROI EXCLUSIVE SERVICE</option>
												<option value='815'>NORIVELTON BEMVINDO DOS REI ME</option>
												<option value='605'>NORTE SERVICE</option>
												<option value='411'>NOVA MACAENSE</option>
												<option value='412'>NOVA S SER E PEC DE APR EL LTDA ME</option>
												<option value='554'>NOVA SERVICE</option>
												<option value='479'>NUCLEO SERVICE</option>
												<option value='778'>NUROITE ELETRONICA</option>
												<option value='468'>OFFICINA DO CELLULAR</option>
												<option value='584'>OFICINA DO FRIO CURITIBA</option>
												<option value='736'>OFICINA ELET. TELECOR LTDA</option>
												<option value='737'>OFICINDA DO FRIO</option>
												<option value='413'>OLIQUIPA</option>
												<option value='820'>P A DA SILVA JUNIOR INF - ME</option>
												<option value='755'>P R DEGELO</option>
												<option value='606'>PANAFAX VIDEOELET. LTDA.</option>
												<option value='438'>PANANORTE</option>
												<option value='371'>PC WORLD</option>
												<option value='451'>PHILSERVICE</option>
												<option value='607'>PLANUS INFORMATICA E TECNOL LTDA</option>
												<option value='452'>PLL EXPRESS</option>
												<option value='453'>POLITRON</option>
												<option value='430'>POLO NORTE REFRIGERACACAO</option>
												<option value='469'>POMPEU ELETRONICA</option>
												<option value='628'>PONTO CELL</option>
												<option value='580'>PONTO VIDEO</option>
												<option value='1085'>POWERTEC COM. E ASSIST. TEC.</option>
												<option value='608'>PRINTER</option>
												<option value='609'>PRINTER FACILITES LOC MAQ ESC LTDA</option>
												<option value='650'>PROCELL SERVICE</option>
												<option value='651'>QUALI INFORMATICA</option>
												<option value='480'>RADIBRA RADIOTECNICA</option>
												<option value='414'>RANGEL&GONCALVES CELULAR LTDA.</option>
												<option value='415'>REC VIDEO</option>
												<option value='555'>REFRIARA PECAS E SERVICOS</option>
												<option value='779'>REFRIED REFRIGERACAO - EUNPOLIS</option>
												<option value='780'>REFRIED REFRIGERACAO - ITAMARAJU</option>
												<option value='416'>REFRIGERACAO ANGRA</option>
												<option value='556'>REFRIGERACAO BARS</option>
												<option value='629'>REFRIGERACAO BASSO LTDA</option>
												<option value='481'>REFRIGERACAO CENTRO OESTE</option>
												<option value='482'>REFRIGERACAO CENTRO OESTE LTDA ME</option>
												<option value='738'>REFRIGERACAO DAMIANI</option>
												<option value='797'>REFRIGERACAO ELETRO FRANCA</option>
												<option value='630'>REFRIGERACAO ESQUIMO</option>
												<option value='557'>REFRIGERACAO GELACE</option>
												<option value='739'>REFRIGERACAO MARGEL LTDA</option>
												<option value='631'>REFRIGERACAO MILAN IND E COM LTDA</option>
												<option value='652'>REFRIGERACAO MODERNA</option>
												<option value='713'>REFRIGERA-O NARGIL</option>
												<option value='662'>REFRIGERACAO VERDILAR LTDA</option>
												<option value='558'>REFRISOM ELETRONICA</option>
												<option value='470'>REFRIZER</option>
												<option value='632'>REILLA SHOP INFORMATICA</option>
												<option value='559'>RENAZA</option>
												<option value='798'>REVIL COM E SERV LTDA</option>
												<option value='417'>RGB ELETRONICA</option>
												<option value='372'>ROBERTO GUEDES DA COSTA</option>
												<option value='471'>ROCCAS ELETRONICA E COMERCIO LTDA</option>
												<option value='472'>ROCHAS ELETRONICA</option>
												<option value='821'>RONDOTEC ELETRONICA</option>
												<option value='382'>ROSANGELA PEREIRA DA SILVA ME</option>
												<option value='383'>RS ELETRONICA</option>
												<option value='740'>S&S CELULARES LTDA</option>
												<option value='741'>S&S CELULARES LTDA.</option>
												<option value='560'>SABERPECA COM DE PECAS E PROD</option>
												<option value='431'>SANATEC LTDA</option>
												<option value='742'>SC SERVICE ITAJAI</option>
												<option value='387'>SENASTEC</option>
												<option value='610'>SENSUS MANUTENCAO</option>
												<option value='561'>SERMAQ</option>
												<option value='714'>SERRANA INFORMATICA LTDA.</option>
												<option value='781'>SERTEG - SERVICOS ELETRONICOS</option>
												<option value='782'>SERV BRAS COMERCIO LTDA</option>
												<option value='715'>SERV CENTRO</option>
												<option value='562'>SERV REFRIGERACAO</option>
												<option value='831'>SERV SUL</option>
												<option value='586'>SERVIBEM</option>
												<option value='454'>SERVICE REFRIGERACAO</option>
												<option value='418'>SERVICOS TECNICOS FALUNE LTDA</option>
												<option value='633'>SERVILAR</option>
												<option value='455'>SERVITRON SERVICOS ELETR. LTDA</option>
												<option value='456'>SERVITRONIC</option>
												<option value='563'>SERWAL SERVICO AUTORIZADO</option>
												<option value='388'>SGM COPIADORAS E IMPRESSORAS</option>
												<option value='716'>SILTEC</option>
												<option value='783'>SILVA SERVICOS ELETRONICOS LTDA</option>
												<option value='389'>SMARTCELL</option>
												<option value='564'>SNEGS ELETRONICA</option>
												<option value='790'>SOBRAL AUTORIZADAS</option>
												<option value='807'>SOCIC S/A</option>
												<option value='784'>SOFT HARD INFORMATICA</option>
												<option value='444'>SOLAR INFORMATICA LTDA ME</option>
												<option value='419'>SOLUCAO CELULARES</option>
												<option value='420'>SOLUCAO CELULARES</option>
												<option value='717'>SOM JORD</option>
												<option value='684'>SONAR ASSISTENCIA TECNICA</option>
												<option value='421'>SONS E IM CEN REP COM E S CIN LTDA</option>
												<option value='422'>SOS ELETRONICA</option>
												<option value='791'>SOS ELETRONICA</option>
												<option value='565'>SOTEL</option>
												<option value='634'>SPEED SERVICE</option>
												<option value='785'>SPEEDCELL COM.E SERV. LTDA ME</option>
												<option value='786'>STARCELL</option>
												<option value='787'>STARCELL ITABUNA</option>
												<option value='432'>STARCELL SERVICE CENTER-SSA</option>
												<option value='566'>SUCESSO REFRIGERACAO</option>
												<option value='457'>SUPORTE INFORMATICA</option>
												<option value='761'>SUPORTE INFORMATICA</option>
												<option value='743'>SUSISTEL TELECOM</option>
												<option value='567'>TANIA APARECIDA ALBUQUERQUE ME</option>
												<option value='568'>TEC CELL</option>
												<option value='569'>TEC LAR PECAS E SERVICOS LTDA</option>
												<option value='635'>TEC LINE SERVICE ELETRONICA LTDA</option>
												<option value='458'>TECBRAS</option>
												<option value='373'>TECHNISON</option>
												<option value='570'>TECHVALLE</option>
												<option value='636'>TECNACELL ASSIST. TECNICA</option>
												<option value='423'>TECNICOM INFORMATICA</option>
												<option value='718'>TECNIHOUSE ELETRONICA</option>
												<option value='719'>TECNITRON</option>
												<option value='571'>TECNO WORK ASS TEC E REFR LTDA</option>
												<option value='424'>TECNOBEL</option>
												<option value='637'>TECNOCEL - ITUIUTABA</option>
												<option value='572'>TECNOCOMPANY</option>
												<option value='829'>TECNOCOOP - ARACAJU</option>
												<option value='808'>TECNOCOOP - BELEM</option>
												<option value='638'>TECNOCOOP - BELO HORIZONTE</option>
												<option value='653'>TECNOCOOP - CAMPO GRANDE</option>
												<option value='663'>TECNOCOOP - CUIABA</option>
												<option value='639'>TECNOCOOP - JUIZ DE FORA</option>
												<option value='752'>TECNOCOOP - MACEIO</option>
												<option value='756'>TECNOCOOP - MANAUS</option>
												<option value='822'>TECNOCOOP - PORTO VELHO</option>
												<option value='640'>TECNOCOOP - POUSO ALEGRE</option>
												<option value='459'>TECNOCOOP - RIO DE JANEIRO</option>
												<option value='799'>TECNOCOOP - SAO LUIS</option>
												<option value='816'>TECNOCOOP - TERESINA</option>
												<option value='483'>TECNOCOOP GOIANIA</option>
												<option value='384'>TECNVIDEO</option>
												<option value='439'>TECTRONICA</option>
												<option value='809'>TEK CEL - BELEM</option>
												<option value='817'>TEKCELL - TERESINA</option>
												<option value='581'>TEKNICA CELULAR</option>
												<option value='425'>TELE AUDIO</option>
												<option value='426'>TELE SERVICO</option>
												<option value='824'>TELE2000</option>
												<option value='810'>TELECELL - MARAB</option>
												<option value='641'>TELECELL CELULAR</option>
												<option value='811'>TELECELL SERVICE CENTER</option>
												<option value='800'>TELECELL SERVICE CENTER</option>
												<option value='642'>TELECENTRO ELETRONICA</option>
												<option value='830'>TELECONCELL</option>
												<option value='473'>TELEMATICA INFORMATICA</option>
												<option value='818'>TELEVIDEO ELETRO ELETRONICA</option>
												<option value='463'>TELEVOX ELETRONICA</option>
												<option value='427'>THO CAR</option>
												<option value='474'>THOMAZ ELETRONICA</option>
												<option value='643'>TRANSISTEC ELETRO ELETRONICA</option>
												<option value='825'>TRONIC CELULAR</option>
												<option value='720'>TV COLLOR FETTER</option>
												<option value='654'>TV LUAR</option>
												<option value='744'>TV SOM ELETRONICA</option>
												<option value='484'>TV STATUS</option>
												<option value='745'>TVMAR ELETRONICA</option>
												<option value='644'>UNICA ELETRONICA</option>
												<option value='823'>UNICELL</option>
												<option value='664'>URSO BRANCO AR CONDICIONADO LTDA</option>
												<option value='645'>UTI DO CELULAR</option>
												<option value='573'>V IMPORTS CELULARES</option>
												<option value='753'>VCE ELETRONICA</option>
												<option value='574'>VELVET COMERCIAL LTDA</option>
												<option value='485'>VIA DIGITAL - ANAPOLIS</option>
												<option value='440'>VIA DIGITAL INFORMATICA</option>
												<option value='812'>VIDEO CHECK</option>
												<option value='646'>VIDEO COLOR ELETRONICA</option>
												<option value='685'>VIDEO FOZ ELETRONICA</option>
												<option value='486'>VIDEO SOM ELETRONICA</option>
												<option value='665'>VIDEO TEC</option>
												<option value='647'>VIDEOCENTER</option>
												<option value='460'>VIDEOMART</option>
												<option value='575'>VIDEOTAPE</option>
												<option value='576'>VIDEOTEC ELETRONICA</option>
												<option value='577'>VIDEOTEC ELETRONICA</option>
												<option value='1183'>VIP Team</option>
												<option value='1184'>VIP Team</option>
												<option value='1185'>VIP Team</option>
												<option value='1186'>VIP Team</option>
												<option value='1187'>VIP Team</option>
												<option value='1188'>VIP Team</option>
												<option value='1189'>VIP Team</option>
												<option value='1190'>VIP Team</option>
												<option value='1191'>VIP Team</option>
												<option value='1192'>VIP Team</option>
												<option value='746'>VIRTUAL SERV INFORMATICA</option>
												<option value='390'>VTEC COMERCIO E SERVICO LTDA</option>
												<option value='747'>WAPCOM TELECOMUNICACOES</option>
												<option value='385'>WORLD CELL</option>
												<option value='578'>ZETEC ABC ELETRONICA</option>
										</select></td>
										<td class='labelFicha'>ACESSRIO FALTANTE</td>
										<td class='link1'><select class='textbox' name='resposta[37]' required='yes'>
												<option selected value='-1'>Escolha...</option>
												<option value='72'>BASE DE TV</option>
												<option value='898'>BASE DO MONITOR</option>
												<option value='40'>BATERIA</option>
												<option value='42'>CABO DE DADOS (EXCETO IMPRESSORA E MONITOR)</option>
												<option value='911'>CABO DE DADOS DE IMPRESSORAS E MONITOR</option>
												<option value='899'>CABO DE FOR-A DE IMPRESSORA E MONITOR</option>
												<option value='43'>CABO DE VDEO (EXCETO IMPRESSORAS E MONITOR)</option>
												<option value='912'>CABO DE VDEO DE MONITOR</option>
												<option value='914'>CABO DE VDEO DVI (EXCETO MONITOR)</option>
												<option value='915'>CABO DE VDEO DVI DE MONITOR</option>
												<option value='41'>CABO FOR-A (EXCETO IMPRESSORA E MONITOR)</option>
												<option value='916'>CABO HDMI (EXCETO MONITOR)</option>
												<option value='917'>CABO HDMI DE MONITOR</option>
												<option value='918'>CABO USB PARA IMPRESSORA</option>
												<option value='49'>CANETA</option>
												<option value='44'>CARREGADOR</option>
												<option value='46'>CARTO DE MEMRIA</option>
												<option value='913'>CARTUCHO DE TONNER IMPRESSORAS</option>
												<option value='47'>CD</option>
												<option value='919'>CILINDRO FOTOCONDUTOR DE IMPRESSORA</option>
												<option value='71'>CONTROLE REMOTO</option>
												<option value='921'>CONTROLE REMOTO DE MONITOR</option>
												<option value='45'>FONE DE OUVIDO</option>
												<option value='51'>KIT COMPLETO OK</option>
												<option value='48'>MANUAL</option>
												<option value='50'>TODOS</option>
										</select></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>SINTOMA INFORMADO</td>
										<td class='link1'><select class='textbox' name='resposta[33]' required='yes'>
												<option selected value='-1'>Escolha...</option>
												<option value='12'>APARELHO SEM DEFEITO</option>
												<option value='69'>CAMERA NO FUNCIONA</option>
												<option value='70'>CARREGADOR COM DEFEITO</option>
												<option value='8'>FONE DE OUVIDO NO FUNCIONA</option>
												<option value='11'>LIGA MAS NO FUNCIONA</option>
												<option value='4'>NO INFORMADO</option>
												<option value='5'>NO LIGA</option>
												<option value='6'>SEM SERVI-O</option>
												<option value='9'>SEM SOM</option>
												<option value='10'>SEM TOM</option>
												<option value='1295'>TE4 (Aparelho com descoloracao)</option>
												<option value='7'>TECLADO NO FUNCIONA</option>
										</select></td>
										<td class='labelFicha'>SINTOMA CONSTATADO</td>
										<td class='link1'><select class='textbox' name='resposta[34]' required='yes'>
												<option selected value='-1'>Escolha...</option>
												<option value='15'>TA1 (nao liga)</option>
												<option value='1264'>TA2 (Desliga sozinho)</option>
												<option value='1265'>TA3 (Liga/Desliga aleatoriamente)</option>
												<option value='1266'>TA4 (Desliga intermitente)</option>
												<option value='1267'>TB1 (Sem indicacao no painel)</option>
												<option value='1268'>TB2 (Display intermitente)</option>
												<option value='1269'>TB3 (Sujeira no display)</option>
												<option value='1270'>TB4 (Pixel apagado/queimado)</option>
												<option value='1271'>TB5 (Dispaly fraco)</option>
												<option value='1272'>TB6 (Imagem distorcida)</option>
												<option value='1273'>TB8 (Lampada/LED nao funciona)</option>
												<option value='1274'>TB9 (LCD sem imagem)</option>
												<option value='1275'>TBA (Indicador de nivel de sinal nao aparece no display)</option>
												<option value='1276'>TBB (Indicacao da rede nao aparece)</option>
												<option value='1278'>TBD (Indicador de nivel de bateria nao aparece)</option>
												<option value='1279'>TBX (Outra indicacao nao funciona)</option>
												<option value='1280'>TC1 (Bateria nao carrega)</option>
												<option value='1281'>TC2 (Bateria com pouca duracao)</option>
												<option value='19'>TD1 (sem audio)</option>
												<option value='1282'>TD2 (Cai a ligacao)</option>
												<option value='1283'>TD3 (Microfonia / eco)</option>
												<option value='1284'>TD4 (Som picotado)</option>
												<option value='1285'>TD5 (Interferencia / ruido)</option>
												<option value='1286'>TD6 (Audio distorcido)</option>
												<option value='1287'>TD7 (Controle de volume nao funciona)</option>
												<option value='20'>TD8 (sem tom)</option>
												<option value='1288'>TD9 (Som muito alto)</option>
												<option value='1289'>TDA (Som fraco)</option>
												<option value='1290'>TDC (Fone de ouvido com problemas)</option>
												<option value='1291'>TDH (Falha no teclado)</option>
												<option value='1306'>TEK (Falha no folder/slide)</option>
												<option value='1307'>TEL (N- envia/recebe SMS/MMS/email)</option>
												<option value='21'>TF1 (liga mas nao funciona - travando)</option>
												<option value='16'>TF2 (sem servico)</option>
												<option value='1308'>TF3 (Nao desliga)</option>
												<option value='1309'>TF4 (Para de funcionar sozinho)</option>
												<option value='1310'>TF5 (Aparelho travando intermitente)</option>
												<option value='1311'>TF6 (Funcionamento do botao intermitente)</option>
												<option value='1312'>TF7 (Nao recebe (so transmite))</option>
												<option value='1313'>TF8 (Nao transmite (so recebe))</option>
												<option value='1314'>TF9 (Nao recebe e nao transmite)</option>
												<option value='1315'>TFA (Recepcao intermitente)</option>
												<option value='1316'>TFB (Transmissao intermitente)</option>
												<option value='1318'>TFD (Sinal fraco)</option>
												<option value='1319'>TFE (Nao disca)</option>
												<option value='1320'>TFF (Disca incorretamente as teclas)</option>
												<option value='1321'>TFG (Auto-discagem nao funciona)</option>
												<option value='1322'>TFH (Rediscagem nao funciona)</option>
												<option value='1323'>TFJ (Falha na resposta automatica)</option>
												<option value='1324'>TFK (Falha ao acessar as mensagens)</option>
												<option value='17'>TFM (falha ao fazer liga-o)</option>
												<option value='1327'>TFO (Vibrador nao funciona)</option>
												<option value='1329'>TFR (Apaga caixa postal)</option>
												<option value='1330'>TFS (Dados se alteram sozinhos)</option>
												<option value='1332'>TFV (Reset nao funciona)</option>
												<option value='1335'>TGJ (Sem comunicacao com o PC)</option>
												<option value='1336'>TGS (Foto distorcida)</option>
												<option value='1339'>TH6 (Ruido no alto falante)</option>
												<option value='1341'>TH8 (Ruido intermitente)</option>
												<option value='1342'>TJ1 (Edicao da foto nao funciona)</option>
												<option value='1343'>TJ2 (Visualizacao da foto nao funciona)</option>
												<option value='1344'>TJ3 (Flash da camera nao funciona)</option>
												<option value='1345'>TJX (Outra falha da camera)</option>
												<option value='1346'>TK2 (Cabo de dados nao funciona)</option>
												<option value='1350'>TK6 (Carregador nao funciona)</option>
												<option value='1352'>TKX (Outro acessorio com problema)</option>
												<option value='1353'>TL1 (MP3 nao funciona)</option>
												<option value='1354'>TL2 (Infravermelho nao funciona)</option>
												<option value='1355'>TL3 (Bluetooth nao funciona)</option>
												<option value='1356'>TL4 (Cartao de memoria nao funciona)</option>
												<option value='1357'>TLA (WiFi nao funciona)</option>
												<option value='1358'>TLB (Problema com a operadora)</option>
												<option value='1359'>TLX (Outra funcionalidade com problema)</option>
												<option value='1360'>TX1 (Nenhum defeito encontrado - NDF)</option>
												<option value='1364'>TX6 (Aquece demais)</option>
												<option value='1368'>TXF (Relogio n- funciona)</option>
												<option value='1369'>TXG (Nao salva dados)</option>
												<option value='1370'>TXI (Gravador nao funciona)</option>
												<option value='1371'>TXJ (Apresenta erro no display)</option>
												<option value='1372'>TXM (Nao reconhece o SIM card)</option>
												<option value='1373'>TXX (Outro problema)</option>
												<option value='13'>X01 (aparelho sem defeito)</option>
										</select></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>APARNCIA</td>
										<td class='link1' colspan='2'><select class='textbox' name='resposta[36]' required='yes'>
												<option selected value='-1'>Escolha...</option>
												<option value='24'>ACESSRIOS DANIFICADOS (FG)</option>
												<option value='25'>ACIDENTADO (FG)</option>
												<option value='26'>APARELHO AMASSADO (FG)</option>
												<option value='923'>APARELHO BLOQUEADO (FG)</option>
												<option value='39'>APARELHO CELULAR CONECTOR OXIDADO / DANIFICADO (FG)</option>
												<option value='38'>APARELHO CELULAR SEM EMBALAGEM / GIFT-BOX (FG)</option>
												<option value='37'>APARELHO COM ETIQUETA DE SERIAL DANIFICADA (FG)</option>
												<option value='36'>APARELHO SEM ETIQUETA DE SERIAL (FG)</option>
												<option value='27'>BOM (DOA)</option>
												<option value='29'>FALTANDO PECA (FG)</option>
												<option value='30'>GABINETE FRONTAL RISCADO (FG)</option>
												<option value='31'>PAINEL LCD/PDP/CDT/DISPLAY RISCADO (G)</option>
												<option value='32'>PAINEL LCD/PDP/CDT/DISPLAY TRINCADO (FG)</option>
												<option value='33'>PEQUENOS RISCOS (DOA)</option>
												<option value='34'>RISCOS PROFUNDOS (G)</option>
												<option value='933'>SEM EMBALAGEM (DOA)</option>
												<option value='35'>TAMPA QUEBRADA (FG)</option>
										</select></td>
										<td class='link1'><table width='185' border='0' cellpadding='0' cellspacing='0'>
												<tr>
													<td valign='middle' width='23'><input type='CHECKBOX' name='resposta[35]' id='resposta[35]' value='35' class='textbox'></td>
													<td valign='middle' class='link1' width='162'><b>APARELHO EST BLOQUEADO?</b></td>
												</tr>
											</table></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>NR. ORDEM SERVI-O 1</td>
										<td class='link1'><input type='TEXT' name='resposta[29]' id='resposta[29]' style='width: 130px;' class='textbox' maxlength='15'></td>
										<td class='labelFicha'>CD PE-A DA O.S. 1</td>
										<td class='link1'><input type='TEXT' name='resposta[32]' id='resposta[32]' style='width: 130px;' class='textbox' maxlength='25'></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>DATA ABERTURA O.S. 1</td>
										<td class='link1'><input type='TEXT' name='resposta[30]' id='resposta[30]' style='width: 70px;' class='textbox' maxlength='10' onKeyPress='formatData(this,event);'
											onBlur='dataErr(this,1);'></td>
										<td class='labelFicha'>DATA ENTREGA O.S. 1</td>
										<td class='link1'><input type='TEXT' name='resposta[31]' id='resposta[31]' style='width: 70px;' class='textbox' maxlength='10' onKeyPress='formatData(this,event);'
											onBlur='dataErr(this,1);'></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>NR. ORDEM SERVI-O 2</td>
										<td class='link1'><input type='TEXT' name='resposta[42]' id='resposta[42]' style='width: 130px;' class='textbox' maxlength='15'></td>
										<td class='labelFicha'>CD PE-A DA O.S. 2</td>
										<td class='link1'><input type='TEXT' name='resposta[43]' id='resposta[43]' style='width: 70px;' class='textbox' maxlength='10'></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>DATA ABERTURA O.S. 2</td>
										<td class='link1'><input type='TEXT' name='resposta[44]' id='resposta[44]' style='width: 70px;' class='textbox' maxlength='10' onKeyPress='formatData(this,event);'
											onBlur='dataErr(this,1);'></td>
										<td class='labelFicha'>DATA ENTREGA O.S. 2</td>
										<td class='link1'><input type='TEXT' name='resposta[45]' id='resposta[45]' style='width: 130px;' class='textbox' maxlength='25' onKeyPress='formatData(this,event);'
											onBlur='dataErr(this,1);'></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>NR. ORDEM SERVI-O 3</td>
										<td class='link1'><input type='TEXT' name='resposta[46]' id='resposta[46]' style='width: 130px;' class='textbox' maxlength='15'></td>
										<td class='labelFicha'>CD PE-A O.S. 3</td>
										<td class='link1'><input type='TEXT' name='resposta[47]' id='resposta[47]' style='width: 70px;' class='textbox' maxlength='10'></td>
									</tr>
									<tr align='left'>
										<td class='labelFicha'>DATA ABERTURA O.S. 3</td>
										<td class='link1'><input type='TEXT' name='resposta[48]' id='resposta[48]' style='width: 70px;' class='textbox' maxlength='10' onKeyPress='formatData(this,event);'
											onBlur='dataErr(this,1);'></td>
										<td class='labelFicha'>DATA ENTREGA O.S. 3</td>
										<td class='link1'><input type='TEXT' name='resposta[49]' id='resposta[49]' style='width: 130px;' class='textbox' maxlength='25' onKeyPress='formatData(this,event);'
											onBlur='dataErr(this,1);'></td>
									</tr>
									<tr align='left'>
										<td width='187' class='labelFicha' valign='top' style='padding-top: 3px;'>Observaes:</td>
										<td colspan="3" class='link1'><textarea id='obs' name='obs' class='textbox' style='width: 100%; height: 50px;' onKeyPress='soNumLetras(event);'></textarea></td>
									</tr>






									<tr>
										<td bgcolor="#F0F0F0" height="35" align="center" colspan="4" style="padding-top: 0px; padding-bottom: 0px;" id="teste"><input type='button' name='button_1' class='button1'
											style='width: 150px;' value='Processar Triagem' onClick='query();'></td>
									</tr>
								</table>
								<input type="hidden" name="lote_id" value="16905"> <input type="hidden" name="modelo_id" value="12369"> <input type="hidden" name="msg"
									value="Voc- deve preencher os campos indicados em vermelho."> <input type="hidden" name="faltante" id="faltante" value="0">
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
						id:757					},
		formatItem:false,	
		autoFill:true  		
	});  
	
	
	
	
	</script>
</body>
</html>

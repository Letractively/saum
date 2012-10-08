Calendar._DN = new Array
("Domingo",
 "Segunda",
 "Terca",
 "Quarta",
 "Quinta",
 "Sexta",
 "Sabado",
 "Domingo");

Calendar._SDN = new Array
("Dom",
 "Seg",
 "Ter",
 "Qua",
 "Qui",
 "Sex",
 "Sab",
 "Dom");

Calendar._FD = 0;

Calendar._MN = new Array
("Janeiro",
 "Fevereiro",
 "Marco",
 "Abril",
 "Maio",
 "Junho",
 "Julho",
 "Agosto",
 "Setembro",
 "Outubro",
 "Novembro",
 "Dezembro");

// short month names
Calendar._SMN = new Array
("Jan",
 "Fev",
 "Mar",
 "Abr",
 "Mai",
 "Jun",
 "Jul",
 "Ago",
 "Set",
 "Out",
 "Nov",
 "Dez");

// tooltips
Calendar._TT = {};
Calendar._TT["INFO"] = "Sobre o calendario";

Calendar._TT["ABOUT"] =
"Selecao de data:\n" +
"- Use os botoes \xab, \xbb para selecionar o ano\n" +
"- Use os botoes " + String.fromCharCode(0x2039) + ", " + String.fromCharCode(0x203a) + " para selecionar o mes\n" +
"- Segure o botao do mouse em qualquer um desses botoes para selecao rapida.";
Calendar._TT["ABOUT_TIME"] = "\n\n" +
"Selecao de hora:\n" +
"- Clique em qualquer parte da hora para incrementar\n" +
"- ou Shift-click para decrementar\n" +
"- ou clique e segure para selecao rapida.";

Calendar._TT["PREV_YEAR"] = "Ant. ano (segure para menu)";
Calendar._TT["PREV_MONTH"] = "Ant. mes (segure para menu)";
Calendar._TT["GO_TODAY"] = "Hoje";
Calendar._TT["NEXT_MONTH"] = "Prox. mes (segure para menu)";
Calendar._TT["NEXT_YEAR"] = "Prox. ano (segure para menu)";
Calendar._TT["SEL_DATE"] = "Selecione a data";
Calendar._TT["DRAG_TO_MOVE"] = "Clique e arraste para mover";
Calendar._TT["PART_TODAY"] = " (hoje)";

Calendar._TT["DAY_FIRST"] = "Mostre %s primeiro";

Calendar._TT["WEEKEND"] = "0,6";

Calendar._TT["CLOSE"] = "Fechar";
Calendar._TT["TODAY"] = "Hoje";
Calendar._TT["TIME_PART"] = "(Shift-)Click ou arraste para mudar valor";

// date formats
Calendar._TT["DEF_DATE_FORMAT"] = "%d-%m-%Y";
Calendar._TT["TT_DATE_FORMAT"] = "%a, %b %e";

Calendar._TT["WK"] = "SM";
Calendar._TT["TIME"] = "Hora:";

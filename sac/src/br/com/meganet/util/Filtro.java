/**
 * @author Telesforo Lacerda Pereira da Cruz.                               <br>
 * @version 1.0                                                             <br>
 *                                                                           <p>
 * Copyright: (c) 2004 - Equifax                                            <br>
 */
package br.com.meganet.util;

import java.util.Date;

public class Filtro{
    /**
     * Sufixo do nome do arquivo
     */
    private String sufixo;

    /**
     * Start point ou end point para processamento dos arquivos
     */
    private Date dataHora;

    /**
     * Prefixo do nome do arquivo
     */
    private String prefixo;

    /**
     * Indica se a data e uma Start Point (0) ou End Poin(2) ou um
     * Exact Match(1).
     */
    private int flagData;

    /**
     * Diretorio onde os arquivos devem ser recuperados
     */
    private String diretorio;

    /**
     * Construtor
     */
    public Filtro(){
    }

    /**
     * java accessor
     */
    public Date getDataHora(){
        return dataHora;
    }

    /**
     * java accessor
     */
    public String getDiretorio(){
        return diretorio;
    }

    /**
     * java accessor
     */
    public int getFlagData(){
        return flagData;
    }

    /**
     * java accessor
     */
    public String getPrefixo(){
        return prefixo;
    }

    /**
     * java accessor
     */
    public String getSufixo(){
        return sufixo;
    }

    /**
     * java accessor
     */
    public void setDataHora(Date date){
        dataHora = date;
    }

    /**
     * java accessor
     */
    public void setDiretorio(String string){
        diretorio = string;
    }

    /**
     * java accessor
     */
    public void setFlagData(int i){
        flagData = i;
    }

    /**
     * java accessor
     */
    public void setPrefixo(String string){
        prefixo = string;
    }

    /**
     * java accessor
     */
    public void setSufixo(String string){
        sufixo = string;
    }

} // fim classe Filtro

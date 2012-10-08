/**
 * Projeto: ASAP - Sistema de Reconciliacao Ativa (SRA)
 * 
 * Tipo : IConnection
 */
package br.com.meganet.telnet.connection;

import java.util.Date;
import java.util.Properties;

import org.apache.commons.net.telnet.TelnetClient;

import br.com.meganet.telnet.exception.TelnetConnectionException;



/**
 * Interface de conexao.
 * 
 * @author Jailson
 */
public interface IConnection {
    /**
     * Metodo que implementa a conexao na Gerencia.
     * 
     * @param propertiesCommParams Properties com os dados de Comunicacao.
     * @throws ConnectionException Excecao levantada caso acontera algum erro na
     *             conexao da Gerencia.
     */
    public void connect(Properties propertiesCommParams)
            throws TelnetConnectionException;

    /**
     * Metodo que implementa a desconexao na Gerencia.
     * 
     * @throws ConnectionException Excecao levantada caso acontera algum erro na
     *             conexao da Gerencia.
     */
    public void disconnect() throws TelnetConnectionException;

    /**
     * Retorna um Date contendo a data de fim da conexao.
     * 
     * @return Um Date contendo a data de fim da conexao.
     */
    public Date getEndDateConnection();

    /**
     * Retorna um Date contendo a data de inicio da conexao.
     * 
     * @return Um Date contendo a data de inicio da conexao.
     */
    public Date getBeginDateConnection();

    /**
     * Retorna um String contendo o Virtual Screen dos comandos.
     * 
     * @return Um String contendo o Virtual Screen dos comandos.
     */
    public String getVirtualScreen();

    /**
     * Inicializa o Virtual Screen.
     */
    public void resetConnection();
    
    /**
     * Metodo responsavel por retornar o TelnetClient.
     * 
     * @return TelnetClient Cliente telnet.
     */
    public TelnetClient getTelnet();
}

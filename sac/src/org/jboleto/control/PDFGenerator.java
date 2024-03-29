/*
 * Esta biblioteca e um software livre, que pode ser redistribuido e/ou
 * modificado sob os termos da Licenca Publica Geral Reduzida GNU,
 * conforme publicada pela Free Software Foundation, versao 2.1 da licenca.
 *
 * Esta biblioteca e distribuida na experanca de ser util aos seus usuarios,
 * porem NAO TEM NENHUMA GARANTIA, EXPLICITAS OU IMPLICITAS, COMERCIAIS OU
 * DE ATENDIMENTO A UMA DETERMINADA FINALIDADE.
 * Veja a Licenca Publica Geral Reduzida GNU para maiores detalhes.
 * A licenca se encontra no arquivo lgpl-br.txt
 */

package org.jboleto.control;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.text.DefaultFormatter;
import javax.swing.text.NumberFormatter;

import org.jboleto.Banco;
import org.jboleto.JBoletoBean;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.BarcodeInter25;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

/**
 *
 * Classe responsavel pela geracao dos boletos no formato PDF.
 * @author Fabio Souza
 */
public class PDFGenerator implements Generator {
    
    ByteArrayOutputStream baos;
    
    private DefaultFormatter formatter;
    
    private Document document;
    private PdfContentByte cb;
    
    public int codBanco;
    
    Image imgTitulo = null;

    //gera template com a imagem do marketing
    Image imgMarketing = null;
    
    Image imgBanco = null;
    
    /**
     * 
     * @param template Imagem de referencia para imprimir 
     */
    public PDFGenerator(JBoletoBean boleto, int codBanco) {

        this.codBanco = codBanco;
        
        baos = new ByteArrayOutputStream();
        
        formatter = new NumberFormatter(new DecimalFormat("#,##0.00"));
        
        document = new Document(PageSize.A4);
        
        try {
            
            PdfWriter writer = PdfWriter.getInstance(document, baos);
            
            document.open();
            
            cb = writer.getDirectContent();
            
            //gera template com o fundo do boleto

        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Adiciona um boleto na fila.
     */
    public void addBoleto(JBoletoBean boleto, Banco banco) {
        
        try {
                                    
            float altura = 412;
            
            document.newPage();
            
            BaseFont bfTextoSimples = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
            BaseFont bfTextoCB = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED);
            
            //inicio das descricoes do boleto
            cb.beginText();
            cb.setFontAndSize(bfTextoCB,10);
            
            if (boleto.getDescricoes() != null) {
                
                Vector<String> descricoes = boleto.getDescricoes();
                
                for (int i=0; i < descricoes.size(); i++) {
                    
                    cb.setTextMatrix(document.left(),document.top()-70+i*15);
                    cb.showText(String.valueOf(descricoes.elementAt(i)));
                }
                
                cb.endText();
            }
            
            //fim descricoes
            
            cb.beginText();
            cb.setFontAndSize(bfTextoCB,13);
            
            cb.setTextMatrix(document.left()+125,altura-87);
            
            cb.showText(banco.getNumero() + "-" + boleto.getDigitoCodigoBarras(banco.getNumero()));
            cb.endText();
            
            cb.beginText();
            cb.setFontAndSize(bfTextoSimples,8);
            
            cb.setTextMatrix(document.left()+50, altura+23);
            cb.showText(boleto.getCedente()); //imprime o cedente
            
            cb.setTextMatrix(document.left()+5,altura);
            cb.showText(boleto.getNomeSacado());
            
            cb.setTextMatrix(document.left()+230,altura);
            cb.showText(boleto.getDataVencimento());
            
            cb.setTextMatrix(document.left()+400,altura);
            cb.showText(formatter.valueToString(new Double(boleto.getValorBoleto())));
            
            // ALTERADO POR GLADYSTON
            cb.setTextMatrix(document.left()+5,altura-19);          
            cb.showText(banco.getAgenciaCodCedenteFormatted());

            cb.setTextMatrix(document.left()+5,altura-40);
            cb.showText(boleto.getNoDocumento());

            // ALTERADO POR GLADYSTON
            cb.setTextMatrix(document.left()+146,altura-19);
            cb.showText(banco.getNossoNumeroFormatted());
            cb.endText();
            
            cb.beginText();
            cb.setFontAndSize(bfTextoCB,10);
            
            cb.setTextMatrix(document.left()+175,altura-87);
            cb.showText(banco.getLinhaDigitavel());
            cb.endText();
            
            cb.beginText();
            cb.setFontAndSize(bfTextoSimples,8);
            
            cb.setTextMatrix(document.left()+5,altura-111);
            cb.showText(boleto.getLocalPagamento());
            
            cb.setTextMatrix(document.left()+5,altura-121);
            cb.showText(boleto.getLocalPagamento2());
            
            cb.setTextMatrix(document.left()+425,altura-121);
            cb.showText(boleto.getDataVencimento());
            
            cb.setTextMatrix(document.left()+5,altura-141);
            cb.showText(boleto.getCedente());
            
            // ALTERADO POR GLADYSTON
            cb.setTextMatrix(document.left()+410,altura-141);
            cb.showText(banco.getAgenciaCodCedenteFormatted());
            
            cb.setTextMatrix(document.left()+5,altura-162);
            cb.showText(boleto.getDataDocumento());
            
            cb.setTextMatrix(document.left()+70,altura-162);
            cb.showText(boleto.getNoDocumento());
            
            cb.setTextMatrix(document.left()+180,altura-162);
            cb.showText(boleto.getEspecieDocumento());
            
            cb.setTextMatrix(document.left()+250,altura-162);
            cb.showText(boleto.getAceite());
            
            cb.setTextMatrix(document.left()+300,altura-162);
            cb.showText(boleto.getDataProcessamento());
            
            // ALTERADO POR GLADYSTON
            cb.setTextMatrix(document.left()+410,altura-162);
            cb.showText(banco.getNossoNumeroFormatted());
            
            // ALTERADO POR GLADYSTON
            cb.setTextMatrix(document.left()+122,altura-185);
            cb.showText(banco.getCarteiraFormatted());
            
            cb.setTextMatrix(document.left()+200,altura-185);
            cb.showText("R$");
            
            cb.setTextMatrix(document.left()+430,altura-185);
            cb.showText(formatter.valueToString(new Double(boleto.getValorBoleto())));
            
            cb.setTextMatrix(document.left()+5,altura-207);
            cb.showText(boleto.getInstrucao1());
            
            cb.setTextMatrix(document.left()+5,altura-217);
            cb.showText(boleto.getInstrucao2());
            
            cb.setTextMatrix(document.left()+5,altura-227);
            cb.showText(boleto.getInstrucao3());
            
            cb.setTextMatrix(document.left()+5,altura-237);
            cb.showText(boleto.getInstrucao4());
            
            cb.setTextMatrix(document.left()+5,altura-247);
            cb.showText(boleto.getInstrucao5());
            
            cb.setTextMatrix(document.left()+5,altura-277);
            cb.showText(boleto.getCedente());
            
            cb.setTextMatrix(document.left()+100,altura-302);
            cb.showText(boleto.getNomeSacado() + "     " + boleto.getCpfSacado());
            
            cb.setTextMatrix(document.left()+100,altura-312);
            cb.showText(boleto.getEnderecoSacado());
            
            cb.setTextMatrix(document.left()+100,altura-322);
            cb.showText(boleto.getCepSacado() + " " + boleto.getBairroSacado() + " - " + boleto.getCidadeSacado() + " " + boleto.getUfSacado());
            
            cb.endText();
            
            BarcodeInter25 code = new BarcodeInter25();
            code.setCode(banco.getCodigoBarras());
            code.setExtended(true);
            
            code.setTextAlignment(Element.ALIGN_LEFT);
            code.setBarHeight(37.00f);
            code.setFont(null);
            code.setX(0.73f);
            code.setN(3);
            
            PdfTemplate imgCB = code.createTemplateWithBarcode(cb,null,null);
            cb.addTemplate(imgCB,40,10);
            
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }    
    
    /**
     * Fecha o documento e gera um arquivo com o caminho e o nome especificado abaixo
     * @param caminho Caminho e nome do arquivo que gostaria para gerar o pdf
     */
    public void closeBoleto(String caminho) {

        try{

            document.close();

            FileOutputStream fos = new FileOutputStream(caminho);

            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        }
        catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    /**
     * Fecha o documento e retorna o resultado em um array de bytes (mais usado em projetos web)
     * @return byte[]
     */
    public byte[] closeBoleto() {

        byte[] retorno = null;

        try {

            document.close();

            retorno = baos.toByteArray();
        }
        catch (Exception ex) {

            ex.printStackTrace();
        }

        return retorno;
    }
}

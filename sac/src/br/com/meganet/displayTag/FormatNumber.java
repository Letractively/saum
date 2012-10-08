package br.com.meganet.displayTag;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import br.com.meganet.util.Logger;

public class FormatNumber implements DisplaytagColumnDecorator{
	private static Logger logger = new Logger(FormatNumber.class);

    public Object decorate(Object columnValue, PageContext pageContext, MediaTypeEnum media) throws DecoratorException {                
        try
        {
                Object colVal = columnValue;
                if ( columnValue != null ){
                        colVal = Double.parseDouble( (String)columnValue );
                }
                return colVal;
        }catch ( Exception nfe ){}
        logger.erro("Erro ao converte numero: " + (String)columnValue);
        return columnValue;
    }
}

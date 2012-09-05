package br.com.clarotriagem.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.faces.bean.ManagedBean;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.utils.Excel;

@ManagedBean
@Scope("request")
@Component
public class RelatorioBean extends BaseBean {

	private static final long serialVersionUID = -707630952557616891L;
	private StreamedContent file;
	
	public RelatorioBean(){
        InputStream stream = null;
        try {
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			HSSFWorkbook d = Excel.outro();
			d.write(outByteStream);
        	
			stream = new ByteArrayInputStream(outByteStream.toByteArray());
            file = new DefaultStreamedContent(stream, "application/ms-excel", "Downloaded_Enastr1.xls");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

	}
	
	public StreamedContent getFile() {
		return file;
	}


	public void setFile(StreamedContent file) {
		this.file = file;
	}
	
}

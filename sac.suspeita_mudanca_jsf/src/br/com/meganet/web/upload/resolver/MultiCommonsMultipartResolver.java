package br.com.meganet.web.upload.resolver;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.fileupload.FileItem;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class MultiCommonsMultipartResolver extends CommonsMultipartResolver {
    
    public MultiCommonsMultipartResolver() {}
    
    public MultiCommonsMultipartResolver(ServletContext servletContext) {
        super(servletContext);
    }
    
    /**
     * Only one line changed which is indicated below.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    protected MultipartParsingResult parseFileItems(List fileItems, String encoding) {
        Map multipartFiles = new HashMap();
        Map multipartParameters = new HashMap();
        
        // Extract multipart files and multipart parameters.
        for (Iterator it = fileItems.iterator(); it.hasNext();) {
            FileItem fileItem = (FileItem) it.next();
            if (fileItem.isFormField()) {
                String value = null;
                if (encoding != null) {
                    try {
                        value = fileItem.getString(encoding);
                    } catch (UnsupportedEncodingException ex) {
                        value = fileItem.getString();
                    }
                } else {
                    value = fileItem.getString();
                }
                String[] curParam = (String[]) multipartParameters.get(fileItem.getFieldName());
                if (curParam == null) {
                    // simple form field
                    multipartParameters.put(fileItem.getFieldName(), new String[] { value });
                } else {
                    // array of simple form fields
                    String[] newParam = StringUtils.addStringToArray(curParam, value);
                    multipartParameters.put(fileItem.getFieldName(), newParam);
                }
            } else {
                // multipart file field
                CommonsMultipartFile file = new CommonsMultipartFile(fileItem);
                if(fileItem.getName() != null & !"".equals(fileItem.getName())){
	                if (multipartFiles.put(fileItem.getName(), file) != null) {
	                    throw new MultipartException("Multiple files for field name [" + file.getName()
	                            + "] found - not supported by MultipartResolver");
	                }
                }
            }
        }
        return new MultipartParsingResult(multipartFiles, multipartParameters);
    }
    
}
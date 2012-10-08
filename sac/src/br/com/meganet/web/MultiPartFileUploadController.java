package br.com.meganet.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import br.com.meganet.web.bean.MultiPartFileUploadBean;


public class MultiPartFileUploadController extends SimpleFormController {

    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
            BindException errors) throws Exception {

        /*
         * validate type of request and type of form backing object bean
         */
        Assert.state(request instanceof MultipartHttpServletRequest, "request !instanceof MultipartHttpServletRequest");
        Assert.state(command instanceof MultiPartFileUploadBean, "command !instanceof MultiPartFileUploadBean");

        /*
         * validate binding of text input field
         */
        MultiPartFileUploadBean bean = (MultiPartFileUploadBean) command;
        Assert.state(bean.getType().equals("multiPartFileSingle"), "type != multiPartFileSingle");

        /*
         * validate binding of uploaded file
         */
        MultipartFile file = bean.getFile();
        Assert.notNull(file, "multi part file must not be null");
        return super.onSubmit(request, response, command, errors);
    }

}
package br.com.eclinic.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter{
	
	private static final String LOGIN = "login";
	private static final String RESOURCES = "resources";
	private static final String USUARIO_LOGADO = "usuarioLogado";

	@Override
	  public boolean preHandle(HttpServletRequest request,
	      HttpServletResponse response,
	      Object controller) throws Exception {

		 String uri = request.getRequestURI();
		 String urlInicial = request.getContextPath()+"/";
 	      if(uri.endsWith(urlInicial)){
	    	  return true;
	      }
		 
 	      if(uri.contains(RESOURCES)){
 	    	  return true;
 	      }
	      
	      if(request.getSession().getAttribute(USUARIO_LOGADO) != null) {
	        return true;
	      }
	      if(uri.contains(LOGIN)){
	    	  return true;
	      }
	      response.sendRedirect(urlInicial);
	      return false;
	  }

}

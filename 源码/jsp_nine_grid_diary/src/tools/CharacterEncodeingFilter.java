package tools;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(
		urlPatterns = {"/*"},
		initParams = {
				@WebInitParam(name = "encoding", value = "UTF-8")
		})

public class CharacterEncodeingFilter implements Filter{
	protected String encoding = null;
	protected FilterConfig filterConfig = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter(encoding);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (encoding != null){
			request.setCharacterEncoding(encoding);
			response.setContentType("text/html; charset=" + encoding); 
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

}

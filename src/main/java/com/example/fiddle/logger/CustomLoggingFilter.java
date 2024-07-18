package com.example.fiddle.logger;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.Filter;
import org.springframework.stereotype.Component;

@Component
public class CustomLoggingFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    // Wrapping the request and response to capture their bodies
    CustomHttpRequestWrapper requestWrapper = new CustomHttpRequestWrapper(httpRequest);
    CustomHttpResponseWrapper responseWrapper = new CustomHttpResponseWrapper(httpResponse);

    // Proceed with the next filter in the chain
    chain.doFilter(requestWrapper, responseWrapper);

    // Logging the request body
    System.out.println("Request body: " + new String(requestWrapper.getByteArray()));

    // Logging the response body
    System.out.println("Response body: " + new String(responseWrapper.getByteArray()));

    // Copy the response content to the original response
    PrintWriter out = response.getWriter();
    out.write(new String(responseWrapper.getByteArray()));
    out.flush();
  }

  @Override
  public void destroy() {
    // Cleanup logic if needed
  }
}

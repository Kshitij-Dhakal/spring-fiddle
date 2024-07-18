package com.example.fiddle.logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

public class CustomHttpResponseWrapper extends HttpServletResponseWrapper {

  private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
  private final PrintWriter printWriter = new PrintWriter(byteArrayOutputStream);

  public CustomHttpResponseWrapper(HttpServletResponse response) {
    super(response);
  }

  @Override
  public ServletOutputStream getOutputStream() throws IOException {
    return new CustomServletOutputStream(byteArrayOutputStream);
  }

  @Override
  public PrintWriter getWriter() throws IOException {
    return printWriter;
  }

  public byte[] getByteArray() {
    return byteArrayOutputStream.toByteArray();
  }
}

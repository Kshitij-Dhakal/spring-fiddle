package com.example.fiddle.logger;

import java.io.IOException;
import java.io.InputStream;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;

public class CustomServletInputStream extends ServletInputStream {

  private final InputStream inputStream;

  public CustomServletInputStream(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  @Override
  public int read() throws IOException {
    return inputStream.read();
  }

  @Override
  public boolean isFinished() {
    try {
      return inputStream.available() == 0;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return true;
  }

  @Override
  public boolean isReady() {
    return true;
  }

  @Override
  public void setReadListener(ReadListener listener) {
    // Not implemented
  }
}

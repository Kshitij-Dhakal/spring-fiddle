package com.example.fiddle.logger;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CustomHttpRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] byteArray;

    public CustomHttpRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        InputStream inputStream = request.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, length);
        }
        this.byteArray = byteArrayOutputStream.toByteArray();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new CustomServletInputStream(new ByteArrayInputStream(byteArray));
    }

    public byte[] getByteArray() {
        return byteArray;
    }
}


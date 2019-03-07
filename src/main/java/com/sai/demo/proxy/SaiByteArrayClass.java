package com.sai.demo.proxy;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class SaiByteArrayClass extends SimpleJavaFileObject {
    private ByteArrayOutputStream byteArrayOutputStream;

    SaiByteArrayClass(String name) {
        super(URI.create("bytes:///" + name.replace('.', '/') + ".class"), Kind.CLASS);
    }

    public byte[] getCode(){
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public OutputStream openOutputStream() throws IOException{
        byteArrayOutputStream= new ByteArrayOutputStream();
        return byteArrayOutputStream;
    }
}

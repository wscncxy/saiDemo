package com.sai.demo.proxy;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

public class SaiStringSource extends SimpleJavaFileObject {

    private String code;

    SaiStringSource(String name, String code) {
        super(URI.create("string:///" + name.replace('.', '/') + ".java"), Kind.SOURCE);
        this.code = code;
    }

    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return this.code;
    }
}

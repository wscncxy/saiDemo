package com.sai.demo.proxy;

public class SaiClassLoad extends ClassLoader {
    private Iterable<SaiByteArrayClass> classeList;

    public SaiClassLoad(Iterable<SaiByteArrayClass> classeList) {
        this.classeList = classeList;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        //在List对象中查找指定的class
        for (SaiByteArrayClass byteArrayClass : classeList) {
            if (byteArrayClass.getName().equals("/" + name.replace('.', '/') + ".class")) {
                byte[] bytes = byteArrayClass.getCode();
                return defineClass(name, bytes, 0, bytes.length);
            }
        }
        throw new ClassNotFoundException(name);
    }
}
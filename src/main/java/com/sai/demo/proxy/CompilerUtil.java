package com.sai.demo.proxy;

import com.sai.demo.util.StringUtils;
import com.sun.tools.javac.util.List;

import javax.tools.*;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class CompilerUtil {


    public final static java.util.List<SaiByteArrayClass> classList = new ArrayList<>();

    public static boolean DefaultCompilter(String fileName) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        return compiler.run(null, null, null, "-sourcepath", "src", fileName.endsWith(".java") ? fileName : fileName + ".java") == 0;
    }

    public static boolean compilterStringCode(String name, String code) {
        return comilterStringCode(null, null, null, null, List.of(new SaiStringSource(name, code)));
    }

    public static boolean compilterStringCode(Map<String, String> sourceMap) {
        java.util.List<SaiStringSource> list = new ArrayList<>();
        if (sourceMap != null && !sourceMap.isEmpty()) {
            Iterator<Map.Entry<String, String>> iterator = sourceMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String name = entry.getKey();
                String code = entry.getValue();
                if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(code)) {
                    list.add(new SaiStringSource(name, code));
                }
            }
        }
        if (list.size() == 0) {
            return false;
        }

        List sourceList = List.from(list);
        return comilterStringCode(null, null, null, null, sourceList);
    }

    public static boolean compilterStringCode(List sourceList) {
        return comilterStringCode(null, null, null, null, sourceList);
    }

    public static boolean comilterStringCode(Writer out,
                                             DiagnosticListener<? super JavaFileObject> diagnosticListener,
                                             Iterable<String> options,
                                             Iterable<String> classes,
                                             Iterable<? extends JavaFileObject> compilationUnits) {
        //获取编译器
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        //获取当前编译器文件管理
        StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(null, null, null);

        //重写编译器文件管理的文件输出方式
        JavaFileManager javaFileManager = new ForwardingJavaFileManager<JavaFileManager>(standardFileManager) {
            @Override
            public JavaFileObject getJavaFileForOutput(Location location,
                                                       String className,
                                                       JavaFileObject.Kind kind, FileObject sibling)
                    throws IOException {
                //重写class字节输出到内存
                if (kind == JavaFileObject.Kind.CLASS) {
                    SaiByteArrayClass byteArrayClass = new SaiByteArrayClass(className);
                    classList.add(byteArrayClass);
                    return byteArrayClass;
                } else {
                    return super.getJavaFileForOutput(location, className, kind, sibling);
                }
            }
        };
        JavaCompiler.CompilationTask task = compiler.getTask(out, javaFileManager, diagnosticListener, options, classes, compilationUnits);
        return task.call();
    }

    public static boolean comilterCode(Writer out,
                                       JavaFileManager fileManager,
                                       DiagnosticListener<? super JavaFileObject> diagnosticListener,
                                       Iterable<String> options,
                                       Iterable<String> classes,
                                       Iterable<? extends JavaFileObject> compilationUnits) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        JavaCompiler.CompilationTask task = compiler.getTask(out, fileManager, diagnosticListener, options, classes, compilationUnits);
        return task.call();
    }

    public static java.util.List<SaiByteArrayClass> getClassList() {
        return classList;
    }
}

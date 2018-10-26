package com.sai.demo.generator;

import com.sai.demo.util.PropertiesUtils;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Generator {

    private Configuration freemarkConfiguration = null;
    List<File> fileList;
    private final String classRootPath = Generator.class.getResource("/").getPath();
    private final String templateRootPath = classRootPath + "template/";
    private final String templateOutRootPath = classRootPath + "templateOut/";
    private final String filePathTemplatePre = "FilePath:";
    private PropertiesUtils properties;
    private String templateBasePath;
    private String templateOutBasePath;
    private List<String> templateFilePathList = new ArrayList<>();
    private List<String> templateDirPathList = new ArrayList<>();

    public Generator() {
        this.properties = new PropertiesUtils();
        String templateName = properties.getProperty("template.name");
        init(null);
    }

    public Generator(String templateName) {
        this.properties = new PropertiesUtils();
        init(templateName);
    }

    public Generator(PropertiesUtils properties) {
        this.properties = properties;
        String templateName = properties.getProperty("template.name");
        init(templateName);
    }

    private void init(String templateName) {
        try {
            if (StringUtils.isBlank(templateName)) {
                throw new IOException("模板名不能为空");
            }
            //判断模板是否存在
            templateBasePath = templateRootPath + templateName;
            File templateBaseDir = new File(templateBasePath);
            if ((!templateBaseDir.exists()) || templateBaseDir.isFile()) {
                throw new IOException("模板名错误，模板不存在");
            }

            //遍历获得模板文件名
            allTemplateFileList(templateBaseDir, "", templateFilePathList, templateDirPathList);
            TemplateLoader templateLoader = new FileTemplateLoader(templateBaseDir);

            StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
            for (String templateFilePath : templateFilePathList) {
                stringTemplateLoader.putTemplate(filePathTemplatePre + templateFilePath, templateFilePath);
            }


            //创建文件输出根目录
            File templateOutRootDir = new File(templateOutRootPath);
            if (templateOutRootDir.exists()) {
                if (templateBaseDir.isFile()) {
                    throw new IOException("模板输出文件夹创建失败，存在同名文件夹");
                }
            } else {
                if (!templateOutRootDir.mkdir()) {
                    throw new IOException("模板输出文件夹创建失败");
                }
            }

            //创建模板输出文件夹
            templateOutBasePath = templateOutRootPath + templateName;
            File templateOutBaseDir = new File(templateOutBasePath);
            if (templateOutBaseDir.exists()) {
                templateOutBaseDir.renameTo(new File(templateOutBasePath + "." + System.currentTimeMillis() + "back"));
            }
            templateOutBaseDir.mkdir();


            //加载
            freemarkConfiguration = new Configuration(Configuration.VERSION_2_3_28);
            freemarkConfiguration.setDefaultEncoding("UTF-8");
            freemarkConfiguration.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);

            MultiTemplateLoader multiTemplateLoader = new MultiTemplateLoader(new TemplateLoader[]{stringTemplateLoader, templateLoader});
            freemarkConfiguration.setTemplateLoader(multiTemplateLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void process(Map<String, Object> dataMap) {
        try {
            if (templateDirPathList.size() > 0) {
                for (String dirPath : templateDirPathList) {
                    File file = new File(templateOutBasePath + "/" + dirPath);
                    file.mkdir();
                }
            }

            for (String templateFilePath : templateFilePathList) {
                Template fileTemplate = freemarkConfiguration.getTemplate(templateFilePath);
                Template filePathTemplate = freemarkConfiguration.getTemplate(filePathTemplatePre + templateFilePath);
                FileOutputStream fileOutputStream = null;
                BufferedWriter bufferedWriter = null;
                try {
                    StringWriter fileOutPathWriter = new StringWriter();
                    filePathTemplate.process(dataMap, fileOutPathWriter);
                    fileOutputStream = new FileOutputStream(templateOutBasePath + "/" + fileOutPathWriter.toString());
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "UTF-8"));
                    fileTemplate.process(dataMap, bufferedWriter);
                    bufferedWriter.flush();
                    fileOutputStream.flush();
                } catch (Exception e) {
                } finally {
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void allTemplateFileList(File file, String baseFile, List<String> filePathList, List<String> dirPathList) {
        if (file.exists() && file.isDirectory()) {
            File[] childFileArray = file.listFiles();
            if (childFileArray != null && childFileArray.length > 0) {
                for (File childFile : childFileArray) {
                    String childFilePath = baseFile + childFile.getName();
                    if (childFile.isDirectory()) {
                        dirPathList.add(childFilePath);
                        allTemplateFileList(childFile, baseFile + childFile.getName() + "/", filePathList, dirPathList);
                    } else {
                        filePathList.add(childFilePath);
                    }
                }
            }
        }
    }
}

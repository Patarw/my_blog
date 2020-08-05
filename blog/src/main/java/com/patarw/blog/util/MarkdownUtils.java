package com.patarw.blog.util;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.ext.gfm.tables.TablesExtension;

import java.util.*;


//将markdown语法转成html
public class MarkdownUtils {

    //从html中提取纯文本
    public static String getText(String strHtml) {
        String txtcontent = strHtml.replaceAll("</?[^>]+>", ""); //剔出<html>的标签
        txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");//去除字符串中的空格,回车,换行符,制表符
        return txtcontent;
    }

    //将markdown语法转成html格式
    public static String markdownToHtml(String markdown){
        Parser parser =  Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

    //拓展：标题描点，表格生成
    public static String markdownToHtmlExtensions(String markdown){
        Set<Extension> heading = Collections.singleton(HeadingAnchorExtension.create());
        List<Extension> extensions = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder()
                .extensions(extensions)
                .build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(heading)
                .extensions(extensions)
                .attributeProviderFactory(new AttributeProviderFactory() {
                    @Override
                    public AttributeProvider create(AttributeProviderContext attributeProviderContext) {
                        return new CustomAttributeProvider();
                    }
                })
                .build();
        return renderer.render(document);
    }

    //处理标签的属性
    static class CustomAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            if (node instanceof Link) {
                attributes.put("target", "_blank");
            }
            if (node instanceof TableBlock) {
                attributes.put("class", "ui celled table");
            }
        }
    }

}

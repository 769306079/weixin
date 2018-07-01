package com.winfo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.winfo.pojo.Music;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Html {
    //爬虫动态获取音乐
    public static Music getMusic(String name) {
        //  Save_Html("http://www.baidu.com","李白");
        ArrayList<String> hrefs = new ArrayList<>();
        Document htmlTextByUrl = getHtmlTextByUrl("http://baidu.9ku.com/song/" + name + "/");
        Elements li = htmlTextByUrl.select("li");
        Elements a = li.select("a");
        for (Element e : a) {
            Elements elements = e.getElementsByAttributeValue("class", "play");
            // System.out.println(elementsByAttributeValue.get(1));
            if (elements.size() > 0) {
                String href = elements.get(0).attr("href");
                System.out.println(href);
                hrefs.add(href);
            }
        }
        if (hrefs.size() == 0) {
            return null;
        }
        Document htmlTextByUrl1 = getHtmlTextByUrl(hrefs.get(0));
        Elements property = htmlTextByUrl1.getElementsByAttributeValue("property", "og:title");
        String content = property.get(0).attr("content");//歌名
        Elements property1 = htmlTextByUrl1.getElementsByAttributeValue("property", "og:music:artist");
        String content1 = property1.get(0).attr("content");//演唱者

        Random random = new Random();
        int randomInt = random.nextInt(1000);
        //String substring = hrefs.get(0).substring(hrefs.get(0).length() - 10, hrefs.get(0).length() - 4);
        Pattern r = Pattern.compile("[^/$.htm]+(?!.*/)");
        Matcher matcher = r.matcher(hrefs.get(0));
        String substring = "";
        matcher.find();
        substring = matcher.group(0);

        System.out.println(substring);
        Document htmlTextByUrl2 = getHtmlTextByUrl("http://www.9ku.com/html/playjs/" + randomInt + "/" + substring + ".js");
        String text = htmlTextByUrl2.text();
        JSONObject jsonObject = JSON.parseObject(text.substring(1, text.length() - 1));
        String wma = jsonObject.getString("wma");//音乐链

        Music music = new Music();
        music.setTitle(content);
        music.setDescription(content1);
        music.setMusicURL(wma);
        music.setHQMusicUrl(wma);
        return music;

    }

    //根据url从网络获取网页文本
    public static Document getHtmlTextByUrl(String url) {
        Document doc = null;
        try {
            //doc = Jsoup.connect(url).timeout(5000000).get();
            int i = (int) (Math.random() * 1000); //做一个随机延时，防止网站屏蔽
            while (i != 0) {
                i--;
            }
            doc = Jsoup.connect(url).data("query", "Java")
                    .userAgent("Mozilla")
                    .timeout(300000).get();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                doc = Jsoup.connect(url).timeout(5000000).get();
            } catch (IOException e1) { // TODO Auto-generated catch block  e1.printStackTrace(); } }

                return doc;
            }
        }
        return doc;
    }
//根据本地路径获取网页文本，如果不存在就通过url从网络获取并保存

    public Document getHtmlTextByPath(String name, String url) {
        String path = "D:/Html/" + name + ".html";
        Document doc = null;
        File input = new File(path);
        String urlcat = url;
        try {
            doc = Jsoup.parse(input, "GBK");
            if (!doc.children().isEmpty()) {
                doc = null;
                System.out.println("已经存在");
            }
        } catch (IOException e) {
            System.out.println("文件未找到，正在从网络获取.......");
            doc = getHtmlTextByUrl(url);
            //并且保存到本地
            this.Save_Html(url, name);
        }
        return doc;
    }  //此处为保存网页的函数

    //将网页保存在本地（通过url,和保存的名字）
    public void Save_Html(String url, String name) {
        try {
            name = name + ".html";
            // System.out.print(name);
            File dest = new File("D:/Html/" + name);//D:\Html
            //接收字节输入流
            InputStream is;
            //字节输出流
            FileOutputStream fos = new FileOutputStream(dest);

            URL temp = new URL(url);
            is = temp.openStream();

            //为字节输入流加缓冲
            BufferedInputStream bis = new BufferedInputStream(is);
            //为字节输出流加缓冲
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            int length;

            byte[] bytes = new byte[1024 * 20];
            while ((length = bis.read(bytes, 0, bytes.length)) != -1) {
                fos.write(bytes, 0, length);
            }


            bos.close();
            fos.close();
            bis.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
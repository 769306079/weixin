package com.winfo.util;

import com.winfo.pojo.Music;
import com.winfo.pojo.WeChatRespBean;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息处理工具类
 * Created by xdp on 2016/1/26.
 */
public class MessageHandlerUtil {


    /**
     * 解析微信发来的请求（XML）
     *
     * @param request 封装了请求信息的HttpServletRequest对象
     * @return map 解析结果
     * @throws Exception
     */
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList) {
            System.out.println(e.getName() + "|" + e.getText());
            map.put(e.getName(), e.getText());
        }

        // 释放资源
        inputStream.close();
        inputStream = null;
        return map;
    }


    public static WeChatRespBean returnMusic(String content, String type) {
        if (content.length() > 3 && content.substring(0, 3).equals("音乐 ") || type.equals("2")) {
            String name = content.substring(3, content.length());

            if (type.equals("2")) {
                name = content.substring(0, content.length() - 1);
            }
            name = name.replace(" ", "");
            if (name.equals("") || name == null) {

                WeChatRespBean respBean = new WeChatRespBean();
                respBean.setMsgType("text");
                respBean.setContent("对不起，未查询到此音乐!");

                return respBean;
            }
            System.out.println(name);


            Music music = Html.getMusic(name.replace(" ", ""));
            // System.out.println(content.substring(3,content.length()));
            if (music == null) {
                WeChatRespBean respBean = new WeChatRespBean();
                respBean.setMsgType("text");
                respBean.setContent("对不起，未查询到此音乐!");

                return respBean;
            }

            WeChatRespBean respBean = new WeChatRespBean();
            respBean.setMusic(music);
            respBean.setMsgType("music");

            return respBean;

        } else {
            WeChatRespBean respBean = new WeChatRespBean();
            respBean.setMsgType("text");
            respBean.setContent("感谢您关注我的个人公众号，请回复如下关键词来使用公众号提供的服务：\n音乐 +歌名  如：音乐 新贵妃醉酒");

            return respBean;
        }
    }


    /**
     * 生成消息创建时间 （整型）
     *
     * @return 消息创建时间
     */
    private static Long getMessageCreateTime() {
        Date dt = new Date();// 如果不需要格式,可直接用dt,dt就是当前系统时间
        DateFormat df = new SimpleDateFormat("yyyyMMddhhmm");// 设置显示格式
        String nowTime = df.format(dt);
        long dd = (long) 0;
        try {
            dd = df.parse(nowTime).getTime();
        } catch (Exception e) {

        }
        return dd;
    }
}

package com.winfo.web;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;
import com.winfo.pojo.Voice;
import com.winfo.pojo.WeChatReqBean;
import com.winfo.pojo.WeChatRespBean;
import com.winfo.util.MessageHandlerUtil;
import com.winfo.util.WeiXinUtlis;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.Date;

@Controller
public class WeiXinController {

    @ResponseBody
    @RequestMapping(value = "/weixin", method = RequestMethod.GET)
    public String weiChatGet(String signature, String timestamp, String nonce, String echostr) {
        if (signature != null && timestamp != null && nonce != null) {
            boolean b = WeiXinUtlis.checkSignature(signature, timestamp, nonce);
            if (b) {
                System.out.println("---");
                return echostr;
            }
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/weixin", method = RequestMethod.POST)
    public void weiChatPost(HttpServletRequest request, HttpServletResponse response, String echostr) throws IOException {
        response.setContentType("application/xml");
        PrintWriter out = response.getWriter();

        StringBuffer sb = new StringBuffer();
        ServletInputStream is = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(isr);

        String s = "";
        while ((s = br.readLine()) != null) {
            sb.append(s);
        }
        String xml = sb.toString();
        JAXBContext jc;
        try {
            jc = JAXBContext.newInstance(WeChatReqBean.class);

            Unmarshaller u = jc.createUnmarshaller();

            WeChatReqBean reqBean = (WeChatReqBean) u.unmarshal(new StringReader(xml));

            System.out.println(reqBean);
            String content = null;
            if (echostr != null && echostr.length() > 1) {
                content = echostr;
            } else {
                content = reqBean.getRecognition();
            }

            jc = JAXBContext.newInstance(WeChatRespBean.class);

            Marshaller m = jc.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.setProperty(CharacterEscapeHandler.class.getName(), new CharacterEscapeHandler() {
                @Override
                public void escape(char[] chars, int i, int i1, boolean b, Writer writer) throws IOException {
                    writer.write(chars, i, i1);
                }
            });
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            Voice voice = new Voice();
            voice.setMediaId(reqBean.getMediaId());

            //获取消息类型
            String msgType = reqBean.getMsgType();

            WeChatRespBean respBean = null;
            if (msgType.equals("text")) {
                respBean = MessageHandlerUtil.returnMusic(reqBean.getContent(), "1");
            } else if (msgType.equals("voice")) {
                respBean = MessageHandlerUtil.returnMusic(content, "2");
                //  respBean = createRespBean(reqBean, content);
            }
            respBean.setFromUserName(reqBean.getToUserName());
            respBean.setToUserName(reqBean.getFromUserName());
            respBean.setCreateTime(new Date().getTime());

            // StringWriter   writer = new StringWriter();

            m.marshal(respBean, out);

/*            m.marshal(respBean, writer);
            System.out.println(writer.toString());*/

            out.flush();

        } catch (JAXBException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
                out = null;
            }
        }


    }

    /**
     * 发送文本消息
     *
     * @param reqBean
     * @param content
     * @return
     */
    private WeChatRespBean createRespBean(WeChatReqBean reqBean, String content) {
        WeChatRespBean respBean = new WeChatRespBean();
        respBean.setFromUserName(reqBean.getToUserName());
        respBean.setToUserName(reqBean.getFromUserName());
        respBean.setMsgType("text");
        respBean.setCreateTime(new Date().getTime());
        respBean.setContent(content);
        return respBean;
    }
}

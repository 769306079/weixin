package com.winfo.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "xml")
public class WeChatRespBean {
    private String toUserName;// 开发者微信号

    private String fromUserName;// 发送方帐号（一个OpenID）

    private Long createTime;// 消息创建时间

    private String msgType;// 消息类型

    private String content;// 文本消息内容

    private Voice voice;// 语音消息内容

    private Integer funcFlag = 0;

    private Music music;

    public String getToUserName() {
        return toUserName;
    }

    @XmlElement(name = "ToUserName")
    @XmlJavaTypeAdapter(value = CDATAdapter.class)
    // 表示此文本数据不由XML解析器进行解析。
    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    @XmlElement(name = "FromUserName")
    @XmlJavaTypeAdapter(value = CDATAdapter.class)
    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    @XmlElement(name = "CreateTime")
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    @XmlElement(name = "MsgType")
    @XmlJavaTypeAdapter(value = CDATAdapter.class)
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    @XmlElement(name = "Content")
    @XmlJavaTypeAdapter(value = CDATAdapter.class)
    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFuncFlag() {
        return funcFlag;
    }

    @XmlElement(name = "FuncFlag")
    public void setFuncFlag(Integer funcFlag) {
        this.funcFlag = funcFlag;
    }

    public Voice getVoice() {
        return voice;
    }

    @XmlElement(name = "Voice")
    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    public Music getMusic() {
        return music;
    }

    @XmlElements({
            @XmlElement(name = "Music", type = Music.class),
// @XmlElement(name = "adInfo", type = AdInfo.class)一个集合可以放置多个xml元素名称和对象类型、在xml中是相互独立的集合元素，无包含关系
    })
    public void setMusic(Music music) {
        this.music = music;
    }

    @Override
    public String toString() {
        return "WeChatRespBean{" +
                "toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime=" + createTime +
                ", msgType='" + msgType + '\'' +
                ", content='" + content + '\'' +
                ", voice=" + voice +
                ", funcFlag=" + funcFlag +
                ", music=" + music +
                '}';
    }
}

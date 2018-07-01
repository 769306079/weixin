package com.winfo.pojo;

import com.sun.xml.internal.txw2.annotation.XmlCDATA;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "xml")
public class WeChatReqBean {
    private String toUserName;
    private String fromUserName;
    private Long createTime;
    private String msgType;
    private String content;
    private String recognition;

    private Double location_X;
    private Double location_Y;
    private Integer scale;
    private String label;

    private String mediaId;
    private String format;

    private Long msgId;

    public String getToUserName() {
        return toUserName;
    }

    @XmlCDATA
    @XmlElement(name = "ToUserName")
    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    @XmlElement(name = "FromUserName")
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

    @XmlCDATA
    @XmlElement(name = "MsgType")
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    @XmlCDATA
    @XmlElement(name = "Content")
    public void setContent(String content) {
        this.content = content;
    }

    public Double getLocation_X() {
        return location_X;
    }

    @XmlElement(name = "Location_X")
    public void setLocation_X(Double location_X) {
        this.location_X = location_X;
    }

    public Double getLocation_Y() {
        return location_Y;
    }

    @XmlElement(name = "Location_Y")
    public void setLocation_Y(Double location_Y) {
        this.location_Y = location_Y;
    }

    public Integer getScale() {
        return scale;
    }

    @XmlElement(name = "Scale")
    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    @XmlCDATA
    @XmlElement(name = "Label")
    public void setLabel(String label) {
        this.label = label;
    }

    public Long getMsgId() {
        return msgId;
    }

    @XmlElement(name = "MsgId")
    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public String getMediaId() {
        return mediaId;
    }

    @XmlCDATA
    @XmlElement(name = "MediaId")
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    @XmlCDATA
    @XmlElement(name = "Format")
    public void setFormat(String format) {
        this.format = format;
    }

    public String getRecognition() {
        return recognition;
    }

    @XmlCDATA
    @XmlElement(name = "Recognition")
    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    @Override
    public String toString() {
        return "WeChatReqBean [toUserName=" + toUserName + ", fromUserName="
                + fromUserName + ", createTime=" + createTime + ", msgType="
                + msgType + ", content=" + content + ", recognition="
                + recognition + ", location_X=" + location_X + ", location_Y="
                + location_Y + ", scale=" + scale + ", label=" + label
                + ", mediaId=" + mediaId + ", format=" + format + ", msgId="
                + msgId + "]";
    }

}

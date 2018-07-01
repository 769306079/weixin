package com.winfo.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * 音乐消息
 */
@XmlRootElement(name = "Music")
public class Music {
    /*private String ToUserName;//接收方帐号（收到的OpenID）
    private String FromUserName;//开发者微信号
    private String CreateTime;//消息创建时间 （整型）
    private String MsgType;//music*/
    private String Title;//音乐标题
    private String Description;//音乐描述
    private String MusicURL;//音乐链接
    private String HQMusicUrl;//	高质量音乐链接，WIFI环境优先使用该链接播放音乐
    private String ThumbMediaId;//缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id

/*    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }*/

    public String getTitle() {
        return Title;
    }

    @XmlElement(name = "Title")
    @XmlJavaTypeAdapter(value = CDATAdapter.class)
    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    @XmlElement(name = "Description")
    @XmlJavaTypeAdapter(value = CDATAdapter.class)
    public void setDescription(String description) {
        Description = description;
    }

    public String getMusicURL() {
        return MusicURL;
    }

    @XmlElement(name = "MusicUrl")
    @XmlJavaTypeAdapter(value = CDATAdapter.class)
    public void setMusicURL(String musicURL) {
        MusicURL = musicURL;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    @XmlElement(name = "HQMusicUrl")
    @XmlJavaTypeAdapter(value = CDATAdapter.class)
    public void setHQMusicUrl(String HQMusicUrl) {
        this.HQMusicUrl = HQMusicUrl;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    @XmlElement(name = "ThumbMediaId")
    @XmlJavaTypeAdapter(value = CDATAdapter.class)
    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

    @Override
    public String toString() {
        return "Music{" +
                ", Description='" + Description + '\'' +
                ", MusicURL='" + MusicURL + '\'' +
                ", HQMusicUrl='" + HQMusicUrl + '\'' +
                ", ThumbMediaId='" + ThumbMediaId + '\'' +
                '}';
    }
}

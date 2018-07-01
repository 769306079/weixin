package com.winfo.pojo;

import com.sun.xml.internal.txw2.annotation.XmlCDATA;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Voice")
public class Voice {
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    @XmlCDATA
    @XmlElement(name = "MediaId")
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}

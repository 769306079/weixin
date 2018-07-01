package com.winfo.pojo;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * 不进行XML解析
 *
 * @author rkcoe
 */
public class CDATAdapter extends XmlAdapter<String, String> {

    @Override
    public String unmarshal(String v) {
        return v;
    }

    @Override
    public String marshal(String v) {
        return "<![CDATA[" + v + "]]>";
    }

}

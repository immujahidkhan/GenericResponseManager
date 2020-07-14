
package com.genericresponseexample;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Datum implements Serializable {

    @SerializedName("lang_id")
    @Expose
    private Integer langId;
    @SerializedName("lang_name")
    @Expose
    private String langName;
    @SerializedName("lang_code")
    @Expose
    private String langCode;
    @SerializedName("lang_is_rtl")
    @Expose
    private Integer langIsRtl;
    private final static long serialVersionUID = 5739606646972282625L;

    public Integer getLangId() {
        return langId;
    }

    public void setLangId(Integer langId) {
        this.langId = langId;
    }

    public String getLangName() {
        return langName;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public Integer getLangIsRtl() {
        return langIsRtl;
    }

    public void setLangIsRtl(Integer langIsRtl) {
        this.langIsRtl = langIsRtl;
    }

    @Override
    public String toString() {
        return "Datum{" +
                "langId=" + langId +
                ", langName='" + langName + '\'' +
                ", langCode='" + langCode + '\'' +
                ", langIsRtl=" + langIsRtl +
                '}';
    }
}
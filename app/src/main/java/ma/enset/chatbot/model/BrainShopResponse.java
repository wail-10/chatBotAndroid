package ma.enset.chatbot.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BrainShopResponse implements Serializable {
    @SerializedName("cnt")
    private String cnt;

    public BrainShopResponse() {
    }

    public BrainShopResponse(String cnt) {
        this.cnt = cnt;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }
}

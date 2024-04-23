package ma.enset.chatbot.api;

import ma.enset.chatbot.model.BrainShopResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface BrainShopAPI {
    @GET
    Call<BrainShopResponse> getMessage(@Url String url);
}

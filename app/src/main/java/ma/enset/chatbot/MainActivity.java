package ma.enset.chatbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import ma.enset.chatbot.adapters.ChatBotAdapter;
import ma.enset.chatbot.api.BrainShopAPI;
import ma.enset.chatbot.model.BrainShopResponse;
import ma.enset.chatbot.model.MessageModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<MessageModel> messages = new ArrayList<>();
    private EditText messageEditText;
    private Button sendButton;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageEditText = findViewById(R.id.edit_text_message);
        sendButton = findViewById(R.id.button_send);
        recyclerView = findViewById(R.id.recycler_view);

        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.brainshop.ai/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        BrainShopAPI brainShopAPI = retrofit.create(BrainShopAPI.class);
        ChatBotAdapter chatBotAdapter = new ChatBotAdapter(messages, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setAdapter(chatBotAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        sendButton.setOnClickListener(v -> {
            String message = messageEditText.getText().toString();
            messages.add(new MessageModel(message, "user"));
            chatBotAdapter.notifyDataSetChanged();
            String url = "http://api.brainshop.ai/get?bid=181515&key=hkOkjaQHwFagxouY&uid=[uid]&msg="+message;
            Call<BrainShopResponse> response = brainShopAPI.getMessage(url);
            messageEditText.setText("");
            response.enqueue(new Callback<BrainShopResponse>() {
                @Override
                public void onResponse(Call<BrainShopResponse> call, Response<BrainShopResponse> response) {
                    Log.i("info", response.body().getCnt());
                    messages.add(new MessageModel(response.body().getCnt(), "bot"));
                    chatBotAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<BrainShopResponse> call, Throwable throwable) {
                    Log.e("error", throwable.getMessage());
                }
            });
        });
    }
}
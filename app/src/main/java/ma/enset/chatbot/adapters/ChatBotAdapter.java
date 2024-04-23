package ma.enset.chatbot.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ma.enset.chatbot.MainActivity;
import ma.enset.chatbot.R;
import ma.enset.chatbot.model.MessageModel;

public class ChatBotAdapter extends RecyclerView.Adapter {
    private List<MessageModel> messages;
    private Context context;

    public ChatBotAdapter() {
    }

    public ChatBotAdapter(List<MessageModel> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.user_msg_item, parent, false);
                return new UserMessageViewHolder(view);
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.bot_msg_item, parent, false);
                return new BotMessageViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        String sender = messages.get(position).getSender();
        switch (sender) {
            case "user":
                return 0;
            case "bot":
                return 1;
            default: return -1;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel message = messages.get(position);
        switch (message.getSender()) {
            case "user":
                ((UserMessageViewHolder) holder).messageTextView.setText(message.getMessage());
                break;
            case "bot":
                ((BotMessageViewHolder) holder).messageTextViewBot.setText(message.getMessage());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class UserMessageViewHolder extends RecyclerView.ViewHolder {
        TextView messageTextView;
        public UserMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.messageTextView = itemView.findViewById(R.id.tvMsgUser);
        }
    }

    public static class BotMessageViewHolder extends RecyclerView.ViewHolder {
        TextView messageTextViewBot;
        public BotMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.messageTextViewBot = itemView.findViewById(R.id.tvMsgBot);
        }
    }
}

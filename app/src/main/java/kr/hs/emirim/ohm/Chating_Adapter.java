package kr.hs.emirim.ohm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Chating_Adapter extends RecyclerView.Adapter<Chating_Adapter.ViewHolder> {
    private List<Chating_Data> mDataset;
    private String mNickName;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView chat_nickname; //닉네임 
        private TextView chat_msg; //채팅 내용
        private View rootView;

        public ViewHolder(ConstraintLayout v) {
            super(v);
            chat_msg = v.findViewById(R.id.chat_msg);
            chat_nickname = v.findViewById(R.id.chat_nickname);
            rootView = v;
        }
    }

    public Chating_Adapter(List<Chating_Data> MyDataSet, Context context, String mNickName) {
        mDataset = MyDataSet;
        this.mNickName = mNickName;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Chating_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_chat, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Chating_Data chat = mDataset.get(position);

        holder.chat_nickname.setText(chat.getNickname());
        holder.chat_msg.setText(chat.getMsg()); //DTD

        if(chat.getNickname().equals(this.mNickName)){
            holder.chat_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            holder.chat_nickname.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END); //내가 채팅을 할 경우 오른쪽

        }else{
            holder.chat_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.chat_nickname.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START); //상대방이 채팅을 할 경우 왼쪽
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }
    public Chating_Data getChat(int positon){
      return mDataset != null ? mDataset.get(positon) : null;
    }
    public void addChat(Chating_Data chat){
        mDataset.add(chat);
        notifyItemInserted(mDataset.size()-1); //갱신
    }
}

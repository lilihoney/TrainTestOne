package test.up.day.day.com.daytest.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import test.up.day.day.com.daytest.MainActivity;
import test.up.day.day.com.daytest.R;

/**
 * Created by Administrator on 2017/10/9.
 */


public class TargetAdapter extends RecyclerView.Adapter<TargetAdapter.ViewHolder>{
    public MainActivity.TargetClassDemo[] data = null;
    private TargetAdapter.OnItemClickListener onItemClickListener;

    public TargetAdapter(MainActivity.TargetClassDemo[] data){
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    @Override
    public TargetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final TargetAdapter.ViewHolder holder, final int position) {
        holder.mTextView.setText(data[position].getTargetName());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(holder.mTextView, position);
                }
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ViewHolder(View view){
            super(view);
            mTextView = view.findViewById(R.id.text);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
}


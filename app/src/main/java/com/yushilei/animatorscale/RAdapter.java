package com.yushilei.animatorscale;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by  yushilei.
 * @time 2016/9/8 -11:19.
 * @Desc
 */
public class RAdapter extends RecyclerView.Adapter<RAdapter.VH> implements View.OnClickListener {
    List<String> data = new ArrayList<>();
    Context mContext;

    public RAdapter(Context context) {
        mContext = context;
        for (int i = 0; i < 40; i++) {
            data.add("item+" + i);
        }
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        VH tag = new VH(view);
        view.setTag(tag);
        view.setOnClickListener(this);
        return tag;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.setPos(position);
        holder.tv.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        int pos = ((VH) v.getTag()).getPos();
        Toast.makeText(mContext, data.get(pos), Toast.LENGTH_SHORT).show();
    }

    public static class VH extends RecyclerView.ViewHolder {
        private TextView tv;
        int pos;

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }

        public VH(View itemView) {
            super(itemView);
            tv = ((TextView) itemView.findViewById(R.id.item_tv));
        }
    }
}

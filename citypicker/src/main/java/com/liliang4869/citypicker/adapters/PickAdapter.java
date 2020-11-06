package com.liliang4869.citypicker.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.liliang4869.citypicker.CommonUtil;
import com.liliang4869.citypicker.databinding.PickItemBinding;

import java.util.List;

public class PickAdapter extends RecyclerView.Adapter<PickAdapter.MyViewHolder> {
    private List objectNameCallbackList;
    private CommonUtil.ObjectNameCallback selected;
    private PickAdapterListener pickAdapterListener;
    public PickAdapter(List objectNameCallbackList, CommonUtil.ObjectNameCallback selected) {
        this.objectNameCallbackList = objectNameCallbackList;
        this.selected = selected;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PickItemBinding pickItemBinding=PickItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(pickItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      final Object o=objectNameCallbackList.get(position);
        if(o instanceof CommonUtil.ObjectNameCallback){
            CommonUtil.ObjectNameCallback objectNameCallback=(CommonUtil.ObjectNameCallback) o;
            holder.pickItemBinding.text.setText(objectNameCallback.getObjectName());
            holder.pickItemBinding.text.setTextColor((selected!=null&&((CommonUtil.ObjectNameCallback) o).getObjectName().equals(selected.getObjectName()))?0xff0199ff:0xff333333);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!o.equals(selected) &&pickAdapterListener!=null){
                    pickAdapterListener.onItemClick((CommonUtil.ObjectNameCallback) o);
                }
                selected=(CommonUtil.ObjectNameCallback) o;
                notifyDataSetChanged();
            }
        });
        }
    }

    public CommonUtil.ObjectNameCallback getSelected() {
        return selected;
    }

    public void setSelected(CommonUtil.ObjectNameCallback selected) {
        this.selected = selected;
        notifyDataSetChanged();
    }
public int getSelectedIndex(){
        int index=objectNameCallbackList.indexOf(selected);
        return Math.max(index,0);
}
    @Override
    public int getItemCount() {
        return objectNameCallbackList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private PickItemBinding pickItemBinding;
        public MyViewHolder(PickItemBinding pickItemBinding) {
            super(pickItemBinding.getRoot());
            this.pickItemBinding=pickItemBinding;
        }
    }

    public PickAdapterListener getPickAdapterListener() {
        return pickAdapterListener;
    }

    public void setPickAdapterListener(PickAdapterListener pickAdapterListener) {
        this.pickAdapterListener = pickAdapterListener;
    }

    public interface PickAdapterListener{
        void onItemClick(CommonUtil.ObjectNameCallback o);
    }
}

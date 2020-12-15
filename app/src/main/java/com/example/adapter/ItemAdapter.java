package com.example.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beacon.R;
import com.example.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> ItemList;
    private Context context;

    public ItemAdapter(Context context, List<Item> dataList) {
        this.context = context;
        this.ItemList = dataList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.store_item, parent, false);
        return new ItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item i = ItemList.get(position);
        holder.tv_itemName.setText(i.getItemName());
        holder.tv_itemDiscount.setText("Discount: " +i.getDiscount() +"%");
        holder.tv_itemPrice.setText("Price: "+i.getPrice());
        holder.tv_itemRegion.setText("Region: " +i.getRegion());
        Picasso.get()
                .load(i.getItemPhoto()).fit()
                .placeholder(R.drawable.item_placeholder)
                .error(R.drawable.item_placeholder)
                .into(holder.iv_itemImage);
    }

    @Override
    public int getItemCount() {
        return ItemList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        ImageView iv_itemImage;
        TextView tv_itemName;
        TextView tv_itemPrice;
        TextView tv_itemDiscount;
        TextView tv_itemRegion;


        public ItemViewHolder(View view) {
            super(view);
            this.mView = view;

            tv_itemName = this.mView.findViewById(R.id.item_name);
            tv_itemDiscount = this.mView.findViewById(R.id.item_discount);
            tv_itemPrice = this.mView.findViewById(R.id.item_price);
            iv_itemImage = this.mView.findViewById(R.id.item_iv);
            tv_itemRegion = this.mView.findViewById(R.id.item_region);
        }
    }
}

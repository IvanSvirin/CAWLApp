/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.cawlapp.cawlapp.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cawlapp.cawlapp.R;
import com.cawlapp.cawlapp.presentation.model.DealModel;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Adapter that manages a collection of {@link DealModel}.
 */
public class DealAdapter extends RecyclerView.Adapter<DealAdapter.DealsViewHolder> {
    private Picasso picasso;
    public interface OnItemClickListener {
        void onDealItemClicked(DealModel dealModel);
    }

    private List<DealModel> dealsCollection;
    private final LayoutInflater layoutInflater;

    private OnItemClickListener onItemClickListener;

    @Inject
    public DealAdapter(Context context) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.dealsCollection = Collections.emptyList();
        picasso = Picasso.with(context);
    }

    @Override
    public int getItemCount() {
        return (this.dealsCollection != null) ? this.dealsCollection.size() : 0;
    }

    @Override
    public DealsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.item_deals, parent, false);
        return new DealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DealsViewHolder holder, final int position) {
        final DealModel dealModel = this.dealsCollection.get(position);

        picasso.load(dealModel.getVendorLogoUrl()).into(holder.mLogo);

        holder.mCashbackType.setText(dealModel.getCouponType().toUpperCase());
        holder.mDesctiption.setText(dealModel.getLabel());

        String incomeDate = dealModel.getExpirationDate();
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = format.parse(incomeDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String stringDate = String.format("%tm/%<td/%<tY", date);
        holder.mExp.setText("Exp. " + stringDate);
        holder.mCashBack.setText(String.valueOf(dealModel.getVendorCommission()));

        holder.mCashBack.setText(("+ " + dealModel.getVendorCommission() + "% CASH BACK").toUpperCase());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DealAdapter.this.onItemClickListener != null) {
                    DealAdapter.this.onItemClickListener.onDealItemClicked(dealModel);
                }
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setDealsCollection(Collection<DealModel> dealsCollection) {
        this.validateDealsCollection(dealsCollection);
        this.dealsCollection = (List<DealModel>) dealsCollection;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void validateDealsCollection(Collection<DealModel> dealsCollection) {
        if (dealsCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    static class DealsViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.fragment_hot_deals_logo)
        ImageView mLogo;
        @Bind(R.id.fragment_hot_deals_cashback_type)
        TextView mCashbackType;
        @Bind(R.id.fragment_hot_deals_description)
        TextView mDesctiption;
        @Bind(R.id.fragment_hot_deals_exp)
        TextView mExp;
        @Bind(R.id.fragment_hot_deals_cash_back)
        TextView mCashBack;
        @Bind(R.id.fragment_hot_deals_coupon)
        TextView mCoupon;
        @Bind(R.id.fragment_hot_deals_shop_now)
        TextView mShopNow;
        @Bind(R.id.fragment_hot_deals_share)
        ImageView mShareImage;

        public DealsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

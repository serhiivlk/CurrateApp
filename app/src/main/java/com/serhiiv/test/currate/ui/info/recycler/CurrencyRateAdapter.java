package com.serhiiv.test.currate.ui.info.recycler;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.serhiiv.test.currate.R;
import com.serhiiv.test.currate.core.entity.CurrencyRate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrencyRateAdapter extends ListAdapter<CurrencyRate, CurrencyRateAdapter.CurrencyRateViewHolder> {
    private static final DiffUtil.ItemCallback<CurrencyRate> DIFF_CALLBACK = new DiffUtil.ItemCallback<CurrencyRate>() {
        @Override
        public boolean areItemsTheSame(@NonNull CurrencyRate oldItem, @NonNull CurrencyRate newItem) {
            return oldItem.getPair().equals(newItem.getPair());
        }

        @Override
        public boolean areContentsTheSame(@NonNull CurrencyRate oldItem, @NonNull CurrencyRate newItem) {
            return oldItem.equals(newItem);
        }
    };

    public CurrencyRateAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public CurrencyRateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_currency_rate, parent, false);
        return new CurrencyRateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyRateViewHolder holder, int position) {
        CurrencyRate item = getItem(position);
        holder.bind(item);
    }

    class CurrencyRateViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pair_name)
        TextView pairNameTextView;
        @BindView(R.id.pair_rate)
        TextView pairRateTextView;

        CurrencyRateViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(CurrencyRate item) {
            pairNameTextView.setText(item.getPair().toString());
            pairRateTextView.setText(String.valueOf(item.getRate()));
        }
    }
}

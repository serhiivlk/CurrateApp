package com.serhiiv.test.currate.ui.main.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.serhiiv.test.currate.R;
import com.serhiiv.test.currate.core.entity.CurrencyPair;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrencyPairAdapter extends ListAdapter<CurrencyPair, CurrencyPairAdapter.CurrencyPairViewHolder> {
    private static final DiffUtil.ItemCallback<CurrencyPair> DIFF_CALLBACK = new DiffUtil.ItemCallback<CurrencyPair>() {
        @Override
        public boolean areItemsTheSame(@NonNull CurrencyPair oldItem, @NonNull CurrencyPair newItem) {
            return oldItem.getPair().equals(newItem.getPair());
        }

        @Override
        public boolean areContentsTheSame(@NonNull CurrencyPair oldItem, @NonNull CurrencyPair newItem) {
            return oldItem.equals(newItem);
        }
    };

    public CurrencyPairAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public CurrencyPairViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_currency_pair, parent, false);
        return new CurrencyPairViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyPairViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class CurrencyPairViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pair_name)
        TextView nameTextView;

        public CurrencyPairViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(CurrencyPair pair) {
            nameTextView.setText(pair.toString());
        }
    }
}

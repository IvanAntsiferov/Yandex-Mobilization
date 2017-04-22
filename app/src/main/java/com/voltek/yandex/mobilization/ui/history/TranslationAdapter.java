package com.voltek.yandex.mobilization.ui.history;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.voltek.yandex.mobilization.R;
import com.voltek.yandex.mobilization.entity.general.Translation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TranslationAdapter extends RecyclerView.Adapter<TranslationAdapter.ViewHolder> {

    private List<Translation> mItems;

    private Context mContext;

    public TranslationAdapter(Context context, List<Translation> items) {
        mContext = context;
        mItems = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ib_favorite)
        ImageButton buttonFavorite;
        @BindView(R.id.tv_to)
        TextView textViewTo;
        @BindView(R.id.tv_from)
        TextView textViewFrom;
        @BindView(R.id.tv_direction)
        TextView textViewDirection;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_translation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Translation translation = mItems.get(position);

        holder.textViewTo.setText(translation.getToText());
        holder.textViewFrom.setText(translation.getFromText());
        holder.textViewDirection.setText(translation.getLangs());
        if (translation.getFavorite()) {
            holder.buttonFavorite.setImageDrawable(
                    mContext.getResources().getDrawable(R.drawable.ic_bookmark_24dp));
        } else {
            holder.buttonFavorite.setImageDrawable(
                    mContext.getResources().getDrawable(R.drawable.ic_bookmark_border_24dp));
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void replaceData(List<Translation> translations) {
        mItems.clear();
        mItems.addAll(translations);
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }
}

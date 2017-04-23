package com.voltek.yandex.mobilization.ui.history;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.voltek.yandex.mobilization.R;
import com.voltek.yandex.mobilization.entity.general.Translation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;

public class TranslationAdapter extends RecyclerView.Adapter<TranslationAdapter.ViewHolder> {

    private List<Translation> mItems;

    private Context mContext;

    // For on item click listener
    private PublishSubject<Translation> mOnItemClickSubject = PublishSubject.create();

    public Observable<Translation> getClickedItem() {
        return mOnItemClickSubject;
    }

    // For favorite button click listener
    private PublishSubject<Translation> mOnFavoriteClickSubject = PublishSubject.create();

    public Observable<Translation> getFavoriteClickedItem() {
        return mOnFavoriteClickSubject;
    }

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
        @BindView(R.id.container_translation)
        RelativeLayout containerTranslation;

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
        changeFavoriteIcon(holder.buttonFavorite, translation.getFavorite());

        RxView.clicks(holder.containerTranslation).subscribe(o -> {
            mOnItemClickSubject.onNext(translation);
        }, Timber::e);

        RxView.clicks(holder.buttonFavorite).subscribe(o -> {
            changeFavoriteIcon(holder.buttonFavorite, !translation.getFavorite());
            mOnFavoriteClickSubject.onNext(translation);
        }, Timber::e);
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

    private void changeFavoriteIcon(ImageButton imageButton, boolean isFavorite) {
        if (isFavorite) {
            imageButton.setImageDrawable(
                    mContext.getResources().getDrawable(R.drawable.ic_bookmark_24dp));
        } else {
            imageButton.setImageDrawable(
                    mContext.getResources().getDrawable(R.drawable.ic_bookmark_border_24dp));
        }
    }
}

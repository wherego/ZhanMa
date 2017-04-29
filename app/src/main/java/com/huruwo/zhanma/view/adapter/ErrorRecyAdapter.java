package com.huruwo.zhanma.view.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huruwo.zhanma.R;
import com.huruwo.zhanma.db.dbmodel.ItemTable;
import com.huruwo.zhanma.view.widget.TagGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ErrorRecyAdapter extends RecyclerView.Adapter<ErrorRecyAdapter.MyViewHolder> {


    private List<ItemTable> itemTables;
    private LayoutInflater mInflater;
    private int mLastPosition = -1;
    private Context context;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public ErrorRecyAdapter(Context context, List<ItemTable> itemTables) {
        mInflater = LayoutInflater.from(context);
        this.context=context;
        this.itemTables = itemTables;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                R.layout.item_error, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    removeData(pos);
                    return false;
                }
            });
        }

        //设置动画 且设置只有下拉有，上拉没有
        Animator[] animators = getAnimators(holder.itemView);
        if (animators.length > 0 && holder.getAdapterPosition() > mLastPosition) {
            if (animators.length > 1) {
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(animators);
                animatorSet.start();
            } else {
                for (Animator animator : animators) {
                    animator.start();
                }
            }
        } else {

        }
        mLastPosition = holder.getAdapterPosition();


    }

    @Override
    public int getItemCount() {
        return itemTables.size();
    }

    public void addData(int position, ItemTable itemTable) {
        itemTables.add(position,itemTable);
        notifyItemInserted(position);
    }


    public void removeData(int position) {
        itemTables.remove(position);
        notifyItemRemoved(position);
    }


    class MyViewHolder extends ViewHolder {


        @BindView(R.id.tv_title)
        TextView mTvtitle;
        @BindView(R.id.ques_tag)
        TagGroup mTag;



        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //设置item的出场动画
    private Animator[] getAnimators(View view) {
        return new Animator[]{
                ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1f).setDuration(1000),
                ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, 100, 0).setDuration(1000)
        };
    }

    public List<ItemTable> getData() {
        return itemTables;
    }
}
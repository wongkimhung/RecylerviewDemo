package com.rtfsc.recylerviewdemo.view.adapter;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rtfsc.recylerviewdemo.R;
import com.rtfsc.recylerviewdemo.view.helper.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author HuangJx
 *         create on 2016/5/12.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> implements SimpleItemTouchHelperCallback.OnMoveAndSwipedListener {
	private List<String> mItems = new ArrayList<>();
	private OnStartDragListener mOnStartDragListener;

	public RecyclerViewAdapter(Context context, OnStartDragListener onStartDragListener) {
		mItems.addAll(Arrays.asList(context.getResources().getStringArray(R.array.dummy_items)));
		mOnStartDragListener = onStartDragListener;
	}

	@Override
	public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
		return new ItemViewHolder(inflate);
	}

	@Override
	public void onBindViewHolder(final ItemViewHolder holder, int position) {
		holder.text.setText(mItems.get(position));
		holder.handle.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (mOnStartDragListener != null) {
					//如果按下
					if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
						//回调RecyclerListFragment中的startDrag方法
						//让mItemTouchHelper执行拖拽操作
						mOnStartDragListener.startDrag(holder);
					}
				}
				return false;
			}
		});
	}

	@Override
	public int getItemCount() {
		return mItems.size();
	}


	public class ItemViewHolder extends RecyclerView.ViewHolder {
		TextView text;
		ImageView handle;

		public ItemViewHolder(View itemView) {
			super(itemView);
			text = (TextView) itemView.findViewById(R.id.text);
			handle = (ImageView) itemView.findViewById(R.id.handle);
		}
	}

	@Override
	public boolean onItemMove(int fromPosition, int toPosition) {
		//交换mItems数据的位置
		Collections.swap(mItems, fromPosition, toPosition);
		//交换RecyclerView列表中item的位置
		notifyItemMoved(fromPosition, toPosition);
		return false;
	}

	@Override
	public void onItemDismiss(int position) {
		mItems.remove(position);
		notifyItemRemoved(position);
	}


	public interface OnStartDragListener {
		void startDrag(RecyclerView.ViewHolder viewHolder);
	}

	public void setOnStartDragListener(OnStartDragListener onStartDragListener) {
		mOnStartDragListener = onStartDragListener;
	}
}

package com.rtfsc.recylerviewdemo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rtfsc.recylerviewdemo.view.adapter.RecyclerViewAdapter;
import com.rtfsc.recylerviewdemo.view.helper.SimpleItemTouchHelperCallback;

/**
 * @author HuangJx
 *         create on 2016/5/12.
 */
public class RecyclerGridFragment extends Fragment implements RecyclerViewAdapter.OnStartDragListener {
	private ItemTouchHelper mItemTouchHelper;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return new RecyclerView(container.getContext());
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), this);
		RecyclerView recyclerView = (RecyclerView) view;
		recyclerView.setHasFixedSize(true);
		recyclerView.setAdapter(adapter);
		//只有这里和RecyclerListFragment不一样，这里我们指定布局为GridView样式，2列
		recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

		SimpleItemTouchHelperCallback callback = new SimpleItemTouchHelperCallback();
		callback.setOnMoveAndSwipedListener(adapter);

		mItemTouchHelper = new ItemTouchHelper(callback);
		mItemTouchHelper.attachToRecyclerView(recyclerView);
	}

	@Override
	public void startDrag(RecyclerView.ViewHolder viewHolder) {
		if (mItemTouchHelper != null) {
			mItemTouchHelper.startDrag(viewHolder);
		}
	}
}

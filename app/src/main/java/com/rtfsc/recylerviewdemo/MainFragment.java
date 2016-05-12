package com.rtfsc.recylerviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author HuangJx
 *         create on 2016/5/12.
 */
public class MainFragment extends ListFragment {
	private OnListItemClickListener mOnListItemClickListener;

	public interface OnListItemClickListener {
		void onListItemClick(int position);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mOnListItemClickListener = (OnListItemClickListener) getActivity();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String[] items = getResources().getStringArray(R.array.main_items);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_list_item_1, items
		);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (mOnListItemClickListener != null) {
			mOnListItemClickListener.onListItemClick(position);
		}
	}
}

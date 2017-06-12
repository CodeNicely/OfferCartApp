package com.codenicely.brandstore.project.state.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.home.HomePage;
import com.codenicely.brandstore.project.state.data.StateDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ujjwal on 9/6/17.
 */
public class StateAdapter extends RecyclerView.Adapter<StateAdapter.MyViewHolder> {

	List<StateDetails> stateDetailsList= new ArrayList<>();
	private Context context;
	private LayoutInflater layoutInflater;
	private StateFragment stateFragment;

	public StateAdapter(Context context, StateFragment stateFragment) {
		this.context = context;
		this.stateFragment = stateFragment;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = layoutInflater.inflate(R.layout.state_item, parent, false);
		return new MyViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(final MyViewHolder holder, int position) {
		final StateDetails stateDetails = stateDetailsList.get(position);

		holder.state.setText(stateDetails.getState_name());
		holder.state.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (context instanceof HomePage) {
					stateFragment.onStateSelected(stateDetails.getState_id(),
							stateDetails.getState_name());
					holder.done.setVisibility(View.VISIBLE);
				}

			}
		});

	}

	@Override
	public int getItemCount() {
		return stateDetailsList.size();
	}

	public void setData(List<StateDetails> stateDetailsList) {
		this.stateDetailsList = stateDetailsList;
	}
	public class MyViewHolder extends RecyclerView.ViewHolder {

		private TextView state;
		private ImageView done;

		private MyViewHolder(View itemView) {
			super(itemView);

			done = (ImageView) itemView.findViewById(R.id.done);
			state = (TextView) itemView.findViewById(R.id.state);
		}

	}
}

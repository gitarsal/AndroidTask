package com.android.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.model.Task;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import p.androidtask.R;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> implements RealmChangeListener {

    private RealmResults<Task> mTasks;
    private OnTaskClickListener mOnTaskClickListener;
    private Context context;

    public TaskListAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Task task = mTasks.get(position);

        holder.mTextName.setText(task.getName());
        holder.mTextDescription.setText(task.getDescription());
        holder.mTextCreated.setText(context.getString(R.string.datecreated) + task.getDateCreated());
        holder.mTextUpdated.setText(context.getString(R.string.updatedon) + task.getDateUpdated());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnTaskClickListener != null) {
                    mOnTaskClickListener.delete(task.getId());
                }
            }
        });
        holder.mLayoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (mOnTaskClickListener != null) {
                    mOnTaskClickListener.onTaskClick(task.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    @Override
    public void onChange() {
        notifyDataSetChanged();
    }

    public void setOnTaskClickListener(final OnTaskClickListener onTaskClickListener) {
        mOnTaskClickListener = onTaskClickListener;
    }

    public void setTasks(final RealmResults<Task> tasks) {
        mTasks = tasks;
        mTasks.addChangeListener(this);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.layout_item_container)
        LinearLayout mLayoutItem;
        @Bind(R.id.text_name)
        TextView mTextName;
        @Bind(R.id.text_description)
        TextView mTextDescription;
        @Bind(R.id.text_created)
        TextView mTextCreated;
        @Bind(R.id.text_updated)
        TextView mTextUpdated;
        @Bind(R.id.delete)
        Button delete;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnTaskClickListener {
        void onTaskClick(int id);

        void delete(int id);
    }
}

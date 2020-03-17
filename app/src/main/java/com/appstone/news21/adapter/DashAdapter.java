package com.appstone.news21.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appstone.news21.MainActivity;
import com.appstone.news21.R;
import com.appstone.news21.model.MenuModel;

import java.util.ArrayList;

public class DashAdapter extends RecyclerView.Adapter<DashAdapter.MenuViewHolder> {

//    private static final String TAG = DashAdapter.class.getSimpleName();

    private Context mContext;
    private ArrayList<MenuModel> mData;


    public DashAdapter(Context context, ArrayList<MenuModel> data) {
        this.mContext = context;
        this.mData = data;
    }


    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dashboard_layout, viewGroup, false);

        return new MenuViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, final int position) {
        // include binding logic here
        MenuModel menu = mData.get(position);
        holder.mTvMenuName.setText(menu.getCategory());
        holder.mLlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MainActivity.class);
                switch (position) {
                    case 0:
                        intent.putExtra("key", "Sports");
                        mContext.startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("key", "Finance");
                        mContext.startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra("key", "Entertainment");
                        mContext.startActivity(intent);
                        break;
                    case 3:
                        intent.putExtra("key", "Technology");
                        mContext.startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView mTvMenuName;
        LinearLayout mLlRoot;

        MenuViewHolder(View itemView) {
            super(itemView);
            mTvMenuName = itemView.findViewById(R.id.tv_dash_text);
            mLlRoot = itemView.findViewById(R.id.ll_root);
        }
    }
}
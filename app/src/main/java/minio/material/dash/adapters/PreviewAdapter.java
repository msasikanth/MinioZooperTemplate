package minio.material.dash.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import minio.material.dash.R;

/**
 * Created by sasikanth on 05/02/16.
 */
public class PreviewAdapter extends RecyclerView.Adapter<PreviewAdapter.WidgetHolder> {

    private List<WidgetPreview> widgetPreviewList;

    @Override
    public WidgetHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(WidgetHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class WidgetHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView place_holder;
        ImageView image;
        TextView text;
        RelativeLayout loadingLayout;
        AdapterView.OnItemClickListener onItemClickListener;

        public WidgetHolder(View itemView, AdapterView.OnItemClickListener clickListener) {
            super(itemView);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            place_holder = (CardView) itemView.findViewById(R.id.place_holder);
            image = (ImageView) itemView.findViewById(R.id.widget);
            text = (TextView) itemView.findViewById(R.id.name);
            loadingLayout = (RelativeLayout) itemView.findViewById(R.id.loading_layout);
            onItemClickListener = clickListener;
        }

        @Override
        public void onClick(View v) {

        }

        };
    }


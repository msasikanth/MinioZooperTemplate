package minio.material.dash;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pddstudio.zooperutils.SimpleWidgetCallback;
import com.pddstudio.zooperutils.ZooperUtils;

import java.io.IOException;
import java.util.List;

import minio.material.dash.adapters.PreviewAdapter;

/**
 * Created by sasikanth on 05/02/16.
 */
public class PreviewActivity extends AppCompatActivity implements SimpleWidgetCallback {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_main);

        try {
            ZooperUtils.init(this);
            ZooperUtils.getInstance().registerSimpleWidgetCallback(this);
        } catch (IOException io) {
            io.printStackTrace();
            finish();
        }




    }

    @Override
    public void onFinishedLoading(List<Bitmap> images) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(ZooperUtils.getInstance().createSimpleWidgetAdapter(images, true));
        recyclerView.setHasFixedSize(true);
    }
}

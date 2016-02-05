package minio.material.dash;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.pddstudio.zooperutils.SimpleWidgetCallback;
import com.pddstudio.zooperutils.ZooperUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.IOException;
import java.util.List;


/**
 * Created by sasikanth on 05/02/16.
 */
public class PreviewActivity extends AppCompatActivity implements SimpleWidgetCallback {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private SystemBarTintManager mStatusBarManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mStatusBarManager = new SystemBarTintManager(this);
        mStatusBarManager.setStatusBarTintEnabled(true);
        mStatusBarManager.setStatusBarTintColor(getResources().getColor(R.color.colorPrimary));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        try {
            ZooperUtils.init(this);
            ZooperUtils.getInstance().registerSimpleWidgetCallback(this);
        } catch (IOException io) {
            io.printStackTrace();
        }

    }


    @Override
    public void onFinishedLoading(List<Bitmap> images) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(ZooperUtils.getInstance().createSimpleWidgetAdapter(images, true));
        recyclerView.getAdapter().notifyDataSetChanged();

    }
}

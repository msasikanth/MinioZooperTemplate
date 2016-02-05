package minio.material.dash;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import minio.material.dash.adapters.ChangelogAdapter;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.readystatesoftware.systembartint.SystemBarTintManager;


/**
 * Created by sasik on 10/4/2015.
 */
public class About extends AppCompatActivity {


    private SystemBarTintManager mStatusBarManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

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

        TextView author = (TextView) findViewById(R.id.dashauthor);
        author.setText(Html.fromHtml(getString(R.string.dashboard_author)));

        TextView authordesc = (TextView) findViewById(R.id.dashauthor_info);
        authordesc.setText(Html.fromHtml(getString(R.string.dashboard_author_desc)));


        TextView author2 = (TextView) findViewById(R.id.dashauthor2);
        author2.setText(Html.fromHtml(getString(R.string.dashboard_author2)));

        TextView authordesc2 = (TextView) findViewById(R.id.dashauthor_info2);
        authordesc2.setText(Html.fromHtml(getString(R.string.dashboard_author_desc2)));


        TextView dashauthorinstabtn = (TextView) findViewById(R.id.dashauthor_insta_button);
        dashauthorinstabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(getString(R.string.author_instagram));
                Intent insta = new Intent(Intent.ACTION_VIEW, uri);
                insta.setPackage("com.instagram.android");
                try {
                    startActivity(insta);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/sasikanth_")));
                }
            }
        });

        TextView dashauthorsource = (TextView) findViewById(R.id.dashauthor_source);
        dashauthorsource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(getString(R.string.author_source));
                Intent source = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(source);
            }
        });

        TextView dashappgplus = (TextView) findViewById(R.id.dashauthor_gplus);
        dashappgplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(getString(R.string.author_plus));
                Intent gplus = new Intent(Intent.ACTION_VIEW, uri);
                gplus.setPackage("com.google.android.apps.plus");
                startActivity(gplus);
            }
        });

        TextView dashappgpluscommu = (TextView) findViewById(R.id.dashapp_gplus);
        dashappgpluscommu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(getString(R.string.app_gplus));
                Intent gpluscommu = new Intent(Intent.ACTION_VIEW, uri);
                gpluscommu.setPackage("com.google.android.apps.plus");
                startActivity(gpluscommu);
            }
        });

        TextView authorlink = (TextView) findViewById(R.id.author_link);
        authorlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(getString(R.string.author_link1));
                Intent authorlink = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(authorlink);
            }
        });

        TextView authorlink2 = (TextView) findViewById(R.id.author_link2);
        authorlink2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(getString(R.string.author_link2));
                Intent authorlink2 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(authorlink2);
            }
        });

        TextView changelog = (TextView) findViewById(R.id.dashapp_changelog);
        changelog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(About.this)
                        .title(R.string.changelog_title)
                        .adapter(new ChangelogAdapter(About.this, R.array.fullchangelog), null)
                        .positiveText(R.string.great)
                        .callback(new MaterialDialog.ButtonCallback() {
                        }).show();
            }
        });


   }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }
}

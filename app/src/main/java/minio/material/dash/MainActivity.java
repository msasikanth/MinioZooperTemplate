package minio.material.dash;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.CardView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import minio.material.dash.adapters.ChangelogAdapter;
import minio.material.dash.utilities.Preferences;
import minio.material.dash.utilities.Util;

import com.afollestad.materialdialogs.MaterialDialog;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private TextView install;
    private TextView install2;
    private TextView play;
    private TextView amazon;
    private TextView preview;
    private CoordinatorLayout uilayout;
    private Preferences mPrefs;
    private static final int REQUEST_READ_STORAGE = 1;
    private String MARKETURL;
    private SystemBarTintManager mStatusBarManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChangelogPop();
        check();

        MARKETURL = "https://play.google.com/store/apps/details?id=";

        mPrefs = new Preferences(MainActivity.this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mStatusBarManager = new SystemBarTintManager(this);
        mStatusBarManager.setStatusBarTintEnabled(true);
        mStatusBarManager.setTintColor(Color.parseColor("#00000000"));

        uilayout = (CoordinatorLayout) findViewById(R.id.rootLayout);



        boolean installed = appInstalledOrNot("org.zooper.zwpro");  // Checks if the Zooper Pro app is installed or not on device.
        if(installed) {

        } else {
            new MaterialDialog.Builder(MainActivity.this)
                    .title(R.string.zooper_pro)
                    .content(R.string.install_package)
                    .positiveText(R.string.ok)
                    .callback(new MaterialDialog.ButtonCallback() {
                    }).show();
        }


        install = (TextView) findViewById(R.id.install);
        install.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23 && PermissionChecker
                        .checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                        PermissionChecker.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_READ_STORAGE);
                } else {
                    // Do absolutely NOTHING
                }
                CopyFonts();
                Snackbar snackbar = Snackbar
                        .make(v, getString(R.string.font_install), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.ok), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });

                snackbar.show();
            }
        });

        install2 = (TextView) findViewById(R.id.install2);
        install2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23 && PermissionChecker
                        .checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                        PermissionChecker.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_READ_STORAGE);
                } else {
                    // Do absolutely NOTHING
                }
                CopyIcons();
                Snackbar snackbar = Snackbar
                        .make(v, getString(R.string.icon_install), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.ok), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });

                snackbar.show();
            }
        });

        preview = (TextView) findViewById(R.id.preview);
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent preview = new Intent(MainActivity.this, PreviewActivity.class);
                startActivity(preview);

            }
        });

        amazon = (TextView) findViewById(R.id.amazon);
        amazon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean installed = appInstalledOrNot("com.amazon.venezia");
                if (installed) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("amzn://apps/android?p=" + getString(R.string.install_zooper))));
                } else {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=" + getString(R.string.install_zooper))));
                }
            }
        });

        play = (TextView) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getString(R.string.install_zooper))));
            }
        });

        CardView about = (CardView) findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new Intent(MainActivity.this, About.class);
                startActivity(about);
            }
        });


    }


    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }



    public void CopyIcons() {               //  Installs weather IconSets on the device
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("IconSets");
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        for (String filename : files) {
            System.out.println("File name => " + filename);
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open("IconSets/" + filename);
                out = new FileOutputStream(Environment.getExternalStorageDirectory().toString() + "/ZooperWidget/IconSets/" + filename);
                copyFile(in, out);
                in.close();
                in = null;
                out.flush();
                out.close();
                out = null;
            } catch (Exception e) {
                Log.e("tag", e.getMessage());
            }

        }
    }

    public void CopyFonts() {               //  Installs weather Fonts on the device
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("fonts");
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        for (String filename : files) {
            System.out.println("File name => " + filename);
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open("fonts/" + filename);
                out = new FileOutputStream(Environment.getExternalStorageDirectory().toString() + "/ZooperWidget/fonts/" + filename);
                copyFile(in, out);
                in.close();
                in = null;
                out.flush();
                out.close();
                out = null;
            } catch (Exception e) {
                Log.e("tag", e.getMessage());
            }

        }
    }

    public void check() {             // Checks if the device has IconSets and Fonts already installed
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("IconSets");
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }
        for (String filename : files) {
            File file = new File(Environment.getExternalStorageDirectory().toString() + "/ZooperWidget/IconSets/" + filename);
            boolean installed = file.exists();
            if (!installed) {
                new MaterialDialog.Builder(this)
                        .title(R.string.important)
                        .content(R.string.important_con)
                        .positiveText(R.string.ok)
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                super.onPositive(dialog);
                            }
                        }).show();
                break;
            }
        }


    }


    private void Changelog() {
        new MaterialDialog.Builder(this)
                .title(R.string.changelog_title)
                .adapter(new ChangelogAdapter(this, R.array.fullchangelog), null)
                .positiveText(R.string.great)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        mPrefs.setNotFirstrun();
                    }
                }).show();
    }

    private void ChangelogPop() {
        String launchinfo = getSharedPreferences("PrefsFile", MODE_PRIVATE).getString("version", "0");
        if (launchinfo != null && !launchinfo.equals(Util.getAppVersion(this)))
            Changelog();
        storeSharedPrefs();
    }

    @SuppressLint("CommitPrefEdits")
    private void storeSharedPrefs() {
        SharedPreferences sharedPreferences = getSharedPreferences("PrefsFile", MODE_PRIVATE);
        sharedPreferences.edit().putString("version", Util.getAppVersion(this)).commit();
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.action_favorite:
                Intent rate = new Intent(Intent.ACTION_VIEW, Uri.parse(MARKETURL + getPackageName()));
                startActivity(rate);
                break;

        }
        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }


}

package smvitm.rajath.varnothsava.Activity;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;

import smvitm.rajath.varnothsava.R;

/*
Created by Rajath
For more details contact me at
Name : Rajath
Email : ykrajath4@gmail.com
WhatsApp : +91 9591708470
Phone : +91 9591708470
*/

public class GotoWeb extends AppCompatActivity {
    static String topic;
    static String link = "null";
    static String newVersion = "0";
    static Thread thread;
    static String year;
    static String branch;
    static String section;
    WebView webview;
    URL url;
    Button a;
    TextView e1, e2, e3, e4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String a = intent.getStringExtra("key");
        if (a.equalsIgnoreCase("Website")) {
            Web("https://goo.gl/CgkQge");
        } else if (a.equalsIgnoreCase("fb")) {
            Web("https://www.facebook.com/varnothsava18");
        } else if (a.equalsIgnoreCase("insta")) {
            Web("https://www.instagram.com/varnothsava_18/");
        } else if (a.equalsIgnoreCase("DeveloperLinkedin")) {
            Web("https://www.linkedin.com/in/rajathyk/");
        } else if (a.equalsIgnoreCase("Developerg")) {
            Web("https://github.com/Rajath4");
        } else if (a.equalsIgnoreCase("reg")) {
            Web("https://goo.gl/ZkyXyD");
        } else if (a.equalsIgnoreCase("regT")) {
            Web("https://goo.gl/ysnsbU");
        }
    }


    public void Web(String url) {

        if (!MainActivity.checkInternetConnection(GotoWeb.this)) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setIcon(R.mipmap.ic_launcher);
            alertDialog.setTitle("Connection Error!");
            alertDialog.setMessage("Internet Connection is not available");
            alertDialog.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alertDialog.setCancelable(false).create().show();
        } else {

            webview = new WebView(this);
            setContentView(webview);
            webview.getSettings().setJavaScriptEnabled(true);
            webview.getSettings().setLoadWithOverviewMode(true);
            webview.getSettings().setUseWideViewPort(true);
            webview.getSettings().setSupportZoom(true);
            webview.getSettings().setBuiltInZoomControls(true);
            webview.getSettings().setDisplayZoomControls(false);
            webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

            webview.getSettings().setAppCacheMaxSize(200 * 1024 * 1024); // 200MB
            webview.getSettings().setAppCachePath(getApplicationContext().getCacheDir().getAbsolutePath());
            webview.getSettings().setAllowFileAccess(true);
            webview.getSettings().setAppCacheEnabled(true);
            webview.getSettings().setJavaScriptEnabled(true);
            webview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

/*
            webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
*/


            webview.clearView();
            webview.loadUrl(url);
            webview.setWebViewClient(new MyWebViewClient());
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            //if Back key pressed and webview can navigate to previous page
            webview.goBack();
            // go back to previous page
            return true;
        } else {
            // finish the activity
            finish();
        }

        return super.onKeyDown(keyCode, event);
    }

    private class MyWebViewClient extends WebViewClient {
        ProgressDialog pd = null;

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.endsWith(".pdf")) {
                try {
                    Intent intentUrl = new Intent(Intent.ACTION_VIEW);
                    intentUrl.setDataAndType(Uri.parse(url), "application/pdf");
                    intentUrl.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentUrl);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(GotoWeb.this, "Please install any PDF viewer from Google Play Store", Toast.LENGTH_SHORT).show();
                }
            }
            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            pd = new ProgressDialog(GotoWeb.this);
            pd.setTitle("Please wait");
            pd.setMessage("Page is loading...");
            pd.setCancelable(true);
            pd.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            pd.dismiss();
        }
    }

}
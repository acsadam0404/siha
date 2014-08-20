package com.si.ha.android;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.si.ha.android.qrcode.IntentIntegrator;
import com.si.ha.android.qrcode.IntentResult;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        findViewById(R.id.scanner).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {

            // handle scan result
            String contentsString = scanResult.getContents() == null ? "0" : scanResult.getContents();
            if (contentsString.equalsIgnoreCase("0")) {
                Toast.makeText(this, "Problem to get the  content Number", Toast.LENGTH_LONG).show();

            } else {
                ((TextView)findViewById(R.id.scanView)).setText(contentsString);

                new PostDeviceTask().execute(contentsString);
            }

        } else {
            Toast.makeText(this, "Problem to scan the barcode.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()) {
            case R.id.action_devices:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            case R.id.action_speech:
                startActivity(new Intent(getApplicationContext(), SpeechActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    class PostDeviceTask extends AsyncTask<String, Void, String> {


        protected String doInBackground(String... content) {
            RestTemplate restTemplate = new RestTemplate();
            List<HttpMessageConverter<?>> cnvs = new ArrayList<>();
            cnvs.add(new MappingJackson2HttpMessageConverter());
            restTemplate.setMessageConverters(cnvs);

            //String str = restTemplate.getForObject("http://acspc.acsadam.hu:18080/siha/rest/devices", String.class);
            // restTemplate.postForObject("http://acspc.acsadam.hu:18080/siha/rest/devices", device, Device.class);
            try {
                // XXX itt elég lesz getelni a device IP címével (amit kiolvasunk QR kódból, és így visszakapjuk az egész devicet */
                String result = restTemplate.getForObject("http://acspc.acsadam.hu:18080/siha/rest/devices/", String.class);
                return result;
            } catch (Throwable t) {
                Log.e("D", "resthívás szar", t);
                throw t;
            }
            // Device result = restTemplate.getForObject("http://acspc.acsadam.hu:18080/siha/rest/devices/1", Device.class);
        }

        protected void onPostExecute(String device) {

            Toast.makeText(MainActivity.this, device, Toast.LENGTH_LONG).show();
            Log.i("D", device.toString());
            ((TextView)findViewById(R.id.scanView)).setText(device);

        }
    }

}

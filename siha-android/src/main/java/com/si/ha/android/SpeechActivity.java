package com.si.ha.android;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ács Ádám on 2014.08.20..
 */
public class SpeechActivity extends ActionBarActivity{
    private static final String TAG = "SpeechA";

    @Override
    protected void onStart() {
        super.onStart();
        findViewById(R.id.speechBtn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                        "com.domain.app");

                SpeechRecognizer recognizer = SpeechRecognizer
                        .createSpeechRecognizer(SpeechActivity.this);
                RecognitionListener listener = new RecognitionListener() {
                    @Override
                    public void onResults(Bundle results) {
                        ArrayList<String> voiceResults = results
                                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                        if (voiceResults == null) {
                            Log.e(TAG, "No voice results");
                        } else {
                            Log.d(TAG, "Printing matches: ");
                            for (String match : voiceResults) {
                                Log.d(TAG, match);
                                ((TextView)findViewById(R.id.speechView)).setText(match);
                            }

                        }
                    }

                    @Override
                    public void onReadyForSpeech(Bundle params) {
                        Log.d(TAG, "Ready for speech");
                    }

                    @Override
                    public void onError(int error) {
                        Log.d(TAG,
                                "Error listening for speech: " + error);
                    }

                    @Override
                    public void onBeginningOfSpeech() {
                        Log.d(TAG, "Speech starting");
                    }

                    @Override
                    public void onBufferReceived(byte[] buffer) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onEndOfSpeech() {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onEvent(int eventType, Bundle params) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onPartialResults(Bundle partialResults) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onRmsChanged(float rmsdB) {
                        // TODO Auto-generated method stub

                    }
                };
                recognizer.setRecognitionListener(listener);
                recognizer.startListening(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speech_layout);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}

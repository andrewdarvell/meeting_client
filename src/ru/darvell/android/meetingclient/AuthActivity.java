package ru.darvell.android.meetingclient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ru.darvell.android.meetingclient.api.MeetingApi;

import java.util.Map;

/**
 * Форма авторизации и регистрации
 */
public class AuthActivity extends Activity {

	MyTask mt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.authlayout);

//		if (android.os.Build.VERSION.SDK_INT > 9) {
//			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//			StrictMode.setThreadPolicy(policy);
//		}

		Button button = (Button) findViewById(R.id.button);
		Button button2 = (Button) findViewById(R.id.button2);

		final EditText loginText = (EditText) findViewById(R.id.loginText);
		final EditText passText = (EditText) findViewById(R.id.passText);

		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				doLogin(loginText.getText().toString(), passText.getText().toString());
			}
		});
	}

	void doLogin(String login, String pass){
		mt = new MyTask();
		mt.execute(MeetingApi.prepareLogin(login, pass));
	}


	class MyTask extends AsyncTask<Map<String,String>, Integer, String> {
		@Override
		protected String doInBackground(Map<String, String>... params) {
			try {
				Log.i("debug", "Send Post!!!");
				return MeetingApi.sendPost(params[0]);

			}catch (Exception e){
				e.printStackTrace();
				return null;
			}
		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
			if (s == null) {
				Log.i("debug", "Error!!!");
			}else {
				Log.i("debug", s);
			}

		}
	}


}
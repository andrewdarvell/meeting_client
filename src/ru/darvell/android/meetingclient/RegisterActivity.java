package ru.darvell.android.meetingclient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ru.darvell.android.meetingclient.api.MeetingApi;

import java.util.Map;

/**
 * Регистрация нового пользователя
 */
public class RegisterActivity extends Activity {

	Button registerBtn;
	Button cancelBtn;
	EditText loginText;
	EditText passText;
	EditText emailText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registerlayaut);

		registerBtn = (Button) findViewById(R.id.b_apply_register);
		cancelBtn = (Button) findViewById(R.id.b_cancel_register);
		loginText = (EditText) findViewById(R.id.t_login_register);
		passText = (EditText) findViewById(R.id.t_pass_register);
		emailText = (EditText) findViewById(R.id.t_email_register);

		registerBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				doRegister();
			}
		});

		cancelBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				cancelRegister();
			}
		});

	}

	void doRegister(){
		MyTask myTask  = new MyTask();
		myTask.execute(MeetingApi.preapareRegister(loginText.getText().toString(),
													passText.getText().toString(),
													emailText.getText().toString()
													));
	}

	void cancelRegister(){

	}

	class MyTask extends AsyncTask<Map<String,String>, Integer, String>{

		@Override
		protected String doInBackground(Map<String, String>... params) {
			try {
				Log.i("debug", "Send Get!!!");
				return MeetingApi.sendGet(params[0]);
			}catch (Exception e){
				e.printStackTrace();
				return null;
			}
		}

		@Override
		protected void onPostExecute(String s) {
			if (s == null) {
				Log.i("debug", "Error!!!");
			}else {
				Map<String, String> response = MeetingApi.parseParams(s);
				if (response.get("code").equals("0")){
					Log.i("debug", "Success Register");
				}else{
					Log.e("debug", s);
				}
			}
		}
	}

}
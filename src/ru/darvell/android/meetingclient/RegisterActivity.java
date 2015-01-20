package ru.darvell.android.meetingclient;

import android.app.Activity;
import android.os.Bundle;

/**
 * Регистрация нового пользователя
 */
public class RegisterActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registerlayaut);
	}
}
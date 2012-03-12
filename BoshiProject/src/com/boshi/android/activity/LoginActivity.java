package com.boshi.android.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.boshi.android.schedule.R;
import com.boshi.android.tools.HttpUtil;
import com.boshi.android.tools.RemoteServiceAccessPoint;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoginActivity extends Activity 
{

	private boolean validate() 
	{
		String userName = ((TextView) findViewById(R.id.loginNameText))
				.getText().toString();
		String passWord = ((TextView) findViewById(R.id.loginPassText))
				.getText().toString();

		if (userName.trim().equals("") || passWord.trim().equals("")) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("提示").setMessage("用户名和密码都不能为空")
					.setPositiveButton("确定", null);
			AlertDialog dialog = builder.create();
			dialog.show();
			return false;
		}
		return true;
	}

	private void enterMain() 
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示").setMessage("恭喜注册成功现在进入")
				.setPositiveButton("确定", null);
		AlertDialog dialog = builder.create();
		dialog.show();

	}

	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.login);

		Button loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new OnClickListener() 
		{

			public void onClick(View v) 
			{
				Intent intent = new Intent(LoginActivity.this,
						DayViewActivity.class);
				startActivity(intent);
			}
		});
		loginButton.setWidth(this.getWindowManager().getDefaultDisplay()
				.getWidth() / 2);

		LinearLayout layout = (LinearLayout) findViewById(R.id.whole);
		layout.setBackgroundColor(Color.GRAY);

		Button registButton = (Button) findViewById(R.id.registButton);
		registButton.setWidth(this.getWindowManager().getDefaultDisplay()
				.getWidth() / 2);
		registButton.setOnClickListener(new OnClickListener() 
		{

			public void onClick(View v) 
			{
				if (validate()) {
					String userName = ((TextView) findViewById(R.id.loginNameText))
							.getText().toString();
					String passWord = ((TextView) findViewById(R.id.loginPassText))
							.getText().toString();
					Map<String, String> paramMap = new HashMap<String, String>();
					paramMap.put("userName", userName);
					paramMap.put("passWord", passWord);

					try 
					{

						JSONObject result = new JSONObject(HttpUtil
								.postRequest(
										RemoteServiceAccessPoint.checkLogin,
										paramMap));
						String resultStr = result.getString("result");
						if (resultStr.equals("OK")) {
							enterMain();
						}
					} 
					catch (Exception ex) 
					{

					}
				}
			}
		});
	}
}

package com.abmiues;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;


public class LoginActivity extends Activity {


	private UserLoginTask mAuthTask = null;

	// UI references.
	private AutoCompleteTextView mUseridView;
	private EditText mpwdView;
	private ProgressDialog progrssdialog;
	private String userid;
	private String pwd;
	private SharedPreferences localdata;
	private String ip;
	private String host;
	private String JSESSIONID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		mUseridView = (AutoCompleteTextView) findViewById(R.id.userid);
		mpwdView = (EditText) findViewById(R.id.password);
		localdata=getSharedPreferences("localdata",0);
		host=localdata.getString("host", "80");
		ip=localdata.getString("ip", "139.129.119.93");
		userid=mUseridView.getText().toString();
		pwd=mpwdView.getText().toString();
		Button museridSignInButton = (Button) findViewById(R.id.email_sign_in_button);
		museridSignInButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				mAuthTask =	new UserLoginTask(userid, pwd);
				mAuthTask.execute();
			}
		});

	}




	private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
		// Create adapter to tell the AutoCompleteTextView what to show in its
		// dropdown list.
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(LoginActivity.this,
				android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

		mUseridView.setAdapter(adapter);
	}


	public class UserLoginTask extends AsyncTask<Void, Void, String> {
		private final String muserid;
		private final String mpwd;
		private String result;
		@Override
		protected void onPreExecute() {
			result="";
			progrssdialog.show();
		}



		UserLoginTask(String userid, String pwd) {
			muserid = userid;
			mpwd = pwd;
		}

		@Override
		protected String doInBackground(Void... param) {
			String url="http://"+ip+":"+host+"/myservers";
			//创建post请求
			DefaultHttpClient httpClient=new DefaultHttpClient();
			if(!muserid.equals("")&&!mpwd.equals("")){
				HttpPost httpRequest  = new HttpPost(url);
				//将要发送的数据装入params中
				ArrayList<NameValuePair> params=new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("userid",muserid));
				params.add(new BasicNameValuePair("pwd", mpwd));
				//超时处理
				HttpParams params1 = httpClient.getParams();  
				HttpConnectionParams.setConnectionTimeout(params1, 16000);  
				HttpConnectionParams.setSoTimeout(params1, 16000);
				try {
					//将要发送的数据加入到post请求中
					httpRequest.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
					HttpResponse httpResponse=httpClient.execute(httpRequest);
					//获取session
					CookieStore cookieStore = httpClient.getCookieStore();  
					List<Cookie> cookies = cookieStore.getCookies();  
					for(int i=0;i<cookies.size();i++){  
						if("JSESSIONID".equals(cookies.get(i).getName())){  
							JSESSIONID = cookies.get(i).getValue();  
						}
					}  
					StringBuilder builder=new StringBuilder();
					BufferedReader bufferedReader2 = new BufferedReader(  
							new InputStreamReader(httpResponse.getEntity().getContent()));  
					for(String s=bufferedReader2.readLine();s!=null;s=bufferedReader2.readLine())
						builder.append(s);
					result= builder.toString();
				} catch(ClientProtocolException e){  
					e.printStackTrace();  
				}catch(UnsupportedEncodingException e){  
					e.printStackTrace();  
				} catch (IOException e) {  
					e.printStackTrace();  
				} 
				
			}
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			mAuthTask = null;
			progrssdialog.cancel();
			if (result.equals("111")) {
				localdata.edit().putString("userid", muserid).commit();
				localdata.edit().putBoolean("islogin", true).commit();
				localdata.edit().putString("sessionid", JSESSIONID).commit();
				startActivity(new Intent(LoginActivity.this,MainActivity.class));
			} else {
				mpwdView.setError(getString(R.string.error_incorrect_password));
				mpwdView.requestFocus();
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			progrssdialog.cancel();
		}
	}
}

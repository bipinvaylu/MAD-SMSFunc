package com.mad.smsfunc;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity
{

	EditText phoneNo;
	EditText smsBody;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		phoneNo = (EditText) findViewById(R.id.phoneNo);
		smsBody = (EditText) findViewById(R.id.smsBody);

		findViewById(R.id.smsManager).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					// Get the default instance of the SmsManager
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(phoneNo.getText().toString(), null, smsBody.getText().toString(), null,
							null);
					Toast.makeText(getApplicationContext(), "Your sms sent successfully !", Toast.LENGTH_LONG).show();
				} catch(Exception e) {
					Toast.makeText(getApplicationContext(), "Your sms sending failed...", Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}

			}
		});

		findViewById(R.id.sendSms).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// add the phone number in the data
				Uri uri = Uri.parse("smsto:" + phoneNo.getText().toString());
				Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);
				// add the message at the sms_body extra field
				smsIntent.putExtra("sms_body", smsBody.getText().toString());
				try {
					startActivity(smsIntent);
				} catch(Exception e) {
					Toast.makeText(getApplicationContext(), "Your sms sending failed...", Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		});

		findViewById(R.id.viewSms).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent smsIntent = new Intent(Intent.ACTION_VIEW);
				smsIntent.putExtra("address", phoneNo.getText().toString());
				smsIntent.putExtra("sms_body", smsBody.getText().toString());
				smsIntent.setType("vnd.android-dir/mms-sms");
				try {
					startActivity(smsIntent);
				} catch(Exception e) {
					Toast.makeText(getApplicationContext(), "Your sms sending failed...", Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		});
	}

	// protected boolean isValidData() {
	// String strPhoneNo = phoneNo.getText().toString();
	// String strMessage = smsBody.getText().toString();
	//
	// // If not valid
	// boolean isValidData = true;
	// if(!strPhoneNo.equalsIgnoreCase("^[+]?[0-9]{10,13}$")) {
	// isValidData = false;
	// phoneNo.setError("Enter valid number");
	// }
	// if(strMessage == null || strMessage.trim().length() <= 0) {
	// isValidData = false;
	// smsBody.setError("Enter message text first");
	// }
	//
	// return isValidData;
	// }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

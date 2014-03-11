package com.mad.smsfunc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		if(bundle != null) {
			StringBuffer buffer = new StringBuffer();
			String phoneNo = null;
			Object[] pdus = (Object[]) bundle.get("pdus");
			for (Object pdu : pdus) {
				SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
				if(smsMessage.getOriginatingAddress() != null && phoneNo == null) {
					phoneNo = smsMessage.getOriginatingAddress();
				}
				buffer.append(smsMessage.getMessageBody());
			}
			Log.d(SMSReceiver.class.getSimpleName(), "PhoneNo: " + phoneNo + " Buffer: " + buffer.toString());

			Toast.makeText(context, "PhoneNo: " + phoneNo + " MessageBody: " + buffer.toString(), Toast.LENGTH_LONG)
					.show();
		}
	}

	// PDU(“protocol data unit”) is the industry format for an SMS message, which contains not only the SMS message, but
	// also metadata about the SMS message, such as text encoding, the sender, SMS service center address, and much
	// more.
}

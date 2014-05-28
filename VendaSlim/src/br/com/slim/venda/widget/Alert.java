package br.com.slim.venda.widget;

import org.holoeverywhere.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;

public class Alert {
	private AlertDialog alert;
	public void showDialog(Context context,String title, String message, String textBtnPositive, String textBtnNegative){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		if(textBtnPositive != null){
			builder.setPositiveButton(textBtnPositive, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					alert.dismiss();
				}
			});
		}
		
		if(textBtnNegative != null){
			builder.setNegativeButton(textBtnNegative, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					alert.dismiss();
				}
			});
		}
		alert = builder.create();
		alert.show();
	}
}

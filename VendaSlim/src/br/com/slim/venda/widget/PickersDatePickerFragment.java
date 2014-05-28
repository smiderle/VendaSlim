package br.com.slim.venda.widget;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.holoeverywhere.app.Dialog;
import org.holoeverywhere.app.DialogFragment;
import org.holoeverywhere.widget.datetimepicker.date.DatePickerDialog;
import org.holoeverywhere.widget.datetimepicker.date.DatePickerDialog.OnDateSetListener;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
//import org.holoeverywhere.widget.EditText;

@SuppressLint("ValidFragment")
public class PickersDatePickerFragment extends DialogFragment implements OnDateSetListener {
	
	EditText edText;
    private static final Calendar CALENDAR = Calendar.getInstance();
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    
	public PickersDatePickerFragment(EditText edText) {
		this.edText = edText;
	}

	@Override
	public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear,
			int dayOfMonth) {
		CALENDAR.set(Calendar.YEAR, year);
        CALENDAR.set(Calendar.MONTH, monthOfYear); 
        CALENDAR.set(Calendar.DAY_OF_MONTH, dayOfMonth); 
        edText.setText(DATE_FORMAT.format(CALENDAR.getTime()));
		
	}
	
	
	
	
	
	
	
	
	
	
	/*
		
	EditText edText;
	public PickersDatePickerFragment(EditText edText) {
		this.edText = edText;
	}
	
    private static final Calendar CALENDAR = Calendar.getInstance();

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(getSupportActivity(), getTheme(), this, CALENDAR.get(Calendar.YEAR),
        		CALENDAR.get(Calendar.MONTH), CALENDAR.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onDateSet(DatePicker picker, int year, int monthOfYear, int dayOfMonth) {
        CALENDAR.set(Calendar.YEAR, year);
        CALENDAR.set(Calendar.MONTH, monthOfYear); 
        CALENDAR.set(Calendar.DAY_OF_MONTH, dayOfMonth); 
        edText.setText(DATE_FORMAT.format(CALENDAR.getTime()));
    }
*/}

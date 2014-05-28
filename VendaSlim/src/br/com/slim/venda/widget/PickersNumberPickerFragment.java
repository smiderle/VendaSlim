package br.com.slim.venda.widget;

import org.holoeverywhere.app.AlertDialog;
import org.holoeverywhere.app.Dialog;
import org.holoeverywhere.app.DialogFragment;
import org.holoeverywhere.widget.NumberPicker;

import android.os.Bundle;
import android.view.View;
import br.com.slim.venda.R;

public class PickersNumberPickerFragment extends DialogFragment{

	private View makeNumberPicker() {
        View view = getLayoutInflater().inflate(R.layout.picker_number_picker_demo);
        NumberPicker numberPicker = (NumberPicker) view.findViewById(R.id.numberPicker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(100);
        numberPicker.setValue(1);
        
        return view;
    }
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getSupportActivity(), getTheme());
        builder.setView(makeNumberPicker());
        builder.setNegativeButton("Saida", null);
        builder.setPositiveButton("Salvar", null);
        return builder.create();
    }
	
}

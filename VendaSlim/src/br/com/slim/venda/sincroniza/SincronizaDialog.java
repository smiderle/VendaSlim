package br.com.slim.venda.sincroniza;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.holoeverywhere.app.Dialog;
import org.holoeverywhere.app.DialogFragment;
import org.holoeverywhere.app.ProgressDialog;
import org.holoeverywhere.widget.Button;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import br.com.slim.venda.R;
import br.com.slim.venda.integration.Integration;
import br.com.slim.venda.pedido.PedidoVO;
import br.com.slim.venda.websinc.Websinc;
import br.com.slim.venda.websinc.WebsincDAO;

public class SincronizaDialog extends DialogFragment{
	Context context;
	Button btnNotification;
	private String msgErro = "";
	private BaseAdapter adapter;
	private ArrayList<PedidoVO> lsPedidoVO;
	
	public SincronizaDialog(Context context, Button btnNotification, SincronizaAdapter adapter, ArrayList<PedidoVO> lsPedidoVO) {
		this.context = context;
		this.btnNotification = btnNotification;
		this.adapter = adapter;
		this.lsPedidoVO = lsPedidoVO;
	}
	
    private final class CustomTask extends AsyncTask<Void, Integer, Void> {
        private int mInitalPosition = 0;

        @Override
        protected Void doInBackground(Void... params) {
        	try {
				StringBuffer sb = new StringBuffer("0");
				Integration it   = new Integration(context);
				
				it.receberDataExpiracao(((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId());
				mDialog.setTitle("Enviando Clientes...");				
				it.enviarClientes();
				publishProgress(10);
				mDialog.setTitle("Enviando Pedidos...");
				it.enviarPedidos();
				publishProgress(20);
				mDialog.setTitle("Recebendo Atualizações...");

				List<Websinc> lsWebsinc =  it.atualizar2();

				WebsincDAO websincDAO = new WebsincDAO(context);
				Integer size = lsWebsinc.size();
				Integer valorUnitario = 0;
				
				if(size > 0)
					valorUnitario = 70 / size;
				
				
				
				for (Websinc websinc : lsWebsinc) {
					try{
						websincDAO.execute(websinc.getComando());					
						sb.append(",");
						sb.append(websinc.getSequencia());					
					} catch(SQLiteException e){
						e.printStackTrace();
					}
					
					publishProgress(valorUnitario +20);
					valorUnitario ++;
				}
				
				
				if(sb.length() > 1){
					it.deleteWebsinc(sb);
				}
				
				publishProgress(100);
				            
				mLastPosition = 0;
				
				
				 try {
	                    TimeUnit.MILLISECONDS.sleep(1000);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                    return null;
	                }
				
				if (getDialog() != null && getDialog().isShowing()) {
				    dismiss();
				}
				return null;
			} catch (Exception e) {
				dismiss();
				msgErro = e.getMessage();				
				e.printStackTrace();
				return null;
			}
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if (mDialog != null) {
                mDialog.setProgress(mLastPosition = values[0]);
            }
        }
    }

    private static final String KEY_INITIAL_POSITION = "initial_position";
    private ProgressDialog mDialog;
    private int mLastPosition = 0;
    private CustomTask mTask;

    @Override
    public void onCancel(DialogInterface dialog) {
        if (mTask != null) {
            mTask.cancel(false);
        }
        super.onCancel(dialog);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mLastPosition = savedInstanceState.getInt(KEY_INITIAL_POSITION, 0);
        }
        mDialog = new ProgressDialog(getSupportActivity(), getTheme());
        mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mDialog.setIndeterminate(false);
        mDialog.setMax(100);
        mDialog.setMessage("Sincronizando...");
        mDialog.setProgressNumberFormat("");
        setCancelable(false);
        mTask = new CustomTask();
        mTask.mInitalPosition = mLastPosition;
        mTask.execute();
        return mDialog;
    }
    
    private void mostrarNotificacao(String msg){
    	if(msg.length() > 100){
    		msg = "Ocorreu um problema ao sincronizar. Tente novamente mais tarde.";
    	}
		Animation animBounce;
		animBounce = AnimationUtils.loadAnimation(context, R.anim.move_in_move_out);
		btnNotification.setAnimation(animBounce);
		btnNotification.setText(msg);
		btnNotification.setVisibility(View.VISIBLE);
	
	}

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (mTask != null) {
            mTask.cancel(false);
        }
        if(msgErro != null &&  !msgErro.trim().equals(""))
        	mostrarNotificacao(msgErro);
        else
			atualizaStatusSincronizado();
			
        super.onDismiss(dialog);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INITIAL_POSITION, mLastPosition);
    }
    
    
    private void atualizaStatusSincronizado(){
    	if(lsPedidoVO != null){
	    	for (PedidoVO pedidoVO:lsPedidoVO) {
				pedidoVO.setSincronizado(true);
			}
	    	
			adapter.notifyDataSetChanged();
    	}
    }
}

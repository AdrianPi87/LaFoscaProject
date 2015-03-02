package com.prueba.lafosca;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Activities extends Activity  {

	private boolean isState = false;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activities);
		
		final Button state = (Button) findViewById(R.id.State);
		
		final Button open = (Button) findViewById(R.id.Open);
		
		final Button close = (Button) findViewById(R.id.Close);
		
		//final Button flag = (Button) findViewById(R.id.Flag);
		
		//final Button clean = (Button) findViewById(R.id.Clean);
		
		final Button kids = (Button) findViewById(R.id.Kids);
		
		// OnClick del boton de Estado
		state.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	new Thread(new Runnable() { 
                    public void run(){
                    	//Hacemos la llamada http para el estado, nos retorna true o false si la llamada es correcta o no
                    	boolean success = HttpClientComunication.getStateMethod();
                    	
                    	if(success) {
                    		isState = true;
							runOnUiThread(new Runnable() 
						    {
						        public void run()
						        {						            
						            if(HttpClientComunication.getState().equals("closed")) {						            	
						            	
						            	TextView tv1 = (TextView) findViewById(R.id.text_id);
								        tv1.setText("state: " + HttpClientComunication.getState() + "\n");
						            }
						            else {
							        	
						            	TextView tv1 = (TextView) findViewById(R.id.text_id);
						            	tv1.setText("state: " + HttpClientComunication.getState() + "\n" + " Flag: " + HttpClientComunication.getFlag() + "\n" + " hapiness: " + HttpClientComunication.getHappiness() + "\n" + "Dirtiness: " + HttpClientComunication.getDirtiness());
						            }
						        }
						     });
						}
						else {								
							 runOnUiThread(new Runnable() 
						        {
						        public void run()
						        {						        							            
						            TextView tv1 = (TextView) findViewById(R.id.text_id);
							        tv1.setText("Error de servicio");
						        }
						        });
						}
                    }
                }).start();
            }
        });
		
		open.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	new Thread(new Runnable() { 
                    public void run(){
                    	try {
                    		//Hacemos la llamada http para hacer un open, nos retorna true o false si la llamada es correcta o no
							boolean success = HttpClientComunication.putOpen();
							
							if(success) {
							  	
								boolean success2 = HttpClientComunication.putFlag();
											
								if(success2) {
									//TextView tv = (TextView) findViewById(R.id.text_id4);
									//tv.setText("Flag modificado a " + HttpClientComunication.getFlag());
									runOnUiThread(new Runnable() 
									{
										public void run()
										{			
											TextView tv1 = (TextView) findViewById(R.id.text_id2);
											tv1.setText("Estado Abierto, flag cambiado a 1 y Kids actualizados");
										}
									});
									}
									else {								
										runOnUiThread(new Runnable() 
										{
										public void run()
										{								   							        								        	
											 TextView tv = (TextView) findViewById(R.id.text_id2);
											 tv.setText("Error de servicio flag");
										}
										});
									}
								   	// TextView tv1 = (TextView) findViewById(R.id.text_id2);
								    // tv1.setText("Estado Abierto");
								            
							}
							else {								
								 runOnUiThread(new Runnable() 
							        {
							        public void run()
							        {
							        	TextView tv1 = (TextView) findViewById(R.id.text_id2);
							        	tv1.setText("Error de servicio open");
							        }
							        });
							}
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                }).start();
            }
        });
		
		close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	new Thread(new Runnable() { 
                    public void run(){
                    	try {
                    		//Hacemos la llamada http para hacer un close, nos retorna true o false si la llamada es correcta o no
                    		boolean success = HttpClientComunication.putClose();
							
							if(success) {
								
								boolean success2 = HttpClientComunication.postClean(HttpClientComunication.getUserName(), HttpClientComunication.getPassword());
											
								if(success2) {
									runOnUiThread(new Runnable() 
									{
										public void run()
										{
											TextView tv = (TextView) findViewById(R.id.text_id3);
										    tv.setText("Estado Cerrado y limpiado");
										}
									});
								}
								else {
									runOnUiThread(new Runnable() 
									{
										public void run()
										{
											TextView tv = (TextView) findViewById(R.id.text_id3);
											tv.setText("Error de servicio clean");
										}
									});
								}
							        	
							    // TextView tv = (TextView) findViewById(R.id.text_id3);
							    // tv.setText("Estado Cerrado");

							}
							else {								
								 runOnUiThread(new Runnable() 
							        {
							        public void run()
							        {							        	
							            TextView tv = (TextView) findViewById(R.id.text_id3);
							            tv.setText("Error de servicio close");
							        }
							        });
							}
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                }).start();
            }
        });
		
		kids.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {   	
            	goKids();
            }
        });
		
		/*clean.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	new Thread(new Runnable() { 
                    public void run(){
                    	try {
                    		//Hacemos la llamada http para hacer un clean, nos retorna true o false si la llamada es correcta o no
							boolean success = HttpClientComunication.postClean(HttpClientComunication.getUserName(), HttpClientComunication.getPassword());
							
							if(success) {
								 runOnUiThread(new Runnable() 
							        {
							        public void run()
							        {							        	
							            TextView tv = (TextView) findViewById(R.id.text_id5);
							            tv.setText("Limpiamos");

							        }
							        });
							}
							else {								
								 runOnUiThread(new Runnable() 
							        {
							        public void run()
							        {					        	
							            TextView tv = (TextView) findViewById(R.id.text_id5);
							            tv.setText("Error de servicio");

							        }
							        });
							}
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                }).start();
            }
        });*/
		
		/*flag.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	new Thread(new Runnable() { 
                    public void run(){
                    	try {
                    		//Hacemos la llamada http para modificar el flag, nos retorna true o false si la llamada es correcta o no
							boolean success = HttpClientComunication.putFlag();
							
							if(success) {
								 runOnUiThread(new Runnable() 
							        {
							        public void run()
							        {							        	
							            TextView tv = (TextView) findViewById(R.id.text_id4);
							            tv.setText("Flag modificado a " + HttpClientComunication.getFlag());
							        }
							        });
							}
							else {								
								 runOnUiThread(new Runnable() 
							        {
							        public void run()
							        {								   							        								        	
							            TextView tv = (TextView) findViewById(R.id.text_id4);
							            tv.setText("Error de servicio");
							        }
							        });
							}
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                }).start();
            }
        });*/
	}
	
	public void goKids() {
		if(isState) {
			Intent act = new Intent(this, ViewKids.class);
			startActivity(act);
		}
		else {
			
			LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout7);
            TextView tv = new TextView(Activities.this);						            
            tv.setText("Falta state");
            linearLayout.addView(tv);
		}
	}

}

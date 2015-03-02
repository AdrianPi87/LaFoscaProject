package com.prueba.lafosca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainPrincipal extends Activity{

	// Variable estatica de control del login
	private static boolean loginSuccess = false;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// Boton de acceso a la pantalla de crear usuario
		final Button accesCreateUser = (Button) findViewById(R.id.button1);
		
		// Boton de login
		final Button login = (Button) findViewById(R.id.button2);
		
		// Boton de acceso a la pantalla de otras actividades
		final Button other = (Button) findViewById(R.id.other);
        
		// OnClick de acceso a la pantalla de creacion de usuario
		accesCreateUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {   	
            	changeActivityCreateUser();
            }
        });
		
		// OnClick: Hacemos login
		login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	new Thread(new Runnable() { 
                    public void run(){
                    	boolean success = HttpClientComunication.getUser();
                    	if(success) {
                    		loginSuccess = true;
							 runOnUiThread(new Runnable() 
						        {
						        public void run()
						        {
						        	/*LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout1);
						            TextView tv = new TextView(MainPrincipal.this);						            
						            
						            tv.setTop(5);
						            
						            tv.setText("Login correcto");
						            linearLayout.addView(tv);*/
						        	
						        	TextView tv = (TextView) findViewById(R.id.view2);
						        	tv.setText("Login correcto");
						        }
						        });
						}
						else {								
							 runOnUiThread(new Runnable() 
						        {
						        public void run()
						        {						        							           
						            TextView tv = (TextView) findViewById(R.id.view);
						        	tv.setText("Error de login");
						        }
						        });
						}
                    }
                }).start();
            }
        });
		
		// OnClick de acceso a pantalla de actividades
		other.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {   	
            	changeActivityActivities();
            }
        });
		
	}
	
	// Metodo que cambia la actividad a la pantalla de creacion de usuario
	public void changeActivityCreateUser() {
		
		Intent act = new Intent(this, CreateUser.class);
		startActivity(act);
	}
	
	// Metodo que cambia la actividad a la pantalla de actividades
	public void changeActivityActivities() {
		
		if(loginSuccess) {
			Intent act = new Intent(this, Activities.class);
			startActivity(act);
		}
		else {
			LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout1);
            TextView tv = new TextView(MainPrincipal.this);						            
            
            tv.setText("Falta hacer login");
            linearLayout.addView(tv);
		}
	}

}

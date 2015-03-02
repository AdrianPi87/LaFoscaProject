package com.prueba.lafosca;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateUser extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createuser);
		
		final EditText username = (EditText)findViewById(R.id.username);
		final EditText password = (EditText)findViewById(R.id.password);
		
		final Button createUser = (Button) findViewById(R.id.create);
		
		final Button back = (Button) findViewById(R.id.backPrincipal);
		
		createUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	new Thread(new Runnable() { 
                    public void run(){
                    	try {
							boolean success = HttpClientComunication.postData(username.getText().toString(), password.getText().toString());
							if(success) {
								 runOnUiThread(new Runnable() 
							        {
							        public void run()
							        {
							        	/*LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout2);
							            TextView tv = new TextView(CreateUser.this);
							            tv.setText("Usuario creado");
							            linearLayout.addView(tv);*/
							        	
							        	TextView tv = (TextView) findViewById(R.id.view);
							        	tv.setText("Usuario creado");
							        }
							        });
							}
							else {								
								 runOnUiThread(new Runnable() 
							        {
							        public void run()
							        {
							        	/*LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout2);
							            TextView tv = new TextView(CreateUser.this);
							            tv.setText("Error al crear usuario");
							            linearLayout.addView(tv);*/
							            
							            TextView tv = (TextView) findViewById(R.id.view);
							        	tv.setText("Error al crear usuario");
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
		
		back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	backPrincipal();
            }
		});
	}
	
	public void updateViewText(String s) {
		
		/*LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout2);
		TextView txt1 = new TextView(CreateUser.this);
		txt1.setText(s);
		linearLayout.setBackgroundColor(Color.TRANSPARENT);
		linearLayout.addView(txt1);*/
		
		
	}
	
	public void backPrincipal() {
		
		Intent act = new Intent(this, MainPrincipal.class);
		startActivity(act);
	}

}

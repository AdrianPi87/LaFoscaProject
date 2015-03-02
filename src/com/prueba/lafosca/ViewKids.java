package com.prueba.lafosca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewKids extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewkids);
		
		for(int i = 0; i < HttpClientComunication.getLengthAgeArray(); i++) {
			LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutKids);
			TextView tv = new TextView(ViewKids.this);						            
            tv.setText(HttpClientComunication.getArrayNamePos(i) + " : " + HttpClientComunication.getArrayAgePos(i));
            linearLayout.addView(tv);
		}
	}

	public void goKids() {
		
		Intent act = new Intent(this, Activities.class);
		startActivity(act);
		
	}
}

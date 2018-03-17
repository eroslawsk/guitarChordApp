package csc212.guitarchord;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class InstructActivity extends Activity {
	
	private ChordActivity preferences;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.instruct);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.instruct, menu);
        return true;

    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		
		case R.id.menuInstruct:
			return true;
			
		case R.id.menuFinder:
			startActivity(new Intent(getApplicationContext(),
					ChordActivity.class));
					return true;
					
		case R.id.menuQuit:
			preferences.clearPreferences();
			finish();
			System.exit(0);
	
			
		default:
			return super.onOptionsItemSelected(item);
		}
		}
}
package csc212.guitarchord;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class ChordActivity extends Activity {
	private static final String PREFS_NAME = "ChordData";
	
	private TextView currentChord;
	private TextView currentMajorMinor;
	String chordList[];
	String majorMinor[];
	private String chord;
	private String maj;
	private GraphView gview;
	private int indexChord;
	private int indexKey;
	
	public String getChord() {
		return chord;
	}

	public void setChord(String chord) {
		this.chord = chord;
	}

	public String getMaj() {
		return maj;
	}

	public void setMaj(String maj) {
		this.maj = maj;
	}

	public int getIndexChord() {
		return indexChord;
	}

	public void setIndexChord(int indexChord) {
		this.indexChord = indexChord;
	}

	public int getIndexKey() {
		return indexKey;
	}

	public void setIndexKey(int indexKey) {
		this.indexKey = indexKey;
	}

	@Override
	protected void onPause() {
	    super.onPause();
	    Log.i(getClass().getSimpleName(), "onPause");
	    putChordPreferences();
	}

	@Override
	protected void onResume() {
	    super.onResume();
	    Log.i(getClass().getSimpleName(), "onResume");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(getClass().getSimpleName(), "onDestroy");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i(getClass().getSimpleName(), "onRestart");
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.i(getClass().getSimpleName(), "onStart");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i(getClass().getSimpleName(), "onStop");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		  Log.i(getClass().getSimpleName(), "onCreate");
		setContentView(R.layout.finder);
		
	Spinner chordSelection = (Spinner) findViewById(R.id.chordSpnr);
	Spinner majorSelection = (Spinner) findViewById(R.id.majMinSpnr);
	currentChord = (TextView) findViewById(R.id.currentChord);
	currentMajorMinor = (TextView) findViewById(R.id.currentMajMin);
	gview = (GraphView) findViewById(R.id.GraphView);
	
	ArrayAdapter<CharSequence> adapter = 
			ArrayAdapter.createFromResource(this, R.array.chordList, android.R.layout.simple_spinner_item);

	ArrayAdapter<CharSequence> adapter1 =
			ArrayAdapter.createFromResource(this, R.array.majorMinor, android.R.layout.simple_spinner_item);
		
	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	
	chordSelection.setAdapter(adapter);
	majorSelection.setAdapter(adapter1);
	
	chordSelection.setOnItemSelectedListener(new OnItemSelectedListener(){
	
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3){
		
			indexChord = arg0.getSelectedItemPosition();
		
			chordList = getResources().getStringArray(R.array.chordList);
		
		    chord = chordList[indexChord];
		    
		    setChord(chord);
		    
		    Update();   
		    
		}
		
		@Override
		public void onNothingSelected(AdapterView<?> parent) {

			
		}
	});	
	
	majorSelection.setOnItemSelectedListener(new OnItemSelectedListener(){
		
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3){
		
	    indexKey = arg0.getSelectedItemPosition();
		
		majorMinor = getResources().getStringArray(R.array.majorMinor);
		
		maj = majorMinor[indexKey];
		
		setMaj(maj);
		
		Update();
		
	}
	
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}	
	});
	getChordPreferences();
	}
	
public void Update(){
	
	currentChord.setText(getChord());
		
	currentMajorMinor.setText(getMaj());	
	
	   gview.setIndexChord(indexChord);
		
		gview.setIndexKey(indexKey);
		
		gview.invalidate();
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finder, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch(item.getItemId()){
		
		case R.id.menuFinder:
			return true;
			
		case R.id.menuInstruct:
			startActivity(new Intent(getApplicationContext(),
					InstructActivity.class));
			return true;
			
		case R.id.menuQuit:
			clearPreferences();
			finish();
			System.exit(0);
			
		
		default:
		return super.onOptionsItemSelected(item);
	}
}

	private void putChordPreferences(){
		SharedPreferences state = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
		
		SharedPreferences.Editor editor = state.edit();
	    
		editor.putString(getPackageName() + ".chord", getChord());
		editor.putString(getPackageName() + ".maj", getMaj());
		editor.putInt(getPackageName() + ".indexChord", getIndexChord());
		editor.putInt(getPackageName() + ".indexKey", getIndexKey());
		
		editor.commit();
	}
	
	private void getChordPreferences(){
		
		SharedPreferences prefState = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        		
		String chord = prefState.getString(getPackageName() + ".chord", "");
		String maj = prefState.getString(getPackageName() + ".maj", "");
		int indexChord = prefState.getInt(getPackageName() + ".indexChord", 0);
		int indexKey = prefState.getInt(getPackageName() + ".indexKey", 0);
		
		Spinner chordSelection = (Spinner) findViewById(R.id.chordSpnr);
		Spinner majorSelection = (Spinner) findViewById(R.id.majMinSpnr);
		
		chordSelection.setSelection(indexChord);
		majorSelection.setSelection(indexKey);
		setChord(chord);
		setMaj(maj);
		setIndexChord(indexChord);
		setIndexKey(indexKey);
		
		
	}
	public void clearPreferences(){
		SharedPreferences prefState = getSharedPreferences(PREFS_NAME,

                MODE_PRIVATE);

         SharedPreferences.Editor editor = prefState.edit();

         editor.clear();

         editor.commit();

	}
}
	

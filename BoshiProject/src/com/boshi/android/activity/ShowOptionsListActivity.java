
package com.boshi.android.activity;

import com.boshi.android.schedule.R;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

public class ShowOptionsListActivity extends ListActivity
{

	private Bundle bundle;
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		this.getWindow( ).setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN );
		Resources res = getResources( );
		Intent intent = getIntent();
		if(intent != null)
		{
			bundle = intent.getExtras();
			String[] arrays = res.getStringArray( bundle.getInt("contentKey") );
			ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,
					android.R.layout.simple_list_item_single_choice,
					arrays );

			setListAdapter( adapter );
		}
		
	}
	
	protected void onListItemClick( ListView l, View v, int position, long id )
	{
		switch(bundle.getInt( "contentKey" ))
		{
			case R.array.importance:
				bundle.putInt( "importance", position );
			case R.array.remind:
				bundle.putInt( "remind" , position );
			case R.array.repeat:
				bundle.putInt( "repeat", position );
			case R.array.contentVisibility:
				bundle.putInt( "contentVisibility", position );
			case R.array.scheduleVisibility:
				bundle.putInt( "scheduleVisibility", position );
		}
		Intent intent = getIntent();
		intent.putExtras( bundle );
		setResult(DesignConstants.singleChoiceListActivity,intent);
		finish();
	}

}

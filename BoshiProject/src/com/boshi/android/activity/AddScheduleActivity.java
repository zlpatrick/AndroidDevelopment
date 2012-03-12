
package com.boshi.android.activity;

import com.boshi.android.schedule.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

public class AddScheduleActivity extends Activity
{

	private final int START_DATE = 0;
	private final int START_TIME = 1;
	private final int END_DATE = 2;
	private final int END_TIME = 3;

	private int startYear;
	private int startMonth;
	private int startDay;
	private int startHour;
	private int startMinute;

	private int endYear;
	private int endMonth;
	private int endDay;
	private int endHour;
	private int endMinute;
	private int windowWidth;
	private Bundle bundles;


	private void populateControlListensers( )
	{
		TextView tx = (TextView) findViewById( R.id.inputImportance );
		tx.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				Intent intent = new Intent( AddScheduleActivity.this,
						ShowOptionsListActivity.class );

				bundles.putInt( "contentKey", R.array.importance );
				intent.putExtras( bundles );
				startActivityForResult( intent , DesignConstants.singleChoiceListActivity );
			}
		} );

		tx = (TextView) findViewById( R.id.inputRemind );
		tx.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				Intent intent = new Intent( AddScheduleActivity.this,
						ShowOptionsListActivity.class );

				bundles.putInt( "contentKey", R.array.remind );
				intent.putExtras( bundles );

				startActivityForResult( intent , DesignConstants.singleChoiceListActivity );
			}
		} );

		tx = (TextView) findViewById( R.id.inputRepeat );
		tx.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				Intent intent = new Intent( AddScheduleActivity.this,
						ShowOptionsListActivity.class );

				bundles.putInt( "contentKey", R.array.repeat );
				intent.putExtras( bundles );
				startActivityForResult( intent , DesignConstants.singleChoiceListActivity );
			}
		} );

		tx = (TextView) findViewById( R.id.inputContentVisibility );
		tx.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				Intent intent = new Intent( AddScheduleActivity.this,
						ShowOptionsListActivity.class );

				bundles.putInt( "contentKey", R.array.contentVisibility );
				intent.putExtras( bundles );
				startActivityForResult( intent , DesignConstants.singleChoiceListActivity );
			}
		} );
		tx = (TextView) findViewById( R.id.inputScheduleVisibility );
		tx.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				Intent intent = new Intent( AddScheduleActivity.this,
						ShowOptionsListActivity.class );

				bundles.putInt( "contentKey", R.array.scheduleVisibility );
				intent.putExtras( bundles );
				startActivityForResult( intent , DesignConstants.singleChoiceListActivity );
			}
		} );

		Button addButton = (Button) findViewById( R.id.addScheduleButton );
		addButton.setBackgroundColor( Color.RED );
		addButton.setTextColor( Color.WHITE );
		addButton.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{

			}
		} );
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		if(resultCode == DesignConstants.singleChoiceListActivity)
		{
			bundles = data.getExtras( );
			Resources res = getResources( );
			String[] arrays = res.getStringArray( bundles.getInt("contentKey") );
			
			switch( bundles.getInt( "contentKey" ))
			{
				case R.array.importance:
					TextView tx = (TextView) findViewById( R.id.inputImportance );
					tx.setText( tx.getText( ) + "  " + arrays[bundles.getInt( "importance" )] );
					break;
				case R.array.remind:
					tx = (TextView) findViewById( R.id.inputRemind );
					tx.setText( tx.getText( ) + "  " + arrays[bundles.getInt( "remind" )] );
					break;
				case R.array.repeat:
					tx = (TextView) findViewById( R.id.inputRepeat );
					tx.setText( tx.getText( ) + "  " + arrays[bundles.getInt( "repeat" )] );
					break;
				case R.array.contentVisibility:
					tx = (TextView) findViewById( R.id.inputContentVisibility );
					tx.setText( tx.getText( ) + "  " + arrays[bundles.getInt( "contentVisibility" )] );
					break;
				case R.array.scheduleVisibility:
					tx = (TextView) findViewById( R.id.inputScheduleVisibility );
					tx.setText( tx.getText( ) + "  " + arrays[bundles.getInt( "scheduleVisibility" )] );
					break;
			}
		}
	}


	private void populateDateTime( )
	{
		Time time = new Time( );
		time.setToNow( );
		startYear = time.year;
		startMonth = time.month;
		startDay = time.monthDay;

		endYear = startYear;
		endMonth = startMonth;
		endDay = startDay;

		
		String dateText = startYear
				+ "年" + String.valueOf( startMonth + 1 ) + "月" + startDay + "日";
		Button button = (Button) findViewById( R.id.startDate );
		button.setText( dateText );
		button.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				showDialog( START_DATE );
			}
		} );
		LayoutParams params = (LayoutParams)button.getLayoutParams( );
		params.width = windowWidth * 2 / 3;
		

		button = (Button) findViewById( R.id.endDate );
		button.setText( dateText );
		button.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				showDialog( END_DATE );
			}
		} );
		params = (LayoutParams)button.getLayoutParams( );
		params.width = windowWidth * 2 / 3;

		startHour = time.hour;
		startMinute = 0;
		endHour = startHour;
		endMinute = 30;
		String timeText = startHour + ":00";
		button = (Button) findViewById( R.id.startTime );
		button.setText( timeText );
		button.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				showDialog( START_TIME );
			}
		} );
		params = (LayoutParams)button.getLayoutParams( );
		params.width = windowWidth / 3;

		timeText = startHour + ":30";
		button = (Button) findViewById( R.id.endTime );
		button.setText( timeText );
		button.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				showDialog( END_TIME );
			}
		} );
		params = (LayoutParams)button.getLayoutParams( );
		params.width = windowWidth / 3;
		
		CheckBox checkbox = (CheckBox)findViewById(R.id.checkBox1);
		checkbox.setOnCheckedChangeListener( new OnCheckedChangeListener(){

			public void onCheckedChanged( CompoundButton buttonView, boolean isChecked )
			{
				if(isChecked)
				{
					Button button = (Button) findViewById( R.id.startDate );
					LayoutParams param = button.getLayoutParams( );
					param.width = windowWidth;
					button.setLayoutParams( param );
					
					button = (Button) findViewById( R.id.endDate );
					param = button.getLayoutParams( );
					param.width = windowWidth;
					button.setLayoutParams( param );
					
					button = (Button) findViewById( R.id.startTime );
					button.setVisibility( Button.INVISIBLE );
					
					button = (Button) findViewById( R.id.endTime );
					button.setVisibility( Button.INVISIBLE );
				}
				else
				{
					Button button = (Button) findViewById( R.id.startDate );
					LayoutParams param = button.getLayoutParams( );
					param.width = windowWidth*2/3;
					button.setLayoutParams( param );
					
					button = (Button) findViewById( R.id.endDate );
					param = button.getLayoutParams( );
					param.width = windowWidth*2/3;
					button.setLayoutParams( param );
					
					button = (Button) findViewById( R.id.startTime );
					button.setVisibility( Button.VISIBLE );
					param = button.getLayoutParams( );
					param.width = windowWidth/3;
					button.setLayoutParams( param );
					
					button = (Button) findViewById( R.id.endTime );
					button.setVisibility( Button.VISIBLE );
					param = button.getLayoutParams( );
					param.width = windowWidth/3;
					button.setLayoutParams( param );
					
				}
				
			}
			
		});
	}

	private DatePickerDialog.OnDateSetListener mStartDateSetListener = new DatePickerDialog.OnDateSetListener( ) {

		public void onDateSet( DatePicker view, int year, int month,
				int dayOfMonth )
		{
			startYear = year;
			startMonth = month;
			startDay = dayOfMonth;
			String dateText = startYear
					+ "年" + String.valueOf( startMonth + 1 ) + "月" + startDay
					+ "日";
			Button button = (Button) findViewById( R.id.startDate );
			button.setText( dateText );
		}
	};

	private TimePickerDialog.OnTimeSetListener mStartTimeSetListener = new TimePickerDialog.OnTimeSetListener( ) {

		public void onTimeSet( TimePicker view, int hourOfDay, int minute )
		{
			startHour = hourOfDay;
			startMinute = minute;
			String timeText = hourOfDay
					+ ":"
					+ ( minute < 10 ? ( "0" + minute )
							: String.valueOf( minute ) );
			Button button = (Button) findViewById( R.id.startTime );
			button.setText( timeText );
		}
	};

	private DatePickerDialog.OnDateSetListener mEndDateSetListener = new DatePickerDialog.OnDateSetListener( ) {

		public void onDateSet( DatePicker view, int year, int month,
				int dayOfMonth )
		{
			endYear = year;
			endMonth = month;
			endDay = dayOfMonth;
			String dateText = endYear
					+ "年" + String.valueOf( endMonth + 1 ) + "月" + endDay + "日";
			Button button = (Button) findViewById( R.id.endDate );
			button.setText( dateText );
		}
	};

	private TimePickerDialog.OnTimeSetListener mEndTimeSetListener = new TimePickerDialog.OnTimeSetListener( ) {

		public void onTimeSet( TimePicker view, int hourOfDay, int minute )
		{
			endHour = hourOfDay;
			endMinute = minute;
			String timeText = hourOfDay
					+ ":"
					+ ( minute < 10 ? ( "0" + minute )
							: String.valueOf( minute ) );
			Button button = (Button) findViewById( R.id.endTime );
			button.setText( timeText );
		}
	};

	protected Dialog onCreateDialog( int id )
	{
		switch ( id )
		{
			case START_DATE :
				return new DatePickerDialog( this,
						mStartDateSetListener,
						startYear,
						startMonth,
						startDay );
			case START_TIME :
				return new TimePickerDialog( this,
						mStartTimeSetListener,
						startHour,
						startMinute,
						true );
			case END_DATE :
				return new DatePickerDialog( this,
						mEndDateSetListener,
						endYear,
						endMonth,
						endDay );
			case END_TIME :
				return new TimePickerDialog( this,
						mEndTimeSetListener,
						endHour,
						endMinute,
						true );
		}
		return null;
	}

	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		this.getWindow( ).setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN );
		setContentView( R.layout.addschedule );
		windowWidth = this.getWindowManager( ).getDefaultDisplay( ).getWidth( );

		LinearLayout whole = (LinearLayout) findViewById( R.id.whole );
		whole.setBackgroundColor( Color.GRAY );

		Intent intent = getIntent();
		if(intent!=null)
		{
			if(intent.getExtras( )!=null)
				bundles = intent.getExtras( );
			else 
				bundles = new Bundle();
		}
		populateDateTime( );
		populateControlListensers( );

	}
}

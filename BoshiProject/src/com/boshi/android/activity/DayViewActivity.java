
package com.boshi.android.activity;

import java.text.ParseException;
import java.util.Calendar;

import com.boshi.android.schedule.R;
import com.boshi.android.tools.Lunar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class DayViewActivity extends Activity
{

	private LinearLayout schedule, personal, friend, discount, entertainment,
			more, middle, whole, daylight, morning, afternoon, night;
	
	private int startHour;
	private Calendar startDay;

	private void populateBottomMenuProperties( )
	{
		schedule = (LinearLayout) findViewById( R.id.home );
		schedule.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				schedule.setBackgroundResource( R.drawable.tab_two_highlight );
				personal.setBackgroundResource( R.drawable.tab_one_normal );
				friend.setBackgroundResource( R.drawable.tab_one_normal );
				
				discount.setBackgroundResource( R.drawable.tab_one_normal );
				entertainment.setBackgroundResource( R.drawable.tab_one_normal );
			}
		} );
		schedule.setBackgroundResource( R.drawable.tab_two_highlight );

		personal = (LinearLayout) findViewById( R.id.personal );
		personal.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				personal.setBackgroundResource( R.drawable.tab_two_highlight );
				schedule.setBackgroundResource( R.drawable.tab_one_normal );
				friend.setBackgroundResource( R.drawable.tab_one_normal );
				
				discount.setBackgroundResource( R.drawable.tab_one_normal );
				entertainment.setBackgroundResource( R.drawable.tab_one_normal );
				Intent intent = new Intent(DayViewActivity.this,MyInfoActivity.class);
				startActivity(intent);
			}
		} );

		discount = (LinearLayout) findViewById( R.id.discount );
		discount.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				personal.setBackgroundResource( R.drawable.tab_one_normal );
				schedule.setBackgroundResource( R.drawable.tab_one_normal );
				friend.setBackgroundResource( R.drawable.tab_one_normal );
				
				discount.setBackgroundResource( R.drawable.tab_two_highlight );
				entertainment.setBackgroundResource( R.drawable.tab_one_normal );
			}
		} );

		entertainment = (LinearLayout) findViewById( R.id.entertainment );
		entertainment.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				personal.setBackgroundResource( R.drawable.tab_one_normal );
				schedule.setBackgroundResource( R.drawable.tab_one_normal );
				friend.setBackgroundResource( R.drawable.tab_one_normal );
				
				discount.setBackgroundResource( R.drawable.tab_one_normal );
				entertainment.setBackgroundResource( R.drawable.tab_two_highlight );
			}
		} );

		friend = (LinearLayout) findViewById( R.id.friend );
		friend.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				friend.setBackgroundResource( R.drawable.tab_two_highlight );
				schedule.setBackgroundResource( R.drawable.tab_one_normal );
				personal.setBackgroundResource( R.drawable.tab_one_normal );
				
				discount.setBackgroundResource( R.drawable.tab_one_normal );
				entertainment.setBackgroundResource( R.drawable.tab_one_normal );
				
				Intent intent = new Intent(DayViewActivity.this,FriendTabActivity.class);
				startActivity(intent);
			}
		} );
	}

	protected void populateTopTitleProperties( )
	{
		TextView tx = (TextView) findViewById( R.id.blogname1 );
		LayoutParams params = (LayoutParams) tx.getLayoutParams( );
		params.width = getWindowManager( ).getDefaultDisplay( ).getWidth( )
				- 30 - DesignConstants.topLeftButtonWidth
				- DesignConstants.topRightButtonWidth;
		tx.setLayoutParams( params );

		ImageView img = (ImageView) findViewById( R.id.addScheduleImg );
		img.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				Intent intent = new Intent( DayViewActivity.this,
						AddScheduleActivity.class );
				startActivity( intent );

			}
		} );

	}

	private void populateMiddleCompositeProperties( )
	{
		whole = (LinearLayout) findViewById( R.id.whole );
		whole.setBackgroundColor( Color.WHITE );
		middle = (LinearLayout) findViewById( R.id.middle );
		LayoutParams params = (LayoutParams) middle.getLayoutParams( );
		int topHeight = ( (LinearLayout) findViewById( R.id.top ) ).getLayoutParams( ).height;
		int bottomHeight = ( (LinearLayout) findViewById( R.id.bottom ) ).getLayoutParams( ).height;
		params.height = getWindowManager( ).getDefaultDisplay( ).getHeight( )
				- topHeight - bottomHeight;
		middle.setLayoutParams( params );

		populateDateTextPart( );
		populateHourTextPart( );
		populateScheduleViewPart( );
		addPreviousNext5DaysListener();
	}
	
	private void addPreviousNext5DaysListener()
	{
		TextView tx = (TextView)findViewById(R.id.previous5day);
		tx.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				startDay.add( Calendar.DAY_OF_YEAR, -5 );
				LinearLayout main = (LinearLayout) findViewById( R.id.scheduleView );
				main.removeAllViews( );
				populateScheduleViewPart();
			}
		} );
		
		tx = (TextView)findViewById(R.id.next5day);
		tx.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				startDay.add( Calendar.DAY_OF_YEAR, 5 );
				LinearLayout main = (LinearLayout) findViewById( R.id.scheduleView );
				main.removeAllViews( );
				populateScheduleViewPart();
			}
		} );
		
		tx = (TextView)findViewById(R.id.currentday);
		tx.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				Time time = new Time( );
				time.setToNow( );
				int year = time.year;
				int month = time.month+1;
				int day = time.monthDay;
				Calendar cal = Calendar.getInstance();
				String dateText = year + "年" + month + "月" + day + "日";
				try 
				{
					cal.setTime(Lunar.chineseDateFormat.parse(dateText));
				} 
				catch (ParseException e) {
				}
				startDay = cal;
				LinearLayout main = (LinearLayout) findViewById( R.id.scheduleView );
				main.removeAllViews( );
				populateScheduleViewPart();
			}
		} );
	}
	
	private void populateScheduleViewPart( )
	{
		LinearLayout main = (LinearLayout) findViewById( R.id.scheduleView );

		for ( int i = 0; i < 5; i++ )
		{
			int month = startDay.get( Calendar.MONTH ) + 1;
			int day = startDay.get( Calendar.DAY_OF_MONTH );
			LinearLayout subMain = new LinearLayout( this );
			LayoutParams params = new LayoutParams( LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT );
			params.setMargins( 1, 1, 1, 1 );
			subMain.setLayoutParams( params );
			subMain.setBackgroundResource( R.drawable.detailplancorner );
			subMain.setPadding( 0, 0, 0, 0 );
			subMain.setOrientation( LinearLayout.HORIZONTAL );
			main.addView( subMain );

			TextView tx = new TextView( this );
			params = new LayoutParams( 15, LayoutParams.FILL_PARENT );
			params.gravity = Gravity.CENTER;
			params.setMargins( 2, 2, 2, 2 );
			tx.setLayoutParams( params );
			tx.setBackgroundResource( R.drawable.leftcorner );
			tx.setGravity( Gravity.CENTER );
			tx.setTextSize( 9 );
			tx.setTextColor( Color.WHITE );
			tx.setText( month + "月" + day + "日" );
			subMain.addView( tx );

			LinearLayout rightPart = new LinearLayout( this );
			params = new LayoutParams( LayoutParams.FILL_PARENT,
					LayoutParams.FILL_PARENT );
			params.setMargins( 1, 1, 1, 1 );
			params.gravity = Gravity.CENTER;
			rightPart.setLayoutParams( params );
			rightPart.setPadding( 0, 0, 0, 0 );
			rightPart.setOrientation( LinearLayout.VERTICAL );
			subMain.addView( rightPart );

			LinearLayout allDayPart = new LinearLayout( this );
			params = new LayoutParams( LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT );
			params.setMargins( 1, 1, 1, 1 );
			allDayPart.setLayoutParams( params );
			allDayPart.setPadding( 0, 0, 0, 0 );

			allDayPart.setOrientation( LinearLayout.HORIZONTAL );
			rightPart.addView( allDayPart );

			tx = new TextView( this );
			params = new LayoutParams( 15, LayoutParams.FILL_PARENT );
			params.gravity = Gravity.CENTER;
			params.setMargins( 1, 1, 1, 1 );
			tx.setLayoutParams( params );
			tx.setGravity( Gravity.CENTER );
			tx.setTextSize( 9 );
			tx.setTextColor( Color.WHITE );
			tx.setText( "全天" );
			tx.setBackgroundResource( R.drawable.leftcorner );
			allDayPart.addView( tx );

			LinearLayout allDayPartRight = new LinearLayout( this );
			params = new LayoutParams( LayoutParams.FILL_PARENT,
					LayoutParams.FILL_PARENT );
			params.setMargins( 1, 1, 1, 1 );
			params.gravity = Gravity.CENTER;
			allDayPartRight.setLayoutParams( params );
			allDayPartRight.setGravity( Gravity.CENTER );
			allDayPartRight.setPadding( 0, 0, 0, 0 );
			allDayPartRight.setOrientation( LinearLayout.VERTICAL );
			allDayPart.addView( allDayPartRight );

			tx = new TextView( this );
			params = new LayoutParams( LayoutParams.FILL_PARENT, 20 );
			params.setMargins( 1, 1, 1, 1 );
			params.gravity = Gravity.CENTER;
			tx.setLayoutParams( params );
			tx.setTextColor( Color.WHITE );
			tx.setGravity( Gravity.LEFT );
			tx.setText( "徐家汇商圈打折" );
			tx.setTextSize( 10 );
			tx.setBackgroundColor( Color.BLUE );
			allDayPartRight.addView( tx );

			tx = new TextView( this );
			params = new LayoutParams( LayoutParams.FILL_PARENT, 20 );
			params.setMargins( 1, 1, 1, 1 );
			params.gravity = Gravity.CENTER;
			tx.setLayoutParams( params );
			tx.setTextColor( Color.WHITE );
			tx.setGravity( Gravity.LEFT );
			tx.setText( "ZARA全场对折" );
			tx.setTextSize( 10 );
			tx.setBackgroundColor( Color.BLUE );
			allDayPartRight.addView( tx );
			
			tx = new TextView( this );
			params = new LayoutParams( LayoutParams.FILL_PARENT, 20 );
			params.setMargins( 1, 1, 1, 1 );
			params.gravity = Gravity.CENTER;
			tx.setLayoutParams( params );
			tx.setTextColor( Color.WHITE );
			tx.setGravity( Gravity.LEFT );
			tx.setText( "浦东第一八佰伴要卖疯啦" );
			tx.setTextSize( 10 );
			tx.setBackgroundColor( Color.BLUE );
			allDayPartRight.addView( tx );

			LinearLayout DetailPart = new LinearLayout( this );
			params = new LayoutParams( LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT );
			params.setMargins( 1, 1, 1, 1 );
			DetailPart.setLayoutParams( params );
			DetailPart.setPadding( 0, 0, 0, 0 );

			DetailPart.setOrientation( LinearLayout.HORIZONTAL );
			rightPart.addView( DetailPart );

			tx = new TextView( this );
			params = new LayoutParams( 15, LayoutParams.FILL_PARENT );
			params.gravity = Gravity.CENTER;
			params.setMargins( 1, 1, 1, 1 );
			tx.setLayoutParams( params );
			tx.setGravity( Gravity.CENTER );
			tx.setTextSize( 9 );
			tx.setTextColor( Color.WHITE );
			tx.setText( "分时" );
			tx.setBackgroundResource( R.drawable.leftcorner );
			DetailPart.addView( tx );

			LinearLayout DetailPartRight = new LinearLayout( this );
			params = new LayoutParams( LayoutParams.FILL_PARENT,
					LayoutParams.FILL_PARENT );
			params.setMargins( 1, 1, 1, 1 );
			DetailPartRight.setLayoutParams( params );
			DetailPartRight.setPadding( 0, 0, 0, 0 );
			DetailPartRight.setOrientation( LinearLayout.VERTICAL );
			DetailPart.addView( DetailPartRight );

			tx = new TextView( this );
			params = new LayoutParams( LayoutParams.FILL_PARENT, 40 );
			params.setMargins( 120, 1, 80, 1 );
			params.gravity = Gravity.CENTER;
			tx.setLayoutParams( params );
			tx.setTextColor( Color.BLACK );
			tx.setGravity( Gravity.LEFT );
			tx.setText( "陪朋友去逛街" );
			tx.setTextSize( 10 );
			tx.setBackgroundColor( Color.YELLOW );
			DetailPartRight.addView( tx );
			
			tx = new TextView( this );
			params = new LayoutParams( LayoutParams.FILL_PARENT, 40 );
			params.setMargins( 80, 1, 200, 1 );
			params.gravity = Gravity.CENTER;
			tx.setLayoutParams( params );
			tx.setTextColor( Color.BLACK );
			tx.setGravity( Gravity.LEFT );
			tx.setText( "和Patrick去吃饭" );
			tx.setTextSize( 10 );
			tx.setBackgroundColor( Color.YELLOW );
			DetailPartRight.addView( tx );
			
			startDay.add( Calendar.DAY_OF_YEAR, 1 );
		}
		startDay.add( Calendar.DAY_OF_YEAR, -5 );
	}

	private void populateHourTextPart( )
	{
		TextView text = (TextView) findViewById( R.id.hour1 );
		text.setText( String.valueOf(startHour)+"点"  );
		text.setBackgroundResource( R.drawable.tabcorner );
		
		text = (TextView) findViewById( R.id.hour2 );
		text.setText( String.valueOf(startHour+1)+"点" );
		text.setBackgroundResource( R.drawable.tabcorner );
		
		text = (TextView) findViewById( R.id.hour3 );
		text.setText( String.valueOf(startHour+2)+"点"  );
		text.setBackgroundResource( R.drawable.tabcorner );
		
		text = (TextView) findViewById( R.id.hour4 );
		text.setText( String.valueOf(startHour+3)+"点"  );
		text.setBackgroundResource( R.drawable.tabcorner );
		
		text = (TextView) findViewById( R.id.hour5 );
		text.setText( String.valueOf(startHour+4)+"点"  );
		text.setBackgroundResource( R.drawable.tabcorner );
		
		text = (TextView) findViewById( R.id.hour6 );
		text.setText( String.valueOf(startHour+5)+"点"  );
		text.setBackgroundResource( R.drawable.tabcorner );
	}

	private void populateDateTextPart( )
	{
		Time time = new Time( );
		time.setToNow( );
		int year = time.year;
		int month = time.month+1;
		int day = time.monthDay;
		Calendar cal = Calendar.getInstance();
		String dateText = year + "年" + month + "月" + day + "日";
		try 
		{
			cal.setTime(Lunar.chineseDateFormat.parse(dateText));
		} 
		catch (ParseException e) {
		}
		Lunar lunar = new Lunar(cal);
		startHour = time.hour;
		startDay = cal;
		
		TextView text = (TextView) findViewById( R.id.dateText );
		text.setText( "今天是 " + dateText + "  农历 " + lunar.toString( ) );
	}

	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		this.getWindow( ).setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN );
		setContentView( R.layout.dayview );
	

		populateTopTitleProperties( );
		populateBottomMenuProperties( );
		populateMiddleCompositeProperties( );
	}
	
	private void initConfigVariable( )
	{
		Time time = new Time( );
		time.setToNow( );
		startHour = time.hour;
	}

	public boolean onCreateOptionsMenu( Menu menu )
	{
		MenuInflater inflater = getMenuInflater( );

		inflater.inflate( R.menu.bottom_option_menu, menu );
		return super.onCreateOptionsMenu( menu );
	}

	public boolean onOptionsItemSelected( MenuItem item )
	{
		int itemID = item.getItemId( );

		switch ( itemID )
		{
			case R.id.dayView :
				break;
			case R.id.weekView :
				break;
			case R.id.monthView :
				Intent intent = new Intent( DayViewActivity.this,
						MonthViewActivity.class );
				startActivity( intent );
				break;
			case R.id.exit:
				android.os.Process.killProcess(android.os.Process.myPid());
				System.exit(0);   
				break;
		}
		return super.onOptionsItemSelected( item );
	}

}
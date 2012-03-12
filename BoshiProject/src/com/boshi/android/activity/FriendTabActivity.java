package com.boshi.android.activity;

import com.boshi.android.schedule.R;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class FriendTabActivity extends Activity 
{
	private LinearLayout schedule, personal, friend, discount, entertainment, more, middle, whole;
	private void populateTopTitleProperties( )
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
				Intent intent = new Intent( FriendTabActivity.this,
						AddScheduleActivity.class );
				startActivity( intent );

			}
		} );

	}

	private void populateBottomMenuProperties( )
	{
		int width = ( getWindowManager( ).getDefaultDisplay( ).getWidth( ) ) / 6;
		schedule = (LinearLayout) findViewById( R.id.home );
		schedule.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				schedule.setBackgroundResource( R.drawable.tab_two_highlight );
				personal.setBackgroundResource( R.drawable.tab_one_normal );
				friend.setBackgroundResource( R.drawable.tab_one_normal );
				discount.setBackgroundResource( R.drawable.tab_one_normal );
				entertainment.setBackgroundResource( R.drawable.tab_one_normal );
				Intent intent = new Intent(FriendTabActivity.this,DayViewActivity.class);
				startActivity(intent);
			}
		} );
		
		LayoutParams params = (LayoutParams) schedule.getLayoutParams( );
		params.width = width;
		schedule.setLayoutParams( params );

		personal = (LinearLayout) findViewById( R.id.personal );
		personal.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				personal.setBackgroundResource( R.drawable.tab_two_highlight );
				schedule.setBackgroundResource( R.drawable.tab_one_normal );
				friend.setBackgroundResource( R.drawable.tab_one_normal );
				discount.setBackgroundResource( R.drawable.tab_one_normal );
				entertainment.setBackgroundResource( R.drawable.tab_one_normal );
				Intent intent = new Intent(FriendTabActivity.this,MyInfoActivity.class);
				startActivity(intent);
			}
		} );
		params = (LayoutParams) personal.getLayoutParams( );
		params.width = width;
		personal.setLayoutParams( params );

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

		params = (LayoutParams) discount.getLayoutParams( );
		params.width = width;
		discount.setLayoutParams( params );

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
		params = (LayoutParams) entertainment.getLayoutParams( );
		params.width = width;
		entertainment.setLayoutParams( params );

		friend = (LinearLayout) findViewById( R.id.friend );
		friend.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				friend.setBackgroundResource( R.drawable.tab_two_highlight );
				schedule.setBackgroundResource( R.drawable.tab_one_normal );
				personal.setBackgroundResource( R.drawable.tab_one_normal );
				
				discount.setBackgroundResource( R.drawable.tab_one_normal );
				entertainment.setBackgroundResource( R.drawable.tab_one_normal );
			}
		} );
		friend.setBackgroundResource( R.drawable.tab_two_highlight );
		params = (LayoutParams) friend.getLayoutParams( );
		params.width = width;
		friend.setLayoutParams( params );

//		more = (LinearLayout) findViewById( R.id.more );
//		more.setOnClickListener( new OnClickListener( ) {
//
//			public void onClick( View v )
//			{
//				more.setBackgroundResource( R.drawable.tab_two_highlight );
//				schedule.setBackgroundResource( R.drawable.tab_one_normal );
//				personal.setBackgroundResource( R.drawable.tab_one_normal );
//				friend.setBackgroundResource( R.drawable.tab_one_normal );
//				discount.setBackgroundResource( R.drawable.tab_one_normal );
//				entertainment.setBackgroundResource( R.drawable.tab_one_normal );
//
//			}
//		} );
//		params = (LayoutParams) more.getLayoutParams( );
//		params.width = width;
//		more.setLayoutParams( params );
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
		TabHost tabHost = (TabHost) findViewById(R.id.tabhost);  
		tabHost.setup( );
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("ºÃÓÑ")
				.setContent(R.id.view1));
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("¶¯Ì¬")
				.setContent(R.id.view2));
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("ÆÀÂÛ")
				.setContent(R.id.view3));
	}
	
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		this.getWindow( ).setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN );
		setContentView(R.layout.friendtab);
		
		populateTopTitleProperties( );
		populateBottomMenuProperties( );
		populateMiddleCompositeProperties( );
	}
}

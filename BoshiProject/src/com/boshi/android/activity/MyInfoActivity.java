package com.boshi.android.activity;

import com.boshi.android.schedule.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;


public class MyInfoActivity extends Activity
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
				Intent intent = new Intent( MyInfoActivity.this,
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
				Intent intent = new Intent(MyInfoActivity.this,DayViewActivity.class);
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
			}
		} );
		personal.setBackgroundResource( R.drawable.tab_two_highlight );
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
				Intent intent = new Intent(MyInfoActivity.this,FriendTabActivity.class);
				startActivity(intent);
			}
		} );
		
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
		whole.setBackgroundColor( Color.GRAY );
		middle = (LinearLayout) findViewById( R.id.middle );
		LayoutParams params = (LayoutParams) middle.getLayoutParams( );
		int topHeight = ( (LinearLayout) findViewById( R.id.top ) ).getLayoutParams( ).height;
		int bottomHeight = ( (LinearLayout) findViewById( R.id.bottom ) ).getLayoutParams( ).height;
		params.height = getWindowManager( ).getDefaultDisplay( ).getHeight( )
				- topHeight - bottomHeight;
		middle.setLayoutParams( params );
		
		TextView tx = (TextView)findViewById(R.id.oneNote);
		tx.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				popupMessageDetail();
			}
		}  );
		tx.setText( "one message" );
	}
	
	private void popupMessageDetail()
	{
		LayoutInflater factory = LayoutInflater.from( this );
		final View textEntryView = factory.inflate( R.layout.messagedetail,
				null );
		TextView tx = (TextView) textEntryView.findViewById( R.id.InvitationDetailText );
		tx.setText( "This is the detail of the Text" );
		Button button = (Button) textEntryView.findViewById( R.id.acceptInviteButton );
		
		button = (Button) textEntryView.findViewById( R.id.rejectInviteButton );
		button.setOnClickListener( new OnClickListener( ) {

			public void onClick( View v )
			{
				TextView tx = (TextView) textEntryView.findViewById( R.id.InvitationDetailText );
				tx.setText( "button Clicked" );
			}
		} );
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("ฯ๊ว้");
		AlertDialog dialog = builder.create();
		dialog.setView(  textEntryView ); 
		dialog.show();
	}
	
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		this.getWindow( ).setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN );
		setContentView(R.layout.myinfo);
		
		populateTopTitleProperties( );
		populateBottomMenuProperties( );
		populateMiddleCompositeProperties( );
		
		TextView tx = (TextView) findViewById( R.id.myinfotitle );
		tx.setBackgroundColor( Color.BLACK );
		tx.setTextColor( Color.WHITE );
		
		
	}
}

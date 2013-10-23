/*	Written by: Derek Hamel, Jacob Dobkins, Drew West
 * 	Date: 10/11/13
 */

package tjaf.monsterfight;

import tjaf.monsterfight.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
	/* Called when the activity is first created. 
	 * This is the very beginning of the game, and includes
	 * setting the window size and laying out the main menu */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// gets the display so it can be manipulated
		MFEngine.display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		// calls the Activity superclass constructor with the current state
		super.onCreate(savedInstanceState);
		// removes the title from the screen, annoying but necessary
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// sets the window to fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// display the splash screen image from a layout
		setContentView(R.layout.splashscreen);
		// forces the screen to stay in landscape
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		// sets the focus to the mainmenu class/activity
		Intent mainMenu = new Intent(MainActivity.this,
				MFMainMenu.class);
		// starts the main menu
		MainActivity.this.startActivity(mainMenu);
		// ends this activity
		MainActivity.this.finish();
	}
	
	// this is an optional options menu ***NOT IMPLEMENTED***
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

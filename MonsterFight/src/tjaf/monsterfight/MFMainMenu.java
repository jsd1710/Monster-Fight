/*	Written by: Derek Hamel, Jacob Dobkins, Drew West
 * 	Date: 10/11/13
 */

package tjaf.monsterfight;

import android.app.Activity;
import android.content.Intent;
import android.widget.ImageButton;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.os.Bundle;

public class MFMainMenu extends Activity {
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// see main activity if confused here
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splashscreen);
		
		/** Fire up background music */
		MFEngine.musicThread = new Thread(){
			public void run(){
				Intent bgmusic = new Intent(getApplicationContext(), MFMusic.class);
				startService(bgmusic);
				MFEngine.context = getApplicationContext();
			}
		};
		MFEngine.musicThread.start();
	
		final MFEngine engine = new MFEngine(); // easier to access engine
	
		/** Set menu button options */
		ImageButton start = (ImageButton)findViewById(R.id.btnStart);
		ImageButton exit = (ImageButton)findViewById(R.id.btnExit);
		start.getBackground().setAlpha(MFEngine.MENU_BUTTON_ALPHA);
		start.setHapticFeedbackEnabled(MFEngine.HAPTIC_BUTTON_FEEDBACK);
		exit.getBackground().setAlpha(MFEngine.MENU_BUTTON_ALPHA);
		exit.setHapticFeedbackEnabled(MFEngine.HAPTIC_BUTTON_FEEDBACK);
		
		start.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// Start Game 
				Intent game = new Intent(getApplicationContext(),MFGame.class);
				MFMainMenu.this.startActivity(game);
			}
		});
		
		exit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				boolean clean = false;
				clean = engine.onExit(v);
				if (clean){
					int pid= android.os.Process.myPid();
					android.os.Process.killProcess(pid);
				}
			}
		});
	}
	
}

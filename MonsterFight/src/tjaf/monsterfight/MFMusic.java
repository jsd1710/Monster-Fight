/*	Written by: Derek Hamel, Jacob Dobkins, Drew West
 * 	Date: 10/11/13
 */

package tjaf.monsterfight;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.content.Context;

public class MFMusic extends Service {
	// variable for checking if the music is running
	public static boolean isRunning = false;
	// what plays the music
	MediaPlayer player;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate(); // super service call
		// starts the music loop, sets the volume and the raw music
		setMusicOptions(this,MFEngine.LOOP_BACKGROUND_MUSIC,MFEngine.R_VOLUME,MFEngine.L_VOLUME,
				MFEngine.SPLASH_SCREEN_MUSIC);
	}
	
	// if the music options are changed within the android framework
	// it allows for the game music to be changed as well
	public void setMusicOptions(Context context, boolean isLooped, int rVolume,
			int lVolume, int soundFile){
		player = MediaPlayer.create(context, soundFile);
		player.setLooping(isLooped);
		player.setVolume(rVolume,lVolume);
	}
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		try
		{
			// try to run the music
			player.start();
			isRunning = true;
		}catch(Exception e){
			// if an exception is thrown, stop running the music
			isRunning = false;
			player.stop();
		}
		return 1;
	}
	// nothing done at the start of the music
	public void onStart(Intent intent, int startId) {
	}
	// stop the music
	public void onStop() {
		isRunning = false;
	}
	
	public IBinder onUnBind(Intent arg0) {
		return null;
	}
	// not pause-able
	public void onPause() {}
	
	// stops the music when destroyed
	@Override
	public void onDestroy() {
		player.stop();
		player.release();
	}
	// stops the music when memory is low
	@Override
	public void onLowMemory() {
		player.stop();
	}

}

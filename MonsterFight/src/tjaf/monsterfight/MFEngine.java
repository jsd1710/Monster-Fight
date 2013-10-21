package tjaf.monsterfight;

import android.content.Context;
import android.content.Intent;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

public class MFEngine {
	/*Constants that will be used in the game*/
	public static final int GAME_THREAD_DELAY = 4000;
	public static final int MENU_BUTTON_ALPHA = 0;
	public static final boolean HAPTIC_BUTTON_FEEDBACK = true;
	public static final int SPLASH_SCREEN_MUSIC = R.raw.menumusic;
	public static final int R_VOLUME = 100;
	public static final int L_VOLUME = 100;
	public static final boolean LOOP_BACKGROUND_MUSIC = true;
	public static final int GAME_THREAD_FPS_SLEEP = (1000/60);
	public static Context context;
	public static Thread musicThread;
	public static final int BACKGROUND_TEXTURE_ONE = R.drawable.fieldbg1;
	public static float SCROLL_BACKGROUND_1 = .0008f;
	public static Display display;
	public static final int PLAYER = R.drawable.runnersprite;
	public static final int PLAYER_WALK_LEFT_1 = 1;
	public static final int PLAYER_WALK_UP_1 = 2;
	public static final int PLAYER_WALK_DOWN_1 = 3;
	public static final int PLAYER_RELEASE = 5;
	public static final int PLAYER_WALK_RIGHT_1 = 4;
	public static final int PLAYER_FRAMES_BETWEEN_ANI = 5;
	public static int playerAction = 0;
	
	/*Kill game and exit*/
	@SuppressWarnings("deprecation")
	public boolean onExit(View v) {
		try{
			Intent bgmusic = new Intent(context, MFMusic.class);
			context.stopService(bgmusic);
			musicThread.stop();
			return true;
		}catch(Exception e){
			return true;
		}
	}
}

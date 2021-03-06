/*	Written by: Derek Hamel, Jacob Dobkins, Drew West
 * 	Date: 10/11/13
 */

package tjaf.monsterfight;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class MFGame extends Activity {
	private MFGameView gameView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        gameView = new MFGameView(this);
		setContentView(gameView);
	}
	@Override
	protected void onResume() {
		super.onResume();
		gameView.onResume();
	}
	@Override
	protected void onPause() {
		super.onPause();
		gameView.onPause();
	}
	
	@SuppressWarnings("deprecation")
	@Override
   	public boolean onTouchEvent(MotionEvent event) {
   		// gets the x and y of touch event
   		float x = event.getX();
        float y = event.getY();
        // checks the angle relative to the center of the screen
        float angle = (float)Math.atan((y-(MFEngine.display.getHeight()/2))/((x-(MFEngine.display.getWidth()/2))));
        
        //System.out.println(angle*180/Math.PI);
        //System.out.println(MFEngine.playerAction);
        switch (event.getAction()){
        	case MotionEvent.ACTION_DOWN:
        		// when player touches screen, scroll background in that direction
        		if(x-(MFEngine.display.getWidth()/2)<0)
        			MFGameRenderer.setScroll(true, angle, false);
        		else
        			MFGameRenderer.setScroll(true, angle, true);
        		// then check which direction to animate player character
        		if(x>=(MFEngine.display.getWidth()/2) && (angle*180/Math.PI)<45 && (angle*180/Math.PI)>=-45) 
        			MFEngine.playerAction = MFEngine.PLAYER_WALK_RIGHT_1;
        		if(x<(MFEngine.display.getWidth()/2) && (angle*180/Math.PI)>=-45 && (angle*180/Math.PI)<45)
        			MFEngine.playerAction = MFEngine.PLAYER_WALK_LEFT_1;
        		if((y<(MFEngine.display.getHeight()/2) && (angle*180/Math.PI)<-45) 
        				|| (y<(MFEngine.display.getHeight()/2) && (angle*180/Math.PI)>=45))
        			MFEngine.playerAction = MFEngine.PLAYER_WALK_UP_1;
        		if((y>=(MFEngine.display.getHeight()/2) && (angle*180/Math.PI)<-45) 
        				|| (y>=(MFEngine.display.getHeight()/2) && (angle*180/Math.PI)>=45))
        			MFEngine.playerAction = MFEngine.PLAYER_WALK_DOWN_1;
        		break;
        	case MotionEvent.ACTION_UP:
        		// stop moving the background and set the player character back to default
        		MFGameRenderer.setScroll(false, 0, false);
        		MFEngine.playerAction = MFEngine.PLAYER_RELEASE;
        		break;
        }
		return false;
    }
	

}

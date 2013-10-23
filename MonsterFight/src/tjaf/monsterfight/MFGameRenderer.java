/*	Written by: Derek Hamel, Jacob Dobkins, Drew West
 * 	Date: 10/11/13
 */

package tjaf.monsterfight;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLSurfaceView.Renderer;

public class MFGameRenderer implements Renderer{
	
	private MFBackground background = new MFBackground();
	private float bgScrollx=0, bgScrolly=0; 
	private static float angle;
	private static boolean scroll = false,hemi1 = false;
	private MFHero player1 = new MFHero();
	private int heroFrames = 0;
	private long loopStart = 0;
	private long loopEnd = 0;
	private long loopRunTime = 0 ;
	
	@Override
	public void onDrawFrame(GL10 gl) {
	// TODO Auto-generated method stub
		loopStart = System.currentTimeMillis();
		try {
			if (loopRunTime < MFEngine.GAME_THREAD_FPS_SLEEP){
				Thread.sleep(MFEngine.GAME_THREAD_FPS_SLEEP -
				loopRunTime);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		// scroll the background
		if(scroll){
			// sets the scroll increment for x and y based on direction
			if(hemi1){
				bgScrollx += (float)Math.cos(angle)*MFEngine.SCROLL_BACKGROUND_1;
				bgScrolly -= (float)Math.sin(angle)*MFEngine.SCROLL_BACKGROUND_1;
			}
			else{
				bgScrollx -= (float)Math.cos(angle)*MFEngine.SCROLL_BACKGROUND_1;
				bgScrolly += (float)Math.sin(angle)*MFEngine.SCROLL_BACKGROUND_1;
			}	
			scrollBackground1(gl);
			movePlayer1(gl);
			//System.out.println(angle);
	        //System.out.println(bgScrollx + "       " + bgScrolly);
		}else{
			scrollBackground1(gl);
			movePlayer1(gl);
		}
		
		//All other game drawing will be called here
		
		loopEnd = System.currentTimeMillis();
		loopRunTime = ((loopEnd - loopStart));
	}
	
	private void scrollBackground1(GL10 gl){
		if (bgScrollx == Float.MAX_VALUE || bgScrolly == Float.MAX_VALUE){
			bgScrollx = 0f;
			bgScrolly = 0f;
		}
		
		/*This code just resets the scale and translate of the
		Model matrix mode, we are not moving it*/
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glPushMatrix();
		gl.glScalef(1f, 1f, 1f);
		gl.glTranslatef(0f, 0f, 0f);
		
		gl.glMatrixMode(GL10.GL_TEXTURE);
		gl.glLoadIdentity();
		gl.glTranslatef(bgScrollx, bgScrolly, 0.0f); //scrolling the texture
		
		background.draw(gl);
		gl.glPopMatrix();
		gl.glLoadIdentity();
	}
	
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width,height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(0f, 1f, 0f, 1f, -1f, 1f);
	}
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		background.loadTexture(gl,MFEngine.BACKGROUND_TEXTURE_ONE,
				MFEngine.context);
		player1.loadTexture(gl, MFEngine.PLAYER, MFEngine.context);

	}
	
	// all player character animation in the field is done here.
	private void movePlayer1(GL10 gl){
		switch (MFEngine.playerAction){
		case MFEngine.PLAYER_WALK_RIGHT_1:
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			gl.glPushMatrix();
			gl.glScalef(.25f, .25f, 1f);
			gl.glTranslatef(1.5f,1.5f, 0f);
			if (heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.0f,0.125f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI 
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*2){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.125f,0.125f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*2  
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*3){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.25f,0.125f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*3  
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*4){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.375f,0.125f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*4  
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*5){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.5f,0.125f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*5  
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*6){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.625f,0.125f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*6  
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*7){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.75f,0.125f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*7  
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*8){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.875f,0.125f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*8)
				heroFrames = 0;
			player1.draw(gl);
			gl.glPopMatrix();
			gl.glLoadIdentity();
			break;
		case MFEngine.PLAYER_WALK_LEFT_1:
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			gl.glPushMatrix();
			gl.glScalef(.25f, .25f, 1f);
			gl.glTranslatef(1.5f,1.5f, 0f);
			if (heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.0f,0.25f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI 
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*2){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.125f,0.25f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*2  
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*3){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.25f,0.25f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*3  
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*4){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.375f,0.25f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*4  
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*5){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.5f,0.25f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*5  
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*6){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.625f,0.25f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*6  
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*7){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.75f,0.25f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*7  
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*8){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.875f,0.25f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*8)
				heroFrames = 0;
			player1.draw(gl);
			gl.glPopMatrix();
			gl.glLoadIdentity();
			break;
		case MFEngine.PLAYER_WALK_UP_1:
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			gl.glPushMatrix();
			gl.glScalef(.25f, .25f, 1f);
			gl.glTranslatef(1.5f,1.5f, 0f);
			if (heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.0f,0.0f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI 
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*2){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.125f,0.0f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*2  
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*3){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.25f,0.0f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*3  
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*4){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.375f,0.0f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*4  
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*5){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.5f,0.0f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*5  
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*6){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.625f,0.0f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*6  
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*7){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.75f,0.0f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*7  
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*8){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.875f,0.0f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*8)
				heroFrames = 0;
			player1.draw(gl);
			gl.glPopMatrix();
			gl.glLoadIdentity();
			break;
		case MFEngine.PLAYER_WALK_DOWN_1:
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			gl.glPushMatrix();
			gl.glScalef(.25f, .25f, 1f);
			gl.glTranslatef(1.5f,1.5f, 0f);
			if (heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.0f,0.375f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI 
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*2){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.125f,0.375f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*2 
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*3){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.25f,0.375f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*3 
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*4){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.375f,0.375f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*4 
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*5){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.5f,0.375f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*5 
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*6){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.625f,0.375f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*6 
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*7){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.75f,0.375f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*7 
					&& heroFrames < MFEngine.PLAYER_FRAMES_BETWEEN_ANI*8){
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.875f,0.375f, 0.0f);
				heroFrames += 1;
			}
			if(heroFrames >= MFEngine.PLAYER_FRAMES_BETWEEN_ANI*8)
				heroFrames = 0;
			player1.draw(gl);
			gl.glPopMatrix();
			gl.glLoadIdentity();
			break;
		case MFEngine.PLAYER_RELEASE:
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			gl.glPushMatrix();
			gl.glScalef(.25f, .25f, 1f);
			gl.glTranslatef(1.5f,1.5f, 0f);
			gl.glMatrixMode(GL10.GL_TEXTURE);
			gl.glLoadIdentity();
			gl.glTranslatef(0.0f,0.375f, 0.0f);
			player1.draw(gl);
			gl.glPopMatrix();
			gl.glLoadIdentity();
			heroFrames += 1;
			break;
		default:
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			gl.glPushMatrix();
			gl.glScalef(.25f, .25f, 1f);
			gl.glTranslatef(1.5f,1.5f, 0f);
			gl.glMatrixMode(GL10.GL_TEXTURE);
			gl.glLoadIdentity();
			gl.glTranslatef(0.0f,0.375f, 0.0f);
			player1.draw(gl);
			gl.glPopMatrix();
			gl.glLoadIdentity();
			break;
		}
	}
	
	public static void setScroll(boolean s, float a, boolean semi1){
		scroll = s;
		angle = a;
		hemi1 = semi1;
	}

}

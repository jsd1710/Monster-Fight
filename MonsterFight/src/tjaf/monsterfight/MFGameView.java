package tjaf.monsterfight;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MFGameView extends GLSurfaceView{
	private MFGameRenderer renderer;

	public MFGameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		renderer = new MFGameRenderer();
		
		this.setRenderer(renderer);
	}

}

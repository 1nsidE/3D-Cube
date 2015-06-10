import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;



public class MainWnd {
	private final Shell main_shell = new Shell();
	private final Cube cube = new Cube();
	
	private RectFace faces[];
	private GC bufferGC;
	private Image image;
	/**
	 * Launch the application.
	 * @param args
	 */

    public static void main(String[] args) {
        try {
            MainWnd window = new MainWnd();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MainWnd(){
        initFaces();
    }

	private void initFaces(){
		faces = new RectFace[] { 
				new RectFace( 0, 1, 2, 3, SWT.COLOR_DARK_GREEN ),
				new RectFace( 3, 7, 4, 0, SWT.COLOR_DARK_RED ),
				new RectFace( 0, 4, 5, 1, SWT.COLOR_DARK_BLUE ),
				new RectFace( 1, 5, 6, 2, SWT.COLOR_DARK_CYAN ),		
				new RectFace( 2, 3, 7, 6, SWT.COLOR_DARK_YELLOW ),
				new RectFace( 6, 5, 4, 7, SWT.COLOR_DARK_MAGENTA ) };
	}
	
	/**
	 * Open the window.
	 */
	public void open() {
		final int TIME_OUT = 50;
		final Display display = Display.getDefault();
		createContents();
		
		main_shell.open();
		main_shell.layout();
		
		Runnable timer = new Runnable(){
			@Override
			public void run(){
				cube.RotateX(1);
				cube.RotateY(1);
				cube.RotateZ(1);
				
				main_shell.redraw();
				display.timerExec( TIME_OUT, this);
			}
		};
		
		display.timerExec( TIME_OUT, timer );
		
		while ( !main_shell.isDisposed() ) {
			if ( !display.readAndDispatch() ) {
				display.sleep();
			}			
		} 
		bufferGC.dispose();
		image.dispose();
	}	

	/**
	 * Create contents of the window.
	 */
	protected void createContents(){
		main_shell.setSize( 320, 320 );
		main_shell.setText( "3D Cube" );
		
		image  = new Image( main_shell.getDisplay(), main_shell.getDisplay().getBounds() );
		
		bufferGC = new GC( image );
        bufferGC.setAntialias(1);

		main_shell.addPaintListener( new PaintAdapter(){
			@Override
			public void paintControl( PaintEvent e ){
				bufferGC.setBackground( main_shell.getDisplay().getSystemColor( SWT.COLOR_BLACK ) );
				bufferGC.fillRectangle( image.getBounds() );
				drawRectangle();
				e.gc.drawImage( image, 0, 0 );
			}
		});
	}
	
	private void drawRectangle(){
		final Point3D [] pt3D = cube.getPoints();
		final int [] pt = new int[ 8 ];
		
		sortFacesByZ( pt3D );

		for( int i = 0; i != faces.length; i++ ){			
			pt[ 0 ] = pt3D[ faces[ i ].a1 ].get2DX(); pt[ 1 ] = pt3D[ faces[ i ].a1 ].get2DY();
			pt[ 2 ] = pt3D[ faces[ i ].a2 ].get2DX(); pt[ 3 ] = pt3D[ faces[ i ].a2 ].get2DY();
			pt[ 4 ] = pt3D[ faces[ i ].a3 ].get2DX(); pt[ 5 ] = pt3D[ faces[ i ].a3 ].get2DY();
			pt[ 6 ] = pt3D[ faces[ i ].a4 ].get2DX(); pt[ 7 ] = pt3D[ faces[ i ].a4 ].get2DY();
			
			bufferGC.setBackground( main_shell.getDisplay().getSystemColor( faces[ i ].color ) );
			bufferGC.fillPolygon( pt );
		}		
	}
	
	private void sortFacesByZ( Point3D [] pt3D ){
		RectFace t_face;
		final double [] avgZ = new double [ faces.length ];
		for( int i = 0; i != faces.length; i++ ){
			avgZ[ i ] = pt3D[ faces[ i ].a1 ].getZ() + pt3D[ faces[ i ].a2 ].getZ() + pt3D[ faces[ i ].a3 ].getZ() + pt3D[ faces[ i ].a4 ].getZ(); 
		}
		
		//because faces.length always = 6, bubble sort is pretty much fast
		for( int i = 0; i != faces.length; i++ ){
			for( int  j = i; j != faces.length; j++ ){
				if( avgZ[ i ] < avgZ[ j ] ){
					t_face = faces[ i ];
					faces[ i ] = faces[ j ];
					faces[ j ] = t_face;
				}
			}
		}
		
	}

}

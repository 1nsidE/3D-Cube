public class Cube {
	Point3D pt3D[] ;

	public Cube(Point3D pt[]) throws IllegalArgumentException {
		if( pt.length != 8 ){
			throw new IllegalArgumentException("Cube can have only 8 points!");
		}
		
		for( int i = 0; i != pt.length; i++ ){
			pt3D[ i ].x = pt[ i ].x;
			pt3D[ i ].y = pt[ i ].y;
			pt3D[ i ].z = pt[ i ].z;
			
			pt3D[ i ].dis = pt[ i ].dis;
			pt3D[ i ].fov = pt[ i ].fov;
		}		
	}
	
	public Cube(){
		initDefault();
	}

private void initDefault() {
		pt3D = new Point3D[] { new Point3D( 1, 1, 1 ),
							   new Point3D( 1, -1, 1 ),
							   new Point3D( -1, -1, 1 ),
							   new Point3D( -1, 1, 1 ),
							   new Point3D( 1, 1, -1 ),
							   new Point3D( 1, -1, -1 ),
							   new Point3D( -1, -1, -1 ),
							   new Point3D( -1, 1, -1 ) };	
	}
	

	public void RotateX( double angle ){
		for( int  i = 0; i != pt3D.length; i++ ){
			pt3D[ i ].RotateX( angle );
		}
	}
	
	public void RotateY( double angle ){
		for( int  i = 0; i != pt3D.length; i++ ){
			pt3D[ i ].RotateY( angle );
		}
	}
	
	public void RotateZ( double angle ){
		for( int  i = 0; i != pt3D.length; i++ ){
			pt3D[ i ].RotateZ( angle );
		}
	}
	
	public Point3D[] getPoints(){
		Point3D [] pt = new Point3D[ 8 ];
		
		for( int i = 0 ; i != pt3D.length; i++ ){
			pt[ i ] = new Point3D();
			
			pt[ i ].x = pt3D[ i ].x;
			pt[ i ].y = pt3D[ i ].y;
			pt[ i ].z = pt3D[ i ].z;
			
			pt[ i ].dis = pt3D[ i ].dis;
			pt[ i ].fov = pt3D[ i ].fov;			
		}
		return pt;
	}
}

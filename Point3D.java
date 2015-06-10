import java.lang.Math;

public class Point3D {
	double x = 1,y = 1,z = 1;
	int dis = 5, fov = 360;
	
	public Point3D( double X, double Y, double Z ){
		x = X;
		y = Y;
		z = Z;
	}
	
	public Point3D( Point3D pt ){
		x = pt.x;
		y = pt.y;
		z = pt.z;
	}
	
	public Point3D(){
	}
	
	public void RotateX( double angle ){
		final double rad = Math.PI*angle / 180;
		
		final double yt = y * Math.cos( rad ) - z * Math.sin( rad );
		z = y * Math.sin( rad ) + z * Math.cos( rad );
		
		y = yt;
	}
	
	public void RotateY( double angle ){
		final double rad = Math.PI*angle / 180;
		
		final double zt = z * Math.cos( rad ) - x * Math.sin( rad );
		x = z * Math.sin( rad ) + x * Math.cos( rad );
		
		z = zt;
	}
	
	public void RotateZ( double angle ){
		final double rad = Math.PI*angle / 180;
		
		final double xt = x * Math.cos( rad ) - y * Math.sin( rad );
		y = x * Math.sin( rad ) + y * Math.cos( rad );
		
		x = xt;
	}
	
	public void setX( double X ){
		x = X;
	}
	
	public void setY( double Y ){
		y = Y;
	}
	
	public void setZ( double Z ){
		z = Z;
	}
	
	public void setDistance( int distance ){
		dis = distance;
	}
	
	public void setFOV( int FOV ){
		fov = FOV;
	}
	
	public double getX(){
		return x; 
	}
	
	public double getY(){
		return y;
	}
	
	public double getZ(){
		return z;
	}
	
	public int getDistance(){
		return dis;
	}
	
	public int getFOV(){
		return fov;
	}
	
	public int get2DX(){
		final double factor = fov / ( dis + z );
		
		return ( int )( factor * x + 160 );
	}
	
	public int get2DY(){
		final double factor = fov / ( dis + z );
		
		return ( int )( factor * y + 135 );
	}
}

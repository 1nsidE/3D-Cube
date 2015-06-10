import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;


public abstract class PaintAdapter implements PaintListener {

	@Override
	public abstract void paintControl(PaintEvent arg0);

}

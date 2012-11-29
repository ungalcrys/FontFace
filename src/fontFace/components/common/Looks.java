package fontFace.components.common;

import javax.swing.LookAndFeel;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;

public class Looks {
	public static final LookAndFeel[] looks = new LookAndFeel[] { new PlasticLookAndFeel(), new Plastic3DLookAndFeel(), new PlasticXPLookAndFeel() };

	/*
	 * new MotifLookAndFeel(), new GTKLookAndFeel(), new NimbusLookAndFeel(), new WindowsClassicLookAndFeel()
	 */
	public static LookAndFeel getLook(String id) {
		for (LookAndFeel look : looks)
			if (look.getID().equals(id))
				return look;
		return null;
	}

}

package IHM;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class BackgroundImage extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	
	public BackgroundImage(){
		try {
			image = ImageIO.read(new File("football-stadium-1920x1080.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0,0,null);
	}
}

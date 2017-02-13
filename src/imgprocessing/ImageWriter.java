package imgprocessing;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class ImageWriter 
{
    private String imageName;
    private String message;

    public ImageWriter(String imageName, String message) 
    {
        setImageName(imageName);
        setMessage(message);
    }
    
    public void setImageName(String x)
    {//setter
        imageName = x;
    }

    public void setMessage(String x)
    {//setter
        if(x.length() > 17)
            message = x.substring(0,17) + "...";
        else
            message = x;
    }
    
    boolean writeIt()
    {
        //load the image in memory
        File f = new File(imageName);
        if(f.exists())
        {
            try
            {
                //load the image in memory
                BufferedImage bi;
                bi = ImageIO.read(f);

                //write the text on it
                Graphics g;
                g =bi.getGraphics();
                g.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
                g.setColor(Color.red);
                g.drawString(message, 10, 50);//10 away from the left and 50 down from the top
                
                //generate a new name for the output
                File f1 = new File(getNewName());
                
                //save it back
                ImageIO.write(bi, "PNG", f1);
                
                return true;
            }
            catch(Exception ex)
            {
                System.out.println("Err in writeIt() : " + ex);
            }
            
        }
        return false;
    }
    
    String getNewName()
    {
        int pos = imageName.lastIndexOf(".");
        return  imageName.substring(0, pos) + "_1" + imageName.substring(pos);
    }
    
}

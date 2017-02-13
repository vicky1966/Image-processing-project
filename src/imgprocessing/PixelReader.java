package imgprocessing;

import java.io.File;
import java.awt.image.*;
import javax.imageio.*;

public class PixelReader 
{
    String imageName;
    String message;
    
    public PixelReader(String imageName) 
    {
        setImageName(imageName);
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

    String readIt()
    {
        File f = new File(imageName);
        if(f.exists())
        {
            try
            {
                int i, j, x;
                int w, h;
                int flag;
                
                BufferedImage bi;
                bi = ImageIO.read(f);

                Raster rstr = bi.getData();
                w = bi.getWidth();
                h = bi.getHeight();
                
                String temp= "";
                
                x = 0;
                flag = 1;
                for(i =0 ; i< w && flag != 0; i++)
                {
                    for(j =0 ; j < h && flag != 0; j++)
                    {
                        if(x < HeaderManager.headerLength)
                            temp = temp + (char) rstr.getSample(i, j, x%3);
                        else if(x == HeaderManager.headerLength)
                        {
                            flag = HeaderManager.getMessageLength(temp);
                            message = ""+ (char) rstr.getSample(i, j, x%3);
                            flag--;
                        }
                        else
                        {
                            message = message + (char) rstr.getSample(i, j, x%3);
                            flag--;
                        }
                        x++;
                    }//for(j
                }//for(i
                
                setMessage(message);
                
                return message;
            }//try
            catch(Exception ex)
            {
                System.out.println("Err in readIt : " + ex);
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    String getNewName()
    {
        int pos = imageName.lastIndexOf(".");
        return  imageName.substring(0, pos) + "_1" + imageName.substring(pos);
    }
}

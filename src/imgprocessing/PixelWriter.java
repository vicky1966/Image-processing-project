package imgprocessing;

import java.io.File;
import java.awt.image.*;
import javax.imageio.*;

public class PixelWriter 
{
    String imageName;
    String message;
    
    public PixelWriter(String imageName, String message) 
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

    String writeIt()
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

                WritableRaster wrstr = bi.getRaster();
                w = bi.getWidth();
                h = bi.getHeight();
                
                String q = HeaderManager.setHeader(message);
                
                x = 0;
                flag = 1;
                for(i =0 ; i< w && flag == 1; i++)
                {
                    for(j =0 ; j < h && flag == 1; j++)
                    {
                        wrstr.setSample(i, j, x%3, (int) q.charAt(x));
                        x++;
                        if(x == q.length())
                            flag = 0;
                    }//for(j
                }//for(i
                
                //update the buffered image
                bi.setData(wrstr);
                
                //generate a new name for the output
                String name= getNewName();
                File f1 = new File(name);
                
                //save it back
                ImageIO.write(bi, "PNG", f1);
                
                return name;
            }//try
            catch(Exception ex)
            {
                System.out.println("Err in writeIt : " + ex);
                
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

package imgprocessing;

public class Main 
{
    public static void main(String[] args) 
    {
        //ImageWriter iw = new ImageWriter("d:\\One.png", "I CAN WRITE TEXT ON IMAGES");
        PixelWriter pw = new PixelWriter("d:\\One.png", "I CAN WRITE TEXT ON IMAGES");
        String name = pw.writeIt();
        if(name != null)
        {
            System.out.println("SUCCESS");
            
            PixelReader pr = new PixelReader(name);
            String message = pr.readIt();
            System.out.println("MESSAGE : " + message);
        }
        else
            System.out.println("Failure");
        
        
        
    }
}

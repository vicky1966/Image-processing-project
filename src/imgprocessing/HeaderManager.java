package imgprocessing;

public class HeaderManager 
{
    public static final int headerLength = 10;

    public static String setHeader(String message)
    {
        int x = message.length();
        String header= String.valueOf(x);
        //rpad with spaces
        while(header.length() < headerLength)
            header = header + " ";
        
        message = header + message;
        return message;
    }
    
    public static int getMessageLength(String header)//"20        "
    {
        return Integer.parseInt(header.substring(0,header.indexOf(" ")));
        //return Integer.parseInt(message.substring(0, headerLength).trim());
    }
}

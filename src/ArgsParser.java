public class ArgsParser
{
    private int maxLength;
    private String[] parsedArgs;
    private String[] options;
    private String[] foundArguments;


    ArgsParser(String[] args)
    {
        maxLength=args.length;
        parsedArgs=new String[maxLength];
        parsedArgs=args;

        detectOptions();
        detectArguments();
        System.out.println("options are:" + options[0] + " " + options[1]);
        System.out.println("arguments are:" + foundArguments[0] + " " + foundArguments[1]);
    }
    public void argsPrinter()
    {
        for(int i=0;i<maxLength;i++)
            System.out.println(parsedArgs[i]);
    }
    private void detectOptions()
    {
        options = new String[maxLength];
        int numOfOpts=0;
        for (int i=0;i<maxLength;i++)
            if(parsedArgs[i].startsWith("-"))
            {
                options[numOfOpts]=parsedArgs[i];
                numOfOpts++;
            }
    }
    private void detectArguments()
    {
        foundArguments = new String[maxLength];
        int numOfOpts=0;
        for (int i=0;i<maxLength;i++)
            if(!parsedArgs[i].startsWith("-"))
            {
                foundArguments[numOfOpts]=parsedArgs[i];
                numOfOpts++;
            }
    }
    public static void main(String[] args)
    {
        System.out.println("dimitris trelakias");
    }
}

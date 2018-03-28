import javax.swing.text.html.Option;

public class OptionParser {

    private String[] optionsShortArray,optionsLongArray,helpShortArray,helpLongArray;
    private boolean[] optionalShortArray,optionalLongArray;

    private int counterShort=0;
    private int counterLong=0;

    OptionParser()
    {
        optionsShortArray= new String[10];
        optionalShortArray= new boolean[10];
        optionsLongArray=new String[10];
        optionalLongArray=new boolean[10];
        helpShortArray=new String[10];
        helpLongArray=new String[10];
    }
    public void addOptionShort(String optionLetter,boolean isOptional)
    {
        if(optionLetter.startsWith("-")&& !optionLetter.contains("--"))
        {
            optionalShortArray[counterShort] = isOptional;
            optionsShortArray[counterShort] = optionLetter;
            counterShort++;
        }
        else
            System.out.println("Short Options Must start with one '-' " );


    }
    public void addOptionLong(String optionString,boolean isOptional)
    {
        if(optionString.startsWith("--"))
        {
            optionalLongArray[counterLong] = isOptional;
            optionsLongArray[counterLong] = optionString;
            counterLong++;
        }
        else
            System.out.println("Long Options Must start with two '--' " );


    }
    public void PrintOptions()
    {
        for(int i=0;i<optionsLongArray.length;i++)
        {
            if(optionsLongArray[i]!=null)
            {
                if (optionalLongArray[i])
                    System.out.println("Option : " + optionsLongArray[i] + " is Long and its optional");
                else
                    System.out.println("Option : " + optionsLongArray[i] + " is Long and its not optional");
            }
        }

        for(int i=0;i<optionsShortArray.length;i++)
        {
            if(optionsShortArray[i]!=null)
            {
            if (optionalShortArray[i])
                System.out.println("Option : " + optionsShortArray[i] + " is Short and its optional");
            else
                System.out.println("Option : " + optionsShortArray[i] + " is Short and its not optional");
        }
        }
    }
    public void findOption(String Option)
    {
        boolean flag=false;
        for (int i=0;i<optionsShortArray.length;i++)
        {
            if(Option.equals(optionsShortArray[i]))
                flag=true;
        }
        for (int i=0;i<optionsLongArray.length;i++)
        {
            if(Option.equals(optionsLongArray[i]))
                flag=true;
        }
        if (flag)
            System.out.println("Option "+Option+ " exists!");
        else
            System.out.println("Option "+Option+ " does not exist!");
    }
    public void addHelp(String option,String help)
    {
        for (int i=0;i<optionsShortArray.length;i++)
        {
            if(option.equals(optionsShortArray[i]))
                helpShortArray[i]=help;
        }
        for (int i=0;i<optionsLongArray.length;i++)
        {
            if(option.equals(optionsLongArray[i]))
                helpLongArray[i]=help;
        }
    }
    public void help()
    {
        for(int i=0;i<optionsLongArray.length;i++)
        {
            if(optionsLongArray[i]!=null&&helpLongArray[i]!=null)
            {
                System.out.println("Option " + optionsLongArray[i] +" : " + helpLongArray[i] );
            }
        }

        for(int i=0;i<optionsShortArray.length;i++)
        {
            if(optionsShortArray[i]!=null&&helpShortArray[i]!=null)
            {
                System.out.println("Option "+optionsShortArray[i] +" HELP : " + helpShortArray[i]);
            }
        }
    }
}
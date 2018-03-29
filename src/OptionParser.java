/**
 * OptionParser is a Class member of ProjectArgsHandler library
 * It was written as part of a project at Programming Methodology course of Informatics Engineering Faculty of Technological Education Institute of Central Macedonia
 * OptionParse helps to create read update and delete java command line options
 *
 * @author IgorSpiridonov
 */
public class OptionParser {

    private String[] optionsShortArray,optionsLongArray,helpShortArray,helpLongArray;
    private boolean[] necessaryShortArray, necessaryLongArray;

    private int counterShort=0;
    private int counterLong=0;
    private int length;

    /**
     * OptionParser constructor creates an OptionParser Object and initializes the String tables with the given String length
     * @param length Stores the number of options tha will be made
     */
    OptionParser(int length)
    {
        this.length=length;
        optionsShortArray= new String[length];
        necessaryShortArray = new boolean[length];
        optionsLongArray=new String[length];
        necessaryLongArray =new boolean[length];
        helpShortArray=new String[length];
        helpLongArray=new String[length];
    }

    /**
     * Create a Short option and define if the option will have necessary arguments
     * @param optionName The short option that will be made
     * @param isNecessary   Define if the option will have necessary arguments
     */
    public void addOptionShort(String optionName,boolean isNecessary)
    {
        if(optionName.startsWith("-")&& !optionName.contains("--"))
        {
            necessaryShortArray[counterShort] = isNecessary;
            optionsShortArray[counterShort] = optionName;
            counterShort++;

        }
        else
            System.out.println("Short Options Must start with one '-' " );


    }

    /**
     * Create a Long option and define if the option will have necessary arguments
     * @param optionName The long option that will be made
     * @param isNecessary   Define if the option will have necessary arguments
     */
    public void addOptionLong(String optionName,boolean isNecessary)
    {
        if(optionName.startsWith("--"))
        {
            necessaryLongArray[counterLong] = isNecessary;
            optionsLongArray[counterLong] = optionName;
            counterLong++;
        }
        else
            System.out.println("Long Options Must start with two '--' " );


    }

    /**
     * Prints all the options and if they will have necessary arguments
     */
    public void printOptions()
    {
        for(int i=0;i<length;i++)
        {
            if(optionsLongArray[i]!=null)
            {
                if (necessaryLongArray[i])
                    System.out.println("Option : " + optionsLongArray[i] + " is Long and its optional");
                else
                    System.out.println("Option : " + optionsLongArray[i] + " is Long and its not optional");
            }
            if(optionsShortArray[i]!=null)
            {
                if (necessaryShortArray[i])
                    System.out.println("Option : " + optionsShortArray[i] + " is Short and its optional");
                else
                    System.out.println("Option : " + optionsShortArray[i] + " is Short and its not optional");
        }
        }
    }

    /**
     * Returns true if the given option is parsed
     * @param option The option to find if it is parsed
     * @return true if options has been parsed
     */
    public boolean optionExists(String option)
    {
        boolean exists=false;
        for (int i=0;i<length;i++)
        {
            if(option.equals(optionsShortArray[i])||option.equals(optionsLongArray[i]))
                exists=true;
        }
        return exists;
    }

    /**
     * Checks if the option have necessary arguments
     * @param option The options to be checked if it has necessary arguments
     * @return Returns true if the option has necessary arguments
     */
    public boolean isNecessary(String option)
    {
        boolean isNecessary=false;
        for (int i=0;i<length;i++)
        {
            if(option.equals(optionsShortArray[i]))
            {
                if (necessaryShortArray[i])
                    isNecessary = true;
            }
            if(option.equals(optionsLongArray[i]))
            {
                if (necessaryLongArray[i])
                    isNecessary = true;
            }

        }
        return isNecessary;

    }

    /**
     * Adds the comment to be show at the help menu
     * @param option To which option add the comment
     * @param help  THe comment that will be added to the option
     */
    public void addHelp(String option,String help)
    {
        for (int i=0;i<length;i++)
        {
            if(option.equals(optionsShortArray[i]))
                helpShortArray[i]=help;
            if(option.equals(optionsLongArray[i]))
                helpLongArray[i]=help;
        }
    }

    /**
     * Shows the menu with the options that have help instructions
     */
    public void helpMenu()
    {
        for(int i=0;i<length;i++)
        {
            if(optionsLongArray[i]!=null&&helpLongArray[i]!=null)
            {
                System.out.println("Option " + optionsLongArray[i] +" HELP : " + helpLongArray[i]);
            }
            if(optionsShortArray[i]!=null&&helpShortArray[i]!=null)
            {
                System.out.println("Option "+optionsShortArray[i] +" HELP : " + helpShortArray[i]);
            }
        }
    }
}
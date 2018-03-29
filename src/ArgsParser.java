/**
 * OptionParser is a Class member of ProjectArgsHandler library
 * It was written as part of a project at Programming Methodology course of Informatics Engineering Faculty of Technological Education Institute of Central Macedonia
 * OptionParse helps to handle the java command line arguments
 * @author IgorSpiridonov
 */
public class ArgsParser
{
    private int numberOfPassedArgs;
    private int[] optionArgumentsCount;
    private int numOfOpts=0;

    private String[] parsedArgs;
    private String[] foundOptions;
    private String[] foundArguments;
    private String[][] matchedArguments; //two dimensional table with option in rows and their matched arguments in columns

    /**
     * ArgsParser constructor initializes the parsedArgs table with the given args
     * @param args Stores the given arguments
     */
    public ArgsParser(String[] args)
    {
        numberOfPassedArgs =args.length;
        parsedArgs=new String[numberOfPassedArgs];
        optionArgumentsCount=new int[numberOfPassedArgs];
        parsedArgs=args;
    }

    /**
     * Prints all the parsed arguments
     */
    public void argsPrinter()
    {
        for(int i = 0; i< numberOfPassedArgs; i++)
            System.out.println(parsedArgs[i]);
    }


    private void detectOptions()
    {
        foundOptions = new String[numberOfPassedArgs];

        for (int i = 0; i< numberOfPassedArgs; i++)
            if(parsedArgs[i].startsWith("-"))
            {
                foundOptions[numOfOpts]=parsedArgs[i];
                numOfOpts++;
            }
    }

    private void detectArguments()
    {
        foundArguments = new String[numberOfPassedArgs -numOfOpts];
        int numOfOpts=0;
        int count=-1;
        for (int i = 0; i< numberOfPassedArgs; i++)
            if(!parsedArgs[i].startsWith("-"))
            {
                foundArguments[numOfOpts]=parsedArgs[i];
                numOfOpts++;
                optionArgumentsCount[count]++;
            }
            else
                count++;
    }

    /**
     * Is Called to match the Options with their arguments
     */
    public void matchOptsWithArgs()
    {
        detectOptions();
        detectArguments();
        int tempArgsPassedCounter=0;
        int argsPassedCounter=0;

        matchedArguments=new String[numOfOpts][getMaxFromIntTable(optionArgumentsCount)];
        for(int i=0;i<numOfOpts;i++) {
            for (int j = 0; j < optionArgumentsCount[i]; j++) {
                matchedArguments[i][j] = foundArguments[j + argsPassedCounter];
                tempArgsPassedCounter++;
            }
            argsPassedCounter = tempArgsPassedCounter;
        }
    }

    private int getMaxFromIntTable(int[] table)
    {
        int max=table[0];
        for (int i=0;i<table.length;i++)
            if (table[i]>max)
                max=table[i];
        return max;
    }

    /**
     * Returns the Number of Options tha have been passed to the command line
     * @return Returns the number of options
     */
    public int getNumberOfOptions()
    {
        return numOfOpts;
    }

    /**
     * Returns the number of passed arguments
     * @return Return the number of passed arguments
     */
    public int getNumberOfPassedArgs()
    {
        return numberOfPassedArgs;
    }

    /**
     * Returns the number of arguments that have a specific option
     * @param option  The option to check the arguments
     * @return  Returns the number of arguments of the given option or returns -1 if option not found
     */
    public int getNumberOfAnOptionArguments(String option)
    {
        int foundPosition=findPositionOfAnOption(option);

        if(foundPosition!=-1)
            return optionArgumentsCount[foundPosition];
        else
            return -1;
    }

    /**
     * Returns the position of the given option at the Option Table
     * @param option The option to check the position
     * @return Returns the position of the option
     */
    public int findPositionOfAnOption(String option) //returns -1 if not found
    {
        int foundPosition=-1;
        for(int i=0;i<numOfOpts;i++)
            if(foundOptions[i].equals(option))
                foundPosition=i;
        return foundPosition;
    }

    /**
     * Returns the table of the options tha have been parsed to the java command line arguments
     * @return Returns the table of the parsed options
     */
    public String[] getTableOfParsedOptions()
    {
        return foundOptions;
    }

    /**
     * Returns a two dimensional String table with the options at rows and their matched arguments in columns
     * @return Returns a two dimensional String table with options and their matched arguments
     */
    public String[][] getTableOfParsedOptionsWithMatchedArgs() // returns a two dimensional table with option in rows and their matched arguments in columns
    {
        return matchedArguments;
    }

    /**
     * Returns the table of the matched arguments of the given option
     * @param option The option to return it's arguments
     * @return  Returns a String table with the matched arguments
     */
    public String[] getOptionMatchedArguments(String option)
    {
        int pos=findPositionOfAnOption(option);

        return matchedArguments[pos];
    }

    /**
     * Returns true if an option is parsed to the java command line arguments
     * @param option The option to check if it is parsed
     * @return Returns true if the option has been parsed
     */
    public boolean isParsed(String option)
    {
        int pos=findPositionOfAnOption(option);
        return pos != -1;
    }

    /**
     * Returns true if an option have matched arguments
     * @param option The option to be checked for matched arguments
     * @return Returns true if an option have matched arguments
     */
    public boolean optionHaveArguments(String option)
    {
        int numOfOptsArgs=getNumberOfAnOptionArguments(option);
        return numOfOptsArgs>0;
    }
}

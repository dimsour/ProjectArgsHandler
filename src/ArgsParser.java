public class ArgsParser
{
    private int numberOfPassedArgs;
    private int[] optionArgumentsCount;
    private int numOfOpts=0;

    private String[] parsedArgs;
    private String[] foundOptions;
    private String[] foundArguments;
    private String[][] matchedArguments; //two dimensional table with option in rows and their matched arguments in columns




    ArgsParser(String[] args)
    {
        numberOfPassedArgs =args.length;
        parsedArgs=new String[numberOfPassedArgs];
        optionArgumentsCount=new int[numberOfPassedArgs];
        parsedArgs=args;
    }

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

        System.out.println(getMaxFromIntTable(optionArgumentsCount));
        System.out.println(numOfOpts);

        for (int i=0;i<numOfOpts;i++)
        {
            System.out.println("Option "+ foundOptions[i]+" have "+optionArgumentsCount[i]+" arguments : " );
            for(int j=0;j<optionArgumentsCount[i];j++)
                System.out.println(matchedArguments[i][j]);
        }
    }

    public static void main(String[] args)
    {

    }

    private int getMaxFromIntTable(int[] table)
    {
        int max=table[0];
        for (int i=0;i<table.length;i++)
            if (table[i]>max)
                max=table[i];
        return max;
    }

    public int getNumberOfOptions()
    {
        return numOfOpts;
    }

    public int getNumberOfPassedArgs()
    {
        return numberOfPassedArgs;
    }

    public int getNumberOfAnOptionArguments(String option)
    {
        int foundPosition=findPositionOfAnOption(option);

        if(foundPosition!=-1)
            return optionArgumentsCount[foundPosition];
        else
            return -1;
    }

    public int findPositionOfAnOption(String option) //returns -1 if not found
    {
        int foundPosition=-1;
        for(int i=0;i<numOfOpts;i++)
            if(foundOptions[i].equals(option))
                foundPosition=i;
        return foundPosition;
    }

    public String[] getTableOfParsedOptions()
    {
        return foundOptions;
    }

    public String[][] getTableOfParsedOptionsWithMatchedArgs() // returns a two dimensional table with option in rows and their matched arguments in columns
    {
        return matchedArguments;
    }

    public String[] getOptionMatchedArguments(String option)
    {
        int pos=findPositionOfAnOption(option);

        return matchedArguments[pos];
    }

    public boolean isParsed(String option)
    {
        int pos=findPositionOfAnOption(option);
        return pos != -1;
    }





}

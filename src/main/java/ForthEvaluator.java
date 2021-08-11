import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class ForthEvaluator
{

    public Stack<Integer> integers;
    public Stack<String> instructions;
    public Stack<String> userInstructions;


    public ForthEvaluator()
    {
        integers = new Stack<>();
        instructions = new Stack<>();
        userInstructions = new Stack<>();
    }

    private void parser(String aLine)
    {
        Scanner aScan = new Scanner(aLine);
        boolean userInstruction = false;
        boolean done = false;

        while (aScan.hasNext() && !done)
        {
            String data = aScan.next();

            if (data.equals(":"))
            {
                userInstruction = true;
            } else if (userInstruction)
            {
                checkWord someChecker = (String someWord) -> (!someWord.equals(";") && !someWord.equals(":"));

                process(aLine, someChecker, userInstructions);
                done = true;
            } else
            {
                checkWord someChecker = (String someWord) -> (true);
                process(aLine, someChecker, integers);
                done = true;
            }
        }

    }

    public void process(String aLine, checkWord aChecker, Stack aStack)
    {
        Scanner aScan = new Scanner(aLine);

        while (aScan.hasNext())
        {
            String aWord = aScan.next();

            if (aChecker.processLine(aWord))
            {
                aStack.push(aWord);
            }
        }
    }


    public interface checkWord
    {
        boolean processLine(String aLine);
    }

    public List evaluateProgram(List aList)
    {
        List answer = new ArrayList();

        for (Object aLine : aList)
        {
            //TODO Must implement parser for regular instructions
            String line = (String) aLine;
            parser(line);
        }

        //Scanner aScan = new Scanner(aList);

        return answer;
    }

}





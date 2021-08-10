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

        while (aScan.hasNext())
        {
            String data = aScan.next();

            if (data.equals(":"))
            {
                userInstruction = true;
            }
            else if (userInstruction)
            {

            }
        }

    }

    public interface processLineFunction
    {
        void processLine(String aLine);
    }

    public List evaluateProgram(List aList)
    {
        List answer = new ArrayList();

        for (Object aLine : aList)
        {
            String line = (String) aLine;
            parser(line);
        }

        //Scanner aScan = new Scanner(aList);


        return answer;
    }


}





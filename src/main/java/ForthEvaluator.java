import javax.naming.PartialResultException;
import java.lang.reflect.Array;
import java.util.*;

public class ForthEvaluator
{

    public Stack<Integer> integers;
    public Stack<String> instructions;
    public Queue<String> userInstructions;
    public Queue<Integer> queueOfIntegers;

    public HashMap<String, singleInstruction> listOfInstructions;


    public ForthEvaluator()
    {
        integers = new Stack<>();
        instructions = new Stack<>();
        userInstructions = new LinkedList<>();

        //queueOfIntegers.remove();

        listOfInstructions = new HashMap<>();


        singleInstruction duplicate = (int a) ->
        {
            ArrayList<Integer> meow = new ArrayList<>();
            meow.add(a);
            meow.add(a);
            return meow;
        };

        twoInstruction add = (int a, int b) ->
        {
            ArrayList<Integer> meow = new ArrayList<>();
            meow.add(a + b);
            return meow;
        };

        twoInstruction divide = (int a, int b) ->
        {
            ArrayList<Integer> meow = new ArrayList<>();
            meow.add(a / b);
            return meow;
        };

        twoInstruction minus = (int a, int b) ->
        {
            ArrayList<Integer> meow = new ArrayList<>();
            meow.add(a - b);
            return meow;
        };



    }

    private void userInstructionsParser(String aLine) throws IllegalArgumentException
    {
        Scanner aScan = new Scanner(aLine);
        String nameOfInstruction = "";
        int count = 0;
        Queue<String> instructions = new LinkedList<>();

        while (aScan.hasNext())
        {
            String instruction = aScan.next();

            if (!instruction.equals(":") && !instruction.equals(";"))
            {
                if (count == 1)
                {
                    nameOfInstruction = instruction;
                }
                else
                {
                    instructions.add(instruction);
                }
            }
            count++;
        }

        while (!instructions.isEmpty())
        {

        }

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

    public interface singleInstruction
    {
        List processInstruction(int a);
    }

    public interface twoInstruction
    {
        List processInstruction(int a, int b);
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


class Instruction
{
    
}




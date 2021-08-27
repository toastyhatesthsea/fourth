import java.util.*;
import java.util.concurrent.ExecutionException;

public class ForthEvaluator
{

    public Stack<Integer> integers;
    public Stack<String> instructions;
    public Queue<String> userInstructions;
    public Queue<Integer> queueOfIntegers;

    public HashMap<String, Instruction> listOfInstructions;


    public ForthEvaluator()
    {
        integers = new Stack<>();
        instructions = new Stack<>();
        userInstructions = new LinkedList<>();

        //queueOfIntegers.remove();

        listOfInstructions = new HashMap<>();

        listOfInstructions.put("+", new TwoInstruction("Addition", TwoInstruction.add));
        listOfInstructions.put("-", new TwoInstruction("Subtraction", TwoInstruction.minus));
        listOfInstructions.put("/", new TwoInstruction("Division", TwoInstruction.divide));
        listOfInstructions.put("*", new TwoInstruction("Multiplication", TwoInstruction.multiply));
        listOfInstructions.put("swap", new TwoInstruction("Swapping", TwoInstruction.swap));
        listOfInstructions.put("over", new TwoInstruction("Overing", TwoInstruction.over));


        SingleInstruction single = new SingleInstruction("rawr", SingleInstruction.duplicate);

        listOfInstructions.put("dup", new SingleInstruction("Duplicating", SingleInstruction.duplicate));
        listOfInstructions.put("drop", new SingleInstruction("Dropping", SingleInstruction.drop));


    }

    private void userInstructionsParser(String aLine, checkWord someChecker) throws IllegalArgumentException
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
                if (count == 1) //
                {
                    if (isInteger(instruction))
                    {
                        throw new IllegalArgumentException("Cannot redefine numbers");
                    } else
                    {
                        nameOfInstruction = instruction.toLowerCase();
                    }

                } else
                {
                    Instruction instructionFromList = this.listOfInstructions.get(instruction);

                    if (instructionFromList != null && instructionFromList.getClass() == UserInstruction.class)
                    {
                        instructions.addAll(instructionFromList.multipleInstructions);
                    } else
                    {
                        instructions.add(instruction.toLowerCase());
                    }
                }
            }
            count++;
        }

        this.listOfInstructions.put(nameOfInstruction, new UserInstruction(nameOfInstruction, instructions, this.listOfInstructions));


    }

    public boolean isInteger(String anInt)
    {
        try
        {
            Integer.parseInt(anInt);
            return true;
        } catch (Exception e)
        {
            return false;
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
            Stack aStack = new Stack();


            if (data.equals(":"))
            {
                userInstruction = true;
            } else if (userInstruction)
            {
                checkWord someChecker = (String someWord) -> (!someWord.equals(";") && !someWord.equals(":"));

                userInstructionsParser(aLine, someChecker);
                done = true;
            } else
            {
                checkWord someChecker = (String someWord) -> (true);
                //process(aLine, someChecker, integers);
                //processRegularInstruction(aLine, someChecker);
                processRegularInstructionPartTwo(aLine, someChecker);
                done = true;
            }
        }

    }

    public List<Integer> evaluate()
    {
        ArrayList<Integer> answer = new ArrayList<>();
        while (!this.instructions.isEmpty())
        {
            String data = this.instructions.remove(0);

            Instruction anInstruction = this.listOfInstructions.get(data);

            anInstruction.processInstruction(this.integers);
        }

        return answer;
    }

    public void process(String aLine, checkWord aChecker, Queue aStack)
    {
        Scanner aScan = new Scanner(aLine);

        while (aScan.hasNext())
        {
            String aWord = aScan.next();

            if (aChecker.processLine(aWord))
            {
                aStack.add(aWord);
            }
        }
    }

    public void processRegularInstruction(String line, checkWord aChecker)
    {
        Scanner aScan = new Scanner(line);

        while (aScan.hasNext())
        {
            String word = aScan.next();

            try
            {
                Integer anInt = Integer.parseInt(word);
                this.integers.add(anInt);
            } catch (Exception e)
            {
                this.instructions.add(word);
            }
        }
    }

    public void processRegularInstructionPartTwo(String line, checkWord aChecker)
    {
        Scanner aScan = new Scanner(line);

        while (aScan.hasNext())
        {
            String data = aScan.next();

            try
            {
                Integer anInt = Integer.parseInt(data);
                this.integers.add(anInt);
            } catch (Exception e)
            {
                Instruction anInstruction = this.listOfInstructions.get(data.toLowerCase());

                if (anInstruction == null)
                {
                    throw new IllegalArgumentException("No definition available for operator \"" + data+ "\"");
                }

                anInstruction.processInstruction(this.integers);
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
            //TODO Must check for errors of using instructions that require two arguments
            String line = (String) aLine;
            parser(line);
            //evaluate();
        }

        //Scanner aScan = new Scanner(aList);

        return (List) this.integers;
    }

}


interface singleInstructions
{
    Stack processInstruction(int a);
}

interface twoInstructions
{
    Stack processInstruction(int a, int b);
}

interface threeInstruction extends singleInstructions
{
    List processInstruction(int a, int b, int c);
}






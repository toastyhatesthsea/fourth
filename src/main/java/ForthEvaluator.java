import java.util.*;

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

        SingleInstruction single = new SingleInstruction("rawr", SingleInstruction.duplicate);

        listOfInstructions.put("dup", new SingleInstruction("Duplicating", SingleInstruction.duplicate));


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
                } else
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
            Stack aStack = new Stack();


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

            this.integers = (Stack<Integer>) anInstruction.processInstruction(this.integers);
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
                Instruction anInstruction = this.listOfInstructions.get(data);
                Stack<Integer> answer = (Stack<Integer>) anInstruction.processInstruction(this.integers);
                this.integers.addAll(answer);
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


abstract class Instruction
{
    String name;
    Queue<String> multipleInstructions;


    public Instruction(String aName, Queue<String> aMultipleInstructions)
    {
        this.multipleInstructions = aMultipleInstructions;
        this.name = aName;
    }

    public Instruction(String aName)
    {
        this.name = aName;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Queue<String> getMultipleInstructions()
    {
        return multipleInstructions;
    }

    public void setMultipleInstructions(Queue<String> multipleInstructions)
    {
        this.multipleInstructions = multipleInstructions;
    }

    abstract public List<Integer> processInstruction(Stack<Integer> aStack);
}


class UserInstruction extends Instruction
{
    public UserInstruction(String aName, Queue<String> instructions)
    {
        super(aName, instructions);
    }

    @Override
    public List<Integer> processInstruction(Stack<Integer> someIntegers)
    {
        return null;
    }
}

class TwoInstruction extends Instruction
{
    twoInstructions anInstruction;

    @Override
    public List<Integer> processInstruction(Stack<Integer> aStack) throws IllegalArgumentException
    {
        Integer intOne;
        Integer intTwo;
        try
        {
            intOne = aStack.pop();
            intTwo = aStack.pop();

            Stack<Integer> answer = anInstruction.processInstruction(intOne, intTwo);

            //aStack.addAll(answer);

            return answer;
        } catch (NullPointerException e)
        {
            IllegalArgumentException meow = new IllegalArgumentException(this.getName() + " requires " +
                    "that the stack contain at least 2 values");
            throw meow;
        } catch (ArrayIndexOutOfBoundsException e)
        {
            IllegalArgumentException meow = new IllegalArgumentException(this.getName() + " requires " +
                    "that the stack contain at least 2 values");
            throw meow;
        }

    }

    public static twoInstructions add = (int a, int b) ->
    {
        Stack<Integer> answer = new Stack<>();
        answer.add(a + b);
        return answer;
    };

    public static twoInstructions minus = (int a, int b) ->
    {
        Stack<Integer> answer = new Stack<>();
        answer.add(a - b);
        return answer;
    };

    public static twoInstructions divide = (int a, int b) ->
    {
        Stack<Integer> answer = new Stack<>();
        try
        {
            answer.add(a / b);
        } catch (ArithmeticException e)
        {
            IllegalArgumentException meow = new IllegalArgumentException("Division by 0 is not allowed");
            throw meow;
        }
        return answer;
    };

    public static twoInstructions multiply = (int a, int b) ->
    {
        Stack<Integer> answer = new Stack<>();
        answer.add(a * b);
        return answer;
    };


    public TwoInstruction(String aName, twoInstructions aFunc)
    {
        super(aName);
        this.anInstruction = aFunc;
    }
}

class SingleInstruction extends Instruction
{
    singleInstructions anInstruction;

    public static singleInstructions duplicate = (int anInt) ->
    {
        Stack<Integer> answer = new Stack<>();

        answer.push(anInt);
        answer.push(anInt);

        return answer;
    };

    public SingleInstruction(String aName, singleInstructions aFunc)
    {
        super(aName);
        this.anInstruction = aFunc;
    }

    @Override
    public List<Integer> processInstruction(Stack<Integer> someIntegers)
    {
        Integer intOne;

        try
        {
            intOne = someIntegers.pop();

            Stack<Integer> answer = anInstruction.processInstruction(intOne);

            return answer;
        } catch (NullPointerException e)
        {
            IllegalArgumentException meow = new IllegalArgumentException(this.getName() + " requires " +
                    "that the stack contain at least 1 value");
            throw meow;
        } catch (EmptyStackException e)
        {
            IllegalArgumentException meow = new IllegalArgumentException(this.getName() + " requires " +
                    "that the stack contain at least 1 value");
            throw meow;
        }
    }

    public singleInstructions getAnInstruction()
    {
        return anInstruction;
    }

    public void setAnInstruction(singleInstructions anInstruction)
    {
        this.anInstruction = anInstruction;
    }
}



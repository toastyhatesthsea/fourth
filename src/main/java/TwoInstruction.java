import java.util.List;
import java.util.Stack;


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






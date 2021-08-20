import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;


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

    public static singleInstructions drop = (int anInt) ->
    {
        Stack<Integer> answer = new Stack<>();
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



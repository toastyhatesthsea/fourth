import java.util.List;
import java.util.Queue;
import java.util.Stack;


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

    abstract public void processInstruction(Stack<Integer> aStack);
}



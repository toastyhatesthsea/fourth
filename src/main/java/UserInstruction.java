import java.security.cert.TrustAnchor;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ExecutionException;

class UserInstruction extends Instruction
{
    HashMap<String, Instruction> instructionList;

    public UserInstruction(String aName, Queue<String> instructions, HashMap listOfInstructions)
    {
        super(aName, instructions);

        instructionList = listOfInstructions;
    }

    @Override
    public void processInstruction(Stack<Integer> someIntegers)
    {

        Stack<Integer> answer = new Stack<>();

        for (String aString : super.multipleInstructions)
        {
            try
            {
                int aNum = Integer.parseInt(aString);
                someIntegers.add(aNum);
            } catch (Exception e)
            {
                Instruction anInstruction = instructionList.get(aString);

                anInstruction.processInstruction(someIntegers);
            }
        }
    }
}

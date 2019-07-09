package tracinggenealogies;

public class LinkedQueue
{
    private class Node
    {
        String value;
        Node next;
        Node(String val, Node n)
        {
            value = val;
            next = n;
        }
    }
    
    private Node front = null;
    private Node rear = null;
    
    public void enqueue(String s)
    {
        if (rear != null)
        {
            rear.next = new Node(s, null);
            rear = rear.next;
        }
        else
        {
            rear = new Node(s, null);
            front = rear;
        }
    }
    
    public boolean empty()
    {
        return front == null;
    }
    
    public String peek() throws Exception
    {
        if (empty())
            throw new Exception();
        else
            return front.value;
    }
    
    public String dequeue() throws Exception
    {
        if (empty())
            throw new Exception();
        else
        {
            String value = front.value;
            front = front.next;
            if (front == null) rear = null;
            return value;
        }
    }
    
    public String toString()
    {
        StringBuilder sBuilder = new StringBuilder();
        
        Node p = front;
        while (p != null)
        {
            sBuilder.append(p.value + " ");
            p = p.next;
        }
        return sBuilder.toString();
    }
}

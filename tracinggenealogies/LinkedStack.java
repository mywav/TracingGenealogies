
package tracinggenealogies;

import java.util.EmptyStackException;

/**
 *
 * @author Ryan.Newbold
 */
public class LinkedStack 
{
    private class Node
    {
        String parent;
        int numOfChildren;
        String[] arrayOfChildren;
        Node next;
        
        Node(String parentName, int children, String[] childrenArray, Node n)
        {
            parent = parentName;
            numOfChildren = children;
            arrayOfChildren = childrenArray;
            next = n;
        }
        
    public boolean hasThisChild(Node p, String name)
        {
            boolean hasTheChild = false;
            for (int index = 0; index < p.arrayOfChildren.length; index++)
            {
                if (arrayOfChildren[index].compareTo(name) == 0)
                hasTheChild = true;
            }
            return hasTheChild;
        }
    }
    
    private Node top = null;
    
    public boolean isEmpty()
    {
        return top == null;
    }
    
    public void push(String name, int number, String[] array)
    {
        top = new Node(name, number, array, top);
    }
    
    public String peekName()
    {
        if (isEmpty())
            throw new EmptyStackException();
        else return top.parent;
    }
    
    public int peekNumber()
    {
        if (isEmpty())
            throw new EmptyStackException();
        else return top.numOfChildren;
    }
    
    public String[] peekArray()
    {
        if (isEmpty())
            throw new EmptyStackException();
        else return top.arrayOfChildren;
    }
            
    public String popName()
    {
        if (isEmpty())
            throw new EmptyStackException();
        else
        {
            String name = top.parent;
            return name;
        }
    }
    
    public int popNumber()
    {
        if (isEmpty())
            throw new EmptyStackException();
        else
        {
            int number = top.numOfChildren;
            return number;
        }
    }
    
    public String[] popArray()
    {
        if (isEmpty())
            throw new EmptyStackException();
        else
        {
            String[] array = top.arrayOfChildren;
            return array;
        }
    }
    
    public int size()
    {
        int count = 0;
        Node t = top;
        while (t != null)
        {
            count++;
            t = t.next;
        }
        return count;
    }
    
    public boolean isParent(LinkedStack linkedStack, String nameToSearch)
    {
        boolean yes = false;
        Node ref = linkedStack.top;
        while (ref != null)
        {
            if (String.valueOf(nameToSearch).compareTo(String.valueOf(ref.parent)) == 0)
                yes = true;
            ref = ref.next;
        }       
        return yes;
    }
    
    public String getParent(String name)
    {   
        String blank = null;
        Node p = top;
        while(p != null)
        {
            if (p.hasThisChild(p, name))
                    {
                        blank = p.parent;
                    }
            p = p.next;
        }
        return blank;
    }
    
   
    
    public boolean isChild(LinkedStack linkedStack, String nameToSearch)
    {
  
        boolean isChild = false;        
        
       
        Node ref = linkedStack.top;
        
        
        for (int index = 0; index < linkedStack.size(); index++)
        {            
            String[] refArray = ref.arrayOfChildren;
            for (int j = 0; j < ref.arrayOfChildren.length; j++)
            {
                if (nameToSearch.compareTo(refArray[j]) == 0)
                isChild = true;
            }
            ref = ref.next;
        }
        return isChild;
    }
}

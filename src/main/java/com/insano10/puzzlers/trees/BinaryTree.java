package com.insano10.puzzlers.trees;

import java.util.Optional;
import java.util.Stack;
import java.util.function.Consumer;

public class BinaryTree<T>
{
    private final  Node<T> root;

    public BinaryTree(Node<T> root)
    {
        this.root = root;
    }

    public void traversePreorder(Consumer<T> onVisit)
    {
        Stack<Node<T>> working = new Stack<>();
        working.push(root);

        while(!working.isEmpty())
        {
            Node<T> next = working.pop();
            onVisit.accept(next.data);

            if(next.right != null)
            {
                working.push(next.right);
            }
            if(next.left != null)
            {
                working.push(next.left);
            }
        }
    }

    public void traverseInorder(Consumer<T> onVisit)
    {
        Stack<Node<T>> working = new Stack<>();
        Node<T> nextNode = root;

        while(true)
        {
            if(nextNode != null)
            {
                //keep going left
                working.push(nextNode);
                nextNode = nextNode.left;
            }
            else
            {
                //we've reached a leaf
                if(!working.isEmpty())
                {
                    Node<T> nodeToVisit = working.pop();
                    onVisit.accept(nodeToVisit.data);
                    nextNode = nodeToVisit.right;
                }
                else
                {
                    break;
                }
            }
        }
    }

    public void traversePostorder(Consumer<T> onVisit)
    {
        Stack<Node<T>> working = new Stack<>();

        Node<T> lastNode = null;
        working.push(root);

        while(!working.isEmpty())
        {
            Node<T> nextNode = working.peek();

            if(nextNode != null)
            {

                if(lastNode == null || nextNode.equals(lastNode.left) || nextNode.equals(lastNode.right))
                {
                    //going down the tree
                    if(nextNode.left != null)
                    {
                        working.push(nextNode.left);
                    }
                    else if(nextNode.right != null)
                    {
                        working.push(nextNode.right);
                    }
                    else
                    {
                        onVisit.accept(nextNode.data);
                        working.pop();
                    }
                }
                else if(nextNode.left.equals(lastNode))
                {
                    //going back up the tree on the left side
                    if(nextNode.right != null)
                    {
                        working.push(nextNode.right);
                    }
                    else
                    {
                        onVisit.accept(nextNode.data);
                        working.pop();
                    }
                }
                else if(nextNode.right.equals(lastNode))
                {
                    onVisit.accept(nextNode.data);
                    working.pop();
                }

                lastNode = nextNode;
            }
        }
    }
}

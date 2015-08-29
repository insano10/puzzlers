package com.insano10.puzzlers.trees;

import java.util.Stack;
import java.util.function.Consumer;

public class IterativeBinaryTree<T> implements BinaryTree<T>
{

    @Override
    public void traversePreorder(Node<T> root, Consumer<T> onVisit)
    {
        Stack<Node<T>> working = new Stack<>();
        working.push(root);

        while(!working.isEmpty())
        {
            Node<T> next = working.pop();
            onVisit.accept(next.getData());

            if(next.getRight() != null)
            {
                working.push(next.getRight());
            }
            if(next.getLeft() != null)
            {
                working.push(next.getLeft());
            }
        }
    }

    @Override
    public void traverseInorder(Node<T> root, Consumer<T> onVisit)
    {
        Stack<Node<T>> working = new Stack<>();
        Node<T> nextNode = root;

        while(true)
        {
            if(nextNode != null)
            {
                //keep going left
                working.push(nextNode);
                nextNode = nextNode.getLeft();
            }
            else
            {
                //we've reached a leaf
                if(!working.isEmpty())
                {
                    Node<T> nodeToVisit = working.pop();
                    onVisit.accept(nodeToVisit.getData());
                    nextNode = nodeToVisit.getRight();
                }
                else
                {
                    break;
                }
            }
        }
    }

    @Override
    public void traversePostorder(Node<T> root, Consumer<T> onVisit)
    {
        Stack<Node<T>> working = new Stack<>();

        Node<T> lastNode = null;
        working.push(root);

        while(!working.isEmpty())
        {
            Node<T> nextNode = working.peek();

            if(nextNode != null)
            {

                if(lastNode == null || nextNode.equals(lastNode.getLeft()) || nextNode.equals(lastNode.getRight()))
                {
                    //going down the tree
                    if(nextNode.getLeft() != null)
                    {
                        working.push(nextNode.getLeft());
                    }
                    else if(nextNode.getRight() != null)
                    {
                        working.push(nextNode.getRight());
                    }
                    else
                    {
                        onVisit.accept(nextNode.getData());
                        working.pop();
                    }
                }
                else if(nextNode.getLeft().equals(lastNode))
                {
                    //going back up the tree on the left side
                    if(nextNode.getRight() != null)
                    {
                        working.push(nextNode.getRight());
                    }
                    else
                    {
                        onVisit.accept(nextNode.getData());
                        working.pop();
                    }
                }
                else if(nextNode.getRight().equals(lastNode))
                {
                    onVisit.accept(nextNode.getData());
                    working.pop();
                }

                lastNode = nextNode;
            }
        }
    }
}

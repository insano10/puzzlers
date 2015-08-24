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

            next.right.ifPresent(working::push);
            next.left.ifPresent(working::push);
        }
    }

    public void traverseInorder(Consumer<T> onVisit)
    {
        Stack<Node<T>> working = new Stack<>();
        Optional<Node<T>> nextNode = Optional.of(root);

        while(true)
        {
            if(nextNode.isPresent())
            {
                //keep going left
                working.push(nextNode.get());
                nextNode = nextNode.get().left;
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

    }
}

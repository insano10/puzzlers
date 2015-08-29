package com.insano10.puzzlers.trees;

import java.util.Stack;
import java.util.function.Consumer;

public class IterativeBinaryTree<T> implements BinaryTree<T>
{

    @Override
    public void traversePreorder(BinaryTreeNode<T> root, Consumer<T> onVisit)
    {
        Stack<BinaryTreeNode<T>> working = new Stack<>();
        working.push(root);

        while(!working.isEmpty())
        {
            BinaryTreeNode<T> next = working.pop();
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
    public void traverseInorder(BinaryTreeNode<T> root, Consumer<T> onVisit)
    {
        Stack<BinaryTreeNode<T>> working = new Stack<>();
        BinaryTreeNode<T> nextNode = root;

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
                    BinaryTreeNode<T> nodeToVisit = working.pop();
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
    public void traversePostorder(BinaryTreeNode<T> root, Consumer<T> onVisit)
    {
        Stack<BinaryTreeNode<T>> working = new Stack<>();

        BinaryTreeNode<T> lastNode = null;
        working.push(root);

        while(!working.isEmpty())
        {
            BinaryTreeNode<T> nextNode = working.peek();

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

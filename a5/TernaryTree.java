package cs445.a5;

import cs445.a5.StackAndQueuePackage.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TernaryTree<T> implements TernaryTreeInterface<T> {
    private TernaryNode<T> root;

    public TernaryTree() {
        root = null;
    }

    public TernaryTree(T rootData) {
        root = new TernaryNode<>(rootData);
    }

    public TernaryTree(T rootData, TernaryTree<T> leftTree,
                       TernaryTree<T> middleTree,
                       TernaryTree<T> rightTree) {
        privateSetTree(rootData, leftTree, middleTree, rightTree);
    }

    public void setTree(T rootData) {
        root = new TernaryNode<>(rootData);
    }

    public void setTree(T rootData, TernaryTreeInterface<T> leftTree, TernaryTreeInterface<T> middleTree,
                        TernaryTreeInterface<T> rightTree) {
        privateSetTree(rootData, (TernaryTree<T>) leftTree,
                (TernaryTree<T>) middleTree, (TernaryTree<T>) rightTree);
    }

    private void privateSetTree(T rootData, TernaryTree<T> leftTree,
                                TernaryTree<T> middleTree,
                                TernaryTree<T> rightTree) {
        TernaryNode<T> root = new TernaryNode<>(rootData);
        if((leftTree != null) && (!leftTree.isEmpty())) {
            root.setLeftChild(leftTree.root);
        }
        if((middleTree != null) && (!middleTree.isEmpty())) {
            if((middleTree != leftTree)) {
                root.setMiddleChild(middleTree.root);
            } else {
                root.setMiddleChild(middleTree.root);
            }
        }
        if((rightTree != null) && (!rightTree.isEmpty())) {
            if((rightTree != leftTree) && (rightTree != middleTree)) {
                root.setRightChild(rightTree.root);
            } else {
                root.setRightChild(rightTree.root.copy());
            }
        }
        this.root = root;
        if((leftTree != null) && (leftTree != this)) {
            leftTree.clear();
        }
        if((middleTree != null) && (middleTree != this)) {
            middleTree.clear();
        }
        if((rightTree != null) && (rightTree != this)) {
            rightTree.clear();
        }
    }

    public T getRootData() {
        if(isEmpty()) {
            throw new EmptyTreeException();
        } else {
            return root.getData();
        }
    }

    protected void setRootData(T rootData) {
        root.setData(rootData);
    }

    protected void setRootNode(TernaryNode<T> rootNode) {
        root = rootNode;
    }

    protected TernaryNode<T> getRootNode() {
        return root;
    }

    public int getHeight() {
        int height = 0;
        if(!isEmpty()) {
            height = root.getHeight();
        }
        return height;
    }

    public int getNumberOfNodes() {
        int numOfNodes = 0;
        if(!isEmpty()) {
            numOfNodes = root.getNumberOfNodes();
        }
        return numOfNodes;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
    }

    public Iterator<T> getPreorderIterator() {
        return new PreorderIterator();
    }

    public Iterator<T> getInorderIterator() {
        throw new UnsupportedOperationException("The TernaryTree class does not support inorder iteration because it" +
                "cannot be evenly split into halves");
    }

    public Iterator<T> getPostorderIterator() {
        return new PostorderIterator();
    }

    public Iterator<T> getLevelOrderIterator() {
        return new LevelOrderIterator();
    }

    private class PreorderIterator implements Iterator {
        private StackInterface<TernaryNode<T>> nodeStack;

        public PreorderIterator() {
            nodeStack = new LinkedStack<>();
            if(root != null) {
                nodeStack.push((TernaryNode<T>) root);
            }
        }

        public T next() {
            TernaryNode<T> nextNode;

            if(hasNext()) {
                nextNode = nodeStack.pop();
                TernaryNode<T> leftChild = nextNode.getLeftChild();
                TernaryNode<T> middleChild = nextNode.getMiddleChild();
                TernaryNode<T> rightChild = nextNode.getRightChild();


                if (rightChild != null) {
                    nodeStack.push(rightChild);
                }
                if (leftChild != null) {
                    nodeStack.push(leftChild);
                }
                if (middleChild != null) {
                    nodeStack.push(middleChild);
                }
            } else {
                throw new NoSuchElementException();
            }
            return nextNode.getData();
        }

        public boolean hasNext() {
            return !nodeStack.isEmpty();
        }

        public T remove(T elem) {
            throw new UnsupportedOperationException("The TernaryTree class does not support removal of nodes.");
        }
    }

    private class PostorderIterator implements Iterator<T> {
        private LinkedStack<TernaryNode<T>> nodeStack;
        private TernaryNode<T> currNode;

        public PostorderIterator() {
            nodeStack = new LinkedStack<>();
            currNode = root;
        }

        public T next() {
            boolean foundNext = false;
            TernaryNode<T> leftChild, middleChild, rightChild, nextNode = null;

            while(currNode != null) {
                nodeStack.push(currNode);
                leftChild = currNode.getLeftChild();
                middleChild = currNode.getMiddleChild();
                rightChild = currNode.getRightChild();
                if(leftChild == null && middleChild == null) {
                    currNode = currNode.getRightChild();
                } else if(leftChild == null) {
                    currNode = currNode.getMiddleChild();
                } else {
                    currNode = currNode.getLeftChild();
                }
            }

            if(!nodeStack.isEmpty()) {
                nextNode = nodeStack.pop();

                TernaryNode<T> parent = null;
                if(!nodeStack.isEmpty()) {
                    parent = nodeStack.peek();
                    if(nextNode == parent.getLeftChild()) {
                        currNode = parent.getMiddleChild();
                    } else if(nextNode == parent.getMiddleChild()) {
                        currNode = parent.getRightChild();
                    } else {
                        currNode = null;
                    }
                } else {
                    currNode = null;
                }
            } else {
                throw new NoSuchElementException();
            }
            return nextNode.getData();
        }

        public boolean hasNext() {
            return !nodeStack.isEmpty() || (currNode != null);
        }

        public T remove(T elem) {
            throw new UnsupportedOperationException("The TernaryTree class does not support removal of nodes.");
        }
    }

    private class LevelOrderIterator implements Iterator<T> {
        private QueueInterface<TernaryNode<T>> nodeQueue;

        public LevelOrderIterator() {
            nodeQueue = new LinkedQueue<>();
            if(root != null) {
                nodeQueue.enqueue(root);
            }
        }

        public T next() {
            TernaryNode<T> nextNode;

            if(hasNext()) {
                nextNode = nodeQueue.dequeue();
                TernaryNode<T> leftChild = nextNode.getLeftChild();
                TernaryNode<T> middleChild = nextNode.getMiddleChild();
                TernaryNode<T> rightChild = nextNode.getRightChild();

                if(leftChild != null) {
                    nodeQueue.equals(leftChild);
                }
                if(middleChild != null) {
                    nodeQueue.equals(middleChild);
                }
                if(rightChild != null) {
                    nodeQueue.equals(rightChild);
                }
            } else {
                throw new NoSuchElementException();
            }
            return nextNode.getData();
        }

        public boolean hasNext() {
            return !nodeQueue.isEmpty();
        }

        public T remove(T elem) {
            throw new UnsupportedOperationException("The TernaryTree class does not support removal of nodes.");
        }
    }
}

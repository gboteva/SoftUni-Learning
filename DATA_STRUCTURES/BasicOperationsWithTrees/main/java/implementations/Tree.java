package implementations;

import interfaces.AbstractTree;

import java.util.*;

@SuppressWarnings("all")


public class Tree<E> implements AbstractTree<E> {
    private E key;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E key, Tree<E>... children) {
        this.key = key;
        this.children = new ArrayList<>();

        for (Tree<E> child : children) {
            this.children.add(child);
            child.parent = this;
        }
    }

    @Override
    public List<E> orderBfs() {
        //Breadth-First Search
        List<E> result = new ArrayList<>();
        Deque<Tree<E>> queue = new ArrayDeque<>();

        if (this.key == null){ //this is add because Remove operation
            return result;     //must return empty list if remove the root
        }
        queue.offer(this);

        while (!queue.isEmpty()) {
            Tree<E> currentTree = queue.poll();
            result.add(currentTree.key);

            for (Tree<E> child : currentTree.children) {
                queue.offer(child);
            }
        }
        return result;
    }

    @Override
    public List<E> orderDfs() {
        //Depth-First Search
        List<E> result = new ArrayList<>();
        depthFirstSearchRecursion(result, this);
        return result;
    }

    private void depthFirstSearchRecursion(List<E> result, Tree<E> tree) {
        for (Tree<E> child : tree.children) {
            depthFirstSearchRecursion(result, child);
        }
        result.add(tree.key);
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {
        searchParentAndAddChild(parentKey, this, child);
    }

    private void searchParentAndAddChild(E parentKey, Tree<E> startTree, Tree<E> child) {
        Tree<E> parrent = getTreeWithGivenKey(parentKey, startTree);
        child.parent = parrent;
        parrent.children.add(child);
    }

    private Tree<E> getTreeWithGivenKey(E parentKey, Tree<E> tree) {

//       With Breadth first search
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(tree);

        while (!queue.isEmpty()) {
            Tree<E> current = queue.poll();
            if (current.key.equals(parentKey)) {
                return current;
            }

            for (Tree<E> child : current.children) {
                queue.offer(child);
            }
        }
        return null;

//        //with depth first search
//        if (tree.key.equals(parentKey)){
//            return tree;
//        }
//
//        for (Tree<E> child : tree.children) {
//           Tree<E> found = getParent(parentKey, child);
//
//           if (found!=null){
//               return found;
//           }
//        }
//
//        return null;
    }

    @Override
    public void removeNode(E nodeKey) {
        Tree<E> treeToRemove = getTreeWithGivenKey(nodeKey, this);

        if (treeToRemove == null){
            throw new IllegalArgumentException("This node doesn't exist");
        }

        for (Tree<E> child : treeToRemove.children) {
            child.parent = null;
        }

        treeToRemove.children.clear();

        Tree<E> parent = treeToRemove.parent;

        if (parent != null){
            //if try to remove the root will throw
            parent.children.remove(treeToRemove);
        }

        treeToRemove.key = null;

    }

    @Override
    public void swap(E firstKey, E secondKey) {
        Tree<E> firstTree = getTreeWithGivenKey(firstKey, this);
        Tree<E> secondTree = getTreeWithGivenKey(secondKey, this);

        if (firstTree == null || secondTree == null){
            throw new IllegalArgumentException("One of nodes missing");
        }

        Tree<E> firstParent = firstTree.parent;
        Tree<E> secondParent = secondTree.parent;

        if (firstParent == null){
            this.key = secondTree.key;
            this.parent = null;
            this.children = secondTree.children;
            return;
        }else if (secondParent == null){
            this.key = firstTree.key;
            this.parent = null;
            this.children = firstTree.children;
            return;
        }

        firstTree.parent = secondParent;
        secondTree.parent = firstParent;

        int firstIndex = firstParent.children.indexOf(firstTree);
        int secondIndex = secondParent.children.indexOf(secondTree);

        firstParent.children.set(firstIndex,secondTree);
        secondParent.children.set(secondIndex, firstTree);
    }
}




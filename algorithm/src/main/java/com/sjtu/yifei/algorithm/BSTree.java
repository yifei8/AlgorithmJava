package com.sjtu.yifei.algorithm;

/**
 * 类描述：二叉查找树（Binary Search Tree，简称BST）
 * <p>
 * 二叉查找树是一棵二叉树，它的左子节点的值比父节点的值要小，右节点的值要比父节点的值大。它的高度决定了它的查找效率。
 * <p>
 * 在理想的情况下，二叉查找树增删查改的时间复杂度为O(logN)（其中N为节点数），最坏的情况下为O(N)。当它的高度为logN+1时，我们就说二叉查找树是平衡的。
 * <p>
 * 创建人：yifei
 * 创建时间：2018/12/13
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class BSTree<T extends Comparable<T>> {

    public BSTreeNode<T> root;

    public BSTree(T data) {
        this.root = new BSTreeNode<>(data);
    }

    public void addNode(T data) {
        BSTreeNode<T> parent = root; //parent表示待添加的节点的父节点
        while (true) {
            if (data.equals(parent.data)) {//插入失败
                break;
            } else if (data.compareTo(parent.data) < 0) {
                if (parent.left == null) {
                    parent.left = new BSTreeNode<>(data, parent);
                    break;
                } else {
                    parent = parent.left;
                }
            } else {
                if (parent.right == null) {
                    parent.right = new BSTreeNode<>(data, parent);
                    break;
                } else {
                    parent = parent.right;
                }
            }
        }
    }

    /**
     * 删除节点
     *
     * @param data
     * @return
     */
    public boolean deleteNode(T data) {
        BSTree.BSTreeNode<T> delNode = searchNode(data);//先找出待删除待节点
        if (delNode == null) {//没有该节点，无需删除
            return false;
        }
        if (delNode.left == null && delNode.right == null) {//左右子树都为空
            if (delNode.parent.left == delNode) {
                delNode.parent.left = null;
            } else {
                delNode.parent.right = null;
            }
        } else if (delNode.left != null && delNode.right != null) { //左右子树都不为空
            BSTree.BSTreeNode<T> changeNode = getLastRightNode(delNode);
            delNode.data = changeNode.data;
            if (changeNode.parent.left == delNode) {
                changeNode.parent.left = null;
            } else {
                changeNode.parent.right = null;
            }
        } else if (delNode.left != null) {//左子树不为空
            if (delNode.parent.left == delNode) {
                delNode.parent.left = delNode.left;
            } else {
                delNode.parent.right = delNode.left;
            }
        } else {//右子树不为空
            if (delNode.parent.left == delNode) {
                delNode.parent.left = delNode.right;
            } else {
                delNode.parent.right = delNode.right;
            }
        }

        return false;
    }

    /**
     * 在二叉查找树中查找值为search的节点
     *
     * @param search 查找节点值
     * @return
     */
    public BSTreeNode<T> searchNode(T search) {
        BSTreeNode<T> cursor = root;
        while (cursor != null) {
            if (search.equals(cursor.data)) {
                return cursor;
            } else if (search.compareTo(cursor.data) < 0) {
                cursor = cursor.left;
            } else {
                cursor = cursor.right;
            }
        }
        return null;
    }

    /**
     * 返回当前节点下最大的节点
     *
     * @param node
     * @return
     */
    private BSTreeNode<T> getLastRightNode(BSTree.BSTreeNode<T> node) {
        while (node != null) {
            if (node.right != null) {
                node = node.right;
            } else if (node.left != null) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }

    public static class BSTreeNode<T extends Comparable<T>> {
        T data;
        BSTreeNode<T> parent;
        BSTreeNode<T> left;
        BSTreeNode<T> right;

        public BSTreeNode(T data) {
            this.data = data;
        }

        public BSTreeNode(T data, BSTreeNode<T> parent) {
            this.data = data;
            this.parent = parent;
        }
    }
}

package com.sjtu.yifei.algorithm;

/**
 * 类描述：红黑树（Red-Black Tree，以下简称RBTree）它一种特殊的二叉查找树。
 * 红黑树是特殊的二叉查找树，意味着它满足二叉查找树的特征：任意一个节点所包含的键值，大于等于左孩子的键值，小于等于右孩子的键值。
 * 除了具备该特性之外，红黑树还包括许多额外的信息。
 * <p>
 * 红黑树的每个节点上都有存储位表示节点的颜色，颜色是红(Red)或黑(Black)。
 * 红黑树的特性:
 * (1) 每个节点或者是黑色，或者是红色。
 * (2) 根节点是黑色。
 * (3) 每个叶子节点是黑色。 [注意：这里叶子节点，是指为空的叶子节点！]
 * (4) 如果一个节点是红色的，则它的子节点必须是黑色的。
 * (5) 从一个节点到该节点的子孙节点的所有路径上包含相同数目的黑节点。
 * <p>
 * 关于它的特性，需要注意的是：
 * 第一，特性(3)中的叶子节点，是只为空(NIL或null)的节点。
 * 第二，特性(5)，确保没有一条路径会比其他路径长出俩倍。因而，红黑树是相对是接近平衡的二叉树。
 * <p>
 * 创建人：yifei
 * 创建时间：2018/12/13
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class RBTree<T extends Comparable<T>> {

    public RBTreeNode<T> root;

    public RBTree() {
    }

    public RBTree(T data) {
        this.root = new RBTreeNode<>(data, false);
    }

    public RBTreeNode<T> searchNode(T data) {
        RBTreeNode<T> cursor = root;
        while (cursor != null) {
            if (data.equals(cursor.data)) {
                return cursor;
            } else if (data.compareTo(cursor.data) < 0) {
                cursor = cursor.left;
            } else {
                cursor = cursor.right;
            }
        }
        return null;
    }

    public void addNode(T data) {
        RBTreeNode<T> addNode = null;
        RBTreeNode<T> parent = root;
        if (parent == null) {
            addNode = new RBTreeNode<>(data, false);
            parent = addNode;
            root = parent;
        }
        while (true) {
            if (data.equals(parent.data)) {
                break;
            } else if (data.compareTo(parent.data) < 0) {
                if (parent.left != null) {
                    parent = parent.left;
                } else {
                    addNode = new RBTreeNode<>(data, true, parent);
                    parent.left = addNode;
                    fixRBTree(addNode);
                    break;
                }
            } else {
                if (parent.right != null) {
                    parent = parent.right;
                } else {
                    addNode = new RBTreeNode<>(data, true, parent);
                    parent.right = addNode;
                    fixRBTree(addNode);
                    break;
                }
            }
        }
        // TODO: 2018/12/13 通过旋转和重新着色等方法来修正该树，使之重新成为一颗红黑树。
    }

    /**
     * 插入修复情况1：如果当前结点的父结点是红色且祖父结点的另一个子结点（叔叔结点）是红色
     * 插入修复情况2：当前结点的父结点是红色,叔叔结点是黑色，当前结点是其父结点的右子
     * 插入修复情况3：当前结点的父结点是红色,叔叔结点是黑色，当前结点是其父结点的左子
     *
     * @param addNode
     */
    private void fixRBTree(RBTreeNode<T> addNode) {
        RBTreeNode<T> parent = addNode.parent;
        while (parent != null && parent.isRed && parent.parent != null) {
            RBTreeNode<T> gParent = parent.parent;
            RBTreeNode<T> brother;
            if (gParent.left == parent) {
                brother = gParent.right;
            } else {
                brother = gParent.left;
            }
            //情况1：如果当前结点的父结点是红色且祖父结点的另一个子结点（叔叔结点）是红色
            //解法： 将当前结点的父结点和叔叔结点涂黑，祖父结点涂红，把当前结点指向祖父结点，从新的当前结点重新开始算法。
            if (brother != null && brother.isRed) {
                parent.left.isRed = false;
                if (parent.right != null) {
                    parent.right.isRed = false;
                }
                gParent.isRed = true;
                addNode = gParent;
            } else {
                //情况2：当前结点的父结点是红色,叔叔结点是黑色，当前结点是其父结点的右子
                //解法： 当前结点的父结点做为新的当前结点，以新当前结点为支点左旋
                if (parent.right == addNode) {//修复情况2
                    addNode = addNode.parent;
                    rotateLeft(addNode); //左旋
                }
                //情况3：当前结点的父结点是红色,叔叔结点是黑色，当前结点是其父结点的左子
                //解法： 父结点变为黑色，祖父结点变为红色，在祖父结点为支点右旋
                else {//修复情况3
                    parent.isRed = false;
                    gParent.isRed = true;
                    rotateRight(gParent); //右旋
                }
            }
            parent = addNode.parent;
        }
    }

    @Deprecated //还未完成
    private void deleteNode(T data) {
        //先找到待删除的节点
        RBTreeNode<T> delNode = searchNode(data);
        if (delNode.left == null && delNode.right == null) {//左右子树都为空时，直接删除该节点
            if (delNode.parent.left == delNode) {
                delNode.parent.left = null;
            } else {
                delNode.parent.right = null;
            }
        } else if (delNode.left != null && delNode.right != null) {//左右子树都不为空时
            RBTreeNode<T> changeNode = getLastRightNode(delNode);
            delNode.data = changeNode.data;
            if (changeNode.parent.left == changeNode) {
                changeNode.parent.left = null;
            } else {
                changeNode.parent.right = null;
            }
            // TODO: 2018/12/13  通过旋转和重新着色等方法来修正该树，使之重新成为一颗红黑树。
        } else if (delNode.right != null) {//左子树为空，右子树不为空
            if (delNode.parent.left == delNode) {
                delNode.parent.left = delNode.right;
            } else {
                delNode.parent.right = delNode.right;
            }
            // TODO: 2018/12/13
        } else {
            if (delNode.parent.left == delNode) {
                delNode.parent.left = delNode.left;
            } else {
                delNode.parent.right = delNode.left;
            }
        }
    }

    /**
     * 返回当前节点下最大的节点
     *
     * @param node
     * @return
     */
    private RBTreeNode<T> getLastRightNode(RBTreeNode<T> node) {
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

    /**
     * 左旋
     *
     * @param node
     * @return
     */
    private boolean rotateLeft(RBTreeNode<T> node) {
        RBTreeNode<T> rightNode = node.right;
        if (rightNode == null) { // 右子树为空就不需要左旋转
            return false;
        }
        //step 1: 旋转节点的右子树(rightNode) 与 右节点的左子树 关联
        node.right = rightNode.left; //右子树的左节点给节点的右子树
        rightNode.left.parent = node;

        //step 2: 旋转节点与父节点解除关联，父节点指向当前节点的子树指向rightNode
        RBTreeNode<T> parent = node.parent;
        rightNode.parent = parent;
        if (parent.left == node) {
            parent.left = rightNode;
        } else {
            parent.right = rightNode;
        }

        //step3: rightNode的左子树指向旋转节点
        rightNode.left = node;
        node.parent = rightNode;
        return true;
    }

    /**
     * 右旋 原理同左旋
     *
     * @param node
     * @return
     */
    private boolean rotateRight(RBTreeNode<T> node) {
        RBTreeNode<T> leftNode = node.left;
        if (leftNode == null) {
            return false;
        }
        //step 1:
        node.left = leftNode.right;
        leftNode.right.parent = node;
        //step 2
        RBTreeNode<T> parent = node.parent;
        leftNode.parent = parent;
        if (parent.left == node) {
            parent.left = leftNode;
        } else {
            parent.right = leftNode;
        }
        //step3
        leftNode.right = node;
        node.parent = leftNode;
        return true;
    }

    public static class RBTreeNode<T extends Comparable<T>> {
        T data;
        boolean isRed; //true 红色节点；false 黑色节点
        RBTreeNode<T> parent;
        RBTreeNode<T> left;
        RBTreeNode<T> right;

        public RBTreeNode(T data, boolean isRed) {
            this.data = data;
            this.isRed = isRed;
        }

        public RBTreeNode(T data, boolean isRed, RBTreeNode<T> parent) {
            this.data = data;
            this.isRed = isRed;
            this.parent = parent;
        }
    }
}

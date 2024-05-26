package com.fizz.ds.bt;

import lombok.Getter;

@Getter
public class BST {

    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert(10);
        bst.insert(1);
        bst.insert(7);
        bst.insert(9);
        bst.insert(99);
        bst.insert(56);
        bst.insert(34);
        bst.insert(77);
        bst.insert(229);
        Node search = bst.search(9);
        System.out.println(search.data);

        BST.inorder(bst.getRoot());

        System.out.println("Tree Structure:");
        bst.printTreeStructure(bst.getRoot(), "", true);
    }

        // 新增打印树结构的方法
    public void printTreeStructure(Node node, String prefix, boolean isLeft) {
        if (node == null) {
            return;
        }

        // 打印当前节点
        if (isLeft) {
            System.out.println(prefix + "├───" + node.data);
        } else {
            System.out.println(prefix + "└───" + node.data);
        }

        // 递归打印子树，调整prefix以反映连接线
        // 注意这里的递归调用顺序，先处理左子树再处理右子树，同时更新isLeft状态
        printTreeStructure(node.left, prefix + (isLeft ? "│   " : "    "), true);
        printTreeStructure(node.right, prefix + (isLeft ? "│   " : "    "), false);
    }

    private Node root;

    public static class Node {
        private Node left;
        private Node right;
        private final int data;
        public Node(int data) {
            this.data = data;
        }
    }

    public Node search(int data) {
        if (root == null) {
            return null;
        }
        Node current = root;
        while (current != null) {
            if (data == current.data) {
                return current;
            } else if (data > current.data) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return null;
    }

    public Node insert(int data) {
        if (root == null) {
           return root = new Node(data);
        }
        Node current = root;
        while (true) {
            if (data < current.data) {
                if (current.left == null) {
                    return current.left = new Node(data);
                } else {
                    current = current.left;
                }
            } else if (data > current.data) {
                if (current.right == null) {
                    return current.right = new Node(data);
                } else {
                    current = current.right;
                }
            }
        }
    }

    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }
}

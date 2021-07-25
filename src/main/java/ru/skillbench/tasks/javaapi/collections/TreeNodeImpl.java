package ru.skillbench.tasks.javaapi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreeNodeImpl implements TreeNode {
    TreeNode parent;
    List<TreeNode> children = new ArrayList<>();
    boolean expanded = false;
    Object data;

    @Override
    public TreeNode getParent() {
        return parent;
    }

    @Override
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    @Override
    public TreeNode getRoot() {
        if (parent == null) {
            return null;
        }
        TreeNode tn = parent;
        while (tn.getParent() != null) {
            tn = tn.getParent();
        }
        return tn;
    }

    @Override
    public boolean isLeaf() {
        return getChildCount() == 0;
    }

    @Override
    public int getChildCount() {
        return this.children.size();
    }

    @Override
    public Iterator<TreeNode> getChildrenIterator() {
        return children.iterator();
    }

    @Override
    public void addChild(TreeNode child) {
        this.children.add(child);
        child.setParent(this);
    }

    @Override
    public boolean removeChild(TreeNode child) {
        for (int i = 0; i < children.size(); i++) {
            TreeNode node = children.get(i);
            if (node.equals(child)) {
                child.setParent(null);
                children.remove(child);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isExpanded() {
        return expanded;
    }

    @Override
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
        children.forEach(treeNode -> treeNode.setExpanded(expanded));
    }

    @Override
    public Object getData() {
        return this.data;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String getTreePath() {
        String elem = data == null ? "empty" : data.toString();
        if (parent == null) {
            return elem;
        }
        return parent.getTreePath() + "->" + elem;
    }

    @Override
    public TreeNode findParent(Object data) {
        if (data == null) {
            if (this.data == null) {
                return this;
            } else if (parent != null) {
                return parent.findParent(data);
            } else {
                return null;
            }
        }
        if (data.equals(this.data)) {
            return this;
        }

        if (parent != null) {
            return parent.findParent(data);
        }
        return null;
    }

    @Override
    public TreeNode findChild(Object data) {
        if (isLeaf()) {
            return null;
        }
        if (data == null) {
            for (TreeNode child :
                    children) {
                if (child.getData() == null) {
                    return child;
                }
                TreeNode find = child.findChild(null);
                if (find != null) {
                    return find;
                }
            }
            return null;
        }

        for (TreeNode child :
                children) {
            if (child.getData() == null) {
                continue;
            }
            if (child.getData().equals(data)) {
                return child;
            }
            TreeNode find = child.findChild(data);
            if (find != null) {
                return find;
            }
        }

        return null;
    }
}

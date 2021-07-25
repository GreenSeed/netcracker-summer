package ru.skillbench.tasks.basics.entity;

public class LocationImpl implements Location {
    private String name;
    private Type type;
    private Location parent;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public void setParent(Location parent) {
        this.parent = parent;
    }

    @Override
    public String getParentName() {
        if (this.parent == null) {
            return "--";
        }
        return this.parent.getName();
    }

    @Override
    public Location getTopLocation() {
        if (this.parent == null) {
            return this;
        }
        return this.parent.getTopLocation();
    }

    @Override
    public boolean isCorrect() {
        if (parent == null) {
            return true;
        }
        return this.parent.getType().compareTo(this.type) < 0 && this.parent.isCorrect();
    }

    @Override
    public String getAddress() {
        String elem = name.endsWith(".") || name.split(" ")[0].contains(".") ?
                name : type.getNameForAddress() + name;
        if (this.parent == null) {
            return elem;
        }
        return elem + ", " + this.parent.getAddress();
    }

    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
}

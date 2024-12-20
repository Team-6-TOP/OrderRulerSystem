package models;

import java.util.Objects;

public class CustomerModel {
    private Integer ID;
    private String name;
    private String type;

    public CustomerModel(Integer ID, String name, String type) {
        this.ID = ID;
        this.name = name;
        this.type = type;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerModel that = (CustomerModel) o;
        return Objects.equals(ID, that.ID) && Objects.equals(name, that.name) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, type);
    }

    @Override
    public String toString() {
        return "Customer: " + "ID - " + ID +
                ", " + name + ", " + type;
    }
}

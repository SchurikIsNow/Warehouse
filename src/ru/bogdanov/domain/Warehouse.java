package ru.bogdanov.domain;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    private List<Component> components = new ArrayList<>();

    public void addComponent(String name, Integer quantity) {
	if (components.isEmpty()) {
	    components.add(new Component(name, quantity));
	    return;
	}
	for (Component c : components) {
	    if (c.getName().equals(name)) {
		c.setQuantity(c.getQuantity() + quantity);
		return;
	    }
	}
	components.add(new Component(name, quantity));
    }

    public void updateComponent(String name, Integer quantity) {
	if (components.isEmpty()) {
	    components.add(new Component(name, quantity));
	    return;
	}
	for (Component c : components) {
	    if (c.getName().equals(name)) {
		c.setQuantity(quantity);
		return;
	    }
	}
	components.add(new Component(name, quantity));
    }

    public void delComponent(String name) {
	if (components.isEmpty()) {
	    System.out.println("Warehouse is empty");
	    return;
	}
	for (Component c : components) {
	    if (c.getName().equals(name)) {
		components.remove(c);
		return;
	    }
	}
	System.out.println("This component is already out");
    }

    public int getSizeWarehouse() {
	return components.size();
    }

    public Component getComponent(String name) {
	if (components.isEmpty()) {
	    System.out.println("Warehouse is empty");
	    return null;
	}
	for (Component c : components) {
	    if (c.getName().equals(name)) {
		return c;
	    }
	}
	System.out.println("This component is missing");
	return null;
    }

}

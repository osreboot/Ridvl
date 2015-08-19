package com.osreboot.ridhvl.menu;

import java.util.ArrayList;
import java.util.Stack;

import com.osreboot.ridhvl.action.HvlAction2;

public class HvlMenu {

	private static HvlMenu current;
	private static Stack<HvlMenu> popups;
	private static Stack<Boolean> blocks;
	
	private static HvlAction2<HvlMenu, HvlMenu> menuChanged;

	static {
		popups = new Stack<>();
		blocks = new Stack<>();
	}

	public static HvlMenu getCurrent() {
		return current;
	}

	public static void setCurrent(HvlMenu currentArg) {
		if(menuChanged != null) menuChanged.run(current, currentArg);
		current = currentArg;
		current.setTotalTime(0);
		if (current.getComponents().size() > 0)
			current.setFocused(current.getComponents().get(0));// TODO implement
																// boolean
																// joystick
																// using system
	}

	public static void updateMenus(float delta) {
		boolean blocked = false;

		// Go from the top down (highest popup to lowest).
		for (int i = popups.size() - 1; i >= 0; i--) {
			HvlMenu popup = popups.get(i);
			boolean block = blocks.get(i);

			// If nothing else has blocked updating, we can update this.
			if (!blocked) {
				popup.update(delta);
			}

			// If this blocks lower popups, then say that we are blocked.
			if (block && !blocked)
				blocked = true;
		}

		if (!blocked) {
			current.update(delta);
		}

		// Draw in reverse order: draw the main menu...
		current.draw(delta);

		// ... and then the popups from the bottom up.
		for (int i = 0; i < popups.size(); i++) {
			popups.get(i).draw(delta);
		}
	}

	private ArrayList<HvlComponent> components;
	
	private boolean interactable;
	
	private HvlComponent focused;
	private float totalTime;

	public HvlMenu() {
		components = new ArrayList<HvlComponent>();
		interactable = true;
	}

	public ArrayList<HvlComponent> getComponents() {
		return components;
	}

	public void add(HvlComponent control) {
		components.add(control);
	}

	@SuppressWarnings("unchecked")
	public <T> T getChild(int index) {
		if (index >= components.size())
			return null;
		
		return (T) components.get(index);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getFirstChildOfType(Class<? extends T> type) {
		for (HvlComponent comp : components)
		{
			if (comp.getClass().equals(type))
				return (T) comp;
		}
		
		return null;
	}
	
	public void update(float delta) {
		totalTime += delta;
		for (HvlComponent c : components)
			c.metaUpdate(delta);
	}

	public void draw(float delta) {
		for (HvlComponent c : components) {
			if (c.isVisible())
				c.metaDraw(delta);
		}
	}
	
	public HvlComponent getFocused() {
		return focused;
	}

	public void setFocused(HvlComponent focusedArg) {
		focused = focusedArg;
	}

	public float getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(float totalTimeArg) {
		totalTime = totalTimeArg;
	}

	public static void addPopup(HvlMenu popup, boolean blocking) {
		popups.push(popup);
		blocks.push(blocking);
	}

	public static void removePopup() {
		if (popups.isEmpty())
			return;

		popups.pop();
		blocks.pop();
	}

	public static void removePopup(HvlMenu remove, boolean allAbove) {
		if (popups.isEmpty())
			return;

		if (!popups.contains(remove))
			return;

		int index = popups.indexOf(remove);

		if (allAbove) {
			while (popups.peek() != remove) {
				popups.pop();
				blocks.pop();
			}
			popups.pop();
			blocks.pop();
		} else {
			popups.remove(index);
			blocks.remove(index);
		}
	}

	public static boolean hasPopup() {
		return !popups.isEmpty();
	}
	
	public static boolean isBlocked(HvlMenu menu) {		
		for (int i = popups.size() - 1; i >= 0; i--) {
			// If we've found the menu (and haven't been blocked already) return false
			// (before we check for blocks)
			if (popups.get(i) == menu)
				return false;
			
			// If this one is blocking (and isn't the target: see above) return true
			if (blocks.get(i))
				return true;
		}
		
		// Either this is the base menu (and therefore unblocked if we've gotten this far)
		// or isn't actually a current menu (so return false).
		return false;
	}

	public boolean isInteractable() {
		return interactable;
	}

	public void setInteractable(boolean interactableArg) {
		interactable = interactableArg;
	}

	public static HvlAction2<HvlMenu, HvlMenu> getMenuChanged() {
		return menuChanged;
	}

	public static void setMenuChanged(HvlAction2<HvlMenu, HvlMenu> menuChangedArg) {
		menuChanged = menuChangedArg;
	}
}

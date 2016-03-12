package com.mhatre.sagar.design.patterns.tutorial;

//
/* Ref : https://dzone.com/articles/design-patterns-abstract-factory
 * 
 * Provides an interface for creating families of related or dependent objects without specifying their concrete classes. 
 * */

//Our AbstractProduct

interface Window {
	public void setTitle(String text);
	public void repaint();
}

// ConcreteProductA1

class MSWindow implements Window {
	public void setTitle(String text) {
		// MS Windows specific behaviour
	}

	public void repaint() {
		// MS Windows specific behaviour
	}

}
// ConcreteProductA2

class MacOSXWindow implements Window {
	public void setTitle(String text) {
		// Mac OSX specific behaviour
	}

	public void repaint() {

		// Mac OSX specific behaviour

	}

}
// AbstractFactory

interface AbstractWidgetFactory {

	public Window createWindow();

}

public class C_AbstractFactory {

	public static void main(String[] args) {
		GUIBuilder builder = new GUIBuilder();

		AbstractWidgetFactory widgetFactory = null;

		// check what platform we're on
		String OS = "MACOSX";

		if (OS == "MACOSX") {
			widgetFactory = new MacOSXWidgetFactory();
		} else {
			widgetFactory = new MsWindowsWidgetFactory();
		}

		builder.buildWindow(widgetFactory);
	}

}

class GUIBuilder {

	public void buildWindow(AbstractWidgetFactory widgetFactory) {

		Window window = widgetFactory.createWindow();

		window.setTitle("New Window");

	}

}

class MacOSXWidgetFactory implements AbstractWidgetFactory {

	// create a MacOSXWindow

	public Window createWindow() {

		MacOSXWindow window = new MacOSXWindow();

		return window;

	}

}

class MsWindowsWidgetFactory implements AbstractWidgetFactory {

	// create an MSWindow

	public Window createWindow() {

		MSWindow window = new MSWindow();

		return window;

	}

}

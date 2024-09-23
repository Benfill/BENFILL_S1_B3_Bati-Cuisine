package app;

import controller.AppController;
import util.TableCreation;

public class Main {

	public static void main(String[] args) {
		startApp();
	}

	public static void startApp() {
		new TableCreation();
		new AppController();
	}
}

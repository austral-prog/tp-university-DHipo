package com.university.cli.menu;

import com.university.cli.menu.Menu;
import java.util.Map;
import java.util.HashMap;
public class StudentMenu extends Menu {
	public Map<String, Runnable> options = new HashMap<>() {{
    put("option1", () -> System.out.println("in sub menu"));
  }};
	@Override
	public void print() {
    System.out.println("student menu");
    while(scene == null){
      for (String key : options.keySet().stream().toList())
        System.out.println("\t-> " + key);
      scene = cHandler.handleInput(options);
      if (scene != null) scene.run();
    }
	}
}

package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args) {
    String[] langs = {"Java", "C#", "Python", "PHP"};
    for (String l : langs) {
      System.out.println("Я хочу выучить " + l);
    }

    List languages = Arrays.asList("Java", "C#", "Python", "PHP");
    for (int i = 0; i < languages.size(); i++) {
      System.out.println("Я хочу выучить " + languages.get(i));
    }


  }
}

package project.ui;

public class UIFactory {
  private static UI _UI = new PopupUI();

  public static UI ui() {
    return _UI;
  }
}
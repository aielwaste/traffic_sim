package state.ui.main;

import state.ui.UI;
import state.ui.UIMenu;
import state.ui.UIMenuAction;
import state.ui.UIMenuBuilder;
import state.ui.UIForm;
import state.ui.UIFormTest;
import state.ui.UIFormBuilder;

class Control {
  final State EXITED; 
  final State EXIT; 
  private State _state;
  private UI _ui;
  Control(UI ui) {
    EXITED = new ExitedState();
    EXIT = new ExitState(this,ui);
    _ui = ui;
    _state = EXIT;
  }
  
  void run() {
    while (_state != EXITED) {
      _state = _state.run();
    } 
  }
}

interface State {
  public State run();
}

final class ExitedState implements State {
  public State run() {
    return this;
  }
}

final class ExitState implements State {
  Control _control;
  UI _ui;
  UIMenu _m;
  ExitState(Control control, UI ui) {
    _control = control;
    _ui = ui;

    UIMenuBuilder m;
    m = new UIMenuBuilder();
    
    m.add("Default", new UIMenuAction() { public Object run() {return this;} });
    m.add("Yes",
      new UIMenuAction() {
        public Object run() {
          return _control.EXITED;
        }
      });
    m.add("No",
      new UIMenuAction() {
        public Object run() {
          return _control.EXIT;
        }
      });
    _m = m.toUIMenu("Are you sure you want to exit?");
  }
  public State run() {
    return (State) _ui.processMenu(_m);
  }
}
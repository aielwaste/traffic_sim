package project.model;

import java.util.ArrayList;

public abstract interface Grid { }

class Alternating implements Grid {
  Alternating(Model model, ArrayList<Agent> _agents, Intersection[][] intersection, AnimatorBuilder builder) {
    int rows = MP.getRows();
    int columns = MP.getColumns();

    boolean westToEast = false;
    boolean isStreet = true;
    for (int i = 0; i < rows; i++) {
      CarHandlerList street = new CarHandlerList(isStreet, model, westToEast);
      Source source = new Source(i, -1, westToEast);
      street.insert(source);
      _agents.add(source);
      for (int j = 0; j < columns; j++) {
        Road s = new Road(i, j, westToEast);
        street.insert(s);
        
        if (!westToEast) {
          builder.addHorizontalRoad(s, s.getXPos(), s.getYPos(), westToEast);
          street.insert(intersection[i][j].getEWLight());
        }
        else {
          builder.addHorizontalRoad(s, s.getXPos(), columns - s.getYPos(), westToEast);
          street.insert(intersection[i][(columns - 1 - j)].getEWLight());
        }
      }
      
      Road s = new Road(i, columns, westToEast);
      
      street.insert(s);
      
      if (!westToEast) {
        builder.addHorizontalRoad(s, s.getXPos(), s.getYPos(), westToEast);
      } else {
        builder.addHorizontalRoad(s, s.getXPos(), columns - s.getYPos(), westToEast);
      }
      street.insert(new Sink(i, columns, westToEast));
      westToEast = !westToEast;
    }

    boolean southToNorth = false;
    isStreet = false;
    for (int j = 0; j < columns; j++) {
      CarHandlerList avenue = new CarHandlerList(isStreet, model, southToNorth);
      Source source = new Source(-1, j, southToNorth);
      avenue.insert(source);
      _agents.add(source);
      for (int i = 0; i < rows; i++) {
        Road a = new Road(i, j, southToNorth);
        avenue.insert(a);
        if (!southToNorth) {
          builder.addVerticalRoad(a, a.getXPos(), a.getYPos(), southToNorth);
          avenue.insert(intersection[i][j].getNSLight());
        }
        else {
          builder.addVerticalRoad(a, rows - a.getXPos(), a.getYPos(), southToNorth);
          avenue.insert(intersection[(rows - 1 - i)][j].getNSLight());
        }
      }

      Road l = new Road(rows, j, southToNorth);
      avenue.insert(l);
      if (!southToNorth) {
        builder.addVerticalRoad(l, l.getXPos(), l.getYPos(), southToNorth);
      }
      else {
        builder.addVerticalRoad(l, rows - l.getXPos(), l.getYPos(), southToNorth);
      }
      avenue.insert(new Sink(rows, j, southToNorth));
      southToNorth = !southToNorth;
    }
  }
}

class Simple implements Grid {
    Simple(Model model, ArrayList<Agent> _agents, Intersection[][] intersection, AnimatorBuilder builder) {
      int rows = MP.getRows();
      int columns = MP.getColumns();

      boolean westToEast = false;
      boolean isStreet = true;
      for (int i = 0; i < rows; i++) {
        CarHandlerList street = new CarHandlerList(isStreet, model, westToEast);
        Source source = new Source(i, -1, westToEast);
        street.insert(source);
        _agents.add(source);
        for (int j = 0; j < columns; j++) {
          Road s = new Road(i, j, westToEast);
          street.insert(s);
          if (!westToEast)
          {
            builder.addHorizontalRoad(s, s.getXPos(), s.getYPos(), westToEast);
            street.insert(intersection[i][j].getEWLight());
          }
          else
          {
            builder.addHorizontalRoad(s, s.getXPos(), columns - s.getYPos(), westToEast);
            street.insert(intersection[i][(columns - 1 - j)].getEWLight());
          }
        }
        Road s = new Road(i, columns, westToEast);
        street.insert(s);
        if (!westToEast)
        {
          builder.addHorizontalRoad(s, s.getXPos(), s.getYPos(), westToEast);
        }
        else
        {
          builder.addHorizontalRoad(s, s.getXPos(), columns - s.getYPos(), westToEast);
        }
        street.insert(new Sink(i, columns, westToEast));
      }

      boolean southToNorth = false;
      isStreet = false;
      for (int j = 0; j < columns; j++) {
        CarHandlerList avenue = new CarHandlerList(isStreet, model, southToNorth);
        Source source = new Source(-1, j, southToNorth);
        avenue.insert(source);
        _agents.add(source);
        for (int i = 0; i < rows; i++) {
          Road a = new Road(i, j, southToNorth);
          avenue.insert(a);
          if (!southToNorth)
          {
            builder.addVerticalRoad(a, a.getXPos(), a.getYPos(), southToNorth);
            avenue.insert(intersection[i][j].getNSLight());
          }
          else
          {
            builder.addVerticalRoad(a, rows - a.getXPos(), a.getYPos(), southToNorth);
            avenue.insert(intersection[(rows - 1 - i)][j].getNSLight());
          }
        }

        Road l = new Road(rows, j, southToNorth);
        avenue.insert(l);
        if (!southToNorth) {
          builder.addVerticalRoad(l, l.getXPos(), l.getYPos(), southToNorth);
        }
        else
        {
          builder.addVerticalRoad(l, rows - l.getXPos(), l.getYPos(), southToNorth);
        }
        avenue.insert(new Sink(rows, j, southToNorth));
      }
    }
  }
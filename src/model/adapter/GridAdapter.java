package model.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import model.Card;
import model.CardCell;
import model.Cell;
import model.HoleCell;
import provider.model.Grid;
import provider.model.GridCellReadOnly;

public class GridAdapter implements Grid, List<List<Cell<Card>>> {
  private final List<List<Cell<Card>>> grid;

  public GridAdapter(List<List<Cell<Card>>> grid) {
    this.grid = Objects.requireNonNull(grid);
  }

  public GridAdapter(Grid grid) {
    this.grid = gridToList(grid);
  }

  private static List<List<Cell<Card>>> gridToList(Grid grid) {
    List<List<Cell<Card>>> retGrid = new ArrayList<>();
    GridCellReadOnly[][] oldGrid = grid.readOnlyArray2D();
    List<Cell<Card>> tempRow = new ArrayList<>();

    for (GridCellReadOnly[] row : oldGrid) {
      for (GridCellReadOnly cell : row) {
        if (cell.canHaveCard()) {
          tempRow.add(new CardCell());
        } else {
          tempRow.add(new HoleCell());
        }
      }
      retGrid.add(new ArrayList<>(tempRow));
      tempRow.clear();
    }
    return retGrid;
  }

  @Override
  public boolean isFull() {
    for (List<Cell<Card>> row : grid) {
      for (Cell<Card> cell : row) {
        if (!cell.hasCard()) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public int getNumHoles() {
    int numHoles = 0;
    for (List<Cell<Card>> row : grid) {
      for (Cell<Card> cell : row) {
        try {
          cell.getCard();
        } catch (IllegalStateException e) {
          numHoles++;
        }
      }
    }
    return numHoles;
  }

  @Override
  public GridCellReadOnly[][] readOnlyArray2D() {
    GridCellReadOnly[][] array2D =
            new GridCellReadOnly[grid.size()][grid.get(0).size()];
    for (int i = 0; i < grid.size(); i++) {
      for (int j = 0; j < grid.size(); j++) {
        array2D[i][j] = new GridCellReadOnlyAdapter(grid.get(i).get(j));
      }
    }
    return array2D;
  }

  @Override
  public int numRows() {
    return grid.size();
  }

  @Override
  public int numCols() {
    return grid.get(0).size();
  }

  @Override
  public int size() {
    return grid.size();
  }

  @Override
  public boolean isEmpty() {
    return grid.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return grid.contains(o);
  }

  @Override
  public Iterator<List<Cell<Card>>> iterator() {
    return grid.iterator();
  }

  @Override
  public Object[] toArray() {
    return grid.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return grid.toArray(a);
  }

  @Override
  public boolean add(List<Cell<Card>> cells) {
    return grid.add(cells);
  }

  @Override
  public boolean remove(Object o) {
    return grid.remove(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return grid.containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends List<Cell<Card>>> c) {
    return grid.addAll(c);
  }

  @Override
  public boolean addAll(int index, Collection<? extends List<Cell<Card>>> c) {
    return grid.addAll(index, c);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return grid.removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return grid.retainAll(c);
  }

  @Override
  public void clear() {
    grid.clear();
  }

  @Override
  public List<Cell<Card>> get(int index) {
    return grid.get(index);
  }

  @Override
  public List<Cell<Card>> set(int index, List<Cell<Card>> element) {
    return grid.set(index, element);
  }

  @Override
  public void add(int index, List<Cell<Card>> element) {
    grid.add(index, element);
  }

  @Override
  public List<Cell<Card>> remove(int index) {
    return grid.remove(index);
  }

  @Override
  public int indexOf(Object o) {
    return grid.indexOf(o);
  }

  @Override
  public int lastIndexOf(Object o) {
    return grid.lastIndexOf(o);
  }

  @Override
  public ListIterator<List<Cell<Card>>> listIterator() {
    return grid.listIterator();
  }

  @Override
  public ListIterator<List<Cell<Card>>> listIterator(int index) {
    return grid.listIterator(index);
  }

  @Override
  public List<List<Cell<Card>>> subList(int fromIndex, int toIndex) {
    return grid.subList(fromIndex, toIndex);
  }
}

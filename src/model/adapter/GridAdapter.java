package model.adapter;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import model.Cell;
import provider.model.Grid;
import provider.model.GridCellReadOnly;

public class GridAdapter implements Grid, List<List<Cell>> {
  private final List<List<Cell>> grid;

  public GridAdapter(List<List<Cell>> grid) {
    this.grid = Objects.requireNonNull(grid);
  }

  @Override
  public boolean isFull() {
    for (List<Cell> row : grid) {
      for (Cell cell : row) {
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
    for (List<Cell> row : grid) {
      for (Cell cell : row) {
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
    return grid.size();
  }

  @Override
  public int size() {
    return grid.size();
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public boolean contains(Object o) {
    return false;
  }

  @Override
  public Iterator<List<Cell>> iterator() {
    return null;
  }

  @Override
  public Object[] toArray() {
    return new Object[0];
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return grid.toArray(a);
  }

  @Override
  public boolean add(List<Cell> cells) {
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
  public boolean addAll(Collection<? extends List<Cell>> c) {
    return grid.addAll(c);
  }

  @Override
  public boolean addAll(int index, Collection<? extends List<Cell>> c) {
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
  public List<Cell> get(int index) {
    return grid.get(index);
  }

  @Override
  public List<Cell> set(int index, List<Cell> element) {
    return grid.set(index, element);
  }

  @Override
  public void add(int index, List<Cell> element) {
    grid.add(index, element);
  }

  @Override
  public List<Cell> remove(int index) {
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
  public ListIterator<List<Cell>> listIterator() {
    return grid.listIterator();
  }

  @Override
  public ListIterator<List<Cell>> listIterator(int index) {
    return grid.listIterator(index);
  }

  @Override
  public List<List<Cell>> subList(int fromIndex, int toIndex) {
    return grid.subList(fromIndex, toIndex);
  }
}

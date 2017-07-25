package tk.ladyka.andrei.utils;

public interface ComparableHelper<T> extends Comparable<T> {

  public static final int BEFORE = -1;
  public static final int EQUAL = 0;
  public static final int AFTER = 1;
}

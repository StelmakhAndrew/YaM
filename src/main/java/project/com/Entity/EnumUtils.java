package project.com.Entity;

public class EnumUtils {
  public static Integer getDatabaseId(Enum item) {
    if (item == null) {
      return 0;
    } else {
      return (item.ordinal() + 1);
    }
  }
}

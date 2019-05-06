package project.com.Entity;

/**
 * class EnumUtils generate number of enums
 *
 */
public class EnumUtils {
  public static Integer getDatabaseId(Enum item) {
    if (item == null) {
      return 0;
    } else {
      return (item.ordinal() + 1);
    }
  }
}

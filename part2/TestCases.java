import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

import jdk.jfr.Timestamp;

public class TestCases
{
   public static final double DELTA = 0.00001;

   /*
    * This test is just to get you started.
    */

   @Test
   public void myTests(){
      Point test = new Point(-3.0, 1.0);
      assertEquals(test.getX(), new Point(1.0, 3.0).rotate90().getX(), DELTA);
      assertEquals(test.getY(), new Point(1.0, 3.0).rotate90().getY(), DELTA);
      assertEquals(5.0, new Point(3.0, 4.0).getRadius(), DELTA);
      assertEquals(5.0, new Point(3.0, 4.0).getRadius(), DELTA);
      assertEquals(13.0, new Point(5.0, 12.0).getRadius(), DELTA);
      assertEquals(3.14159265359 / 2, new Point(0.0, 1.0).getAngle(), DELTA);
      assertEquals(- 3.14159265359 / 2, new Point(0.0, -1.0).getAngle(), DELTA);
      assertEquals(-1, new Point(1.0, 3.0).rotate90().rotate90().getX(), DELTA);
      assertEquals(-3, new Point(1.0, 3.0).rotate90().rotate90().getY(), DELTA);
      assertEquals(3, new Point(1.0, 3.0).rotate90().rotate90().rotate90().getX(), DELTA);
      assertEquals(-1, new Point(1.0, 3.0).rotate90().rotate90().rotate90().getY(), DELTA);
      assertEquals(1, new Point(1.0, 3.0).rotate90().rotate90().rotate90().rotate90().getX(), DELTA);
      assertEquals(3, new Point(1.0, 3.0).rotate90().rotate90().rotate90().rotate90().getY(), DELTA);
   }

   @Test
   public void testGetX()
   {
      assertEquals(1.0, new Point(1.0, 2.0).getX(), DELTA);
   }

   /*
    * The tests below here are to verify the basic requirements regarding
    * the "design" of your class.  These are to remain unchanged.
    */

   @Test
   public void testImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getX",
         "getY",
         "getRadius",
         "getAngle",
         "rotate90"
         );

      final List<Class> expectedMethodReturns = Arrays.asList(
         double.class,
         double.class,
         double.class,
         double.class,
         Point.class
         );

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0],
         new Class[0],
         new Class[0],
         new Class[0],
         new Class[0]
         );

      verifyImplSpecifics(Point.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   private static void verifyImplSpecifics(
      final Class<?> clazz,
      final List<String> expectedMethodNames,
      final List<Class> expectedMethodReturns,
      final List<Class[]> expectedMethodParameters)
      throws NoSuchMethodException
   {
      assertEquals("Unexpected number of public fields",
         0, Point.class.getFields().length);

      final List<Method> publicMethods = Arrays.stream(
         clazz.getDeclaredMethods())
            .filter(m -> Modifier.isPublic(m.getModifiers()))
            .collect(Collectors.toList());

      assertTrue("Unexpected number of public methods",
         expectedMethodNames.size()+1 >= publicMethods.size());

      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodReturns.size());
      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodParameters.size());

      for (int i = 0; i < expectedMethodNames.size(); i++)
      {
         Method method = clazz.getDeclaredMethod(expectedMethodNames.get(i),
            expectedMethodParameters.get(i));
         assertEquals(expectedMethodReturns.get(i), method.getReturnType());
      }
   }
}
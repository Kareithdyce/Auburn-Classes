public class Kata
{
  public static void main(String[] args) {
    System.out.println(NumbersWithDigitInside(20, 0)[0]);
    System.out.println(NumbersWithDigitInside(20, 0)[1]);
    System.out.print(NumbersWithDigitInside(20, 0)[2]);
  }
  public static long[] NumbersWithDigitInside(long x, long d)
  {
  long [] solve = new long[3];
  long add = 0;
  long multi = 0;
  long count = 0;
  for (long i = 1; i <= x; i++) {
    if (i == d) {
    count++;
    add = i;
    multi = i;
    continue;
    }
    long temp = i;
      while (temp > 9){
        if (temp % 10 == d){
          count++;
          add += i;
          multi = (multi == 0)? i :  multi * i;
          temp = 0;
        }
        else {
          temp /= 10;
          if (temp == d){
          count++;
          add += i;
          multi = (multi == 0)? i :  multi * i;
          temp = 0;
        }
      }

    }

  }
  solve[0] = count;
  solve[1] = add;
  solve[2] = multi;
    return solve;
  }
}

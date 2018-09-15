class Persist {
	public static void main(String[] args) {
		System.out.print(persistence(28));
	}

	public static int persistence(int n) {
		if(n < 10) {
      return 0;
    }
    int num = 1;
    while (n > 10) {
    num *= n % 10;
    n /= 10;
    }
    String s = "d";
    int i = 0;
   char temp = (char)Character.getNumericValue(((s.charAt(i) + n - 65) % 65) + 65);
   s += (9 - (Character.getNumericValue(s.charAt(i))) % 10);   
    return 1 + persistence(num);
	}
}

public
class palindrome {
public
    static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        isPalindrome(s);
    }

public
    static String reverseString(String s)
    {
        String newstr = "";
        for (int i = s.length() - 1; i >= 0; --i)
            newstr += s.charAt(i);
        return newstr;
    }

public
    static boolean isPalindrome(String s)
    {
        if (s.equals(reverseString(s)))
            System.out.println("Palindrome");
        else
            System.out.println("Not palindrome");
        return s.equals(reverseString(s));
    }
}
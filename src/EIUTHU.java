import java.io.*;
import java.util.*;

class EIUTHU {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        String firstString = sc.next();
        String secondString = sc.next();

        int countOverlap = 0;
        for (int i = 1; i <= firstString.length() & i <= secondString.length(); i++) {
            String tempString = secondString.substring(0, i);
            if (firstString.endsWith(tempString)) {
                countOverlap = tempString.length();
            }
        }
        System.out.println(firstString.length() + secondString.length() - countOverlap);
    }

}

package part1.task2;

public class Sort {
    // Sort the array of string according to lengths.
    public static void sort(String []s, int n)
    {
        for (int i=1 ;i<n; i++)
        {
            String temp = s[i];

            // Insert s[j] at its correct position
            int j = i - 1;
            while (j >= 0 && temp.length() < s[j].length())
            {
                s[j+1] = s[j];
                j--;
            }
            s[j+1] = temp;
        }
    }

    // Print the sorted array of string
    static void printArraystring(String str[], int n)
    {
        for (int i=0; i<n; i++)
            System.out.print(str[i]+" ");
    }

    public static void main(String args[])
    {
        String []arr = {"China", "I", "from", "am"};
        int n = arr.length;

        // Perform sort
        sort(arr,n);

        // Print result
        printArraystring(arr, n);
    }
}
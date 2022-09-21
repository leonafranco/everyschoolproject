package com.company;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LeituraDados {
    // Disable default constructor
    private LeituraDados()  {}

    /**
     * Reads an integer from the standard input.
     * The input is terminated by a return. If the input isn't a valid number,
     * "!!! Not an integer!!!" is displayed and the user can try to write the number again.
     *
     * @return the number read
     */
    public static int leituraInt()
    {
        while(true)
        {
            try
            {
                return Integer.valueOf(leituraString().trim()).intValue();
            }
            catch(Exception e)
            {
                System.out.println("!!! Not an integer !!!");
            }
        }
    }

    /**
     * Reads a double from the standard input.
     * The input is terminated by a return. If the input isn't a valid number,
     * "!!! Not a double!!!" is displayed and the user can try to write the number again.
     *
     * @return the number read
     */
    public static double leituraDouble()
    {
        while(true)
        {
            try
            {
                return Double.valueOf(leituraString().trim()).doubleValue();
            }
            catch(Exception e)
            {
                System.out.println("!!! Not a double !!!");
            }
        }
    }
    /**
     * Reads a long from the standard input.
     * The input is terminated by a return. If the input isn't a valid number,
     * "!!! Not a long !!!" is displayed and the user can try to write the number again.
     *
     * @return the number read
     */
    public static long leituraLong()
    {
        while(true)
            try
            {
                return Long.valueOf(leituraString().trim()).longValue() ;
            }
            catch(Exception e)
            {
                System.out.println("!!! Not a long !!!");
            }
    }

    /**
     * Reads a float from the standard input.
     * The input is terminated by a return. If the input isn't a valid number,
     * "!!! Not a float!!!" is displayed and the user can try to write the number again.
     *
     * @return the number read
     */
    public static float leituraFloat()
    {
        while(true)
        {
            try
            {
                return Float.valueOf(leituraString().trim()).floatValue();
            }
            catch(Exception e)
            {
                System.out.println("!!! Not a float !!!");
            }
        }
    }


    /**
     * Reads a string from the standard input.
     * The input is terminated by a return.
     * @return the string read, without the final '\n\r'
     */
    public static String leituraString()
    {
        String s = "";

        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in), 1);
            s = in.readLine();
        }
        catch (IOException e)
        {
            System.out.println("Error reading from the input stream.");
        }

        return s;
    }

    /**
     * Reads a string from the standard input.
     * The input is terminated by a return.
     * @return the string read, without the final '\n\r'
     */
    public static char leituraChar()
    {
        char s = 0;

        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in), 1);
            s = (char)in.read();
        }
        catch (IOException e)
        {
            System.out.println("Error reading char from input stream");
        }

        return s;
    }
}
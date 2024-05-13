/*
* This program sorts an array using the merging algorithm
*
* @author  Ava Venturino
* @version 1.0
* @since   2021-05-11
*/

import java.util.Arrays;
import java.util.Scanner;

/**
* This is the program.
*/
final class MergeSort {
    /**
    * Prevent instantiation.
    * Throw an exception IllegalStateException.
    * if this is ever called
    *
    * @throws IllegalStateException if this is ever called
    *
    */
    private MergeSort() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
     * Formats an array of integers.
     *
     * @param array to be formatted
     * @return the formatted array as a string
     */
    static String formatArray(int[] array) {
        String formattedArray = "";

        for (int counter = 0; counter < array.length; counter++) {
            if (counter == array.length - 1) {
                formattedArray = formattedArray + array[counter];
            } else {
                formattedArray = formattedArray + array[counter] + ",";
            }
        }

        return formattedArray;
    }

    /**
     * Removes the first element of an array.
     *
     * @param array the array to have the first element removed
     * @return the new array
     */
    static int[] removeFirst(int[] array) {
        final int[] newArray = new int[array.length - 1];
        for (int counter = 1; counter < array.length; counter++) {
            newArray[counter - 1] = array[counter];
        }

        return newArray;
    }

    /**
     * Pushes an element to an array.
     *
     * @param array the array to have the new element
     * @param newElement the new element of the array
     * @return the new array
     */
    static int[] push(int[] array, int newElement) {
        final int[] newArray = new int[array.length + 1];

        for (int counter = 0; counter < array.length; counter++) {
            newArray[counter] = array[counter];
        }

        newArray[array.length] = newElement;

        return newArray;
    }

    /**
     * Merges two arrays together.
     *
     * @param left the array to the left
     * @param right the array to the right
     * @return the merged array
     */
    static int[] merge(int[] left, int[] right) {
        int[] sortArray = {};
        int[] otherLeft = left;
        int[] otherRight = right;

        while (otherLeft.length > 0 && otherRight.length > 0) {
            if (otherLeft[0] < otherRight[0]) {
                sortArray = push(sortArray, otherLeft[0]);
                otherLeft = removeFirst(otherLeft);
            } else {
                sortArray = push(sortArray, otherRight[0]);
                otherRight = removeFirst(otherRight);
            }
        }

        for (int counter = 0; counter < otherLeft.length; counter++) {
            sortArray = push(sortArray, otherLeft[counter]);
        }
        for (int counter = 0; counter < otherRight.length; counter++) {
            sortArray = push(sortArray, otherRight[counter]);
        }

        return sortArray;
    }

    /**
     * Sorts an array using the merging sorting algorithm.
     *
     * @param sortArray the array to be sorted
     * @return the sorted array
     */
    static int[] mergeSort(int[] sortArray) {
        final int mid = sortArray.length / 2;
        final int[] sortedArray;

        if (sortArray.length < 2) {
            sortedArray = sortArray;
        } else {
            final int[] left = Arrays.copyOfRange(sortArray, 0, mid);
            final int[] right = Arrays.copyOfRange(
                sortArray, mid, sortArray.length
            );
            sortedArray = merge(mergeSort(left), mergeSort(right));
        }

        return sortedArray;
    }

    /**
    * The starting main() function.
    *
    * @param args No args will be used
    */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arrayLength = 5;
        int[] array = new int[arrayLength];

        for (int counter = 0; counter < arrayLength; ) {
            System.out.print("Enter a number to put at [" + counter + "]: ");
            if (scanner.hasNextInt()) {
                array[counter++] = scanner.nextInt();
            } else {
                System.out.println("Invalid input.");
                scanner.next(); // Clear the input buffer
            }
        }

        scanner.close();

        // Show unsorted array
        System.out.println("\nUnsorted array: " + formatArray(array) + "\n");

        // Sort array
        int[] sortedArray = mergeSort(array);
        System.out.println("Sorted array: " + formatArray(sortedArray));

        System.out.println("\nDone.");
    }
}

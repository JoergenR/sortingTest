package main;

import java.util.Random;
import java.util.Scanner;
import jsjf.CircularArrayQueue;

public class sortingTest {

    public static int length;

    public static int random() {
        Random r = new Random();
        int randomNum = r.nextInt();
        return randomNum;
    }

    public static int[] fillArray(int length) {
        int array[] = new int[length];
        for(int i=0; i<array.length; i++)
        {
            array[i] = random();
        }
        return array;
    }

    public static void insertionSort(int A[]) {

        //Innstikksortering av array med heltall

        int n = A.length;
        int key;

        int randomArray[] = fillArray(length);

        for (int i = 1; i < n; i++) {
            // A er sortert t.o.m. indeks i-1
            key = A[i];
            int j  = i;
            // Setter element nummer i på riktig plass
            // blant de i-1 første elementene
            while( j > 0 && A[j-1] > key)
            {
                A[j] = A[j-1];
                j--;
            }
            A[j] = key;
        }

    }

    public static void quickSort(int A[], int min, int max) {

        // Quicksort av array med heltall

        int indexofpartition;

        if(max - min > 0)
        {
            // Partisjonerer array
            indexofpartition = findPartition(A, min, max);

            // Sorterer venstre del
            quickSort(A, min, indexofpartition - 1);

            // Sorterer høyre del
            quickSort(A, indexofpartition + 1, max);
        }
    }

    private static int findPartition (int[] A, int min, int max) {

        int left, right;
        int temp, partitionelement;

        // Bruker *første* element til å dele opp
        partitionelement = A[min];

        left = min;
        right = max;

        // Gjør selve partisjoneringen
        while (left < right)
        {
            // Finn et element som er større enn part.elementet
            while (A[left] <= partitionelement && left < right)
                left++;

            // Finn et element som er mindre enn part.element
            while (A[right] > partitionelement)
                right--;

            // Bytt om de to hvis ikke ferdig
            if (left < right)
            {
                temp = A[left];
                A[left] = A[right];
                A[right] = temp;
            }
        }

        // Sett part.elementet mellom partisjoneringene
        temp = A[min];
        A[min] = A[right];
        A[right] = temp;

        // Returnerer indeksen til part.elementet
        return right;
    }

    public static void mergeSort(int[] A, int min, int max) {

        // Flettesortering av array med heltall

        if (min==max)
            return;

        int[] temp;
        int index1, left, right;
        int size = max - min +1;
        int mid = (min + max) /2;

        temp = new int[size];

        // Flettesorterer de to halvdelene av arrayen
        mergeSort(A, min, mid);
        mergeSort(A, mid + 1, max);

        // Kopierer array over i temp.array
        for( index1 = 0; index1 < size; index1++)
            temp[index1] = A[min + index1];

        // Fletter sammen de to sorterte halvdelene over i A
        left = 0;
        right = mid - min + 1;
        for(index1 = 0; index1 < size; index1++)
        {
            if (right <= max - min)
                if (left <= mid - min)
                    if (temp[left] > temp[right])
                        A[index1 + min] = temp[right++];
                    else
                        A[index1 + min] = temp[left++];
                else
                    A[index1 + min] = temp[right++];
            else
                A[index1 + min] = temp[left++];
        }
    }

    public static void radixSort(int[] A, int maksAntSiffer) {

        // Radixsortering av en array a med desimale heltall
        // maksAntSiffer: Maksiamlt antall siffer i tallene

        int ti_i_m = 1; // Lagerer 10^m
        int n = A.length;

        // Oppretter 10 tomme køer
        CircularArrayQueue<Integer>[] Q =
                (CircularArrayQueue<Integer>[])(new CircularArrayQueue[10]);

        for (int i = 0; i < 10; i++)
            Q[i] = new CircularArrayQueue<Integer>();

        // Sorterer på et og et siffer, fra venstre mot høyre

        for (int m = 0; m < maksAntSiffer; m++)
        {
            // Fordeler tallene i 10 køer
            for (int i = 0; i < n; i++)
            {
                int siffer = (A[i] / ti_i_m) % 10;
                Q[siffer].enqueue(new Integer(A[i]));
            }

            // Tømmer køene og legger tallene fortløpende tilbake i A
            int j = 0;
            for ( int i = 0; i < 10; i++)
                while(!Q[i].isEmpty())
                    A[j++] = (int) Q[i].dequeue();

            // Beregner 10^m for neste iterasjon
            ti_i_m *= 10;
        }
    }

    public static void main(String[] args) {

        // Henter input fra bruker
        Scanner in = new Scanner(System.in);
        System.out.println("Skriv inn antall tall som skal sorteres: ");
        length = in.nextInt();
        int A[] = new int[length];

        System.out.println("Hvilken sorteringsmetode ønskes? ");
        System.out.println("1. Insertion sort");
        System.out.println("2. Quicksort");
        System.out.println("3. Merge sort");
        System.out.println("4. Radixsort");
        int choosenMethod = in.nextInt();

        if(choosenMethod == 1)
        {
            insertionSort(A);
        }
        else if(choosenMethod == 2)
        {
            quickSort(A,);
        }
        else if(choosenMethod == 3)
        {
            mergeSort();
        }
        else if(choosenMethod == 4)
        {
            radixSort();
        }
        else
        {
            System.out.println("Vennligst spesifiser metode 1, 2, 3 eller 4");
        }

    }

}

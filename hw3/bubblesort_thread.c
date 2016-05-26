#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define N 100000

struct arg_struct {
    int no;
    int* arr;
    int start;
    int n;
};

void swap(int *xp, int *yp) {
    int temp = *xp;
    *xp = *yp;
    *yp = temp;
}

void bubbleSort(int arr[], int n) {
    int i, j;
    for(i=0;i<n-1;i++)
        for(j=0;j<n-i-1;j++)
            if (arr[j] > arr[j+1])
                swap(&arr[j], &arr[j+1]);
}

void *bubbleSortThread(void *arguments) {
    struct arg_struct *args = arguments;
    int start = args->start;
    int n = args->n;
    int* arr = args->arr;

    int i=start, j=start;
    for(i=start;i<n-1;i++) {
        for (j = start; j < n - i +start - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                swap(&arr[j], &arr[j + 1]);
            }
        }
    }

    pthread_exit(0);
}

int* merge(int arr1[], int arr2[]) {
    int* merged = (int *)malloc(sizeof(int)*N);
    int i=0, j=0;
    for(int count=0; count < N; count++) {
        if(i < N/2 && ( j >= N/2 || arr1[i] <= arr2[j] )) {
            merged[count] = arr1[i];
            i++;
        }
        else if(j < N/2 && ( i >= N/2 || arr2[j] <= arr1[i] ) ) {
            merged[count] = arr2[j];
            j++;
        }
    }
    return merged;
}


void printArray(int arr[], int size) {
    int i;
    for (i=0; i < size; i++)
        printf("%d ", arr[i]);

    printf("\n");
}

int main() {
    pthread_t thread_arr[2];
    struct arg_struct args1;
    struct arg_struct args2;
    int i, n;
    int* A;
    clock_t start, end;
    double elapsed_time;
    A = (int *)malloc(sizeof(int)*N);
    if(A==NULL){
        printf("Fail to malloc\n");
        exit(0);
    }
    for (i=N-1; i>=0; i--)
        A[N-1-i] = i;
    // print the last ten elements
    printArray(&A[N-10], 10);
    start = clock();

    args1.arr = A;
    args1.start = 0;
    args1.n = N/2;
    args1.no = 1;
    args2.arr = A;
    args2.start = N/2;
    args2.n = N;
    args2.no = 2;

    pthread_create(&thread_arr[0], NULL, &bubbleSortThread, (void *)&args1);
    pthread_create(&thread_arr[1], NULL, &bubbleSortThread, (void *)&args2);
    pthread_join(thread_arr[0], NULL);
    pthread_join(thread_arr[1], NULL);

    int* arr1 = (int *)malloc(sizeof(int)*N/2);
    int* arr2 = (int *)malloc(sizeof(int)*N/2);

    memcpy(arr1, A, N/2*sizeof(int));
    memcpy(arr2, A+N/2, N/2*sizeof(int));

    int* merged = merge(arr1,arr2);


    //bubbleSort(A, N);
    end = clock();
    // print the last ten elements
    printArray(&merge(arr1,arr2)[N-10], 10);
    elapsed_time = (end-start)/(double)CLOCKS_PER_SEC;
    printf("elapsed time = %lf\n", elapsed_time);
    return 0;
}
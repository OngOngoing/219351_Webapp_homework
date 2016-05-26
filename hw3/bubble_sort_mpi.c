#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define N 100000

void swap(int *xp, int *yp) {
  int temp = *xp;
  *xp = *yp;
  *yp = temp;
}

// A function to implement bubble sort
void bubbleSort(int arr[], int n) {
  int i, j;
  for (i = 0; i < n-1; i++)      
    for (j = 0; j < n-i-1; j++)
      if (arr[j] > arr[j+1])
	    swap(&arr[j], &arr[j+1]);
}

int isSorted(int *a, int size) {
  int i;
  for (i = 0; i < size - 1; i++) {
    if (a[i] > a[i + 1]) {
      return 0;
    }
  }
  return 1;
}

int* merge(int arr1[], int n1, int arr2[], int n2) {
    int* merged = (int *)malloc(sizeof(int)*n1+n2);
    int i=0, j=0;
    for(int count=0; count < n1+n2; count++) {
        if(i < n1 && ( j >= n2 || arr1[i] <= arr2[j] )) {
            merged[count] = arr1[i];
            i++;
        }
        else if(j < n2 && ( i >= n1 || arr2[j] <= arr1[i] ) ) {
            merged[count] = arr2[j];
            j++;
        }
    }
    return merged;
}

// Function to print an array
void printArray(int arr[], int size)
{
  int i;
  for (i=0; i < size; i++)
    printf("%d ", arr[i]);
  printf("\n");
}


int* mergeInArray(int arr[],int start1, int n1, int start2, int n2) {
    int* merged = (int *)malloc(sizeof(int)*n1+n2);
    int i=0, j=0;
    for(int count=0; count < n1+n2; count++) {
        if(i < n1 && ( j >= n2 || arr[start1+i] <= arr[start2+j] )) {
            merged[count] = arr[start1+i];
            i++;
        }
        else if(j < n2 && ( i >= n1 || arr[start2+j] <= arr[start1+i] ) ) {
            merged[count] = arr[start2+j];
            j++;
        }
    }
    return merged;
}


int main(int argc, char** argv) {
	int i, n;
	int* A;
	clock_t start, end;
	double elapsed_time, t1, t2;

	MPI_Init(NULL, NULL);
    int world_size, world_rank;
    MPI_Comm_size(MPI_COMM_WORLD, &world_size);
    MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);
    int elements_per_process = N/world_size;

    MPI_Barrier(MPI_COMM_WORLD);
	t1 = MPI_Wtime();
	A = (int *)malloc(sizeof(int)*N);
	if (A == NULL) {
		printf("Fail to malloc\n");
		exit(0);
	}
	for (i=N-1; i>=0; i--)
		A[N-1-i] = i;
	
	if (isSorted(A, N))
	  printf("Array is sorted\n");
	else
	  printf("Array is NOT sorted\n");

    int *subarray = (int *)malloc(sizeof(int) * elements_per_process);
    MPI_Scatter(A, elements_per_process, MPI_INT, subarray,
                elements_per_process, MPI_INT, 0, MPI_COMM_WORLD);
    printf("this is subarray of %d\n", world_rank);

	bubbleSort(subarray, elements_per_process);
	printArray(&subarray[elements_per_process-10], 10);

    int* B = (int *)malloc(sizeof(int)*N);
    int* result = (int *)malloc(sizeof(int)*N);
    MPI_Gather(subarray, elements_per_process, MPI_INT, B, elements_per_process, MPI_INT, 0, MPI_COMM_WORLD);
    if(world_rank == 0){
        result = mergeInArray(B,0,elements_per_process,N/world_size,elements_per_process);
        printArray(&result[N-10], 10);
        free(A);
        free(B);
    }

    if (isSorted(result, N))
	  printf("Array is sorted\n");
	else
	  printf("Array is NOT sorted\n");

    free(result);
    free(subarray);
    //printArray(&result[N-10], 10);
	t2 = MPI_Wtime();
	printf( "Elapsed time MPI_Wtime is %f\n", t2 - t1 ); 
	MPI_Finalize();
	return 0;
}
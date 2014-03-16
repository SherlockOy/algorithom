#include <stdio.h>
//希尔排序
void shellSort(int v[], int n);
//归并排序
void mergeSort(int v[], int help[],int low, int high);
void merge(int v[], int help[],int low, int mid, int high);
//交换值
void swap(int *a, int *b);
//堆排序
void heapSort(int v[],int len);
void buildMaxHeap(int v[], int len);
void adjustDown(int v[], int k, int len);
//简单选择排序
void selectSort(int v[], int n);
//冒泡排序
void bubbleSort(int v[], int n);
//快速排序
void quickSort(int v[],int low, int high);
int partition(int v[], int low, int high);



int main(void){

    int v[10] = {6,3,2,9,0,4,7,5,1,8};
    int help[10];
    //shellSort(v,10);
    //mergeSort(v,help,0,9);
    //heapSort(v,10);
    //selectSort(v,10);
    //quickSort(v,0,9);
    bubbleSort(v,10);
    for(int i = 0; i<10; i++){
        printf("%d\n", v[i]);
    }
}

//swap
void swap(int *a,int *b){
    int temp = *a;
    *a = *b;
    *b = temp;
}


//mergeSort mergeSort function
void mergeSort(int v[], int help[],int low, int high){
    if(high > low){
        int mid = (high+low)/2;

        mergeSort(v,help,low,mid);
        mergeSort(v,help,mid+1,high);
        merge(v,help,low,mid,high);
    }
}
//mergeSort merge function
void merge(int v[], int help[],int low, int mid ,int high){
    int i,j,k;
    for(k = low; k<=high; k++){
        help[k] = v[k];
    }
        

    for(i = low, j = mid+1, k = low; i<= mid && j<=high;k++){
        if(help[i]<=help[j])
            v[k] = help[i++];
        else
            v[k] = help[j++];
    }
    while(i<=mid)   v[k++] = help[i++];
    while(j<=high)  v[k++] = help[j++];
}


//shellSort
void shellSort(int v[], int n){
    for(int gap = n/2; gap>0; gap /= 2)
        for(int i = gap; i<n; i++)
            for(int j = i-gap; j>=0&&v[j]>v[j+gap]; j-=gap){
                int temp = v[j];
                v[j] = v[j+gap];
                v[j+gap] = temp;
            }
}

//heapSort
void heapSort(int v[], int len){
    buildMaxHeap(v,len);
    for(int i = len; i>1; i--){
        int temp = v[0];
        v[0] = v[i-1];
        v[i-1] = temp;
        adjustDown(v,1,i-1);
    }
}

//heapSort buildMaxHeap
void buildMaxHeap(int v[], int len){
    for(int i = len/2;i>0;i--)
        adjustDown(v,i,len);
}

//heapSort adjustDown
void adjustDown(int v[], int k, int len){
    int i;
    for(i = 2*k; i<=len; i*=2){
        if( i<len && v[i-1]<v[i])
            i++;
        if(v[k-1]>=v[i-1])
            break;
        else{
            int temp = v[k-1];
            v[k-1] = v[i-1];
            v[i-1] = temp;
            k = i;
        }
    }
}

//selectSort
void selectSort(int v[], int n){
    int i;
    int j;
    for(i = 0; i< n;i++){
        int min = i; 
        for(j = i+1; j<n;j++ ){
            if(v[min]>v[j])
                min = j;
        }
        if(min!=i)
            swap(&v[i],&v[min]);
    }
}

//bubbleSort
void bubbleSort(int v[], int n){

    for(int i = 0; i<n; i++){
        int temp = v[i];
        int swapped = 0;
        for(int j = n-1; j>i; j--){
            if(v[j]<v[j-1]){
                swap(&v[j],&v[j-1]);
                swapped = 1;
                }
        }
        if(swapped == 0)
            break;
    }
}



//quickSort
void quickSort(int v[],int low,int high){
    if(low<high){
        int pivotpos = partition(v,low,high);
        quickSort(v,low,pivotpos-1);
        quickSort(v,pivotpos+1,high);
    }
}

//quickSort partition
int partition(int v[], int low,int high){
    int pivot = v[low];
    while(low<high){
        while(low<high && v[high]>=pivot)   high--;
        v[low] = v[high];
        while(low<high && v[low]<=pivot)    low++;
        v[high] = v[low];
    }
    v[low] = pivot;
    return low;
}

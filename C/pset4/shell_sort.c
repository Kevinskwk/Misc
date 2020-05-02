int arr[100];

void shift_element_by_gap(unsigned int i, unsigned int gap) {
    int ivalue;
    unsigned int j;
    for (j = i, ivalue = arr[i]; j >= gap && arr[j-gap] > ivalue; j -= gap)
        arr[j] = arr[j-gap];
    arr[j] = ivalue;
}

void shell_sort(void) {
    unsigned int gap, i, len = array_length(arr);
    for (gap = len/2; gap > 0; gap/=2) {
        for (i = gap; i < len; i++) {
            if (arr[i-gap] > arr[i]) {
                shift_element_by_gap(i, gap); 
            }
        }
    }
}
int arr[100];

/* move previous element down until insertion point reached */
void shift_element(int *pElement) {
    int ivalue = *pElement;
    for (ivalue = *pElement; pElement > arr && *(pElement - 1) > ivalue; pElement--)
        *pElement = *(pElement-1);
    *pElement = ivalue;
}

/* iterate until out-of-order element found; shift the element, and continue iterating */
void insertion_sort() {
    int *pElement, *pEnd = arr + array_length(arr);
    for (pElement = arr+1; pElement < pEnd; pElement++)
        if (*pElement < *(pElement - 1))
            shift_element(pElement);
}
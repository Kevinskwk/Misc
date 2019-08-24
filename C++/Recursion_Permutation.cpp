#include <iostream>
#include <string>
using namespace std;

int cnt(0);
void permute(char a[], int i, int n) {
    int j;
    if (i == n) {
        cout << a <<endl;
        cnt++;
    }
    else {
        for (j = i; j <= n; j++){
            swap(a[i], a[j]);
            permute(a, i+1, n);
            swap(a[i], a[j]);
    
        }
    }
}

int main() {
    char a[] = "ABCD";
    permute(a, 1, 2);
    cout << cnt << endl;
    return 0;
}
# C Programming Notes

Reference: MIT OCW 6.087 - Practical Programming in C

https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-087-practical-programming-in-c-january-iap-2010/index.htm


## Lecture 1: Compliling, Debugging

### Compiling
- gcc: The GNU Compiler Collection
- Run gcc:
    ```
    $ gcc -Wall infilename.c -o outfilename.o
    ```
    - `-Wall`: enables most compiler warnings

- Embeded debugging info and disable optimization:
    ```
    $ gcc -g -O0 -Wall infilename.c -o outfilename.o
    ```

### Debugging

#### gdb: The GNU Project Debugger
- Useful commands:
    - `break linenumber` - create breakpoint at specified line
    - `break file:linenumber` - create breakpoint at line in file
    - `run` - run program
    - `c` - continue execution
    - `next` - execute next line
    - `step` - execute next line or step into function
    - `quit` - quit gdb
    - `print expression` - print current value of the specified expression
    - `help command` - in-program help

#### Memory debugging
- `valgrind`: diagnose memory-related problems

## Syntax

### Structure of a .c file
```
/* Begin with comments about file contents */

Insert #include statements and preprocessor definitions

Function prototypes and variable declarations

Define main() function
{
    Function body
}

Define other function
{
    Function body
}
...
```

### Comments
- completely ignored by compiler
- can appear almost anywhere
```c
// single line comment
/* Multiple 
   line
   comment */
```

### The #include macro
- Header files: constants, functions, and other declarations
- `#include <stdio.h>` - read the contents of the *header file* `stdio.h`
- `stdio.h`: standard I/O functions for console, files; part of the C Standard Library
- Other important header files: `ctype.h`, `math.h`, `stdlib.h`, `string.h`, `time.h`
- include files must be on *include path*
    - `-Idirectory` with `gcc`: specify additional include directories
    - standard include directories assumed by default
    - `#include "stdio.h"` - searches `./` for `stdio.h` first

### Declaring variables
- you must declare variables before using them
- variable declaration:
    ```c
    int n; // integer data type variable n
    float phi; // floating point data type variable phi
    ```
- Initializing variables:
    ```c
    int n;
    n = 3;

    float phi = 1.618;

    int a, b, c = 0, d = 4
    ```

### Arithmetic expressions
- Order of operations:
    - +, - (sign) : right-to-left
    - *, /, % : left-to-right
    - +, - : left-to-right
    - =, +=, -=, *=, /=, %= : right-to-left

### Strings
- Strings are stored as character array
- Null-terminated (last char in array is `'\0'` null)
- Special characters specified using `\`:
    - `'\\'` - backslash
    - `'\''` - apostrophe
    - `'\"'` - quotation mark
    - `'\b, \t, \r, \n'` - backspace, tab, carriage return, linefeed
    - `'\ooo, \xhh'` - octal and hexadecimal ASCII character codes, e.g. `'\x41'` – `'A'`, `'\060'` – `'0'`

### Console I/O
- stdout, stdin: console output and input streams
- `puts(string)`: print string to stdout
- `putchar(char)`: print character to stdout
- `char = getchar()`: return character from stdin
- `string = gets(string)`: read line from stdin into string

### Macros

#### Preprocessor macros
- Preprocessor macros begin with `#` character
    ```
    #include <stdio.h>
    ```
- defines msg as “hello, 6.087 students” throughout source file
    ``` 
    #define msg "hello, 6.087 students"
    ```
- many constants specified this way 

#### Defining expression macros
- `#define` can take arguments and be treated like a function
    ```
    #define add3(x,y,z) ((x)+(y)+(z))
    ```
- parentheses ensure order of operations
- compiler performs inline replacement; not suitable for recursion 

#### Conditional preprocessor macros
- `#if, #ifdef, #ifndef, #else, #elif, #endif` are conditional preprocessor macros, can control which lines are compiled
    - evaluated before code itself is compiled, so conditions must be preprocessor defines or literals
    - the `gcc` option `-Dname=value` sets a preprocessor define that can be used
    - Used in header files to ensure declarations happen only once 
- `#pragma` - preprocessor directive
- `#error, #warning` - trigger a custom compiler error/warning
- `#undef msg` - remove the definition of msg at compile time 


## Lecture 2: Variables and datatypes, operators

### Variables
- A variable is as named link/reference to a value stored in the system’s memory or an expression that can be evaluated. 
- Naming rules:
    - Variable names can contain letters, digits and `_`
    - Variable names should start with letters.
    - Keywords (e.g., `for`, `while`, etc.) cannot be used as variable names
    - Variable names are case sensitive. int x; int X declares two different variables.

### Data types and sizes

#### A small family of datatypes
- Numeric (`int`, `long`, `float`, `double`)
- Character (`char`)
- User defined (`struct`, `union`) 

#### Numeric datatypes
- unsigned version has roughly double the range of its unsigned counterparts
- signed and unsigned characters differ only when used in arithmetic expressions

#### Big endian vs. little endian
- guaranteed relationship:
    - `sizeof(char) < sizeof(short) <= sizeof(int) <= sizeof(long)`
    - `sizeof(char) < sizeof(short) <= sizeof(float) <= sizeof(double)`

- Big endian: the **most** significant bits (MSBs) occupy the lower address. This representation is used in the powerpc processor. Networks generally use big-endian order, and thus it is called network order. 
- Little endian: the **least** signficant bits (LSBs) occupy the lower address. This representation is used on all x86 compatible processors. 

#### Constants
Constants are literal/fixed values assigned to variables or used directly in expressions. 
```c
// integer
int i = 3; //integer
long l = 3; //long integer
unsigned long ul = 3UL; //unsigned long
int i = 0xA; // hexadecimal
int i = 012; // octal number

// floating point
float pi = 3.14159; // float
float pi = 2.141F // float
double pi = 3.1415926535897932384L // double

// character
'A' // character
'\x41' // specified in hex
'\0101' // specified in octal

// string
"hello world" // string literal
"hello""world" // same as "hello world"

// enumeration
enum BOOL{NO, YES}; //NO=0, YES=1
enum COLOR{R=1, G, B, Y=10}; // R=1, G=2, B=3, Y=10
```

### Operators

#### Arithmetic operators
- `+` addition
- `-` subtraction
- `*` multiplication
- `/` division
- `%` modulus

#### Relational operators
- `>` greater than
- `>=` greater than or equal to
- `<` lesser than
- `<=` lesser than or equal to
- `==` equal to
- `!=` not equal to

#### Logical operators
- `&&` AND
- `||` OR
- `!` NOT

#### Bitwise operators
- `&` AND
- `|` OR
- `^` XOR
- `<<` left shift
- `>>` right shift

#### Assignment Operators
- `var = var (op) expr`

#### Conditional expression
```c
if (cond)
    x = <expra>;
else
    x = <exprb>;
```
Or
```c
sign = x > 0 ? 1 : -1;
// same as
if (x>0)
    sign = 1;
else
    sign = -1;
```

### Type conversions
- when variables are promoted to higher precision, data is preserved
- `char` is automatically converted to `int` by the compiler when comparing adn manipulating
    ```c
    char c = 'm'
    isUpper = (c>='A' && c<='Z') ? 1: 0;
    
    if (!isUpper)
        c = c - 'a' + 'A';
    ```

### Precedence and Order of Evaluation
- `++, -, (cast), sizeof()` have the highest priority
- `*, /, %` have higher priority than `+, -`
- `==, !=` have higher priority than `&&, ||`
- assignment operators have very low priority


## Lecture 3: Control flow, Functions and modular programming, Variable scope

### Control conditions
- no boolean type be fore C99, (use `stdbool.h` after C99)
- Expression is non-zero => condition true
- expression must be numeric (or a pointer)\

- conditional statements
    - if statement:
        ```c
        if (x % 2 == 0)
            y += x/2;
        else if (x % 4 == 1)
            y += 2*((x+3)/4);
        else
            y += (x+1)/2;

        if (x % 4 == 0) {
            if (x % 2 == 0)
            y = 2;
        } else
            y = 1; 
        ```
    
    - switch statement:
        ```c
        switch (ch) {
            case 'Y': /∗ch == 'Y'∗/
                /∗ do something ∗/
                break;
            case 'N': /∗ch == 'N'∗/
                /∗ do something else ∗/
                break;
            default: /∗ otherwise ∗/
                /∗ do a third thing ∗/
                break;
        }
        ```

- Loop statements
    - while loop
        ```c
        while (/*consition*/)
            /*loop body*/
        ```
    - for loop
        ```c
        int factorial(int n) {
            int i, j = 1;
            for (i=1; i<=n; i++)
                j *= i;
            return j;
        }
        ```

    - do-while loop
        ```c
        char c;
        do {
            /*loop body*/
            puts("Keep going? (y/n)");
            c = getchar();
            /*other processing*/
        } while (c == 'y' && /*other conditions*/)
        ```

    - `break`: exits innermost loop or `switch` statement to exit early
    - `continue`: skips resst of innermost loop body, jumping to loop condition

### Divide and Conquer
- Break down problem into simpler sub-problems
- Consider iteration and recursion
- Minimize transfer of state between functions

### Modular programming
- C programs do not need to be monolithic
    - Module: interface and implementation
    - interface: header files
- implementation: auxilliary source/object files
- Need to inform other source files about functions/global variables in `euclid.c`
- For functions: put function prototypes in a header file
- For variables: re-declare the global variable using the `extern` keyword in header file
- `extern` informs compiler that variable defined somewhere else
- Enables access/modifying of global variable from other source files

#### example: euclid
- `euclid.c`:
    ```c
    /* compute the gcd*/
    int gcd(int a, int b) {
        if (a < b) {
            int temp = b;
            b = a;
            a = temp;
        }

        while (b) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    int ext_euclid(int a, int b) {
        if (a < b) {
            int temp = b;
            b = a;
            a = temp;
        }

        while (b) {
            int temp = b;
            int q = 
            b = a % b;
            a = temp;
        }
        return a;
    }
    ```
- `euclid.h`:
    ```c
    /∗ensure included only once∗/
    #ifndef __EUCLID_H__
    #define __EUCLID_H__

    /∗global variables (declared in euclid.c)∗/
    extern int x, y;

    /∗compute gcd∗/
    int gcd(int a, int b);

    /∗compute g = gcd(a, b) and solve ax+by=g∗/
    int ext_euclid(int a, int b);
    
    #endif
    ```

- Using the euclid module
    - `#include "euclid.h"`
- Compiling the module
    - When compiling the source files, the outputs need to be linked together into a single output
    - `$ gcc -g -O0 -Wall main.c euclid.c -o main.o`

### Variable scope
- scope - the region in which a variable is valid
- Many cases, corresponds to block with variable's declaration
- Variables declared outside of a function have global scope
- Function definitions also have scope 

#### Static variables
- `static` keyword has two meanings, depending on where the static variable is declared
- Outside a function, `static` variables/functions only visible within that file, not globally (cannot be `extern`'ed)
- Inside a function, static variables:
    - are still local to that function
    - are initialized only during program initialization
    - do not get reinitialized with each function call 
- `static int somePersistentVar = 0;` 

#### Register variables
- During execution, data processed in registers (faster than memory)
- Explicitly store commonly used data in registers - minimize load/store overhead
- Can explicitly declare certain variables as registers using `register` keyword
    - must be a simple type (implementation-dependent)
    - only local variables and function arguments eligible
    - excess/unallowed register declarations ignored, compiled as regular variables
- Registers do not reside in addressed memory; pointer of a register variable is illegal

## Lecture 4: Control flow, Input Output

### Control flow
goto
- `goto` allows you to jump unconditionally to arbitrary part of your code (within the same function).
- the location is identified using a label.
- a label is a named location in the code. It has the same form as a variable followed by a ’`:`’
    ```c
    start:
    {
        if(cond)
        goto outside;
        /∗some code∗/
        goto start;
    }
    outside:
    /∗outside block*/ 
    ```
- `goto` is dangerous!
    - Excess use of goto creates sphagetti code.
    - Using goto makes code harder to read and debug.
    - Any code that uses goto can be written without using one.

- error handling: goto provides a convenient way to exit from nested blocks

### I/O
- Input and output facilities are provided by the standard library `<stdio.h>` and not by the language itself.
- A text stream consists of a series of lines ending with `'\n'`. The standard library takes care of conversion from `'\r\n'-'\n'`
- A binary stream consists of a series of raw bytes.
- The streams provided by standard library are buffered.

#### Standard I/O
- `int putchar(int)` puts the character c on the standard output. 
- `int getchar()` returns the next character from standard input.
- To use a file instead of standard input, use '`<`' operator (*nix)
    - Normal invocation: `$ ./a.out`
    - Input redirection: `a.out < file.txt`. Treats file.txt as source of standard input.This is an OS feature, not a language feature. 

- `int printf(char format[], arg1, arg2,...)`
    - `printf()` can be used for formatted output.
    - It takes in a variable number of arguments.
    - It returns the number of characters printed.
    - The format can contain literal strings as well as format specifiers (starts with %). 
    - The format specification has the following components:
        ```
        %[flags][width ][. precision ][ length]<type>
        ```
    - type:
        - `d,i`: integer `printf("%d",10); /∗prints 10∗/`
        - `x,X`: integer(hex) `printf("%x",10); /∗print 0xa∗/`
        - `u`: unsigned integer `printf("%u",10); /∗prints 10∗/`
        - `c`: character `printf("%c",’A’); /∗prints A∗/`
        - `s`: string `printf("%s","hello"); /∗prints hello∗/`
        - `f`: float `printf("%f",2.3); /∗ prints 2.3∗/`
        - `d`: double `printf("%d",2.3); /∗ prints 2.3∗/`
        - `e,E`: float(exp) `1e3,1.2E3,1E−3`
        - `%`: literal % `printf("%d %%",10); /∗prints 10%∗/ `

    - width:
        - `printf("%d",10)` "10"
        - `printf("%4d",10)` bb10 (b:space)
        - `printf("%s","hello")` hello
        - `printf("%7s","hello")` bbhello 
    
    - precision:
        - `printf("%.2f,%.0f,1.141,1.141)` 1.14,1
        - `printf("%.2e,%.0e,1.141,100.00)` 1.14e+00,1e+02
        - `printf("%.4s","hello")` hell
        - `printf("%.1s","hello")` h 
    
    - modifier:
        - `h` interpreted as short. Use with i,d,o,u,x
        - `l` interpreted as long. Use with i,d,o,u,x
        - `L` interpreted as double. Use with e,f,g

- formatted input `int scanf(char∗ format ,...)` is the input analog of printf.
    - scanf reads characters from standard input, interpreting them according to format specification
    - Similar to printf , scanf also takes variable number of arguments.
    - The format specification is the same as that for printf
    - When multiple items are to be read, each item is assumed to be separated by white space.
    - It returns the number of items read or EOF.
    - **Important**: scanf ignores white spaces.
    - **Important**: Arguments have to be address of variables (pointers).

#### String I/O
- `int sprintf(char string [], char format[],arg1,arg2)`
    - The format specification is the same as printf.
    - The output is written to string (does not check size).
    - Returns the number of character written or negative value on error.

- `int sscanf(char str [], char format[],arg1,arg2)`
    - The format specification is the same as scanf;
    - The input is read from str variable.
    - Returns the number of items read or negative value on error. 

#### File I/O
- `FILE* fopen(char name[], char mode[])` open the file
    - mode can be "r" (read only),"w" (write only),"a" (append) among other options. "b" can be appended for binary files.
- `int fclose(FILE* fp)` close the file
- `int getc(FILE* fp)` reads a single char from the stream
- `char[] fgets(char line[], int maxlen, FILE∗ fp)` reads a single line (upto maxlen characters) from the input stream (including linebreak). 
- `int putc(int c, FILE∗ fp)` writes a single character c to the output stream.
- `int fputs(char line[], FILE∗ fp)` writes a single line to the output stream. 
- `int fscanf(FILE∗ fp,char format[], arg1, arg2)` reads items from input stream fp.

#### Command line input
`int main(int argc, char∗ argv[])`
- `argc`: count of arguments.
- `argv[]`: an array of pointers to each of the arguments
- note: the arguments include the name of the program as well. 


## Lecture 5: Pointers

### Pointers and Memory Addresses
- Pointer: memory address of a variable
- Address ca nbe used to access/modify a variable from anywhere

#### Physical and virtual memory
- Physical memory: physical resources where data can be stored and accessed by computer
    - cache, RAM, hard disk, removable storage
    - Memory management - major function of OS
- Virtual memory: abstraction by OS, addressable space accessible by your code
    - Virtual memory maps to different parts of physical memory
    - Usable parts of virtual memory: *stack* and *heap*
        - stack: where declared variables go
        - heap: where dynamic memory goes

#### Addressing and Indirection
- Every variable residing in memory has an address
- What doesn't have an address?
    - register variables
    - constants/literals/processor defines
    - expressions (unless result in a variable)
- The `&` operator:
    ```c
    int n = 4;
    double pi = 3.14159;
    int *pn = &n; /* address of integer n */
    double *ppi = &pi; /* address of double pi */
    ```
- Address of a variable of type `t` has type `t*`
- Accessing/modifying addressed variable: dereferencing/indirection operator `*`
    ```c
    /* prints "pi = 3.14159\n" */
    printf("pi = %g\n", &ppi);

    /* pi now equals 7.14159 */
    *ppi = *ppi + *pn
    ```
- Dereferenced pointer is like any other variable
- null pointer, i.e. 0 (`NULL`): pointer that does not reference anything
- casting pointers
    - can explicitly cast any pointer type to any other pointer type
        ```c
        ppi = (double *)pn; /* pn originally of type (int*) */
        ```
    - Dereferenced pointer has new type, regardless of real type of data

#### Functions with Multiple Outputs
- Can use pointers to pass back multiple outputs:
    ```c
    /* defining */
    int ext_euclid(int a, int b, int *x, int *y);

    /* calling */
    int x, y, g;
    /* assume a, b declared previously */
    g = ext_euclid(a, b, &x, &y);
    ```
- swapping two ints:
    ```c
    void swap(int *x, int *y) {
        int temp = *x;
        *x = *y;
        *y = temp;
    }

    int a = 5, b = 7;
    swap(&a, &b);
    ```

### Arrays and Pointer Arithmetic

#### Array and pointers
- Primitive arrays implemented in C using pointer to block of contiguous memory
- Consider array of 8 ints:
    ```c
    int arr[8];
    ```
- Accessing arr using array entry operator:
    ```c
    int a = arr[0];
    ```
- `arr` is like a pointer to element 0 of the array:
    ```c
    int *pa = arr; /*<=>*/ int *pa = &arr[0];
- Not modifiable/reassignable like a pointer
- The `sizeof()` operator
    - For primitive types/variables, size of type in bytes
    - For primitive arrays, size of array in bytes
    - Array length:
        ```c
        #define array_length(arr) (sizeof(arr) == 0 ? 0 : sizeof(arr) / sizeof((arr)[0]))
        ```

#### Pointer arithmetic
- Suppose `int *pa = arr;`
- Pointer not an int, but can add or subtract an int from a pointer: `pa + i` points to `arr[i]`
- Address value increments by i times size of data type. Suppose `arr[0]` has address 100. Then `arr[3]` has address 112.

### Strings as arrays
- Strings stored as null-terminated character arrays (last character == `'\0'`)
- Suppose `char str[] = "This is a string.";` and `char *pc = str;`
- Manipulate string as you would an array
    ```c
    *(pc + 10) = 'S';
    puts(str); /* prints "This is a String." */
    ```

#### String utility functions
- String functions in standard header `string.h`
- Copy functions:
    ```c
    char *strcpy(strto, strfrom); /* copy strfrom to strto */
    char *strncpy(strto, strfrom, n); /* copy n chars from strfrom to strto */
    ```
- Comparison functions:
    ```c
    int strcmp(str1, str2); /* compare str1, str2; return 0 if equal, positive if str1>str2, negative if str1 < str2 */
    int strncmp(str1, str2, n); /* compare first n chars of str1 and str2 */
    ```
- String length:
    ```c
    int strlen(str); /* get length of str */
    ```
- Concantenation functions:
    ```c
    char ∗strcat(strto, strfrom); /* add strfrom to end of strto */
    char ∗strncat(strto, strfrom, n); /* add n chars from strfrom to end of strto */
    ```
- Search functions:
    ```c
    char ∗strchr(str ,c); /* find char c in str, return pointer to first occurrence, or NULL if not found */
    char ∗strrchr(str ,c); /* find char c in str, return pointer to last occurrence, or NULL if not found */
    ```

### Searching amd Sorting Algorithms
- Simple linear search:
    ```c
    int arr[100];

    int * linear_search(int val)) {
        int *parr, *parrend = arr + array_length(arr);
        for (parr = arr; parr < parrend; parr++) {
            if (*parr == val)
                return parr;
        }
        return NULL;
    }
    ```

- Insertion sort:
    ```c
    /* using indexing */
    int arr[100];

    /* move previous element down until insertion point reached */
    void shift_element(unsigned int i) {
        int ivalue;
        for (ivalue = arr[i]; i && arr[i-1] > ivalue; i--)
            arr[i] = arr[i-1];
        arr[i] = ivalue;
    }

    /* iterate until out-of-order element found; shift the element, and continue iterating */
    void insertion_sort() {
        unsigned int i, len = array_length(arr);
        for (i = 1; i < len; i++)
            if (arr[i] < arr[i-1])
                shift_element(i);
    }
    ```
    ```c
    /* using pointers */
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
    ```

- Quicksort
    ```c
    void quick_sort(unsigned int left, unsigned int right) {
        unsigned int i, mid;
        int pivot;
        if (left >= right)
            return; /* nothing to sort */
        /* pivot is midpoint; move to left side */
        swap(arr + left, arr + (left + right) / 2);
        pivot = arr[mid = left];
        /* separate into side < pivot (left+1 to mid) and side >= pivot (mid+1 to right) */
        for (i = left+1; i <= right; i++)
            if (arr[i] < pivot)
                swap(arr + ++mid, arr + i);
        /* restore pivot position */
        swap(arr+left, arr+mid);
        /* sort two sides */
        if (mid > left)
            quick_sort(left, mid-1);
        if (mid < right)
            quick_sort(mid+1, right);
    }

    quick_sort(0, array_length(arr)-1);
    ```
    - Implemented in C standard library as `qsort()` in `stdlib.h`

- Binary search
    ```c
    int *binary_search(int val) {
        unsigned int L = 0, R = array_length(arr), M;
        while (L < R) {
            M = (L + R - 1) / 2;
            if (val == arr[M])
                return arr + M; /* found */
            else if (val < arr[M])
                R = M; /* in first half */
            else
                L = M + 1 /* in second half */
        }
        return NULL; /* not found */
    }
    ```
    - Implemented in C standard library as `bsearch()` in `stdlib.h`


## Lecture 6: User defined datatype, Data structure

### User defined datatype

#### Structures
 A structure is a collection of related variables (of possibly different types) grouped together under a single name. This is a an example of **composition** - building complex structures out of simple ones.

```c
struct point
{
    int x;
    int y;
};
/* notice the ; at the end */

struct employee
{
    char fname[100];
    char lname[100];
    int age;
};

struct triangle
{
    struct point ptA;
    struct point ptB;
    struct point ptC;
};
/* members can be structures */

struct chain_element
{
    int data;
    struct chain_element* next;
};
/* members can be self referential */
```
- Initialization is done by specifying values of every member.
    ```c
    struct point ptA = {10, 20};
    ```
- Assignment operator copies every member of the structure, structures are copied element wise
- Indivifual members can be accessed using '.' operator
    ```c
    struct point pt = {10, 20};
    int x = pt.x;
    int y = pt.y;
    ```
- For large structures it is more efficient to pass pointers.
    ```c
    void foo(struct point * pp);
    struct point pt;
    foo(&pt);
    ```
- Members can be accessed from structure pointers using '`->`' operator.
    ```c
    struct point p = {10, 20};
    struct point * pp = &p;
    pp->x = 10; /* changes p.x */
    int y = pp->y; /* same as y = p.y */

    /* OR */
    (*pp).x = 10;
    int y = (*pp).y;
    ```

- Arrays of structures
    ```c
    /* Declaring arrays of structure: */
    struct point p[10];
    
    /* Initializing arrays: */
    struct point p[3] = {0, 1, 10, 20, 30, 12};
    struct point p[3] = {{0, 1}, {10, 20}, {30, 12}};
    ```

- Size of structures: greater than or equal to the sum of the sizes of its members

#### Unions
A union is a variable that may hold objects of different types/sizes in the same memory location.
```c
union data
{
    int idata;
    float fdata;
    char* sdata;
} d1, d2, d3;

d1.idata = 10;
d1.fdata = 3.14F;
d1.sdata = "hello world";
```
- The size of the union variable is equal to the size of its largest element.
- **Important**: The compiler soes not test if the data is being read in the correct format.
    ```c
    union data d;
    d.idata = 10;
    float f = d.fdata; /* will give junk */
    ```
- A common solution is to maintain a separate variable:
    ```c
    enum dtype{INT, FLOAT, CHAR};
    struct variant
    {
        union data d;
        enum dtype t;
    };
    ```

#### Bitfields
 A bit-field is a set of adjacent bits within a single ’word’.
```c
struct flag
{
    unsigned int is_color: 1;
    unsigned int has_sound: 1;
    unsigned int is_ntsc: 1;
};
```
- The number after the colons specifies the width in bits
- Each variable should be declared as `unsigned int`

### Data Structure

#### Dynamic Memory allocation
```c
void* malloc(size_t n)
```
- `malloc()` allocates blocks of memory
- returns a pointer to **unitialized** block of memory on success
- returns NULL on failure
- the returned value should be cast to appropriate type using:
    ```c
    int* ip = (int*)malloc(sizeof(int)*100);
    ```

```c
void* calloc(size_t n, size_t size)
```
- allocates an arrat of n elements each of which is 'size' bytes.
- initializes memory to 0

```c
void free(void*)
```
- Frees memory allocated by `malloc()`
- Common error: accessing memory after calling free

#### Linked lists
A dynamic data structure that consists of a sequence of records where each element contains a link to the next record in the sequence
- linked lists can be *singly linked*, *doubly linked* or *circular*. For now, we will focus on *singly linked* list
- Every node has a *payload* and a link to the next node in the list
- The start (*head*) of the list is maintained in a separate variable
- End of the list is indicated by a NULL (*sentinel)
```c
struct node
{
    int data; /* payload */
    struct node* next;
};
struct node* head; /* beginning */
```
- linked list vs. array:
    - size: dynamic vs. fixed
    - indexing: O(n) vs. O(1)
    - inserting: O(1) vs. O(n)
    - deleting: O(1) vs. O(n)
- Creating new element:
    ```c
    struct node* nalloc(int data)
    {
        struct node* p = (struct node*)malloc(sizeof(struct node));
        if (p != NULL)
        {
            p->data = data;
            p->next = NULL;
        }
        return p;
    }
    ```
- Adding elements to front:
    ```c
    struct node* addfront(struct node* head, int data)
    {
        struct node* p = nalloc(data);
        if (p==NULL)
            return head;
        p->next = head;
        return p;
    }
    ```
- Iterating:
    ```c
    for (p = head; p != NULL; p = p->next)
        /* do something */
    
    for (p = head; p->nexy != NULL; p = p->next)
        /* do something */
    ```

#### Binary trees
- A binary tree is a dynamic data structure where each node has at most two children. A binary **search** tree is a binary tree with ordering among its children. 
- Usually, all elements in the left subtree are assumed to be ”less” than the root element and all elements in the right subtree are assumed to be "greater" than the root element. 

```c
struct tnode
{
    int data; /* payload */
    struct tnode* left;
    struct tnode* right;
};
```

- The operation on trees can be framed as recursive operations
- Traversal (printing, searching): 
    - pre-order: root, left, right
    - Inorder: left, root, right
    - post-order: right, left, root
- Add node:
    ```c
    struct tnode* addnode(struct tnode* root, int data)
    {
        struct tnode* p = NULL;
        /*termination condition*/
        if (root == NULL)
        {
            /*allocate node*/
            /*return new root*/
        }
        /*recursive call*/
        else if (data < root->data)
            root->left = addnode(root->left, data);
        else
            root->right = addnode(root->right, data);
    }
    ```

## Lecture 7: More about pointers, Data structures

### More about Pointers

#### Pointers to Pointers
- Address stored by pointer is also a piece of data in memory
- You can address location of address in memory - pointer to that pointer
    ```c
    int n = 3;
    int* pn = &n; //pointer to n
    int** pn = &pn; //pointer to address of n
    ```
- Manu uses in C: pointer arrays, string arrays
- examples:
    ```c
    void swap(int** a, int** b)
    {
        int* temp = *a;
        *a = *b;
        *b = temp;
    }
    ```

#### Pointer Arrays
- Pointer array: array of pointers
    - `int* arr[20];` - an array of pointers to `int`'s
    - `char* arr[10];` - an array of pointers to `char`'s
- Pointers in array can point to arrays themselves
    - `char* strs[10];` - an array of `char` arrays (or strings)
##### Example: sorting
- Have an array `int arr[100];` that contains some numbers
- want to have a sorted version of the array, but not modifying `arr`
- Can declare a pointer array `int* sorted_array[100];` containing pointers to elements of `arr` and sort the pointers instead of the numbers themselves
```c
int arr[100];
int* sorted_array[100];

// move previous elements down until insertion point reached
void shift_element(unsigned int i) {
    int* pvalue;
    //guard against going outside array
    for (pvalue = sorted_array[i]; i && *sorted_array[i-1] > *pvalue; i--) {
        //move pointer down
        sorted_array[i] = sorted_array[i-1];
    }
    sorted_array[i] = pvalue; //insert pointer
}

// iterate until out-of-order element found; shift the element and continue iterating
void insertion_sort(void) {
    unsigned int i, len = array_length(arr);
    for (i = 1; i < len; i++) {
        if (*sorted_array[i] < *sorted_array[i-1])
            shift_element(i);
    }
}
```

##### String arrays
- An array of strings, each stored as a pointer to an array of chars
- Each string may be of different length
    ```c
    char str1[] = "hello"; //length = 6
    char str2[] = "goodbye"; //length = 8
    char str3[] = "ciao"; //length = 5
    char* strArray[] = {str1, str2, str3};
    ```
- Note that `strArray` contains only pointers, not the characters themselves.

#### Multidimensional Arrays
- C also permites multidimensional arrays specified using `[]` brackets notation:
    - `int world[20][30];` is a 20*30 2-D array of `int`'s
- Higher dimensions possible:
    - `char bigcharmatrix[15][7][35][4];`
- Multidimensional arrays are rectangular; pointer arrays can be arbitary shaped

### Data Structures

#### Stacks
- Special type of list - last element in (push) is first out (pop)
- Read and write from same end of list
- The stack (where local variables are stored) is implemented as a *gasp* stack

##### Stack as array
- Store as array buffer (static allocation or dynamic allocation):
    ```c
    int stack_buffer[100];
    ```
- Elements added and removed from end of array; need to track end:
    ```c
    int itop = 0; //end at zero => initialized for empty stack
    ```
- Add element using `void push(int);`
    ```c
    void push(int elem) {
        stack_buffer[itop++] = elem;
    }
    ```
- Remove element using `int pop(void);`
    ```c
    int pop(void) {
        if (itop > 0)
            return stack_buffer[--itop];
        else
            return 0; //or other special value
    }
    ```
- Some implementations provide `int top(void);` to read last (top) element without removing it

##### Stack as linked list
- Store as linked list (dynamic allocation):
    ```c
    struct s_listnode {
        int element;
        struct s_listnode* pnext;
    };

    struct s_listnode* stack_buffer = NULL; //start empty
    ```
- "Top" is now at front of linked list (no need to track)
- Add element using `void push(int);`
    ```c
    void push(int elem) {
        //allocate new node
        struct s_listnode* new_node = (struct s_listnode*)malloc(sizeof(struct s_listnode));
        new_node->pnext = stack_buffer;
        new_node->element = elem;
        stack_buffer = new_nodes;
    }
    ```
- Adding an element pushes back the rest of the stack
- Remove element using `int pop(void);`
    ```c
    int pop(void) {
        if (stack_buffer) {
            struct s_listnode* pelem = stack_buffer;
            int elem = stack_buffer->element;
            stack_buffer = pelem->pnext;
            free(pelem); //remove node from memory
            return elem;
        } else
            return 0; //or other special value
    }
    ```
- Some implementations provide `int top(void);` to read last (top) element without removing it

#### Queues
- Opposite of stack - first in (enqueue), first out (dequeue)
- Read and write from opposite ends of list
- Important for UIs (event/message queues), networking (Tx, Rx packet queues)
- Imposes an ordering on elements 

##### Queue as array
- Store as array buffer (static or dynamic allocation)
    ```c
    float queue_buffer[100];
    ```
- Elements added to end (rear), removed from beginning (front)
- Need to keep track of front and rear, or track front and number of elements
    ```c
    int ifront = 0; icount = 0;
    ```
- Add element using `void enqueue(float);`
    ```c
    void enqueue(float elem) {
        if (icount < 100) {
            queue_buffer[(ifront + icount)%100] = elem; //use a circular buffer
            icount++;
        }
    }
    ```
- Remove element using `float dequeue(void);`
    ```c
    float dequeue(void) {
        if (icount > 0) {
            float elem = queue_buffer[ifront];
            icount--;
            ifront++;
            if (ifront == 100)
                ifront = 0;
            return elem;
        } else
            return 0.;
    }
    ```

##### Queue as linked list
- Store as linked list (dynamic allocation)
    ```c
    struct s_listnode {
        float element;
        struct s_listnode* pnext;
    };

    struct s_listnode* queue_buffer = NULL; //start empty
    ```
- Let front be at beginning- no need to track front
- Rear is at end - we should track it:
    ```c
    struct s_listnode* prear = NULL;
    ```
- Add element using `void enqueue(float);`
    ```c
    void enqueue(float elem) {
        struct s_listnode* new_node = (struct s_listnode*)malloc(sizeof(struct s_listnode)); //allocate new node
        new_node->element = elem;
        new_node->pnext = NULL; //at rear
        if (prear)
            prear->pnext = new_node;
        else //empty
            queue_buffer = new_node;
        prear = new_node;
    }
    ```
- Adding an element doesn't affect the front if the queue is not empty
- Remove element using `float dequeue(void);`
    ```c
    float dequeue(void) {
        if (queue_buffer) {
            struct s_listnode* pelem = queue_buffer;
            float elem = queue_buffer->element;
            queue_buffer = pelem->pnext;
            if (pelem == prear) //at end
                prear = NULL;
            free(pelem); //remove node from memory
            return elem;
        } else
            return 0.;
    }
    ```
- Removing element doesn't affect rear unless resulting queue is empty

#### Application: Calculator
- Stacks and queues allow us to design a simple expression evaluator
- Prefix, infix, postfix notation: operator before, between, and after operands, respectively
    - infix: (A + B) * (C - D)
    - prefix: * + A B - C D
    - postfix: A B + C D - *
- Infix more natural to write, postfix easier to evaluate 

##### Infix to postfix
1. dequeue token from input
2. if operand (number), add to output queue
3. if operator, then pop operators off stack and add to output queue as long as
    - top operator on stack has higher precedence, or
    - top operator on stack has same precedence and is left-associative

    and push new operator onto stack
4. return to step 1 as long as tokens remain in input
5. pop remaining operators from stack and add to output queue 

##### Evaluating postfix
1. dequeue a token from the postfix queue
2. if token is an operand, push onto stack
3. if token is an operator, pop operands off stack (2 for binary operator); push result onto stack
4. repeat until queue is empty
5. item remaining in stack is final result 


## Lecture 8: Void and function pointers, Hash tables

### Moooore Pointers

#### Void pointers
- C does not allow us to declare and use void variables.
- void can be used only as return type or parameter of a function.
- C allows void pointers
- void pointers can be used to point to any data type
    ```c
    int x; void* p = &x; //points to int
    float f; void* p = &f; //points to float
    ```
- void pointers cannot be dereferenced. The pointers should always be cast before dereferencing.
    ```c
    void* p; printf("%d", *p); //invalid
    void* p; int* px = (int*)p; printf("%d", *px); //valid
    ```

#### Function pointers
- In some programming languages, functions are first class variables (can be passed to functions, returned from functions etc.).
- In C, function itself is not a variable. But it is possible to declare pointer to functions. 
- Declaration examples:
    ```c
    int (*fp)(int); //notice the ()
    int (*fp)(void*, void*);
    ```
- Function pointers can be assigned, pass to and from functions, placed in arrays etc.

##### Callbacks
-  Callback is a piece of executable code passed to functions. In C, callbacks are implemented by passing function pointers.
- Example:
    ```c
    void qsort(void* arr, int num, int size, int (*fp)(void* pa, void* pb));
    ```
    - `qsort()` function from the standard library can sort an array of any datatype
    - `qsort()` calls a function whenever a comparison needs to be done
    - The function takes two arguments and returns (<0, 0, >0) depending on the relative order of the two items

    ```c
    int arr[] = {10, 9, 8, 1, 2, 3, 5};
    //callback
    int asc(void* pa, void* pb) {
        return (*(int*)pa - *(int*)pb);
    }
    //callback
    int desc(void* pa, void* pb) {
        return (*(int*)pb - *(int*)pa);
    }
    //sort in ascending order
    qsort(arr, sizeof(arr)/sizeof(int), sizeof(int), asc);
    //sort in descending order
    qsort(arr, sizeof(arr)/sizeof(int), sizeof(int), desc);
    ```
- Consider a linked list with nodes defined as follows:
    ```c
    struct node {
        int data;
        struct node* next;
    };
    ```
    Also consider the function `apply` defined as follows:
    ```c
    void apply(struct node* phead, void (*fp)(void*, void*), void* arg) //only fp has to be names
    {
        struct node* p = phead;
        while (p != NULL) {
            fp(p, arg); // can also use (*fp)(p, arg)
            p = p->next;
        }
    }
    ```
    - iterating:
    ```c
    struct node* phead;
    //populate somewhere
    void print(void* p, void* arg) {
        struct node* np = (struct node*)p;
        printf("%d", np->data);
    }
    apply(phead, print, NULL);
    ```
    - Counting nodes:
    ```c
    void dototal(void* p, void* arg) {
        struct node* np = (struct node*)p;
        int* ptotal = (int*) arg;
        *ptotal += np->data;
    }
    int total = 0;
    apply(phead, dototal, &total);
    ```

##### Array of function pointers
Example:Consider the case where different functions are called based on a value.
```c
enum TYPE{SQUARE, RECT, CIRCLE, POLYGON};

struct shape {
    float params[MAX];
    enum TYPE type;
};

void draw(struct shape* ps)
{
    switch(ps->type) 
    {
        case SQUARE:
            draw_square(ps); break;
        case RECT:
            draw_rect(ps); break;
        //...
    }
}
```

The same can be done using an array of function pointers instead:
```c
void (*fp[4])(struct shape* ps) = {&draw_square, &draw_rect, &draw_circle, &draw_poly};

typedef void (*fp)(struct shape* ps) drawfn;
drawfn fp[4] = {&draw_square, &draw_rec, &draw_circle, &draw_poly};

void draw(struct shape* ps) {
    (*fp[ps->type])(ps); //call the correct function
}
```

### Hash Table
Hash tables (hashmaps) combine linked list and arrays to provide an efficient data structure for storing dynamic data. Hash tables are commonly implemented as an array of linked lists (hash tables with chaining).

- Each data item is associated with a *key* that determines its location.
- *Hash functions* are used to generate an evenly distributed hash value.
- A *hash collision* is said to occur when two items have the same hash value.
- Items with the same hash keys are chained
- Retrieving an item is *O(1)* operation. 

#### Hash functions
- A hash function maps its input into a finite range: hash value, hash code.
- The hash value should ideally have uniform distribution.
- Other uses of hash functions: cryptography, caches (computers/internet), bloom filters etc.
- Hash function types:
    - Division type
    - Multiplication type
- Other ways to avoid collision: linear probing, double hashing. 

#### Example
```c
#define MAX_BUCKETS 1000
#define MULTIPLIER 31

struct wordrec
{
    char* word;
    unsigned long count;
    struct wordrec* next
};

//hash bucket
struct wordrec* table[MAX_LEN];

unsigned long hashstring(const char* str)
{
    unsigned long hash = 0;
    while (*str) {
        hash = hash * MULTIPLIER + *str;
        str++; 
    }
    return  hash % MAX_BUCKETS;
}

struct wordrec* lookup(const char* str, int create)
{
    struct wordrec* curr = NULL;
    unsigned long hash = hashstring(str);
    struct wordrec* wp = table[hash];
    for (curr = wp; cur != NULL; curr = cur->next)
        /*search*/;
    //not found:
    if (create)
        /*add to front*/
    return curr;
}
```


## Lecture 9: External Libraries, More data structures

### Using External Libraries

#### Symbols and Linkage
- External libraries provide a wealth  of functionality - example: C standard library
- Programs access libraries’ functions and variables via identifiers known as symbols
- Header file declarations/prototypes mapped to symbols at compile time
- Symbols are linked to definitions in external libraries during linking
- Our own program produces symbols, too 

##### Functions and variables as symbols
- Consider the simple hellp world program written below:
    ```c
    #include <stdio.h>

    const char msg[] = "Hello, world.";

    int main(void) {
        puts(msg);
        return 0;
    }
    ```
- variables and functions declared globally: `msg`, `main()`, `puts()`, others in `stdio.h`
- compile but not link:
    ```
    $ gcc -Wall -c hello.c -o hello.o
    ```
    - `-c`: compile, but do not link hello.c; result will compile the code into machine instructions but not make the program executable
    - addresses for lines of code and static and global variables not yet assigned
    - need to perform *link* step on hello.o (using `gcc` or `ld`) to assign memory to each symbol
    - linking resolves symbols defined elsewhere (like the C standard library) and makes the code executable.
- look at the symbols in the compiled file hello.o:
    ```
    $ nm hello.o
    ```
    - 'T' - (text) code; 'R' - read-only memory; 'U' - undefined symbol
    - addresses all zero before linking; symbols not allocated memory yet
    - undefined symbols are defined externally, resolved during linking
- After linking:
    ```
    $ gcc -Wall hello.o -o hello
    ```
    - memory allocated for defined symbols
    - undefined symbols located in external libraires
    - shared symbol (`puts`) not assigned memory until run time

#### Static vs. Dynamic Linkage
- Functions, global variables must be allocated memory before use
- Can allocate at compile time (static) or at run time (shared)
- Advantages/disadvantages to both
- Symbols in same file, other `.o` files, or static libraries (archives, `.a` files) -> static linkage
- Symbols in shared libraries (`.so` files) -> dynamic linkage
- `gcc` links against shared libraries by default, can force static linkage using `-static` flag 

##### Static linkage
- At link time, statically linked symbols added to executable
- Results in much larger executable file (static: 688K, synamic: 10K)
- Resulting executable does not depend on locating external library files at run time
- To use newer version of library, have to recompile

##### Dynamic linkage
- Dynamic linkage occurs at run time
- During compile, linker just looks for symbol in external shared libraries
- Share library symbols loaded as part of program startup (before `main`)
- Requires external library to define symbol exactly as expected from header file declaration
    - changing function in shared library can break your program
    - version information used to minimize this problem
    - reason why common libraries like `libc` rarely modify or remove functions, even broken ones like `gets()`

#### Linking External Libraries
- Programs linked against C standard library by default
- To link against library `libnamespec.so` or `libnamespec.a`, use compiler flag `-lnamespec` to link against library
- Library must be in libray path (standard library directories + directories specified using `-L directory` compiler flag)
- Use `-static` for force static linkage
- This is enough for static linkage; library code will be added to resulting executable

##### Loading shared libraries
- Shared library located during compile-time linkage, but needs to be located again during run-time loading
- Shared libraries located at run-time using linker library `ld.so`
- Whenever shared libraries on system change, need to run `ldconfig` to update links seen by `ld.so`
- During loading, symbols in dynamic library are allocated memory and loaded from shared library file 

##### Loading shared libraries on demand
- In Linux, can load symbols from shared libraries on demand using functions in `dlfcn.h`
- Open a shared library for loading:
    ```c
    void ∗ dlopen(const char ∗file, int mode);
    ```
    values for `mode`: combination of `RTLD_LAZY` (lazy loading of library), `RTLD_NOW` (load now), `RTLD_GLOBAL` (make symbols in library available to other libraries yet to be loaded), `RTLD_LOCAL` (symbols loaded are accessible only to your code)

- Get the address of a symbol loaded from the library:
    ```C
    void ∗ dlsym(void ∗ handle, const char ∗ symbol_name);
    ```
    handle from call to dlopen; returned address is pointer to variable or function identified by `symbol_name`

- Need to close shared library file handle after done with symbols in library:
    ```C
    int dlclose(void ∗ handle);
    ```

- These functions are not part of C standard library; need to link against library `libdl: -ldl` compiler flag 

#### Symbol Resolution Issues
- Symbols can be defined in multiple places. Suppose we define our own `puts()` function But, `puts()` defined in C standard library. When we call `puts()`, which one gets used? 
    - Our `puts()` gets used since ours is static, and `puts()` in C standard library not resolved until run-time
    - If statically linked against C standard library, linker finds two `puts()` definitions and aborts (multiple definitions not allowed) 

- How about if we define `puts()` in a shared library and attempt to use it within our programs?
    - Symbols resolved in order they are loaded
    - Suppose our library containing `puts()` is `libhello.so`, located in a standard library directory (like `/usr/lib`), and we compile our `hello.c` code against this library:
        ```
        $ gcc -g -Wall hello.c -lhello -o hello.o
        ```
    - Libraries specified using `-l` flag are loaded in order specified, and before C standard library 

### Creating libraries
- Libraries contain C code like any other program
- Static or shared libraries compiled from (un-linked) object files created using `gcc`
- Compiling a static library:
    - compile, but do not link source files:
        ```
        $ gcc -g -Wall -c infile.c -o outfile.o
        ```
    - collect compiled (unlinked) files into an archive:
        ```
        $ ar -rcs libname.a outfile1.o outfile2.o ...
        ```
#### Creating shared libraries
- Compile and do not link files using `gcc`:
    ```$ gcc -g -Wall -fPIC -c infile.c -o outfile.o```
- `-fPIC` option: create position-independent code, since code will be repositioned during loading
- Link files using `ld` to create a shared object (`.so`) file:
    ```
    $ ld -shared -soname libname.so -o libname.so.version -lc outfile1.o outfile2.o ...
    ```
- If necessary, add directory to `LD_LIBRARY_PATH` environment variable, so `ld.so` can find file when loading at run-time
- Configure `ld.so` for new (or changed) library:
    ```
    $ldconfig -v
    ```

### Data Structures
- Many data structures designed to support certain algorithms
- B-tree - generalized binary search tree, used for databases and file systems
- Priority queue - ordering data by “priority,” used for sorting, event simulation, and many other algorithms 

#### B-trees
- Binary search tree with variable number of children (at least t, up to 2t)
- Tree is balanced – all leaves at same level
- Node contains list of “keys” – divide range of elements in children 

##### Initializing a B-tree
- Initially, B-tree contains root node with no children (leaf node), no keys
- Note: root node exempt from minimum children requirement 

##### Inserting elements
- Insertion is complicated due to maximum number of keys
- At high level:
    1. traverse tree down to leaf node
    2. if leaf already full, split into two leaves:
        - move median key element into parent (splitting parent already full)
        - split remaining keys into two leaves (one with lower, one with higher elements)
    3. add element to sorted list of keys
- Can accomplish in one pass. splitting full parent nodes during traversal in step 1

##### Searching a B-tree
- Search like searching a binary search tree:
    1. start at root.
    2. if node empty, element not in tree
    3. search list of keys for element (using linear or binary search)
    4. if element in list, return element
    5. otherwise, element between keys, and repeat search on child node for that range
- Tree is balanced -> search takes O(log n) time

##### Deletion
- Deletion is complicated by minimum children restriction
- When traversing tree to find element, need to ensure child nodes to be traversed have enough keys
    - if adjacent child node has at least t keys, move separating key from parent to child and closest key in adjacent child to parent
    - if no adjacent child nodes have extra keys, merge child node with adjacent child
- When removing a key from a node with children, need to rearrange keys again
    - if child before or after removed key has enough keys, move closest key from child to parent
    - if neither child has enough keys, merge both children
    - if child not a leaf, have to repeat this process 

#### Priority Queues
- Abstract data structure ordering elements by priority
- Elements enqueued with priority, dequeued in order of highest priority
- Common implementations: heap or binary search tree
- Operations: insertion, peek/extract max-priority element, increase element priority

##### Heaps
- Heap - tree with heap-ordering property: priority(child) ≤ priority(parent)
- More sophisticated heaps exist – e.g. binomial heap, Fibonacci heap
- We’ll focus on simple binary heaps
- Usually implemented as an array with top element at beginning
- Can sort data using a heap – O(n log n) worst case in-place sort!

###### Extracting data
- Heap-ordering property -> maximum priority element at top of heap
- Can peek by looking at top element
- Can remove top element, move last element to top, and swap top element down with its children until it satisfies heap-ordering property:
    1. start at top
    2. find largest of element and left and right child; if element is largest, we are done
    3. otherwise, swap element with largest child and repeat with element in new position

###### Inserting data/ increasing priority
- Insert element at end of heap, set to lowest priority -∞
- Increase priority of element to real priority:
    1. start at element
    2. if new priority less than parent’s, we are done
    3. otherwise, swap element with parent and repeat 


## Lecture 10: C Standard Library

### <stdio.h>
#### Opening, closing files
```c
FILE* fopen(const char* filename, const char* mode);
```
- mode can be "r"(read), "w"(write), "a"(append)
- "b" can be appended for binary input/output (unnecessary in *nx)
- returns NULL on error

```c
FILE* freopen(const char* filename, const char* mode, FILE* stream);
```
- redirects the stream to the file
- returns NULL on error
- can be used in redirecting stdin, stdout, stderr

```c
int fflush(FILE* stream);
```
- flushes any unwritten data
- if stream is NULL flushes all output streams
- returns EOF on error

#### File operations
```c
int remove(const char* filename);
```
- removes the file from the file system
- returns non-zero on error (reasons: permission, existence)

```c
int rename(const char* oldname, const char* newname);
```
- renames file
- returns non-zero on error (reasons: permission, existence)

#### Temporary files
```c
FILE* tmpfile(void);
```
- creates a temporary file with mode "wb+"
- the file is removed automatically when program terminates

```c
char* tmpnam(char s[L_tmpnam]);
```
- creates a string that is not the name of an existing file.
- return reference to internal static array if s is NULL. Pupulate s otherwise
- generates a new name every call.

#### Rawa I/O
```c
size_t fread(void* ptr, size_t size, size_t nobj, FILE* stream);
```
- reads at most `nobj` items of size `size` from `stream` into `ptr`
- returns the number of items read
- `feof` and `ferror` must be used to test end of file

```c
size_t fwrite(const void* ptr, size_t size, size_t nobj, FILE* stream);
```
- write at most `nobj` items of size `size` from `ptr` onto `stream`
- returns number of objects written

#### File position
```c
int fseek(FILE* stream, long offset, int origin);
```
- sets file position in the stream. Subsequent read/write begins at this location
- origin can be `SEEK_SET`, `SEEK_CUR`, `SEEK_END`
- returns non-zero on error

```c
long ftell(FILE* stream);
```
- returns the current position within the file
- returns -1L on error

```c
int rewind(FILE* stream);
```
- sets the file pointer at the beginning
- equivalent to `fseek(stream, 0L, SEEK_SET);`

#### File errors
```c
void clearerr(FILE* stream);
```
- clears EOF and other error indicators on stream

```c
int feof(FILE* stream)
```
- return non-zero(TRUE) if end of file indicator is set for stream
- only way to test end of file for function such as `fwrite()`, `fread()`

```c
int ferror(FILE* stream)
```
- returns non-zero(TRUE) if any error indicator is set for stream


### <ctype.h>
#### Testing charactors

- `isalnum(c)`: `isalpha(c) || isdigit(c)`
- `incntrl(c)`: control characters
- `isdigit(c)`: 0 - 9
- `islower(c)`: 'a' - 'z'
- `isprint(c)`: printable character (includes space)
- `ispunct(c)`: punctuation
- `isspace(c)`: space, tab or new line
- `isupper(c)`: 'A' - 'Z' 


### <stdlib.h>

#### Memory functions
```c
void* memcpy(void* dst, const void* src, size_t n);
```
- copies `n` bytes from `src` to location `dst`
- returns a pointer to `dst`
- `src` and `dst` **cannot overlap**

```c
void* memmove(void* dst, const void* src, size_t n);
```
- behaves same as `memcpy()` function
- `src` and `dst` **can overlap**

```c
int memcmp(const void* cs, const void* ct, int n);
```
- compares first `n` bytes between `cs` and `ct`

```c
void* memset(void* dst, int c, int n);
```
- fills the first `n` bytes of `dst` with the value `c`
- returns a pointer to `dst`

#### Utility
```c
double atof(cosnt char* s);
int atoi(const char* s);
long atol(const char* s);
```
- converts character to float, integer and long respectively

```c
int rand();
```
- returns a pseudo-random number between 0 and `RAND_MAX`

```c
void srand(unsigned int seed);
```
- sets the seed for the pseudo-random generator

#### Exiting
```c
void abort(void);
```
- causes the program to terminate abnormally

```c
void exit(int status);
```
- causes normal program termination. The value `status` is returned to the operating system
- 0 `EXIT_SUCCESS` indicates successful termination. Any other value indicates failure (`EXIT_FAILURE`)

```c
void atexit(void(*fcn)(void));
```
- registers a function `fcn` to be called when the program terminates normally;
- returns non zero when registration cannot be made
- After `exit()` is called, the functions are called in reverse order of registration

```c
int system(const char* cmd);
```
- executes the command in string `cmd`
- if cmd is not null, the program executes the command and returns exit status returned by the command

#### Searching and sorting
```c
void* bsearch(const void* keys, const void* base, size_t n, size_t size,
              int (*cmp)(const void* keyval, const void* datum));
```
- searches `base[0]` through `base[n-1]` for `*key`
- function `cmp()` is used to perform comparison
- returns a pointer to the matching item if it exists and NULL otherwise

```c
void qsort(void* base, size_t n, size_t sz,
           int (*cmp)(const void*, const void*));
```
- sorts `base[0]` through `base[n-1]` in ascending/descending order
- function `cmp()` is used to perform comparison


### <assert.h>
#### Diagnostics
```c
void assert(int expression);
```
- used to check for invariants/code consistency during debugging
- does nothing when expression is true
- prints and error message indicating, expression, filename and line number

Alternative ways to print filename and line number during execution is to use: `__FILE__, __LINE__` macros


### <stdarg.h>
#### Variable argument lists
- functions can have variable number of arguments
- the data type of the argument can be different for each argument
- at least one mandatory argument is required
- Declaration:
    ```c    
    int printf(char* fmt, ...); //fmt is last named argument
    ```

```c
va_list ap
```
- `ap` defines an iterator that will point to the variable argument
- before using, it has to be initialized using `va_start`

```c
va_start(va_list ap, lastarg);
```
- `ap` lastarg refers to the **name** of the last named argument
- va_start is a macro

```c
va_arg(va_list ap, type);
```
- each call of `va_arg` points ap to the next argument
- type has to be inferred from the fixed argument (e.g. printf) or determined based on previous argument(s)

```c
va_end(va_list ap);
```
- must be called before the function is exited

**example**:
```c
int sum(int num,...)
{
    va_list ap;
    int total = 0;
    va_start(ap, num);
    while (num > 0) {
        total += va_arg(ap, int);
        num--;
    }
    va_end(ap);
    return total;
}

int suma = sum(4, 1, 2, 3, 4); //called with five args
```


### <time.h>
`time_t`, `clock_t`, `struct tm` are data types associated with time

```c
struct tm {
    int tm_sec; //seconds
    int tm_min; //minutes
    int tm_hour; //hour since midnight (0, 23)
    int tm_mday; //day of the month (1, 31)
    int tm_mon; //month
    int tm_year; //years since 1900
    int tm_wday; //day since sunday (0, 6)
    int tm_yday; //day dince Jan 1 (0, 365)
    int tm_isdst; //DST flag
};
```

```c
clock_t clock();
```
- returns processor time used since beginning of program
- divide by `CLOCKS_PER_SEC` to get time in seconds

```c
time_t time(time_t* tp);
```
- returns current time (seconds since Jan 1 1970)
- if `tp` is not NULL, also populates tp

```c
double difftime(time_t t1, time_t t2);
```
- returns difference in seconds

```c
time_t mktime(struct tm* tp);
```
- converts the structure to a time_t object
- returns -1 if conversion is not possible

```c
char* asctime(const struct tm* tp);
```
- returns string representation of the form "Sun Jan 3 15:14:13 1988"
- returns static reference (can be overwritten by other calls)

```c
struct tm* localtime(const time_t* tp);
```
- converts **calendar time** to local time"

```c
char* ctime(const time_t* tp);
```
- converts **calendar time** to string representation of local time"
- equivalent to `sctime(localtime(tp))`

```c
size_t strftime(char* s, size_t smax, const char* fmt, const struct tm* tp);
```
- returns time in the desired format
- does not write more than `smax` characters into the string `s`
    - `%a` - abbreviated weekday name
    - `%A` - full weekday name
    - `%b` - abbreviated month name
    - `%B` - full month name
    - `%d` - day of the month
    - `%H` - hour (0-23)
    - `%I` - hour (0-12)
    - `%m` - month
    - `%M` - minute
    - `%p` - AM/PM
    - `%S` - second 


## Lecture 11: Memory Management

### Dynamic Memory Allocation
- Memory allocated during runtime
- Request to map memory using `mmap()` function (in `<sys/mman.h>`)
- Virtual memory can be returned to OS using `munmap()`
- Virtual memory either backed by a file/device or by demand-zero memory:
    - all bits initialized to zero
    - not stored on disk
    - used for stack, heap, uninitialized (at compile time) globals 

#### Designing the `malloc()` function

##### Mapping memory
- Mapping memory
    ```c
    void *mmap(void* start, size_t length, int prot, int flags, int fd, off_t offset);
    ```
    - asks OS to map virtual memory of specifies length, using specified physical memory (file or demand-zero)
    - `fd` is file descriptor (integer referring to a file, not a file stream) for physical memory (i.e. file) to load into memory
    - for demand-zero, including the heap, use `MMAP_ANON` flag
    - `start` - suggested starting address of mapped memory, usually NULL

- Unmap memory:
    ```c
    int munmap(void* start, size_t length);
    ```

##### The heap
- Heap - private section of virtual memory (demand-zero) used for dynamic allocation
- Starts empty, zero-sized
- `brk` - OS pointer to top of heap, moves upwards as heap grows
- To resize heap, can use `sbrk()` function:
    ```c
    void* sbrk(int inc); //returns old value of brk_ptr
    ```
- Functions like `malloc()` and `new` (in C++) manage heap, mapping memory as needed
- Dynamic memory allocators divide heap into blocks

##### Requirements
- Must be able to allocate, free memory in any order
- Auxiliary data structure must be on heap
- Allocated memory cannot be moved
- Attempt to minimize fragmentation 

##### Fragmentation
- Two types - internal and external
- Internal - block size larger than allocated variable in block
- External - free blocks spread out on heap
- Minimize external fragmentation by preferring fewer larger free blocks

##### Design choices
- **Data structure to track blocks**
    - Implicit free list: no data structure required
    - Explicit free list: heap divided into fixed-size blocks; maintain a linked list of blocks
        - allocating memory: remove allocated block from list
        - freeing memory: add block back to free list
    - Linked list iteration in linear time
    - Segregated free list: multiple linked lists for blocks of different sizes
    - Explicit lists stored within blocks (pointers in payload section of free blocks)

- **Algorithm for positioning a new allocation**
    - Block must be large enough for allocation
    - First fit: start at beginning of list, use first block
    - Next fit: start at end of last search, use next block
    - Best fit: examines entire free list, uses smallest block
    - First fit and next fit can fragment beginning of heap, but relatively fast
    - Best fit can have best memory utilization, but at cost of examining entire list 

- **Splitting/joining free blocks**
    - At allocation, can use entire free block, or part of it, splitting the block in two
    - Splitting reduces internal fragmentation, but more complicated to implement
    - Similarly, can join adjacent free blocks during (or after) freeing to reduce external fragmentation
    - To join (coalesce) blocks, need to know address of adjacent blocks
    - Footer with pointer to head of block – enable successive block to find address of previous block 

#### A Simple Implementation of `malloc()`
- Payload 8 byte alignment; 16 byte minimum block size
- Implicit free list
- Coalescence with boundary tags; only split if remaining block space ≥ 16 bytes

##### Initialization
1. Allocate 16 bytes for padding, prologue, epilogue
2. Insert 4 byte padding and prologue block (header + footer only, no payload) at beginning
3. Add an epilogue block (header only, no payload)
4. Insert a new free chunk (extend the heap) 

##### Allocating data
1. Compute total block size (header + payload + footer)
2. Locate free block large enough to hold data (using first or next fit for speed)
3. If block found, add data to block and split if padding ≥ 16 bytes
4. Otherwise, insert a new free chunk (extending the heap), and add data to that
5. If could not add large enough free chunk, out of memory

##### Freeing data
1. Mark block as free (bit flag in header/footer)
2. If previous block free, coalesce with previous block (update size of previous)
3. If next block free, coalesce with next block (update size)

##### Explicit free list
- Maintain pointer to head, tail of free list (not in address order)
- When freeing, add free block to end of list; set pointer to next, previous block in free list at beginning of payload section of block
- When allocating, iterate through free list, remove from list when allocating block
- For segregated free lists, allocator maintains array of lists for different sized free blocks 


#### A Real-World Implementation of `malloc()`
- Used in GNU libc version of `malloc()`
- Chunks implemented as in segregated free list, with pointers to previous/next chunks in free list in payload of free blocks
- Lists segregated into bins according to size; bin sizes spaced logarithmically
- Placement done in best-fit order
- Deferred coalescing and splitting performed to minimize overhead 

### Using `malloc()`
- Minimize overhead - use fewer, larger allocations
- Minimize fragmentation - reuse memory allocations as much as possible
- Growing memory - using `realloc()` can reduce fragmentation
- Repeated allocation and freeing of variables can lead to poor performance from unnecessary splitting/coalescing (depending on implementation of `malloc()`) 

#### Using `valgrind` to detect memory leaks
- A simple tutorial: http://cs.ecs.baylor.edu/~donahoo/tools/valgrind/
- `valgrind` program provides several performance tools, including memcheck:
    ```
    $ valgrind --tool=memcheck --leak-check=yes program.o
    ```
- memcheck runs program using virtual machine and tracks memory leaks
- Does not trigger on out-of-bounds index errors for arrays on the stack

#### Other `valgrind` tools
- Can use to profile code to measure memory usage, identify execution bottlenecks
- valgrind tools (use name in `-tool=` flag):
    - `cachegrind` – counts cache misses for each line of code
    - `callgrind` – counts function calls and costs in program
    - `massif` – tracks overall heap usage 

### Garbage Collection
- C implements no garbage collector
- Memory not freed remains in virtual memory until program terminates
- Other languages like Java implement garbage collectors to free unreferenced memory
- When is memory unreferenced? 
    - Pointer(s) to memory no longer exist
    - Tricky when pointers on heap or references are circular (think of circular linked lists)
    - Pointers can be masked as data in memory; garbage collector may free data that is still referenced (or not free unreferenced data) 

#### Garbage collection and memory allocation
- Program relies on garbage collector to free memory
- Garbage collector calls `free()`
- `malloc()` may call garbage collector if memory allocation above a threshold 

#### Mark-and-sweep collector 
- Simple tracing garbage collector
- Starts with list of known in-use memory (e.g. the stack)
- Mark: trace all pointers, marking data on the heap as it goes
- Sweep: traverse entire heap, freeing unmarked data
- Requires two complete traversals of memory, takes a lot of time
- Implementation available at http://www.hpl.hp.com/personal/Hans_Boehm/gc/

#### Copying collector
- Uses a duplicate heap; copies live objects during traversal to the duplicate heap (the to-space)
- Updates pointers to point to new object locations in duplicate heap
- After copying phase, entire old heap (the from-space) is freed
- Code can only use half the heap

##### Cheney’s (not Dick’s) algorithm
- Method for copying garbage collector using breadth-first-search of memory graph
- Start with empty to-space
- Examine stack; move pointers to to-space and update pointers to to-space references
- Items in from-space replaced with pointers to copy in to-space
- Starting at beginning of to-space, iterate through memory, doing the same as pointers are encountered
- Can accomplish in one pass 


## Lecture 12: Multithreading and Concurrency

### Multithreaded programming

#### Preliminaries: Parallel computing
- Parallerlism: Multiple computations are done simultaneously
    - Instruction level (pipelining)
    - Data parallelism (SMD)
    - Task parallelism (embarrasingly parallel)
- Concurrency: Multiple computations that **may** be done in parallel
- Concurrency v.s. Parallelism

#### Process v.s. Threads
- Process: An instance of a program that is being executed in its **own** address space. In POSIX systems, each process mantains its own heap, stack, registers, file descriptors etc.

    Communication:
    - Shared memory
    - Network
    - Pipes, Queues

- Thread: A light weighted process that shares its address space with others. In POSIX systems, each thread maintains the bare essentials: registers, stack, signals.

    Communication:
    - shared address space

#### Multithreaded concurrency
**Serial execution**
- All our programmes so far has a single thread of execution: main thread
- Program exits when the main thread exits

**Multithreaded**
- Program is organized as multiple and concurrent threads of execution
- The main thread *spawns* multiple threads
- The thread **may** communicate with one another
- Advantages:
    - Improves performance
    - Improves responsiveness
    - Improves utilization
    - less overhead compared to multiple process

#### Multithreaded programming
Even in C, multithreaded programming may be accomplished in several ways
- Pthreads: POSIX C library
- OpenMP
- Intel threading building blocks
- Clik (from CASIL!)
- Grand central despatch
- CUDA (GPU)
- OpenCL (GPU/CPU)

#### Not all code can be made parallel
```c
//parallelizable
float params[10];
for(int i = 0; i < 10; i++)
    do_something(params[i]);
```
```c
//not parallelizable
float params[10];
float prev = 0;
for(int i = 0; i < 10; i++)
    prev = complicated(params[i], prev);
```

#### Not all multi-threaded code is safe
```c
int balance = 500;
void deposit(int sum) {
    int currbalance = balance; //read balance
    ...
    currbalance += sum;
    balance = currbalance; //write balance
}

void withdraw(int sum) {
    int currbalance = balance; //read balance
    if(currbalance > 0)
        currbalance -= sum;
    balance = currbalance; //write balance
}

...
deposit(100); //thread 1
...
withdraw(50); //thread 2
...
withdraw(100); //thread 3
```
- minimize use of global/static memory
- Scenario: T1(read), T2(read, write), T1(write), balance = 600
- Scenario: T2(read), T1(read, write), T2(write), balance = 450

### Pthread

#### API
- Thread management: creating, joining, attributes: `pthread_`
- Mutexes: create, destroy mutexes: `pthread_mutex_`
- Condition variables: create,destroy,wait,signal: `pthread_cond_`
- Synchronization: read/write locks and barriers: `pthread_rwlock_, pthread_barrier_`
- Including:
    ```c
    #include <pthread.h>
    ```
- Compiling:
    ```
    gcc -Wall -O0 -o <output> file.c -pthread
    ```

##### Creating threads
```c
int pthread_create(pthread_t* thread, const pthread_attr_t* attr, void*(*start_routine)(void*), void* arg);
```
- creates a new thread with the attributes specified by `attr`.
- Default attributes are used if `attr` is NULL.
- On success, stores the thread it into `thread`
- calls function `start_routine(arg)` on a separate thread of execution.
- returns zero on success, non-zero on error. 

```c
void pthread_exit(void* value_ptr);
```
- called implicitly when thread function exits
- analogous to exit()

**Example**
```c
#include <pthread.h>
#include <stdio.h>
#define NUM_THREADS 5

void* PrintHello(void* threadid) {
    long id;
    tid = (long)threadid;
    printf("Hello World! It's me, thread #%ld !\n", tid);
    pthread_exit(NULL);
}

int main(int argc, char* argv[]) {
    pthread_t threads[NUM_THREADS];
    int rc;
    long t;
    for(t = 0; t < NUM_THREADS; t++) {
        printf("In main: creating thread %ld\n", t);
        rc = pthread_create(&threads[1], NULL, PrintHello, (void*)t);
        if(rc) {
            printf("ERROR; return code from pthread_create() is %d\n", rc);
            exit(-1);
        }
    }
    pthread_exit(NULL);
}
```

##### Synchronization: joining
```c
int pthread_join(pthread_t thread, void** value_ptr);
```
- `pthread_join()` blocks the calling thread until the specified thread terminates
- If `value_ptr` is not null; it will contain the return status of the called thread
- Other ways to synchronize: mutex, condition variables

**Example**
```c
#define NELEMENTS 5000
#define BLK_SIZE 1000
#define NTHREADS (NELEMENTS/BLK_SIZE)

int main(int argc, char* argv[])
{
    pthread_t thread[NUM_THREADS];
    pthread_attr_t attr;
    int rc; long t; void* status;

    //initialize and set thread attribute
    pthread_attr_init(&attr);
    pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_JOINABLE);

    for(t=0; t<NUM_THREADS; t++) {
        printf("Main: creating thread %ld\n", t);
        rc = pthread_join(thread[t], &status);
        if (rc) {
            printf("ERROR; return code from pthread_join() is %d\n", rc);
            exit(-1);
        }
    }
    printf("Main: program completed, Exiting.\n");
}
```

#### Mutex
- Mutex (mutual exclusion) acts as a "lock" protecting access to the shared resource
- Only one thread can "own" the mutex at a time. Threads must take turns to lock the mutex

```c
int pthread_mutex_destroy(pthread_mutex_t* mutex);
int pthread_mutex_init(pthread_mutex_t* mutex, const pthread_mutexattr_t* attr);

thread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
```
- `pthread_mutex_init()` initializes a mutex. If attributes are NULL, default attributes are used.
- The macro `PTHREAD_MUTEX_INITIALIZER` can be used to initialize static mutexes.
- `pthread_mutex_destroy()` destroys the mutex.
- Both function return 0 on success, non zero on error.

```c
int pthread_mutex_lock(pthread_mutex_t∗ mutex);
int pthread_mutex_trylock(pthread_mutex_t∗ mutex);
int pthread_mutex_unlock(pthread_mutex_t∗ mutex); 
```
- `pthread_mutex_lock()` locks the given mutex. If the mutex is locked, the function is blocked until it becomes available.
- `pthread_mutex_trylock()` is the non-blocking version. If the mutex is currently locked the call will return immediately.
- `pthread_mutex_unlock()` unlocks the mutex.

**Previous example using mutex**
```c
int balance = 500;
pthread_mutex_t mutexbalance = PTHREAD_MUTEX_INITIALIZER;

void deposit(int sum) {
    pthread_mutex_lock(&mutexbalance);
    {
    int currbalance = balance; //read balance
    ...
    currbalance += sum;
    balance = currbalance; //write balance
    }
    pthread_mutex_unlock(&mutexbalance);
}

void withdraw(int sum) {
    pthread_mutex_lock(&mutexbalance);
    {
    int currbalance = balance; //read balance
    if(currbalance > 0)
        currbalance -= sum;
    balance = currbalance; //write balance
    }
    pthread_mutex_unlock(&mutexbalance);
}

...
deposit(100); //thread 1
...
withdraw(50); //thread 2
...
withdraw(100); //thread 3
```
- Scenario: T1(read, write), T2(read, write), balance = 550
- Scenario: T2(read), T1(read, write), T2(write), balance = 550


#### Condition variables
Sometimes locking or unlocking is based on a run-time condition. Without condition variables, program would have to poll the variable/condition continuously.

Consumer:
1. lock mutex on global item variable
2. wait for (item>0) signal from producer (mutex unlocked automatically).
3. wake up when signalled (mutex locked again automatically), unlock mutex and proceed.

Producer:
1. produce something
2. lock global item variable, update item
3. signal waiting (threads)
4. unlock mutex

```c
int pthread_cond_destroy(pthread_cond_t* cond);
int pthread_cond_init(pthread_cond_t* cond, const pthread_condattr_t* attr);
pthread_cond_t cond = PTHREAD_COND_INITIALIZER;
```
-  `pthread_cond_init()` initialized the condition variable. If `attr` is NULL, default attributes are sed.
- `pthread_cond_destroy()` will destroy (uninitialize) the condition variable.
- destroying a condition variable upon which other threads are currently blocked results in undefined behavior.
- macro `PTHREAD_COND_INITIALIZER` can be used to initialize condition variables. No error checks are performed.
- Both function return 0 on success and non-zero otherwise.

```c
int pthread_cond_wait(pthread_cond_t* cond, pthread_mutex_t* mutex);
```
- blocks on a condition variable.
- must be called with the mutex already locked otherwise behavior undefined.
- automatically releases mutex
- upon successful return, the mutex will be automatically locked again. 

```c
int pthread_cond_broadcast(pthread_cond_t* cond);
int pthread_cond_signal(pthread_cond_t* cond);
```
- unblocks threads waiting on a condition variable.
- `pthread_cond_broadcast()` unlocks **all** threads that are waiting.
- `pthread_cond_signal()` unlocks **one of** the threads that are waiting.
- both return 0 on success, non zero otherwise. 

**Example**
```c
#include <pthread.h>

pthread_cond_t cond_recv = PTHREAD_COND_INITIALIZER;
pthread_cond_t cond_send = PTHREAD_COND_INITIALIZER;
pthread_mutex_t cond_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t count_mutex = PTHREAD_MUTEX_INITIALIZER;

int full = 0;
int count = 0;

void* produce(void*)
{
    while(1) {
        pthread_mutex_lock(&cond_mutex);
        while(full) {
            pthread_cond_wait(&cond_recv, &cond_mutex);
        }

        pthread_mutex_unlock(&cond_mutex);
        pthread_mutex_lock(&count_mutex);
        count++; full = 1;
        printf("produced(%d):%d\n", pthread_self(), count);
        pthread_cond_broadcast(&cond_send);
        pthread_mutex_unlock(&count_mutex);
        if(count >= 10) break;
    }
}

void* consume(void*)
{
    while(1) {
        pthread_mutex_lock(&cond_mutex);
        while(!full) {
            pthread_cond_wait(&cond_send, &cond_mutex);
        }

        pthread_mutex_unlock(&cond_mutex);
        pthread_mutex_lock(&count_mutex);
        full = 0;
        printf("consumed(%ld):%d\n", pthread_self(), count);
        pthread_cond_broadcast(&cond_recv);
        pthread_mutex_unlock(&count_mutex);
        if(count >= 10) break;
    }
}

int main()
{
    pthread_t cons_thread, prod_thread;
    pthread_create(&prod_thread, NULL, produce, NULL);
    pthread_create(&cons_thread, NULL, consume, NULL);

    pthread_join(cons_thread, NULL);
    pthread_join(cons_thread, NULL);
    return 0;
}
```


## Lecture 13: Multithreaded Programming, Sockets and Asynchronous I/O

### Multithreaded Programming
- OS implements scheduler - determines which threads execute when
- Scheduling may execute threads in arbitary order
- Without proper synchronization, code can execute non-deterministically
- Suppose we have teo threads: 1 reads a variable, 2 modifies that variable
- Scheduler may execute 1 then 2, or 2 then 1
- Non-determinism creates a *race condition* - where the behaviour/result depends on the order of execution

#### Race Conditions
- Race conditions occur when multiple threads share a variable, without proper synchronization
- Synchronization uses special variables, like a mutex, to ensure order of execition is correct
- **Example 1**: thread T1 needs to do something before thread T2:
    - condition variable forces thread T2 to wait for thread T1
    - producer-consumer model program
- **Example 2**: two threads both need to access a variable and modify it based on its value
    - surround access and modification with a mutex
    - mutex groups operations together to make them *atomic* - theated as one unit

##### Race conditions in assembly
Consider the following program `race.c`:
```c
unsigned int cnt = 0;

void* count(void* arg)
{//thread body
    int i;
    for (i=0; i<10000000, i++)
        cnt++;
    return NULL;
}

int main(void)
{
    pthread_t tids[4];
    int i;
    for (i=0; i<4; i++)
        pthread_create(&tids[i], NULL, count, NULL);
    for (i=0; i<4; i++)
        pthread_join(&tids[i], NULL);
    printf("cnt = %u\n", cnt);
    return 0;
}
```
Ideally, cnt = 400000000. However, running the code gives non-determined values

- C is not designed for multithreading
- No notion of atomic operations in C
- Increment `cnt++;` maps to three assembly operations:
    1. load `cnt` into a register
    2. increment value in register
    3. save new register value as new `cnt`
- So if thread interrupted in the middle, race condition happens

fixing the code
```c
pthread_mutex_t mutex;
unsigned int cnt = 0;

void* count(void* arg)
{//thread body
    int i;
    for (i=0; i<10000000, i++) {}
        pthread_mutex_lock(&mutex);
        cnt++;
        pthread_mutex_unlock(&mutex);
    }
    return NULL;
}

int main(void)
{
    pthread_t tids[4];
    int i;
    pthread_mutex_init(&mutex, NULL);
    for (i=0; i<4; i++)
        pthread_create(&tids[i], NULL, count, NULL);
    for (i=0; i<4; i++)
        pthread_join(&tids[i], NULL);
    pthread_mutex_destroy(&mutex);
    printf("cnt = %u\n", cnt);
    return 0;
}
```

- Note that the new code functions correctly, but is much slower
- C statements are not atomic - threads may be interrupted at assembly level, in the middle of a C statement
- Atomic operations like mutex locking must be specifies as atomic using special assembly instructions
- Ensure that all statements accessing/modifying shared variables are synchronized

#### Semaphores
- Semaphore - special nonnegative integer variable `s`, initially 1, which implements two atomic operations:
    - `P(s)` - wait until `s > 0`, decrement `s` and return
    - `V(s)` - increment `s` by 1, unblocking calls `V(s)`
- Implemented in `<semophore.h>`, part of library `rt`, not `pthread`

##### Using semaphores
- Initialize semapore to `value`:
    ```c
    int sem_init(sem_t* sem, int pshared, unsigned int vaue);
    ```
- Destroy semaphore:
    ```c
    int sem_destroy(sem_t* sem);
    ```
- Wait to lock, blocking:
    ```c
    int sem_wait(sem_t* sem);
    ```
- Try to lock, returning immediately (0 if now locked, -1 otherwise):
    ```c
    int sem_trywait(sem_t* sem);
    ```
- Increment semaphore, unblocking a waiting thread:
    ```c
    int sem_post(sem_t* sem);
    ```

##### Producer and consumer revisited
- Use a semaphore to track available slots in shared buffer
- Use a semaphore to track items in shared buffer
- Use a semaphore/mutex to make buffer operations synchronous

```c
#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>

sem_t mutex, slots, items

#define SLOTS 2
#define ITEMS 10

void* produce(void* arg)
{
    int i;
    for (i=0; i<ITEMS; i++) {
        sem_wait(&slots);
        sem_wait(&mutex);
        printf("produced(%ld):%d\n", pthread_self(), i+1);
        sem_post(&mutex);
        sem_post(&items);
    }
    return NULL;
}

void* consume(void* arg)
{
    int i;
    for (i=0; i<ITEMS; i++) {
        sem_wait(&items);
        sem_wait(&mutex);
        printf("consumed(%ld):%d\n", pthread_self(), i+1);
        sem_post(&mutex);
        sem_post(&slots);
    }
    return NULL;
}

int main()
{
    pthread_t tcons, tpro;

    sem_init(&mutex, 0, 1);
    sem_init(&slots, 0, SLOTS);
    sem_init(&items, 0, 0);

    pthread_create(&tcons, NULL, consume, NULL);
    pthread_create(&tpro, NULL, produce, NULL);
    pthread_join(tcons, NULL);
    pthread_join(tpro, NULL);

    sem_destroy(&mutex);
    sem_destroy(&slots);
    sem_destroy(&items);
    return 0;
}
```

##### Other chanllenges
- Synchronization object help solve race conditions
- Improper use can cause other problems
- Some common issues
    - thread safety and reentrant functions
    - deadlock
    - starvation

#### Thread Safety, Deadlock, and Starvation
- Function is *thread safe* if it always behaves correctly when called from multiple concurrent threads
- Unsafe functions fall in several categories:
    - accesses/modifies unsynchronized shared variables
    - function that maintain state using static variables - like `rand()`, `strtok()`
    - functions that return pointers to static memory - like `gethostbyname()`
    - functions that call unsafe functions may be unsafe

##### Reentrant functions
- Reentrant function - does not reference any shared data when used by multiple threads
- All reentrant functions are thread-safe
- Reentrant versions of many unsafe C standard library functions exist:
    ```c
    //Unsafe function       Reentrant version
    rand();                 rand_r();
    strtok();               strtok_r();
    asctime();              asctime_r();
    ctime();                ctime_r();
    gethostbyaddr();        gethostbyaddr_r();
    gethostbyname();        gethostbyname_r();
    inet_ntoa();            (none);
    localtime();            localtime_r();
    ```
##### Thread safety
To make your code thread-safe:
- Use synchronization objects around shared variables
- Use reentrant functions
- Use synchronization around functions returning pointers to shared memory (*lock-and-copy*):
    1. lock mutex for function
    2. call unsafe function
    3. dynamically allocate memory for result; (deep) copy result into new memory
    4. unlock mutex

##### Deadlock
- Deadlock - happens when every thread is waiting on another thread to unblock
- Usually caused by improper ordering of synchronization objects
- Tricky bug to locate and reproduce, since schedule-dependent
- Can visualize using a progress graph - trace progress of threads in terms of synchronization objects
- Defeating deadlock extremely difficult in general
- When using only mutexes, can use the "mutex lock ordering rule" to avoid deadlock scenarios:

*A program is deadlock-free if, for each pair of mutexes (s, t) in the program, each thread that uses both s and t simultaneously locks them in the same order.*

##### Starvation and priority inversion
- Starvation similar to deadlock
- Scheduler never allocates resources (e.g. CPU time) for a thread to complete its task
- Happens during priority inversion
    - example: highest priority thread T1 waiting for low priority thread T2 to finish using a resource, while thread T3, which has higher priority than T2, is allowed to run indefinitely
    - thread T1 is considered to be in starvation

### Sockets and Asynchronous I/O

#### Sockets
- *Socket* - abstraction to enable communication across a network in a manner similar to file I/O
- Uses header `<sys/socket.h>` (extension of C standard library)
- Network I/O, due to latency, usually implemented asynchronously, using multithreading
- Sockets uses client/server model of establishing connections

##### Creating a socket
- Create a socket, getting the file descriptor for that socket:
    ```c
    int socket(int domain, int type, int protocol);
    ```
    - `domain` - use constant `AF_INET`, so we're using the internet; might also use `AF_INET6` for IPv6 addresses
    - `type` - use constant `SOCK_STREAM` for connection-based protocols like TCP/IP; use `SOCK_DGRAM` for connectionless datagram protocols like UDP (focus on the former)
    - `protocol` - specify 0 to use default protocol for the socket type (e.g. TCP)
    - returns nonnegative integer for file descriptor, or -1 if couldn't create socket

- Don't forget to close the file descriptor when you're done

##### Connecting to a server
- Using created sicket, we connect to server using:
    ```c
    int connect(int fd, struct socksddr* addr, int addr_len);
    ```
    - `fd` - the socket's file descriptor
    - `addr` - the address and port of the server to connect to; for internet addresses, cast data of type `struct sockaddr_in`, which has the following members:
        - `sin_family` - address family; always `AF_INET`
        - `sin_port` - port in network byte order (use `htons()` to convert to network byte order)
        - `sin_addr.s_addr` - IP address in network byte order order (use `hton1()` to convert to network byte order)
    - `addr_len` - size of `sockaddr_in` structure
    - return 0 if successful

##### Associate server socket with a port
- Using created socket, we bind to the port using:
    ```c
    int bind(int fd, struct sockaddr* addr, int addr_len);
    ```
    - `fd`, `addr`, `addr_len` - same as for `connect()`
    - note that address should be IP address of desired interface (e.g. `eth0`) on local machine
    - ensure that port for server is not taken (or you may get "address already in use" errors)
    - return 0 if socket successfully bound to port

##### Listening for clients
- Using the bound socket, start listening:
    ```c
    int listen(int fd, int backlog);
    ```
    - `fd` - bound socket file desciptor
    - `backlog` - length of queue for pending TCP/IP connections; normally set to a large number, like 1024
    - returns 0 if successful

##### Accepting a client's connection
- Wait for a client's connection request (may already be queued):
    ```c
    int accept(int fd, struct sockaddr* addr, int* addr_len);
    ```
    - `fd` - socket's file descriptor
    - `addr` - pointer to structure to be filled with client address info (can be NULL)
    - `addr_len` - pointer to int that specifies length of structure pointed to by `addr`; on output, specifies the length of the stored address (stored address may be truncated if bigger than supplied structure)
    - returns (nonnegative) file descriptor for connected client socket if successful

##### Reading and writing with sockets
- Send data using the following functions:
    ```c
    int write(int fd, const void* buf, size_t len);
    int send(int fd, const void* buf, size_t len, int flags);
    ```
- Receive data using the following functions
    ```c
    int read(int fd, void* buf, size_t, len);
    int recv(int fd, void* buf, size_t len, int flags);
    ```
    - `fd` - socket's file desciptor 
    - `buf` - buffer of data to read or write
    - `len` - length to buffer in bytes
    - `flags` - special flags; we'll use 0
    - all these return the number of bytes read/written (if successful)

#### Asynchronous I/O
- Up to now, all I/O has been synchronous - functions do not return until operation has been performed
- MUltithreading allows us to read/write a file or socket without blocking our main program code (just put I/O functions in a separate thread)
- Multiplexed I/O - use `select()` or `poll()` with multiple file descriptors

##### I/O multiplexing with `select()`
- To check if multiple files/sockets have data to read/write/etc: (`#include <sys/select.h>`)
    ```c
    int select(int nfds, fd_set* readfds, fd_set* errorfds, struct timeval* timeout);
    ```
    - `nfds` - specifies the total range of file descriptors to be tested (0 up to `nfds-1`)
    - `readfds`, `writefds`, `errorfds` - if not NULL, pointer to set of file descriptors to be tested for being ready to read, write or having an error; on output, set will contain a list of only those file desciptors that are ready
    - `timeout` - if no file descriptors are ready immediately, maximum time to wait for a file desriptor to be ready
    - returns the total number of set file descriptor bits in all the sets
- Note that `select()` is a blocking function

- `fd_set` - a mask for file descriptors; bits are set ("1") if in the set, or unset ("0") otherwise
- Use the following functions to set up the structure:
    - `FD_ZERO(&fdset)` - initialize the set to have bits unset for all file descriptors
    - `FD_SET(fd, &fdset)` - set the bit for file descriptor fd in the set
    - `FD_CLR(fd, &fdset)` - clear the bit for file descriptor fd in the set
    - `FD_ISSET(fd, &fdset)` - returns nonzero if bit for file descriptor fd is set in the set

##### I/O multiplexing using `poll()`
- Similar to `select()`, but specifies file descriptors differentlt: (`#include <poll.h>`)
    ```c
    int poll(struct pollfd fds[], nfds_t nfds, int timeout);
    ```
    - `fds` – an array of `pollfd` structures, whose members `fd`, `events`, and `revents`, are the file descriptor, events to check (OR-ed combination of flags like `POLLIN`, `POLLOUT`, `POLLERR`, `POLLHUP`), and result of polling with that file descriptor for those events, respectively
    - `nfds` – number of structures in the array
    - `timeout` – number of milliseconds to wait; use 0 to return immediately, or −1 to block indefinitely 



## Lecture 14: Linux Inter Process Communication

### Inter process communication
- Each process has its own address space. Therefore, individual processes cannot communicate unlike threads
- Interprocess communication: Linux/Unix provides several ways to allow communications
    - signal
    - pipes
    - FIFO queues
    - shared memory
    - semaphores
    - sockets

#### Signals
`<signals.h>`
- Unix/Linux allows us to handle exceptions that arise during execution (e.g. interrupt, loating point error, segmentation fault etc.)
- A process receives a *signal* when such a condition occurs
```c
void (*signal(int sig, void(*handler)(int)))(int);
```
- determines how subsequent signals will be handled
- pre-defined behavior: `SIG_DFL` (default), `SIG_IGN` (ignore)
- returns the previous handler

Valid signals:
- `SIGABRT`: abnormal termination
- `SIGFPE`: floating point error
- `SIGILL`: illegal instruction
- `SIGINT`: interrupt
- `SIGSEGV`: segmentation fault
- `SIGTERM`: termination request
- `SIGBUS`: bus error
- `SIGQUIT`: quit

The two signals `SIGSTOP`, `SIGKILL` cannot be handled

```c
int raise(int sig);
```
can be used to send signal `sig` to the program
- Ther can be race conditions
- signal handler itself can be interrupted
- use of non-reentrant functions unsafe
- `sigprocmask` can be used to prevent interruptions
- handler is **reset** each time it is called

**Example**
```c
#include <stdio.h>

void sigproc() {
    signal(SIGINT, sigproc);
    printf("you have pressed ctrl-c \n");
}

void quitproc() {
    printf("ctrl-\\ pressed to quit");
    exit(0); //normall exit status
}

main() {
    signal(SIGINT, sigproc);
    signal(SIGQUIT, quitproc);
    printf("ctrl-c disabled, use ctrl-\\ to quit\n");
    for(;;); //infinite loop
}
```

#### Fork
```c
pid_t fork(void);
```
- `fork()` is a system call to create a new **process**
- In the child process, it returns 0
- In the parent process, it returns the PID (process id) of the child
- The child PID can be used to send signals to the child process
- returns -1 on failure (invalid PID)

**Example**
```c
#include <stdlib.h>
#include <stdio.h>

int main() {
    pid_t pid = fork();
    int i;
    if (pid) {
        for (i=0; i<5; i++) {
            sleep(2);
            printf("parent process: %d\n");
        }
    } else {
        for (i=0; i<5; i++) {
            sleep(1);
            printf("child process: %d\n")
        }
    }
}

/*Output:
parent process: 0
child process: 1
child process: 2
parent process: 1
child process: 3
child process: 4
parent process: 2
parent process: 3
parent process: 4
```

- `fork()` makes a full copy of the parents address space
- `pid_t getpid()` returns PID of the current process
- `pid_t getppid()` returns PID of the parent process
- `wait(int*)` is used to wait for the child to finish
- `waitpid()` is used to wait for a specific child

Zombies
- the child process can exit before the parent
- stray process is marked as `<defunct>`
- `preap` can be used to reap zombie processes


#### Pipes
Pipes are used in unix to redirect output of one command to another. Pipes also allow parent processes to communicate with its children.
- `ls | more` - displays results of `ls` one screen at a time
- `cat file.txt | sort` - displays conttents of file.txt in sorted order

```c
int pipe(int FILEDS[2])
```
- A pipe can be thought of as a pair of file descriptors
- no physical file is associated with the file secriptor
- one end is opened in write mode
- other end is opened in read mode

**Example**
```c
//source: http://beej.us/guide
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <sys/types.h>
#include <unistd.h>

int main(void) {
    int pfds[2];
    char buf[30];
    pipe(pfds);
    if (!fork()) {
        printf("CHILD: writing to the pipe\n");
        write(pfds[1], "test", 5);
        printf("CHILD: exiting\n");
        exit(0);
    } else {
        printf("PARENT: reading from pipe\n");
        read(pfds[0], buf, 5);
        printf("PARENT: read \"%s\"\n", buf);
        wait(NULL);
    }
    return 0;
}
```

#### FIFO
- FIFO queues may be thought of as named pipes
- Multiple processes can read and write from a FIFO
- Unlike pipes, the processes can be unrelated
- FIFOs can be created using `mknod` system call

```c
int mknod(const char* path, mode_t mode, dev_t, dev);
```
- `<sys/stat.h>` contains the declaration for mknod
- `mknod` used to create *special* files - devices, fifos, etc
- `mode` can have special bits such as `S_IFIFO|0644`
- `dev` is intrepreted based on the mode
- example: `mknod("myfifo", S_IFIFO|0644, 0)`

**Example**
```c
//source: http://beej.us/guide
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <unistd.h>

#define FIFO_NAME "fifo"

int main(void) {
    char s[300];
    int num, fd;
    mknod(FIFO_NAME, S_FIFO|0660, 0);
    printf("waiting for readers...\n");
    fd = open(FIFO_NAME, O_WRONLY);
    printf("got a reader\n");

    while (gets(s), !feof(stdin)) {
        num = write(fd, s, strlen(s));
        if (num == -1)
            perror("write");
        else
            printf("wrote %d bytes\n", num);
    }
    return 0;
}
```
```c
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>

#define FIFO_NAME "fifo"

int main(void) {
    char s[300];
    int num, fd;
    mknod(FIFO_NAME, S_FIFO|0660, 0);
    printf("waiting for writers...\n");
    fd = open(FIFO_NAME, O_RDONLY);
    printf("got a writer\n");

    do {
        num = read(fd, s, 300);
        if (num == -1)
            perror("read");
        else {
            s[num] = '\0';
            printf("read %d bytes: \"%s\"\n", num, s);
        }
    } while (num > 0);
    return 0;
}
```

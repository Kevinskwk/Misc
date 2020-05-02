#include <stdio.h>
#include <stdlib.h>

struct node
{
    int data;
    struct node* next;
};

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

struct node* addfront(struct node* head, int data)
{
    struct node* p = nalloc(data);
    if (p==NULL)
        return head;
    p->next = head;
    return p;
}

void display(struct node* head)
{
    struct node* p = NULL;
    printf("list:");
    for (p = head; p != NULL; p = p->next)
        printf("%d ", p->data);
    printf("\n");
}

struct node* addback(struct node* head, int data)
{
    struct node* p = nalloc(data);
    struct node* curr = NULL;
    if (p == NULL)
        return head;
    //special case: empty list
    if (head == NULL)
    {
        head = p;
        return p;
    }
    else
    {
        //find last element
        for (curr = head; curr->next != NULL; curr = curr->next);
        curr->next = p;
        return head;
    }
}

struct node* find(struct node* head, int data)
{
    struct node* curr = NULL;
    for (curr = head; curr->next != NULL; curr = curr->next)
    {
        if (curr->data == data) return curr;
    }
    return NULL;
}

struct node* delnode(struct node* head, struct node* pelement)
{
    struct node* p = NULL;
    struct node* q = NULL;
    for (p = head; p != NULL && p != pelement; p = p->next)
        q = p; //follows p
    
    if (p == NULL) //not found
        return head;
    
    if (q == NULL) //head element
    {
        head = head->next;
        free(p);
    }

    else
    {
        q->next = p->next; //skip p
        free(p);
    }
    return head;    
}

void freelist(struct node* head)
{
    struct node* p = NULL;
    while (head)
    {
        p = head;
        head = head->next;
        free(p);
    }
}

int main()
{
    //test addfront
    struct node* head = NULL;
    struct node* np = NULL;
    puts("shoulde display empty");
    display(head);

    //test add front
    head = addfront(head, 10);
    head = addfront(head, 20);
    puts("should display 20, 10");
    display(head);

    //test free list
    freelist(head);
    head = NULL;
    puts("should display empty");
    display(head);

    //test add back
    head = addback(head, 10);
    head = addback(head, 20);
    head = addback(head, 30);
    puts("should display 10, 20, 30");
    display(head);

    //test find
    np = find(head, -20);
    puts("should display empty");
    display(np);

    np = find(head, 20);
    puts("should display 20, 30");
    display(np);

    //test delnode
    head = delnode(head, np);
    puts("should display 10,30");
    display(head);

    np = find(head, 10);
    head = delnode(head, np);
    puts("should display 30");
    display(head);

    //clean up
    freelist(head);
    return 0;
}
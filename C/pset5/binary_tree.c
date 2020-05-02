#include <stdio.h>
#include <stdlib.h>

struct tnode
{
    int data;
    struct tnode* left;
    struct tnode* right;
};

struct tnode* talloc(int data)
{
    struct tnode* p = (struct tnode*)malloc(sizeof(struct tnode));
    if (p != NULL)
    {
        p->data = data;
        p->left = p->right = NULL;
    }
    return p;
}

struct tnode* addnode(struct tnode* root, int data)
{
    struct tnode* p = NULL;
    /*termination condition*/
    if (root == NULL)
    {
        struct tnode* node = talloc(data);
        return (root=node);
    }
    /*recursive call*/
    else if (data < root->data)
        root->left = addnode(root->left, data);
    else
        root->right = addnode(root->right, data);
}

void preorder(struct tnode* root)
{
    if (root == NULL) return;
    printf("%d ", root->data);
    preorder(root->left);
    preorder(root->right);
}

void inorder(struct tnode* root)
{
    if (root == NULL) return;
    inorder(root->left);
    printf("%d ", root->data);
    inorder(root->right);
}

int deltree(struct tnode* root)
{
    int count = 0;
    if (root == NULL) return;
    count += deltree(root->right);
    count += deltree(root->left);
    free(root);
    return ++count;
}

int main()
{
    struct tnode* root = NULL;
    int count = 0;
    //adding elements
    root = addnode(root, 3);
    root = addnode(root, 1);
    root = addnode(root, 0);
    root = addnode(root, 2);
    root = addnode(root, 8);
    root = addnode(root, 6);
    root = addnode(root, 5);
    root = addnode(root, 9);

    //test preorder
    puts("should print 3, 1, 0, 2, 8, 5, 9");
    preorder(root);
    puts("\n");

    //test inorder
    puts("should print 0, 1, 2, 3, 5, 6, 8, 9");
    inorder(root);
    puts("\n");

    //test deltree
    count = deltree(root);
    root = NULL;
    puts("should expect 8 nodes deleted");
    printf("%d nodes deleted\n", count);
    return 0;
}

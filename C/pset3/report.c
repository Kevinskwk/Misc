#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define STRSIZE 100
#define NFIELDS 9

int main()
{
    char inputFile[] = "stateoutflow0708.txt";

    char state_code_org[STRSIZE];
    char county_code_ori[STRSIZE];
    char state_code_dest[STRSIZE];
    char county_code_dest[STRSIZE];
    char state_abbrv[STRSIZE];
    char state_name[STRSIZE];
    char line[STRSIZE*NFIELDS];
    int unsigned return_num = 0;
    int unsigned exempt_num = 0;
    int unsigned aggr_agi = 0;
    long unsigned total = 0;

    int fields_read = 0;
    FILE* fp = fopen(inputFile, "r");
    if (fp==NULL) {
        fprintf(stderr, "Cannot open file\n");
        exit(-1);
    }

    fgets(line, STRSIZE*NFIELDS, fp);

    printf("%-30s %6s\n", "STATE", "TOTAL");
    printf("---------------------------------------\n");
    while(fgets(line, STRSIZE*NFIELDS, fp))
    {
        fields_read = sscanf(line, "%s %s %s %s %s %s %d %d %d",
                             state_code_org,
                             county_code_ori,
                             state_code_dest,
                             county_code_dest,
                             state_abbrv,
                             state_name,
                             &return_num,
                             &exempt_num,
                             &aggr_agi);
        if(strcmp(state_code_org, "\"25\"")==0)
        {
            printf("%-30s %6i\n", state_name, aggr_agi);
            total += aggr_agi;
        }
    }

    printf(" ----------------------------------------\n");
    printf("%-30s %6lu\n", "Total", total);
    fclose(fp);
    return 0;
}
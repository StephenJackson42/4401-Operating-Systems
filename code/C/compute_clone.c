#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sched.h>

#define MAX 300

typedef struct {
    char id;
	int loop_cnt;
} parameter_t;

void compute_thread( void *params) {
    int i, j, k, loop_cnt;
    double a[MAX][MAX], b[MAX][MAX], c[MAX][MAX];
    for( i=0; i<MAX; i++) {
        for( j=0; j<MAX; j++) {
            a[i][j] = (i+1)*(j+1);
            a[i][j] = (i+2)*(j+2);
        }
    }
    loop_cnt = ((parameter_t *)params)->loop_cnt;
    for( ; loop_cnt>0; loop_cnt--) {
        for( i=0; i<MAX; i++)
            for( j=0; j<MAX; j++)
               for( k=0; k<MAX; k++)
                    c[i][j] += a[i][k]*b[k][j];
        printf( "%d", ((parameter_t *)params)->id);
    }
}

void main( int argc, char* argv[]) {
	int i, n;
	pthread_t *threads;
	parameter_t *args;

	n = atoi( argv[1]);

	threads = (pthread_t *) malloc( n*sizeof( pthread_t *));

	args = (parameter_t *) malloc( sizeof(parameter_t)*n);
	for (i=0; i<n; i++) {
		args[i].id = i;
        args[i].loop_cnt = 50;
		pthread_create( &threads[i], NULL, compute_thread, (void *)(args+i));
	}
	for (i=0; i<n; i++) {
		pthread_join( threads[i], NULL);
	}
    printf( "\n");
}

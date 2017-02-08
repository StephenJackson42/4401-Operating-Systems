#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

void syscall_failed( char *message) {	
	fprintf( stderr, "%s failed.\n", message);
	exit(-1);
}

int main( int argc, char *argv[]) {
	pid_t  pid;

	pid = fork();          /* fork another process */
	if (pid < 0) 	   		/* error occurred */
		syscall_failed( "fork()");

	else if (pid == 0) { 	/* child process */
		printf( "--- child ---\n");
		execvp( argv[1], argv+1);
		printf( "=== exec() error! ===\n");
		

	} else {               /* parent process */
		wait (pid);    	/* wait for the child */
		printf( "parent>>\n");
	}
	return 0;
}

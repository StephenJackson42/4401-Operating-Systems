#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <unistd.h>

void syscall_failed( char *message) {	
	fprintf( stderr, "%s failed.\n", message);
	exit(-1);
}

int main( int argc, char *argv[]) {
	int	   fd[2];
	pid_t  pid;

	if( pipe( fd) < 0)
		syscall_failed( "pipe()");
	
	pid = fork();          /* fork another process */
	if (pid < 0) 	   		/* error occurred */
		syscall_failed( "fork()");

	else if (pid == 0) { 	/* child process */
		// Close stdin, duplicate the input side of pipe to stdin
		dup2( 0, fd[0]);
		// Write message to pipe
		execlp( "sort", "sort", NULL);

	} else {               /* parent process */
		// Wait for child
		wait( pid);
        printf( " --- parent ---\n");
	}
	return 0;
}

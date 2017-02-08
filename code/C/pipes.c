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
	char   buffer[64];
	pid_t  pid;

	if( pipe( fd) < 0)
		syscall_failed( "pipe()");
	
	pid = fork();          /* fork another process */
	if (pid < 0) 	   		/* error occurred */
		syscall_failed( "fork()");

	else if (pid == 0) { 	/* child process */
		// Close pipe input
		close( fd[0]);
		// Write message to pipe
		write( fd[1], argv[1], strlen(argv[1])+1);
		exit( 0);

	} else {               /* parent process */
		// Close pipe output
		close( fd[1]);
		read( fd[0], buffer, sizeof( buffer));
        printf( "parent> Received message: %s\n", buffer);
	}
	return 0;
}

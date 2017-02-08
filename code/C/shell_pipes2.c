/*
 * Executes the shell command "ls -l | grep shm | wc -l"
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <unistd.h>

void syscall_failed( char *message) {	
	fprintf( stderr, "%s failed.\n", message);
	exit(-1);
}

int main(void) {
    int fd1[2], fd2[2];
	if( pipe( fd1) < 0 || pipe( fd2) < 0)
		syscall_failed( "pipe()");

	// Launch "ls -l"
    pid_t pid = fork();
    if( pid < 0)
		syscall_failed( "fork()");
    else if (pid == 0) {
        dup2( fd1[1], 1);
        char *argv[] = { "ls", "-l", NULL};
        execvp( "ls", argv);
    } else {
        close( fd1[1]);
    }

	// Launch "grep shm"
    pid = fork();
    if( pid < 0)
		syscall_failed( "fork()");
    else if (pid == 0) {
        dup2( fd1[0], 0);
        dup2( fd2[1], 1);
        char *argv[] = {"grep", "shm", NULL};
        execvp( "grep", argv);
    } else {
		close( fd2[1]);
    }
    
    // Launch "wc -l"
    pid = fork();
    if (pid == 0) {
        dup2( fd2[0], 0);
        char *argv[] = {"wc", "-l", NULL};
        execvp( "wc", argv);
    } 

    waitpid( pid);
}

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/mman.h>
#include <sys/types.h>
#include <fcntl.h>

#define SHARED_LEN  1024
#define SHARED_NAME "shared_memory"

void syscall_failed( char *message)	{
	fprintf( stderr, "%s failed.\n", message);
	exit(-1);
}

int main( int argc, char *argv[]) {
	int 	fd; 		// File descriptor of shared segment
	char   *shared;		// Pointer to shared segment (as string)

	// Create shared object
	fd = shm_open( SHARED_NAME, O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);
	if( fd == -1)
		syscall_failed( "shm_open()");

	// Set the size of the shared object
	if( ftruncate( fd, SHARED_LEN) == -1)
		syscall_failed( "ftruncate()");
		
	// Map shared memory object to this process' address space
	shared = mmap( NULL, SHARED_LEN, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
	if( shared == MAP_FAILED)
		syscall_failed( "mmap()");
		
	sprintf( shared, "%s", argv[1]);
	sleep( 10);
	if( shm_unlink( SHARED_NAME) == -1)
		syscall_failed( "shm_unlink()");
}

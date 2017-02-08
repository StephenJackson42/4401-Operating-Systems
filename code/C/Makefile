#
# CSCI 4401 C examples
#

# Instructions
# 1. To compile, run "make", or "make all"
# 2. To run examples, type "make <example>-run", where <example is one of 
#       the *-run targets: fork-run, shm-run, etc.

all: fork shm pipes compute_pthreads compute_clone

# fork: process creation
fork: shell_fork.c
	gcc -o shell_fork shell_fork.c

fork-run: 
	./shell_fork ls -l
	./shell_fork grep main *.c

# shm: shared memory
shm: shm_server.c shm_client.c
	gcc -o shm_server shm_server.c -lrt
	gcc -o shm_client shm_client.c -lrt

shm-run:
	./shm_server "hello there" &
	./shm_client
	
# pipes: pipe communication
pipes: pipes.c shell_pipes.c shell_pipes2.c
	gcc -o pipes pipes.c
	gcc -o shell_pipes shell_pipes.c
	gcc -o shell_pipes2 shell_pipes2.c

pipes-run:
	./pipes "hello pipes!"
	./shell_pipes < alice.txt
	./shell_pipes2

# compute_threads: simple p-threads demo
compute_pthreads: compute_pthreads.c
	gcc -o compute_pthreads compute_pthreads.c -lpthread 

# compute_clone: linux-specific threads
compute_clone: compute_clone.c
	gcc -o compute_clone compute_clone.c -pthread
	
# cleanup
clean:
	rm compute_clone compute_pthreads pipes shell_fork shell_pipes shell_pipes2 shm_client shm_server


CC=g++
CFLAGS=-I.
vm_sim: vm_sim.o intro.o 
     $(CC) -o vm_sim vm_sim.o intro.o

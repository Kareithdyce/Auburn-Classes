CC=g++
CFLAGS=-I.

vm_sim: intro.o vm_sim.o
     $(CC) -o vm_sim intro.o vm_sim.o
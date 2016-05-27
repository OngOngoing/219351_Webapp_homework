/* -*- Mode: C; c-basic-offset:4 ; -*- */
/*  
 *  (C) 2007 by Argonne National Laboratory.
 *      See COPYRIGHT in top-level directory.
 */

/* This file creates strings for the most important configuration options.
   These are then used in the file src/mpi/init/initthread.c to initialize
   global variables that will then be included in both the library and 
   executables, providing a way to determine what version and features of
   MPICH2 were used with a particular library or executable. 
*/
#ifndef MPICH2INFO_H_INCLUDED
#define MPICH2INFO_H_INCLUDED

#define MPICH2_CONFIGURE_ARGS_CLEAN "--disable-f77 --disable-fc --disable-cxx"
#define MPICH2_VERSION_DATE "Thu Jun 16 16:41:08 CDT 2011"
#define MPICH2_DEVICE "ch3:nemesis"
#define MPICH2_COMPILER_CC "gcc    -O2"
#define MPICH2_COMPILER_CXX "  "
#define MPICH2_COMPILER_F77 "  "
#define MPICH2_COMPILER_FC "  "

#endif

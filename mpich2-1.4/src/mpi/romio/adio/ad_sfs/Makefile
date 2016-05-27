CC          = gcc
AR          = ar cr
LIBNAME     = /home/kumamon/Documents/paruj/219351_Webapp_homework/mpich2-1.4/lib/libmpich.a
srcdir      = /home/kumamon/Documents/paruj/219351_Webapp_homework/mpich2-1.4/src/mpi/romio/adio/ad_sfs

INCLUDES    = -I. -I${srcdir}/../include -I../include -I../../include  -I${srcdir}/../../../../include -I../../../../include
CFLAGS      =  -I/home/kumamon/Documents/paruj/219351_Webapp_homework/mpich2-1.4/src/mpl/include -I/home/kumamon/Documents/paruj/219351_Webapp_homework/mpich2-1.4/src/mpl/include -I/home/kumamon/Documents/paruj/219351_Webapp_homework/mpich2-1.4/src/openpa/src -I/home/kumamon/Documents/paruj/219351_Webapp_homework/mpich2-1.4/src/openpa/src -I/home/kumamon/Documents/paruj/219351_Webapp_homework/mpich2-1.4/src/mpid/ch3/include -I/home/kumamon/Documents/paruj/219351_Webapp_homework/mpich2-1.4/src/mpid/ch3/include -I/home/kumamon/Documents/paruj/219351_Webapp_homework/mpich2-1.4/src/mpid/common/datatype -I/home/kumamon/Documents/paruj/219351_Webapp_homework/mpich2-1.4/src/mpid/common/datatype -I/home/kumamon/Documents/paruj/219351_Webapp_homework/mpich2-1.4/src/mpid/common/locks -I/home/kumamon/Documents/paruj/219351_Webapp_homework/mpich2-1.4/src/mpid/common/locks -I/home/kumamon/Documents/paruj/219351_Webapp_homework/mpich2-1.4/src/mpid/ch3/channels/nemesis/include -I/home/kumamon/Documents/paruj/219351_Webapp_homework/mpich2-1.4/src/mpid/ch3/channels/nemesis/include -I/home/kumamon/Documents/paruj/219351_Webapp_homework/mpich2-1.4/src/mpid/ch3/channels/nemesis/nemesis/include -I/home/kumamon/Documents/paruj/219351_Webapp_homework/mpich2-1.4/src/mpid/ch3/channels/nemesis/nemesis/include -I/home/kumamon/Documents/paruj/219351_Webapp_homework/mpich2-1.4/src/mpid/ch3/channels/nemesis/nemesis/utils/monitor -I/home/kumamon/Documents/paruj/219351_Webapp_homework/mpich2-1.4/src/mpid/ch3/channels/nemesis/nemesis/utils/monitor -I/home/kumamon/Documents/paruj/219351_Webapp_homework/mpich2-1.4/src/util/wrappers -I/home/kumamon/Documents/paruj/219351_Webapp_homework/mpich2-1.4/src/util/wrappers    -O2  -D_LARGEFILE64_SOURCE -D_FILE_OFFSET_BITS=64 -DHAVE_ROMIOCONF_H -D_LARGEFILE64_SOURCE -D_FILE_OFFSET_BITS=64 $(INCLUDES)

VPATH=.:${srcdir}

AD_SFS_OBJECTS = \
      ad_sfs_open.o \
      ad_sfs_fcntl.o \
      ad_sfs_flush.o \
      ad_sfs.o

default: $(LIBNAME)

.c.o:
	$(CC) $(CFLAGS) -c $<

$(LIBNAME): $(AD_SFS_OBJECTS)
	$(AR) $(LIBNAME) $(AD_SFS_OBJECTS)

clean:
	@rm -f *.o
